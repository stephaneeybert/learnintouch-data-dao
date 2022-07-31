package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ShopOrderDao;
import com.thalasoft.learnintouch.data.dao.domain.ShopOrder;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class ShopOrderHibernateDao extends GenericHibernateDao<ShopOrder, Serializable> implements ShopOrderDao {

	@Override
	public Page<ShopOrder> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.desc("orderDate"));
		Page<ShopOrder> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public ShopOrder findWithEmail(String email) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("email", email));
		return (ShopOrder) criteria.uniqueResult();
	}

	@Override
	public Page<ShopOrder> findWithUser(UserAccount userAccount, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("userAccount", userAccount));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("orderDate"));
		Page<ShopOrder> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ShopOrder> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
		String pattern = "%" + searchPattern + "%";
		Criterion firstname = Restrictions.ilike("firstname", pattern);
		Criterion lastname = Restrictions.ilike("lastname", pattern);
		Criterion email = Restrictions.ilike("email", pattern);
		Criterion organisation = Restrictions.ilike("organisation", pattern);
		Criterion telephone = Restrictions.ilike("telephone", pattern);
		Criterion mobilePhone = Restrictions.ilike("mobilePhone", pattern);
		Criterion fax = Restrictions.ilike("fax", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(firstname).add(lastname).add(email).add(organisation).add(telephone).add(mobilePhone).add(fax);
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("orderDate"));
		Page<ShopOrder> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ShopOrder> findWithStatus(String status, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("status", status));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("orderDate"));
		Page<ShopOrder> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ShopOrder> findWithStatusAndYearAndMonth(String status, int year, int month, int pageNumber, int pageSize) {
		String statement = "select so from ShopOrder so where so.status = :status and YEAR(so.orderDate) = :year and MONTH(so.orderDate) = :month order by so.orderDate desc";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("status", status);
		parameters.put("year", year);
		parameters.put("month", month);
		Page<ShopOrder> page = getPage(pageNumber, pageSize, statement, parameters, getSession());
		return page;
	}

	@Override
	public Page<ShopOrder> findWithYearAndMonth(int year, int month, int pageNumber, int pageSize) {
		String statement = "select so from ShopOrder so where YEAR(so.orderDate) = :year and MONTH(so.orderDate) = :month order by so.orderDate desc";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("year", year);
		parameters.put("month", month);
		Page<ShopOrder> page = getPage(pageNumber, pageSize, statement, parameters, getSession());
		return page;
	}

}
