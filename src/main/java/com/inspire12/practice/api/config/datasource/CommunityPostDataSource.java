package com.inspire12.practice.api.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.inspire12.practice.api.domain.posts",
    entityManagerFactoryRef = "communityEntityManagerFactory",
    transactionManagerRef = CommunityPostDataSource.TX_MANAGER
)
public class CommunityPostDataSource {
    public final static String TX_MANAGER ="communityTransactionManager";

    @Bean
    @ConfigurationProperties(prefix = "community.datasource")
    public DataSource communityDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean communityEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(communityDataSource());
        entityManager.setPackagesToScan(new String[]{"com.inspire12.practice.api.domain.posts"});
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        entityManager.setJpaPropertyMap(new HashMap<String, String>() {{
            put("hibernate.hbm2ddl.auto", "create");
            put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
            put("hibernate.id.new_generator_mappings", "false");
        }});
        return entityManager;
    }

    @Bean
    PlatformTransactionManager communityTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(communityEntityManagerFactory().getObject());
        return transactionManager;
    }
}
