package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.NavbarItem;
import com.thalasoft.learnintouch.data.jpa.domain.NavbarLanguage;
import com.thalasoft.learnintouch.data.jpa.domain.TemplateModel;
import com.thalasoft.learnintouch.data.jpa.domain.Webpage;

public interface NavbarItemRepository extends GenericRepository<NavbarItem, Long> {

	@Query("UPDATE NavbarItem SET templateModel = NULL WHERE templateModel = :templateModel")
	public void resetNavigationModelReferences(@Param("templateModel") TemplateModel templateModel);

	public NavbarItem findByName(String name);

    @Query("SELECT n FROM NavbarItem n WHERE n.navbarLanguage = :navbarLanguage ORDER BY n.listOrder")
	public List<NavbarItem> findByNavbarLanguageOrderByListOrder(@Param("navbarLanguage") NavbarLanguage navbarLanguage);

    @Query("SELECT n FROM NavbarItem n WHERE n.navbarLanguage = :navbarLanguage ORDER BY n.id")
    public List<NavbarItem> findByNavbarLanguageOrderById(@Param("navbarLanguage") NavbarLanguage navbarLanguage);

	@Query("SELECT n FROM NavbarItem n WHERE n.navbarLanguage = :navbarLanguage AND n.listOrder > :listOrder ORDER BY n.listOrder ASC LIMIT 1")
	public NavbarItem findByNextListOrder(@Param("navbarLanguage") NavbarLanguage navbarLanguage, @Param("listOrder") int listOrder);

	@Query("SELECT n FROM NavbarItem n WHERE n.navbarLanguage = :navbarLanguage AND n.listOrder < :listOrder ORDER BY n.listOrder DESC LIMIT 1")
	public NavbarItem findByPreviousListOrder(@Param("navbarLanguage") NavbarLanguage navbarLanguage, @Param("listOrder") int listOrder);

	@Query("SELECT n FROM NavbarItem n WHERE n.navbarLanguage = :navbarLanguage AND n.listOrder = :listOrder ORDER BY n.listOrder DESC")
	public List<NavbarItem> findByListOrder(@Param("navbarLanguage") NavbarLanguage navbarLanguage, @Param("listOrder") int listOrder);

	@Query("SELECT COUNT(DISTINCT ni1.id) as count FROM NavbarItem ni1, NavbarItem ni2 WHERE ni1.id != ni2.id AND ni1.navbarLanguage.id = ni2.navbarLanguage.id AND ni1.listOrder = ni2.listOrder AND ni1.navbarLanguage = :navbarLanguage")
	public Long countDuplicateListOrders(@Param("navbarLanguage") NavbarLanguage navbarLanguage);
	
	public List<NavbarItem> findByImage(String image);

	public List<NavbarItem> findByImageOver(String imageOver);

}
