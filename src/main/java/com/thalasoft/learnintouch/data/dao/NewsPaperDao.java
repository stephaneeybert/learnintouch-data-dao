package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import org.joda.time.LocalDateTime;

import com.thalasoft.learnintouch.data.dao.domain.NewsPaper;
import com.thalasoft.learnintouch.data.dao.domain.NewsPublication;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface NewsPaperDao extends GenericDao<NewsPaper, Serializable> {

	public Page<NewsPaper> findAll(int pageNumber, int pageSize);

	public NewsPaper findWithTitle(String title);

	public List<NewsPaper> findWithImage(String image);

	public Page<NewsPaper> findWithNewsPublication(NewsPublication newsPublication, int pageNumber, int pageSize);

	public Page<NewsPaper> findWithPatternLike(String pattern, int pageNumber, int pageSize);

	public Page<NewsPaper> findCurrentWithPatternLike(String pattern, LocalDateTime systemDateTime, int pageNumber, int pageSize);

	public Page<NewsPaper> findCurrentWithPatternInNewsPaperAndNewsPublicationLike(String pattern, LocalDateTime systemDateTime, int pageNumber, int pageSize);

	public Page<NewsPaper> findWithNewsPublicationAndCurrent(NewsPublication newsPublication, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<NewsPaper> findCurrent(LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<NewsPaper> findWithNewsPublicationAndArchived(NewsPublication newsPublication, LocalDateTime systemDateTime, int pageNumber, int pageSize);

	public Page<NewsPaper> findWithNewsPublicationAndDeferred(NewsPublication newsPublication, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<NewsPaper> findWithNewsPublicationAndPublished(NewsPublication newsPublication, int pageNumber, int pageSize);
	
	public Page<NewsPaper> findWithNewsPublicationAndNotPublished(NewsPublication newsPublication, int pageNumber, int pageSize);

	public Page<NewsPaper> findWithPatternLikeAndNewsPublication(String pattern, NewsPublication newsPublication, int pageNumber, int pageSize);
	
	public Page<NewsPaper> findWithNewsPublicationAndRecent(NewsPublication newsPublication, LocalDateTime systemDateTime, int pageNumber, int pageSize);

	public Page<NewsPaper> findWithNewsPublicationAndPassed(NewsPublication newsPublication, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
}
