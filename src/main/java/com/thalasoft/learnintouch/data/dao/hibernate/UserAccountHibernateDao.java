package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.UserAccountDao;
import com.thalasoft.learnintouch.data.dao.domain.MailList;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class UserAccountHibernateDao extends GenericHibernateDao<UserAccount, Serializable> implements UserAccountDao {

    private static final String DB_TABLE_ADDRESS = "address";

    @Override
    public Page<UserAccount> findAll(int pageNumber, int pageSize) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
        Page<UserAccount> page = getPage(pageNumber, pageSize, criteria, orderList);
        return page;
    }

    @Override
    public UserAccount findWithEmail(String email) {
        Criterion criterion = Restrictions.eq("email", email);
        return findObjectByCriteria(criterion);
    }

    @Override
    public UserAccount findWithEmailAndReadablePassword(String email, String readablePassword) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("email", email)).add(Restrictions.eq("readablePassword", readablePassword)).addOrder(Order.asc("firstname"))
                .addOrder(Order.asc("lastname"));
        return findObjectByCriteria(criteria);
    }

    @Override
    public Page<UserAccount> findWithMobilePhone(String mobilePhone, int pageNumber, int pageSize) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("mobilePhone", mobilePhone));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
        Page<UserAccount> page = getPage(pageNumber, pageSize, criteria, orderList);
        return page;
    }

    @Override
    public Page<UserAccount> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
        String pattern = "%" + searchPattern + "%";
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        Criterion email = Restrictions.ilike("email", pattern);
        Criterion firstname = Restrictions.ilike("firstname", pattern);
        Criterion lastname = Restrictions.ilike("lastname", pattern);
        Criterion homePhone = Restrictions.ilike("homePhone", pattern);
        Criterion workPhone = Restrictions.ilike("workPhone", pattern);
        Criterion mobilePhone = Restrictions.ilike("mobilePhone", pattern);
        Criterion fax = Restrictions.ilike("fax", pattern);
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(email);
        disjunction.add(firstname);
        disjunction.add(lastname);
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
        disjunction.add(homePhone);
        disjunction.add(workPhone);
        disjunction.add(mobilePhone);
        disjunction.add(fax);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
        Page<UserAccount> page = getPage(pageNumber, pageSize, criteria, orderList);
        return page;
    }

    @Override
    public Page<UserAccount> findMailSubscribersLikePattern(String searchPattern, int pageNumber, int pageSize) {
        String pattern = "%" + searchPattern + "%";
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("mailSubscribe", true));
        Criterion email = Restrictions.ilike("email", pattern);
        Criterion firstname = Restrictions.ilike("firstname", pattern);
        Criterion lastname = Restrictions.ilike("lastname", pattern);
        Criterion homePhone = Restrictions.ilike("homePhone", pattern);
        Criterion workPhone = Restrictions.ilike("workPhone", pattern);
        Criterion mobilePhone = Restrictions.ilike("mobilePhone", pattern);
        Criterion fax = Restrictions.ilike("fax", pattern);
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(email);
        disjunction.add(firstname);
        disjunction.add(lastname);
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
        disjunction.add(homePhone);
        disjunction.add(workPhone);
        disjunction.add(mobilePhone);
        disjunction.add(fax);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
        Page<UserAccount> page = getPage(pageNumber, pageSize, criteria, orderList);
        return page;
    }

    @Override
    public Page<UserAccount> findNotMailSubscribersLikePattern(String searchPattern, int pageNumber, int pageSize) {
        String pattern = "%" + searchPattern + "%";
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("mailSubscribe", false));
        Criterion email = Restrictions.ilike("email", pattern);
        Criterion firstname = Restrictions.ilike("firstname", pattern);
        Criterion lastname = Restrictions.ilike("lastname", pattern);
        Criterion homePhone = Restrictions.ilike("homePhone", pattern);
        Criterion workPhone = Restrictions.ilike("workPhone", pattern);
        Criterion mobilePhone = Restrictions.ilike("mobilePhone", pattern);
        Criterion fax = Restrictions.ilike("fax", pattern);
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(email);
        disjunction.add(firstname);
        disjunction.add(lastname);
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
        disjunction.add(homePhone);
        disjunction.add(workPhone);
        disjunction.add(mobilePhone);
        disjunction.add(fax);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
        Page<UserAccount> page = getPage(pageNumber, pageSize, criteria, orderList);
        return page;
    }

    @Override
    public Page<UserAccount> findSmsSubscribersLikePattern(String searchPattern, int pageNumber, int pageSize) {
        String pattern = "%" + searchPattern + "%";
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("smsSubscribe", true));
        Criterion email = Restrictions.ilike("email", pattern);
        Criterion firstname = Restrictions.ilike("firstname", pattern);
        Criterion lastname = Restrictions.ilike("lastname", pattern);
        Criterion homePhone = Restrictions.ilike("homePhone", pattern);
        Criterion workPhone = Restrictions.ilike("workPhone", pattern);
        Criterion mobilePhone = Restrictions.ilike("mobilePhone", pattern);
        Criterion fax = Restrictions.ilike("fax", pattern);
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(email);
        disjunction.add(firstname);
        disjunction.add(lastname);
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
        disjunction.add(homePhone);
        disjunction.add(workPhone);
        disjunction.add(mobilePhone);
        disjunction.add(fax);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
        Page<UserAccount> page = getPage(pageNumber, pageSize, criteria, orderList);
        return page;
    }

    @Override
    public Page<UserAccount> findNotSmsSubscribersLikePattern(String searchPattern, int pageNumber, int pageSize) {
        String pattern = "%" + searchPattern + "%";
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("smsSubscribe", false));
        Criterion email = Restrictions.ilike("email", pattern);
        Criterion firstname = Restrictions.ilike("firstname", pattern);
        Criterion lastname = Restrictions.ilike("lastname", pattern);
        Criterion homePhone = Restrictions.ilike("homePhone", pattern);
        Criterion workPhone = Restrictions.ilike("workPhone", pattern);
        Criterion mobilePhone = Restrictions.ilike("mobilePhone", pattern);
        Criterion fax = Restrictions.ilike("fax", pattern);
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(email);
        disjunction.add(firstname);
        disjunction.add(lastname);
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
        disjunction.add(homePhone);
        disjunction.add(workPhone);
        disjunction.add(mobilePhone);
        disjunction.add(fax);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
        Page<UserAccount> page = getPage(pageNumber, pageSize, criteria, orderList);
        return page;
    }

    @Override
    public Page<UserAccount> findMailSubscribersLikeCountry(String searchPattern, int pageNumber, int pageSize) {
        String pattern = "%" + searchPattern + "%";
        Criteria criteria = getSession().createCriteria(getPersistentClass(), "u");
        criteria.createAlias(DB_TABLE_ADDRESS, "a", CriteriaSpecification.INNER_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.ilike("a.country", pattern));
        conjunction.add(Restrictions.eq("u.mailSubscribe", true));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("u.firstname")).add(Order.asc("u.lastname")).add(Order.asc("u.email"));
        Page<UserAccount> page = getPage(pageNumber, pageSize, criteria, orderList);
        return page;
    }

    @Override
    public Page<UserAccount> findAllMailSubscribers(int pageNumber, int pageSize) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("mailSubscribe", true));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
        Page<UserAccount> page = getPage(pageNumber, pageSize, criteria, orderList);
        return page;
    }

    @Override
    public Page<UserAccount> findAllSmsSubscribers(int pageNumber, int pageSize) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("smsSubscribe", true)).add(Restrictions.isNotNull("mobilePhone"));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
        Page<UserAccount> page = getPage(pageNumber, pageSize, criteria, orderList);
        return page;
    }

    @Override
    public Page<UserAccount> findNotValid(LocalDateTime dateTime, int pageNumber, int pageSize) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.isNotNull("validUntil")).add(Restrictions.lt("validUntil", dateTime));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
        Page<UserAccount> page = getPage(pageNumber, pageSize, criteria, orderList);
        return page;
    }

    @Override
    public Page<UserAccount> findValidTemporarily(LocalDateTime dateTime, int pageNumber, int pageSize) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.isNotNull("validUntil")).add(Restrictions.ge("validUntil", dateTime));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
        Page<UserAccount> page = getPage(pageNumber, pageSize, criteria, orderList);
        return page;
    }

    @Override
    public Page<UserAccount> findValidPermanently(int pageNumber, int pageSize) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.isNull("validUntil"));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
        Page<UserAccount> page = getPage(pageNumber, pageSize, criteria, orderList);
        return page;
    }

    @Override
    public long countNotValidPermanently() {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.isNotNull("validUntil"));
        criteria.setProjection(Projections.rowCount());
        Long count = (Long) criteria.list().get(0);
        return count.longValue();
    }

    @Override
    public Page<UserAccount> findNotYetConfirmedEmail(int pageNumber, int pageSize) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("smsSubscribe", true));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
        Page<UserAccount> page = getPage(pageNumber, pageSize, criteria, orderList);
        return page;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserAccount> findWithImage(String image) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("image", image));
        return criteria.list();
    }

    @Override
    public Page<UserAccount> findWithCreationDateTime(LocalDateTime fromDateTime, LocalDateTime toDateTime, int pageNumber, int pageSize) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.sqlRestriction("DATE(creation_datetime) between DATE('" + fromDateTime + "') and DATE('" + toDateTime + "')"));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
        Page<UserAccount> page = getPage(pageNumber, pageSize, criteria, orderList);
        return page;
    }

    public Page<UserAccount> findWithMailList(MailList mailList, int pageNumber, int pageSize) {
        String statement = "select u from UserAccount u, MailList ml, MailListUser mlu where u.id = mlu.userAccount.id and mlu.mailList.id = ml.id and ml.id = :mailListId order by u.firstname, u.lastname, u.email";
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("mailListId", mailList.getId());
        Page<UserAccount> page = getPage(pageNumber, pageSize, statement, parameters, getSession());
        return page;
    }

    @Override
    public Page<UserAccount> findCurrentMailSubscribers(LocalDateTime dateTime, int pageNumber, int pageSize) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("mailSubscribe", true));
        criteria.add(Restrictions.or(Restrictions.isNull("validUntil"), Restrictions.and(Restrictions.isNotNull("validUntil"), Restrictions.ge("validUntil", dateTime))));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
        Page<UserAccount> page = getPage(pageNumber, pageSize, criteria, orderList);
        return page;
    }

    @Override
    public Page<UserAccount> findCurrentSmsSubscribers(LocalDateTime dateTime, int pageNumber, int pageSize) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("smsSubscribe", true)).add(
                Restrictions.or(Restrictions.isNull("validUntil"),
                        Restrictions.and(Restrictions.isNotNull("validUntil"), Restrictions.ge("validUntil", dateTime))));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
        Page<UserAccount> page = getPage(pageNumber, pageSize, criteria, orderList);
        return page;
    }

    @Override
    public Page<UserAccount> findExpiredMailSubscribers(LocalDateTime dateTime, int pageNumber, int pageSize) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("mailSubscribe", true)).add(Restrictions.isNotNull("validUntil")).add(Restrictions.lt("validUntil", dateTime));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
        Page<UserAccount> page = getPage(pageNumber, pageSize, criteria, orderList);
        return page;
    }

    @Override
    public Page<UserAccount> findExpiredSmsSubscribers(LocalDateTime dateTime, int pageNumber, int pageSize) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("smsSubscribe", true)).add(Restrictions.isNotNull("validUntil")).add(Restrictions.lt("validUntil", dateTime));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
        Page<UserAccount> page = getPage(pageNumber, pageSize, criteria, orderList);
        return page;
    }

    @Override
    public Page<UserAccount> findImported(int pageNumber, int pageSize) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("imported", true));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
        Page<UserAccount> page = getPage(pageNumber, pageSize, criteria, orderList);
        return page;
    }

    @Override
    public long countImported() {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("imported", true));
        criteria.setProjection(Projections.rowCount());
        return ((Long) criteria.uniqueResult()).longValue();
    }

    @Override
    public long resetImported() {
        Query query = getSession().createQuery("update UserAccount set imported = false where imported = true");
        return query.executeUpdate();
    }

}
