package org.openpaas.paasta.portal.common.api.entity.portal;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by SEJI on 2018-03-07.
 */
@Entity
@Table(name = "invite_org_space")
public class InviteOrgSpace {

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "gubun", nullable = false)
    private String gubun;

    @Column(name = "invite_id", nullable = false)
    private int inviteId;

    @Column(name = "role_name", nullable = false)
    private String roleName;

    @Column(name = "invite_user_id", nullable = false)
    private String inviteUserId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @CreationTimestamp
    @Column(name = "create_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "access_cnt", nullable = false)
    private int accessCnt;

    @Column(name = "invite_name", nullable = false)
    private String inviteName;

    @Column(name = "setyn", nullable = false)
    private String  setyn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getGubun() {
        return gubun;
    }

    public void setGubun(String gubun) {
        this.gubun = gubun;
    }

    public int getInviteId() {
        return inviteId;
    }

    public void setInviteId(int inviteId) {
        this.inviteId = inviteId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getInviteUserId() {
        return inviteUserId;
    }

    public void setInviteUserId(String inviteUserId) {
        this.inviteUserId = inviteUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    public Date getCreateTime() {
        return createTime == null ? null : new Date(createTime.getTime());
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime == null ? null : new Date(createTime.getTime());
    }

    public int getAccessCnt() {
        return accessCnt;
    }

    public void setAccessCnt(int accessCnt) {
        this.accessCnt = accessCnt;
    }

    public String getInviteName() {
        return inviteName;
    }

    public void setInviteName(String inviteName) {
        this.inviteName = inviteName;
    }

    public String getSetyn() {
        return setyn;
    }

    public void setSetyn(String setyn) {
        this.setyn = setyn;
    }

}
