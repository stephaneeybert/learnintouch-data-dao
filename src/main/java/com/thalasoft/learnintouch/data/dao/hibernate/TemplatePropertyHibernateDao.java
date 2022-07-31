package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.TemplatePropertyDao;
import com.thalasoft.learnintouch.data.dao.domain.TemplateProperty;
import com.thalasoft.learnintouch.data.dao.domain.TemplatePropertySet;

@Repository
@Transactional
public class TemplatePropertyHibernateDao extends GenericHibernateDao<TemplateProperty, Serializable> implements TemplatePropertyDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TemplateProperty> findWithPropertySet(TemplatePropertySet templatePropertySet) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (templatePropertySet != null) {
			criteria.add(Restrictions.eq("templatePropertySet", templatePropertySet));
		} else {
			criteria.add(Restrictions.isNull("templatePropertySet"));
		}
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TemplateProperty> findWithPropertySetAndName(TemplatePropertySet templatePropertySet, String name) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (templatePropertySet != null) {
			criteria.add(Restrictions.eq("templatePropertySet", templatePropertySet));
		} else {
			criteria.add(Restrictions.isNull("templatePropertySet"));
		}
		criteria.add(Restrictions.eq("name", name));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TemplateProperty> findWithValue(String value) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("value", value));
		return criteria.list();
	}

}
