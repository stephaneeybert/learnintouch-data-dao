package com.thalasoft.learnintouch.data.dao.domain;

public class SocialUser implements java.io.Serializable {

	private Long id;
	private int version;
	private String facebookUserId;
	private String linkedinUserId;
	private String googleUserId;
	private String twitterUserId;
	private UserAccount userAccount;

	public SocialUser() {
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

	public String getFacebookUserId() {
		return this.facebookUserId;
	}

	public void setFacebookUserId(String facebookUserId) {
		this.facebookUserId = facebookUserId;
	}

	protected String getLinkedinUserId() {
		return linkedinUserId;
	}

	protected void setLinkedinUserId(String linkedinUserId) {
		this.linkedinUserId = linkedinUserId;
	}

	public String getGoogleUserId() {
		return googleUserId;
	}

	public void setGoogleUserId(String googleUserId) {
		this.googleUserId = googleUserId;
	}

	public String getTwitterUserId() {
		return twitterUserId;
	}

	public void setTwitterUserId(String twitterUserId) {
		this.twitterUserId = twitterUserId;
	}

	public UserAccount getUserAccount() {
		return this.userAccount;
	}
	
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
}
