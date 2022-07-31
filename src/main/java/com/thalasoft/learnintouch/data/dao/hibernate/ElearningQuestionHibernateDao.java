package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ElearningQuestionDao;
import com.thalasoft.learnintouch.data.dao.domain.ElearningExercise;
import com.thalasoft.learnintouch.data.dao.domain.ElearningExercisePage;
import com.thalasoft.learnintouch.data.dao.domain.ElearningQuestion;

@Repository
@Transactional
public class ElearningQuestionHibernateDao extends GenericHibernateDao<ElearningQuestion, Serializable> implements ElearningQuestionDao {

	private static final String DB_TABLE_ELEARNING_EXERCISE_PAGE = "elearningExercisePage";

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningQuestion> findWithExercisePage(ElearningExercisePage elearningExercisePage) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningExercisePage", elearningExercisePage));
		criteria.addOrder(Order.asc("listOrder"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningQuestion> findWithExercisePageOrderById(ElearningExercisePage elearningExercisePage) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningExercisePage", elearningExercisePage));
		criteria.addOrder(Order.asc("listOrder"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningQuestion> findWithExercise(ElearningExercise elearningExercise) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "eq");
		criteria.createAlias(DB_TABLE_ELEARNING_EXERCISE_PAGE, "eep", CriteriaSpecification.INNER_JOIN);
		criteria.add(Restrictions.eq("eep.elearningExercise", elearningExercise));
		criteria.addOrder(Order.asc("eep.listOrder")).addOrder(Order.asc("eq.listOrder"));
		return criteria.list();
	}

	@Override
	public ElearningQuestion findWithListOrder(ElearningExercisePage elearningExercisePage, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningExercisePage", elearningExercisePage));
		criteria.add(Restrictions.eq("listOrder", listOrder));
		criteria.setMaxResults(1);
		return (ElearningQuestion) criteria.uniqueResult();
	}

	@Override
	public ElearningQuestion findNextWithListOrder(ElearningExercisePage elearningExercisePage, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningExercisePage", elearningExercisePage));
		criteria.add(Restrictions.gt("listOrder", listOrder));
		criteria.addOrder(Order.asc("listOrder")).setMaxResults(1);
		return (ElearningQuestion) criteria.uniqueResult();
	}

	@Override
	public ElearningQuestion findPreviousWithListOrder(ElearningExercisePage elearningExercisePage, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningExercisePage", elearningExercisePage));
		criteria.add(Restrictions.lt("listOrder", listOrder));
		criteria.addOrder(Order.desc("listOrder")).setMaxResults(1);
		return (ElearningQuestion) criteria.uniqueResult();
	}

	@Override
	public long countListOrderDuplicates(ElearningExercisePage elearningExercisePage) {
		String statement = "select count(distinct eq1.id) as count from ElearningQuestion eq1, ElearningQuestion eq2 where eq1.id != eq2.id and eq1.listOrder = eq2.listOrder and eq1.elearningExercisePage.id = eq2.elearningExercisePage.id and eq1.elearningExercisePage.id = :elearningExercisePageId";
		Query query = getSession().createQuery(statement);
		query.setLong("elearningExercisePageId", elearningExercisePage.getId());
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningQuestion> findWithImage(String image) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("image", image));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningQuestion> findWithAudio(String audio) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("audio", audio));
		return criteria.list();
	}

}
