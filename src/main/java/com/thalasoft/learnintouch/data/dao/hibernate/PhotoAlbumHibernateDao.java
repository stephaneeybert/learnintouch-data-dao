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

import com.thalasoft.learnintouch.data.dao.PhotoAlbumDao;
import com.thalasoft.learnintouch.data.dao.domain.PhotoAlbum;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class PhotoAlbumHibernateDao extends GenericHibernateDao<PhotoAlbum, Serializable> implements PhotoAlbumDao {

	@Override
	public Page<PhotoAlbum> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.addOrder(Order.asc("listOrder"));
        OrderList orderList = new OrderList().add(Order.asc("listOrder"));
		Page<PhotoAlbum> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PhotoAlbum> findAllOrderById() {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	@Override
	public Page<PhotoAlbum> findNotHidden(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.or(Restrictions.eq("hide", false), Restrictions.isNull("hide")));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("listOrder"));
		Page<PhotoAlbum> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public PhotoAlbum findWithName(String name) {
		Criterion criterion = Restrictions.eq("name", name);
		return findObjectByCriteria(criterion);
	}

	@Override
	public Page<PhotoAlbum> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
		String pattern = "%" + searchPattern + "%";
		Criterion name = Restrictions.ilike("name", pattern);
		Criterion event = Restrictions.ilike("event", pattern);
		Criterion location = Restrictions.ilike("location", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(name).add(event).add(location);
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("listOrder"));
		Page<PhotoAlbum> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public PhotoAlbum findWithListOrder(int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("listOrder", listOrder));
		criteria.setMaxResults(1);
		return (PhotoAlbum) criteria.uniqueResult();
	}

	@Override
	public PhotoAlbum findNextWithListOrder(int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.gt("listOrder", listOrder)).addOrder(Order.asc("listOrder")).setMaxResults(1);
		return (PhotoAlbum) criteria.uniqueResult();
	}

	@Override
	public PhotoAlbum findPreviousWithListOrder(int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.lt("listOrder", listOrder)).addOrder(Order.desc("listOrder")).setMaxResults(1);
		return (PhotoAlbum) criteria.uniqueResult();
	}

	@Override
	public long countListOrderDuplicates() {
		Query query = getSession().createQuery("select count(distinct pa1.id) as count from PhotoAlbum pa1, PhotoAlbum pa2 where pa1.id != pa2.id and pa1.listOrder = pa2.listOrder");
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

}
