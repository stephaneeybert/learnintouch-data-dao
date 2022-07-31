package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.Flash;

public interface FlashDao extends GenericDao<Flash, Serializable> {

	public List<Flash> findAll();

	public List<Flash> findWithFilename(String filename);

	public List<Flash> findWithWddx(String wddx);

}
