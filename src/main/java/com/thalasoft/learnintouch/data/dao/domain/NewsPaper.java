package com.thalasoft.learnintouch.data.dao.domain;

import org.joda.time.LocalDateTime;

public class NewsPaper implements java.io.Serializable {

	private Long id;
	private int version;
	private String title;
	private String image;
	private String header;
	private String footer;
	private LocalDateTime releaseDate;
	private LocalDateTime archiveDate;
	private boolean notPublished;
	private NewsPublication newsPublication;

	public NewsPaper() {
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

	public NewsPublication getNewsPublication() {
		return this.newsPublication;
	}

	public void setNewsPublication(NewsPublication newsPublication) {
		this.newsPublication = newsPublication;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getHeader() {
		return this.header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getFooter() {
		return this.footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
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

	public boolean getNotPublished() {
		return this.notPublished;
	}

	public void setNotPublished(boolean notPublished) {
		this.notPublished = notPublished;
	}

}
