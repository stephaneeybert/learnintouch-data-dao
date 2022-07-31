package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ElearningCourseItemDao;
import com.thalasoft.learnintouch.data.dao.domain.ElearningCourse;
import com.thalasoft.learnintouch.data.dao.domain.ElearningCourseItem;
import com.thalasoft.learnintouch.data.dao.domain.ElearningExercise;
import com.thalasoft.learnintouch.data.dao.domain.ElearningLesson;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class ElearningCourseItemHibernateDao extends GenericHibernateDao<ElearningCourseItem, Serializable> implements ElearningCourseItemDao {

	private static final String DB_TABLE_ELEARNING_LESSON_PARAGRAPH = "elearningLessonParagraph";
	private static final String DB_TABLE_ELEARNING_LESSON = "elearningLesson";

	@Override
	public Page<ElearningCourseItem> findWithCourse(ElearningCourse elearningCourse, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		Criterion criterion = Restrictions.eq("elearningCourse", elearningCourse); 
		criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.asc("listOrder"));
		Page<ElearningCourseItem> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningCourseItem> findWithCourseOrderById(ElearningCourse elearningCourse) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningCourse", elearningCourse));
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningCourseItem> findWithExercise(ElearningExercise elearningExercise) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (elearningExercise != null) {
			criteria.add(Restrictions.eq("elearningExercise", elearningExercise));
		} else {
			criteria.add(Restrictions.isNull("elearningExercise"));
		}
		return criteria.list();
	}
	
	@Override
	public ElearningCourseItem findWithCourseAndExercise(ElearningCourse elearningCourse, ElearningExercise elearningExercise) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningCourse", elearningCourse));
		if (elearningExercise != null) {
			criteria.add(Restrictions.eq("elearningExercise", elearningExercise));
		} else {
			criteria.add(Restrictions.isNull("elearningExercise"));
		}
		return (ElearningCourseItem) criteria.uniqueResult();
	}
	
	@Override
	public ElearningCourseItem findWithCourseAndLessonExercise(ElearningCourse elearningCourse, ElearningLesson elearningLesson, ElearningExercise elearningExercise) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "eci");
		criteria.createAlias(DB_TABLE_ELEARNING_LESSON, "el", CriteriaSpecification.INNER_JOIN);
		criteria.createAlias(DB_TABLE_ELEARNING_LESSON_PARAGRAPH, "elp", CriteriaSpecification.INNER_JOIN);
		criteria.add(Restrictions.eq("eci.elearningCourse", elearningCourse));
		if (elearningExercise != null) {
			criteria.add(Restrictions.eq("elp.elearningExercise", elearningExercise));
		} else {
			criteria.add(Restrictions.isNull("elp.elearningExercise"));
		}
		return (ElearningCourseItem) criteria.uniqueResult();
	}
	
	@Override
	public ElearningCourseItem findWithCourseAndLesson(ElearningCourse elearningCourse, ElearningLesson elearningLesson) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningCourse", elearningCourse));
		if (elearningLesson != null) {
			criteria.add(Restrictions.eq("elearningLesson", elearningLesson));
		} else {
			criteria.add(Restrictions.isNull("elearningLesson"));
		}
		return (ElearningCourseItem) criteria.uniqueResult();
	}
	
	@Override
	public ElearningCourseItem findWithListOrder(ElearningCourse elearningCourse, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningCourse", elearningCourse));
		criteria.add(Restrictions.eq("listOrder", listOrder));
		criteria.setMaxResults(1);
		return (ElearningCourseItem) criteria.uniqueResult();
	}

	@Override
	public ElearningCourseItem findNextWithListOrder(ElearningCourse elearningCourse, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningCourse", elearningCourse));
		criteria.add(Restrictions.gt("listOrder", listOrder));
		criteria.addOrder(Order.asc("listOrder")).setMaxResults(1);
		return (ElearningCourseItem) criteria.uniqueResult();
	}

	@Override
	public ElearningCourseItem findPreviousWithListOrder(ElearningCourse elearningCourse, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningCourse", elearningCourse));
		criteria.add(Restrictions.lt("listOrder", listOrder));
		criteria.addOrder(Order.desc("listOrder")).setMaxResults(1);
		return (ElearningCourseItem) criteria.uniqueResult();
	}

	@Override
	public long countListOrderDuplicates(ElearningCourse elearningCourse) {
		String statement = "select count(distinct eci1.id) as count from ElearningCourseItem eci1, ElearningCourseItem eci2 where eci1.id != eci2.id and eci1.listOrder = eci2.listOrder and eci1.elearningCourse.id = eci2.elearningCourse.id and eci1.elearningCourse.id = :elearningCourseId";
		Query query = getSession().createQuery(statement);
		query.setLong("elearningCourseId", elearningCourse.getId());
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

}
