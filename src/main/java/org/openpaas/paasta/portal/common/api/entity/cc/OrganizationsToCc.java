package org.openpaas.paasta.portal.common.api.entity.cc;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by indra on 2018-02-12.
 */
@Entity
@Table(name = "organizations")
public class OrganizationsToCc {

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

    @Column(name = "billing_enabled", nullable = false)
    private Boolean billingEnabled;

    @Column(name = "quota_definition_id", nullable = false)
    private String quotaDefinitionId;

    @Column(name = "status")
    private String status;

    @Column(name = "default_isolation_segment_guid")
    private String defaultIsolationSegmentGuid;

    @Formula("COALESCE((SELECT COUNT(s.organization_id) FROM spaces s WHERE s.organization_id = id GROUP BY s.organization_id), 0)")
    private int spaceCount;

    @Formula("COALESCE((SELECT COUNT(a.id) FROM apps a WHERE a.space_guid IN (SELECT s.guid FROM spaces s WHERE s.organization_id = id)), 0)")
    private int applicationCount;

    @Formula("COALESCE((SELECT COUNT(*) FROM organizations_users ou WHERE ou.organization_id = id GROUP BY ou.organization_id), 0)")
    private int userCount;



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

    public Boolean getBillingEnabled() {
        return billingEnabled;
    }

    public void setBillingEnabled(Boolean billingEnabled) {
        this.billingEnabled = billingEnabled;
    }

    public String getQuotaDefinitionId() {
        return quotaDefinitionId;
    }

    public void setQuotaDefinitionId(String quotaDefinitionId) {
        this.quotaDefinitionId = quotaDefinitionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDefaultIsolationSegmentGuid() {
        return defaultIsolationSegmentGuid;
    }

    public void setDefaultIsolationSegmentGuid(String defaultIsolationSegmentGuid) {
        this.defaultIsolationSegmentGuid = defaultIsolationSegmentGuid;
    }

    public int getSpaceCount() {
        return spaceCount;
    }

    public void setSpaceCount(int spaceCount) {
        this.spaceCount = spaceCount;
    }

    public int getApplicationCount() {
        return applicationCount;
    }

    public void setApplicationCount(int applicationCount) {
        this.applicationCount = applicationCount;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }
}
