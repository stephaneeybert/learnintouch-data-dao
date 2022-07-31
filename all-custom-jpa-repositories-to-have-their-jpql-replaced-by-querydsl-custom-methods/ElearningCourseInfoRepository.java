package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.dao.domain.ElearningCourse;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningCourseInfo;

public interface ElearningCourseInfoRepository extends GenericRepository<ElearningCourseInfo, Long> {

    @Query("SELECT eci FROM ElearningCourseInfo eci WHERE eci.elearningCourse = :elearningCourse ORDER BY eci.listOrder")
    public Page<ElearningCourseInfo> findByCourse(@Param("elearningCourse") ElearningCourse elearningCourse, Pageable page);

    @Query("SELECT eci FROM ElearningCourseInfo eci WHERE eci.elearningCourse = :elearningCourse ORDER BY eci.id")
    public List<ElearningCourseInfo> findByCourseOrderById(@Param("elearningCourse") ElearningCourse elearningCourse);

    @Query("SELECT eci FROM ElearningCourseInfo eci WHERE (eci.elearningCourse = :elearningCourse OR (eci.elearningCourse IS NULL AND :elearningCourse < '1')) AND eci.listOrder > :listOrder ORDER BY eci.listOrder ASC LIMIT 1")
    public ElearningCourseInfo findByNextListOrder(@Param("elearningCourse") ElearningCourse elearningCourse, @Param("listOrder") int listOrder);

    @Query("SELECT eci FROM ElearningCourseInfo eci WHERE (eci.elearningCourse = :elearningCourse OR (eci.elearningCourse IS NULL AND :elearningCourse < '1')) AND eci.listOrder < :listOrder ORDER BY eci.listOrder DESC LIMIT 1")
    public ElearningCourseInfo findByPreviousListOrder(@Param("elearningCourse") ElearningCourse elearningCourse, @Param("listOrder") int listOrder);

    @Query("SELECT eci FROM ElearningCourseInfo eci WHERE (eci.elearningCourse = :elearningCourse OR (eci.elearningCourse IS NULL AND :elearningCourse < '1')) AND eci.listOrder = :listOrder ORDER BY eci.listOrder DESC")
    public List<ElearningCourseInfo> findByListOrder(@Param("elearningCourse") ElearningCourse elearningCourse, @Param("listOrder") int listOrder);
    
    @Query("SELECT COUNT(DISTINCT eci1.id) as count FROM ElearningCourseInfo eci1, ElearningCourseInfo eci2 WHERE eci1.id != eci2.id AND eci1.elearningCourse.id = eci2.elearningCourse.id AND eci1.listOrder = eci2.listOrder AND eci1.elearningCourse = :elearningCourse")
    public Long countDuplicateListOrders(@Param("elearningCourse") ElearningCourse elearningCourse);

}
