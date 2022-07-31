package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.GenericDao;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;
import com.thalasoft.learnintouch.data.utils.TypeResolver;

@Repository
@Transactional
public abstract class GenericHibernateDao<T, ID extends Serializable> implements GenericDao<T, ID> {

	private static Logger logger = LoggerFactory.getLogger(GenericHibernateDao.class);

	private Class<T> persistentClass;
	private Class<ID> idClass;
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public GenericHibernateDao() {
		Class<?>[] typeArguments = TypeResolver.resolveArguments(getClass(), GenericDao.class);
	    this.persistentClass = (Class<T>) typeArguments[0];
	    this.idClass = (Class<ID>) typeArguments[1];
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			throw new IllegalStateException("sessionFactory has not been set on DAO before usage");
		}
		return sessionFactory;
	}

	public Session getSession() {
		return getSessionFactory().getCurrentSession();
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public Class<ID> getIdClass() {
		return idClass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findWithId(ID id, boolean lock) {
		T entity;

		if (lock) {
			entity = (T) getSession().load(getPersistentClass(), id, LockOptions.UPGRADE);
		} else {
			entity = (T) getSession().load(getPersistentClass(), id);
		}

		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isFoundById(ID id) {
		try {
			@SuppressWarnings("unused")
			T entity = (T) getSession().load(getPersistentClass(), id);
		} catch (ObjectNotFoundException e) {
			return false;
		}

		return true;
	}

	@Override
	public long countAll() {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.setProjection(Projections.rowCount());
		return ((Long) criteria.list().get(0)).longValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findWithExample(T exampleInstance, String... excludeProperty) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		criteria.add(example);
		return criteria.list();
	}

	@Override
	public T makePersistent(T entity) {
		getSession().saveOrUpdate(entity);
		return entity;
	}

	@Override
	public void makeTransient(T entity) {
		getSession().delete(entity);
		logger.debug("The entity " + entity.getClass().getCanonicalName() + " was deleted fine.");
	}

	@Override
	public void flush() {
		getSession().flush();
	}

	@Override
	public void clear() {
		getSession().clear();
	}

	@Override
	public void close() {
		getSession().close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findObjectsByCriteria(Criterion... criterion) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			criteria.add(c);
		}
		return criteria.list();
	}

	@Override
	public T findObjectByCriteria(Criterion... criterion) {
		T object = null;
		List<T> results = findObjectsByCriteria(criterion);

		if (results.isEmpty()) {
			if (logger.isDebugEnabled()) {
				logger.debug("No search results found for: " + criterion);
			}
		} else {
			if (results.size() > 1 && logger.isDebugEnabled()) {
				logger.debug("The criterion : " + criterion + " should return only one result");
			}
			object = results.get(0);
		}

		return object;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findObjectByCriteria(Criteria criteria) {
		T object = null;
		List<T> results = criteria.list();

		if (results.isEmpty()) {
			if (logger.isDebugEnabled()) {
				logger.debug("No search results found for the criteria : " + criteria);
			}
		} else {
			if (results.size() > 1 && logger.isDebugEnabled()) {
				logger.debug("The criteria : " + criteria + " should return only one result");
			}
			object = results.get(0);
		}

		return object;
	}

    @Override
    public Page<T> getPage(int pageNumber, int pageSize, Criteria criteria) {
        return getPage(pageNumber, pageSize, criteria, null, null);
    }
    
    @Override
    public Page<T> getPage(int pageNumber, int pageSize, Criteria criteria, Projection projection) {
        return getPage(pageNumber, pageSize, criteria, projection, null);
    }
    
    @Override
    public Page<T> getPage(int pageNumber, int pageSize, Criteria criteria, OrderList orderList) {
        return getPage(pageNumber, pageSize, criteria, null, orderList);
    }

    @SuppressWarnings("unchecked")
    @Override
	public Page<T> getPage(int pageNumber, int pageSize, Criteria criteria, Projection projection, OrderList orderList) {
		int totalSize = 0;
		if (pageSize > 0) {
		    criteria.setProjection(Projections.rowCount());
		    // TODO Why is uniqueResult returning a Long and setFirstResult requiring an int ?
		    totalSize = ((Long) criteria.uniqueResult()).intValue();
		    int startIndex = Page.getStartIndex(pageNumber, pageSize, totalSize);
		    criteria.setFirstResult(startIndex);
		    criteria.setMaxResults(pageSize);
		}
        if (projection != null) {
            criteria.setProjection(projection);
        } else {
            // Remove the count project
            criteria.setProjection(null);
            // Specify the main class as being the projection to avoid JOINed projections
            criteria.setResultTransformer(Criteria.ROOT_ENTITY);
        }
		if (orderList != null) {
		    for(Order order : orderList.getOrders()) {
		        criteria.addOrder(order);		        
		    }
		}
		List<T> list = criteria.list();
		return new Page<T>(pageNumber, pageSize, totalSize, list);
	}

	@SuppressWarnings("unchecked")
    @Override
	public Page<T> getPage(int pageNumber, int pageSize, final String statement, final Map<String, Object> parameters, Session session) {
		Query query = session.createQuery(statement);
		Set<String> keys = parameters.keySet();
		for (String key : keys) {
			query.setParameter(key, parameters.get(key));
		}
		int totalSize = 0;
		if (pageSize > 0) {
			String countStatement = "select count(1) " + removeSelect(removeOrders(statement));
			Query countQuery = session.createQuery(countStatement);
			for (String key : keys) {
				countQuery.setParameter(key, parameters.get(key));
			}
			Integer count = (Integer) countQuery.list().get(0);
			totalSize = count.intValue();
			int startIndex = Page.getStartIndex(pageNumber, pageSize, totalSize);
			query.setFirstResult(startIndex).setMaxResults(pageSize);
		}
		List<T> list = query.list();
		return new Page<T>(pageNumber, pageSize, totalSize, list);
	}

	public static String removeSelect(String hql) {
		int beginPos = hql.toLowerCase().indexOf("from");
		return hql.substring(beginPos);
	}

	private static final Pattern ORDER_PATTERN = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);

	public static String removeOrders(String hql) {
		Matcher m = ORDER_PATTERN.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

}
