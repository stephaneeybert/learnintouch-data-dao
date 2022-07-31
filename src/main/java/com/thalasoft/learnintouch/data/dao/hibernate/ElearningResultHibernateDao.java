package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ElearningResultDao;
import com.thalasoft.learnintouch.data.dao.domain.ElearningClass;
import com.thalasoft.learnintouch.data.dao.domain.ElearningCourse;
import com.thalasoft.learnintouch.data.dao.domain.ElearningExercise;
import com.thalasoft.learnintouch.data.dao.domain.ElearningResult;
import com.thalasoft.learnintouch.data.dao.domain.ElearningSession;
import com.thalasoft.learnintouch.data.dao.domain.ElearningSubscription;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class ElearningResultHibernateDao extends GenericHibernateDao<ElearningResult, Serializable> implements ElearningResultDao {

	private static final String DB_TABLE_ELEARNING_COURSE = "elearningCourse";
	private static final String DB_TABLE_ELEARNING_CLASS = "elearningClass";
	private static final String DB_TABLE_ELEARNING_SUBSCRIPTION = "elearningSubscription";
	private static final String DB_TABLE_USER_ACCOUNT = "userAccount";

	@Override
	public Page<ElearningResult> findWithReleaseDate(LocalDateTime sinceDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.isNull("subscription"));
        conjunction.add(Restrictions.ge("exerciseDateTime", sinceDateTime));
		criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("exerciseDateTime")).add(Order.asc("firstname")).add(Order.asc("lastname"));
		Page<ElearningResult> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningResult> findOldResult(LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.isNotNull("exerciseDateTime"));
        conjunction.add(Restrictions.le("exerciseDateTime", sinceDateTime));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("exerciseDateTime")).add(Order.asc("firstname")).add(Order.asc("lastname"));
		Page<ElearningResult> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningResult> findNonSubscription(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.isNull("subscription"));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("exerciseDateTime")).add(Order.asc("firstname")).add(Order.asc("lastname"));
		Page<ElearningResult> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningResult> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
		String pattern = "%" + searchPattern + "%";
		Criterion email = Restrictions.ilike("email", pattern);
		Criterion firstname = Restrictions.ilike("firstname", pattern);
		Criterion lastname = Restrictions.ilike("lastname", pattern);
		Criterion message = Restrictions.ilike("message", pattern);
		Criterion comment = Restrictions.ilike("comment", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(email).add(firstname).add(lastname).add(message).add(comment);
		conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("exerciseDateTime")).add(Order.asc("firstname")).add(Order.asc("lastname"));
		Page<ElearningResult> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningResult> findWithSessionAndCourseAndClassAndExercise(ElearningSession elearningSession, ElearningCourse elearningCourse, ElearningClass elearningClass, ElearningExercise elearningExercise, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "er");
		criteria.createAlias(DB_TABLE_ELEARNING_SUBSCRIPTION, "esu", CriteriaSpecification.INNER_JOIN);
		criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("esu.elearningSession", elearningSession));
        conjunction.add(Restrictions.eq("esu.elearningCourse", elearningCourse));
        conjunction.add(Restrictions.eq("esu.elearningClass", elearningClass));
        conjunction.add(Restrictions.eq("er.elearningExercise", elearningExercise));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("u.firstname")).add(Order.asc("u.lastname"));
		Page<ElearningResult> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningResult> findWithSessionAndCourseAndClass(ElearningSession elearningSession, ElearningCourse elearningCourse, ElearningClass elearningClass, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "er");
		criteria.createAlias(DB_TABLE_ELEARNING_SUBSCRIPTION, "esu", CriteriaSpecification.INNER_JOIN);
		criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("esu.elearningSession", elearningSession));
        conjunction.add(Restrictions.eq("esu.elearningCourse", elearningCourse));
        conjunction.add(Restrictions.eq("esu.elearningClass", elearningClass));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("er.exerciseDateTime")).add(Order.asc("er.firstname")).add(Order.asc("er.lastname"));
		Page<ElearningResult> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningResult> findWithSessionAndCourseAndExercise(ElearningSession elearningSession, ElearningCourse elearningCourse, ElearningExercise elearningExercise, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "er");
		criteria.createAlias(DB_TABLE_ELEARNING_SUBSCRIPTION, "esu", CriteriaSpecification.INNER_JOIN);
		criteria.createAlias(DB_TABLE_ELEARNING_CLASS, "ecl", CriteriaSpecification.INNER_JOIN);
		criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("esu.elearningSession", elearningSession));
        conjunction.add(Restrictions.eq("esu.elearningCourse", elearningCourse));
        conjunction.add(Restrictions.eq("er.elearningExercise", elearningExercise));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("ecl.name")).add(Order.desc("er.exerciseDateTime")).add(Order.asc("u.firstname")).add(Order.asc("u.lastname"));
		Page<ElearningResult> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningResult> findWithSessionAndCourse(ElearningSession elearningSession, ElearningCourse elearningCourse, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "er");
		criteria.createAlias(DB_TABLE_ELEARNING_SUBSCRIPTION, "esu", CriteriaSpecification.INNER_JOIN);
		criteria.createAlias(DB_TABLE_ELEARNING_CLASS, "ecl", CriteriaSpecification.INNER_JOIN);
		criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("esu.elearningSession", elearningSession));
        conjunction.add(Restrictions.eq("esu.elearningCourse", elearningCourse));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("ecl.name")).add(Order.desc("er.exerciseDateTime")).add(Order.asc("u.firstname")).add(Order.asc("u.lastname"));
		Page<ElearningResult> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningResult> findWithSessionAndClass(ElearningSession elearningSession, ElearningClass elearningClass, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "er");
		criteria.createAlias(DB_TABLE_ELEARNING_SUBSCRIPTION, "esu", CriteriaSpecification.INNER_JOIN);
		criteria.createAlias(DB_TABLE_ELEARNING_COURSE, "eco", CriteriaSpecification.INNER_JOIN);
		criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("esu.elearningSession", elearningSession));
        conjunction.add(Restrictions.eq("esu.elearningClass", elearningClass));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("eco.name")).add(Order.desc("er.exerciseDateTime")).add(Order.asc("u.firstname")).add(Order.asc("u.lastname"));
		Page<ElearningResult> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningResult> findWithSession(ElearningSession elearningSession, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "er");
		criteria.createAlias(DB_TABLE_ELEARNING_SUBSCRIPTION, "esu", CriteriaSpecification.INNER_JOIN);
		criteria.createAlias(DB_TABLE_ELEARNING_COURSE, "eco", CriteriaSpecification.INNER_JOIN);
		criteria.createAlias(DB_TABLE_ELEARNING_CLASS, "ecl", CriteriaSpecification.INNER_JOIN);
		criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("esu.elearningSession", elearningSession));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("eco.name")).add(Order.desc("er.exerciseDateTime")).add(Order.asc("u.firstname")).add(Order.asc("u.lastname"));
		Page<ElearningResult> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningResult> findWithSubscription(ElearningSubscription elearningSubscription, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "er");
		criteria.createAlias(DB_TABLE_ELEARNING_SUBSCRIPTION, "esu", CriteriaSpecification.INNER_JOIN);
		criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("er.elearningSubscription", elearningSubscription));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("er.exerciseDateTime")).add(Order.asc("u.firstname")).add(Order.asc("u.lastname"));
		Page<ElearningResult> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningResult> findWithUser(UserAccount userAccount, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "er");
		criteria.createAlias(DB_TABLE_ELEARNING_SUBSCRIPTION, "es", CriteriaSpecification.INNER_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("es.user", userAccount));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("er.exerciseDateTime"));
		Page<ElearningResult> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public ElearningResult findWithSubscriptionAndExercise(ElearningSubscription elearningSubscription, ElearningExercise elearningExercise) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (elearningSubscription != null) {
			criteria.add(Restrictions.eq("elearningSubscription", elearningSubscription));
		} else {
			criteria.add(Restrictions.isNull("elearningSubscription"));
		}
		criteria.add(Restrictions.eq("elearningExercise", elearningExercise));
		return (ElearningResult) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningResult> findWithExerciseAndEmailAndDate(ElearningExercise elearningExercise, String email, LocalDateTime exerciseDateTime) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningExercise", elearningExercise));
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.sqlRestriction("DATE(exerciseDateTime) >= DATE('" + exerciseDateTime + "')"));
		criteria.addOrder(Order.desc("exerciseDateTime")).addOrder(Order.asc("firstname")).addOrder(Order.asc("lastname"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningResult> findWithExerciseAndEmail(ElearningExercise elearningExercise, String email) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningExercise", elearningExercise));
		criteria.add(Restrictions.eq("email", email));
		criteria.addOrder(Order.desc("exerciseDateTime")).addOrder(Order.asc("firstname")).addOrder(Order.asc("lastname"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningResult> findWithEmail(String email) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("email", email));
		criteria.addOrder(Order.desc("exerciseDateTime"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningResult> findWithExercise(ElearningExercise elearningExercise) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningExercise", elearningExercise));
		criteria.addOrder(Order.desc("exerciseDateTime")).addOrder(Order.asc("firstname")).addOrder(Order.asc("lastname"));
		return criteria.list();
	}
	
}
