package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.TemplatePageDao;
import com.thalasoft.learnintouch.data.dao.domain.TemplateModel;
import com.thalasoft.learnintouch.data.dao.domain.TemplatePage;

@Repository
@Transactional
public class TemplatePageHibernateDao extends GenericHibernateDao<TemplatePage, Serializable> implements TemplatePageDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TemplatePage> findWithModel(TemplateModel templateModel) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("templateModel", templateModel));
		return criteria.list();
	}

	@Override
	public TemplatePage findWithModelAndSystemPage(TemplateModel templateModel, String systemPage) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("templateModel", templateModel));
		criteria.add(Restrictions.eq("systemPage", systemPage));
		criteria.setMaxResults(1);
		return (TemplatePage) criteria.uniqueResult();
	}

}
