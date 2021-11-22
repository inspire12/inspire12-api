//package com.inspire12.practice.api.config.datasource;
//
//import com.zaxxer.hikari.HikariDataSource;
//import java.util.HashMap;
//import javax.sql.DataSource;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@EnableJpaRepositories(
//    basePackages = "com.inspire12.practice.api.domain.posts",
//    entityManagerFactoryRef = "communityEntityManagerFactory",
//    transactionManagerRef = "communityTransactionManager"
//)
//public class CommunityDataSource {
//    @Bean("communityDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.paige-community")
//    public DataSource communityDataSource() {
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }
//
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean communityEntityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
//        entityManager.setDataSource(communityDataSource());
//        entityManager.setPackagesToScan(new String[]{"com.inspire12.practice.api.domain.post"});
//        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//
//        entityManager.setJpaPropertyMap(new HashMap<String, String>() {{
//            put("hibernate.hbm2ddl.auto", "none");
//            put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//            put("hibernate.id.new_generator_mappings", "false");
//        }});
//        return entityManager;
//    }
//
//    @Bean("communityTransactionManager")
//    PlatformTransactionManager communityTransactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(communityEntityManagerFactory().getObject());
//        return transactionManager;
//    }
//}
