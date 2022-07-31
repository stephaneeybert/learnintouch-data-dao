package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.NavlinkDao;
import com.thalasoft.learnintouch.data.dao.domain.Navlink;

@Repository
@Transactional
public class NavlinkHibernateDao extends GenericHibernateDao<Navlink, Serializable> implements NavlinkDao {

}
