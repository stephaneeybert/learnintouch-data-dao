package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.AdminDao;
import com.thalasoft.learnintouch.data.dao.MailDao;
import com.thalasoft.learnintouch.data.dao.domain.Admin;
import com.thalasoft.learnintouch.data.dao.domain.Mail;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class AdminDaoTest extends AbstractDaoTest {

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private MailDao mailDao;

	private Admin admin0;
	private Admin admin1;
	private Admin admin2;
	private List<Admin> manyAdmins;
	private Mail mail0;
	private Mail mail1;
	private Mail mail2;
	private Mail mail3;
	private Mail mail4;
	private Mail mail5;

	private String subject0 = "Hello from Thalasoft";
	private String subject1 = "A history of mailing";
	private String subject2 = "My proposition";
	private String body = "<p>The body of an email</p>";
	private String image = "<img src='myimage.png' border='0'>";
	private String bodyWithImage = "A body with an image " + image;
	private String attachedFile = "myattachedfile.pdf";

	protected void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	protected void setMailDao(MailDao mailDao) {
		this.mailDao = mailDao;
	}

	public AdminDaoTest() {
		admin0 = new Admin();
		admin0.setLogin("stephane");
		admin0.setPassword("toto");
        admin0.setPasswordSalt("");
		admin0.setEmail("stephane@thalasoft.com");
		admin0.setFirstname("Stephane");
		admin0.setLastname("Eybert");

		admin1 = new Admin();
		admin1.setLogin("marc");
		admin1.setPassword("marcus");
        admin1.setPasswordSalt("");
		admin1.setEmail("marceybert@yahoo.fr");
		admin1.setFirstname("Marc");
		admin1.setLastname("Eybert");

		admin2 = new Admin();
		admin2.setLogin("cyril");
		admin2.setPassword("cyr");
        admin2.setPasswordSalt("");
		admin2.setEmail("cyril@yahoo.es");
		admin2.setFirstname("Cyril");
		admin2.setLastname("Eybert");

		manyAdmins = new ArrayList<Admin>();
		for (int i = 0; i < 50; i++) {
			Admin oneAdmin = new Admin();
			String index = intToString(i, 3);
			oneAdmin.setFirstname("zfirstname" + index);
			oneAdmin.setLastname("zlastname" + index);
			oneAdmin.setEmail("zemail@thalasoft.com" + index);
			oneAdmin.setLogin("zlogin" + index);
			oneAdmin.setPassword("zpassword" + index);
	        oneAdmin.setPasswordSalt("");
			manyAdmins.add(oneAdmin);
		}
	}

	private String intToString(int num, int digits) {
		String output = Integer.toString(num);
		while (output.length() < digits) {
			output = "0" + output;
		}
		return output;
	}

	@Before
	public void beforeAnyTest() throws Exception {
		admin0 = adminDao.makePersistent(admin0);
		admin1 = adminDao.makePersistent(admin1);
		admin2 = adminDao.makePersistent(admin2);
		for (Admin oneAdmin : manyAdmins) {
			oneAdmin = adminDao.makePersistent(oneAdmin);
		}
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(admin2.getId());
		admin2.setPassword("mypass");
		Admin loadedAdmin = adminDao.findWithId(admin2.getId(), true);
		assertNotNull(loadedAdmin.getId());
		assertEquals(admin2.getEmail(), loadedAdmin.getEmail());
		assertEquals(admin2.getPassword(), loadedAdmin.getPassword());
		assertNotSame(admin2.hashCode(), 0L);
		assertEquals(admin2.hashCode(), loadedAdmin.hashCode());
		assertFalse(admin2.toString().equals(""));
		loadedAdmin = adminDao.findWithLogin(admin0.getLogin());
		assertEquals(admin0.getLogin(), loadedAdmin.getLogin());
		loadedAdmin = adminDao.findWithLoginAndPassword(admin0.getLogin(), "toto");
		assertEquals(admin0.getLogin(), loadedAdmin.getLogin());
	}

	@Test
	public void testFindAll() {
		Page<Admin> page = adminDao.findAll(0, 10);
		List<Admin> admins = page.getPageItems();
		assertEquals(admin2.getLogin(), admins.get(0).getLogin());
		assertEquals(admin1.getLogin(), admins.get(1).getLogin());
		assertEquals(admin0.getLogin(), admins.get(2).getLogin());
		page = adminDao.findAll(1, 2);
		admins = page.getPageItems();
		assertEquals(2, admins.size());
		assertEquals(admin2.getLogin(), admins.get(0).getLogin());
		assertEquals(admin1.getLogin(), admins.get(1).getLogin());
		page = adminDao.findAll(1, 1);
		admins = page.getPageItems();
		assertEquals(1, admins.size());
		assertEquals(admin2.getLogin(), admins.get(0).getLogin());
		page = adminDao.findAll(1, 10);
		admins = page.getPageItems();
		assertEquals(10, admins.size());
		page = adminDao.findAll(1, 1);
		admins = page.getPageItems();
		assertEquals(1, admins.size());
		page = adminDao.findAll(0, 0);
		admins = page.getPageItems();
		assertEquals(53, admins.size());
	}

	@Test
	public void testFindWithLogin() {
		Admin retrievedAdmin = adminDao.findWithLogin(admin1.getLogin());
		assertEquals(admin1.getLogin(), retrievedAdmin.getLogin());
	}

	@Test
	public void testFindWithLoginAndPassword() {
		Admin retrievedAdmin = adminDao.findWithLoginAndPassword(admin1.getLogin(), "marcus");
		assertEquals(admin1.getLogin(), retrievedAdmin.getLogin());
	}

	@Test
	public void testFindWithEmail() {
		Admin retrievedAdmin = adminDao.findWithEmail("marceybert@yahoo.fr");
		assertEquals(admin1.getLogin(), retrievedAdmin.getLogin());
	}

	@Test
	public void testFindWithPatternLike() {
		Page<Admin> page = adminDao.findWithPatternLike(admin1.getLogin(), 1, 10);
		List<Admin> admins = page.getPageItems();
		assertEquals(1, admins.size());
		assertEquals(admin1.getLogin(), admins.get(0).getLogin());
		page = adminDao.findWithPatternLike("hoo", 1, 10);
		admins = page.getPageItems();
		assertEquals(2, admins.size());
		assertEquals(admin2.getLogin(), admins.get(0).getLogin());
		assertEquals(admin1.getLogin(), admins.get(1).getLogin());
		page = adminDao.findWithPatternLike("hoo", 1, 1);
		admins = page.getPageItems();
		assertEquals(1, admins.size());
		assertEquals(admin2.getLogin(), admins.get(0).getLogin());
		page = adminDao.findWithPatternLike("hoo", 2, 1);
		admins = page.getPageItems();
		assertEquals(1, admins.size());
		assertEquals(admin1.getLogin(), admins.get(0).getLogin());
	}

	@Test
	public void testFindAllNonSuperAdminNorWithLogin() {
		admin1.setSuperAdmin(true);
		Page<Admin> page = adminDao.findAllNonSuperAdminPlusSpecifiedOne("unknown", 1, 10);
		List<Admin> admins = page.getPageItems();
		assertEquals(admin2.getLogin(), admins.get(0).getLogin());
		assertEquals(admin0.getLogin(), admins.get(1).getLogin());
		assertEquals("zlogin000", admins.get(2).getLogin());
		assertEquals(10, admins.size());
		page = adminDao.findAllNonSuperAdminPlusSpecifiedOne(admin1.getLogin(), 1, 5);
		admins = page.getPageItems();
		assertEquals(admin2.getLogin(), admins.get(0).getLogin());
		assertEquals(admin1.getLogin(), admins.get(1).getLogin());
		assertEquals(admin0.getLogin(), admins.get(2).getLogin());
		assertEquals("zlogin000", admins.get(3).getLogin());
		assertEquals(5, admins.size());
		admin1.setSuperAdmin(false);
		page = adminDao.findAllNonSuperAdminPlusSpecifiedOne(admin2.getLogin(), 1, 10);
		admins = page.getPageItems();
		assertEquals(10, admins.size());
		assertEquals(admin2.getLogin(), admins.get(0).getLogin());
		assertEquals(admin1.getLogin(), admins.get(1).getLogin());
	}

	@Test
	public void testPagination() {
		Page<Admin> page = adminDao.findAllNonSuperAdminPlusSpecifiedOne(admin2.getLogin(), 1, 10);
		List<Admin> admins = page.getPageItems();
		assertEquals(10, admins.size());
		assertEquals(1, page.getPageNumber());
		assertEquals(10, page.getPageSize());
		assertEquals(6, page.getNumberOfPages());
		assertEquals(6, page.getLastPageNumber());
		assertEquals(2, page.getNextPageNumber());
		assertEquals(1, page.getPreviousPageNumber());
		assertFalse(page.hasPreviousPage());
		assertTrue(page.hasNextPage());
		assertEquals(53, page.getTotalSize());
		page = adminDao.findAllNonSuperAdminPlusSpecifiedOne(admin2.getLogin(), 4, 10);
		admins = page.getPageItems();
		assertEquals(10, admins.size());
		assertEquals(4, page.getPageNumber());
		assertEquals(10, page.getPageSize());
		assertEquals(6, page.getNumberOfPages());
		assertEquals(6, page.getLastPageNumber());
		assertEquals(5, page.getNextPageNumber());
		assertEquals(3, page.getPreviousPageNumber());
		assertTrue(page.hasPreviousPage());
		assertTrue(page.hasNextPage());
		assertEquals(53, page.getTotalSize());
	}

	@Test
	public void testPaginationIgnoresChildren() {
		addChildren();
		Page<Admin> page = adminDao.findAll(1, 2);
		List<Admin> admins = page.getPageItems();
		assertEquals(admin2.getFirstname(), admins.get(0).getFirstname());
		assertEquals(admin1.getFirstname(), admins.get(1).getFirstname());
		page = adminDao.findAll(2, 2);
		admins = page.getPageItems();
		assertEquals(admin0.getFirstname(), admins.get(0).getFirstname());
	}

	private void addChildren() {
		mail0 = new Mail();
		mail0.setCreationDatetime(new LocalDateTime());
		mail0.setSendDatetime(new LocalDateTime());
		mail0.setSubject(subject0);
		mail0.setBody(body);
		mail0.setAdmin(admin2);
		mail1 = new Mail();
		mail1.setCreationDatetime(new LocalDateTime());
		mail1.setSendDatetime(new LocalDateTime().plusDays(1));
		mail1.setSubject(subject1);
		mail1.setBody(body);
		mail1.setAttachments(attachedFile);
		mail1.setAdmin(admin2);
		mail2 = new Mail();
		mail2.setCreationDatetime(new LocalDateTime());
		mail2.setSendDatetime(new LocalDateTime().plusDays(2));
		mail2.setSubject(subject2);
		mail2.setBody(bodyWithImage);
		mail2.setAdmin(admin2);
		mail3 = new Mail();
		mail3.setCreationDatetime(new LocalDateTime());
		mail3.setSendDatetime(new LocalDateTime());
		mail3.setSubject(subject0);
		mail3.setBody(body);
		mail3.setAdmin(admin1);
		mail4 = new Mail();
		mail4.setCreationDatetime(new LocalDateTime());
		mail4.setSendDatetime(new LocalDateTime().plusDays(1));
		mail4.setSubject(subject1);
		mail4.setBody(body);
		mail4.setAttachments(attachedFile);
		mail4.setAdmin(admin1);
		mail5 = new Mail();
		mail5.setCreationDatetime(new LocalDateTime());
		mail5.setSendDatetime(new LocalDateTime().plusDays(2));
		mail5.setSubject(subject2);
		mail5.setBody(bodyWithImage);
		mail5.setAdmin(admin1);
		mail0 = mailDao.makePersistent(mail0);
		mail1 = mailDao.makePersistent(mail1);
		mail2 = mailDao.makePersistent(mail2);
		mail3 = mailDao.makePersistent(mail3);
		mail4 = mailDao.makePersistent(mail4);
		mail5 = mailDao.makePersistent(mail5);
	}

}