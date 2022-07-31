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

import com.thalasoft.learnintouch.data.dao.ElearningExerciseDao;
import com.thalasoft.learnintouch.data.dao.domain.ElearningCategory;
import com.thalasoft.learnintouch.data.dao.domain.ElearningCourse;
import com.thalasoft.learnintouch.data.dao.domain.ElearningExercise;
import com.thalasoft.learnintouch.data.dao.domain.ElearningLevel;
import com.thalasoft.learnintouch.data.dao.domain.ElearningScoring;
import com.thalasoft.learnintouch.data.dao.domain.ElearningSubject;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class ElearningExerciseHibernateDao extends GenericHibernateDao<ElearningExercise, Serializable> implements ElearningExerciseDao {

	private static final String DB_TABLE_ELEARNING_COURSE = "elearningCourse";
	private static final String DB_TABLE_ELEARNING_COURSE_ITEM = "elearningCourseItem";

	@Override
	public Page<ElearningExercise> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningExercise> findAllNotGarbage(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		Criterion criterion = Restrictions.eq("garbage", false);
		criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningExercise> findAllGarbage(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		Criterion criterion = Restrictions.eq("garbage", true); 
		criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningExercise> findWithPublicAccessAndReleasedSince(LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("garbage", false));
		criteria.add(Restrictions.eq("publicAccess", true));
		Criterion criterion;
		if (sinceDateTime.isBefore(systemDateTime)) {
		    criterion = Restrictions.conjunction().add(Restrictions.le("releaseDate", systemDateTime))
		    .add(Restrictions.ge("releaseDate", sinceDateTime));
		} else {
		    criterion = Restrictions.conjunction().add(Restrictions.gt("releaseDate", sinceDateTime));
		}
        criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningExercise> findWithPublicAccess(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		Criterion criterion = Restrictions.conjunction().add(Restrictions.eq("garbage", false))
		.add(Restrictions.eq("publicAccess", true));
        criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningExercise> findWithNotPublicAccessAndReleasedSince(LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		Conjunction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.eq("garbage", false))
		.add(Restrictions.eq("publicAccess", false));
		if (sinceDateTime.isBefore(systemDateTime)) {
			conjunction.add(Restrictions.le("releaseDate", systemDateTime))
			.add(Restrictions.ge("releaseDate", sinceDateTime));
		} else {
			conjunction.add(Restrictions.gt("releaseDate", sinceDateTime));
		}
		criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningExercise> findWithNotPublicAccess(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		Criterion criterion = Restrictions.conjunction().add(Restrictions.eq("garbage", false))
		.add(Restrictions.eq("publicAccess", false));
		criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public ElearningExercise findWithName(String name) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("name", name));
		criteria.setMaxResults(1);
		return (ElearningExercise) criteria.uniqueResult();
	}

	@Override
	public Page<ElearningExercise> findWithCourseAndReleasedSince(ElearningCourse elearningCourse, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "ee");
		criteria.createAlias(DB_TABLE_ELEARNING_COURSE_ITEM, "eci", CriteriaSpecification.LEFT_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("ee.garbage", false))
		.add(Restrictions.eq("eci.elearningCourse", elearningCourse));
		if (sinceDateTime.isBefore(systemDateTime)) {
		    conjunction.add(Restrictions.le("ee.releaseDate", systemDateTime))
			.add(Restrictions.ge("ee.releaseDate", sinceDateTime));
		} else {
		    conjunction.add(Restrictions.gt("ee.releaseDate", sinceDateTime));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("eci.listOrder")).add(Order.asc("ee.name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningExercise> findWithReleasedSince(LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
		if (sinceDateTime.isBefore(systemDateTime)) {
		    conjunction.add(Restrictions.le("releaseDate", systemDateTime))
			.add(Restrictions.ge("releaseDate", sinceDateTime));
		} else {
		    conjunction.add(Restrictions.gt("releaseDate", sinceDateTime));
		}
		criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningExercise> findWithCategoryAndReleasedSince(ElearningCategory elearningCategory, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
		if (elearningCategory != null) {
		    conjunction.add(Restrictions.eq("elearningCategory", elearningCategory));
		} else {
		    conjunction.add(Restrictions.isNull("elearningCategory"));
		}
		if (sinceDateTime.isBefore(systemDateTime)) {
		    conjunction.add(Restrictions.le("releaseDate", systemDateTime))
			.add(Restrictions.ge("releaseDate", sinceDateTime));
		} else {
		    conjunction.add(Restrictions.gt("releaseDate", sinceDateTime));
		}
		criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningExercise> findWithSubjectAndReleasedSince(ElearningSubject elearningSubject, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
		if (elearningSubject != null) {
		    conjunction.add(Restrictions.eq("elearningSubject", elearningSubject));
		} else {
		    conjunction.add(Restrictions.isNull("elearningSubject"));
		}
		if (sinceDateTime.isBefore(systemDateTime)) {
		    conjunction.add(Restrictions.le("releaseDate", systemDateTime))
			.add(Restrictions.ge("releaseDate", sinceDateTime));
		} else {
		    conjunction.add(Restrictions.gt("releaseDate", sinceDateTime));
		}
		criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningExercise> findWithCategoryAndLevelAndSubjectAndReleasedSince(ElearningCategory elearningCategory, ElearningLevel elearningLevel, ElearningSubject elearningSubject, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
		if (elearningCategory != null) {
		    conjunction.add(Restrictions.eq("elearningCategory", elearningCategory));
		} else {
		    conjunction.add(Restrictions.isNull("elearningCategory"));
		}
		if (elearningLevel != null) {
		    conjunction.add(Restrictions.eq("elearningLevel", elearningLevel));
		} else {
		    conjunction.add(Restrictions.isNull("elearningLevel"));
		}
		if (elearningSubject != null) {
		    conjunction.add(Restrictions.eq("elearningSubject", elearningSubject));
		} else {
		    conjunction.add(Restrictions.isNull("elearningSubject"));
		}
		if (sinceDateTime.isBefore(systemDateTime)) {
		    conjunction.add(Restrictions.le("releaseDate", systemDateTime))
		    .add(Restrictions.ge("releaseDate", sinceDateTime));
		} else {
		    conjunction.add(Restrictions.gt("releaseDate", sinceDateTime));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningExercise> findWithCategoryAndLevelAndReleasedSince(ElearningCategory elearningCategory, ElearningLevel elearningLevel, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
		if (elearningCategory != null) {
		    conjunction.add(Restrictions.eq("elearningCategory", elearningCategory));
		} else {
		    conjunction.add(Restrictions.isNull("elearningCategory"));
		}
		if (elearningLevel != null) {
		    conjunction.add(Restrictions.eq("elearningLevel", elearningLevel));
		} else {
		    conjunction.add(Restrictions.isNull("elearningLevel"));
		}
		if (sinceDateTime.isBefore(systemDateTime)) {
		    conjunction.add(Restrictions.le("releaseDate", systemDateTime));
		    conjunction.add(Restrictions.ge("releaseDate", sinceDateTime));
		} else {
		    conjunction.add(Restrictions.gt("releaseDate", sinceDateTime));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningExercise> findWithCategoryAndSubjectAndReleasedSince(ElearningCategory elearningCategory, ElearningSubject elearningSubject, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
		if (elearningCategory != null) {
		    conjunction.add(Restrictions.eq("elearningCategory", elearningCategory));
		} else {
		    conjunction.add(Restrictions.isNull("elearningCategory"));
		}
		if (elearningSubject != null) {
		    conjunction.add(Restrictions.eq("elearningSubject", elearningSubject));
		} else {
		    conjunction.add(Restrictions.isNull("elearningSubject"));
		}
		if (sinceDateTime.isBefore(systemDateTime)) {
		    conjunction.add(Restrictions.le("releaseDate", systemDateTime));
		    conjunction.add(Restrictions.ge("releaseDate", sinceDateTime));
		} else {
		    conjunction.add(Restrictions.gt("releaseDate", sinceDateTime));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningExercise> findWithLevelAndSubjectAndReleasedSince(ElearningLevel elearningLevel, ElearningSubject elearningSubject, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
		if (elearningLevel != null) {
		    conjunction.add(Restrictions.eq("elearningLevel", elearningLevel));
		} else {
		    conjunction.add(Restrictions.isNull("elearningLevel"));
		}
		if (elearningSubject != null) {
		    conjunction.add(Restrictions.eq("elearningSubject", elearningSubject));
		} else {
		    conjunction.add(Restrictions.isNull("elearningSubject"));
		}
		if (sinceDateTime.isBefore(systemDateTime)) {
		    conjunction.add(Restrictions.le("releaseDate", systemDateTime));
		    conjunction.add(Restrictions.ge("releaseDate", sinceDateTime));
		} else {
		    conjunction.add(Restrictions.gt("releaseDate", sinceDateTime));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningExercise> findWithCategoryAndLevelAndSubject(ElearningCategory elearningCategory, ElearningLevel elearningLevel, ElearningSubject elearningSubject, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
		if (elearningCategory != null) {
		    conjunction.add(Restrictions.eq("elearningCategory", elearningCategory));
		} else {
		    conjunction.add(Restrictions.isNull("elearningCategory"));
		}
		if (elearningLevel != null) {
		    conjunction.add(Restrictions.eq("elearningLevel", elearningLevel));
		} else {
		    conjunction.add(Restrictions.isNull("elearningLevel"));
		}
		if (elearningSubject != null) {
		    conjunction.add(Restrictions.eq("elearningSubject", elearningSubject));
		} else {
		    conjunction.add(Restrictions.isNull("elearningSubject"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningExercise> findWithCategoryAndLevel(ElearningCategory elearningCategory, ElearningLevel elearningLevel, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
		if (elearningCategory != null) {
		    conjunction.add(Restrictions.eq("elearningCategory", elearningCategory));
		} else {
		    conjunction.add(Restrictions.isNull("elearningCategory"));
		}
		if (elearningLevel != null) {
		    conjunction.add(Restrictions.eq("elearningLevel", elearningLevel));
		} else {
		    conjunction.add(Restrictions.isNull("elearningLevel"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningExercise> findWithCategoryAndSubject(ElearningCategory elearningCategory, ElearningSubject elearningSubject, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
		if (elearningCategory != null) {
		    conjunction.add(Restrictions.eq("elearningCategory", elearningCategory));
		} else {
		    conjunction.add(Restrictions.isNull("elearningCategory"));
		}
		if (elearningSubject != null) {
		    conjunction.add(Restrictions.eq("elearningSubject", elearningSubject));
		} else {
		    conjunction.add(Restrictions.isNull("elearningSubject"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningExercise> findWithLevelAndSubject(ElearningLevel elearningLevel, ElearningSubject elearningSubject, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
		if (elearningLevel != null) {
		    conjunction.add(Restrictions.eq("elearningLevel", elearningLevel));
		} else {
		    conjunction.add(Restrictions.isNull("elearningLevel"));
		}
		if (elearningSubject != null) {
		    conjunction.add(Restrictions.eq("elearningSubject", elearningSubject));
		} else {
		    conjunction.add(Restrictions.isNull("elearningSubject"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningExercise> findWithSubject(ElearningSubject elearningSubject, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
		if (elearningSubject != null) {
		    conjunction.add(Restrictions.eq("elearningSubject", elearningSubject));
		} else {
		    conjunction.add(Restrictions.isNull("elearningSubject"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningExercise> findWithLevelAndReleasedSince(ElearningLevel elearningLevel, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
		if (elearningLevel != null) {
		    conjunction.add(Restrictions.eq("elearningLevel", elearningLevel));
		} else {
		    conjunction.add(Restrictions.isNull("elearningLevel"));
		}
		if (sinceDateTime.isBefore(systemDateTime)) {
		    conjunction.add(Restrictions.le("releaseDate", systemDateTime));
		    conjunction.add(Restrictions.ge("releaseDate", sinceDateTime));
		} else {
		    conjunction.add(Restrictions.gt("releaseDate", sinceDateTime));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningExercise> findWithLevel(ElearningLevel elearningLevel, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
		if (elearningLevel != null) {
		    conjunction.add(Restrictions.eq("elearningLevel", elearningLevel));
		} else {
		    conjunction.add(Restrictions.isNull("elearningLevel"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningExercise> findWithCategory(ElearningCategory elearningCategory, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
		if (elearningCategory != null) {
		    conjunction.add(Restrictions.eq("elearningCategory", elearningCategory));
		} else {
		    conjunction.add(Restrictions.isNull("elearningCategory"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningExercise> findWithScoring(ElearningScoring elearningScoring, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
		if (elearningScoring != null) {
		    conjunction.add(Restrictions.eq("elearningScoring", elearningScoring));
		} else {
		    conjunction.add(Restrictions.isNull("elearningScoring"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningExercise> findWithCourse(ElearningCourse elearningCourse, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "ee");
		criteria.createAlias(DB_TABLE_ELEARNING_COURSE_ITEM, "eci", CriteriaSpecification.LEFT_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("ee.garbage", false));
        conjunction.add(Restrictions.eq("eci.elearningCourse", elearningCourse));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("eci.listOrder")).add(Order.asc("ee.name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningExercise> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
		String pattern = "%" + searchPattern + "%";
		conjunction.add(Restrictions.ilike("name", pattern));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningExercise> findWithPatternInExerciseAndCourseLike(String searchPattern, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "ee");
		criteria.createAlias(DB_TABLE_ELEARNING_COURSE_ITEM, "eci", CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias(DB_TABLE_ELEARNING_COURSE, "ec", CriteriaSpecification.LEFT_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("ee.garbage", false));
		String pattern = "%" + searchPattern + "%";
		Criterion exerciseName = Restrictions.ilike("ee.name", pattern);
		Criterion courseName = Restrictions.ilike("ec.name", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(exerciseName).add(courseName);
		conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("ee.name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningExercise> findWithImageInIntroductionLike(String image, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
		String pattern = "%" + image + "%";
		conjunction.add(Restrictions.ilike("introduction", pattern));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningExercise> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningExercise> findWithImage(String image) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("image", image));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningExercise> findWithAudio(String audio) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("audio", audio));
		return criteria.list();
	}

}
