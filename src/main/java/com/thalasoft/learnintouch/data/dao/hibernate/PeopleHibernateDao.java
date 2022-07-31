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

import com.thalasoft.learnintouch.data.dao.PeopleDao;
import com.thalasoft.learnintouch.data.dao.domain.People;
import com.thalasoft.learnintouch.data.dao.domain.PeopleCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class PeopleHibernateDao extends GenericHibernateDao<People, Serializable> implements PeopleDao {

	@Override
	public Page<People> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("peopleCategory")).add(Order.asc("listOrder"));
		Page<People> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<People> findWithCategory(PeopleCategory peopleCategory, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
		if (peopleCategory != null) {
		    conjunction.add(Restrictions.eq("peopleCategory", peopleCategory));
		} else {
		    conjunction.add(Restrictions.isNull("peopleCategory"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("listOrder"));
		Page<People> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<People> findWithCategoryOrderById(PeopleCategory peopleCategory) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (peopleCategory != null) {
			criteria.add(Restrictions.eq("peopleCategory", peopleCategory));
		} else {
			criteria.add(Restrictions.isNull("peopleCategory"));
		}
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	@Override
	public People findWithListOrder(PeopleCategory peopleCategory, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (peopleCategory != null) {
			criteria.add(Restrictions.eq("peopleCategory", peopleCategory));
		} else {
			criteria.add(Restrictions.isNull("peopleCategory"));
		}
		criteria.add(Restrictions.eq("listOrder", listOrder));
		criteria.setMaxResults(1);
		return (People) criteria.uniqueResult();
	}

	@Override
	public People findNextWithListOrder(PeopleCategory peopleCategory, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (peopleCategory != null) {
			criteria.add(Restrictions.eq("peopleCategory", peopleCategory));
		} else {
			criteria.add(Restrictions.isNull("peopleCategory"));
		}
		criteria.add(Restrictions.gt("listOrder", listOrder)).addOrder(Order.asc("listOrder")).setMaxResults(1);
		return (People) criteria.uniqueResult();
	}

	@Override
	public People findPreviousWithListOrder(PeopleCategory peopleCategory, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (peopleCategory != null) {
			criteria.add(Restrictions.eq("peopleCategory", peopleCategory));
		} else {
			criteria.add(Restrictions.isNull("peopleCategory"));
		}
		criteria.add(Restrictions.lt("listOrder", listOrder)).addOrder(Order.desc("listOrder")).setMaxResults(1);
		return (People) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<People> findWithImage(String image) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("image", image));
		return criteria.list();
	}

	@Override
	public long countListOrderDuplicates(PeopleCategory peopleCategory) {
		String statement = "select count(distinct p1.id) as count from People p1, People p2 where p1.id != p2.id and p1.listOrder = p2.listOrder and ";
		if (peopleCategory != null) {
			statement += "p1.peopleCategory = p2.peopleCategory and p1.peopleCategory.id = :peopleCategoryId";
		} else {
			statement += "p1.peopleCategory.id is null and p2.peopleCategory.id is null";
		}
		Query query = getSession().createQuery(statement);
		if (peopleCategory != null) {
			query.setLong("peopleCategoryId", peopleCategory.getId());
		}
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

}
