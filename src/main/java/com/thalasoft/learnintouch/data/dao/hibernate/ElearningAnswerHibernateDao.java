package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ElearningAnswerDao;
import com.thalasoft.learnintouch.data.dao.domain.ElearningAnswer;
import com.thalasoft.learnintouch.data.dao.domain.ElearningQuestion;

@Repository
@Transactional
public class ElearningAnswerHibernateDao extends GenericHibernateDao<ElearningAnswer, Serializable> implements ElearningAnswerDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningAnswer> findWithQuestion(ElearningQuestion elearningQuestion) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningQuestion", elearningQuestion));
		criteria.addOrder(Order.asc("listOrder"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningAnswer> findWithQuestionOrderById(ElearningQuestion elearningQuestion) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningQuestion", elearningQuestion));
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	@Override
	public ElearningAnswer findWithQuestionAndAnswer(ElearningQuestion elearningQuestion, String answer) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningQuestion", elearningQuestion));
		criteria.add(Restrictions.eq("answer", answer));
		criteria.addOrder(Order.asc("listOrder"));
		return (ElearningAnswer) criteria.uniqueResult();
	}

	@Override
	public ElearningAnswer findWithListOrder(ElearningQuestion elearningQuestion, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningQuestion", elearningQuestion));
		criteria.add(Restrictions.eq("listOrder", listOrder));
		criteria.setMaxResults(1);
		return (ElearningAnswer) criteria.uniqueResult();
	}

	@Override
	public ElearningAnswer findNextWithListOrder(ElearningQuestion elearningQuestion, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningQuestion", elearningQuestion));
		criteria.add(Restrictions.gt("listOrder", listOrder));
		criteria.addOrder(Order.asc("listOrder")).setMaxResults(1);
		return (ElearningAnswer) criteria.uniqueResult();
	}

	@Override
	public ElearningAnswer findPreviousWithListOrder(ElearningQuestion elearningQuestion, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningQuestion", elearningQuestion));
		criteria.add(Restrictions.lt("listOrder", listOrder));
		criteria.addOrder(Order.desc("listOrder")).setMaxResults(1);
		return (ElearningAnswer) criteria.uniqueResult();
	}

	@Override
	public long countListOrderDuplicates(ElearningQuestion elearningQuestion) {
		String statement = "select count(distinct ea1.id) as count from ElearningAnswer ea1, Elearninganswer ea2 where ea1.id != ea2.id and ea1.listOrder = ea2.listOrder and ea1.elearningQuestion.id = ea2.elearningQuestion.id and ea1.elearningQuestion.id = :elearningQuestionId";
		Query query = getSession().createQuery(statement);
		query.setLong("elearningQuestionId", elearningQuestion.getId());
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningAnswer> findWithImage(String image) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("image", image));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningAnswer> findWithAudio(String audio) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("audio", audio));
		return criteria.list();
	}

}
