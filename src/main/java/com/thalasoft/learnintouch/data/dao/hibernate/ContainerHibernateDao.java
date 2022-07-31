package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ContainerDao;
import com.thalasoft.learnintouch.data.dao.domain.Container;

@Repository
@Transactional
public class ContainerHibernateDao extends GenericHibernateDao<Container, Serializable> implements ContainerDao {

}
