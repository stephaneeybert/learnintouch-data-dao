package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.NavbarItem;
import com.thalasoft.learnintouch.data.dao.domain.NavbarLanguage;
import com.thalasoft.learnintouch.data.dao.domain.TemplateModel;

public interface NavbarItemDao extends GenericDao<NavbarItem, Serializable> {

	public NavbarItem findWithName(String name);

	public List<NavbarItem> findWithImage(String image);

	public List<NavbarItem> findWithImageOver(String imageOver);

	public List<NavbarItem> findWithNavbarLanguageOrderById(NavbarLanguage navbarLanguage);

	public List<NavbarItem> findWithNavbarLanguage(NavbarLanguage navbarLanguage);

	public NavbarItem findWithListOrder(NavbarLanguage navbarLanguage, int listOrder);

	public NavbarItem findNextWithListOrder(NavbarLanguage navbarLanguage, int listOrder);

	public NavbarItem findPreviousWithListOrder(NavbarLanguage navbarLanguage, int listOrder);

	public long countListOrderDuplicates(NavbarLanguage navbarLanguage);

	public long resetNavigationModelReferences(TemplateModel templateModel);
	
}
