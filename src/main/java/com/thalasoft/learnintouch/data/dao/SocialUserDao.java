package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.SocialUser;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;

public interface SocialUserDao extends GenericDao<SocialUser, Serializable> {

	public SocialUser findWithFacebookUserIdAndUser(String facebookUserId, UserAccount userAccount);
	
	public SocialUser findWithFacebookUserId(String facebookUserId);
	
	public SocialUser findWithLinkedinUserIdAndUser(String linkedinUserId, UserAccount userAccount);
	
	public SocialUser findWithLinkedinUserId(String linkedinUserId);
	
	public SocialUser findWithUser(UserAccount userAccount);
	
}
