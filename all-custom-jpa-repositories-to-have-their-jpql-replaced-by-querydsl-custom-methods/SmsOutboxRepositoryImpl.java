package com.thalasoft.learnintouch.data.jpa.repository;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

public class SmsOutboxRepositoryImpl implements SmsOutboxRepositoryCustom {

    @Autowired
    private SmsOutboxRepository mailOutboxRepository;

    public Long countUnsent() {
        String sqlStatement = "SELECT COUNT(so.id) AS count FROM SmsOutbox so WHERE so.sent != '1'";
        
        TypedQuery<Long> query = mailOutboxRepository.getEntityManager().createQuery(sqlStatement, Long.class);

        Long count = query.getSingleResult();
        
        return count;
    }

    public Long countFailed() {
        String sqlStatement = "SELECT COUNT(so.id) AS count FROM SmsOutbox so WHERE so.sent != '1'";
        
        TypedQuery<Long> query = mailOutboxRepository.getEntityManager().createQuery(sqlStatement, Long.class);

        Long count = query.getSingleResult();
        
        return count;
    }

}
