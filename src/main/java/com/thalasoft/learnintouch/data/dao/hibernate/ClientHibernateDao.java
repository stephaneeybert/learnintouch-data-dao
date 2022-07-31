package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ClientDao;
import com.thalasoft.learnintouch.data.dao.domain.Client;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class ClientHibernateDao extends GenericHibernateDao<Client, Serializable> implements ClientDao {

	@Override
	public Page<Client> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<Client> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Client findWithName(String name) {
		Criterion criterion = Restrictions.eq("name", name);
		return findObjectByCriteria(criterion);
	}

	@Override
	public List<Client> findWithImage(String image) {
		Criterion criterion = Restrictions.eq("image", image);
		return findObjectsByCriteria(criterion);
	}

}
