package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.dao.domain.ElearningCourse;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningCourseItem;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningExercise;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningLesson;

public interface ElearningCourseItemRepository extends GenericRepository<ElearningCourseItem, Long> {

    @Query("SELECT eci FROM ElearningCourseItem eci WHERE eci.elearningCourse = :elearningCourse ORDER BY eci.listOrder")
    public Page<ElearningCourseItem> findByCourse(@Param("elearningCourse") ElearningCourse elearningCourse, Pageable page);

    @Query("SELECT eci FROM ElearningCourseItem eci WHERE eci.elearningCourse = :elearningCourse ORDER BY eci.id")
    public List<ElearningCourseItem> findByCourseOrderById(@Param("elearningCourse") ElearningCourse elearningCourse);

    @Query("SELECT eci FROM ElearningCourseItem eci WHERE eci.elearningExercise = :elearningExercise OR (eci.elearningExercise IS NULL AND :elearningExercise < '1') ORDER BY eci.listOrder")
    public Page<ElearningCourseItem> findByExercise(@Param("elearningExercise") ElearningExercise elearningExercise, Pageable page);

    @Query("SELECT eci FROM ElearningCourseItem eci WHERE eci.elearningLesson = :elearningLesson OR (eci.elearningLesson IS NULL AND :elearningLesson < '1') ORDER BY eci.listOrder")
    public Page<ElearningCourseItem> findByLesson(@Param("elearningLesson") ElearningLesson elearningLesson, Pageable page);

    @Query("SELECT eci FROM ElearningCourseItem eci WHERE eci.elearningCourse = :elearningCourse AND (eci.elearningExercise = :elearningExercise OR (eci.elearningExercise IS NULL AND :elearningExercise < '1'))")
    public ElearningCourseItem findByCourseAndExercise(@Param("elearningCourse") ElearningCourse elearningCourse, @Param("elearningExercise") ElearningExercise elearningExercise);
    
    @Query("SELECT eci FROM ElearningCourseItem eci, ElearningLesson el, ElearningLessonParagraph elp WHERE eci.elearningLesson.id = el.id AND el.id = elp.elearningLesson.id AND eci.elearningCourse = :elearningCourse AND (elp.elearningExercise = :elearningExercise OR (elp.elearningExercise IS NULL AND :elearningExercise < '1'))")
    public ElearningCourseItem findByCourseAndLessonExercise(@Param("elearningCourse") ElearningCourse elearningCourse, @Param("elearningExercise") ElearningExercise elearningExercise);

    @Query("SELECT eci FROM ElearningCourseItem eci WHERE eci.elearningCourse = :elearningCourse AND (eci.elearningLesson = :elearningLesson OR (eci.elearningLesson IS NULL AND :elearningLesson < '1'))")
    public ElearningCourseItem findByCourseAndLesson(@Param("elearningCourse") ElearningCourse elearningCourse, @Param("elearningLesson") ElearningLesson elearningLesson);

    @Query("SELECT eci FROM ElearningCourseItem eci WHERE (eci.elearningCourse = :elearningCourse OR (eci.elearningCourse IS NULL AND :elearningCourse < '1')) AND eci.listOrder > :listOrder ORDER BY eci.listOrder ASC LIMIT 1")
    public ElearningCourseItem findByNextListOrder(@Param("elearningCourse") ElearningCourse elearningCourse, @Param("listOrder") int listOrder);

    @Query("SELECT eci FROM ElearningCourseItem eci WHERE (eci.elearningCourse = :elearningCourse OR (eci.elearningCourse IS NULL AND :elearningCourse < '1')) AND eci.listOrder < :listOrder ORDER BY eci.listOrder DESC LIMIT 1")
    public ElearningCourseItem findByPreviousListOrder(@Param("elearningCourse") ElearningCourse elearningCourse, @Param("listOrder") int listOrder);

    @Query("SELECT eci FROM ElearningCourseItem eci WHERE (eci.elearningCourse = :elearningCourse OR (eci.elearningCourse IS NULL AND :elearningCourse < '1')) AND eci.listOrder = :listOrder ORDER BY eci.listOrder DESC")
    public List<ElearningCourseItem> findByListOrder(@Param("elearningCourse") ElearningCourse elearningCourse, @Param("listOrder") int listOrder);
    
    @Query("SELECT COUNT(DISTINCT eci1.id) as count FROM ElearningCourseItem eci1, ElearningCourseItem eci2 WHERE eci1.id != eci2.id AND eci1.elearningCourse.id = eci2.elearningCourse.id AND eci1.listOrder = eci2.listOrder AND eci1.elearningCourse = :elearningCourse")
    public Long countDuplicateListOrders(@Param("elearningCourse") ElearningCourse elearningCourse);

}
