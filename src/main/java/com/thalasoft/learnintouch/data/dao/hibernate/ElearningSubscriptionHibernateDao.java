package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ElearningSubscriptionDao;
import com.thalasoft.learnintouch.data.dao.domain.ElearningClass;
import com.thalasoft.learnintouch.data.dao.domain.ElearningCourse;
import com.thalasoft.learnintouch.data.dao.domain.ElearningSession;
import com.thalasoft.learnintouch.data.dao.domain.ElearningSubscription;
import com.thalasoft.learnintouch.data.dao.domain.ElearningTeacher;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class ElearningSubscriptionHibernateDao extends GenericHibernateDao<ElearningSubscription, Serializable> implements ElearningSubscriptionDao {

	private static final String DB_TABLE_ELEARNING_SESSION = "elearningSession";
	private static final String DB_TABLE_USER_ACCOUNT = "userAccount";

	@Override
	public Page<ElearningSubscription> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "es");
		criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
        OrderList orderList = new OrderList().add(Order.asc("u.firstname")).add(Order.asc("u.lastname")).add(Order.asc("u.email")).add(Order.desc("es.subscriptionDate"));
		Page<ElearningSubscription> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningSubscription> findWithTeacher(ElearningTeacher elearningTeacher, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "es");
		criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
		if (elearningTeacher != null) {
		    conjunction.add(Restrictions.eq("es.elearningTeacher", elearningTeacher));
		} else {
		    conjunction.add(Restrictions.isNull("es.elearningTeacher"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("u.firstname")).add(Order.asc("u.lastname")).add(Order.asc("u.email")).add(Order.desc("es.subscriptionDate"));
		Page<ElearningSubscription> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningSubscription> findWithSession(ElearningSession elearningSession, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "es");
		criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("es.elearningSession", elearningSession));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("u.firstname")).add(Order.asc("u.lastname")).add(Order.asc("u.email")).add(Order.desc("es.subscriptionDate"));
		Page<ElearningSubscription> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningSubscription> findWithCourse(ElearningCourse elearningCourse, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "es");
		criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("es.elearningCourse", elearningCourse));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("u.firstname")).add(Order.asc("u.lastname")).add(Order.asc("u.email")).add(Order.desc("es.subscriptionDate"));
		Page<ElearningSubscription> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningSubscription> findWithClass(ElearningClass elearningClass, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "es");
		criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
		if (elearningClass != null) {
		    conjunction.add(Restrictions.eq("es.elearningClass", elearningClass));
		} else {
		    conjunction.add(Restrictions.isNull("es.elearningClass"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("u.firstname")).add(Order.asc("u.lastname")).add(Order.asc("u.email")).add(Order.desc("es.subscriptionDate"));
		Page<ElearningSubscription> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningSubscription> findWithSessionAndCourse(ElearningSession elearningSession, ElearningCourse elearningCourse, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "es");
		criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("es.elearningSession", elearningSession));
        conjunction.add(Restrictions.eq("es.elearningCourse", elearningCourse));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("u.firstname")).add(Order.asc("u.lastname")).add(Order.asc("u.email")).add(Order.desc("es.subscriptionDate"));
		Page<ElearningSubscription> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningSubscription> findWithSessionAndClass(ElearningSession elearningSession, ElearningClass elearningClass, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "es");
		criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("es.elearningSession", elearningSession));
		if (elearningClass != null) {
		    conjunction.add(Restrictions.eq("es.elearningClass", elearningClass));
		} else {
		    conjunction.add(Restrictions.isNull("es.elearningClass"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("u.firstname")).add(Order.asc("u.lastname")).add(Order.asc("u.email")).add(Order.desc("es.subscriptionDate"));
		Page<ElearningSubscription> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningSubscription> findWithSessionAndCourseAndClass(ElearningSession elearningSession, ElearningCourse elearningCourse, ElearningClass elearningClass, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "es");
		criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("es.elearningSession", elearningSession));
        conjunction.add(Restrictions.eq("es.elearningCourse", elearningCourse));
		if (elearningClass != null) {
		    conjunction.add(Restrictions.eq("es.elearningClass", elearningClass));
		} else {
		    conjunction.add(Restrictions.isNull("es.elearningClass"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("u.firstname")).add(Order.asc("u.lastname")).add(Order.asc("u.email")).add(Order.desc("es.subscriptionDate"));
		Page<ElearningSubscription> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningSubscription> findWithSessionAndTeacher(ElearningSession elearningSession, ElearningTeacher elearningTeacher, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "es");
		criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("es.elearningSession", elearningSession));
		if (elearningTeacher != null) {
		    conjunction.add(Restrictions.eq("es.elearningTeacher", elearningTeacher));
		} else {
		    conjunction.add(Restrictions.isNull("es.elearningTeacher"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("u.firstname")).add(Order.asc("u.lastname")).add(Order.asc("u.email")).add(Order.desc("es.subscriptionDate"));
		Page<ElearningSubscription> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningSubscription> findWithSessionAndCourseAndTeacher(ElearningSession elearningSession, ElearningCourse elearningCourse, ElearningTeacher elearningTeacher, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "es");
		criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("es.elearningSession", elearningSession));
        conjunction.add(Restrictions.eq("es.elearningCourse", elearningCourse));
		if (elearningTeacher != null) {
		    conjunction.add(Restrictions.eq("es.elearningTeacher", elearningTeacher));
		} else {
		    conjunction.add(Restrictions.isNull("es.elearningTeacher"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("u.firstname")).add(Order.asc("u.lastname")).add(Order.asc("u.email")).add(Order.desc("es.subscriptionDate"));
		Page<ElearningSubscription> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningSubscription> findWithSessionAndClassAndTeacher(ElearningSession elearningSession, ElearningClass elearningClass, ElearningTeacher elearningTeacher, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "es");
		criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("es.elearningSession", elearningSession));
		if (elearningClass != null) {
		    conjunction.add(Restrictions.eq("es.elearningClass", elearningClass));
		} else {
		    conjunction.add(Restrictions.isNull("es.elearningClass"));
		}
		if (elearningTeacher != null) {
		    conjunction.add(Restrictions.eq("es.elearningTeacher", elearningTeacher));
		} else {
		    conjunction.add(Restrictions.isNull("es.elearningTeacher"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("u.firstname")).add(Order.asc("u.lastname")).add(Order.asc("u.email")).add(Order.desc("es.subscriptionDate"));
		Page<ElearningSubscription> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningSubscription> findWithSessionAndCourseAndClassAndTeacher(ElearningSession elearningSession, ElearningCourse elearningCourse, ElearningClass elearningClass, ElearningTeacher elearningTeacher, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "es");
		criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("es.elearningSession", elearningSession));
        conjunction.add(Restrictions.eq("es.elearningCourse", elearningCourse));
		if (elearningClass != null) {
		    conjunction.add(Restrictions.eq("es.elearningClass", elearningClass));
		} else {
		    conjunction.add(Restrictions.isNull("es.elearningClass"));
		}
		if (elearningTeacher != null) {
		    conjunction.add(Restrictions.eq("es.elearningTeacher", elearningTeacher));
		} else {
		    conjunction.add(Restrictions.isNull("es.elearningTeacher"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("u.firstname")).add(Order.asc("u.lastname")).add(Order.asc("u.email")).add(Order.desc("es.subscriptionDate"));
		Page<ElearningSubscription> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningSubscription> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
	    Criteria criteria = getSession().createCriteria(getPersistentClass(), "es");
	    criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
		String pattern = "%" + searchPattern + "%";
		Criterion firstname = Restrictions.ilike("u.firstname", pattern);
		Criterion lastname = Restrictions.ilike("u.lastname", pattern);
		Criterion email = Restrictions.ilike("u.email", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(firstname).add(lastname).add(email);
        if (searchPattern.contains(" ")) {
            String[] pieces = searchPattern.split(" ");
            if (pieces[0] != null) {
                Criterion firstnameBis = Restrictions.ilike("u.firstname", pieces[0]);
                disjunction.add(firstnameBis);
            }
            if (pieces[1] != null) {
                Criterion lastnameBis = Restrictions.ilike("u.lastname", pieces[1]);
                disjunction.add(lastnameBis);
            }
        }
        conjunction.add(disjunction);
        OrderList orderList = new OrderList().add(Order.asc("u.firstname")).add(Order.asc("u.lastname")).add(Order.asc("u.email")).add(Order.desc("es.subscriptionDate"));
		Page<ElearningSubscription> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
    @Override
    public Page<ElearningSubscription> findWithPatternLikeAndDistinctUser(String searchPattern, int pageNumber, int pageSize) {
        Criteria criteria = getSession().createCriteria(getPersistentClass(), "es");
        criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        String pattern = "%" + searchPattern + "%";
        Criterion firstname = Restrictions.ilike("u.firstname", pattern);
        Criterion lastname = Restrictions.ilike("u.lastname", pattern);
        Criterion email = Restrictions.ilike("u.email", pattern);
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(firstname).add(lastname).add(email);
        if (searchPattern.contains(" ")) {
            String[] pieces = searchPattern.split(" ");
            if (pieces[0] != null) {
                Criterion firstnameBis = Restrictions.ilike("u.firstname", pieces[0]);
                disjunction.add(firstnameBis);
            }
            if (pieces[1] != null) {
                Criterion lastnameBis = Restrictions.ilike("u.lastname", pieces[1]);
                disjunction.add(lastnameBis);
            }
        }
        conjunction.add(disjunction);
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("u.id"));
        criteria.setProjection(projectionList);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("u.firstname")).add(Order.asc("u.lastname")).add(Order.asc("u.email")).add(Order.desc("es.subscriptionDate"));
        Page<ElearningSubscription> page = getPage(pageNumber, pageSize, criteria, orderList);
        return page;
    }
    
	@Override
	public Page<ElearningSubscription> findWithUser(UserAccount userAccount, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("userAccount", userAccount));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("es.subscriptionDate"));
		Page<ElearningSubscription> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningSubscription> findWithUserAndTeacher(UserAccount userAccount, ElearningTeacher elearningTeacher, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("userAccount", userAccount));
        conjunction.add(Restrictions.eq("elearningTeacher", elearningTeacher));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("es.subscriptionDate"));
		Page<ElearningSubscription> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public ElearningSubscription findWithUserAndSubscription(UserAccount userAccount, ElearningSubscription elearningSubscription) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("userAccount", userAccount));
		criteria.add(Restrictions.eq("id", elearningSubscription));
		criteria.setMaxResults(1);
		return (ElearningSubscription) criteria.uniqueResult();		
	}

	@Override
	public Page<ElearningSubscription> findUserSubscriptions(UserAccount userAccount, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "es");
		criteria.createAlias(DB_TABLE_ELEARNING_SESSION, "ese", CriteriaSpecification.LEFT_JOIN);
		Conjunction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.eq("es.userAccount", userAccount));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("ese.openDate")).add(Order.desc("es.subscriptionDate"));
		Page<ElearningSubscription> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningSubscription> findOpenedUserSubscriptionsWithCourse(UserAccount userAccount, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "es");
		criteria.createAlias(DB_TABLE_ELEARNING_SESSION, "ese", CriteriaSpecification.LEFT_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("es.userAccount", userAccount));
        conjunction.add(Restrictions.eq("ese.closed", false));
        conjunction.add(Restrictions.le("ese.openDate", systemDateTime));
        conjunction.add(Restrictions.isNotNull("es.elearningCourse"));
		Conjunction datesConjunction0 = Restrictions.conjunction();
		datesConjunction0.add(Restrictions.isNull("ese.closeDate"));
		Conjunction datesConjunction1 = Restrictions.conjunction();
		datesConjunction1.add(Restrictions.isNotNull("ese.closeDate")).add(Restrictions.ge("ese.closeDate", systemDateTime));
		Disjunction datesDisjunction = Restrictions.disjunction();
		datesDisjunction.add(datesConjunction0).add(datesConjunction1);
		conjunction.add(datesDisjunction);
		conjunction.add(Restrictions.le("es.subscriptionDate", systemDateTime));
		Disjunction closeDisjunction = Restrictions.disjunction();
		closeDisjunction.add(Restrictions.isNull("es.subscriptionClose")).add(Restrictions.ge("es.subscriptionClose", systemDateTime));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("ese.openDate")).add(Order.desc("es.subscriptionDate"));
		Page<ElearningSubscription> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningSubscription> findOpenedUserSubscriptions(UserAccount userAccount, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "es");
		criteria.createAlias(DB_TABLE_ELEARNING_SESSION, "ese", CriteriaSpecification.LEFT_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("es.userAccount", userAccount));
        conjunction.add(Restrictions.eq("ese.closed", false));
        conjunction.add(Restrictions.le("ese.openDate", systemDateTime));
		Conjunction datesConjunction0 = Restrictions.conjunction();
		datesConjunction0.add(Restrictions.isNull("ese.closeDate"));
		Conjunction datesConjunction1 = Restrictions.conjunction();
		datesConjunction1.add(Restrictions.isNotNull("ese.closeDate")).add(Restrictions.ge("ese.closeDate", systemDateTime));
		Disjunction datesDisjunction = Restrictions.disjunction();
		datesDisjunction.add(datesConjunction0).add(datesConjunction1);
		conjunction.add(datesDisjunction);
		conjunction.add(Restrictions.le("es.subscriptionDate", systemDateTime));
		Disjunction closeDisjunction = Restrictions.disjunction();
		closeDisjunction.add(Restrictions.isNull("es.subscriptionClose")).add(Restrictions.ge("es.subscriptionClose", systemDateTime));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("ese.openDate")).add(Order.desc("es.subscriptionDate"));
		Page<ElearningSubscription> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public long countOpenedSubscriptions(LocalDateTime systemDateTime) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "es");
		criteria.createAlias(DB_TABLE_ELEARNING_SESSION, "ese", CriteriaSpecification.LEFT_JOIN);
		criteria.add(Restrictions.eq("ese.closed", false));
		criteria.add(Restrictions.le("ese.openDate", systemDateTime));
		Conjunction datesConjunction0 = Restrictions.conjunction();
		datesConjunction0.add(Restrictions.isNull("ese.closeDate"));
		Conjunction datesConjunction1 = Restrictions.conjunction();
		datesConjunction1.add(Restrictions.isNotNull("ese.closeDate")).add(Restrictions.ge("ese.closeDate", systemDateTime));
		Disjunction datesDisjunction = Restrictions.disjunction();
		datesDisjunction.add(datesConjunction0).add(datesConjunction1);
		criteria.add(datesDisjunction);
		criteria.add(Restrictions.le("es.subscriptionDate", systemDateTime));
		Disjunction closeDisjunction = Restrictions.disjunction();
		closeDisjunction.add(Restrictions.isNull("es.subscriptionClose")).add(Restrictions.ge("es.subscriptionClose", systemDateTime));
		criteria.setProjection(Projections.rowCount());
		return ((Long) criteria.list().get(0)).longValue();
	}

}
