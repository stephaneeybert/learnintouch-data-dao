package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.People;
import com.thalasoft.learnintouch.data.dao.domain.PeopleCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface PeopleDao extends GenericDao<People, Serializable> {

	public Page<People> findAll(int pageNumber, int pageSize);

	public Page<People> findWithCategory(PeopleCategory peopleCategory, int pageNumber, int pageSize);

	public List<People> findWithCategoryOrderById(PeopleCategory peopleCategory);

	public People findWithListOrder(PeopleCategory peopleCategory, int listOrder);

	public People findNextWithListOrder(PeopleCategory peopleCategory, int listOrder);

	public People findPreviousWithListOrder(PeopleCategory peopleCategory, int listOrder);

	public long countListOrderDuplicates(PeopleCategory peopleCategory);

	public List<People> findWithImage(String image);

}
