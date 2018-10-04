package org.openpaas.paasta.portal.common.api.entity.portal;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by indra on 2018-06-05.
 */
@Entity
@Table(name = "invite_user")
public class InviteUser {

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "gubun", nullable = false)
    private String gubun;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "org_guid", nullable = false)
    private String orgGuid;

    @UpdateTimestamp
    @Column(name = "created", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "invite_name", nullable = false)
    private String invitename;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGubun() {
        return gubun;
    }

    public void setGubun(String gubun) {
        this.gubun = gubun;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getOrgGuid() {
        return orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    public String getInviteName(){ return this.invitename; }

    public void setInvitename(String invitename){ this.invitename = invitename; }

    @Override
    public String toString() {
        return "InviteUser{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", gubun='" + gubun + '\'' +
                ", role='" + role + '\'' +
                ", orgGuid='" + orgGuid + '\'' +
                ", created=" + created +
                ", token='" + token + '\'' +
                ", invitename='" + invitename + '\'' +
                '}';
    }
}
