package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ElearningSessionCourseDao;
import com.thalasoft.learnintouch.data.dao.domain.ElearningCourse;
import com.thalasoft.learnintouch.data.dao.domain.ElearningSession;
import com.thalasoft.learnintouch.data.dao.domain.ElearningSessionCourse;

@Repository
@Transactional
public class ElearningSessionCourseHibernateDao extends GenericHibernateDao<ElearningSessionCourse, Serializable> implements ElearningSessionCourseDao {

	@Override
	public long deleteWithSession(ElearningSession elearningSession) {
		Query query = getSession().createQuery("delete from ElearningSessionCourse where elearningSession.id = ?");
		query.setLong("elearningSessionId", elearningSession.getId());
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningSessionCourse> findWithSession(ElearningSession elearningSession) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningSession", elearningSession));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningSessionCourse> findWithCourse(ElearningCourse elearningCourse) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningCourse", elearningCourse));
		return criteria.list();
	}

	@Override
	public ElearningSessionCourse findWithSessionAndCourse(ElearningSession elearningSession, ElearningCourse elearningCourse) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningSession", elearningSession));
		criteria.add(Restrictions.eq("elearningCourse", elearningCourse)).setMaxResults(1);
		return (ElearningSessionCourse) criteria.uniqueResult();
	}

}
