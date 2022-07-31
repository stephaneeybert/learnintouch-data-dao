package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.Form;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface FormDao extends GenericDao<Form, Serializable> {

	public Page<Form> findAll(int pageNumber, int pageSize);

	public Form findWithName(String name);

	public List<Form> findWithImage(String image);

}
