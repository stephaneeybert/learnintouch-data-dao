package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.SocialUserDao;
import com.thalasoft.learnintouch.data.dao.domain.SocialUser;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;

@Repository
@Transactional
public class SocialUserHibernateDao extends GenericHibernateDao<SocialUser, Serializable> implements SocialUserDao {

	@Override
	public SocialUser findWithFacebookUserIdAndUser(String facebookUserId, UserAccount userAccount) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("facebookUserId", facebookUserId)).add(Restrictions.eq("userAccount", userAccount));
		return findObjectByCriteria(criteria);
	}

	@Override
	public SocialUser findWithFacebookUserId(String facebookUserId) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("facebookUserId", facebookUserId));
		return findObjectByCriteria(criteria);
	}

	@Override
	public SocialUser findWithLinkedinUserIdAndUser(String linkedinUserId, UserAccount userAccount) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("linkedinUserId", linkedinUserId)).add(Restrictions.eq("userAccount", userAccount));
		return findObjectByCriteria(criteria);
	}

	@Override
	public SocialUser findWithLinkedinUserId(String linkedinUserId) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("linkedinUserId", linkedinUserId));
		return findObjectByCriteria(criteria);
	}

	@Override
	public SocialUser findWithUser(UserAccount userAccount) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("userAccount", userAccount));
		return findObjectByCriteria(criteria);
	}

}
