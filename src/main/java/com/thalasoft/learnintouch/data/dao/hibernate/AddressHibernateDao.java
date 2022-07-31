package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.AddressDao;
import com.thalasoft.learnintouch.data.dao.domain.Address;

@Repository
@Transactional
public class AddressHibernateDao extends GenericHibernateDao<Address, Serializable> implements AddressDao {
	
}
