package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.AdminDao;
import com.thalasoft.learnintouch.data.dao.AdminModuleDao;
import com.thalasoft.learnintouch.data.dao.domain.Admin;
import com.thalasoft.learnintouch.data.dao.domain.AdminModule;

public class AdminModuleDaoTest extends AbstractDaoTest {

	@Autowired
	private AdminModuleDao adminModuleDao;
	
	@Autowired
	private AdminDao adminDao;
	
	private AdminModule adminModule;
	private Admin admin;
	
	protected void setAdminModuleDao(AdminModuleDao adminModuleDao) {
		this.adminModuleDao = adminModuleDao;
	}
	
	protected void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	public AdminModuleDaoTest() {
		admin = new Admin();
		admin.setLogin("stephane");
		admin.setPassword("toto");
        admin.setPasswordSalt("");
		admin.setEmail("mitt@yahoo.se");
		admin.setFirstname("Stephane");
		admin.setLastname("Eybert");
		adminModule = new AdminModule();
		adminModule.setAdmin(admin);
		adminModule.setModule("MODULE_ELEARNING");
	}
	
	@Before
	public void beforeAnyTest() throws Exception {
		admin = adminDao.makePersistent(admin);
		adminModule = adminModuleDao.makePersistent(adminModule);
	}
	
	@Test
	public void testSaveAndRetrieve() {		
		assertNotNull(adminModule.getId());
		AdminModule retrievedAdminModule = adminModuleDao.findWithId(adminModule.getId(), true);
		assertNotNull(retrievedAdminModule.getId());
		assertEquals(adminModule.getModule(), retrievedAdminModule.getModule());
		assertEquals(adminModule.getAdmin().getEmail(), retrievedAdminModule.getAdmin().getEmail());
        assertNotSame(adminModule.hashCode(), 0L);
        assertEquals(adminModule.hashCode(), retrievedAdminModule.hashCode());  
        assertFalse(adminModule.toString().equals(""));
	}
	
	@Test
	public void testFindWithModuleAndAdmin() {
		AdminModule retrievedAdminModule = adminModuleDao.findWithModuleAndAdmin("MODULE_ELEARNING", admin);
        assertEquals("MODULE_ELEARNING", retrievedAdminModule.getModule());
	}

	@Test
	public void testFindWithModule() {
		List<AdminModule> retrievedAdminModules = adminModuleDao.findWithModule("MODULE_ELEARNING");
		assertEquals(1, retrievedAdminModules.size());
		for (AdminModule retrievedAdminModule : retrievedAdminModules) {
			assertEquals("MODULE_ELEARNING", retrievedAdminModule.getModule());			
		}
	}

	@Test
	public void testFindWithAdmin() {
		List<AdminModule> retrievedAdminModules = adminModuleDao.findWithModule("MODULE_ELEARNING");
		assertEquals(1, retrievedAdminModules.size());
		for (AdminModule retrievedAdminModule : retrievedAdminModules) {
			assertEquals("stephane", retrievedAdminModule.getAdmin().getLogin());			
		}
	}

}