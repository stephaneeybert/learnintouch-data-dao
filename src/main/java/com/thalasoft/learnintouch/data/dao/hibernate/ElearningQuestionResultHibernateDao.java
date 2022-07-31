package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ElearningQuestionResultDao;
import com.thalasoft.learnintouch.data.dao.domain.ElearningAnswer;
import com.thalasoft.learnintouch.data.dao.domain.ElearningQuestion;
import com.thalasoft.learnintouch.data.dao.domain.ElearningQuestionResult;
import com.thalasoft.learnintouch.data.dao.domain.ElearningResult;

@Repository
@Transactional
public class ElearningQuestionResultHibernateDao extends GenericHibernateDao<ElearningQuestionResult, Serializable> implements ElearningQuestionResultDao {

	@Override
	public ElearningQuestionResult findWithResultAndQuestionAndAnswer(ElearningResult elearningResult, ElearningQuestion elearningQuestion, ElearningAnswer elearningAnswer) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningResult", elearningResult));
		criteria.add(Restrictions.eq("elearningQuestion", elearningQuestion));
		criteria.add(Restrictions.eq("elearningAnswer", elearningAnswer));
		return (ElearningQuestionResult) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningQuestionResult> findWithResult(ElearningResult elearningResult) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningResult", elearningResult));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningQuestionResult> findWithResultAndQuestion(ElearningResult elearningResult, ElearningQuestion elearningQuestion) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningResult", elearningResult));
		criteria.add(Restrictions.eq("elearningQuestion", elearningQuestion));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningQuestionResult> findWithQuestion(ElearningQuestion elearningQuestion) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningQuestion", elearningQuestion));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningQuestionResult> findWithQuestionAndAnswer(ElearningQuestion elearningQuestion, ElearningAnswer elearningAnswer) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningQuestion", elearningQuestion));
		criteria.add(Restrictions.eq("elearningAnswer", elearningAnswer));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningQuestionResult> findWithAnswer(ElearningAnswer elearningAnswer) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningAnswer", elearningAnswer));
		return criteria.list();
	}
	
}
