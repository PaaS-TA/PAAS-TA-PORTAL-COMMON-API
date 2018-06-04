package org.openpaas.paasta.portal.common.api.entity.portal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by SEJI on 2018-02-20.
 */
@Entity
@NamedNativeQueries({@NamedNativeQuery(name = "UserDetail.getUserInfoList", query = "SELECT " + "    ud.userId, " + "    ud.status " + "   (SELECT cd.value FROM code_detail cd WHERE cd.key = ud.status AND cd.group_id = 'USER_STATUS') AS statusValue " + "   (SELECT " + "       COUNT(1) " + "   FROM user_detail ud2 " + "   WHERE 1=1 " + "   CASE WHEN userId THEN IS NOT NULL " + "       AND ud2.user_id = #{userId}" + "   CASE WHEN searchKeyword IS NOT NULL " + "       AND (LOWER(ud2.user_name) LIKE concat('%', #{searchKeyword},'%') OR LOWER(ud2.user_id) LIKE concat('%', #{searchKeyword},'%'))" + "   AS totalCount," + "   COALESCE (ud.tell_phone, '-') AS tellPhone," + "   COALESCE(ud.zip_code, '-') AS zipCode," + "   COALESCE(ud.address, '-') AS address," + "   COALESCE(ud.address_detail, '-') AS addressDetail," + "   COALESCE(ud.user_name, '-') AS userName," + "   COALESCE(ud.admin_yn, '-') AS adminYn," + "   COALESCE(ud.refresh_token, '-') AS refreshToken" + " FROM user_detail ud " + "WHERE 1=1 " + "   CASE WHEN searchKeyword IS NOT NULL " + "AND ud2.user_id = #{userId}" + "   CASE WHEN searchKeyword IS NOT NULL " + "AND (LOWER(ud.user_name) LIKE concat('%', #{searchKeyword},'%') OR LOWER(ud.user_id) LIKE concat('%', #{searchKeyword},'%'))" + "ORDER BY ud.user_id ASC", resultClass = UserDetail.class)})
@Table(name = "user_detail")

public class UserDetail {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "tell_phone", nullable = false)
    private String tellPhone;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "address_detail", nullable = false)
    private String addressDetail;

    @NotNull
    @Column(name = "admin_yn", nullable = false)
    private String adminYn;

    @Column(name = "img_path", nullable = false)
    private String imgPath;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @Column(name = "auth_access_time", nullable = false)
    private Date authAccessTime;

    @Column(name = "auth_access_cnt", nullable = false)
    private int authAccessCnt;

    @Transient
    private String searchKeyword;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTellPhone() {
        return tellPhone;
    }

    public void setTellPhone(String tellPhone) {
        this.tellPhone = tellPhone;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getAdminYn() {
        return adminYn;
    }

    public void setAdminYn(String adminYn) {
        this.adminYn = adminYn;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getAuthAccessTime() {
        return authAccessTime;
    }

    public void setAuthAccessTime(Date authAccessTime) {
        this.authAccessTime = authAccessTime;
    }

    public int getAuthAccessCnt() {
        return authAccessCnt;
    }

    public void setAuthAccessCnt(int authAccessCnt) {
        this.authAccessCnt = authAccessCnt;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    @Override
    public String toString() {
        return "UserDetail{" + "userId='" + userId + '\'' + ", userName='" + userName + '\'' + ", status='" + status + '\'' + ", tellPhone='" + tellPhone + '\'' + ", zipCode='" + zipCode + '\'' + ", address='" + address + '\'' + ", addressDetail='" + addressDetail + '\'' + ", adminYn='" + adminYn + '\'' + ", imgPath='" + imgPath + '\'' + ", refreshToken='" + refreshToken + '\'' + ", authAccessTime=" + authAccessTime + ", authAccessCnt=" + authAccessCnt + ", searchKeyword='" + searchKeyword + '\'' + '}';
    }
}

