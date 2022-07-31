package com.thalasoft.learnintouch.data.service.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.AdminDao;
import com.thalasoft.learnintouch.data.dao.MailDao;
import com.thalasoft.learnintouch.data.dao.SmsDao;
import com.thalasoft.learnintouch.data.dao.domain.Admin;
import com.thalasoft.learnintouch.data.dao.domain.Mail;
import com.thalasoft.learnintouch.data.dao.domain.Sms;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.service.AdminService;
import com.thalasoft.learnintouch.data.utils.Common;

@Transactional
public class AdminServiceImpl implements AdminService {

    private static final int ADMIN_PASSWORD_SALT_LENGTH = 30;

    @Autowired
	private AdminDao adminDao;

	@Autowired
	private MailDao mailDao;

	@Autowired
	private SmsDao smsDao;

	protected void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	protected void setMailDao(MailDao mailDao) {
		this.mailDao = mailDao;
	}
	
	protected void setSmsDao(SmsDao smsDao) {
		this.smsDao = smsDao;
	}
	
	@Override
	public Admin save(Admin admin) {
		return adminDao.makePersistent(admin);
	}
	
	@Override
	public boolean isNotUsedByAnyMail(Admin admin) {
		Page<Mail> page = mailDao.findWithAdmin(admin, 0, 0);
		List<Mail> mails = page.getPageItems();
		return mails.size() == 0;
	}

	@Override
	public boolean isNotUsedByAnySms(Admin admin) {
		Page<Sms> page = smsDao.findWithAdmin(admin, 0, 0);
		List<Sms> smss = page.getPageItems(); 
		return smss.size() == 0;
	}

    @Override
    public Admin savePassword(Admin admin, String password) {
        String salt = generatePasswordSalt();
        admin.setPasswordSalt(salt);
        admin.setPassword(saltAndHashPassword(password, admin.getPasswordSalt()));
        return save(admin);
    }

    @Override
    public boolean checkPassword(Admin admin, String password) {
        return admin.getPassword().equals(saltAndHashPassword(password, admin.getPasswordSalt()));
    }
    
    private String saltAndHashPassword(String password, String salt) {
        return Common.phpCompatibleMD5(saltPassword(password, salt));
    }
    
    private String generatePasswordSalt() {
        return Common.generateUniqueId(ADMIN_PASSWORD_SALT_LENGTH);
    }
    
    private String saltPassword(String password, String salt) {
        return password + salt;
    }

}

