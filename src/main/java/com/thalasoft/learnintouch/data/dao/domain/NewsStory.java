package com.thalasoft.learnintouch.data.dao.domain;

import org.joda.time.LocalDateTime;

public class NewsStory implements java.io.Serializable {

	private Long id;
	private int version;
	private String headline;
	private String excerpt;
	private String audio;
	private String audioUrl;
	private String link;
	private LocalDateTime releaseDate;
	private LocalDateTime archiveDate;
	private LocalDateTime eventStartDate;
	private LocalDateTime eventEndDate;
	private int listOrder;
	private NewsEditor newsEditor;
	private NewsPaper newsPaper;
	private NewsHeading newsHeading;

	public NewsStory() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getHeadline() {
		return this.headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getExcerpt() {
		return this.excerpt;
	}

	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}

	public String getAudio() {
		return this.audio;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}

	public String getAudioUrl() {
		return this.audioUrl;
	}

	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public LocalDateTime getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(LocalDateTime releaseDate) {
		this.releaseDate = releaseDate;
	}

	public LocalDateTime getArchiveDate() {
		return this.archiveDate;
	}

	public void setArchiveDate(LocalDateTime archive) {
		this.archiveDate = archive;
	}

	public LocalDateTime getEventStartDate() {
		return eventStartDate;
	}

	public void setEventStartDate(LocalDateTime eventStartDate) {
		this.eventStartDate = eventStartDate;
	}

	public LocalDateTime getEventEndDate() {
		return eventEndDate;
	}

	public void setEventEndDate(LocalDateTime eventEndDate) {
		this.eventEndDate = eventEndDate;
	}

	public NewsEditor getNewsEditor() {
		return this.newsEditor;
	}

	public void setNewsEditor(NewsEditor newsEditor) {
		this.newsEditor = newsEditor;
	}

	public NewsPaper getNewsPaper() {
		return this.newsPaper;
	}

	public void setNewsPaper(NewsPaper newsPaper) {
		this.newsPaper = newsPaper;
	}

	public NewsHeading getNewsHeading() {
		return this.newsHeading;
	}

	public void setNewsHeading(NewsHeading newsHeading) {
		this.newsHeading = newsHeading;
	}

	public int getListOrder() {
		return this.listOrder;
	}

	public void setListOrder(int listOrder) {
		this.listOrder = listOrder;
	}

}
