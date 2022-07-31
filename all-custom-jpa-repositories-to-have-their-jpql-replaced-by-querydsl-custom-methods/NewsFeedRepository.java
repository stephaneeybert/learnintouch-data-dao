package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.NewsFeed;
import com.thalasoft.learnintouch.data.jpa.domain.NewsPaper;

public interface NewsFeedRepository extends GenericRepository<NewsFeed, Long> {

    public List<NewsFeed> findByImage(String image);

    @Query("SELECT nf FROM NewsFeed nf WHERE nf.newsPaper = :newsPaper")
    public NewsFeed findByNewsPaper(@Param("newsPaper") NewsPaper newsPaper);
    
}
