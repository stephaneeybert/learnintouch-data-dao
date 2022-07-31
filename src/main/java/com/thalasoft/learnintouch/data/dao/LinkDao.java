package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.Link;
import com.thalasoft.learnintouch.data.dao.domain.LinkCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface LinkDao extends GenericDao<Link, Serializable> {

	public Page<Link> findAll(int pageNumber, int pageSize);

	public Page<Link> findWithCategory(LinkCategory linkCategory, int pageNumber, int pageSize);

	public List<Link> findWithCategoryOrderById(LinkCategory linkCategory);

	public Link findWithListOrder(LinkCategory linkCategory, int listOrder);

	public Link findNextWithListOrder(LinkCategory linkCategory, int listOrder);

	public Link findPreviousWithListOrder(LinkCategory linkCategory, int listOrder);

	public long countListOrderDuplicates(LinkCategory linkCategory);

	public List<Link> findWithImage(String image);

}
