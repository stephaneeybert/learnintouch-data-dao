package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.PeopleCategoryDao;
import com.thalasoft.learnintouch.data.dao.domain.PeopleCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class PeopleCategoryHibernateDao extends GenericHibernateDao<PeopleCategory, Serializable> implements PeopleCategoryDao {

	@Override
	public Page<PeopleCategory> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<PeopleCategory> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
    @SuppressWarnings("unchecked")
    @Override
    public List<PeopleCategory> findAllOrderById() {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.addOrder(Order.asc("id"));
        return criteria.list();
    }

    @Override
    public PeopleCategory findWithListOrder(int listOrder) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("listOrder", listOrder));
        criteria.setMaxResults(1);
        return (PeopleCategory) criteria.uniqueResult();
    }

    @Override
    public PeopleCategory findNextWithListOrder(int listOrder) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.gt("listOrder", listOrder)).addOrder(Order.asc("listOrder")).setMaxResults(1);
        return (PeopleCategory) criteria.uniqueResult();
    }

    @Override
    public PeopleCategory findPreviousWithListOrder(int listOrder) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.lt("listOrder", listOrder)).addOrder(Order.desc("listOrder")).setMaxResults(1);
        return (PeopleCategory) criteria.uniqueResult();
    }

    @Override
    public long countListOrderDuplicates() {
        Query query = getSession().createQuery("select count(distinct pc1.id) as count from PeopleCategory pc1, PeopleCategory pc2 where pc1.id != pc2.id and pc1.listOrder = pc2.listOrder");
        Long count = (Long) query.list().get(0);
        return count.longValue();
    }

}
