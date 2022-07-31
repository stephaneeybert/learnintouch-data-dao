package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.Navlink;
import com.thalasoft.learnintouch.data.jpa.domain.NavlinkItem;
import com.thalasoft.learnintouch.data.jpa.domain.TemplateModel;

public interface NavlinkItemRepository extends GenericRepository<NavlinkItem, Long> {

	@Query("UPDATE NavlinkItem SET templateModel = NULL WHERE templateModel = :templateModel")
	public void resetNavigationModelReferences(@Param("templateModel") TemplateModel templateModel);

	public NavlinkItem findByNavlinkAndLanguageCode(Navlink navlink, String languageCode);

	public NavlinkItem findByNavlinkAndLanguageCodeIsNull(Navlink navlink);

	public List<NavlinkItem> findByNavlink(Navlink navlink);

	public List<NavlinkItem> findByImage(String image);

	public List<NavlinkItem> findByImageOver(String imageOver);

}
