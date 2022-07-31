package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.Admin;
import com.thalasoft.learnintouch.data.dao.domain.Webpage;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface WebpageDao extends GenericDao<Webpage, Serializable> {

	public Page<Webpage> findAll(int pageNumber, int pageSize);

	public Page<Webpage> findWithParent(Webpage parent, int pageNumber, int pageSize);

	public List<Webpage> findWithParentOrderById(Webpage parent);

	public Webpage findWithParentAndName(Webpage parent, String name);

	public Webpage findWithParentAndNameAndNotGarbage(Webpage parent, String name);

	public Webpage findWithListOrder(Webpage parent, int listOrder);

	public Webpage findNextWithListOrder(Webpage parent, int listOrder);

	public Webpage findPreviousWithListOrder(Webpage parent, int listOrder);

	public Page<Webpage> findAllInGarbage(int pageNumber, int pageSize);

	public Page<Webpage> findAllNotInGarbage(int pageNumber, int pageSize);

	public List<Webpage> findContentWithImage(String image);

	public long countListOrderDuplicates(Webpage parent);

	public Page<Webpage> findWithPatternLike(String pattern, int pageNumber, int pageSize);

	public Page<Webpage> findWithAdmin(Admin admin, int pageNumber, int pageSize);
	
}
