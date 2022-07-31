package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.ElearningAnswer;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningAssignment;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningClass;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningExercise;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningResult;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningSession;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningSubscription;

public interface ElearningAssignmentRepository extends GenericRepository<ElearningAnswer, Long> {
    
    @Query("SELECT ea FROM ElearningAssignment ea, ElearningSubscription es, UserAccount ua WHERE ea.elearningSubscription.id = es.id AND es.userAccount.id = ua.id AND ea.elearningExercise = :elearningExercise ORDER BY ea.openingDate DESC")
    public Page<ElearningAssignment> findByExercise(@Param("elearningExercise") ElearningExercise elearningExercise, Pageable page);

    @Query("SELECT ea FROM ElearningAssignment ea WHERE ea.elearningResult = :elearningResult ORDER BY ea.openingDate DESC")
    public List<ElearningAssignment> findByResult(@Param("elearningResult") ElearningResult elearningResult);

    @Query("SELECT ea FROM ElearningAssignment ea WHERE ea.elearningSubscription = :elearningSubscription ORDER BY ea.openingDate DESC")
    public Page<ElearningAssignment> findBySubscription(@Param("elearningSubscription") ElearningSubscription elearningSubscription, Pageable page);

    @Query("SELECT ea FROM ElearningAssignment ea WHERE ea.elearningSubscription = :elearningSubscription AND ea.elearningExercise = :elearningExercise")
    public ElearningAssignment findBySubscriptionAndExercise(@Param("elearningSubscription") ElearningSubscription elearningSubscription, @Param("elearningExercise") ElearningExercise elearningExercise);

    @Query("SELECT ea FROM ElearningAssignment ea WHERE ea.elearningSubscription = :elearningSubscription AND (ea.openingDate IS NULL OR DATE(ea.openingDate) <= :systemDate) AND (ea.closingDate IS NULL OR DATE(ea.closingDate) >= :systemDate) ORDER BY ea.openingDate DESC")
    public Page<ElearningAssignment> findBySubscriptionAndOpened(@Param("elearningSubscription") ElearningSubscription elearningSubscription, @Param("systemDate") String systemDate, Pageable page);

    @Query("DELETE FROM ElearningAssignment ea WHERE ea.elearningSubscription = :elearningSubscription AND (ea.closingDate IS NOT NULL AND DATE(ea.closingDate) < :systemDate)")
    public void deleteBySubscriptionAndClosed(@Param("elearningSubscription") ElearningSubscription elearningSubscription, @Param("systemDate") String systemDate);

    @Query("SELECT ea FROM ElearningAssignment ea WHERE ea.elearningSubscription = :elearningSubscription AND (ea.closingDate IS NOT NULL AND DATE(ea.closingDate) < :systemDate) ORDER BY ea.openingDate DESC")
    public Page<ElearningAssignment> findBySubscriptionAndClosed(@Param("elearningSubscription") ElearningSubscription elearningSubscription, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT ea FROM ElearningAssignment ea WHERE ea.elearningSubscription = :elearningSubscription AND (ea.closingDate IS NULL OR DATE(ea.closingDate) >= :systemDate) ORDER BY ea.openingDate DESC")
    public Page<ElearningAssignment> findBySubscriptionAndNotClosed(@Param("elearningSubscription") ElearningSubscription elearningSubscription, @Param("systemDate") String systemDate, Pageable page);
    
    @Query("SELECT ea FROM ElearningAssignment ea WHERE ea.elearningSubscription = :elearningSubscription AND (ea.openingDate IS NOT NULL AND DATE(ea.openingDate) > :systemDate) ORDER BY ea.openingDate DESC")
    public Page<ElearningAssignment> findBySubscriptionAndDeferred(@Param("elearningSubscription") ElearningSubscription elearningSubscription, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT ea FROM ElearningAssignment ea, ElearningSubscription es, UserAccount ua, ElearningResult er WHERE ea.elearningSubscription.id = es.id AND es.userAccount.id = ua.id AND (ea.elearningResult IS NULL OR ea.elearningResult.id = er.id) AND es.elearningClass = :elearningClass ORDER BY er.exerciseDatetime DESC, er.firstname, er.lastname")
    public Page<ElearningAssignment> findByClass(@Param("elearningClass") ElearningClass elearningClass, Pageable page);

    @Query("SELECT ea FROM ElearningAssignment ea, ElearningSubscription es, UserAccount ua, ElearningResult er WHERE ea.elearningSubscription.id = es.id AND es.userAccount.id = ua.id AND (ea.elearningResult IS NULL OR ea.elearningResult.id = er.id) AND es.elearningClass = :elearningClass AND (ea.openingDate IS NULL OR DATE(ea.openingDate) <= :systemDate) AND (ea.closingDate IS NULL OR DATE(ea.closingDate) >= :systemDate) ORDER BY er.exerciseDatetime DESC, er.firstname, er.lastname")
    public Page<ElearningAssignment> findByClassAndOpened(@Param("elearningClass") ElearningClass elearningClass, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT ea FROM ElearningAssignment ea, ElearningSubscription es, UserAccount ua, ElearningResult er WHERE ea.elearningSubscription.id = es.id AND es.userAccount.id = ua.id AND (ea.elearningResult IS NULL OR ea.elearningResult.id = er.id) AND es.elearningClass = :elearningClass AND (ea.closingDate IS NOT NULL AND DATE(ea.closingDate) < :systemDate) ORDER BY er.exerciseDatetime DESC, er.firstname, er.lastname")
    public Page<ElearningAssignment> findByClassAndClosed(@Param("elearningClass") ElearningClass elearningClass, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT ea FROM ElearningAssignment ea, ElearningSubscription es, UserAccount ua, ElearningResult er WHERE ea.elearningSubscription.id = es.id AND es.userAccount.id = ua.id AND (ea.elearningResult IS NULL OR ea.elearningResult.id = er.id) AND es.elearningClass = :elearningClass AND (ea.closingDate IS NULL OR DATE(ea.closingDate) >= :systemDate) ORDER BY er.exerciseDatetime DESC, er.firstname, er.lastname")
    public Page<ElearningAssignment> findByClassAndNotClosed(@Param("elearningClass") ElearningClass elearningClass, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT ea FROM ElearningAssignment ea, ElearningSubscription es, UserAccount ua, ElearningResult er WHERE ea.elearningSubscription.id = es.id AND es.userAccount.id = ua.id AND (ea.elearningResult IS NULL OR ea.elearningResult.id = er.id) AND es.elearningClass = :elearningClass AND (ea.openingDate IS NOT NULL AND DATE(ea.openingDate) > :systemDate) ORDER BY er.exerciseDatetime DESC, er.firstname, er.lastname")
    public Page<ElearningAssignment> findByClassAndDeferred(@Param("elearningClass") ElearningClass elearningClass, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT ea FROM ElearningAssignment ea, ElearningSubscription es, UserAccount ua, ElearningResult er WHERE ea.elearningSubscription.id = es.id AND es.userAccount.id = ua.id AND (ea.elearningResult IS NULL OR ea.elearningResult.id = er.id) AND (ea.openingDate IS NULL OR DATE(ea.openingDate) <= :systemDate) AND (ea.closingDate IS NULL OR DATE(ea.closingDate) >= :systemDate) ORDER BY er.exerciseDatetime DESC, er.firstname, er.lastname")
    public Page<ElearningAssignment> findOpened(@Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT ea FROM ElearningAssignment ea, ElearningSubscription es, UserAccount ua, ElearningResult er WHERE ea.elearningSubscription.id = es.id AND es.userAccount.id = ua.id AND (ea.elearningResult IS NULL OR ea.elearningResult.id = er.id) AND (ea.openingDate IS NOT NULL AND DATE(ea.openingDate) > :systemDate) ORDER BY er.exerciseDatetime DESC, er.firstname, er.lastname")
    public Page<ElearningAssignment> findDeferred(@Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT ea FROM ElearningAssignment ea, ElearningSubscription es, UserAccount ua, ElearningResult er WHERE ea.elearningSubscription.id = es.id AND es.userAccount.id = ua.id AND (ea.elearningResult IS NULL OR ea.elearningResult.id = er.id) AND (ea.closingDate IS NOT NULL AND DATE(ea.closingDate) < :systemDate) ORDER BY er.exerciseDatetime DESC, er.firstname, er.lastname")
    public Page<ElearningAssignment> findClosed(@Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT ea FROM ElearningAssignment ea, ElearningSubscription es, UserAccount ua, ElearningResult er WHERE ea.elearningSubscription.id = es.id AND es.userAccount.id = ua.id AND (ea.elearningResult IS NULL OR ea.elearningResult.id = er.id) AND (ea.closingDate IS NULL OR DATE(ea.closingDate) >= :systemDate) ORDER BY er.exerciseDatetime DESC, er.firstname, er.lastname")
    public Page<ElearningAssignment> findNotClosed(@Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT ea FROM ElearningAssignment ea, ElearningSubscription esu, ElearningSession es, UserAccount ua, ElearningResult er WHERE ea.elearningSubscription.id = esu.id AND esu.userAccount.id = ua.id AND (ea.elearningResult IS NULL OR ea.elearningResult.id = er.id) AND er.exerciseDatetime IS NOT NULL AND esu.elearningSession = :elearningSession AND ((es.openingDate IS NOT NULL AND es.closingDate IS NOT NULL AND DATE(er.exerciseDatetime) >= DATE(es.openingDate) AND DATE(er.exerciseDatetime) <= DATE(es.closingDate)) OR (es.openingDate IS NOT NULL AND es.closingDate IS NULL AND DATE(er.exerciseDatetime) >= DATE(es.openingDate)) OR (es.openingDate IS NULL AND es.closingDate IS NOT NULL AND DATE(er.exerciseDatetime) <= DATE(es.closingDate))) AND esu.elearningClass = :elearningClass GROUP BY er.id ORDER BY er.exerciseDatetime DESC, er.firstname, er.lastname")
    public Page<ElearningAssignment> findByClassAndResultWithinSession(@Param("elearningClass") ElearningClass elearningClass, @Param("elearningSession") ElearningSession elearningSession, Pageable page);

    @Query("SELECT ea FROM ElearningAssignment ea, ElearningSubscription esu, ElearningSession es, UserAccount ua, ElearningResult er WHERE ea.elearningSubscription.id = esu.id AND esu.userAccount.id = ua.id AND (ea.elearningResult IS NULL OR ea.elearningResult.id = er.id) AND er.exerciseDatetime IS NOT NULL AND esu.elearningSession = :elearningSession AND ((es.openingDate IS NOT NULL AND es.closingDate IS NOT NULL AND DATE(er.exerciseDatetime) >= DATE(es.openingDate) AND DATE(er.exerciseDatetime) <= DATE(es.closingDate)) OR (es.openingDate IS NOT NULL AND es.closingDate IS NULL AND DATE(er.exerciseDatetime) >= DATE(es.openingDate)) OR (es.openingDate IS NULL AND es.closingDate IS NOT NULL AND DATE(er.exerciseDatetime) <= DATE(es.closingDate))) GROUP BY er.id ORDER BY er.exerciseDatetime DESC, er.firstname, er.lastname")
    public Page<ElearningAssignment> findWithinSession(@Param("elearningSession") ElearningSession elearningSession, Pageable page);

    @Query("SELECT ea FROM ElearningAssignment ea, ElearningResult er WHERE (ea.elearningResult IS NULL OR ea.elearningResult.id = er.id) AND ea.elearningSubscription = :elearningSubscription ORDER BY ea.openingDate DESC")
    public Page<ElearningAssignment> findBySubscriptionOrderByResult(@Param("elearningSubscription") ElearningSubscription elearningSubscription, Pageable page);

    @Query("SELECT ea FROM ElearningAssignment ea, ElearningSubscription es, ElearningResult er, UserAccount ua WHERE ea.elearningSubscription.id = es.id AND es.userAccount.id = ua.id AND es.elearningClass = :elearningClass AND (ea.elearningResult IS NULL OR ea.elearningResult.id = er.id) AND er.exerciseDatetime IS NOT NULL AND DATE(er.exerciseDatetime) >= :sinceDate GROUP BY er.id ORDER BY er.exerciseDatetime DESC, er.firstname, er.lastname")
    public Page<ElearningAssignment> findByClassAndResultSinceReleaseDate(@Param("elearningClass") ElearningClass elearningClass, @Param("sinceDate") String sinceDate, Pageable page);

    @Query("SELECT ea FROM ElearningAssignment ea, ElearningSubscription es, ElearningResult er, UserAccount ua WHERE ea.elearningSubscription.id = es.id AND es.userAccount.id = ua.id AND (ea.elearningResult IS NULL OR ea.elearningResult.id = er.id) AND er.exerciseDatetime IS NOT NULL AND DATE(er.exerciseDatetime) >= :sinceDate GROUP BY er.id ORDER BY er.exerciseDatetime DESC, er.firstname, er.lastname")
    public Page<ElearningAssignment> findByResultSinceReleaseDate(@Param("sinceDate") String sinceDate, Pageable page);

}
