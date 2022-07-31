package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thalasoft.learnintouch.data.jpa.domain.Navmenu;
import com.thalasoft.learnintouch.data.jpa.domain.NavmenuItem;
import com.thalasoft.learnintouch.data.jpa.domain.NavmenuLanguage;

public interface NavmenuLanguageRepository extends GenericRepository<NavmenuLanguage, Long> {

	public Page<NavmenuLanguage> findByNavmenu(Navmenu navmenu, Pageable page);

	public NavmenuLanguage findByNavmenuAndLanguageCode(Navmenu navmenu, String languageCode);
	
	public NavmenuLanguage findByNavmenuAndLanguageCodeIsNull(Navmenu navmenu);
	
    public NavmenuLanguage findByNavmenuItem(NavmenuItem navmenuItem);

}
