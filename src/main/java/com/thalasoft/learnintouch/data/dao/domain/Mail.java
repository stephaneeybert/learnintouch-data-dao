package com.thalasoft.learnintouch.data.dao.domain;

import org.joda.time.LocalDateTime;

public class Mail implements java.io.Serializable {

	private Long id;
	private int version;
	private String subject;
	private String body;
	private String description;
	private boolean textFormat;
	private boolean locked;
	private String attachments;
	private LocalDateTime creationDatetime;
	private LocalDateTime sendDatetime;
	private Admin admin;
	private MailCategory mailCategory;

	public Mail() {
		this.creationDatetime = new LocalDateTime();
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

	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public MailCategory getMailCategory() {
		return this.mailCategory;
	}

	public void setMailCategory(MailCategory mailCategory) {
		this.mailCategory = mailCategory;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isTextFormat() {
		return this.textFormat;
	}

	public void setTextFormat(boolean textFormat) {
		this.textFormat = textFormat;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public String getAttachments() {
		return this.attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

	public LocalDateTime getCreationDatetime() {
		return this.creationDatetime;
	}

	public void setCreationDatetime(LocalDateTime creationDatetime) {
		this.creationDatetime = creationDatetime;
	}

	public LocalDateTime getSendDatetime() {
		return this.sendDatetime;
	}

	public void setSendDatetime(LocalDateTime sendDatetime) {
		this.sendDatetime = sendDatetime;
	}

}
