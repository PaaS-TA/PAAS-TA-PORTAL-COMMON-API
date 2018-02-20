package org.openpaas.paasta.portal.common.api.entity.cc;

import org.hibernate.annotations.Formula;

import javax.persistence.*;

/**
 * Created by indra on 2018-02-13.
 */
@Entity
@Table(name = "spacesunion_view")
public class SpaceunionViewCc {

    @Id
    @Column(name = "space_id")
    private int spaceId;

    @Column(name = "user_id")
    private int userId;

    @Formula("DISTINCT (space_id)")
    private int disSpaceId;

    @Formula("(SELECT COUNT(a.id) FROM apps a WHERE a.id = space_id)")
    private int applicationCount;

    @Formula("(SELECT s2.name FROM spaces s2 WHERE s2.id = space_id)")
    private String spaceName;



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

    public int getDisSpaceId() {
        return disSpaceId;
    }

    public void setDisSpaceId(int disSpaceId) {
        this.disSpaceId = disSpaceId;
    }

    public int getApplicationCount() {
        return applicationCount;
    }

    public void setApplicationCount(int applicationCount) {
        this.applicationCount = applicationCount;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }
}
