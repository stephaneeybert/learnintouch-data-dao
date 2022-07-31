package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import com.thalasoft.learnintouch.data.jpa.domain.Client;

public interface ClientRepository extends GenericRepository<Client, Long> {

	public Client findByName(String name);

    public List<Client> findByImage(String image);

}
