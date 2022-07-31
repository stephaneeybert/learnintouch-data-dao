package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.WebpageDao;
import com.thalasoft.learnintouch.data.dao.domain.Admin;
import com.thalasoft.learnintouch.data.dao.domain.Webpage;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class WebpageHibernateDao extends GenericHibernateDao<Webpage, Serializable> implements WebpageDao {

	@Override
	public Page<Webpage> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<Webpage> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<Webpage> findWithParent(Webpage parent, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
		if (parent != null) {
		    conjunction.add(Restrictions.eq("parent", parent));
		} else {
		    conjunction.add(Restrictions.isNull("parent"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("listOrder"));
		Page<Webpage> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Webpage> findWithParentOrderById(Webpage parent) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (parent != null) {
			criteria.add(Restrictions.eq("parent", parent));
		} else {
			criteria.add(Restrictions.isNull("parent"));
		}
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	@Override
	public Webpage findWithParentAndName(Webpage parent, String name) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (parent != null) {
			criteria.add(Restrictions.eq("parent", parent));
		} else {
			criteria.add(Restrictions.isNull("parent"));
		}
		criteria.add(Restrictions.eq("name", name));
		criteria.addOrder(Order.asc("id"));
		return findObjectByCriteria(criteria);
	}

	@Override
	public Webpage findWithParentAndNameAndNotGarbage(Webpage parent, String name) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (parent != null) {
			criteria.add(Restrictions.eq("parent", parent));
		} else {
			criteria.add(Restrictions.isNull("parent"));
		}
		criteria.add(Restrictions.eq("name", name));
		criteria.add(Restrictions.eq("garbage", false));
		criteria.addOrder(Order.asc("id"));
		return findObjectByCriteria(criteria);
	}

	@Override
	public Webpage findWithListOrder(Webpage parent, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (parent != null) {
			criteria.add(Restrictions.eq("parent", parent));
		} else {
			criteria.add(Restrictions.isNull("parent"));
		}
		criteria.add(Restrictions.eq("listOrder", listOrder));
		criteria.setMaxResults(1);
		return (Webpage) criteria.uniqueResult();
	}

	@Override
	public Webpage findNextWithListOrder(Webpage parent, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (parent != null) {
			criteria.add(Restrictions.eq("parent", parent));
		} else {
			criteria.add(Restrictions.isNull("parent"));
		}
		criteria.add(Restrictions.gt("listOrder", listOrder)).addOrder(Order.asc("listOrder")).setMaxResults(1);
		return (Webpage) criteria.uniqueResult();
	}

	@Override
	public Webpage findPreviousWithListOrder(Webpage parent, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (parent != null) {
			criteria.add(Restrictions.eq("parent", parent));
		} else {
			criteria.add(Restrictions.isNull("parent"));
		}
		criteria.add(Restrictions.lt("listOrder", listOrder)).addOrder(Order.desc("listOrder")).setMaxResults(1);
		return (Webpage) criteria.uniqueResult();
	}

	@Override
	public Page<Webpage> findAllInGarbage(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", true));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<Webpage> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<Webpage> findAllNotInGarbage(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<Webpage> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Webpage> findContentWithImage(String image) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.ilike("content", "%" + image + "%"));
		return criteria.list();
	}

	@Override
	public long countListOrderDuplicates(Webpage parent) {
		String statement = "select count(distinct w1.id) as count from Webpage w1, Webpage w2 where w1.id != w2.id and w1.listOrder = w2.listOrder and ";
		if (parent != null) {
			statement += "w1.parent = w2.parent and w1.parent.id = :parentId";
		} else {
			statement += "w1.parent.id is null and w2.parent.id is null";
		}
		Query query = getSession().createQuery(statement);
		if (parent != null) {
			query.setLong("parentId", parent.getId());
		}
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

	@Override
	public Page<Webpage> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
		String pattern = "%" + searchPattern + "%";
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		Criterion name = Restrictions.ilike("name", pattern);
		Criterion description = Restrictions.ilike("description", pattern);
		Criterion content = Restrictions.ilike("content", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(name);
		disjunction.add(description);
		disjunction.add(content);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(disjunction);
        conjunction.add(Restrictions.eq("garbage", false));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<Webpage> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<Webpage> findWithAdmin(Admin admin, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("admin", admin));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<Webpage> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

}
