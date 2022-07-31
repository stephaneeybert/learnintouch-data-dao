package com.thalasoft.learnintouch.data.service;

import com.thalasoft.learnintouch.data.dao.domain.UserAccount;

public interface UserAccountService {

	public UserAccount savePassword(UserAccount userAccount, String password);
	
	public UserAccount saveReadablePassword(UserAccount userAccount, String readablePassword);
	
	public UserAccount save(UserAccount userAccount);
	
	public boolean checkPassword(UserAccount userAccount, String password);
	
}
