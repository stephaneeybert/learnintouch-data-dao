package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.Form;
import com.thalasoft.learnintouch.data.dao.domain.FormItem;

public interface FormItemDao extends GenericDao<FormItem, Serializable> {

	public List<FormItem> findWithForm(Form form);

	public List<FormItem> findWithFormOrderById(Form form);

	public FormItem findWithListOrder(Form form, int listOrder);

	public FormItem findNextWithListOrder(Form form, int listOrder);

	public FormItem findPreviousWithListOrder(Form form, int listOrder);

	public long countListOrderDuplicates(Form form);

}
