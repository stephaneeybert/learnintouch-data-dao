package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.LinkCategoryDao;
import com.thalasoft.learnintouch.data.dao.domain.LinkCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class LinkCategoryHibernateDao extends GenericHibernateDao<LinkCategory, Serializable> implements LinkCategoryDao {

    @Override
    public Page<LinkCategory> findAll(int pageNumber, int pageSize) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("name"));
        Page<LinkCategory> page = getPage(pageNumber, pageSize, criteria, orderList);
        return page;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<LinkCategory> findAllOrderById() {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.addOrder(Order.asc("id"));
        return criteria.list();
    }

    @Override
    public LinkCategory findWithListOrder(int listOrder) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("listOrder", listOrder));
        criteria.setMaxResults(1);
        return (LinkCategory) criteria.uniqueResult();
    }

    @Override
    public LinkCategory findNextWithListOrder(int listOrder) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.gt("listOrder", listOrder)).addOrder(Order.asc("listOrder")).setMaxResults(1);
        return (LinkCategory) criteria.uniqueResult();
    }

    @Override
    public LinkCategory findPreviousWithListOrder(int listOrder) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.lt("listOrder", listOrder)).addOrder(Order.desc("listOrder")).setMaxResults(1);
        return (LinkCategory) criteria.uniqueResult();
    }

    @Override
    public long countListOrderDuplicates() {
        Query query = getSession().createQuery("select count(distinct lc1.id) as count from LinkCategory lc1, LinkCategory lc2 where lc1.id != lc2.id and lc1.listOrder = lc2.listOrder");
        Long count = (Long) query.list().get(0);
        return count.longValue();
    }

}
