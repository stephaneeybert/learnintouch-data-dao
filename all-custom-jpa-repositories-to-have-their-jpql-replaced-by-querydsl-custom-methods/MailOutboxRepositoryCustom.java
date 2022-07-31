package com.thalasoft.learnintouch.data.jpa.repository;

public interface MailOutboxRepositoryCustom {

    public Long countUnsent();

    public Long countFailed();

}
