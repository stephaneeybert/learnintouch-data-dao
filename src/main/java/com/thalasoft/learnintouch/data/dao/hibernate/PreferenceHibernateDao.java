package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.PreferenceDao;
import com.thalasoft.learnintouch.data.dao.domain.Preference;

@Repository
@Transactional
public class PreferenceHibernateDao extends GenericHibernateDao<Preference, Serializable> implements PreferenceDao {

	@Override
	public Preference findWithName(String name) {
		Criterion criterion = Restrictions.eq("name", name);
		return findObjectByCriteria(criterion);
	}

}
