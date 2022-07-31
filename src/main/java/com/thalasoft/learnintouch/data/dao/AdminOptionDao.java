package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.Admin;
import com.thalasoft.learnintouch.data.dao.domain.AdminOption;

public interface AdminOptionDao extends GenericDao<AdminOption, Serializable> {

	public AdminOption findWithNameAndAdmin(String option, Admin admin);

	public List<AdminOption> findWithName(String option);

	public List<AdminOption> findWithAdmin(Admin admin);

}
