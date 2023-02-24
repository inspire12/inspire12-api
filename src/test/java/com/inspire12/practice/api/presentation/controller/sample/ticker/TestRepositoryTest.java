package com.inspire12.practice.api.presentation.controller.sample.ticker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class TestRepositoryTest {


    TestRepository testRepository;

    @MockBean
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        testRepository = new TestRepository(jdbcTemplate);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void bulkUpsert() {
        testRepository.bulkUpsert(TickerTrade.class,
                List.of(TickerTrade
                        .builder()
                        .close(1L)
                        .deliveryTime(LocalDateTime.now())
                        .mktCap(1L)
                        .netChg(1L)
                        .pctChg(1d)
                        .stockCode("1")
                        .stockMkt("1")
                        .stockName("1")
                        .pctChg(1d)
                        .tradeDate(LocalDate.now())

                        .build()));
    }
}