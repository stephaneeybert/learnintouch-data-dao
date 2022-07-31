package com.thalasoft.learnintouch.data.service.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.LinkCategoryDao;
import com.thalasoft.learnintouch.data.dao.LinkDao;
import com.thalasoft.learnintouch.data.dao.domain.Link;
import com.thalasoft.learnintouch.data.dao.domain.LinkCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.service.LinkCategoryService;

@Transactional
public class LinkCategoryServiceImpl implements LinkCategoryService {

	@Autowired
	private LinkDao linkDao;
	
	@Autowired
	private LinkCategoryDao linkCategoryDao;

	protected void setLinkDao(LinkDao linkDao) {
		this.linkDao = linkDao;
	}

	protected void setLinkCategoryDao(LinkCategoryDao linkCategoryDao) {
		this.linkCategoryDao = linkCategoryDao;
	}

	@Override
	public LinkCategory save(LinkCategory linkCategory) {
		return linkCategoryDao.makePersistent(linkCategory);
	}
	
	@Override
	public boolean isNotUsedByAnyLink(LinkCategory linkCategory) {
		Page<Link> page = linkDao.findWithCategory(linkCategory, 0, 0);
		List<Link> links = page.getPageItems(); 
		return links.size() == 0;
	}
	
}
