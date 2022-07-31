package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.NewsStory;
import com.thalasoft.learnintouch.data.jpa.domain.NewsStoryImage;

public interface NewsStoryImageRepository extends GenericRepository<NewsStoryImage, Long> {

    @Query("SELECT nsi FROM NewsStoryImage nsi WHERE nsi.newsStory = :newsStory AND nsi.listOrder = :listOrder ORDER BY nsi.listOrder DESC")
    public List<NewsStoryImage> findByListOrder(@Param("newsStory") NewsStory newsStory, @Param("listOrder") int listOrder);

    @Query("SELECT nsi FROM NewsStoryImage nsi WHERE nsi.newsStory = :newsStory AND nsi.listOrder > :listOrder ORDER BY nsi.listOrder ASC LIMIT 1")
    public NewsStoryImage findByNextListOrder(@Param("newsStory") NewsStory newsStory, @Param("listOrder") int listOrder);
    
    @Query("SELECT nsi FROM NewsStoryImage nsi WHERE nsi.newsStory = :newsStory AND nsi.listOrder < :listOrder ORDER BY nsi.listOrder DESC LIMIT 1")
    public NewsStoryImage findByPreviousListOrder(@Param("newsStory") NewsStory newsStory, @Param("listOrder") int listOrder);
    
    @Query("SELECT COUNT(DISTINCT nsi1.id) as count FROM NewsStoryImage nsi1, NewsStoryImage nsi2 WHERE nsi1.id != nsi2.id AND nsi1.newsStory.id = nsi2.newsStory.id AND nsi1.listOrder = nsi2.listOrder AND nsi1.newsStory = :newsStory")
    public Long countDuplicateListOrders(@Param("newsStory") NewsStory newsStory);
    
    @Query("SELECT nsi FROM NewsStoryImage nsi WHERE nsi.newsStory = :newsStory ORDER BY nsi.listOrder")
    public NewsStoryImage findByNewsStory(@Param("newsStory") NewsStory newsStory);

    @Query("SELECT nsi FROM NewsStoryImage nsi WHERE nsi.newsStory = :newsStory ORDER BY nsi.id")
    public NewsStoryImage findByNewsStoryOrderById(@Param("newsStory") NewsStory newsStory);

    public List<NewsStoryImage> findByImage(String image);

}

