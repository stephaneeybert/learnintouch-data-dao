package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ProfileDao;
import com.thalasoft.learnintouch.data.dao.domain.Profile;

@Repository
@Transactional
public class ProfileHibernateDao extends GenericHibernateDao<Profile, Serializable> implements ProfileDao {

	@Override
	public Profile findWithName(String name) {
		Criterion criterion = Restrictions.eq("name", name);
		return findObjectByCriteria(criterion);
	}

}
