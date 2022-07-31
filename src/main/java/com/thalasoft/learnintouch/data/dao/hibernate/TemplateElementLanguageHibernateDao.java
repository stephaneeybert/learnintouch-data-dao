package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.TemplateElementLanguageDao;
import com.thalasoft.learnintouch.data.dao.domain.TemplateElement;
import com.thalasoft.learnintouch.data.dao.domain.TemplateElementLanguage;

@Repository
@Transactional
public class TemplateElementLanguageHibernateDao extends GenericHibernateDao<TemplateElementLanguage, Serializable> implements TemplateElementLanguageDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TemplateElementLanguage> findWithElement(TemplateElement templateElement) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("templateElement", templateElement));
		return criteria.list();
	}

	@Override
	public TemplateElementLanguage findWithElementAndLanguageCode(TemplateElement templateElement, String languageCode) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("templateElement", templateElement));
		criteria.add(Restrictions.eq("languageCode", languageCode));
		criteria.setMaxResults(1);
		return (TemplateElementLanguage) criteria.uniqueResult();
	}

	@Override
	public TemplateElementLanguage findWithElementAndNoLanguageCode(TemplateElement templateElement) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("templateElement", templateElement));
		criteria.add(Restrictions.isEmpty("languageCode"));
		criteria.setMaxResults(1);
		return (TemplateElementLanguage) criteria.uniqueResult();
	}

}
