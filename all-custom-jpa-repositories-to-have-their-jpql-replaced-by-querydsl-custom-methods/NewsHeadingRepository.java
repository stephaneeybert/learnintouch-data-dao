package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.NewsHeading;
import com.thalasoft.learnintouch.data.jpa.domain.NewsPublication;

public interface NewsHeadingRepository extends GenericRepository<NewsHeading, Long> {

    @Query("SELECT nh FROM NewsHeading nh ORDER BY nh.listOrder")
    public Page<NewsHeading> findThemAll(Pageable page);

    @Query("SELECT nh FROM NewsHeading nh WHERE nh.newsPublication = :newsPublication OR (nh.newsPublication IS NULL AND :newsPublication < '1')) ORDER BY nh.listOrder")
    public NewsHeading findByNewsPublication(@Param("newsPublication") NewsPublication newsPublication);
    
    @Query("SELECT nh FROM NewsHeading nh WHERE nh.newsPublication = :newsPublication OR (nh.newsPublication IS NULL AND :newsPublication < '1')) ORDER BY nh.id")
    public NewsHeading findByNewsPublicationOrderById(@Param("newsPublication") NewsPublication newsPublication);
    
    public List<NewsHeading> findByImage(String image);
    
    @Query("SELECT nh FROM NewsHeading nh WHERE nh.newsPublication = :newsPublication AND nh.listOrder > :listOrder ORDER BY nh.listOrder ASC LIMIT 1")
    public NewsHeading findByNextListOrder(@Param("newsPublication") NewsPublication newsPublication, @Param("listOrder") int listOrder);

    @Query("SELECT nh FROM NewsHeading nh WHERE nh.newsPublication = :newsPublication AND nh.listOrder < :listOrder ORDER BY nh.listOrder DESC LIMIT 1")
    public NewsHeading findByPreviousListOrder(@Param("newsPublication") NewsPublication newsPublication, @Param("listOrder") int listOrder);

    @Query("SELECT nh FROM NewsHeading nh WHERE nh.newsPublication = :newsPublication AND nh.listOrder = :listOrder ORDER BY nh.listOrder DESC")
    public List<NewsHeading> findByListOrder(@Param("newsPublication") NewsPublication newsPublication, @Param("listOrder") int listOrder);

    @Query("SELECT COUNT(DISTINCT nh1.id) as count FROM NewsHeading nh1, NewsHeading nh2 WHERE nh1.id != nh2.id AND nh1.newsPublication.id = nh2.newsPublication.id AND nh1.listOrder = nh2.listOrder AND nh1.newsPublication = :newsPublication")
    public Long countDuplicateListOrders(@Param("newsPublication") NewsPublication newsPublication);

}

