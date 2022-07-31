package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.FormItem;
import com.thalasoft.learnintouch.data.dao.domain.FormValid;

public interface FormValidDao extends GenericDao<FormValid, Serializable> {

	public List<FormValid> findWithFormItem(FormItem formItem);

}
