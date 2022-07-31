package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.ElearningAnswer;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningQuestion;

public interface ElearningAnswerRepository extends GenericRepository<ElearningAnswer, Long> {

    @Query("SELECT ea FROM ElearningAnswer ea ORDER BY ea.listOrder")
    public List<ElearningAnswer> findThemAll();

    @Query("SELECT ea FROM ElearningAnswer ea WHERE ea.elearningQuestion = :elearningQuestion AND answer = :answer")
    public ElearningAnswer findByQuestionAndAnswer(@Param("elearningQuestion") ElearningQuestion elearningQuestion, @Param("answer") String answer);

    @Query("SELECT ea FROM ElearningAnswer ea WHERE ea.elearningQuestion = :elearningQuestion ORDER BY ea.listOrder")
    public List<ElearningAnswer> findByQuestion(@Param("elearningQuestion") ElearningQuestion elearningQuestion);

    @Query("SELECT ea FROM ElearningAnswer ea WHERE ea.elearningQuestion = :elearningQuestion ORDER BY ea.id")
    public List<ElearningAnswer> findByQuestionOrderById(@Param("elearningQuestion") ElearningQuestion elearningQuestion);

    public List<ElearningAnswer> findByImage(String image);

    public List<ElearningAnswer> findByAudio(String audio);
    
    @Query("SELECT ea FROM ElearningAnswer ea WHERE ea.elearningQuestion = :elearningQuestion AND ea.listOrder > :listOrder ORDER BY ea.listOrder ASC LIMIT 1")
    public ElearningAnswer findByNextListOrder(@Param("elearningQuestion") ElearningQuestion elearningQuestion, @Param("listOrder") int listOrder);

    @Query("SELECT ea FROM ElearningAnswer ea WHERE ea.elearningQuestion = :elearningQuestion AND ea.listOrder < :listOrder ORDER BY ea.listOrder DESC LIMIT 1")
    public ElearningAnswer findByPreviousListOrder(@Param("elearningQuestion") ElearningQuestion elearningQuestion, @Param("listOrder") int listOrder);

    @Query("SELECT ea FROM ElearningAnswer ea WHERE ea.elearningQuestion = :elearningQuestion AND ea.listOrder = :listOrder ORDER BY ea.listOrder DESC")
    public Page<ElearningAnswer> findByListOrder(@Param("elearningQuestion") ElearningQuestion elearningQuestion, @Param("listOrder") int listOrder, Pageable page);

    @Query("SELECT COUNT(DISTINCT ea1.id) as count FROM ElearningAnswer ea1, ElearningAnswer ea2 WHERE ea1.id != ea2.id AND ea1.elearningQuestion.id = ea2.elearningQuestion.id AND ea1.listOrder = ea2.listOrder AND ea1.elearningQuestion = :elearningQuestion")
    public Long countDuplicateListOrders(@Param("elearningQuestion") ElearningQuestion elearningQuestion);

}
