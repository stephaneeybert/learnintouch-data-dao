package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.NavmenuItem;
import com.thalasoft.learnintouch.data.jpa.domain.TemplateModel;

public interface NavmenuItemRepository extends GenericRepository<NavmenuItem, Long> {

	@Query("UPDATE NavmenuItem SET templateModel = NULL WHERE templateModel = :templateModel")
	public void resetNavigationModelReferences(@Param("templateModel") TemplateModel templateModel);

	public NavmenuItem findByName(String name);

    @Query("SELECT n FROM NavmenuItem n WHERE n.parent = :parent OR (parent IS NULL AND :parent < '1') ORDER BY n.listOrder")
    public List<NavmenuItem> findByParent(@Param("parent") NavmenuItem parent);

    @Query("SELECT n FROM NavmenuItem n WHERE n.parent = :parent OR (parent IS NULL AND :parent < '1') ORDER BY n.id")
    public List<NavmenuItem> findByParentOrderById(@Param("parent") NavmenuItem parent);

	@Query("SELECT n FROM NavmenuItem n WHERE (n.parent = :parent OR (parent IS NULL AND :parent < '1')) AND n.listOrder > :listOrder ORDER BY n.listOrder ASC LIMIT 1")
	public NavmenuItem findByNextListOrder(@Param("parent") NavmenuItem parent, @Param("listOrder") int listOrder);

	@Query("SELECT n FROM NavmenuItem n WHERE (n.parent = :parent OR (parent IS NULL AND :parent < '1')) AND n.listOrder < :listOrder ORDER BY n.listOrder DESC LIMIT 1")
	public NavmenuItem findByPreviousListOrder(@Param("parent") NavmenuItem parent, @Param("listOrder") int listOrder);

	@Query("SELECT n FROM NavmenuItem n WHERE (n.parent = :parent OR (parent IS NULL AND :parent < '1')) AND n.listOrder = :listOrder ORDER BY n.listOrder DESC")
	public Page<NavmenuItem> findByListOrder(@Param("parent") NavmenuItem parent, @Param("listOrder") int listOrder, Pageable page);

	public List<NavmenuItem> findByImage(String image);

	public List<NavmenuItem> findByImageOver(String imageOver);

    @Query("SELECT COUNT(DISTINCT ni1.id) as count FROM NavmenuItem ni1, NavmenuItem ni2 WHERE ni1.id != ni2.id AND ni1.parent.id = ni2.parent.id AND ni1.listOrder = ni2.listOrder AND ni1.parent = :parent")
    public Long countDuplicateListOrders(@Param("parent") NavmenuItem parent);

}
