package org.openpaas.paasta.portal.common.api.entity.portal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by SEJI on 2018-02-20.
 */
@Entity
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
}

