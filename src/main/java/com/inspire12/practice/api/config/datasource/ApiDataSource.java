package com.inspire12.practice.api.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import java.util.HashMap;
import javax.sql.DataSource;
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


@Configuration
//@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.inspire12.practice.api.domain.user",
    entityManagerFactoryRef = "entityManagerFactory",
    transactionManagerRef = "transactionManager"
)
public class ApiDataSource {

    @Bean
    @ConfigurationProperties(prefix = "primary.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }


    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource());
        entityManager.setPackagesToScan(new String[]{"com.inspire12.practice.api.domain"});
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        entityManager.setJpaPropertyMap(new HashMap<String, String>() {{
            put("hibernate.hbm2ddl.auto", "none");
            put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
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
