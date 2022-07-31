package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.dao.domain.ElearningExercisePage;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningExercise;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningQuestion;

public interface ElearningQuestionRepository extends GenericRepository<ElearningQuestion, Long> {

    @Query("SELECT eq FROM ElearningQuestion eq ORDER BY eq.listOrder")
    public List<ElearningQuestion> findThemAll();

    @Query("SELECT eq FROM ElearningQuestion eq WHERE eq.elearningExercisePage = :elearningExercisePage ORDER BY eq.listOrder")
    public List<ElearningQuestion> findByExercisePage(@Param("elearningExercisePage") ElearningExercisePage elearningExercisePage);

    @Query("SELECT eq FROM ElearningQuestion eq WHERE eq.elearningExercisePage = :elearningExercisePage ORDER BY eq.id")
    public List<ElearningQuestion> findByExercisePageOrderById(@Param("elearningExercisePage") ElearningExercisePage elearningExercisePage);

    @Query("SELECT eq FROM ElearningQuestion eq, ElearningExercisePage eep, ElearningExercise ee WHERE eq.elearningExercisePage.id = eep.id AND eep.elearningExercise.id = ee.id AND ee.id = :elearningExercise ORDER BY eep.listOrder, eq.listOrder")
    public List<ElearningQuestion> findByExercise(@Param("elearningExercise") ElearningExercise elearningExercise);

    public List<ElearningQuestion> findByImage(String image);

    public List<ElearningQuestion> findByAudio(String audio);

    @Query("SELECT eq FROM ElearningQuestion eq WHERE eq.elearningExercisePage = :elearningExercisePage AND eq.listOrder > :listOrder ORDER BY eq.listOrder ASC LIMIT 1")
    public ElearningQuestion findByNextListOrder(@Param("elearningExercisePage") ElearningExercisePage elearningExercisePage, @Param("listOrder") int listOrder);

    @Query("SELECT eq FROM ElearningQuestion eq WHERE eq.elearningExercisePage = :elearningExercisePage AND eq.listOrder < :listOrder ORDER BY eq.listOrder DESC LIMIT 1")
    public ElearningQuestion findByPreviousListOrder(@Param("elearningExercisePage") ElearningExercisePage elearningExercisePage, @Param("listOrder") int listOrder);

    @Query("SELECT eq FROM ElearningQuestion eq WHERE eq.elearningExercisePage = :elearningExercisePage AND eq.listOrder = :listOrder ORDER BY eq.listOrder DESC")
    public List<ElearningQuestion> findByListOrder(@Param("elearningExercisePage") ElearningExercisePage elearningExercisePage, @Param("listOrder") int listOrder);
    
    @Query("SELECT COUNT(DISTINCT eq1.id) as count FROM ElearningQuestion eq1, ElearningQuestion eq2 WHERE eq1.id != eq2.id AND eq1.elearningExercisePage.id = eq2.elearningExercisePage.id AND eq1.listOrder = eq2.listOrder AND eq1.elearningExercisePage = :elearningExercisePage")
    public Long countDuplicateListOrders(@Param("elearningExercisePage") ElearningExercisePage elearningExercisePage);

}
