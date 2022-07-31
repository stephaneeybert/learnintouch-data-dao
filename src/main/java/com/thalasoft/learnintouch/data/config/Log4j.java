package com.thalasoft.learnintouch.data.config;

import java.io.IOException;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.net.SMTPAppender;
import org.apache.log4j.varia.LevelRangeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Log4j {

    public ConsoleAppender consoleAppender() {
    	PatternLayout patternLayout = new PatternLayout();
    	patternLayout.setConversionPattern("%d %-5p  [%c{1}] %m %n");
        ConsoleAppender consoleAppender = new ConsoleAppender(patternLayout);
        consoleAppender.setName("consoleAppender");
        consoleAppender.setThreshold(Level.ALL);
        return consoleAppender;
    }
    
    public FileAppender fileAppender() throws IOException {
        PatternLayout patternLayout = new PatternLayout();
        patternLayout.setConversionPattern("%d %-5p  [%c{1}] %m %n");
        DailyRollingFileAppender fileAppender = new DailyRollingFileAppender(patternLayout, "build.log", "'.'yyyy-MM");
        fileAppender.setName("fileAppender");
        return fileAppender;
    }

    public SMTPAppender mailAppender() {
        SMTPAppender mailAppender = new SMTPAppender();
        mailAppender.setName("mailAppender");
        mailAppender.setThreshold(Level.ERROR);
        mailAppender.setSMTPDebug(true);
        mailAppender.setSMTPProtocol("smtps");
        mailAppender.setSMTPHost("smtp.gmail.com");
        mailAppender.setSMTPPort(465);
        mailAppender.setSMTPUsername("learnintouch@gmail.com");
        mailAppender.setSMTPPassword("xxxxxx");
        mailAppender.setFrom("stephane@learnintouch.com");
        mailAppender.setTo("stephane@learnintouch.com");
        mailAppender.setSubject("[LOG] Java - learnintouch");
        mailAppender.setBufferSize(1);
        PatternLayout patternLayout = new PatternLayout();
        patternLayout.setConversionPattern("%d{ABSOLUTE} %5p %c{1}:%L - %m%n");
        mailAppender.setLayout(patternLayout);
        LevelRangeFilter levelRangeFilter = new LevelRangeFilter();
        levelRangeFilter.setLevelMin(Level.DEBUG);
        levelRangeFilter.setLevelMax(Level.FATAL);
        mailAppender.addFilter(levelRangeFilter);
        return mailAppender;
    }

    @Bean
    public Logger registerSpringLogger() {
        Logger logger = Logger.getLogger("org.springframework");
        logger.setLevel(Level.DEBUG);
        logger.addAppender(consoleAppender());
        return logger;
    }
    
    @Bean
    public Logger registerThalasoftLogger() throws IOException {
        Logger logger = Logger.getLogger("com.thalasoft");
        logger.setLevel(Level.DEBUG);
        logger.addAppender(consoleAppender());
        logger.addAppender(fileAppender());
        return logger;
    }
    
    @Bean
    public Logger registerHibernateTypeLogger() {
        Logger logger = Logger.getLogger("org.hibernate.type");
        logger.setLevel(Level.TRACE);
        logger.addAppender(consoleAppender());
        return logger;
    }
    
    @Bean
    public Logger registerHibernateSqlLogger() {
        Logger logger = Logger.getLogger("org.hibernate.sql");
        logger.setLevel(Level.DEBUG);
        logger.addAppender(consoleAppender());
        return logger;
    }

    @Bean
    public Logger registerJdbcSqlOnlyLogger() throws IOException {
        Logger logger = Logger.getLogger("jdbc.sqlonly");
        logger.setLevel(Level.TRACE);
        logger.setAdditivity(false);
        logger.addAppender(consoleAppender());
        logger.addAppender(fileAppender());
        return logger;
    }

    @Bean
    public Logger registerLog4JdbcLogger() throws IOException {
        Logger logger = Logger.getLogger("log4jdbc.debug");
        logger.setLevel(Level.DEBUG);
        logger.addAppender(consoleAppender());
        return logger;
    }

}
