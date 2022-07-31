package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.TemplatePropertySetDao;
import com.thalasoft.learnintouch.data.dao.domain.TemplatePropertySet;

@Repository
@Transactional
public class TemplatePropertySetHibernateDao extends GenericHibernateDao<TemplatePropertySet, Serializable> implements TemplatePropertySetDao {

}
