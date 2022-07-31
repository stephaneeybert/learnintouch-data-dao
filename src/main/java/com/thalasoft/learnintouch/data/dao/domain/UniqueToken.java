package com.thalasoft.learnintouch.data.dao.domain;

import org.joda.time.LocalDateTime;

public class UniqueToken implements java.io.Serializable {

	private Long id;
	private int version;
	private String name;
	private String value;
	private LocalDateTime creationDatetime;
	private LocalDateTime expirationDatetime;

	public UniqueToken() {
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

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public LocalDateTime getCreationDatetime() {
		return this.creationDatetime;
	}

	public void setCreationDatetime(LocalDateTime creationDatetime) {
		this.creationDatetime = creationDatetime;
	}

	public LocalDateTime getExpirationDatetime() {
		return this.expirationDatetime;
	}

	public void setExpirationDatetime(LocalDateTime expirationDatetime) {
		this.expirationDatetime = expirationDatetime;
	}

}
