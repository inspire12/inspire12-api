package com.inspire12.practice.api.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BulkInsertRepository<T> {
    @Transactional
    List<T> bulkInsert(List<T> list);
}
