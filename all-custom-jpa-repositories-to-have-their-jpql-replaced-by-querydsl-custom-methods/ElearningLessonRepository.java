package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.dao.domain.ElearningCourse;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningCategory;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningLesson;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningLessonModel;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningLevel;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningSubject;

public interface ElearningLessonRepository extends GenericRepository<ElearningLesson, Long> {

    @Query("SELECT el FROM ElearningLesson el ORDER BY el.name")
    public Page<ElearningLesson> findThemAll(Pageable page);

    public List<ElearningLesson> findByElearningLessonModel(ElearningLessonModel elearningLessonModel);

    @Query("SELECT el FROM ElearningLesson el WHERE el.name = :name")
    public ElearningLesson findByName(@Param("name") String name);
    
    public List<ElearningLesson> findByImage(String image);

    @Query("SELECT el FROM ElearningLesson el WHERE el.introduction LIKE CONCAT('%', :image, '%')")
    public List<ElearningLesson> findByIntroductionLikeImage(@Param("image") String image);

    public List<ElearningLesson> findByAudio(String audio);

    @Query("SELECT el FROM ElearningLesson el WHERE el.garbage = '1' ORDER BY el.name")
    public Page<ElearningLesson> findGarbage(Pageable page);
    
    @Query("SELECT el FROM ElearningLesson el WHERE el.garbage != '1' ORDER BY el.name")
    public Page<ElearningLesson> findNonGarbage(Pageable page);
    
    @Query("SELECT el FROM ElearningLesson el WHERE el.garbage != '1' AND LOWER(el.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(el.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR el.id = :searchTerm ORDER BY el.name")
    public Page<ElearningLesson> search(@Param("searchTerm") String searchTerm, Pageable page);

    @Query("SELECT el FROM ElearningLesson el, ElearningCourseItem eci, ElearningCourse ec WHERE (eci.elearningLesson.id = el.id OR eci.elearningLesson IS NULL) AND (eci.elearningCourse.id = ec.id OR eci.elearningCourse IS NULL) AND el.garbage != '1' AND LOWER(el.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(el.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR el.id = :searchTerm OR LOWER(ec.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ec.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY el.name")
    public Page<ElearningLesson> searchLessonAndCourse(@Param("searchTerm") String searchTerm, Pageable page);
    
    @Query("SELECT el FROM ElearningLesson el WHERE el.garbage != '1' AND el.publicAccess = '1' AND ((:sinceDate >= :systemDate AND el.releaseDate > :sinceDate) OR (:sinceDate < :systemDate AND el.releaseDate <= :systemDate AND el.releaseDate >= :sinceDate)) ORDER BY el.name")
    public Page<ElearningLesson> findByPublicAccessAndReleaseDate(@Param("sinceDate") String sinceDate, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT el FROM ElearningLesson el WHERE el.garbage != '1' AND el.publicAccess = '1' ORDER BY el.name")
    public Page<ElearningLesson> findByPublicAccess(Pageable page);

    @Query("SELECT el FROM ElearningLesson el WHERE el.garbage != '1' AND el.publicAccess != '1' AND ((:sinceDate >= :systemDate AND el.releaseDate > :sinceDate) OR (:sinceDate < :systemDate AND el.releaseDate <= :systemDate AND el.releaseDate >= :sinceDate)) ORDER BY el.name")
    public Page<ElearningLesson> findByProtectedAccessAndReleaseDate(@Param("sinceDate") String sinceDate, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT el FROM ElearningLesson el WHERE el.garbage != '1' AND el.publicAccess != '1' ORDER BY el.name")
    public Page<ElearningLesson> findByProtectedAccess(Pageable page);

    @Query("SELECT el FROM ElearningLesson el, ElearningCourseItem eci WHERE (eci.elearningLesson.id = el.id OR eci.elearningLesson IS NULL) AND el.garbage != '1' AND eci.elearningCourse = :elearningCourse AND ((:sinceDate >= :systemDate AND el.releaseDate > :sinceDate) OR (:sinceDate < :systemDate AND el.releaseDate <= :systemDate AND el.releaseDate >= :sinceDate)) ORDER BY eci.listOrder, el.name")
    public Page<ElearningLesson> findByCourseAndReleaseDate(@Param("elearningCourse") ElearningCourse elearningCourse, @Param("sinceDate") String sinceDate, @Param("systemDate") String systemDate, Pageable page);
    
    @Query("SELECT el FROM ElearningLesson el, ElearningCourseItem eci WHERE (eci.elearningLesson.id = el.id OR eci.elearningLesson IS NULL) AND el.garbage != '1' AND eci.elearningCourse = :elearningCourse ORDER BY eci.listOrder, el.name")
    public Page<ElearningLesson> findByCourse(@Param("elearningCourse") ElearningCourse elearningCourse, Pageable page);

    @Query("SELECT el FROM ElearningLesson el WHERE el.garbage != '1' AND ((:sinceDate >= :systemDate AND el.releaseDate > :sinceDate) OR (:sinceDate < :systemDate AND el.releaseDate <= :systemDate AND el.releaseDate >= :sinceDate)) ORDER BY el.name")
    public Page<ElearningLesson> findByReleaseDate(@Param("sinceDate") String sinceDate, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT el FROM ElearningLesson el WHERE el.garbage != '1' AND (el.elearningCategory = :elearningCategory OR (el.elearningCategory IS NULL AND :elearningCategory < '1')) AND ((:sinceDate >= :systemDate AND el.releaseDate > :sinceDate) OR (:sinceDate < :systemDate AND el.releaseDate <= :systemDate AND el.releaseDate >= :sinceDate)) ORDER BY el.name")
    public Page<ElearningLesson> findByCategoryAndReleaseDate(@Param("elearningCategory") ElearningCategory elearningCategory, @Param("sinceDate") String sinceDate, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT el FROM ElearningLesson el WHERE el.garbage != '1' AND (el.elearningCategory = :elearningCategory OR (el.elearningCategory IS NULL AND :elearningCategory < '1')) ORDER BY el.name")
    public Page<ElearningLesson> findByCategory(@Param("elearningCategory") ElearningCategory elearningCategory, Pageable page);

    @Query("SELECT el FROM ElearningLesson el WHERE el.garbage != '1' AND (el.elearningLevel = :elearningLevel OR (el.elearningLevel IS NULL AND :elearningLevel < '1')) AND ((:sinceDate >= :systemDate AND el.releaseDate > :sinceDate) OR (:sinceDate < :systemDate AND el.releaseDate <= :systemDate AND el.releaseDate >= :sinceDate)) ORDER BY el.name")
    public Page<ElearningLesson> findByLevelAndReleaseDate(@Param("elearningLevel") ElearningLevel elearningLevel, @Param("sinceDate") String sinceDate, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT el FROM ElearningLesson el WHERE el.garbage != '1' AND (el.elearningLevel = :elearningLevel OR (el.elearningLevel IS NULL AND :elearningLevel < '1')) ORDER BY el.name")
    public Page<ElearningLesson> findByLevel(@Param("elearningLevel") ElearningLevel elearningLevel, Pageable page);

    @Query("SELECT el FROM ElearningLesson el WHERE el.garbage != '1' AND (el.elearningSubject = :elearningSubject OR (el.elearningSubject IS NULL AND :elearningSubject < '1')) AND ((:sinceDate >= :systemDate AND el.releaseDate > :sinceDate) OR (:sinceDate < :systemDate AND el.releaseDate <= :systemDate AND el.releaseDate >= :sinceDate)) ORDER BY el.name")
    public Page<ElearningLesson> findBySubjectAndReleaseDate(@Param("elearningSubject") ElearningSubject elearningSubject, @Param("sinceDate") String sinceDate, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT el FROM ElearningLesson el WHERE el.garbage != '1' AND (el.elearningSubject = :elearningSubject OR (el.elearningSubject IS NULL AND :elearningSubject < '1')) ORDER BY el.name")
    public Page<ElearningLesson> findBySubject(@Param("elearningSubject") ElearningSubject elearningSubject, Pageable page);

    @Query("SELECT el FROM ElearningLesson el WHERE el.garbage != '1' AND (el.elearningCategory = :elearningCategory OR (el.elearningCategory IS NULL AND :elearningCategory < '1')) AND (el.elearningLevel = :elearningLevel OR (el.elearningLevel IS NULL AND :elearningLevel < '1')) AND ((:sinceDate >= :systemDate AND el.releaseDate > :sinceDate) OR (:sinceDate < :systemDate AND el.releaseDate <= :systemDate AND el.releaseDate >= :sinceDate)) ORDER BY el.name")
    public Page<ElearningLesson> findByCategoryAndLevelAndReleaseDate(@Param("elearningCategory") ElearningCategory elearningCategory, @Param("elearningLevel") ElearningLevel elearningLevel, @Param("sinceDate") String sinceDate, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT el FROM ElearningLesson el WHERE el.garbage != '1' AND (el.elearningCategory = :elearningCategory OR (el.elearningCategory IS NULL AND :elearningCategory < '1')) AND (el.elearningLevel = :elearningLevel OR (el.elearningLevel IS NULL AND :elearningLevel < '1')) ORDER BY el.name")
    public Page<ElearningLesson> findByCategoryAndLevel(@Param("elearningCategory") ElearningCategory elearningCategory, @Param("elearningLevel") ElearningLevel elearningLevel, Pageable page);

    @Query("SELECT el FROM ElearningLesson el WHERE el.garbage != '1' AND (el.elearningCategory = :elearningCategory OR (el.elearningCategory IS NULL AND :elearningCategory < '1')) AND (el.elearningSubject = :elearningSubject OR (el.elearningSubject IS NULL AND :elearningSubject < '1')) AND ((:sinceDate >= :systemDate AND el.releaseDate > :sinceDate) OR (:sinceDate < :systemDate AND el.releaseDate <= :systemDate AND el.releaseDate >= :sinceDate)) ORDER BY el.name")
    public Page<ElearningLesson> findByCategoryAndSubjectAndReleaseDate(@Param("elearningCategory") ElearningCategory elearningCategory, @Param("elearningSubject") ElearningSubject elearningSubject, @Param("sinceDate") String sinceDate, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT el FROM ElearningLesson el WHERE el.garbage != '1' AND (el.elearningCategory = :elearningCategory OR (el.elearningCategory IS NULL AND :elearningCategory < '1')) AND (el.elearningSubject = :elearningSubject OR (el.elearningSubject IS NULL AND :elearningSubject < '1')) ORDER BY el.name")
    public Page<ElearningLesson> findByCategoryAndSubjectAndReleaseDate(@Param("elearningCategory") ElearningCategory elearningCategory, @Param("elearningSubject") ElearningSubject elearningSubject, Pageable page);

    @Query("SELECT el FROM ElearningLesson el WHERE el.garbage != '1' AND (el.elearningLevel = :elearningLevel OR (el.elearningLevel IS NULL AND :elearningLevel < '1')) AND (el.elearningSubject = :elearningSubject OR (el.elearningSubject IS NULL AND :elearningSubject < '1')) AND ((:sinceDate >= :systemDate AND el.releaseDate > :sinceDate) OR (:sinceDate < :systemDate AND el.releaseDate <= :systemDate AND el.releaseDate >= :sinceDate)) ORDER BY el.name")
    public Page<ElearningLesson> findByLevelAndSubjectAndReleaseDate(@Param("elearningLevel") ElearningLevel elearningLevel, @Param("elearningSubject") ElearningSubject elearningSubject, @Param("sinceDate") String sinceDate, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT el FROM ElearningLesson el WHERE el.garbage != '1' AND (el.elearningLevel = :elearningLevel OR (el.elearningLevel IS NULL AND :elearningLevel < '1')) AND (el.elearningSubject = :elearningSubject OR (el.elearningSubject IS NULL AND :elearningSubject < '1')) ORDER BY el.name")
    public Page<ElearningLesson> findByLevelAndSubject(@Param("elearningLevel") ElearningLevel elearningLevel, @Param("elearningSubject") ElearningSubject elearningSubject, Pageable page);

    @Query("SELECT el FROM ElearningLesson el WHERE el.garbage != '1' AND (el.elearningCategory = :elearningCategory OR (el.elearningCategory IS NULL AND :elearningCategory < '1')) AND (el.elearningLevel = :elearningLevel OR (el.elearningLevel IS NULL AND :elearningLevel < '1')) AND (el.elearningSubject = :elearningSubject OR (el.elearningSubject IS NULL AND :elearningSubject < '1')) AND ((:sinceDate >= :systemDate AND el.releaseDate > :sinceDate) OR (:sinceDate < :systemDate AND el.releaseDate <= :systemDate AND el.releaseDate >= :sinceDate)) ORDER BY el.name")
    public Page<ElearningLesson> findByCategoryAndLevelAndSubjectAndReleaseDate(@Param("elearningCategory") ElearningCategory elearningCategory, @Param("elearningLevel") ElearningLevel elearningLevel, @Param("elearningSubject") ElearningSubject elearningSubject, @Param("sinceDate") String sinceDate, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT el FROM ElearningLesson el WHERE el.garbage != '1' AND (el.elearningCategory = :elearningCategory OR (el.elearningCategory IS NULL AND :elearningCategory < '1')) AND (el.elearningLevel = :elearningLevel OR (el.elearningLevel IS NULL AND :elearningLevel < '1')) AND (el.elearningSubject = :elearningSubject OR (el.elearningSubject IS NULL AND :elearningSubject < '1')) ORDER BY el.name")
    public Page<ElearningLesson> findByCategoryAndLevelAndSubject(@Param("elearningCategory") ElearningCategory elearningCategory, @Param("elearningLevel") ElearningLevel elearningLevel, @Param("elearningSubject") ElearningSubject elearningSubject, Pageable page);

}
