package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.TemplatePageTagDao;
import com.thalasoft.learnintouch.data.dao.domain.TemplatePage;
import com.thalasoft.learnintouch.data.dao.domain.TemplatePageTag;

@Repository
@Transactional
public class TemplatePageTagHibernateDao extends GenericHibernateDao<TemplatePageTag, Serializable> implements TemplatePageTagDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TemplatePageTag> findWithPage(TemplatePage templatePage) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("templatePage", templatePage));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TemplatePageTag> findWithPageAndDomTagId(TemplatePage templatePage, String domTagId) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("templatePage", templatePage));
		criteria.add(Restrictions.eq("domTagId", domTagId));
		return criteria.list();
	}

}
