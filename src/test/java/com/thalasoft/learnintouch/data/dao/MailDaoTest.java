package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.AdminDao;
import com.thalasoft.learnintouch.data.dao.MailCategoryDao;
import com.thalasoft.learnintouch.data.dao.MailDao;
import com.thalasoft.learnintouch.data.dao.domain.Admin;
import com.thalasoft.learnintouch.data.dao.domain.Mail;
import com.thalasoft.learnintouch.data.dao.domain.MailCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class MailDaoTest extends AbstractDaoTest {

	@Autowired
	private MailDao mailDao;

	@Autowired
	private MailCategoryDao mailCategoryDao;

	@Autowired
	private AdminDao adminDao;

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
	private MailCategory mailCategory1;
	private MailCategory mailCategory2;
	private Admin admin1;
	private Admin admin2;

	protected void setMailDao(MailDao mailDao) {
		this.mailDao = mailDao;
	}

	protected void setMailCategoryDao(MailCategoryDao mailCategoryDao) {
		this.mailCategoryDao = mailCategoryDao;
	}

	protected void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public MailDaoTest() {
		mail0 = new Mail();
		mail0.setCreationDatetime(new LocalDateTime());
		mail0.setSendDatetime(new LocalDateTime());
		mail0.setSubject(subject0);
		mail0.setBody(body);
		mail1 = new Mail();
		mail1.setCreationDatetime(new LocalDateTime());
		mail1.setSendDatetime(new LocalDateTime().plusDays(1));
		mail1.setSubject(subject1);
		mail1.setBody(body);
		mail1.setAttachments(attachedFile);
		mail2 = new Mail();
		mail2.setCreationDatetime(new LocalDateTime());
		mail2.setSendDatetime(new LocalDateTime().plusDays(2));
		mail2.setSubject(subject2);
		mail2.setBody(bodyWithImage);
		mail3 = new Mail();
		mail3.setCreationDatetime(new LocalDateTime());
		mail3.setSendDatetime(new LocalDateTime());
		mail3.setSubject(subject0);
		mail3.setBody(body);
		mail4 = new Mail();
		mail4.setCreationDatetime(new LocalDateTime());
		mail4.setSendDatetime(new LocalDateTime().plusDays(1));
		mail4.setSubject(subject1);
		mail4.setBody(body);
		mail4.setAttachments(attachedFile);
		mail5 = new Mail();
		mail5.setCreationDatetime(new LocalDateTime());
		mail5.setSendDatetime(new LocalDateTime().plusDays(2));
		mail5.setSubject(subject2);
		mail5.setBody(bodyWithImage);

		mailCategory1 = new MailCategory();
		mailCategory1.setName("marketing");
		mailCategory2 = new MailCategory();
		mailCategory2.setName("marketing2");

		admin1 = new Admin();
		admin1.setFirstname("Stephane");
		admin1.setLastname("Eybert");
		admin1.setLogin("stephane");
		admin1.setPassword("toto");
        admin1.setPasswordSalt("");
		admin1.setEmail("stephane@thalasoft.com");
		admin2 = new Admin();
		admin2.setFirstname("Stephane2");
		admin2.setLastname("Eybert2");
		admin2.setLogin("stephane2");
		admin2.setPassword("toto2");
        admin2.setPasswordSalt("");
		admin2.setEmail("stephane2@thalasoft.com");
	}

	@Before
	public void beforeAnyTest() throws Exception {
		mailCategory1 = mailCategoryDao.makePersistent(mailCategory1);
		mailCategory2 = mailCategoryDao.makePersistent(mailCategory2);
		mail1 = mailDao.makePersistent(mail1);
		mail0 = mailDao.makePersistent(mail0);
		mail2 = mailDao.makePersistent(mail2);
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(mail0.getId());
		assertNotSame(mail0.hashCode(), 0L);
		assertFalse(mail0.toString().equals(""));
		Mail retrievedMail = mailDao.findWithId(mail0.getId(), true);
		assertEquals(mail0.hashCode(), retrievedMail.hashCode());
		assertEquals(mail0.getSubject(), retrievedMail.getSubject());
		assertEquals(mail0.getBody(), retrievedMail.getBody());
		assertNotNull(retrievedMail.getId());
	}

	@Test
	public void testFindAll() {
		Page<Mail> page = mailDao.findAll(0, 0);
		List<Mail> mails = page.getPageItems();
		assertEquals(3, mails.size());
		assertEquals(subject1, mails.get(0).getSubject());
		assertEquals(subject0, mails.get(1).getSubject());
		assertEquals(subject2, mails.get(2).getSubject());
	}

	@Test
	public void testFindWithCategory() {
		mailCategory1 = mailCategoryDao.makePersistent(mailCategory1);
		mail0.setMailCategory(mailCategory1);
		mail2.setMailCategory(mailCategory1);
		Page<Mail> page = mailDao.findWithCategory(mailCategory1, 0, 0);
		List<Mail> mails = page.getPageItems();
		assertEquals(2, mails.size());
		assertEquals(subject0, mails.get(0).getSubject());
		assertEquals(subject2, mails.get(1).getSubject());
		page = mailDao.findWithCategory(null, 0, 0);
		mails = page.getPageItems();
		assertEquals(1, mails.size());
		assertEquals(subject1, mails.get(0).getSubject());
	}

	@Test
	public void testFindWithAdmin() {
		admin1 = adminDao.makePersistent(admin1);
		mail1.setAdmin(admin1);
		mail2.setAdmin(admin1);
		Page<Mail> page = mailDao.findWithAdmin(admin1, 0, 0);
		List<Mail> mails = page.getPageItems();
		assertEquals(2, mails.size());
		assertEquals(subject1, mails.get(0).getSubject());
		assertEquals(subject2, mails.get(1).getSubject());
		page = mailDao.findWithAdmin(null, 0, 0);
		mails = page.getPageItems();
		assertEquals(1, mails.size());
		assertEquals(mail0.getId(), mails.get(0).getId());
	}

	@Test
	public void testFindWithAdminAndCategory() {
		admin1 = adminDao.makePersistent(admin1);
		mailCategory1 = mailCategoryDao.makePersistent(mailCategory1);
		mail1.setAdmin(admin1);
		mail2.setAdmin(admin1);
		mail0.setMailCategory(mailCategory1);
		mail2.setMailCategory(mailCategory1);
		Page<Mail> page = mailDao.findWithAdminAndCategory(admin1, mailCategory1, 0, 0);
		List<Mail> mails = page.getPageItems();
		assertEquals(1, mails.size());
		assertEquals(subject2, mails.get(0).getSubject());
		page = mailDao.findWithAdminAndCategory(admin1, null, 0, 0);
		mails = page.getPageItems();
		assertEquals(1, mails.size());
		assertEquals(subject1, mails.get(0).getSubject());
		page = mailDao.findWithAdminAndCategory(null, mailCategory1, 0, 0);
		mails = page.getPageItems();
		assertEquals(1, mails.size());
		assertEquals(subject0, mails.get(0).getSubject());
	}

	@Test
	public void testFindWithPatternLike() {
		admin1 = adminDao.makePersistent(admin1);
		mail0.setAdmin(admin1);
		mail1.setAdmin(admin1);
		mailCategory1 = mailCategoryDao.makePersistent(mailCategory1);
		mail0.setMailCategory(mailCategory1);
		mail2.setMailCategory(mailCategory1);
		Page<Mail> page = mailDao.findWithPatternLike("history", 0, 0);
		List<Mail> mails = page.getPageItems();
		assertEquals(1, mails.size());
		assertEquals(subject1, mails.get(0).getSubject());
		page = mailDao.findWithPatternLike("steph", 0, 0);
		mails = page.getPageItems();
		assertEquals(2, mails.size());
		assertEquals(subject1, mails.get(0).getSubject());
		assertEquals(subject0, mails.get(1).getSubject());
		page = mailDao.findWithPatternLike("arket", 0, 0);
		mails = page.getPageItems();
		assertEquals(2, mails.size());
		assertEquals(subject0, mails.get(0).getSubject());
		assertEquals(subject2, mails.get(1).getSubject());
	}

	@Test
	public void testFindBodyLikeImage() {
		Page<Mail> page = mailDao.findWithBodyLikeImage(image, 0, 0);
		List<Mail> mails = page.getPageItems();
		assertEquals(1, mails.size());
		assertEquals(bodyWithImage, mails.get(0).getBody());
	}

	@Test
	public void testFindAttachmentsLikeFile() {
		Page<Mail> page = mailDao.findWithAttachmentsLikeFile(attachedFile, 0, 0);
		List<Mail> mails = page.getPageItems();
		assertEquals(1, mails.size());
		assertEquals(subject1, mails.get(0).getSubject());
	}

	@Test
	public void testDeleteByDate() {
		mailDao.flush();
		LocalDateTime sinceDate = new LocalDateTime().plusDays(1);
		assertEquals(2, mailDao.deleteByDate(sinceDate));
		Page<Mail> page = mailDao.findAll(0, 0);
		List<Mail> mails = page.getPageItems();
		assertEquals(1, mails.size());
	}

	@Test
	public void testPagination() {
		mail3 = mailDao.makePersistent(mail3);
		mail4 = mailDao.makePersistent(mail4);
		mail5 = mailDao.makePersistent(mail5);
		admin1 = adminDao.makePersistent(admin1);
		admin2 = adminDao.makePersistent(admin2);
		mail0.setAdmin(admin1);
		mail1.setAdmin(admin1);
		mail2.setAdmin(admin1);
		mail3.setAdmin(admin2);
		mail4.setAdmin(admin2);
		mail5.setAdmin(admin2);
		mailCategory1 = mailCategoryDao.makePersistent(mailCategory1);
		mailCategory2 = mailCategoryDao.makePersistent(mailCategory2);
		mail0.setMailCategory(mailCategory1);
		mail1.setMailCategory(mailCategory1);
		mail2.setMailCategory(mailCategory1);
		mail3.setMailCategory(mailCategory2);
		mail4.setMailCategory(mailCategory2);
		mail5.setMailCategory(mailCategory2);
		Page<Mail> page = mailDao.findWithPatternLike("steph", 1, 2);
		assert (page.isFirstPage());
		assertEquals(2, page.getPageSize());
		assertEquals(3, page.getNumberOfPages());
		assertEquals(2, page.getNextPageNumber());
		assertEquals(6, page.getTotalSize());
		page = mailDao.findWithPatternLike("history", 1, 2);
        assertEquals(page.isFirstPage(), true);
		assertEquals(page.isLastPage(), true);
		assertEquals(2, page.getPageSize());
		assertEquals(1, page.getNumberOfPages());
		assertEquals(1, page.getNextPageNumber());
		assertEquals(2, page.getTotalSize());
		page = mailDao.findWithPatternLike("history", 3, 2);
		assertEquals(page.isLastPage(), true);
		assertEquals(2, page.getPageSize());
		assertEquals(1, page.getNumberOfPages());
		assertEquals(1, page.getPreviousPageNumber());
		assertEquals(2, page.getTotalSize());
	}

}
