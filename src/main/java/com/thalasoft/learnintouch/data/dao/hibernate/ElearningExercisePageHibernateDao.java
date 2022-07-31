package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ElearningExercisePageDao;
import com.thalasoft.learnintouch.data.dao.domain.ElearningExercise;
import com.thalasoft.learnintouch.data.dao.domain.ElearningExercisePage;

@Repository
@Transactional
public class ElearningExercisePageHibernateDao extends GenericHibernateDao<ElearningExercisePage, Serializable> implements ElearningExercisePageDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningExercisePage> findWithExercise(ElearningExercise elearningExercise) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningExercise", elearningExercise));
		criteria.addOrder(Order.asc("listOrder"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningExercisePage> findWithExerciseOrderById(ElearningExercise elearningExercise) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningExercise", elearningExercise));
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	@Override
	public ElearningExercisePage findWithListOrder(ElearningExercise elearningExercise, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningExercise", elearningExercise));
		criteria.add(Restrictions.eq("listOrder", listOrder));
		criteria.setMaxResults(1);
		return (ElearningExercisePage) criteria.uniqueResult();
	}

	@Override
	public ElearningExercisePage findNextWithListOrder(ElearningExercise elearningExercise, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningExercise", elearningExercise));
		criteria.add(Restrictions.gt("listOrder", listOrder));
		criteria.addOrder(Order.asc("listOrder")).setMaxResults(1);
		return (ElearningExercisePage) criteria.uniqueResult();
	}

	@Override
	public ElearningExercisePage findPreviousWithListOrder(ElearningExercise elearningExercise, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningExercise", elearningExercise));
		criteria.add(Restrictions.lt("listOrder", listOrder));
		criteria.addOrder(Order.desc("listOrder")).setMaxResults(1);
		return (ElearningExercisePage) criteria.uniqueResult();
	}

	@Override
	public long countListOrderDuplicates(ElearningExercise elearningExercise) {
		String statement = "select count(distinct eep1.id) as count from ElearningExercisePage eep1, ElearningExercisePage eep2 where eep1.id != eep2.id and eep1.listOrder = eep2.listOrder and eep1.elearningExercise.id = eep2.elearningExercise.id and eep1.elearningExercise.id = :elearningExerciseId";
		Query query = getSession().createQuery(statement);
		query.setLong("elearningExerciseId", elearningExercise.getId());
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningExercisePage> findWithImage(String image) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("image", image));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningExercisePage> findWithImageInTextLike(String image) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		String pattern = "%" + image + "%";
		criteria.add(Restrictions.ilike("text", pattern));
		criteria.addOrder(Order.asc("name"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningExercisePage> findWithAudio(String audio) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("audio", audio));
		return criteria.list();
	}

}
