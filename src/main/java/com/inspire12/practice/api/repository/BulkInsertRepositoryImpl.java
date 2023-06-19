package com.inspire12.practice.api.repository;


import com.inspire12.practice.api.utils.TimeCheck;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
public class BulkInsertRepositoryImpl<T> implements BulkInsertRepository<T> {

    private final JdbcTemplate jdbcTemplate;

    @TimeCheck
    @Transactional
    public List<T> bulkInsert(List<T> list) {
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        Class<?> tClass = list.get(0).getClass();
        int fieldCount = tClass.getDeclaredFields().length - 2;
        List<String> autoSavedDateFormat = List.of("updatedDate", "createdDate");
        jdbcTemplate.batchUpdate(getQuery(tClass), new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) {
                T rowData = list.get(i);
                AtomicInteger index = new AtomicInteger(1);
                ReflectionUtils.doWithFields(tClass, field -> {
                    field.setAccessible(true);
                    try {
                        Object columnData = ReflectionUtils.getField(field, rowData);
                        // enum 일 경우 처리
                        if (field.isAnnotationPresent(Enumerated.class)) {
                            if (EnumType.STRING.equals(field.getAnnotation(Enumerated.class).value())) {
                                columnData = ((Enum) ReflectionUtils.getField(field, rowData)).name();
                            } else {
                                columnData = ((Enum) ReflectionUtils.getField(field, rowData)).ordinal();
                            }
                        }

                        if (!autoSavedDateFormat.contains(field.getName())) {
                            ps.setObject(index.get(), columnData);
                            ps.setObject(index.get() + fieldCount, columnData);
                        }

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    index.getAndIncrement();
                });
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });

        return list;

    }


    String getQuery(Class<?> tClass) {
        List<String> getColumnNames = getColumnNames(tClass);
        List<String> insertPart = new ArrayList<>();
        List<String> updatePart = new ArrayList<>();
        for (String column : getColumnNames) {

            if ("createdDate".equals(column)) {
                insertPart.add("now()");

            } else if ("updatedDate".equals(column)) {
                insertPart.add("now()");
                updatePart.add(column + "=now()");
            } else {
                insertPart.add("?");
                updatePart.add(column + "=?");
            }

        }

        String columns = String.join(",", getColumnNames);
        String insertUpdatePart = String.join(",", insertPart);
        String updatePartStr = String.join(",", updatePart);

        String sql = String.format("""
                INSERT INTO %s (%s) values (%s) ON DUPLICATE KEY UPDATE %s
                """, tClass.getSimpleName(), columns, insertUpdatePart, updatePartStr);
        return sql;
    }

    private List<String> getColumnNames(Class<?> clz) {

        List<String> columns = new ArrayList<>();
        ReflectionUtils.doWithFields(clz, field -> {
            String columnName = field.getName();
            if (field.isAnnotationPresent(Column.class) && !StringUtils.isBlank(field.getAnnotation(Column.class).name())) {
                columnName = field.getAnnotation(Column.class).name();
            }

            columns.add(columnName);
        });
        return columns;
    }
}
