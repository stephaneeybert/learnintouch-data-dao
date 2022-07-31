package com.thalasoft.learnintouch.data.dao.domain;

public class ContentImport  implements java.io.Serializable {

     private Long id;
     private int version;
     private String domainName;
     private boolean isImporting;
     private boolean isExporting;
     private String permissionKey;
     private String permissionStatus;

    public ContentImport() {
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
    public String getDomainName() {
        return this.domainName;
    }
    
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
    public boolean getIsImporting() {
        return this.isImporting;
    }
    
    public void setIsImporting(boolean isImporting) {
        this.isImporting = isImporting;
    }
    public boolean getIsExporting() {
        return this.isExporting;
    }
    
    public void setIsExporting(boolean isExporting) {
        this.isExporting = isExporting;
    }
    public String getPermissionKey() {
        return this.permissionKey;
    }
    
    public void setPermissionKey(String permissionKey) {
        this.permissionKey = permissionKey;
    }
    public String getPermissionStatus() {
        return this.permissionStatus;
    }
    
    public void setPermissionStatus(String permissionStatus) {
        this.permissionStatus = permissionStatus;
    }




}


