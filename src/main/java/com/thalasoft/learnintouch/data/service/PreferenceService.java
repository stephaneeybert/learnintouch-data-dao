package com.thalasoft.learnintouch.data.service;

import com.thalasoft.learnintouch.data.dao.domain.Preference;

public interface PreferenceService {

	public Preference save(Preference preference);
	
	public Preference load(Integer id);
	
}
