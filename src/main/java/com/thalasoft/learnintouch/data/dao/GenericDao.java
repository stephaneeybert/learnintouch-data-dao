package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;

import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

public interface GenericDao<T, ID extends Serializable> {

	T makePersistent(T entity);

	void makeTransient(T entity);

	T findWithId(ID id, boolean lock);

	boolean isFoundById(ID id);

	long countAll();

	List<T> findWithExample(T exampleInstance, String... excludeProperty);

	List<T> findObjectsByCriteria(Criterion... criterion);

	T findObjectByCriteria(Criterion... criterion);

	T findObjectByCriteria(Criteria criteria);

	public Page<T> getPage(int pageNumber, int pageSize, Criteria criteria);
	
    public Page<T> getPage(int pageNumber, int pageSize, Criteria criteria, Projection projection);

    public Page<T> getPage(int pageNumber, int pageSize, Criteria criteria, OrderList orderList);

    public Page<T> getPage(int pageNumber, int pageSize, Criteria criteria, Projection projection, OrderList orderList);
	
	public Page<T> getPage(int pageNumber, int pageSize, String statement, Map<String, Object> parameters, Session session);
	   
	public void flush();

	public void clear();

	public void close();

}