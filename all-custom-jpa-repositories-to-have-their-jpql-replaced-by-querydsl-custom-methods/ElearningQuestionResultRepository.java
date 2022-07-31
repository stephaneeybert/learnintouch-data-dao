package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.ElearningAnswer;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningExercisePage;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningQuestion;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningQuestionResult;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningResult;

public interface ElearningQuestionResultRepository extends GenericRepository<ElearningQuestionResult, Long> {

    @Query("SELECT eqr FROM ElearningQuestionResult eqr WHERE eqr.elearningResult = :elearningResult")
    public List<ElearningQuestionResult> findByResult(@Param("elearningResult") ElearningResult elearningResult);

    @Query("SELECT eqr FROM ElearningQuestionResult eqr WHERE eqr.elearningResult = :elearningResult AND eqr.elearningQuestion = :elearningQuestion ORDER BY eqr.elearningAnswerOrder")
    public List<ElearningQuestionResult> findByResultAndQuestion(@Param("elearningResult") ElearningResult elearningResult, @Param("elearningQuestion") ElearningQuestion elearningQuestion);

    @Query("SELECT eqr FROM ElearningQuestionResult eqr WHERE eqr.elearningQuestion = :elearningQuestion")
    public List<ElearningQuestionResult> findByQuestion(@Param("elearningQuestion") ElearningQuestion elearningQuestion);

    @Query("SELECT eqr FROM ElearningQuestionResult eqr WHERE eqr.elearningQuestion = :elearningQuestion AND eqr.elearningAnswer = :elearningAnswer")
    public List<ElearningQuestionResult> findByQuestionAndAnswer(@Param("elearningQuestion") ElearningQuestion elearningQuestion, @Param("elearningAnswer") ElearningAnswer elearningAnswer);

    @Query("SELECT eqr FROM ElearningQuestionResult eqr WHERE eqr.elearningAnswer = :elearningAnswer")
    public List<ElearningQuestionResult> findByAnswer(@Param("elearningAnswer") ElearningAnswer elearningAnswer);

    @Query("SELECT eqr FROM ElearningQuestionResult eqr WHERE eqr.elearningResult = :elearningResult AND eqr.elearningQuestion = :elearningQuestion AND eqr.elearningAnswer = :elearningAnswer")
    public ElearningQuestionResult findByResultAndQuestionAndAnswer(@Param("elearningResult") ElearningResult elearningResult, @Param("elearningQuestion") ElearningQuestion elearningQuestion, @Param("elearningAnswer") ElearningAnswer elearningAnswer);

    @Query("SELECT eqr FROM ElearningQuestionResult eqr, ElearningQuestion eq WHERE eqr.elearningQuestion.id = eq.id AND eqr.elearningResult = :elearningResult AND eq.elearningExercisePage = :elearningExercisePage")
    public List<ElearningQuestionResult> findByResultAndExercisePage(@Param("elearningResult") ElearningResult elearningResult, @Param("elearningExercisePage") ElearningExercisePage elearningExercisePage);

}
