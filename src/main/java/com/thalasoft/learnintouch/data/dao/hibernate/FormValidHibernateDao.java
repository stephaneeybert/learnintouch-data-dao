package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.FormValidDao;
import com.thalasoft.learnintouch.data.dao.domain.FormItem;
import com.thalasoft.learnintouch.data.dao.domain.FormValid;

@Repository
@Transactional
public class FormValidHibernateDao extends GenericHibernateDao<FormValid, Serializable> implements FormValidDao {

	@SuppressWarnings("unchecked")
	public List<FormValid> findWithFormItem(FormItem formItem) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("formItem", formItem));
		return criteria.list();		
	}

}
