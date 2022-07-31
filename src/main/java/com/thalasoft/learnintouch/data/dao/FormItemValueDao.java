package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.FormItem;
import com.thalasoft.learnintouch.data.dao.domain.FormItemValue;

public interface FormItemValueDao extends GenericDao<FormItemValue, Serializable> {

	public List<FormItemValue> findWithFormItem(FormItem formItem);

}
