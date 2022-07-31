package com.thalasoft.learnintouch.data.config.jndi;

import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.HSQLDialect;
import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.springframework.util.StringUtils;

public enum DatabaseDrivers {

    H2(H2Dialect.class.getName(), org.h2.Driver.class.getName()),
    HSQLDB(HSQLDialect.class.getName(), org.hsqldb.jdbcDriver.class.getName()),
    MYSQL(MySQL5InnoDBDialect.class.getName(), com.mysql.jdbc.Driver.class.getName());

    public static DatabaseDrivers forName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("No name was specified as database id.");
        }
        try {
            return Enum.valueOf(DatabaseDrivers.class, name);
        } catch (Exception x) {
            throw new IllegalArgumentException("The specified name " + name + " is unknown as a database id.");
        }
    }

    private final String jdbcDriver;
    private final String hibernateDialect;

    private DatabaseDrivers(String hibernateDialect, String jdbcDriver) {
        this.hibernateDialect = hibernateDialect;
        this.jdbcDriver = jdbcDriver;
    }

    public String getHibernateDialect() {
        return hibernateDialect;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

}
