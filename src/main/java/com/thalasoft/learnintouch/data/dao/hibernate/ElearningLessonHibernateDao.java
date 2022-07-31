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

import com.thalasoft.learnintouch.data.dao.ElearningLessonDao;
import com.thalasoft.learnintouch.data.dao.domain.ElearningCategory;
import com.thalasoft.learnintouch.data.dao.domain.ElearningCourse;
import com.thalasoft.learnintouch.data.dao.domain.ElearningLesson;
import com.thalasoft.learnintouch.data.dao.domain.ElearningLevel;
import com.thalasoft.learnintouch.data.dao.domain.ElearningSubject;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class ElearningLessonHibernateDao extends GenericHibernateDao<ElearningLesson, Serializable> implements ElearningLessonDao {

	private static final String DB_TABLE_ELEARNING_COURSE = "elearningCourse";
	private static final String DB_TABLE_ELEARNING_COURSE_ITEM = "elearningCourseItem";

	@Override
	public Page<ElearningLesson> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningLesson> findWithCourseAndReleasedSince(ElearningCourse elearningCourse, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "");
		criteria.createAlias(DB_TABLE_ELEARNING_COURSE_ITEM, "eci", CriteriaSpecification.LEFT_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq(".garbage", false));
        conjunction.add(Restrictions.eq("eci.elearningCourse", elearningCourse));
		if (sinceDateTime.isBefore(systemDateTime)) {
		    conjunction.add(Restrictions.le("el.releaseDate", systemDateTime));
		    conjunction.add(Restrictions.ge("el.releaseDate", sinceDateTime));
		} else {
		    conjunction.add(Restrictions.gt("el.releaseDate", sinceDateTime));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("eci.listOrder")).add(Order.asc("el.name"));
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningLesson> findWithCourse(ElearningCourse elearningCourse, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "el");
		criteria.createAlias(DB_TABLE_ELEARNING_COURSE_ITEM, "eci", CriteriaSpecification.LEFT_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("el.garbage", false));
        conjunction.add(Restrictions.eq("eci.elearningCourse", elearningCourse));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("eci.listOrder")).add(Order.desc("el.name"));
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningLesson> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
		String pattern = "%" + searchPattern + "%";
		conjunction.add(Restrictions.ilike("name", pattern));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningLesson> findWithPatternInLessonAndCourseLike(String searchPattern, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "el");
		criteria.createAlias(DB_TABLE_ELEARNING_COURSE_ITEM, "eci", CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias(DB_TABLE_ELEARNING_COURSE, "ec", CriteriaSpecification.LEFT_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("el.garbage", false));
		String pattern = "%" + searchPattern + "%";
		Criterion exerciseName = Restrictions.ilike("el.name", pattern);
		Criterion courseName = Restrictions.ilike("ec.name", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(exerciseName).add(courseName);
		conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("el.name"));
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningLesson> findWithReleasedSince(LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
		if (sinceDateTime.isBefore(systemDateTime)) {
		    conjunction.add(Restrictions.le("releaseDate", systemDateTime));
		    conjunction.add(Restrictions.ge("releaseDate", sinceDateTime));
		} else {
		    conjunction.add(Restrictions.gt("releaseDate", sinceDateTime));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningLesson> findWithCategoryAndReleasedSince(ElearningCategory elearningCategory, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
		if (elearningCategory != null) {
		    conjunction.add(Restrictions.eq("elearningCategory", elearningCategory));
		} else {
		    conjunction.add(Restrictions.isNull("elearningCategory"));
		}
		if (sinceDateTime.isBefore(systemDateTime)) {
		    conjunction.add(Restrictions.le("releaseDate", systemDateTime));
		    conjunction.add(Restrictions.ge("releaseDate", sinceDateTime));
		} else {
		    conjunction.add(Restrictions.gt("releaseDate", sinceDateTime));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningLesson> findWithCategory(ElearningCategory elearningCategory, int pageNumber, int pageSize) {
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
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningLesson> findWithLevelAndReleasedSince(ElearningLevel elearningLevel, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
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
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningLesson> findWithLevel(ElearningLevel elearningLevel, int pageNumber, int pageSize) {
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
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningLesson> findAllNotGarbage(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningLesson> findAllGarbage(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", true));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningLesson> findWithSubjectAndReleasedSince(ElearningSubject elearningSubject, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
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
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningLesson> findWithSubject(ElearningSubject elearningSubject, int pageNumber, int pageSize) {
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
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningLesson> findWithCategoryAndLevelAndSubject(ElearningCategory elearningCategory, ElearningLevel elearningLevel, ElearningSubject elearningSubject, int pageNumber, int pageSize) {
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
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningLesson> findWithCategoryAndLevelAndSubjectAndReleasedSince(ElearningCategory elearningCategory, ElearningLevel elearningLevel, ElearningSubject elearningSubject, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
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
		    conjunction.add(Restrictions.le("releaseDate", systemDateTime));
		    conjunction.add(Restrictions.ge("releaseDate", sinceDateTime));
		} else {
		    conjunction.add(Restrictions.gt("releaseDate", sinceDateTime));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningLesson> findWithCategoryAndLevelAndReleasedSince(ElearningCategory elearningCategory, ElearningLevel elearningLevel, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
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
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningLesson> findWithCategoryAndLevel(ElearningCategory elearningCategory, ElearningLevel elearningLevel, int pageNumber, int pageSize) {
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
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningLesson> findWithCategoryAndSubjectAndReleasedSince(ElearningCategory elearningCategory, ElearningSubject elearningSubject, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
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
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningLesson> findWithCategoryAndSubject(ElearningCategory elearningCategory, ElearningSubject elearningSubject, int pageNumber, int pageSize) {
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
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningLesson> findWithLevelAndSubjectAndReleasedSince(ElearningLevel elearningLevel, ElearningSubject elearningSubject, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
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
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningLesson> findWithLevelAndSubject(ElearningLevel elearningLevel, ElearningSubject elearningSubject, int pageNumber, int pageSize) {
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
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningLesson> findWithImageInTextLike(String image, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		String pattern = "%" + image + "%";
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.ilike("text", pattern));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningLesson> findWithPublicAccessAndReleasedSince(LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
        conjunction.add(Restrictions.eq("publicAccess", true));
		if (sinceDateTime.isBefore(systemDateTime)) {
		    conjunction.add(Restrictions.le("releaseDate", systemDateTime));
		    conjunction.add(Restrictions.ge("releaseDate", sinceDateTime));
		} else {
		    conjunction.add(Restrictions.gt("releaseDate", sinceDateTime));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningLesson> findWithPublicAccess(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
        conjunction.add(Restrictions.eq("publicAccess", true));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningLesson> findWithNotPublicAccessAndReleasedSince(LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
        conjunction.add(Restrictions.eq("publicAccess", false));
		if (sinceDateTime.isBefore(systemDateTime)) {
		    conjunction.add(Restrictions.le("releaseDate", systemDateTime));
		    conjunction.add(Restrictions.ge("releaseDate", sinceDateTime));
		} else {
		    conjunction.add(Restrictions.gt("releaseDate", sinceDateTime));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningLesson> findWithNotPublicAccess(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("garbage", false));
        conjunction.add(Restrictions.eq("publicAccess", false));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningLesson> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public ElearningLesson findWithName(String name) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("name", name));
		criteria.setMaxResults(1);
		return (ElearningLesson) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningLesson> findWithImage(String image) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("image", image));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningLesson> findWithAudio(String audio) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("audio", audio));
		return criteria.list();
	}

}
