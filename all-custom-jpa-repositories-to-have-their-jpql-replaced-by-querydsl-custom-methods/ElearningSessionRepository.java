package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.ElearningSession;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningTeacher;

public interface ElearningSessionRepository extends GenericRepository<ElearningSession, Long> {

    @Query("SELECT es FROM ElearningSession es ORDER BY es.openingDate DESC")
    public Page<ElearningSession> findThemAll(Pageable page);

    @Query("SELECT es FROM ElearningSession es WHERE es.name = :name ORDER BY es.openingDate DESC")
    public Page<ElearningSession> findByName(@Param("name") String name, Pageable page);
    
    @Query("SELECT es FROM ElearningSession es WHERE es.closed != '1' AND es.openingDate > :systemDate ORDER BY es.openingDate DESC")
    public Page<ElearningSession> findNotYetOpened(@Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT es FROM ElearningSession es WHERE es.closed != '1' AND es.openingDate <= :systemDate AND (es.closingDate >= :systemDate OR es.closingDate IS NULL) ORDER BY es.openingDate DESC")
    public Page<ElearningSession> findCurrentlyOpened(@Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT es FROM ElearningSession es WHERE es.closed = '1' OR (es.closingDate < :systemDate AND es.closingDate IS NOT NULL) ORDER BY es.openingDate DESC")
    public Page<ElearningSession> findClosed(@Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT es FROM ElearningSession es WHERE es.closed != '1' AND ((es.closingDate >= :systemDate AND es.closingDate IS NOT NULL) OR es.closingDate IS NULL) ORDER BY es.openingDate DESC")
    public Page<ElearningSession> findNotClosed(@Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT es FROM ElearningSession es WHERE (LOWER(es.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(es.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR es.id = :searchTerm) AND es.closed != '1' AND ((es.closingDate >= :systemDate AND es.closingDate IS NOT NULL) OR es.closingDate IS NULL) ORDER BY es.openingDate DESC")
    public Page<ElearningSession> searchNotClosed(@Param("searchTerm") String searchTerm, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT es FROM ElearningSession es, ElearningSubscription esu WHERE esu.elearningSession.id = es.id AND esu.elearningTeacher = :elearningTeacher ORDER BY es.openingDate DESC")
    public Page<ElearningSession> findBySubscriptionTeacher(@Param("elearningTeacher") ElearningTeacher elearningTeacher, Pageable page);

}
