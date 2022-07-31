package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.Profile;

public interface ProfileDao extends GenericDao<Profile, Serializable> {

	public Profile findWithName(String name);

}
