package com.thalasoft.learnintouch.data.jpa.repository;

public interface SmsOutboxRepositoryCustom {

    public Long countUnsent();

    public Long countFailed();

}
