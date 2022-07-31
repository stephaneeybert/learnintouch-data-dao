package com.thalasoft.learnintouch.data.jpa.repository;

import com.thalasoft.learnintouch.data.jpa.domain.Profile;
import com.thalasoft.learnintouch.data.jpa.domain.Property;

public interface ProfileRepository extends GenericRepository<Profile, Long> {

	public Property findByName(String name);

}
