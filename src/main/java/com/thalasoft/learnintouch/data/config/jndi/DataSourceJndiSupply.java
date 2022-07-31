package com.thalasoft.learnintouch.data.config.jndi;

import java.beans.PropertyVetoException;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.util.StringUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public final class DataSourceJndiSupply {

    private final DataSource dataSource;
    private final DatabaseDrivers databaseId;

    public DataSourceJndiSupply() {
        DatabaseConfigInfo configInfo = (DatabaseConfigInfo) Jndi.lookupNotNull("databaseConfigInfo");
        databaseId = DatabaseDrivers.forName(configInfo.getDatabaseId());
        switch (databaseId) {
        case H2: {
            String sqlInitFile = validateNonblankString(configInfo.getSqlInitFile(), "sqlInitFile");
            dataSource = new EmbeddedDatabaseBuilder().setName("testdb").addScript(sqlInitFile).build();
            break;
        }
        case HSQLDB: {
            String sqlInitFile = validateNonblankString(configInfo.getSqlInitFile(), "sqlInitFile");
            dataSource = new EmbeddedDatabaseBuilder().setName("testdb").addScript(sqlInitFile).build();
            break;
        }
        case MYSQL: {
            dataSource = newComboPooledDataSource(configInfo);
            break;
        }
        default: {
            throw new RuntimeException();
        }
        }
    }

    public DatabaseDrivers getDatabaseId() {
        return databaseId;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    private DataSource newComboPooledDataSource(DatabaseConfigInfo configInfo) {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(databaseId.getJdbcDriver());
        } catch (PropertyVetoException x) {
            throw new RuntimeException(x);
        }
        dataSource.setJdbcUrl(validateNonblankString(configInfo.getJdbcUrl(), "jdbcUrl"));
        dataSource.setUser(validateNonblankString(configInfo.getUser(), "user"));
        dataSource.setPassword(validateNonblankString(configInfo.getPassword(), "password"));
        dataSource.setAutomaticTestTable(configInfo.getAutomaticTestTable());
        dataSource.setMinPoolSize(configInfo.getMinPoolSize());
        dataSource.setMaxPoolSize(configInfo.getMaxPoolSize());
        dataSource.setMaxIdleTime(configInfo.getMaxIdleTime());
        dataSource.setIdleConnectionTestPeriod(configInfo.getIdleConnectionTestPeriod());
        dataSource.setCheckoutTimeout(configInfo.getCheckoutTimeout());
        return dataSource;
    }

    private static String validateNonblankString(String str, String name) {
        if (StringUtils.isEmpty(str)) {
            throw new IllegalStateException("No `".concat(name).concat("' specified."));
        }
        return str;
    }

}