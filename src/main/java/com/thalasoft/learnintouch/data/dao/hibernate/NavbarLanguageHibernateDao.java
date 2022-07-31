package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.NavbarLanguageDao;
import com.thalasoft.learnintouch.data.dao.domain.Navbar;
import com.thalasoft.learnintouch.data.dao.domain.NavbarLanguage;

@Repository
@Transactional
public class NavbarLanguageHibernateDao extends GenericHibernateDao<NavbarLanguage, Serializable> implements NavbarLanguageDao {

	@SuppressWarnings("unchecked")
	public List<NavbarLanguage> findWithNavbar(Navbar navbar) {
		Query query = getSession().createQuery("from NavbarLanguage where navbar.id = :navbarId order by coalesce(languageCode, '0')");
		query.setLong("navbarId", navbar.getId());
		return query.list();
	}

	public NavbarLanguage findWithNavbarAndLanguage(Navbar navbar, String languageCode) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("navbar", navbar)).add(Restrictions.eq("languageCode", languageCode));
		return (NavbarLanguage) criteria.uniqueResult();
	}

	public NavbarLanguage findWithNavbarAndNoLanguage(Navbar navbar) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("navbar", navbar));
		criteria.add(Restrictions.or(Restrictions.isNull("languageCode"), Restrictions.eq("languageCode", "")));
		return (NavbarLanguage) criteria.uniqueResult();
	}

}
