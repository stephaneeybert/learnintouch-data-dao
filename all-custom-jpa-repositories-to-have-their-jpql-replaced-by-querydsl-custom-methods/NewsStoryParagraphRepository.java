package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.NewsStory;
import com.thalasoft.learnintouch.data.jpa.domain.NewsStoryParagraph;

public interface NewsStoryParagraphRepository extends GenericRepository<NewsStoryParagraph, Long> {

    @Query("SELECT nsp FROM NewsStoryParagraph nsp WHERE nsp.newsStory = :newsStory ORDER BY nsp.id")
    public NewsStoryParagraph findByNewsStory(@Param("newsStory") NewsStory newsStory);

    @Query("SELECT nsp FROM NewsStoryParagraph nsp WHERE nsp.header LIKE CONCAT('%', :image, '%') OR nsp.body LIKE CONCAT('%', :image, '%') OR nsp.footer LIKE CONCAT('%', :image, '%')")
    public List<NewsStoryParagraph> findLikeImage(@Param("image") String image);

}

