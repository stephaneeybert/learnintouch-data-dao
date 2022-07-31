package com.thalasoft.learnintouch.data.service;

import com.thalasoft.learnintouch.data.dao.domain.PeopleCategory;

public interface PeopleCategoryService {

	public PeopleCategory save(PeopleCategory peopleCategory);

	public boolean isNotUsedByAnyPeople(PeopleCategory peopleCategory);

}
