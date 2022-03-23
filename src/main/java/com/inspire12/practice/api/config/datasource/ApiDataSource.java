package com.inspire12.practice.api.config.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.HashMap;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


//https://www.baeldung.com/spring-data-jpa-multiple-databases

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.inspire12.practice.api.domain.user",
    entityManagerFactoryRef = "entityManagerFactory",
    transactionManagerRef = ApiDataSource.TX_MANAGER
)
public class ApiDataSource {
    public final static String TX_MANAGER ="transactionManager";

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource.primary")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        DataSource dataSource = dataSource();
        entityManager.setDataSource(dataSource);
        entityManager.setPackagesToScan(new String[]{"com.inspire12.practice.api.domain"});
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManager.setJpaPropertyMap(new HashMap<String, String>() {{
            put("hibernate.hbm2ddl.auto", "create");
            put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
            put("hibernate.id.new_generator_mappings", "false");
        }});
        return entityManager;
    }

    @Bean
    @Primary
    PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
