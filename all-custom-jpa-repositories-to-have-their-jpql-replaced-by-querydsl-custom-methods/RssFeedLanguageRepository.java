package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.RssFeed;
import com.thalasoft.learnintouch.data.jpa.domain.RssFeedLanguage;

public interface RssFeedLanguageRepository extends GenericRepository<RssFeedLanguage, Long> {

    @Query("SELECT rfl FROM RssFeedLanguage rfl WHERE rfl.rssFeed = :rssFeed")
    public RssFeedLanguage findByRssFeed(@Param("rssFeed") RssFeed rssFeed);

    @Query("SELECT rfl FROM RssFeedLanguage rfl WHERE rfl.rssFeed = :rssFeed AND rfl.languageCode = :languageCode")
    public RssFeedLanguage findByRssFeedAndLanguage(@Param("rssFeed") RssFeed rssFeed, @Param("languageCode") String languageCode);

    @Query("SELECT rfl FROM RssFeedLanguage rfl WHERE rfl.rssFeed = :rssFeed AND rfl.languageCode = ''")
    public RssFeedLanguage findByRssFeedAndNoLanguage(@Param("rssFeed") RssFeed rssFeed);
    
}
