package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import org.joda.time.LocalDateTime;

import com.thalasoft.learnintouch.data.dao.domain.Admin;
import com.thalasoft.learnintouch.data.dao.domain.Mail;
import com.thalasoft.learnintouch.data.dao.domain.MailCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface MailDao extends GenericDao<Mail, Serializable> {

	public Page<Mail> findAll(int pageNumber, int pageSize);

	public Page<Mail> findWithCategory(MailCategory mailCategory, int pageNumber, int pageSize);

	public Page<Mail> findWithAdmin(Admin admin, int pageNumber, int pageSize);

	public Page<Mail> findWithAdminAndCategory(Admin admin, MailCategory mailCategory, int pageNumber, int pageSize);

	public Page<Mail> findWithPatternLike(String pattern, int pageNumber, int pageSize);

	public Page<Mail> findWithBodyLikeImage(String image, int pageNumber, int pageSize);

	public Page<Mail> findWithAttachmentsLikeFile(String image, int pageNumber, int pageSize);

	public long deleteByDate(LocalDateTime sinceDate);

}
