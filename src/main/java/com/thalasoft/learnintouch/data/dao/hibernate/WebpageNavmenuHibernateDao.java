package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.WebpageNavmenuDao;
import com.thalasoft.learnintouch.data.dao.domain.WebpageNavmenu;

@Repository
@Transactional
public class WebpageNavmenuHibernateDao extends GenericHibernateDao<WebpageNavmenu, Serializable> implements WebpageNavmenuDao {

}
