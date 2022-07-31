package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.ElearningTeacher;
import com.thalasoft.learnintouch.data.jpa.domain.UserAccount;

public interface ElearningTeacherRepository extends GenericRepository<ElearningTeacher, Long> {

    @Query("SELECT et FROM ElearningTeacher et, UserAccount ua WHERE et.userAccount.id = ua.id ORDER BY ua.lastname, ua.firstname")
    public Page<ElearningTeacher> findThemAll(Pageable page);

    @Query("SELECT et FROM ElearningTeacher et WHERE et.userAccount = :userAccount")
    public ElearningTeacher findByUserAccount(@Param("userAccount") UserAccount userAccount);

}
