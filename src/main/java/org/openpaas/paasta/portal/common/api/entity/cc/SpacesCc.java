package org.openpaas.paasta.portal.common.api.entity.cc;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by indra on 2018-02-13.
 */
@Entity
@Table(name = "spaces")
public class SpacesCc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "guid", nullable = false)
    private String guid;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "organization_id", nullable = false)
    private int organizationId;

    @Column(name = "space_quota_definition_id")
    private int spaceQuotaDefinitionId;

    @Column(name = "allow_ssh", columnDefinition="boolean default true")
    private Boolean allowSsh;

    @Column(name = "isolation_segment_guid")
    private String isolationSegmentGuid;

    @Column(name = "space_id")
    private int spaceId;

    @Column(name = "user_id")
    private int userId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public int getSpaceQuotaDefinitionId() {
        return spaceQuotaDefinitionId;
    }

    public void setSpaceQuotaDefinitionId(int spaceQuotaDefinitionId) {
        this.spaceQuotaDefinitionId = spaceQuotaDefinitionId;
    }

    public Boolean getAllowSsh() {
        return allowSsh;
    }

    public void setAllowSsh(Boolean allowSsh) {
        this.allowSsh = allowSsh;
    }

    public String getIsolationSegmentGuid() {
        return isolationSegmentGuid;
    }

    public void setIsolationSegmentGuid(String isolationSegmentGuid) {
        this.isolationSegmentGuid = isolationSegmentGuid;
    }

    public int getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(int spaceId) {
        this.spaceId = spaceId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
