package com.thalasoft.learnintouch.data.service.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.WebpageDao;
import com.thalasoft.learnintouch.data.dao.domain.Webpage;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.service.WebpageService;

@Transactional
public class WebpageServiceImpl implements WebpageService {

	@Autowired
	private WebpageDao webpageDao;
	
	protected void setWebpageDao(WebpageDao webpageDao) {
		this.webpageDao = webpageDao;
	}

	@Override
	public Webpage save(Webpage webpage) {
		return webpageDao.makePersistent(webpage);
	}
	
	@Override
	public boolean isNotUsedByAnyChild(Webpage webpage) {
		Page<Webpage> page = webpageDao.findWithParent(webpage, 0, 0);
		List<Webpage> documents = page.getPageItems();
		return documents.size() == 0;
	}

}
