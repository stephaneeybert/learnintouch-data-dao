package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.Preference;

public interface PreferenceDao extends GenericDao<Preference, Serializable> {

	public Preference findWithName(String name);

}
