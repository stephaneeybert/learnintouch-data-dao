package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.LinkDao;
import com.thalasoft.learnintouch.data.dao.domain.Link;
import com.thalasoft.learnintouch.data.dao.domain.LinkCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class LinkHibernateDao extends GenericHibernateDao<Link, Serializable> implements LinkDao {

	@Override
	public Page<Link> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("linkCategory")).add(Order.asc("listOrder"));
		Page<Link> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<Link> findWithCategory(LinkCategory linkCategory, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
		if (linkCategory != null) {
		    conjunction.add(Restrictions.eq("linkCategory", linkCategory));
		} else {
		    conjunction.add(Restrictions.isNull("linkCategory"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("listOrder"));
		Page<Link> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Link> findWithCategoryOrderById(LinkCategory linkCategory) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (linkCategory != null) {
			criteria.add(Restrictions.eq("linkCategory", linkCategory));
		} else {
			criteria.add(Restrictions.isNull("linkCategory"));
		}
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	@Override
	public Link findWithListOrder(LinkCategory linkCategory, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (linkCategory != null) {
			criteria.add(Restrictions.eq("linkCategory", linkCategory));
		} else {
			criteria.add(Restrictions.isNull("linkCategory"));
		}
		criteria.add(Restrictions.eq("listOrder", listOrder));
		criteria.setMaxResults(1);
		return (Link) criteria.uniqueResult();
	}

	@Override
	public Link findNextWithListOrder(LinkCategory linkCategory, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (linkCategory != null) {
			criteria.add(Restrictions.eq("linkCategory", linkCategory));
		} else {
			criteria.add(Restrictions.isNull("linkCategory"));
		}
		criteria.add(Restrictions.gt("listOrder", listOrder)).addOrder(Order.asc("listOrder")).setMaxResults(1);
		return (Link) criteria.uniqueResult();
	}

	@Override
	public Link findPreviousWithListOrder(LinkCategory linkCategory, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (linkCategory != null) {
			criteria.add(Restrictions.eq("linkCategory", linkCategory));
		} else {
			criteria.add(Restrictions.isNull("linkCategory"));
		}
		criteria.add(Restrictions.lt("listOrder", listOrder)).addOrder(Order.desc("listOrder")).setMaxResults(1);
		return (Link) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Link> findWithImage(String image) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("image", image));
		return criteria.list();
	}

	@Override
	public long countListOrderDuplicates(LinkCategory linkCategory) {
		String statement = "select count(distinct l1.id) as count from Link l1, Link l2 where l1.id != l2.id and l1.listOrder = l2.listOrder and ";
		if (linkCategory != null) {
			statement += "l1.linkCategory = l2.linkCategory and l1.linkCategory.id = :linkCategoryId";
		} else {
			statement += "l1.linkCategory.id is null and l2.linkCategory.id is null";
		}
		Query query = getSession().createQuery(statement);
		if (linkCategory != null) {
			query.setLong("linkCategoryId", linkCategory.getId());
		}
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

}
