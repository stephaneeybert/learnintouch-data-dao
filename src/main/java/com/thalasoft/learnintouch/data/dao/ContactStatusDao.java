package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.ContactStatus;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ContactStatusDao extends GenericDao<ContactStatus, Serializable> {

	public Page<ContactStatus> findAll(int pageNumber, int pageSize);

	public List<ContactStatus> findAllOrderById();

	public ContactStatus findFirst();

	public ContactStatus findWithListOrder(int listOrder);

	public ContactStatus findNextWithListOrder(int listOrder);

	public ContactStatus findPreviousWithListOrder(int listOrder);

	public long countListOrderDuplicates();

}
