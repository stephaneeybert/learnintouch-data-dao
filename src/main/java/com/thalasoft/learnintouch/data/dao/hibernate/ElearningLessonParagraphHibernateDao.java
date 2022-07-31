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

import com.thalasoft.learnintouch.data.dao.ElearningLessonParagraphDao;
import com.thalasoft.learnintouch.data.dao.domain.ElearningExercise;
import com.thalasoft.learnintouch.data.dao.domain.ElearningLesson;
import com.thalasoft.learnintouch.data.dao.domain.ElearningLessonHeading;
import com.thalasoft.learnintouch.data.dao.domain.ElearningLessonParagraph;

@Repository
@Transactional
public class ElearningLessonParagraphHibernateDao extends GenericHibernateDao<ElearningLessonParagraph, Serializable> implements ElearningLessonParagraphDao {

	private static final String DB_TABLE_ELEARNING_LESSON = "elearningLesson";
	private static final String DB_TABLE_ELEARNING_LESSON_MODEL = "elearningLessonModel";
	private static final String DB_TABLE_ELEARNING_LESSON_HEADING = "elearningLessonHeading";

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningLessonParagraph> findWithLesson(ElearningLesson elearningLesson) {
		String statement = "select distinct elp.* from ElearningLessonParagraph elp, ElearningLessonHeading elh where (elp.elearningLessonHeading.id = elh.id or elp.elearningLessonHeading.id is null) and elp.elearningLesson.id = :elearningLessonId order by elh.listOrder, elp.listOrder";
		Query query = getSession().createQuery(statement);
		query.setLong("elearningLessonId", elearningLesson.getId());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningLessonParagraph> findWithExercise(ElearningExercise elearningExercise) {
		String statement = "select distinct elp.* from ElearningLessonParagraph elp, ElearningLessonHeading elh where (elp.elearningLessonHeading.id = elh.id or elp.elearningLessonHeading.id is null) and elp.elearningExercise.id = :elearningExerciseId order by elh.listOrder, elp.listOrder";
		Query query = getSession().createQuery(statement);
		query.setLong("elearningExerciseId", elearningExercise.getId());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningLessonParagraph> findWithOtherLessonExercise(ElearningLesson elearningLesson, ElearningExercise elearningExercise) {
		String statement = "select distinct elp.* from ElearningLessonParagraph elp, ElearningLessonHeading elh where (elp.elearningLessonHeading.id = elh.id or elp.elearningLessonHeading.id is null) and elp.elearningExercise.id = :elearningExerciseId and elp.elearningLesson.id != :elearningLessonId order by elh.listOrder, elp.listOrder";
		Query query = getSession().createQuery(statement);
		query.setLong("elearningExerciseId", elearningExercise.getId());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningLessonParagraph> findWithLessonAndExercise(ElearningLesson elearningLesson, ElearningExercise elearningExercise) {
		String statement = "select distinct elp.* from ElearningLessonParagraph elp, ElearningLessonHeading elh where (elp.elearningLessonHeading.id = elh.id or elp.elearningLessonHeading.id is null) and elp.elearningLesson.id = :elearningLessonId ";
		if (elearningExercise != null) {
			statement += "and elp.elearningExercise.id = :elearningExerciseId ";
		} else {
			statement += "and elp.elearningExercise.id is null ";
		}
		statement += "order by elh.listOrder, elp.listOrder";
		Query query = getSession().createQuery(statement);
		query.setLong("elearningLessonId", elearningLesson.getId());
		if (elearningExercise != null) {
			query.setLong("elearningExerciseId", elearningExercise.getId());
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningLessonParagraph> findWithLessonHeading(ElearningLessonHeading elearningLessonHeading) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "elp");
		criteria.createAlias(DB_TABLE_ELEARNING_LESSON_HEADING, "elh", CriteriaSpecification.INNER_JOIN);
		criteria.add(Restrictions.eq("elp.elearningLessonHeading", elearningLessonHeading));
		criteria.addOrder(Order.asc("elh.listOrder")).addOrder(Order.asc("elp.listOrder"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningLessonParagraph> findWithLessonAndLessonHeading(ElearningLesson elearningLesson, ElearningLessonHeading elearningLessonHeading) {
		String statement = "select distinct elp.* from ElearningLessonParagraph elp, ElearningLessonHeading elh where (elp.elearningLessonHeading.id = elh.id or elp.elearningLessonHeading.id is null) ";
		statement += "and elp.elearningLesson.id = :elearningLessonId ";
		if (elearningLessonHeading != null) {
			statement += "and elp.elearningLessonHeading.id = :elearningLessonHeadingId ";
		} else {
			statement += "and elp.elearningLessonHeading.id is null ";
		}
		statement += "order by elh.listOrder, elp.listOrder";
		Query query = getSession().createQuery(statement);
		query.setLong("elearningLessonId", elearningLesson.getId());
		if (elearningLessonHeading != null) {
			query.setLong("elearningLessonHeadingId", elearningLessonHeading.getId());
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningLessonParagraph> findWithLessonAndLessonHeadingOrderById(ElearningLesson elearningLesson, ElearningLessonHeading elearningLessonHeading) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("elearningLesson", elearningLesson));
		if (elearningLessonHeading != null) {
			criteria.add(Restrictions.eq("elearningLessonHeading", elearningLessonHeading));
		} else {
			criteria.add(Restrictions.isNull("elearningLessonHeading"));
		}
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningLessonParagraph> findWithLessonAndNoLessonHeading(ElearningLesson elearningLesson) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "elp");
		criteria.createAlias(DB_TABLE_ELEARNING_LESSON, "el", CriteriaSpecification.INNER_JOIN);
		criteria.add(Restrictions.eq("elp.elearningLesson", elearningLesson));
		criteria.add(Restrictions.or(Restrictions.isNull("elp.elearningLessonHeading"), Restrictions.isNull("el.lessonModel")));
		criteria.addOrder(Order.asc("elp.elearningLesson.id")).addOrder(Order.asc("elp.listOrder"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	// It may occur, although it should not happen, that a paragraph has a heading that does not belong to the model 
    // of his new lesson, in case a paragraph was moved to another lesson, and the paragraph heading was not reset
	public List<ElearningLessonParagraph> findWithInvalidHeading(ElearningLesson elearningLesson) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "elp");
		criteria.createAlias(DB_TABLE_ELEARNING_LESSON_HEADING, "elh", CriteriaSpecification.INNER_JOIN);
		criteria.createAlias(DB_TABLE_ELEARNING_LESSON, "el", CriteriaSpecification.INNER_JOIN);
		criteria.createAlias(DB_TABLE_ELEARNING_LESSON_MODEL, "elm", CriteriaSpecification.INNER_JOIN);
		criteria.add(Restrictions.eq("elp.elearningLesson", elearningLesson));
		criteria.addOrder(Order.asc("elh.listOrder")).addOrder(Order.asc("elp.listOrder"));
		return criteria.list();
	}

	@Override
	public ElearningLessonParagraph findWithListOrder(ElearningLesson elearningLesson, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (elearningLesson != null) {
			criteria.add(Restrictions.eq("elearningLesson", elearningLesson));
		} else {
			criteria.add(Restrictions.isNull("elearningLesson"));
		}
		criteria.add(Restrictions.eq("listOrder", listOrder));
		criteria.setMaxResults(1);
		return (ElearningLessonParagraph) criteria.uniqueResult();
	}

	@Override
	public ElearningLessonParagraph findNextWithListOrder(ElearningLesson elearningLesson, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (elearningLesson != null) {
			criteria.add(Restrictions.eq("elearningLesson", elearningLesson));
		} else {
			criteria.add(Restrictions.isNull("elearningLesson"));
		}
		criteria.add(Restrictions.gt("listOrder", listOrder)).addOrder(Order.asc("listOrder")).setMaxResults(1);
		return (ElearningLessonParagraph) criteria.uniqueResult();
	}

	@Override
	public ElearningLessonParagraph findPreviousWithListOrder(ElearningLesson elearningLesson, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (elearningLesson != null) {
			criteria.add(Restrictions.eq("elearningLesson", elearningLesson));
		} else {
			criteria.add(Restrictions.isNull("elearningLesson"));
		}
		criteria.add(Restrictions.lt("listOrder", listOrder)).addOrder(Order.desc("listOrder")).setMaxResults(1);
		return (ElearningLessonParagraph) criteria.uniqueResult();
	}

	@Override
	public long countListOrderDuplicates(ElearningLesson elearningLesson) {
		Query query = getSession().createQuery("select count(distinct elp1.id) as count from ElearningLessonParagraph elp1, ElearningLessonParagraph elp2 where elp1.id != elp2.id and elp1.elearningLesson = elp2.elearningLesson and elp1.listOrder = elp2.listOrder and elp1.elearningLesson.id = :elearningLessonId");
		query.setLong("elearningLessonId", elearningLesson.getId());
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

}
