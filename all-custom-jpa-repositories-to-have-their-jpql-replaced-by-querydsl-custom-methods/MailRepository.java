package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.joda.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.Admin;
import com.thalasoft.learnintouch.data.jpa.domain.Mail;
import com.thalasoft.learnintouch.data.jpa.domain.MailCategory;

public interface MailRepository extends GenericRepository<Mail, Long> {

    @Query("SELECT m FROM Mail m ORDER BY m.subject")
    public Page<Mail> findThemAll(Pageable page);

    @Query("SELECT m FROM Mail m WHERE m.admin = :admin OR (m.admin IS NULL AND :admin < '1') ORDER BY m.subject")
    public Page<Mail> findByAdmin(@Param("admin") Admin admin, Pageable page);

    @Query("SELECT m FROM Mail m WHERE (m.admin = :admin OR (m.admin IS NULL AND :admin < '1')) AND (m.mailCategory = :mailCategory OR (m.mailCategory IS NULL AND :mailCategory < '1')) ORDER BY m.subject")
    public Page<Mail> findByAdminAndCategory(@Param("admin") Admin admin, @Param("mailCategory") MailCategory mailCategory, Pageable page);

    @Query("DELETE FROM Mail m WHERE m.sendDatetime IS NOT NULL AND m.sendDatetime <= :sinceDate")
    public void deleteByDate(@Param("sinceDate") LocalDateTime sinceDate);

    @Query("SELECT m FROM Mail m, Admin a, MailCategory mc WHERE (m.admin.id = a.id OR m.admin IS NULL) AND (m.mailCategory.id = mc.id OR m.mailCategory IS NULL) AND LOWER(m.subject) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(m.body) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(m.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(a.firstname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(a.lastname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(a.login) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(a.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(mc.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY m.subject")
    public Page<Mail> search(@Param("searchTerm") String searchTerm, Pageable page);

    @Query("SELECT m FROM Mail m WHERE m.mailCategory = :mailCategory OR (m.mailCategory IS NULL AND :mailCategory < '1') ORDER BY m.subject")
    public Page<Mail> findByCategory(@Param("mailCategory") MailCategory mailCategory, Pageable page);

    @Query("SELECT m FROM Mail m WHERE m.body LIKE LOWER(CONCAT('%', :image, '%'))")
    public List<Mail> findByBodyLikeImage(@Param("image") String image);

    @Query("SELECT m FROM Mail m WHERE m.attachments LIKE LOWER(CONCAT('%', :file, '%'))")
    public List<Mail> findByAttachmentsLikeFile(@Param("file") String file);

}
