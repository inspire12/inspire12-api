package com.inspire12.practice.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;


@EnableJpaAuditing
@Configuration
public class JpaCommonConfig {

    //https://www.marcobehler.com/guides/spring-transaction-management-transactional-in-depth#_how_spring_and_jpa_hibernate_transaction_management_works
    
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public TransactionTemplate transactionTemplate() {
        return new TransactionTemplate(transactionManager);
    }
}
