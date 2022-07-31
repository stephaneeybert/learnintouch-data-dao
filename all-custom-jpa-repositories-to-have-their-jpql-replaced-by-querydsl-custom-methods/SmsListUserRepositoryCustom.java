package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thalasoft.learnintouch.data.jpa.domain.SmsList;
import com.thalasoft.learnintouch.data.jpa.domain.SmsListUser;

public interface SmsListUserRepositoryCustom {

    public Page<SmsListUser> searchBySmsListAndSubscriber(SmsList smsList, String searchTerm, Pageable page);
    
}
