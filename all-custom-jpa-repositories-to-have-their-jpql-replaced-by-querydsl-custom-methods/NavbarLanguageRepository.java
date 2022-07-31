package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thalasoft.learnintouch.data.jpa.domain.Navbar;
import com.thalasoft.learnintouch.data.jpa.domain.NavbarLanguage;

public interface NavbarLanguageRepository extends GenericRepository<NavbarLanguage, Long> {

	public Page<NavbarLanguage> findByNavbar(Navbar navbar, Pageable page);
	
	public NavbarLanguage findByNavbarAndLanguageCode(Navbar navbar, String languageCode);
	
	public NavbarLanguage findByNavbarAndLanguageCodeIsNull(Navbar navbar);
	
}
