package com.thalasoft.learnintouch.data.service;

import com.thalasoft.learnintouch.data.dao.domain.Admin;

public interface AdminService {

    public Admin savePassword(Admin admin, String password);
    
	public boolean checkPassword(Admin admin, String password);
	
	public Admin save(Admin admin);
	
	public boolean isNotUsedByAnyMail(Admin admin);

	public boolean isNotUsedByAnySms(Admin admin);

}
