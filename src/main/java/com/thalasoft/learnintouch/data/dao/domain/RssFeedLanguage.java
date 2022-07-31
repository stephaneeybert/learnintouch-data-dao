package com.thalasoft.learnintouch.data.dao.domain;

public class RssFeedLanguage implements java.io.Serializable {

	private Long id;
	private int version;
	private String languageCode;
	private String title;
	private String url;
	private RssFeed rssFeed;

	public RssFeedLanguage() {
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

	public String getLanguageCode() {
		return this.languageCode;
	}

	public void setLanguageCode(String language) {
		this.languageCode = language;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public RssFeed getRssFeed() {
		return this.rssFeed;
	}

	public void setRssFeed(RssFeed rssFeed) {
		this.rssFeed = rssFeed;
	}

}
