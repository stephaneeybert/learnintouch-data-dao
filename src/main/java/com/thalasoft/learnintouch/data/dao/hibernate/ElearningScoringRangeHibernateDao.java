package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ElearningScoringRangeDao;
import com.thalasoft.learnintouch.data.dao.domain.ElearningScoring;
import com.thalasoft.learnintouch.data.dao.domain.ElearningScoringRange;

@Repository
@Transactional
public class ElearningScoringRangeHibernateDao extends GenericHibernateDao<ElearningScoringRange, Serializable> implements ElearningScoringRangeDao {

	@Override
	public long deleteWithScoring(ElearningScoring elearningScoring) {
		Query query = getSession().createQuery("delete from ElearningScoringRange where elearningScoring.id = ?");
		query.setLong("elearningScoringId", elearningScoring.getId());
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningScoringRange> findWithScoring(ElearningScoring elearningScoring) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningScoring", elearningScoring));
		return criteria.list();
	}

}
