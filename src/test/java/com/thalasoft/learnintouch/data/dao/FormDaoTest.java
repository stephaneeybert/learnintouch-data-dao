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
import com.thalasoft.learnintouch.data.dao.domain.Form;

public class FormDaoTest extends AbstractDaoTest {

	@Autowired
	private FormDao formDao;
	
	private Form form0;
	private Form form1;
	private String name0 = "form0";
	private String name1 = "form1";
	private String image = "image.png";
	
	protected void setFormDao(FormDao formDao) {
		this.formDao = formDao;
	}

	public FormDaoTest() {
		form0 = new Form();
		form0.setName(name0);
		form1 = new Form();
		form1.setName(name1);
		form1.setImage(image);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		form1 = formDao.makePersistent(form1);
		form0 = formDao.makePersistent(form0);
	}
	
	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(form0.getId());
		assertNotSame(form0.hashCode(), 0L);
		assertFalse(form0.toString().equals(""));
		Form retrievedForm = formDao.findWithId(form0.getId(), false);
		assertEquals(form0.hashCode(), retrievedForm.hashCode());
		assertEquals(form0.getName(), retrievedForm.getName());
		assertNotNull(retrievedForm.getId());
	}
	
	@Test
	public void testFindWithName() {
		Form form = formDao.findWithName(name1);
		assertEquals(name1, form.getName());
	}

	@Test
	public void testFindWithImage() {
		List<Form> forms = formDao.findWithImage(image);
		assertEquals(1, forms.size());
		assertEquals(image, forms.get(0).getImage());
	}

}
