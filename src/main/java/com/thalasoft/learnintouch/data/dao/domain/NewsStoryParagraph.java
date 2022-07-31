package com.thalasoft.learnintouch.data.dao.domain;

public class NewsStoryParagraph implements java.io.Serializable {

	private Long id;
	private int version;
	private String header;
	private String body;
	private String footer;
	private NewsStory newsStory;

	public NewsStoryParagraph() {
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

	public NewsStory getNewsStory() {
		return this.newsStory;
	}

	public void setNewsStory(NewsStory newsStory) {
		this.newsStory = newsStory;
	}

	public String getHeader() {
		return this.header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getFooter() {
		return this.footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

}
