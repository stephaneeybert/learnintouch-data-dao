package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.NavbarDao;
import com.thalasoft.learnintouch.data.dao.domain.Navbar;

@Repository
@Transactional
public class NavbarHibernateDao extends GenericHibernateDao<Navbar, Serializable> implements NavbarDao {

}
