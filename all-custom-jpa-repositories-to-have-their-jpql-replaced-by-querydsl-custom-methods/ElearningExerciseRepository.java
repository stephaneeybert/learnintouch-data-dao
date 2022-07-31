package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.dao.domain.ElearningCourse;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningCategory;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningExercise;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningLevel;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningScoring;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningSubject;

public interface ElearningExerciseRepository extends GenericRepository<ElearningExercise, Long> {

    @Query("SELECT ee FROM ElearningExercise ee ORDER BY ee.name")
    public Page<ElearningExercise> findThemAll(Pageable page);

    @Query("SELECT ee FROM ElearningExercise ee WHERE ee.name = :name")
    public ElearningExercise findByName(@Param("name") String name);
    
    public List<ElearningExercise> findByImage(String image);

    @Query("SELECT ee FROM ElearningExercise ee WHERE ee.introduction LIKE CONCAT('%', :image, '%')")
    public List<ElearningExercise> findByIntroductionLikeImage(@Param("image") String image);

    public List<ElearningExercise> findByAudio(String audio);

    @Query("SELECT ee FROM ElearningExercise ee WHERE ee.garbage = '1' ORDER BY ee.name")
    public Page<ElearningExercise> findGarbage(Pageable page);
    
    @Query("SELECT ee FROM ElearningExercise ee WHERE ee.garbage != '1' ORDER BY ee.name")
    public Page<ElearningExercise> findNonGarbage(Pageable page);
    
    @Query("SELECT ee FROM ElearningExercise ee WHERE ee.garbage != '1' AND (ee.elearningScoring = :elearningScoring OR (ee.elearningScoring IS NULL AND :elearningScoring < '1')) ORDER BY ee.name")
    public Page<ElearningExercise> findByScoring(@Param("elearningScoring") ElearningScoring elearningScoring, Pageable page);
    
    @Query("SELECT ee FROM ElearningExercise ee WHERE ee.garbage != '1' AND LOWER(ee.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ee.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR ee.id = :searchTerm ORDER BY ee.name")
    public Page<ElearningExercise> search(@Param("searchTerm") String searchTerm, Pageable page);

    @Query("SELECT ee FROM ElearningExercise ee, ElearningCourseItem eci, ElearningCourse ec WHERE (eci.elearningExercise.id = ee.id OR eci.elearningExercise IS NULL) AND (eci.elearningCourse.id = ec.id OR eci.elearningCourse IS NULL) AND ee.garbage != '1' AND LOWER(ee.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ee.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR ee.id = :searchTerm OR LOWER(ec.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ec.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY ee.name")
    public Page<ElearningExercise> searchExerciseAndCourse(@Param("searchTerm") String searchTerm, Pageable page);
    
    @Query("SELECT ee FROM ElearningExercise ee WHERE ee.garbage != '1' AND ee.publicAccess = '1' AND ((:sinceDate >= :systemDate AND ee.releaseDate > :sinceDate) OR (:sinceDate < :systemDate AND ee.releaseDate <= :systemDate AND ee.releaseDate >= :sinceDate)) ORDER BY ee.name")
    public Page<ElearningExercise> findByPublicAccessAndReleaseDate(@Param("sinceDate") String sinceDate, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT ee FROM ElearningExercise ee WHERE ee.garbage != '1' AND ee.publicAccess = '1' ORDER BY ee.name")
    public Page<ElearningExercise> findByPublicAccess(Pageable page);

    @Query("SELECT ee FROM ElearningExercise ee WHERE ee.garbage != '1' AND ee.publicAccess != '1' AND ((:sinceDate >= :systemDate AND ee.releaseDate > :sinceDate) OR (:sinceDate < :systemDate AND ee.releaseDate <= :systemDate AND ee.releaseDate >= :sinceDate)) ORDER BY ee.name")
    public Page<ElearningExercise> findByProtectedAccessAndReleaseDate(@Param("sinceDate") String sinceDate, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT ee FROM ElearningExercise ee WHERE ee.garbage != '1' AND ee.publicAccess != '1' ORDER BY ee.name")
    public Page<ElearningExercise> findByProtectedAccess(Pageable page);

    @Query("SELECT ee FROM ElearningExercise ee, ElearningCourseItem eci WHERE (eci.elearningExercise.id = ee.id OR eci.elearningExercise IS NULL) AND ee.garbage != '1' AND eci.elearningCourse = :elearningCourse AND ((:sinceDate >= :systemDate AND ee.releaseDate > :sinceDate) OR (:sinceDate < :systemDate AND ee.releaseDate <= :systemDate AND ee.releaseDate >= :sinceDate)) ORDER BY eci.listOrder, ee.name")
    public Page<ElearningExercise> findByCourseAndReleaseDate(@Param("elearningCourse") ElearningCourse elearningCourse, @Param("sinceDate") String sinceDate, @Param("systemDate") String systemDate, Pageable page);
    
    @Query("SELECT ee FROM ElearningExercise ee, ElearningCourseItem eci WHERE (eci.elearningExercise.id = ee.id OR eci.elearningExercise IS NULL) AND ee.garbage != '1' AND eci.elearningCourse = :elearningCourse ORDER BY eci.listOrder, ee.name")
    public Page<ElearningExercise> findByCourse(@Param("elearningCourse") ElearningCourse elearningCourse, Pageable page);

    @Query("SELECT ee FROM ElearningExercise ee WHERE ee.garbage != '1' AND ((:sinceDate >= :systemDate AND ee.releaseDate > :sinceDate) OR (:sinceDate < :systemDate AND ee.releaseDate <= :systemDate AND ee.releaseDate >= :sinceDate)) ORDER BY ee.name")
    public Page<ElearningExercise> findByReleaseDate(@Param("sinceDate") String sinceDate, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT ee FROM ElearningExercise ee WHERE ee.garbage != '1' AND (ee.elearningCategory = :elearningCategory OR (ee.elearningCategory IS NULL AND :elearningCategory < '1')) AND ((:sinceDate >= :systemDate AND ee.releaseDate > :sinceDate) OR (:sinceDate < :systemDate AND ee.releaseDate <= :systemDate AND ee.releaseDate >= :sinceDate)) ORDER BY ee.name")
    public Page<ElearningExercise> findByCategoryAndReleaseDate(@Param("elearningCategory") ElearningCategory elearningCategory, @Param("sinceDate") String sinceDate, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT ee FROM ElearningExercise ee WHERE ee.garbage != '1' AND (ee.elearningCategory = :elearningCategory OR (ee.elearningCategory IS NULL AND :elearningCategory < '1')) ORDER BY ee.name")
    public Page<ElearningExercise> findByCategory(@Param("elearningCategory") ElearningCategory elearningCategory, Pageable page);

    @Query("SELECT ee FROM ElearningExercise ee WHERE ee.garbage != '1' AND (ee.elearningLevel = :elearningLevel OR (ee.elearningLevel IS NULL AND :elearningLevel < '1')) AND ((:sinceDate >= :systemDate AND ee.releaseDate > :sinceDate) OR (:sinceDate < :systemDate AND ee.releaseDate <= :systemDate AND ee.releaseDate >= :sinceDate)) ORDER BY ee.name")
    public Page<ElearningExercise> findByLevelAndReleaseDate(@Param("elearningLevel") ElearningLevel elearningLevel, @Param("sinceDate") String sinceDate, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT ee FROM ElearningExercise ee WHERE ee.garbage != '1' AND (ee.elearningLevel = :elearningLevel OR (ee.elearningLevel IS NULL AND :elearningLevel < '1')) ORDER BY ee.name")
    public Page<ElearningExercise> findByLevel(@Param("elearningLevel") ElearningLevel elearningLevel, Pageable page);

    @Query("SELECT ee FROM ElearningExercise ee WHERE ee.garbage != '1' AND (ee.elearningSubject = :elearningSubject OR (ee.elearningSubject IS NULL AND :elearningSubject < '1')) AND ((:sinceDate >= :systemDate AND ee.releaseDate > :sinceDate) OR (:sinceDate < :systemDate AND ee.releaseDate <= :systemDate AND ee.releaseDate >= :sinceDate)) ORDER BY ee.name")
    public Page<ElearningExercise> findBySubjectAndReleaseDate(@Param("elearningSubject") ElearningSubject elearningSubject, @Param("sinceDate") String sinceDate, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT ee FROM ElearningExercise ee WHERE ee.garbage != '1' AND (ee.elearningSubject = :elearningSubject OR (ee.elearningSubject IS NULL AND :elearningSubject < '1')) ORDER BY ee.name")
    public Page<ElearningExercise> findBySubject(@Param("elearningSubject") ElearningSubject elearningSubject, Pageable page);

    @Query("SELECT ee FROM ElearningExercise ee WHERE ee.garbage != '1' AND (ee.elearningCategory = :elearningCategory OR (ee.elearningCategory IS NULL AND :elearningCategory < '1')) AND (ee.elearningLevel = :elearningLevel OR (ee.elearningLevel IS NULL AND :elearningLevel < '1')) AND ((:sinceDate >= :systemDate AND ee.releaseDate > :sinceDate) OR (:sinceDate < :systemDate AND ee.releaseDate <= :systemDate AND ee.releaseDate >= :sinceDate)) ORDER BY ee.name")
    public Page<ElearningExercise> findByCategoryAndLevelAndReleaseDate(@Param("elearningCategory") ElearningCategory elearningCategory, @Param("elearningLevel") ElearningLevel elearningLevel, @Param("sinceDate") String sinceDate, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT ee FROM ElearningExercise ee WHERE ee.garbage != '1' AND (ee.elearningCategory = :elearningCategory OR (ee.elearningCategory IS NULL AND :elearningCategory < '1')) AND (ee.elearningLevel = :elearningLevel OR (ee.elearningLevel IS NULL AND :elearningLevel < '1')) ORDER BY ee.name")
    public Page<ElearningExercise> findByCategoryAndLevel(@Param("elearningCategory") ElearningCategory elearningCategory, @Param("elearningLevel") ElearningLevel elearningLevel, Pageable page);

    @Query("SELECT ee FROM ElearningExercise ee WHERE ee.garbage != '1' AND (ee.elearningCategory = :elearningCategory OR (ee.elearningCategory IS NULL AND :elearningCategory < '1')) AND (ee.elearningSubject = :elearningSubject OR (ee.elearningSubject IS NULL AND :elearningSubject < '1')) AND ((:sinceDate >= :systemDate AND ee.releaseDate > :sinceDate) OR (:sinceDate < :systemDate AND ee.releaseDate <= :systemDate AND ee.releaseDate >= :sinceDate)) ORDER BY ee.name")
    public Page<ElearningExercise> findByCategoryAndSubjectAndReleaseDate(@Param("elearningCategory") ElearningCategory elearningCategory, @Param("elearningSubject") ElearningSubject elearningSubject, @Param("sinceDate") String sinceDate, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT ee FROM ElearningExercise ee WHERE ee.garbage != '1' AND (ee.elearningCategory = :elearningCategory OR (ee.elearningCategory IS NULL AND :elearningCategory < '1')) AND (ee.elearningSubject = :elearningSubject OR (ee.elearningSubject IS NULL AND :elearningSubject < '1')) ORDER BY ee.name")
    public Page<ElearningExercise> findByCategoryAndSubjectAndReleaseDate(@Param("elearningCategory") ElearningCategory elearningCategory, @Param("elearningSubject") ElearningSubject elearningSubject, Pageable page);

    @Query("SELECT ee FROM ElearningExercise ee WHERE ee.garbage != '1' AND (ee.elearningLevel = :elearningLevel OR (ee.elearningLevel IS NULL AND :elearningLevel < '1')) AND (ee.elearningSubject = :elearningSubject OR (ee.elearningSubject IS NULL AND :elearningSubject < '1')) AND ((:sinceDate >= :systemDate AND ee.releaseDate > :sinceDate) OR (:sinceDate < :systemDate AND ee.releaseDate <= :systemDate AND ee.releaseDate >= :sinceDate)) ORDER BY ee.name")
    public Page<ElearningExercise> findByLevelAndSubjectAndReleaseDate(@Param("elearningLevel") ElearningLevel elearningLevel, @Param("elearningSubject") ElearningSubject elearningSubject, @Param("sinceDate") String sinceDate, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT ee FROM ElearningExercise ee WHERE ee.garbage != '1' AND (ee.elearningLevel = :elearningLevel OR (ee.elearningLevel IS NULL AND :elearningLevel < '1')) AND (ee.elearningSubject = :elearningSubject OR (ee.elearningSubject IS NULL AND :elearningSubject < '1')) ORDER BY ee.name")
    public Page<ElearningExercise> findByLevelAndSubject(@Param("elearningLevel") ElearningLevel elearningLevel, @Param("elearningSubject") ElearningSubject elearningSubject, Pageable page);

    @Query("SELECT ee FROM ElearningExercise ee WHERE ee.garbage != '1' AND (ee.elearningCategory = :elearningCategory OR (ee.elearningCategory IS NULL AND :elearningCategory < '1')) AND (ee.elearningLevel = :elearningLevel OR (ee.elearningLevel IS NULL AND :elearningLevel < '1')) AND (ee.elearningSubject = :elearningSubject OR (ee.elearningSubject IS NULL AND :elearningSubject < '1')) AND ((:sinceDate >= :systemDate AND ee.releaseDate > :sinceDate) OR (:sinceDate < :systemDate AND ee.releaseDate <= :systemDate AND ee.releaseDate >= :sinceDate)) ORDER BY ee.name")
    public Page<ElearningExercise> findByCategoryAndLevelAndSubjectAndReleaseDate(@Param("elearningCategory") ElearningCategory elearningCategory, @Param("elearningLevel") ElearningLevel elearningLevel, @Param("elearningSubject") ElearningSubject elearningSubject, @Param("sinceDate") String sinceDate, @Param("systemDate") String systemDate, Pageable page);

    @Query("SELECT ee FROM ElearningExercise ee WHERE ee.garbage != '1' AND (ee.elearningCategory = :elearningCategory OR (ee.elearningCategory IS NULL AND :elearningCategory < '1')) AND (ee.elearningLevel = :elearningLevel OR (ee.elearningLevel IS NULL AND :elearningLevel < '1')) AND (ee.elearningSubject = :elearningSubject OR (ee.elearningSubject IS NULL AND :elearningSubject < '1')) ORDER BY ee.name")
    public Page<ElearningExercise> findByCategoryAndLevelAndSubject(@Param("elearningCategory") ElearningCategory elearningCategory, @Param("elearningLevel") ElearningLevel elearningLevel, @Param("elearningSubject") ElearningSubject elearningSubject, Pageable page);

}
