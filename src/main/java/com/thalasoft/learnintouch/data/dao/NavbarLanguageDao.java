package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.Navbar;
import com.thalasoft.learnintouch.data.dao.domain.NavbarLanguage;

public interface NavbarLanguageDao extends GenericDao<NavbarLanguage, Serializable> {

	public List<NavbarLanguage> findWithNavbar(Navbar navbar);

	public NavbarLanguage findWithNavbarAndLanguage(Navbar navbar, String language);

	public NavbarLanguage findWithNavbarAndNoLanguage(Navbar navbar);

}
