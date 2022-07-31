package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.Navmenu;
import com.thalasoft.learnintouch.data.dao.domain.NavmenuItem;
import com.thalasoft.learnintouch.data.dao.domain.NavmenuLanguage;

public interface NavmenuLanguageDao extends GenericDao<NavmenuLanguage, Serializable> {

	public List<NavmenuLanguage> findWithNavmenu(Navmenu navmenu);

	public NavmenuLanguage findWithNavmenuItem(NavmenuItem navmenuItem);

	public NavmenuLanguage findWithNavmenuAndLanguage(Navmenu navmenu, String language);

	public NavmenuLanguage findWithNavmenuAndNoLanguage(Navmenu navmenu);

}
