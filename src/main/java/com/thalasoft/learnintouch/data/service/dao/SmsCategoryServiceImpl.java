package com.thalasoft.learnintouch.data.service.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.SmsCategoryDao;
import com.thalasoft.learnintouch.data.dao.SmsDao;
import com.thalasoft.learnintouch.data.dao.domain.Sms;
import com.thalasoft.learnintouch.data.dao.domain.SmsCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.service.SmsCategoryService;

@Transactional
public class SmsCategoryServiceImpl implements SmsCategoryService {

	@Autowired
	private SmsDao smsDao;
	
	@Autowired
	private SmsCategoryDao smsCategoryDao;

	protected void setSmsDao(SmsDao smsDao) {
		this.smsDao = smsDao;
	}

	protected void setSmsCategoryDao(SmsCategoryDao smsCategoryDao) {
		this.smsCategoryDao = smsCategoryDao;
	}

	@Override
	public SmsCategory save(SmsCategory smsCategory) {
		return smsCategoryDao.makePersistent(smsCategory);
	}

	@Override
	public boolean isNotUsedByAnySms(SmsCategory smsCategory) {
		Page<Sms> page = smsDao.findWithCategory(smsCategory, 0, 0);
		List<Sms> smss = page.getPageItems();
		return smss.size() == 0;
	}

}
