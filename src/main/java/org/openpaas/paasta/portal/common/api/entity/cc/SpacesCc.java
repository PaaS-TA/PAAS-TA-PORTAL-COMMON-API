package org.openpaas.paasta.portal.common.api.entity.cc;

import javax.persistence.*;

/**
 * Created by indra on 2018-02-13.
 */
@Entity
@NamedNativeQueries({
    @NamedNativeQuery(name = "SpacesCc.allList",
            query = "SELECT " +
                    "    space_1.id, " +
                    "    space_2.applicationCount, " +
                    "    space_1.name " +
                    "FROM " +
                    "(SELECT * FROM spaces s1 WHERE 1=1" +
                    ") space_1 " +
                    "LEFT OUTER JOIN " +
                    "(SELECT " +
                    "    DISTINCT (space_all.space_id) AS spaceId, " +
                    "    (SELECT COUNT(a.id) FROM apps a WHERE a.id = space_all.space_id) AS applicationCount, " +
                    "    (SELECT s2.name FROM spaces s2 WHERE s2.id = space_all.space_id) AS spaceName " +
                    "FROM " +
                    "(SELECT * FROM spaces_auditors " +
                    "    UNION ALL " +
                    "SELECT * FROM spaces_developers " +
                    "    UNION ALL " +
                    "SELECT * FROM spaces_managers) space_all " +
                    "WHERE 1=1 " +
                    ") space_2 " +
                    "ON space_1.id = space_2.spaceId " +
                    "ORDER BY space_1.id ASC",
            resultClass = SpacesCc.class
    ),
    @NamedNativeQuery(name = "SpacesCc.allByOrganizationIdList",
            query = "SELECT MAX(spaceId) as id ,spaceNm as name ,SUM(appCnt) as applicationCount " +
                    "FROM ( " +
                    "   SELECT s.id spaceId, s.name spaceNm, CASE WHEN a.guid is NULL THEN 0 ELSE 1 END appCnt " +
                    "   FROM spaces s full outer join apps a on s.guid = a.space_guid" +
                    "   WHERE 1=1 " +
                    "   AND s.organization_id = ? " +
//                    "   AND EXISTS (SELECT organization_id FROM " +
//                    "                   (SELECT organization_id FROM organizations_managers " +
//                    "                          UNION ALL " +
//                    "                      SELECT organization_id FROM organizations_billing_managers " +
//                    "                          UNION ALL " +
//                    "                      SELECT organization_id FROM organizations_auditors " +
//                    "                   ) org_role_all " +
//                    "                WHERE org_role_all.organization_id = ?) " +
                    ") SPACE_INFO " +
                    "GROUP BY spaceNm ",
            resultClass = SpacesCc.class
    )
})
@Table(name = "spaces")
public class SpacesCc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

//    @Column(name = "guid", nullable = false)
//    private String guid;
//
//    @CreationTimestamp
//    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdAt;
//
//    @UpdateTimestamp
//    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date updatedAt;
//
    @Column(name = "name", nullable = false)
    private String name;

    private Integer applicationCount;
//
//    @Column(name = "organization_id", nullable = false)
//    private Integer organizationId;
//
//    @Column(name = "space_quota_definition_id")
//    private Integer spaceQuotaDefinitionId;

//    @Column(name = "allow_ssh", columnDefinition = "TINYINT", length = 1)
//    @Type(type = "org.hibernate.type.NumericBooleanType")
//    private Boolean allowSsh;

//    @Column(name = "isolation_segment_guid")
//    private String isolationSegmentGuid;
//
//    @Column(name = "space_id")
//    private Integer spaceId;
//
//    @Column(name = "user_id")
//    private Integer userId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public String getGuid() {
//        return guid;
//    }
//
//    public void setGuid(String guid) {
//        this.guid = guid;
//    }
//
//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public Date getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(Date updatedAt) {
//        this.updatedAt = updatedAt;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getApplicationCount() {
        return applicationCount;
    }

    public void setApplicationCount(Integer applicationCount) {
        this.applicationCount = applicationCount;
    }

    //    public int getOrganizationId() {
//        return organizationId;
//    }
//
//    public void setOrganizationId(int organizationId) {
//        this.organizationId = organizationId;
//    }
//
//    public int getSpaceQuotaDefinitionId() {
//        return spaceQuotaDefinitionId;
//    }
//
//    public void setSpaceQuotaDefinitionId(int spaceQuotaDefinitionId) {
//        this.spaceQuotaDefinitionId = spaceQuotaDefinitionId;
//    }

//    public Boolean getAllowSsh() {
//        return allowSsh;
//    }
//
//    public void setAllowSsh(Boolean allowSsh) {
//        this.allowSsh = allowSsh;
//    }

//    public String getIsolationSegmentGuid() {
//        return isolationSegmentGuid;
//    }
//
//    public void setIsolationSegmentGuid(String isolationSegmentGuid) {
//        this.isolationSegmentGuid = isolationSegmentGuid;
//    }

//    public int getSpaceId() {
//        return spaceId;
//    }
//
//    public void setSpaceId(int spaceId) {
//        this.spaceId = spaceId;
//    }

//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
}
