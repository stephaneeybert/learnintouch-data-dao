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
import com.thalasoft.learnintouch.data.dao.FormValidDao;
import com.thalasoft.learnintouch.data.dao.domain.Form;
import com.thalasoft.learnintouch.data.dao.domain.FormItem;
import com.thalasoft.learnintouch.data.dao.domain.FormValid;

public class FormValidDaoTest extends AbstractDaoTest {

	@Autowired
	private FormValidDao formValidDao;
	
	@Autowired
	private FormItemDao formItemDao;

	@Autowired
	private FormDao formDao;

	private Form form;
	private FormItem formItem;
	private FormValid formValid0;
	private FormValid formValid1;
	
	protected void setFormValidDao(FormValidDao formValidDao) {
		this.formValidDao = formValidDao;
	}

	protected void setFormItemDao(FormItemDao formItemDao) {
		this.formItemDao = formItemDao;
	}

	protected void setFormDao(FormDao formDao) {
		this.formDao = formDao;
	}

	public FormValidDaoTest() {
		form = new Form();
		form.setName("myform");
		formItem = new FormItem();
		formItem.setName("myform");
		formItem.setListOrder(1);
		formItem.setType("mytype");
		formItem.setForm(form);
		formValid0 = new FormValid();
		formValid0.setType("mytype");
		formValid0.setFormItem(formItem);
		formValid1 = new FormValid();
		formValid1.setType("atype");
		formValid1.setFormItem(formItem);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		form = formDao.makePersistent(form);
		formItem = formItemDao.makePersistent(formItem);
		formValid1 = formValidDao.makePersistent(formValid1);
		formValid0 = formValidDao.makePersistent(formValid0);
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(formValid0.getId());
		assertNotSame(formValid0.hashCode(), 0L);
		assertFalse(formValid0.toString().equals(""));
		FormValid retrievedFormValid = formValidDao.findWithId(formValid0.getId(), false);
		assertEquals(formValid0.hashCode(), retrievedFormValid.hashCode());
		assertEquals(formValid0.getType(), retrievedFormValid.getType());
		assertNotNull(retrievedFormValid.getId());
	}

	@Test
	public void testFindWithFormItem() {
		List<FormValid> formValids = formValidDao.findWithFormItem(formItem);
		assertEquals(2, formValids.size());
		assertEquals(formValid0.getFormItem().getId(), formValids.get(0).getFormItem().getId());
	}

}
