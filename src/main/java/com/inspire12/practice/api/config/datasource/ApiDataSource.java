package com.inspire12.practice.api.config.datasource;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;


//https://www.baeldung.com/spring-data-jpa-multiple-databases
@Configuration
public class ApiDataSource {
    public final static String TX_MANAGER ="transactionManager";

    public static final String PRIMARY_DATASOURCE = "primaryDataSource";
    public static final String SLAVE_DATASOURCE = "slaveDataSource";

    @Bean
    @Primary
    @DependsOn({PRIMARY_DATASOURCE, SLAVE_DATASOURCE})
    public DataSource routingDataSource(
            @Qualifier(PRIMARY_DATASOURCE) DataSource primaryDataSource,
            @Qualifier(SLAVE_DATASOURCE) DataSource slaveDataSource) {

        RoutingDataSource routingDataSource = new RoutingDataSource();

        Map<Object, Object> datasourceMap = new HashMap<>() {
            {
                put("master", primaryDataSource);
                put("slave", slaveDataSource);
            }
        };

        routingDataSource.setTargetDataSources(datasourceMap);
        routingDataSource.setDefaultTargetDataSource(primaryDataSource);

        return routingDataSource;
    }

    @Bean
    @DependsOn("routingDataSource")
    public LazyConnectionDataSourceProxy dataSource(DataSource routingDataSource){
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }


    @Bean
    @ConfigurationProperties("spring.datasource.primary")
    public DataSourceProperties primaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.slave")
    public DataSourceProperties slaveDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(PRIMARY_DATASOURCE)
    public DataSource primaryDataSource() {
        return primaryDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(SLAVE_DATASOURCE)
    public DataSource slaveDataSource() {
        return slaveDataSourceProperties().initializeDataSourceBuilder().build();
    }

    public static class RoutingDataSource extends AbstractRoutingDataSource {
        @Override
        protected Object determineCurrentLookupKey() { // (1)
            return (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) ? "slave" : "master"; //(2)
        }
    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//
//        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
//
//        entityManager.setDataSource(dataSource());
//        entityManager.setPackagesToScan(new String[]{"com.inspire12.practice.api.domain"});
//        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        entityManager.setJpaPropertyMap(new HashMap<String, String>() {{
//            put("hibernate.hbm2ddl.auto", "create");
//            put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//            put("hibernate.id.new_generator_mappings", "false");
//        }});
//        return entityManager;
//    }
//
//    @Bean
//    PlatformTransactionManager transactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//        return transactionManager;
//    }
//
//    @Bean(SLAVE_DATASOURCE)
//    @ConfigurationProperties(prefix = "slave.datasource")
//    public DataSource slaveDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean slaveEntityManagerFactory() {
//
//        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
//        entityManager.setDataSource(slaveDataSource());
//        entityManager.setPackagesToScan(new String[]{"com.inspire12.practice.api.domain"});
//        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        entityManager.setJpaPropertyMap(new HashMap<String, String>() {{
//            put("hibernate.hbm2ddl.auto", "create");
//            put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//            put("hibernate.id.new_generator_mappings", "false");
//        }});
//        return entityManager;
//    }


}
