package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.FormItemValueDao;
import com.thalasoft.learnintouch.data.dao.domain.FormItem;
import com.thalasoft.learnintouch.data.dao.domain.FormItemValue;

@Repository
@Transactional
public class FormItemValueHibernateDao extends GenericHibernateDao<FormItemValue, Serializable> implements FormItemValueDao {

	@SuppressWarnings("unchecked")
	public List<FormItemValue> findWithFormItem(FormItem formItem) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("formItem", formItem));
		return criteria.list();		
	}

}
