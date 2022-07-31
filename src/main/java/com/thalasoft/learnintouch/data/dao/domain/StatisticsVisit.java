package com.thalasoft.learnintouch.data.dao.domain;

import org.joda.time.LocalDateTime;

public class StatisticsVisit implements java.io.Serializable {

	private Long id;
	private int version;
	private LocalDateTime visitDatetime;
	private String visitorHostAddress;
	private String visitorBrowser;
	private String visitorReferer;

	public StatisticsVisit() {
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

	public LocalDateTime getVisitDatetime() {
		return this.visitDatetime;
	}

	public void setVisitDatetime(LocalDateTime visitDatetime) {
		this.visitDatetime = visitDatetime;
	}

	public String getVisitorHostAddress() {
		return this.visitorHostAddress;
	}

	public void setVisitorHostAddress(String visitorHostAddress) {
		this.visitorHostAddress = visitorHostAddress;
	}

	public String getVisitorBrowser() {
		return this.visitorBrowser;
	}

	public void setVisitorBrowser(String visitorBrowser) {
		this.visitorBrowser = visitorBrowser;
	}

	public String getVisitorReferer() {
		return this.visitorReferer;
	}

	public void setVisitorReferer(String visitorReferer) {
		this.visitorReferer = visitorReferer;
	}

}
