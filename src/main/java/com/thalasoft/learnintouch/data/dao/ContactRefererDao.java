package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.ContactReferer;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ContactRefererDao extends GenericDao<ContactReferer, Serializable> {

	public Page<ContactReferer> findAll(int pageNumber, int pageSize);

	public List<ContactReferer> findAllOrderById();

	public ContactReferer findFirst();

	public ContactReferer findWithListOrder(int listOrder);

	public ContactReferer findNextWithListOrder(int listOrder);

	public ContactReferer findPreviousWithListOrder(int listOrder);

	public long countListOrderDuplicates();

}
