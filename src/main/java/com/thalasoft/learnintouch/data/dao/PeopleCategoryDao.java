package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.PeopleCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface PeopleCategoryDao extends GenericDao<PeopleCategory, Serializable> {
	
	public Page<PeopleCategory> findAll(int pageNumber, int pageSize);
	
    public List<PeopleCategory> findAllOrderById();

    public PeopleCategory findWithListOrder(int listOrder);

    public PeopleCategory findNextWithListOrder(int listOrder);

    public PeopleCategory findPreviousWithListOrder(int listOrder);

    public long countListOrderDuplicates();

}
