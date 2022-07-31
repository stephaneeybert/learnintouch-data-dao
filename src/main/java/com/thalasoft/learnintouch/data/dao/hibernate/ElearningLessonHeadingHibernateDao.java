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

import com.thalasoft.learnintouch.data.dao.ElearningLessonHeadingDao;
import com.thalasoft.learnintouch.data.dao.domain.ElearningLessonHeading;
import com.thalasoft.learnintouch.data.dao.domain.ElearningLessonModel;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class ElearningLessonHeadingHibernateDao extends GenericHibernateDao<ElearningLessonHeading, Serializable> implements ElearningLessonHeadingDao {

	@Override
	public Page<ElearningLessonHeading> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("listOrder"));
		Page<ElearningLessonHeading> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningLessonHeading> findWithLessonModel(ElearningLessonModel elearningLessonModel, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
		if (elearningLessonModel != null) {
		    conjunction.add(Restrictions.eq("elearningLessonModel", elearningLessonModel));
		} else {
		    conjunction.add(Restrictions.isNull("elearningLessonModel"));
		}
		criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("listOrder"));
		Page<ElearningLessonHeading> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningLessonHeading> findWithLessonModelOrderById(ElearningLessonModel elearningLessonModel) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("garbage", false));
		if (elearningLessonModel != null) {
			criteria.add(Restrictions.eq("elearningLessonModel", elearningLessonModel));
		} else {
			criteria.add(Restrictions.isNull("elearningLessonModel"));
		}
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningLessonHeading> findWithImage(String image) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("image", image));
		return criteria.list();
	}

	@Override
	public ElearningLessonHeading findWithListOrder(ElearningLessonModel elearningLessonModel, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (elearningLessonModel != null) {
			criteria.add(Restrictions.eq("elearningLessonModel", elearningLessonModel));
		} else {
			criteria.add(Restrictions.isNull("elearningLessonModel"));
		}
		criteria.add(Restrictions.eq("listOrder", listOrder));
		criteria.setMaxResults(1);
		return (ElearningLessonHeading) criteria.uniqueResult();
	}

	@Override
	public ElearningLessonHeading findNextWithListOrder(ElearningLessonModel elearningLessonModel, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (elearningLessonModel != null) {
			criteria.add(Restrictions.eq("elearningLessonModel", elearningLessonModel));
		} else {
			criteria.add(Restrictions.isNull("elearningLessonModel"));
		}
		criteria.add(Restrictions.gt("listOrder", listOrder)).addOrder(Order.asc("listOrder")).setMaxResults(1);
		return (ElearningLessonHeading) criteria.uniqueResult();
	}

	@Override
	public ElearningLessonHeading findPreviousWithListOrder(ElearningLessonModel elearningLessonModel, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (elearningLessonModel != null) {
			criteria.add(Restrictions.eq("elearningLessonModel", elearningLessonModel));
		} else {
			criteria.add(Restrictions.isNull("elearningLessonModel"));
		}
		criteria.add(Restrictions.lt("listOrder", listOrder)).addOrder(Order.desc("listOrder")).setMaxResults(1);
		return (ElearningLessonHeading) criteria.uniqueResult();
	}

	@Override
	public long countListOrderDuplicates(ElearningLessonModel elearningLessonModel) {
		String statement = "select count(distinct elh1.id) as count from ElearningLessonHeading elh1, ElearningLessonHeading elh2 where elh1.id != elh2.id and elh1.listOrder = elh2.listOrder and ";
		if (elearningLessonModel != null) {
			statement += "elh1.elearningLessonModel = elh2.elearningLessonModel and elh1.elearningLessonModel.id = :elearningLessonModelId";
		} else {
			statement += "elh1.elearningLessonModel.id is null and elh2.elearningLessonModel.id is null";
		}
		Query query = getSession().createQuery(statement);
		if (elearningLessonModel != null) {
			query.setLong("elearningLessonModelId", elearningLessonModel.getId());
		}
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

}
