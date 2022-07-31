package com.thalasoft.learnintouch.data.dao.domain;

public class ElearningLevel implements java.io.Serializable {

	private Long id;
	private int version;
	private String name;
	private String description;

	public ElearningLevel() {
	}

	public ElearningLevel(String name, String description) {
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
