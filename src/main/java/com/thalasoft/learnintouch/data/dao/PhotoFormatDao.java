package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.PhotoFormat;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface PhotoFormatDao extends GenericDao<PhotoFormat, Serializable> {

	public Page<PhotoFormat> findAll(int pageNumber, int pageSize);

	public PhotoFormat findWithName(String name);

}
