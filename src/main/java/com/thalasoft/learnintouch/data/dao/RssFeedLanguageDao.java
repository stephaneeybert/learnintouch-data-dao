package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.RssFeed;
import com.thalasoft.learnintouch.data.dao.domain.RssFeedLanguage;

public interface RssFeedLanguageDao extends GenericDao<RssFeedLanguage, Serializable> {

	public List<RssFeedLanguage> findWithRssFeed(RssFeed rssFeed);

	public RssFeedLanguage findWithRssFeedAndLanguage(RssFeed rssFeed, String language);

	public RssFeedLanguage findWithRssFeedAndNoLanguage(RssFeed rssFeed);

}
