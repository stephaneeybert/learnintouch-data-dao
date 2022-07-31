package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.TemplateElementTagDao;
import com.thalasoft.learnintouch.data.dao.domain.TemplateElement;
import com.thalasoft.learnintouch.data.dao.domain.TemplateElementTag;

@Repository
@Transactional
public class TemplateElementTagHibernateDao extends GenericHibernateDao<TemplateElementTag, Serializable> implements TemplateElementTagDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TemplateElementTag> findWithElement(TemplateElement templateElement) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("templateElement", templateElement));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TemplateElementTag> findWithElementAndDomTagId(TemplateElement templateElement, String domTagId) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("templateElement", templateElement));
		criteria.add(Restrictions.eq("domTagId", domTagId));
		return criteria.list();
	}

}
