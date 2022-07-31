package com.thalasoft.learnintouch.data.jpa.repository;

import com.thalasoft.learnintouch.data.jpa.domain.SocialUser;
import com.thalasoft.learnintouch.data.jpa.domain.UserAccount;

public interface SocialUserRepository extends GenericRepository<SocialUser, Long> {

	public SocialUser findByFacebookUserIdAndUserAccount(String facebookUserId, UserAccount userAccount);

    public SocialUser findByFacebookUserId(String facebookUserId);

    public SocialUser findByLinkedinUserIdAndUserAccount(String linkedInUserId, UserAccount userAccount);

    public SocialUser findByLinkedinUserId(String linkedInUserId);

    public SocialUser findByTwitterUserIdAndUserAccount(String twitterUserId, UserAccount userAccount);

    public SocialUser findByTwitterUserId(String twitterUserId);

    public SocialUser findByGoogleUserIdAndUserAccount(String googleUserId, UserAccount userAccount);

    public SocialUser findByGoogleUserId(String googleUserId);

}
