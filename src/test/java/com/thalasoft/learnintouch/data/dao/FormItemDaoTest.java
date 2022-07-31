package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.FormDao;
import com.thalasoft.learnintouch.data.dao.FormItemDao;
import com.thalasoft.learnintouch.data.dao.domain.Form;
import com.thalasoft.learnintouch.data.dao.domain.FormItem;

public class FormItemDaoTest extends AbstractDaoTest {

	@Autowired
	private FormItemDao formItemDao;

	@Autowired
	private FormDao formDao;

	private Form form;
	private FormItem formItem0;
	private FormItem formItem1;
	private FormItem formItem2;

	protected void setFormItemDao(FormItemDao formItemDao) {
		this.formItemDao = formItemDao;
	}

	public FormItemDaoTest() {
		form = new Form();
		form.setName("myform");
		formItem0 = new FormItem();
		formItem0.setName("formItem0");
		formItem0.setListOrder(2);
		formItem0.setType("itemType0");
		formItem0.setForm(form);
		formItem1 = new FormItem();
		formItem1.setName("formItem1");
		formItem1.setListOrder(1);
		formItem1.setType("itemType0");
		formItem1.setForm(form);
		formItem2 = new FormItem();
		formItem2.setName("formItem1");
		formItem2.setListOrder(3);
		formItem2.setType("itemType0");
		formItem2.setForm(form);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		form = formDao.makePersistent(form);
		formItem2 = formItemDao.makePersistent(formItem2);
		formItem0 = formItemDao.makePersistent(formItem0);
		formItem1 = formItemDao.makePersistent(formItem1);
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(formItem0.getId());
		assertNotSame(formItem0.hashCode(), 0L);
		assertFalse(formItem0.toString().equals(""));
		FormItem retrievedFormItem = formItemDao.findWithId(formItem0.getId(), false);
		assertEquals(formItem0.hashCode(), retrievedFormItem.hashCode());
		assertEquals(formItem0.getName(), retrievedFormItem.getName());
		assertNotNull(retrievedFormItem.getId());
	}

	@Test
	public void testFindWithForm() {
		List<FormItem> formItems = formItemDao.findWithForm(form);
		assertEquals(3, formItems.size());
		assertEquals(formItem1.getListOrder(), formItems.get(0).getListOrder());
		assertEquals(formItem1.getName(), formItems.get(0).getName());
		assertEquals(formItem0.getListOrder(), formItems.get(1).getListOrder());
		assertEquals(formItem0.getName(), formItems.get(1).getName());
		assertEquals(formItem2.getListOrder(), formItems.get(2).getListOrder());
		assertEquals(formItem2.getName(), formItems.get(2).getName());
	}

	@Test
	public void testFindWithFormOrderById() {
		List<FormItem> formItems = formItemDao.findWithFormOrderById(form);
		assertEquals(3, formItems.size());
		assertEquals(formItem2.getListOrder(), formItems.get(0).getListOrder());
		assertEquals(formItem2.getName(), formItems.get(0).getName());
		assertEquals(formItem0.getListOrder(), formItems.get(1).getListOrder());
		assertEquals(formItem0.getName(), formItems.get(1).getName());
		assertEquals(formItem1.getListOrder(), formItems.get(2).getListOrder());
		assertEquals(formItem1.getName(), formItems.get(2).getName());
	}

	@Test
	public void testFindWithListOrder() {
		FormItem formItem = formItemDao.findWithListOrder(form, 2);
		assertEquals(formItem0.getListOrder(), formItem.getListOrder());
		assertEquals(formItem0.getId(), formItem.getId());
		formItem = formItemDao.findWithListOrder(null, 2);
		assertNull(formItem);
	}

	@Test
	public void testFindNextByListOrder() {
		FormItem formItem = formItemDao.findNextWithListOrder(form, 1);
		assertEquals(formItem0.getListOrder(), formItem.getListOrder());
		formItem = formItemDao.findNextWithListOrder(form, 2);
		assertEquals(formItem2.getListOrder(), formItem.getListOrder());
	}

	@Test
	public void testFindPreviousByListOrder() {
		FormItem formItem = formItemDao.findPreviousWithListOrder(form, 2);
		assertEquals(formItem1.getListOrder(), formItem.getListOrder());
		formItem = formItemDao.findPreviousWithListOrder(form, 3);
		assertEquals(formItem0.getListOrder(), formItem.getListOrder());
		formItem = formItemDao.findPreviousWithListOrder(form, 4);
		assertEquals(formItem2.getListOrder(), formItem.getListOrder());
		formItem = formItemDao.findPreviousWithListOrder(form, 5);
		assertEquals(formItem2.getListOrder(), formItem.getListOrder());
	}

	@Test
	public void testCountListOrderDuplicates() {
		long count = formItemDao.countListOrderDuplicates(form);
		assertEquals(0, count);
		formItem2.setListOrder(2);
		count = formItemDao.countListOrderDuplicates(form);
		assertEquals(2, count);
	}

}
