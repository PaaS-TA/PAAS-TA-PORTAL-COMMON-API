package org.openpaas.paasta.portal.common.api.entity.cc;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@Table(name = "spaces")
public class SpacesToCc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

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
    private Integer organizationId;

    @Column(name = "space_quota_definition_id")
    private Integer spaceQuotaDefinitionId;

    @Column(name = "allow_ssh", columnDefinition = "TINYINT", length = 1)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean allowSsh;

    @Column(name = "isolation_segment_guid")
    private String isolationSegmentGuid;

    @Column(name = "space_id")
    private Integer spaceId;

    @Column(name = "user_id")
    private Integer userId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Date getCreatedAt() {
        return createdAt == null ? null : new Date(createdAt.getTime());
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt == null ? null : new Date(createdAt.getTime());
    }

    public Date getUpdatedAt() {
        return updatedAt == null ? null : new Date(updatedAt.getTime());
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt == null ? null : new Date(updatedAt.getTime());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getSpaceQuotaDefinitionId() {
        return spaceQuotaDefinitionId;
    }

    public void setSpaceQuotaDefinitionId(Integer spaceQuotaDefinitionId) {
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

    public Integer getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Integer spaceId) {
        this.spaceId = spaceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
