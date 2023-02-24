package com.inspire12.practice.api.presentation.controller.sample.ticker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StopWatch;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
@Repository
public class TestRepository {

    private final JdbcTemplate jdbcTemplate;

    public <T> void bulkUpsert(Class<?> clz, List<T> dataList) {
        if (dataList.isEmpty()) {
            return;
        }
        String query = createQuery(clz);
        int fieldCount = dataList.get(0).getClass().getDeclaredFields().length;

        StopWatch timer = new StopWatch();
        timer.start();
        jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) {
                T data = dataList.get(i);
                AtomicInteger index = new AtomicInteger(1);
                ReflectionUtils.doWithFields(clz, field -> {
                    field.setAccessible(true);
                    try {
                        ps.setObject(index.get(), ReflectionUtils.getField(field, data));
                        ps.setObject(index.get() + fieldCount, ReflectionUtils.getField(field, data));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    index.getAndIncrement();
                });
            }

            @Override
            public int getBatchSize() {
                return dataList.size();
            }
        });
        timer.stop();
        log.info("batchInsert -> Total time in seconds: " + timer.getTotalTimeSeconds());

    }

    private String createQuery(Class<?> clz) {
        StringBuilder fieldListStr = new StringBuilder(" (");
        AtomicInteger fieldCount = new AtomicInteger();
        StringBuilder updateQuery = new StringBuilder();

        ReflectionUtils.doWithFields(clz, field -> {
//            String columnName = field.getDeclaredAnnotation(Column.class) != null ? field.getDeclaredAnnotation(Column.class).name() : field.getName();
            fieldListStr.append(field.getName()).append(",");
            updateQuery.append(field.getName()).append("=?").append(",");
            fieldCount.getAndIncrement();
        });
        if (!fieldListStr.isEmpty()) {
            fieldListStr.deleteCharAt(fieldListStr.length() - 1);
        }
        fieldListStr.append(")");
        fieldListStr.append(" ");

        if (!updateQuery.isEmpty()) {
            updateQuery.deleteCharAt(updateQuery.length() - 1);
        }

        StringBuilder values = new StringBuilder("(");
        for (int i = 0; i < fieldCount.get(); i++) {
            values.append("?,");
        }
        if (values.length() > 0) {
            values.deleteCharAt(values.length() - 1);
        }
        values.append(")");


        String query = """
                INSERT INTO %s %s values %s ON DUPLICATE KEY UPDATE %s
                """.formatted(clz.getSimpleName(), fieldListStr, values, updateQuery);

        System.out.println(query);

        return query;
    }
}
