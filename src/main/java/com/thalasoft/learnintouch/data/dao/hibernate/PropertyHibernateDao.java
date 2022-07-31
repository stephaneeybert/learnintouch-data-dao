package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.PropertyDao;
import com.thalasoft.learnintouch.data.dao.domain.Property;

@Repository
@Transactional
public class PropertyHibernateDao extends GenericHibernateDao<Property, Serializable> implements PropertyDao {

	@Override
	public Property findWithName(String name) {
		Criterion criterion = Restrictions.eq("name", name);
		return findObjectByCriteria(criterion);
	}

}
