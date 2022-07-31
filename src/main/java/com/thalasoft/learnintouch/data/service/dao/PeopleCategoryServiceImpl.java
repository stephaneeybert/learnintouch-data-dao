package com.thalasoft.learnintouch.data.service.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.PeopleCategoryDao;
import com.thalasoft.learnintouch.data.dao.PeopleDao;
import com.thalasoft.learnintouch.data.dao.domain.People;
import com.thalasoft.learnintouch.data.dao.domain.PeopleCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.service.PeopleCategoryService;

@Transactional
public class PeopleCategoryServiceImpl implements PeopleCategoryService {

	@Autowired
	private PeopleDao peopleDao;
	
	@Autowired
	private PeopleCategoryDao peopleCategoryDao;

	protected void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	protected void setPeopleCategoryDao(PeopleCategoryDao peopleCategoryDao) {
		this.peopleCategoryDao = peopleCategoryDao;
	}

	@Override
	public PeopleCategory save(PeopleCategory peopleCategory) {
		return peopleCategoryDao.makePersistent(peopleCategory);
	}

	@Override
	public boolean isNotUsedByAnyPeople(PeopleCategory peopleCategory) {
		Page<People> page = peopleDao.findWithCategory(peopleCategory, 0, 0);
		List<People> peoples = page.getPageItems();
		return peoples.size() == 0;
	}
	
}
