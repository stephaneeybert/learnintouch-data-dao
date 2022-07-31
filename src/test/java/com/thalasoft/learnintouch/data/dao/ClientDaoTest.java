  package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.ClientDao;
import com.thalasoft.learnintouch.data.dao.domain.Client;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class ClientDaoTest extends AbstractDaoTest {

	@Autowired
	private ClientDao clientDao;
	
	private Client client0;
	
	private Client client1;

	protected void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}
	
	public ClientDaoTest() {
		client0 = new Client();
		client0.setName("Thalasoft");
		client0.setUrl("www.thalasoft.com");
		client0.setImage("thalasoft.png");
		client0.setListOrder(1);
		client1 = new Client();
		client1.setName("Google");
		client1.setUrl("www.google.com");		
		client1.setImage("google.png");		
        client0.setListOrder(2);
	}
	
	@Before
	public void beforeAnyTest() throws Exception {
		client0 = clientDao.makePersistent(client0);
		client1 = clientDao.makePersistent(client1);
	}
	
	@Test
	public void testSaveAndRetrieve() {		
		assertNotNull(client0.getId());
		client0.setName("LearnInTouch");
		Client loadedClient = clientDao.findWithId(client0.getId(), true);
		assertNotNull(loadedClient.getId());
		assertEquals(client0.getName(), loadedClient.getName());
        assertNotSame(client0.hashCode(), 0L);
        assertEquals(client0.hashCode(), loadedClient.hashCode());  
        assertFalse(client0.toString().equals(""));
	}

	@Test
	public void testFindAll() {
		Page<Client> page = clientDao.findAll(0, 0);
		List<Client> clients = page.getPageItems();
		assertEquals(2, clients.size());
		assertEquals("Google", clients.get(0).getName());
		assertEquals("Thalasoft", clients.get(1).getName());
	}
	
	@Test
	public void testFindWithName() {
		Client client = clientDao.findWithName("Thalasoft");
		assertEquals("Thalasoft", client.getName());
	}
		
	@Test
	public void testFindWithImage() {
		List<Client> clients = clientDao.findWithImage("thalasoft.png");
		assertEquals(1, clients.size());
		assertEquals("thalasoft.png", clients.get(0).getImage());
	}
	
	@Test
	public void testFindFirstPage() {
		Page<Client> page = clientDao.findAll(1, 1);
		List<Client> clients = page.getPageItems();
		assertEquals(1, clients.size());
		assert (page.isFirstPage());
		assertEquals(1, page.getPageSize());
		assertEquals(2, page.getNumberOfPages());
		assertEquals(2, page.getNextPageNumber());
	}

	@Test
	public void testFindLastPage() {
		Page<Client> page = clientDao.findAll(2, 1);
		List<Client> clients = page.getPageItems();
		assertEquals(1, clients.size());
		assert (page.isLastPage());
		assertEquals(1, page.getPageSize());
		assertEquals(2, page.getNumberOfPages());
		assertEquals(1, page.getPreviousPageNumber());
	}
	  
}