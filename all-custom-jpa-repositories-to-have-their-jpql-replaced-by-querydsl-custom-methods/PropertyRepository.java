package com.thalasoft.learnintouch.data.jpa.repository;

import com.thalasoft.learnintouch.data.jpa.domain.Property;

public interface PropertyRepository extends GenericRepository<Property, Long> {

	public Property findByName(String name);

}
