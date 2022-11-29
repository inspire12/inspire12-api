package com.inspire12.practice.api.config.datasource;

import com.inspire12.practice.api.domain.posts.Posts;
import com.inspire12.practice.api.domain.user.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "apiEntityManagerFactory",
        transactionManagerRef = "apiTransactionManager",
        basePackages = {"com.inspire12.practice.api.domain"}
)
public class ApiJpaConfig {
    @Bean
    public LocalContainerEntityManagerFactoryBean apiEntityManagerFactory(
            @Qualifier("routingDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource)
                .packages("com.inspire12.practice.api.domain")
                .build();
    }

    @Bean
    public PlatformTransactionManager apiTransactionManager(
            @Qualifier("apiEntityManagerFactory") LocalContainerEntityManagerFactoryBean apiEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(apiEntityManagerFactory.getObject()));
    }

}
