package com.thalasoft.learnintouch.data.dao;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:hibernate.xml", "classpath:log4j.xml" })
public abstract class AbstractDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

}
