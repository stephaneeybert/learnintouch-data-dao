package com.thalasoft.learnintouch.data.jpa.repository;

import com.thalasoft.learnintouch.data.jpa.domain.UniqueToken;

public interface UniqueTokenRepository extends GenericRepository<UniqueToken, Long> {

	public UniqueToken findByNameAndValue(String name, String value);

}
