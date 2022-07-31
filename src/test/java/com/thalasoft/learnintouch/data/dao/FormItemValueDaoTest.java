package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.FormDao;
import com.thalasoft.learnintouch.data.dao.FormItemDao;
import com.thalasoft.learnintouch.data.dao.FormItemValueDao;
import com.thalasoft.learnintouch.data.dao.domain.Form;
import com.thalasoft.learnintouch.data.dao.domain.FormItem;
import com.thalasoft.learnintouch.data.dao.domain.FormItemValue;

public class FormItemValueDaoTest extends AbstractDaoTest {

	@Autowired
	private FormItemValueDao formItemValueDao;
	
	@Autowired
	private FormItemDao formItemDao;
	
	@Autowired
	private FormDao formDao;

	private Form form;
	private FormItem formItem;
	private FormItemValue formItemValue0;
	private FormItemValue formItemValue1;
	private String value0 = "value0";
	private String value1 = "value1";

	protected void setFormItemValueDao(FormItemValueDao formItemValueDao) {
		this.formItemValueDao = formItemValueDao;
	}

	protected void setFormItemDao(FormItemDao formItemDao) {
		this.formItemDao = formItemDao;
	}

	protected void setFormDao(FormDao formDao) {
		this.formDao = formDao;
	}

	public FormItemValueDaoTest() {
		form = new Form();
		form.setName("myform");
		formItem = new FormItem();
		formItem.setName("myform");
		formItem.setListOrder(1);
		formItem.setType("mytype");
		formItem.setForm(form);
		formItemValue0 = new FormItemValue();
		formItemValue0.setValue(value0);
		formItemValue0.setFormItem(formItem);
		formItemValue1 = new FormItemValue();
		formItemValue1.setValue(value1);
		formItemValue1.setFormItem(formItem);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		form = formDao.makePersistent(form);
		formItem = formItemDao.makePersistent(formItem);
		formItemValue0 = formItemValueDao.makePersistent(formItemValue0);
		formItemValue1 = formItemValueDao.makePersistent(formItemValue1);
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(formItemValue0.getId());
		assertNotSame(formItemValue0.hashCode(), 0L);
		assertFalse(formItemValue0.toString().equals(""));
		FormItemValue retrievedFormItemValue = formItemValueDao.findWithId(formItemValue0.getId(), false);
		assertEquals(formItemValue0.hashCode(), retrievedFormItemValue.hashCode());
		assertEquals(formItemValue0.getValue(), retrievedFormItemValue.getValue());
		assertNotNull(retrievedFormItemValue.getId());
	}

	@Test
	public void testFindWithFormItem() {
		List<FormItemValue> formItemValues = formItemValueDao.findWithFormItem(formItem);
		assertEquals(2, formItemValues.size());
		assertEquals(formItemValue0.getFormItem().getId(), formItemValues.get(0).getFormItem().getId());
	}

}
