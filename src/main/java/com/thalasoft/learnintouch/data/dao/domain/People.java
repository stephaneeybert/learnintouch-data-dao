package com.thalasoft.learnintouch.data.dao.domain;

public class People implements java.io.Serializable {

	private Long id;
	private int version;
	private String firstname;
	private String lastname;
	private String email;
	private String profile;
	private String image;
	private int listOrder;
	private PeopleCategory peopleCategory;

	public People() {
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

	public PeopleCategory getPeopleCategory() {
		return this.peopleCategory;
	}

	public void setPeopleCategory(PeopleCategory peopleCategory) {
		this.peopleCategory = peopleCategory;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfile() {
		return this.profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getListOrder() {
		return this.listOrder;
	}

	public void setListOrder(int listOrder) {
		this.listOrder = listOrder;
	}

}
