package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.Navlink;
import com.thalasoft.learnintouch.data.dao.domain.NavlinkItem;
import com.thalasoft.learnintouch.data.dao.domain.TemplateModel;

public interface NavlinkItemDao extends GenericDao<NavlinkItem, Serializable> {

	public long resetNavigationModelReferences(TemplateModel templateModel);

	public NavlinkItem findWithNavlinkAndNoLanguage(Navlink navlink);

	public NavlinkItem findWithNavlinkAndLanguage(Navlink navlink, String languageCode);

	public List<NavlinkItem> findWithNavlink(Navlink navlink);

	public List<NavlinkItem> findWithImage(String image);

	public List<NavlinkItem> findWithImageOver(String imageOver);

}
