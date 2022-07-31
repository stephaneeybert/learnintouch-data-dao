package com.thalasoft.learnintouch.data.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({ "classpath:data-source.properties" })
public class DatabaseProperties {

    @Value("${hibernate.dialect}")
    private String hibernateDialect;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHbm2ddlAuto;
    @Value("${dataSource.driverClassName}")
    private String hibernateDriverClassName;
    @Value("${dataSource.url}")
    private String dataSourceUrl;
    @Value("${dataSource.username}")
    private String dataSourceUsername;
    @Value("${dataSource.password}")
    private String dataSourcePassword;

    public String getHibernateDialect() {
        return hibernateDialect;
    }

    public String getHibernateHbm2ddlAuto() {
        return hibernateHbm2ddlAuto;
    }

    public String getHibernateDriverClassName() {
        return hibernateDriverClassName;
    }

    public String getDataSourceUrl() {
        return dataSourceUrl;
    }

    public String getDataSourceUsername() {
        return dataSourceUsername;
    }

    public String getDataSourcePassword() {
        return dataSourcePassword;
    }

}
