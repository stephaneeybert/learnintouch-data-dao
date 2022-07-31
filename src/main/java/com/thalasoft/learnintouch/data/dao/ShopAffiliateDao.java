package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.ShopAffiliate;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ShopAffiliateDao extends GenericDao<ShopAffiliate, Serializable> {

	public Page<ShopAffiliate> findWithPatternLike(String pattern, int pageNumber, int pageSize);

	public Page<ShopAffiliate> findAll(int pageNumber, int pageSize);

	public ShopAffiliate findWithUserAccount(UserAccount userAccount);
	
}
