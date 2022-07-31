package com.thalasoft.learnintouch.data.jpa.repository;

import com.thalasoft.learnintouch.data.jpa.domain.Preference;

public interface PreferenceRepository extends GenericRepository<Preference, Long> {

	public Preference findByName(String name);

}
