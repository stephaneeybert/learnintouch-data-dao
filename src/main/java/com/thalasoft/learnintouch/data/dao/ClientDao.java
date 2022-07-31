package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.Client;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ClientDao extends GenericDao<Client, Serializable> {

	public Page<Client> findAll(int pageNumber, int pageSize);

	public Client findWithName(String name);

	public List<Client> findWithImage(String image);

}
