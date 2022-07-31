package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.ElearningClass;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningCourse;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningExercise;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningResult;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningSession;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningSubscription;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningTeacher;
import com.thalasoft.learnintouch.data.jpa.domain.UserAccount;

public interface ElearningResultRepository extends GenericRepository<ElearningResult, Long> {

    @Query("SELECT er FROM ElearningResult er WHERE LOWER(er.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(er.firstname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(er.lastname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(er.message) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(er.textComment) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR er.id = :searchTerm ORDER BY er.exerciseDatetime DESC, er.firstname, er.lastname")
    public Page<ElearningResult> search(@Param("searchTerm") String searchTerm, Pageable page);
    
    @Query("SELECT er FROM ElearningResult er WHERE er.exerciseDatetime >= :sinceDate ORDER BY er.exerciseDatetime DESC, er.firstname, er.lastname")
    public Page<ElearningResult> findByExercise(@Param("sinceDate") String sinceDate, Pageable page);

    @Query("SELECT er FROM ElearningResult er, ElearningSubscription es, UserAccount ua WHERE er.elearningSubscription = es.id AND es.userAccount.id = ua.id AND er.elearningSubscription = :elearningSubscription AND es.elearningCourse = :elearningCourse ORDER BY er.exerciseDatetime DESC, ua.lastname, ua.firstname")
    public Page<ElearningResult> findBySubscriptionAndCourse(@Param("elearningSubscription") ElearningSubscription elearningSubscription, @Param("elearningCourse") ElearningCourse elearningCourse, Pageable page);

    @Query("SELECT er FROM ElearningResult er, ElearningSubscription es, UserAccount ua WHERE er.elearningSubscription = es.id AND es.userAccount.id = ua.id AND (er.elearningSubscription = :elearningSubscription OR (er.elearningSubscription IS NULL AND :elearningSubscription < '1')) ORDER BY er.exerciseDatetime DESC, ua.lastname, ua.firstname")
    public Page<ElearningResult> findBySubscription(@Param("elearningSubscription") ElearningSubscription elearningSubscription, Pageable page);

    @Query("SELECT er FROM ElearningResult er WHERE er.elearningSubscription IS NULL ORDER BY er.exerciseDatetime DESC, er.firstname, er.lastname")
    public Page<ElearningResult> findNonSubscription(Pageable page);

    @Query("SELECT er FROM ElearningResult er, ElearningSubscription es WHERE er.elearningSubscription = es.id AND es.userAccount = :userAccount ORDER BY er.exerciseDatetime DESC")
    public Page<ElearningResult> findByUserAccount(@Param("userAccount") UserAccount userAccount, Pageable page);

    @Query("SELECT er FROM ElearningResult er, ElearningSubscription es, ElearningCourse ec, UserAccount ua WHERE er.elearningSubscription = es.id AND es.elearningCourse.id = ec.id AND es.userAccount.id = ua.id AND es.elearningSession IS NULL ORDER BY ec.name, er.exerciseDatetime DESC, ua.lastname, ua.firstname")
    public Page<ElearningResult> findByNoSession(Pageable page);

    @Query("SELECT er FROM ElearningResult er, ElearningSubscription es, ElearningCourse ec, UserAccount ua WHERE er.elearningSubscription = es.id AND es.elearningCourse.id = ec.id AND es.elearningSession = :elearningSession ORDER BY ec.name, er.exerciseDatetime DESC, ua.lastname, ua.firstname")
    public Page<ElearningResult> findBySession(@Param("elearningSession") ElearningSession elearningSession, Pageable page);

    @Query("SELECT er FROM ElearningResult er, ElearningSubscription es, UserAccount ua WHERE er.elearningSubscription = es.id AND es.userAccount.id = ua.id AND es.elearningSession = :elearningSession AND es.elearningCourse = :elearningCourse ORDER BY er.exerciseDatetime DESC, ua.lastname, ua.firstname")
    public Page<ElearningResult> findBySessionAndCourse(@Param("elearningSession") ElearningSession elearningSession, @Param("elearningCourse") ElearningCourse elearningCourse, Pageable page);

    @Query("SELECT er FROM ElearningResult er, ElearningSubscription es, UserAccount ua WHERE er.elearningSubscription = es.id AND es.userAccount.id = ua.id AND es.elearningSession = :elearningSession AND es.elearningCourse = :elearningCourse AND er.elearningExercise = :elearningExercise ORDER BY er.exerciseDatetime DESC, ua.lastname, ua.firstname")
    public Page<ElearningResult> findBySessionAndCourseAndExercise(@Param("elearningSession") ElearningSession elearningSession, @Param("elearningCourse") ElearningCourse elearningCourse, @Param("elearningExercise") ElearningExercise elearningExercise, Pageable page);

    @Query("SELECT er FROM ElearningResult er, ElearningSubscription es, ElearningCourse ec, UserAccount ua WHERE er.elearningSubscription = es.id AND es.elearningCourse.id = ec.id AND es.userAccount.id = ua.id AND es.elearningSession = :elearningSession AND es.elearningClass = :elearningClass ORDER BY er.exerciseDatetime DESC, ec.name, ua.lastname, ua.firstname")
    public Page<ElearningResult> findBySessionAndClass(@Param("elearningSession") ElearningSession elearningSession, @Param("elearningClass") ElearningClass elearningClass, Pageable page);

    @Query("SELECT er FROM ElearningResult er, ElearningSubscription es, UserAccount ua WHERE er.elearningSubscription = es.id AND es.userAccount.id = ua.id AND es.elearningSession = :elearningSession AND es.elearningCourse = :elearningCourse AND es.elearningClass = :elearningClass AND er.elearningExercise = :elearningExercise ORDER BY ua.lastname, ua.firstname")
    public Page<ElearningResult> findBySessionAndCourseAndClassAndExercise(@Param("elearningSession") ElearningSession elearningSession, @Param("elearningCourse") ElearningCourse elearningCourse, @Param("elearningClass") ElearningClass elearningClass, @Param("elearningExercise") ElearningExercise elearningExercise, Pageable page);

    @Query("SELECT er FROM ElearningResult er, ElearningSubscription es, UserAccount ua WHERE er.elearningSubscription = es.id AND es.userAccount.id = ua.id AND es.elearningSession = :elearningSession AND es.elearningCourse = :elearningCourse AND es.elearningClass = :elearningClass AND es.elearningTeacher = :elearningTeacher ORDER BY er.exerciseDatetime DESC, ua.lastname, ua.firstname")
    public Page<ElearningResult> findBySessionAndCourseAndClassAndTeacher(@Param("elearningSession") ElearningSession elearningSession, @Param("elearningCourse") ElearningCourse elearningCourse, @Param("elearningClass") ElearningClass elearningClass, @Param("elearningTeacher") ElearningTeacher elearningTeacher, Pageable page);

    @Query("SELECT er FROM ElearningResult er, ElearningSubscription es, UserAccount ua WHERE er.elearningSubscription = es.id AND es.userAccount.id = ua.id AND es.elearningSession = :elearningSession AND es.elearningClass = :elearningClass AND es.elearningTeacher = :elearningTeacher ORDER BY er.exerciseDatetime DESC, ua.lastname, ua.firstname")
    public Page<ElearningResult> findBySessionAndClassAndTeacher(@Param("elearningSession") ElearningSession elearningSession, @Param("elearningClass") ElearningClass elearningClass, @Param("elearningTeacher") ElearningTeacher elearningTeacher, Pageable page);

    @Query("SELECT er FROM ElearningResult er, ElearningSubscription es, UserAccount ua WHERE er.elearningSubscription = es.id AND es.userAccount.id = ua.id AND es.elearningCourse = :elearningCourse AND es.elearningClass = :elearningClass AND es.elearningTeacher = :elearningTeacher ORDER BY er.exerciseDatetime DESC, ua.lastname, ua.firstname")
    public Page<ElearningResult> findByCourseAndClassAndTeacher(@Param("elearningCourse") ElearningCourse elearningCourse, @Param("elearningClass") ElearningClass elearningClass, @Param("elearningTeacher") ElearningTeacher elearningTeacher, Pageable page);

    @Query("SELECT er FROM ElearningResult er, ElearningSubscription es, ElearningCourse ec, UserAccount ua WHERE er.elearningSubscription = es.id AND es.elearningCourse.id = ec.id AND es.userAccount.id = ua.id AND es.elearningClass = :elearningClass ORDER BY er.exerciseDatetime DESC, ec.name, ua.lastname, ua.firstname")
    public Page<ElearningResult> findByClass(@Param("elearningClass") ElearningClass elearningClass, Pageable page);

    @Query("SELECT er FROM ElearningResult er, ElearningSubscription es, UserAccount ua WHERE er.elearningSubscription = es.id AND es.userAccount.id = ua.id AND es.elearningCourse = :elearningCourse AND es.elearningTeacher = :elearningTeacher ORDER BY er.exerciseDatetime DESC, ua.lastname, ua.firstname")
    public Page<ElearningResult> findByCourseAndTeacher(@Param("elearningCourse") ElearningCourse elearningCourse, @Param("elearningTeacher") ElearningTeacher elearningTeacher, Pageable page);

    @Query("SELECT er FROM ElearningResult er, ElearningSubscription es, UserAccount ua WHERE er.elearningSubscription = es.id AND es.userAccount.id = ua.id AND es.elearningCourse = :elearningCourse AND es.elearningClass = :elearningClass ORDER BY er.exerciseDatetime DESC, ua.lastname, ua.firstname")
    public Page<ElearningResult> findByCourseAndClass(@Param("elearningCourse") ElearningCourse elearningCourse, @Param("elearningClass") ElearningClass elearningClass, Pageable page);

    @Query("SELECT er FROM ElearningResult er, ElearningSubscription es, UserAccount ua WHERE er.elearningSubscription = es.id AND es.userAccount.id = ua.id AND es.elearningSession = :elearningSession AND es.elearningCourse = :elearningCourse AND es.elearningTeacher = :elearningTeacher ORDER BY er.exerciseDatetime DESC, ua.lastname, ua.firstname")
    public Page<ElearningResult> findBySessionAndCourseAndTeacher(@Param("elearningSession") ElearningSession elearningSession, @Param("elearningCourse") ElearningCourse elearningCourse, @Param("elearningTeacher") ElearningTeacher elearningTeacher, Pageable page);

    @Query("SELECT er FROM ElearningResult er, ElearningSubscription es, UserAccount ua WHERE er.elearningSubscription = es.id AND es.userAccount.id = ua.id AND es.elearningSession = :elearningSession AND es.elearningTeacher = :elearningTeacher ORDER BY er.exerciseDatetime DESC, ua.lastname, ua.firstname")
    public Page<ElearningResult> findBySessionAndTeacher(@Param("elearningSession") ElearningSession elearningSession, @Param("elearningTeacher") ElearningTeacher elearningTeacher, Pageable page);

    @Query("SELECT er FROM ElearningResult er, ElearningSubscription es, UserAccount ua WHERE er.elearningSubscription = es.id AND es.userAccount.id = ua.id AND es.elearningSession = :elearningSession AND es.elearningCourse = :elearningCourse AND es.elearningClass = :elearningClass ORDER BY er.exerciseDatetime DESC, ua.lastname, ua.firstname")
    public Page<ElearningResult> findBySessionAndCourseAndClass(@Param("elearningSession") ElearningSession elearningSession, @Param("elearningCourse") ElearningCourse elearningCourse, @Param("elearningClass") ElearningClass elearningClass, Pageable page);

    @Query("SELECT er FROM ElearningResult er, ElearningSubscription es, UserAccount ua WHERE er.elearningSubscription = es.id AND es.userAccount.id = ua.id AND es.elearningCourse = :elearningCourse ORDER BY er.exerciseDatetime DESC, ua.lastname, ua.firstname")
    public Page<ElearningResult> findByCourse(@Param("elearningCourse") ElearningCourse elearningCourse, Pageable page);

    @Query("SELECT er FROM ElearningResult er, ElearningSubscription es, UserAccount ua WHERE er.elearningSubscription = es.id AND es.userAccount.id = ua.id AND es.elearningTeacher = :elearningTeacher ORDER BY er.exerciseDatetime DESC, ua.lastname, ua.firstname")
    public Page<ElearningResult> findByTeacher(@Param("elearningTeacher") ElearningTeacher elearningTeacher, Pageable page);

    @Query("SELECT er FROM ElearningResult er WHERE er.elearningExercise = :elearningExercise ORDER BY er.exerciseDatetime DESC")
    public Page<ElearningResult> findByExercise(@Param("elearningExercise") ElearningExercise elearningExercise, Pageable page);

    @Query("SELECT er FROM ElearningResult er WHERE er.elearningExercise = :elearningExercise AND (er.elearningSubscription = :elearningSubscription OR (er.elearningSubscription IS NULL AND :elearningSubscription < '1')) ORDER BY er.exerciseDatetime DESC LIMIT 1")
    public ElearningResult findBySubscriptionAndExercise(@Param("elearningSubscription") ElearningSubscription elearningSubscription, @Param("elearningExercise") ElearningExercise elearningExercise);

    @Query("SELECT er FROM ElearningResult er WHERE er.email = :email ORDER BY er.exerciseDatetime DESC")
    public Page<ElearningResult> findByEmail(@Param("email") String email, Pageable page);

    @Query("SELECT er FROM ElearningResult er WHERE er.email = :email AND er.elearningExercise = :elearningExercise ORDER BY er.exerciseDatetime DESC")
    public ElearningResult findByEmailAndExercise(@Param("email") String email, @Param("elearningExercise") ElearningExercise elearningExercise);

    @Query("SELECT er FROM ElearningResult er WHERE er.email = :email AND er.elearningExercise = :elearningExercise AND DATE(er.exerciseDatetime) = :exerciseDate ORDER BY er.exerciseDatetime DESC")
    public ElearningResult findByEmailAndExerciseAndExerciseDate(@Param("email") String email, @Param("elearningExercise") ElearningExercise elearningExercise, @Param("exerciseDate") String exerciseDate);

    @Query("SELECT er FROM ElearningResult er WHERE er.exerciseDatetime IS NOT NULL AND DATE(er.exerciseDatetime) <= :sinceDate ORDER BY er.exerciseDatetime DESC")
    public ElearningResult findOld(@Param("sinceDate") String sinceDate);

    @Query("SELECT er FROM ElearningResult er, ElearningSubscription es, UserAccount ua WHERE er.elearningSubscription = es.id AND es.userAccount.id = ua.id AND es.elearningClass = :elearningClass AND er.elearningExercise = :elearningExercise ORDER BY ua.lastname, ua.firstname")
    public ElearningResult findByClassAndExercise(@Param("elearningClass") ElearningClass elearningClass, @Param("elearningExercise") ElearningExercise elearningExercise);

}
