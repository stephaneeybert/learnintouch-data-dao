package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.ElearningCourse;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningSession;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningSessionCourse;

public interface ElearningSessionCourseRepository extends GenericRepository<ElearningSessionCourse, Long> {

    @Query("DELETE FROM ElearningSessionCourse esc WHERE esc.elearningSession = :elearningSession")
    public void deleteBySession(@Param("elearningSession") ElearningSession elearningSession);

    @Query("SELECT esc FROM ElearningSessionCourse esc WHERE esc.elearningSession = :elearningSession")
    public List<ElearningSessionCourse> findBySession(@Param("elearningSession") ElearningSession elearningSession);

    @Query("SELECT esc FROM ElearningSessionCourse esc WHERE esc.elearningCourse = :elearningCourse")
    public List<ElearningSessionCourse> findByCourse(@Param("elearningCourse") ElearningCourse elearningCourse);

    @Query("SELECT esc FROM ElearningSessionCourse esc WHERE esc.elearningSession = :elearningSession AND esc.elearningCourse = :elearningCourse")
    public ElearningSessionCourse findBySessionAndCourse(@Param("elearningSession") ElearningSession elearningSession, @Param("elearningCourse") ElearningCourse elearningCourse);

}
