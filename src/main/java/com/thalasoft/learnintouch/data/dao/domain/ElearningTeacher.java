package com.thalasoft.learnintouch.data.dao.domain;

public class ElearningTeacher implements java.io.Serializable {

    private Long id;
    private int version;
    private UserAccount userAccount;

    public ElearningTeacher() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public UserAccount getUserAccount() {
        return this.userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

}
