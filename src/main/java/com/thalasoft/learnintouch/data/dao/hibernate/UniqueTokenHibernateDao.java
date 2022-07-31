package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.UniqueTokenDao;
import com.thalasoft.learnintouch.data.dao.domain.UniqueToken;

@Repository
@Transactional
public class UniqueTokenHibernateDao extends GenericHibernateDao<UniqueToken, Serializable> implements UniqueTokenDao {

	@Override
	public UniqueToken findWithNameAndValue(String name, String value) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("name", name));
		criteria.add(Restrictions.eq("value", value));
		return findObjectByCriteria(criteria);
	}

}
