package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.dao.domain.ElearningCourse;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningExercise;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningExercisePage;

public interface ElearningExercisePageRepository extends GenericRepository<ElearningExercisePage, Long> {

    @Query("SELECT ee FROM ElearningExercisePage ee ORDER BY ee.listOrder")
    public List<ElearningExercisePage> findThemAll();

    @Query("SELECT eep FROM ElearningExercisePage eep WHERE eep.elearningExercise = :elearningExercise ORDER BY eep.listOrder")
    public List<ElearningExercisePage> findByElearningExerciseOrderByListOrder(@Param("elearningExercise") ElearningExercise elearningExercise);

    @Query("SELECT eep FROM ElearningExercisePage eep WHERE eep.elearningExercise = :elearningExercise ORDER BY eep.id")
    public List<ElearningExercisePage> findByElearningExerciseOrderById(@Param("elearningExercise") ElearningExercise elearningExercise);

    public List<ElearningExercisePage> findByImage(String image);
    
    @Query("SELECT eep FROM ElearningExercisePage eep WHERE eep.text LIKE CONCAT('%', :image, '%')")
    public List<ElearningExercisePage> findByTextLikeImage(@Param("image") String image);

    public List<ElearningExercisePage> findByAudio(String audio);
    
    @Query("SELECT eep FROM ElearningExercisePage eep WHERE (eep.elearningExercise = :elearningExercise OR (eep.elearningExercise IS NULL AND :elearningExercise < '1')) AND eep.listOrder > :listOrder ORDER BY eep.listOrder ASC LIMIT 1")
    public ElearningExercisePage findByNextListOrder(@Param("elearningExercise") ElearningCourse elearningExercise, @Param("listOrder") int listOrder);

    @Query("SELECT eep FROM ElearningExercisePage eep WHERE (eep.elearningExercise = :elearningExercise OR (eep.elearningExercise IS NULL AND :elearningExercise < '1')) AND eep.listOrder < :listOrder ORDER BY eep.listOrder DESC LIMIT 1")
    public ElearningExercisePage findByPreviousListOrder(@Param("elearningExercise") ElearningCourse elearningExercise, @Param("listOrder") int listOrder);

    @Query("SELECT eep FROM ElearningExercisePage eep WHERE (eep.elearningExercise = :elearningExercise OR (eep.elearningExercise IS NULL AND :elearningExercise < '1')) AND eep.listOrder = :listOrder ORDER BY eep.listOrder DESC")
    public List<ElearningExercisePage> findByListOrder(@Param("elearningExercise") ElearningCourse elearningExercise, @Param("listOrder") int listOrder);
    
    @Query("SELECT COUNT(DISTINCT eep1.id) as count FROM ElearningExercisePage eep1, ElearningExercisePage eep2 WHERE eep1.id != eep2.id AND eep1.elearningExercise.id = eep2.elearningExercise.id AND eep1.listOrder = eep2.listOrder AND eep1.elearningExercise = :elearningExercise")
    public Long countDuplicateListOrders(@Param("elearningExercise") ElearningCourse elearningExercise);

}
