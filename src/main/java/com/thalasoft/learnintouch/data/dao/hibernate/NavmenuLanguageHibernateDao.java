package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.NavmenuLanguageDao;
import com.thalasoft.learnintouch.data.dao.domain.Navmenu;
import com.thalasoft.learnintouch.data.dao.domain.NavmenuItem;
import com.thalasoft.learnintouch.data.dao.domain.NavmenuLanguage;

@Repository
@Transactional
public class NavmenuLanguageHibernateDao extends GenericHibernateDao<NavmenuLanguage, Serializable> implements NavmenuLanguageDao {

	@SuppressWarnings("unchecked")
	public List<NavmenuLanguage> findWithNavmenu(Navmenu navmenu) {
		Query query = getSession().createQuery("from NavmenuLanguage where navmenu.id = :navmenuId order by coalesce(languageCode, '0')");
		query.setLong("navmenuId", navmenu.getId());
		return query.list();
	}

	public NavmenuLanguage findWithNavmenuItem(NavmenuItem navmenuItem) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("navmenuItem", navmenuItem));
		return (NavmenuLanguage) criteria.uniqueResult();
	}

	public NavmenuLanguage findWithNavmenuAndLanguage(Navmenu navmenu, String languageCode) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("navmenu", navmenu)).add(Restrictions.eq("languageCode", languageCode));
		return (NavmenuLanguage) criteria.uniqueResult();
	}

	public NavmenuLanguage findWithNavmenuAndNoLanguage(Navmenu navmenu) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("navmenu", navmenu));
		criteria.add(Restrictions.or(Restrictions.isNull("languageCode"), Restrictions.eq("languageCode", "")));
		return (NavmenuLanguage) criteria.uniqueResult();
	}

}
