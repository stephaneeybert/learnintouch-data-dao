package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ElearningSolutionDao;
import com.thalasoft.learnintouch.data.dao.domain.ElearningAnswer;
import com.thalasoft.learnintouch.data.dao.domain.ElearningQuestion;
import com.thalasoft.learnintouch.data.dao.domain.ElearningSolution;

@Repository
@Transactional
public class ElearningSolutionHibernateDao extends GenericHibernateDao<ElearningSolution, Serializable> implements ElearningSolutionDao {

	private static final String DB_TABLE_ELEARNING_ANSWER = "elearningAnswer";

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningSolution> findWithQuestion(ElearningQuestion elearningQuestion) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "es");
		criteria.createAlias(DB_TABLE_ELEARNING_ANSWER, "ea", CriteriaSpecification.INNER_JOIN);
		criteria.add(Restrictions.eq("es.elearningQuestion", elearningQuestion));
		criteria.addOrder(Order.asc("ea.listOrder"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningSolution> findWithAnswer(ElearningAnswer elearningAnswer) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningAnswer", elearningAnswer));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningSolution> findWithQuestionAndAnswer(ElearningQuestion elearningQuestion, ElearningAnswer elearningAnswer) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "es");
		criteria.createAlias(DB_TABLE_ELEARNING_ANSWER, "ea", CriteriaSpecification.INNER_JOIN);
		criteria.add(Restrictions.eq("es.elearningQuestion", elearningQuestion));
		criteria.add(Restrictions.eq("es.elearningAnswer", elearningAnswer));
		criteria.addOrder(Order.asc("ea.listOrder"));
		return criteria.list();
	}

}
