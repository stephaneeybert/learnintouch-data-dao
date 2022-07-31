package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.NavmenuItem;
import com.thalasoft.learnintouch.data.dao.domain.TemplateModel;

public interface NavmenuItemDao extends GenericDao<NavmenuItem, Serializable> {

	public NavmenuItem findWithName(String name);

	public List<NavmenuItem> findWithParent(NavmenuItem parent);

	public List<NavmenuItem> findWithParentOrderById(NavmenuItem parent);

	public NavmenuItem findWithListOrder(NavmenuItem parent, int listOrder);

	public NavmenuItem findNextWithListOrder(NavmenuItem parent, int listOrder);

	public NavmenuItem findPreviousWithListOrder(NavmenuItem parent, int listOrder);

	public long countListOrderDuplicates(NavmenuItem parent);

	public List<NavmenuItem> findWithImage(String image);

	public List<NavmenuItem> findWithImageOver(String imageOver);

	public long resetNavigationModelReferences(TemplateModel templateModel);

}
