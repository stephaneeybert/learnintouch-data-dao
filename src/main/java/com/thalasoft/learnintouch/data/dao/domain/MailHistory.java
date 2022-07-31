package com.thalasoft.learnintouch.data.dao.domain;

import org.joda.time.LocalDateTime;

public class MailHistory implements java.io.Serializable {

	private Long id;
	private int version;
	private String subject;
	private String body;
	private String description;
	private String attachments;
	private String email;
	private LocalDateTime sendDatetime;
	private Admin admin;
	private MailList mailList;

	public MailHistory() {
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

	public MailList getMailList() {
		return this.mailList;
	}

	public void setMailList(MailList mailList) {
		this.mailList = mailList;
	}

	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
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

	public String getAttachments() {
		return this.attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getSendDatetime() {
		return this.sendDatetime;
	}

	public void setSendDatetime(LocalDateTime sendDatetime) {
		this.sendDatetime = sendDatetime;
	}

}
