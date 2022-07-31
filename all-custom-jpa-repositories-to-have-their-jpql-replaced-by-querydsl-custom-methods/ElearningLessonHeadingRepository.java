package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.dao.domain.ElearningLessonModel;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningLessonHeading;

public interface ElearningLessonHeadingRepository extends GenericRepository<ElearningLessonHeading, Long> {

    @Query("SELECT elh FROM ElearningLessonHeading elh ORDER BY elh.listOrder")
    public Page<ElearningLessonHeading> findThemAll(Pageable page);

    @Query("SELECT elh FROM ElearningLessonHeading elh WHERE elh.elearningLessonModel = :elearningLessonModel OR (elh.elearningLessonModel IS NULL AND :elearningLessonModel < '1') ORDER BY elh.listOrder")
    public List<ElearningLessonHeading> findByLessonModel(@Param("elearningLessonModel") ElearningLessonModel elearningLessonModel);

    @Query("SELECT elh FROM ElearningLessonHeading elh WHERE elh.elearningLessonModel = :elearningLessonModel OR (elh.elearningLessonModel IS NULL AND :elearningLessonModel < '1') ORDER BY elh.id")
    public List<ElearningLessonHeading> findByLessonModelOrderById(@Param("elearningLessonModel") ElearningLessonModel elearningLessonModel);

    public List<ElearningLessonHeading> findByImage(String image);
    
    @Query("SELECT elh FROM ElearningLessonHeading elh WHERE (elh.elearningLessonModel = :elearningLessonModel OR (elh.elearningLessonModel IS NULL AND :elearningLessonModel < '1')) AND elh.listOrder > :listOrder ORDER BY elh.listOrder ASC LIMIT 1")
    public ElearningLessonHeading findByNextListOrder(@Param("elearningLessonModel") ElearningLessonModel elearningLessonModel, @Param("listOrder") int listOrder);

    @Query("SELECT elh FROM ElearningLessonHeading elh WHERE (elh.elearningLessonModel = :elearningLessonModel OR (elh.elearningLessonModel IS NULL AND :elearningLessonModel < '1')) AND elh.listOrder < :listOrder ORDER BY elh.listOrder DESC LIMIT 1")
    public ElearningLessonHeading findByPreviousListOrder(@Param("elearningLessonModel") ElearningLessonModel elearningLessonModel, @Param("listOrder") int listOrder);

    @Query("SELECT elh FROM ElearningLessonHeading elh WHERE (elh.elearningLessonModel = :elearningLessonModel OR (elh.elearningLessonModel IS NULL AND :elearningLessonModel < '1')) AND elh.listOrder = :listOrder ORDER BY elh.listOrder DESC")
    public List<ElearningLessonHeading> findByListOrder(@Param("elearningLessonModel") ElearningLessonModel elearningLessonModel, @Param("listOrder") int listOrder);
    
    @Query("SELECT COUNT(DISTINCT elh1.id) as count FROM ElearningLessonHeading elh1, ElearningLessonHeading elh2 WHERE elh1.id != elh2.id AND elh1.elearningLessonModel.id = elh2.elearningLessonModel.id AND elh1.listOrder = elh2.listOrder AND elh1.elearningLessonModel = :elearningLessonModel")
    public Long countDuplicateListOrders(@Param("elearningLessonModel") ElearningLessonModel elearningLessonModel);

}
