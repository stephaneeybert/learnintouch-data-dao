package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thalasoft.learnintouch.data.jpa.domain.MailList;
import com.thalasoft.learnintouch.data.jpa.domain.MailListAddress;

public interface MailListAddressRepositoryCustom {

    public Page<MailListAddress> searchByMailListAndSubscriber(MailList mailList, String searchTerm, Pageable page);
    
}
