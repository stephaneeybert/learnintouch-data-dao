package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import org.joda.time.LocalDateTime;

import com.thalasoft.learnintouch.data.dao.domain.MailList;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface UserAccountDao extends GenericDao<UserAccount, Serializable> {

	public Page<UserAccount> findAll(int pageNumber, int pageSize);

	public UserAccount findWithEmail(String email);

	public UserAccount findWithEmailAndReadablePassword(String email, String readablePassword);

	public Page<UserAccount> findWithMobilePhone(String mobilePhone, int pageNumber, int pageSize);

	public Page<UserAccount> findWithPatternLike(String pattern, int pageNumber, int pageSize);

	public Page<UserAccount> findMailSubscribersLikePattern(String pattern, int pageNumber, int pageSize);

	public Page<UserAccount> findNotMailSubscribersLikePattern(String pattern, int pageNumber, int pageSize);

	public Page<UserAccount> findMailSubscribersLikeCountry(String country, int pageNumber, int pageSize);

	public Page<UserAccount> findCurrentMailSubscribers(LocalDateTime dateTime, int pageNumber, int pageSize);

	public Page<UserAccount> findExpiredMailSubscribers(LocalDateTime dateTime, int pageNumber, int pageSize);

	public Page<UserAccount> findAllMailSubscribers(int pageNumber, int pageSize);

	public Page<UserAccount> findSmsSubscribersLikePattern(String pattern, int pageNumber, int pageSize);

	public Page<UserAccount> findNotSmsSubscribersLikePattern(String pattern, int pageNumber, int pageSize);

	public Page<UserAccount> findExpiredSmsSubscribers(LocalDateTime dateTime, int pageNumber, int pageSize);

	public Page<UserAccount> findCurrentSmsSubscribers(LocalDateTime dateTime, int pageNumber, int pageSize);

	public Page<UserAccount> findAllSmsSubscribers(int pageNumber, int pageSize);

	public Page<UserAccount> findNotValid(LocalDateTime dateTime, int pageNumber, int pageSize);

	public Page<UserAccount> findValidTemporarily(LocalDateTime dateTime, int pageNumber, int pageSize);

	public Page<UserAccount> findValidPermanently(int pageNumber, int pageSize);

	public long countNotValidPermanently();

	public Page<UserAccount> findNotYetConfirmedEmail(int pageNumber, int pageSize);
	
	public Page<UserAccount> findWithMailList(MailList mailList, int pageNumber, int pageSize);
	
	public List<UserAccount> findWithImage(String image);

	public Page<UserAccount> findImported(int pageNumber, int pageSize);
	
	public long resetImported();
	
	public long countImported();
	
	public Page<UserAccount> findWithCreationDateTime(LocalDateTime fromDateTime, LocalDateTime toDateTime, int pageNumber, int pageSize);

}
