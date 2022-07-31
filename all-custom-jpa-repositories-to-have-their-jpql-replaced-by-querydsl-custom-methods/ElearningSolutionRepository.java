package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.ElearningAnswer;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningQuestion;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningSolution;

public interface ElearningSolutionRepository extends GenericRepository<ElearningSolution, Long> {

    @Query("SELECT es FROM ElearningSolution es, ElearningAnswer ea WHERE es.elearningAnswer.id = ea.id AND es.elearningQuestion = :elearningQuestion ORDER BY ea.listOrder")
    public List<ElearningSolution> findByQuestion(@Param("elearningQuestion") ElearningQuestion elearningQuestion);

    @Query("SELECT es FROM ElearningSolution es WHERE es.elearningAnswer = :elearningAnswer")
    public List<ElearningSolution> findByAnswer(@Param("elearningAnswer") ElearningAnswer elearningAnswer);

    @Query("SELECT es FROM ElearningSolution es, ElearningAnswer ea WHERE es.elearningAnswer.id = ea.id AND es.elearningQuestion = :elearningQuestion AND es.elearningAnswer = :elearningAnswer ORDER BY ea.listOrder")
    public List<ElearningSolution> findByQuestionAndAnswer(@Param("elearningQuestion") ElearningQuestion elearningQuestion, @Param("elearningAnswer") ElearningAnswer elearningAnswer);

}
