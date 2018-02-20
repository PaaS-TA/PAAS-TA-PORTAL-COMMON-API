package org.openpaas.paasta.portal.common.api.entity.cc;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by indra on 2018-02-08.
 */

@Entity
@Table(name = "buildpack_lifecycle_data")
public class BuildpackLifecyleDataCc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "guid")
    private String guid;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "app_guid")
    private String appGuid;

    @Column(name = "droplet_guid")
    private String dropletGuid;

    @Column(name = "stack")
    private String stack;

    @Column(name = "encrypted_buildpack_url")
    private String encryptedBuildpackUrl;

    @Column(name = "encrypted_buildpack_url_salt")
    private String encryptedBuildpackUrlSalt;

    @Column(name = "admin_buildpack_name")
    private String adminBuildpackName;

    @Column(name = "build_guid")
    private String buildGuid;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAppGuid() {
        return appGuid;
    }

    public void setAppGuid(String appGuid) {
        this.appGuid = appGuid;
    }

    public String getDropletGuid() {
        return dropletGuid;
    }

    public void setDropletGuid(String dropletGuid) {
        this.dropletGuid = dropletGuid;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public String getEncryptedBuildpackUrl() {
        return encryptedBuildpackUrl;
    }

    public void setEncryptedBuildpackUrl(String encryptedBuildpackUrl) {
        this.encryptedBuildpackUrl = encryptedBuildpackUrl;
    }

    public String getEncryptedBuildpackUrlSalt() {
        return encryptedBuildpackUrlSalt;
    }

    public void setEncryptedBuildpackUrlSalt(String encryptedBuildpackUrlSalt) {
        this.encryptedBuildpackUrlSalt = encryptedBuildpackUrlSalt;
    }

    public String getAdminBuildpackName() {
        return adminBuildpackName;
    }

    public void setAdminBuildpackName(String adminBuildpackName) {
        this.adminBuildpackName = adminBuildpackName;
    }

    public String getBuildGuid() {
        return buildGuid;
    }

    public void setBuildGuid(String buildGuid) {
        this.buildGuid = buildGuid;
    }
}
