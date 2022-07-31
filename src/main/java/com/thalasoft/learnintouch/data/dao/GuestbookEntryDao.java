package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.GuestbookEntry;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface GuestbookEntryDao extends GenericDao<GuestbookEntry, Serializable> {

	public Page<GuestbookEntry> findAll(int pageNumber, int pageSize);

	public Page<GuestbookEntry> findWithUser(UserAccount userAccount, int pageNumber, int pageSize);

}
