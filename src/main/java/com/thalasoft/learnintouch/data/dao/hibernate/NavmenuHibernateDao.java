package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.NavmenuDao;
import com.thalasoft.learnintouch.data.dao.domain.Navmenu;

@Repository
@Transactional
public class NavmenuHibernateDao extends GenericHibernateDao<Navmenu, Serializable> implements NavmenuDao {

}
