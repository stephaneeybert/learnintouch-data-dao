package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.NavlinkItemDao;
import com.thalasoft.learnintouch.data.dao.domain.Navlink;
import com.thalasoft.learnintouch.data.dao.domain.NavlinkItem;
import com.thalasoft.learnintouch.data.dao.domain.TemplateModel;

@Repository
@Transactional
public class NavlinkItemHibernateDao extends GenericHibernateDao<NavlinkItem, Serializable> implements NavlinkItemDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<NavlinkItem> findWithNavlink(Navlink navlink) {
		Query query = getSession().createQuery("from NavlinkItem where navlink.id = :navlinkId order by coalesce(languageCode, '0')");
		query.setLong("navlinkId", navlink.getId());
		return query.list();
	}

	@Override
	public NavlinkItem findWithNavlinkAndLanguage(Navlink navlink, String languageCode) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("navlink", navlink)).add(Restrictions.eq("languageCode", languageCode));
		return (NavlinkItem) criteria.uniqueResult();
	}

	public NavlinkItem findWithNavlinkAndNoLanguage(Navlink navlink) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("navlink", navlink));
		criteria.add(Restrictions.or(Restrictions.isNull("languageCode"), Restrictions.eq("languageCode", "")));
		return (NavlinkItem) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NavlinkItem> findWithImage(String image) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("image", image));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NavlinkItem> findWithImageOver(String imageOver) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("imageOver", imageOver));
		return criteria.list();
	}

	@Override
	public long resetNavigationModelReferences(TemplateModel templateModel) {
		Query query = getSession().createQuery("update NavlinkItem set templateModel = NULL where templateModel.id = :templateModelId");
		query.setLong("templateModelId", templateModel.getId());
		return query.executeUpdate();
	}

}
