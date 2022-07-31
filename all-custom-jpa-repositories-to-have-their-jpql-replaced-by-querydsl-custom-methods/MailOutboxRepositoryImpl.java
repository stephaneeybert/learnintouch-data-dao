package com.thalasoft.learnintouch.data.jpa.repository;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

public class MailOutboxRepositoryImpl implements MailOutboxRepositoryCustom {

    @Autowired
    private MailOutboxRepository mailOutboxRepository;

    public Long countUnsent() {
        String sqlStatement = "SELECT COUNT(mo.id) AS count FROM MailOutbox mo WHERE mo.sent != '1'";
        
        TypedQuery<Long> query = mailOutboxRepository.getEntityManager().createQuery(sqlStatement, Long.class);

        Long count = query.getSingleResult();
        
        return count;
    }

    public Long countFailed() {
        String sqlStatement = "SELECT COUNT(mo.id) AS count FROM MailOutbox mo WHERE mo.sent != '1'";
        
        TypedQuery<Long> query = mailOutboxRepository.getEntityManager().createQuery(sqlStatement, Long.class);

        Long count = query.getSingleResult();
        
        return count;
    }

}
