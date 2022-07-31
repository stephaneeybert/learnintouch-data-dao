package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.LinkCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface LinkCategoryDao extends GenericDao<LinkCategory, Serializable> {

    public Page<LinkCategory> findAll(int pageNumber, int pageSize);

    public List<LinkCategory> findAllOrderById();

    public LinkCategory findWithListOrder(int listOrder);

    public LinkCategory findNextWithListOrder(int listOrder);

    public LinkCategory findPreviousWithListOrder(int listOrder);

    public long countListOrderDuplicates();

}
