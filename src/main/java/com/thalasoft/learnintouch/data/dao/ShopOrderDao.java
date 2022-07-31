package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.ShopOrder;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ShopOrderDao extends GenericDao<ShopOrder, Serializable> {

	public Page<ShopOrder> findAll(int pageNumber, int pageSize);

	public ShopOrder findWithEmail(String email);

	public Page<ShopOrder> findWithUser(UserAccount userAccount, int pageNumber, int pageSize);

	public Page<ShopOrder> findWithPatternLike(String pattern, int pageNumber, int pageSize);

	public Page<ShopOrder> findWithStatus(String status, int pageNumber, int pageSize);

	public Page<ShopOrder> findWithStatusAndYearAndMonth(String status, int year, int month, int pageNumber, int pageSize);

	public Page<ShopOrder> findWithYearAndMonth(int year, int month, int pageNumber, int pageSize);

}
