package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.Admin;
import com.thalasoft.learnintouch.data.dao.domain.AdminModule;

public interface AdminModuleDao extends GenericDao<AdminModule, Serializable> {

	public AdminModule findWithModuleAndAdmin(String module, Admin admin);

	public List<AdminModule> findWithModule(String module);

	public List<AdminModule> findWithAdmin(Admin admin);

}
