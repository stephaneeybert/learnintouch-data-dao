package com.thalasoft.learnintouch.data.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.UserAccountDao;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;
import com.thalasoft.learnintouch.data.service.UserAccountService;
import com.thalasoft.learnintouch.data.utils.Common;

@Transactional
public class UserAccountServiceImpl implements UserAccountService {

    private static final int USER_PASSWORD_SALT_LENGTH = 30;

	@Autowired
	private UserAccountDao userDao;

	protected void setUserDao(UserAccountDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserAccount savePassword(UserAccount userAccount, String password) {
        String salt = generatePasswordSalt();
        userAccount.setPasswordSalt(salt);
		userAccount.setPassword(saltAndHashPassword(password, userAccount.getPasswordSalt()));
		return userDao.makePersistent(userAccount);
	}
	
    @Override
    public boolean checkPassword(UserAccount userAccount, String password) {
        return userAccount.getPassword().equals(saltAndHashPassword(password, userAccount.getPasswordSalt()));
    }
    
	@Override
	public UserAccount saveReadablePassword(UserAccount userAccount, String readablePassword) {
		userAccount.setReadablePassword(readablePassword);
		return userDao.makePersistent(userAccount);
	}

	@Override
	public UserAccount save(UserAccount userAccount) {
		return userDao.makePersistent(userAccount);
	}
	
    private String saltAndHashPassword(String password, String salt) {
        return Common.phpCompatibleMD5(saltPassword(password, salt));
    }
    
    private String generatePasswordSalt() {
        return Common.generateUniqueId(USER_PASSWORD_SALT_LENGTH);
    }
    
    private String saltPassword(String password, String salt) {
        return password + salt;
    }

}

