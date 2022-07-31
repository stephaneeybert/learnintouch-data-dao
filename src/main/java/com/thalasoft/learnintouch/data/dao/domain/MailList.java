package com.thalasoft.learnintouch.data.dao.domain;

public class MailList implements java.io.Serializable {

	private Long id;
	private int version;
	private String name;
	private boolean autoSubscribe;
	
	public MailList() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAutoSubscribe() {
		return autoSubscribe;
	}

	public void setAutoSubscribe(boolean autoSubscribe) {
		this.autoSubscribe = autoSubscribe;
	}

}
