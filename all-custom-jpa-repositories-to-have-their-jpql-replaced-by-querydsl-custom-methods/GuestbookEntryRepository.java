package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.GuestbookEntry;
import com.thalasoft.learnintouch.data.jpa.domain.UserAccount;

public interface GuestbookEntryRepository extends GenericRepository<GuestbookEntry, Long> {

    @Query("SELECT ge FROM GuestbookEntry ge ORDER BY ge.publicationDatetime DESC")
    public List<GuestbookEntry> findThemAll();

    @Query("SELECT ge FROM GuestbookEntry ge WHERE ge.userAccount = :userAccount OR (ge.userAccount IS NULL AND :userAccount < '1')")
    public List<GuestbookEntry> findByUserAccount(@Param("userAccount") UserAccount userAccount);

}
