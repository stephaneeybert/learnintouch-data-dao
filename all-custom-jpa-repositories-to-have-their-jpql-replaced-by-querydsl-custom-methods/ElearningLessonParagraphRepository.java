package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.dao.domain.ElearningLessonHeading;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningExercise;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningLesson;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningLessonParagraph;

public interface ElearningLessonParagraphRepository extends GenericRepository<ElearningLessonParagraph, Long> {

    public List<ElearningLessonParagraph> findByImage(String image);
    
    @Query("SELECT elp FROM ElearningLessonParagraph elp WHERE elp.body LIKE CONCAT('%', :image, '%')")
    public List<ElearningLessonParagraph> findByBodyLikeImage(@Param("image") String image);

    public List<ElearningLessonParagraph> findByAudio(String audio);

    @Query("SELECT DISTINCT elp FROM ElearningLessonParagraph elp, ElearningLessonHeading elh WHERE (elp.elearningLessonHeading.id = elh.id OR elp.elearningLessonHeading IS NULL) AND elp.elearningLesson = :elearningLesson ORDER BY elh.listOrder, elp.listOrder")
    public List<ElearningLessonParagraph> findByLesson(@Param("elearningLesson") ElearningLesson elearningLesson);

    @Query("SELECT DISTINCT elp FROM ElearningLessonParagraph elp, ElearningLessonHeading elh WHERE (elp.elearningLessonHeading.id = elh.id OR elp.elearningLessonHeading IS NULL) AND elp.elearningExercise = :elearningExercise AND elp.elearningLessonHeading IS NULL ORDER BY elh.listOrder, elp.listOrder")
    public List<ElearningLessonParagraph> findByExercise(@Param("elearningExercise") ElearningExercise elearningExercise);
    
    @Query("SELECT DISTINCT elp FROM ElearningLessonParagraph elp, ElearningLessonHeading elh WHERE (elp.elearningLessonHeading.id = elh.id OR elp.elearningLessonHeading IS NULL) AND elp.elearningLesson != :elearningLesson AND elp.elearningExercise = :elearningExercise AND elp.elearningLessonHeading IS NULL ORDER BY elh.listOrder, elp.listOrder")
    public List<ElearningLessonParagraph> findByOtherLessonExercise(@Param("elearningLesson") ElearningLesson elearningLesson, @Param("elearningExercise") ElearningExercise elearningExercise);
    
    @Query("SELECT DISTINCT elp FROM ElearningLessonParagraph elp, ElearningLessonHeading elh WHERE (elp.elearningLessonHeading.id = elh.id OR elp.elearningLessonHeading IS NULL) AND elp.elearningLesson = :elearningLesson AND (elp.elearningExercise = :elearningExercise OR (elp.elearningExercise IS NULL AND :elearningExercise < '1')) AND elp.elearningLessonHeading IS NULL ORDER BY elh.listOrder, elp.listOrder")
    public List<ElearningLessonParagraph> findByLessonAndExercise(@Param("elearningLesson") ElearningLesson elearningLesson, @Param("elearningExercise") ElearningExercise elearningExercise);
    
    @Query("SELECT elp FROM ElearningLessonParagraph elp, ElearningLessonHeading elh WHERE elp.elearningLessonHeading.id = elh.id AND elp.elearningLessonHeading = :elearningLessonHeading ORDER BY elh.listOrder, elp.listOrder")
    public List<ElearningLessonParagraph> findByLessonHeading(@Param("elearningLessonHeading") ElearningLessonHeading elearningLessonHeading);
    
    @Query("SELECT DISTINCT elp FROM ElearningLessonParagraph elp, ElearningLessonHeading elh WHERE (elp.elearningLessonHeading.id = elh.id OR elp.elearningLessonHeading IS NULL) AND elp.elearningLesson = :elearningLesson AND (elp.elearningLessonHeading = :elearningLessonHeading OR (elp.elearningLessonHeading IS NULL AND :elearningLessonHeading < '1')) ORDER BY elh.listOrder, elp.listOrder")
    public List<ElearningLessonParagraph> findByLessonAndLessonHeading(@Param("elearningLesson") ElearningLesson elearningLesson, @Param("elearningLessonHeading") ElearningLessonHeading elearningLessonHeading);
    
    @Query("SELECT elp FROM ElearningLessonParagraph elp WHERE elp.elearningLesson = :elearningLesson AND (elp.elearningLessonHeading = :elearningLessonHeading OR (elp.elearningLessonHeading IS NULL AND :elearningLessonHeading < '1')) ORDER BY elp.id")
    public List<ElearningLessonParagraph> findByLessonAndLessonHeadingOrderById(@Param("elearningLesson") ElearningLesson elearningLesson, @Param("elearningLessonHeading") ElearningLessonHeading elearningLessonHeading);
    
    @Query("SELECT DISTINCT elp FROM ElearningLessonParagraph elp, ElearningLesson el WHERE elp.elearningLesson.id = el.id AND elp.elearningLesson = :elearningLesson AND (elp.elearningLessonHeading IS NULL OR el.elearningLessonModel IS NULL) ORDER BY elp.elearningLesson.id, elp.listOrder")
    public List<ElearningLessonParagraph> findByLessonAndNoLessonHeading(@Param("elearningLesson") ElearningLesson elearningLesson);
    
    // It may occur, although it should not happen, that a paragraph has a heading that does not belong to the model of his new lesson, in case a paragraph was moved to another lesson, and the paragraph heading was not reset
    @Query("SELECT DISTINCT elp FROM ElearningLessonParagraph elp, ElearningLessonHeading elh, ElearningLesson el, ElearningLessonModel elm WHERE elp.elearningLesson = :elearningLesson AND elp.elearningLessonHeading.id = elh.id AND elp.elearningLesson.id = el.id AND elh.elearningLessonModel.id != el.elearningLessonModel.id ORDER BY elh.listOrder, elp.listOrder")
    public List<ElearningLessonParagraph> findInvalidLessonHeading(@Param("elearningLesson") ElearningLesson elearningLesson);
    
    @Query("SELECT elp FROM ElearningLessonParagraph elp WHERE elp.elearningLesson = :elearningLesson AND (elp.elearningLessonHeading = :elearningLessonHeading OR (elp.elearningLessonHeading IS NULL AND :elearningLessonHeading < '1')) AND elp.listOrder > :listOrder ORDER BY elp.listOrder ASC LIMIT 1")
    public ElearningLessonParagraph findByNextListOrder(@Param("elearningLesson") ElearningLesson elearningLesson, @Param("elearningLessonHeading") ElearningLessonHeading elearningLessonHeading, @Param("listOrder") int listOrder);

    @Query("SELECT elp FROM ElearningLessonParagraph elp WHERE elp.elearningLesson = :elearningLesson AND (elp.elearningLessonHeading = :elearningLessonHeading OR (elp.elearningLessonHeading IS NULL AND :elearningLessonHeading < '1')) AND elp.listOrder < :listOrder ORDER BY elp.listOrder DESC LIMIT 1")
    public ElearningLessonParagraph findByPreviousListOrder(@Param("elearningLesson") ElearningLesson elearningLesson, @Param("elearningLessonHeading") ElearningLessonHeading elearningLessonHeading, @Param("listOrder") int listOrder);

    @Query("SELECT elp FROM ElearningLessonParagraph elp WHERE elp.elearningLesson = :elearningLesson AND (elp.elearningLessonHeading = :elearningLessonHeading OR (elp.elearningLessonHeading IS NULL AND :elearningLessonHeading < '1')) AND elp.listOrder = :listOrder ORDER BY elp.listOrder DESC")
    public List<ElearningLessonParagraph> findByListOrder(@Param("elearningLesson") ElearningLesson elearningLesson, @Param("elearningLessonHeading") ElearningLessonHeading elearningLessonHeading, @Param("listOrder") int listOrder);
    
    @Query("SELECT COUNT(DISTINCT elp1.id) as count FROM ElearningLessonParagraph elp1, ElearningLessonParagraph elp2 WHERE elp1.id != elp2.id AND elp1.elearningLesson.id = elp2.elearningLesson.id AND elp1.elearningLessonHeading.id = elp2.elearningLessonHeading.id AND elp1.listOrder = elp2.listOrder AND elp1.elearningLessonHeading = :elearningLessonHeading")
    public Long countDuplicateListOrders(@Param("elearningLesson") ElearningLesson elearningLesson, @Param("elearningLessonHeading") ElearningLessonHeading elearningLessonHeading);

}
