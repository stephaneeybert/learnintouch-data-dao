package com.thalasoft.learnintouch.data.dao.domain;

import org.joda.time.LocalDateTime;

public class UserAccount implements java.io.Serializable {

	private Long id;
	private int version;
	private Address address;
	private String firstname;
	private String lastname;
	private String organisation;
	private String email;
	private String fax;
	private String homePhone;
	private String workPhone;
	private String mobilePhone;
	private String password;
	private String passwordSalt;
	private String readablePassword;
	private boolean unconfirmedEmail;
	private LocalDateTime validUntil;
	private LocalDateTime lastLogin;
	private String profile;
	private String image;
	private boolean imported;
	private boolean mailSubscribe;
	private boolean smsSubscribe;
	private LocalDateTime creationDatetime;

	public UserAccount() {
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

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	public String getOrganisation() {
		return this.organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getWorkPhone() {
		return this.workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getReadablePassword() {
		return this.readablePassword;
	}

	public void setReadablePassword(String readablePassword) {
		this.readablePassword = readablePassword;
	}

	protected boolean isUnconfirmedEmail() {
		return unconfirmedEmail;
	}

	protected void setUnconfirmedEmail(boolean unconfirmedEmail) {
		this.unconfirmedEmail = unconfirmedEmail;
	}

	public LocalDateTime getValidUntil() {
		return this.validUntil;
	}

	public void setValidUntil(LocalDateTime validUntil) {
		this.validUntil = validUntil;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
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

	public boolean isImported() {
		return imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}

	public boolean isMailSubscribe() {
		return this.mailSubscribe;
	}

	public void setMailSubscribe(boolean mailSubscribe) {
		this.mailSubscribe = mailSubscribe;
	}

	public boolean isSmsSubscribe() {
		return this.smsSubscribe;
	}

	public void setSmsSubscribe(boolean smsSubscribe) {
		this.smsSubscribe = smsSubscribe;
	}

	public LocalDateTime getCreationDatetime() {
		return this.creationDatetime;
	}

	public void setCreationDatetime(LocalDateTime creationDatetime) {
		this.creationDatetime = creationDatetime;
	}

}
