package com.thalasoft.learnintouch.data.config;

import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@Import({ DatabaseProperties.class, Log4j.class })
@EnableTransactionManagement
public class ApplicationContext {

    @Autowired
    private DatabaseProperties databaseProperties;

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(databaseProperties.getHibernateDriverClassName());
        dataSource.setJdbcUrl(databaseProperties.getDataSourceUrl());
        dataSource.setUser(databaseProperties.getDataSourceUsername());
        dataSource.setPassword(databaseProperties.getDataSourcePassword());
        dataSource.setAcquireIncrement(5);
        dataSource.setMaxStatementsPerConnection(20);
        dataSource.setMaxStatements(100);
        dataSource.setMinPoolSize(2);
        dataSource.setMaxPoolSize(5);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws PropertyVetoException {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabasePlatform(databaseProperties.getHibernateDialect());
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setGenerateDdl(false);

        Map<String, String> jpaPropertiesMap = new HashMap<String, String>();
        jpaPropertiesMap.put("hibernate.dialect", databaseProperties.getHibernateDialect());
        jpaPropertiesMap.put("hibernate.use_sql_comments", "true");
        jpaPropertiesMap.put("hibernate.show_sql", "true");
        jpaPropertiesMap.put("hibernate.format_sql", "true");
        jpaPropertiesMap.put("hibernate.hbm2ddl.auto", databaseProperties.getHibernateHbm2ddlAuto());
        jpaPropertiesMap.put("hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory");
        jpaPropertiesMap.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
        jpaPropertiesMap.put("hibernate.c3p0.min_size", "5");
        jpaPropertiesMap.put("hibernate.c3p0.max_size", "20");
        jpaPropertiesMap.put("hibernate.c3p0.timeout", "1000");
        jpaPropertiesMap.put("hibernate.c3p0.max_statements", "50");
        jpaPropertiesMap.put("hibernate.ejb.interceptor", "com.thalasoft.learnintouch.data.jpa.AuditLogInterceptor");
        
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPackagesToScan("com.thalasoft.learnintouch.data.jpa.domain");
        factoryBean.setJpaPropertyMap(jpaPropertiesMap);
        String[] mappingsResources = new String[] {"com/thalasoft/learnintouch/data/custom/typedef.hbm.xml"};        
        factoryBean.setMappingResources(mappingsResources);
        factoryBean.setDataSource(dataSource());
        return factoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        try {
            transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        } catch (Exception x) {
            throw new RuntimeException(x);
        }
        return transactionManager;
    }

    @Bean 
    public HibernateExceptionTranslator hibernateExceptionTranslator(){ 
      return new HibernateExceptionTranslator(); 
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
       return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
    	return new PersistenceExceptionTranslationPostProcessor();
    }

}
