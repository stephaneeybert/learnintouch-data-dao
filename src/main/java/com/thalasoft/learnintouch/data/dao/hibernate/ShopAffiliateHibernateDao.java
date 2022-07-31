package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ShopAffiliateDao;
import com.thalasoft.learnintouch.data.dao.domain.ShopAffiliate;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class ShopAffiliateHibernateDao extends GenericHibernateDao<ShopAffiliate, Serializable> implements ShopAffiliateDao {

    private static final String DB_TABLE_USER_ACCOUNT = "userAccount";

    @Override
	public ShopAffiliate findWithUserAccount(UserAccount userAccount) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("userAccount", userAccount));
        criteria.setMaxResults(1);
        return findObjectByCriteria(criteria);
	}

    @Override
    public Page<ShopAffiliate> findAll(int pageNumber, int pageSize) {
        Criteria criteria = getSession().createCriteria(getPersistentClass(), "sa");
        criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
        OrderList orderList = new OrderList().add(Order.asc("u.firstname")).add(Order.asc("u.lastname")).add(Order.asc("u.email"));
        Page<ShopAffiliate> page = getPage(pageNumber, pageSize, criteria, orderList);
        return page;
    }

	@Override
	public Page<ShopAffiliate> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
        Criteria criteria = getSession().createCriteria(getPersistentClass(), "sa");
        criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
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
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("u.firstname")).add(Order.asc("u.lastname"));
        Page<ShopAffiliate> page = getPage(pageNumber, pageSize, criteria, orderList);
        return page;
	}

}
