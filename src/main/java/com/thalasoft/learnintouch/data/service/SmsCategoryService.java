package com.thalasoft.learnintouch.data.service;

import com.thalasoft.learnintouch.data.dao.domain.SmsCategory;

public interface SmsCategoryService {

	public SmsCategory save(SmsCategory smsCategory);

	public boolean isNotUsedByAnySms(SmsCategory smsCategory);

}
