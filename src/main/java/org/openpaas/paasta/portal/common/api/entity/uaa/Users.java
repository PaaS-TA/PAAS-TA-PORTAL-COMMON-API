package org.openpaas.paasta.portal.common.api.entity.uaa;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by SEJI on 2018-02-20.
 */
@Entity
@Table(name = "users")

public class Users {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @CreationTimestamp
    @Column(name = "created", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @UpdateTimestamp
    @Column(name = "lastmodified", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodified;

    @Column(name = "version", nullable = false)
    private int version;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "givenname", nullable = false)
    private String givenName;

    @Column(name = "familyname", nullable = false)
    private String familyName;

    @Column(name = "active", nullable = false)
    private String active;

    @Column(name = "phonenumber", nullable = false)
    private String phoneNumber;

    @Column(name = "authorities", nullable = false)
    private String authorities;

    @Column(name = "verified", nullable = false)
    private String verified;

    @Column(name = "origin", nullable = false)
    private String origin;

    @Column(name = "external_id", nullable = false)
    private String externalId;

    @Column(name = "identity_zone_id", nullable = false)
    private String identityZoneId;

    @Column(name = "salt", nullable = false)
    private String salt;

    @UpdateTimestamp
    @Column(name = "passwd_lastmodified", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date passwdLastmodified;

    @Column(name = "legacy_verification_behavior", nullable = false)
    private String legacyVerification_behavior;

    @Column(name = "passwd_change_required", nullable = false)
    private String passwdChange_required;
    //
    @Column(name = "last_logon_success_time", nullable = false)
    private BigInteger lastLogonSuccessTime;

    @Column(name = "previous_logon_success_time", nullable = false)
    private BigInteger previousLogonSuccessTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(Date lastmodified) {
        this.lastmodified = lastmodified;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getIdentityZoneId() {
        return identityZoneId;
    }

    public void setIdentityZoneId(String identityZoneId) {
        this.identityZoneId = identityZoneId;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getPasswdLastmodified() {
        return passwdLastmodified;
    }

    public void setPasswdLastmodified(Date passwdLastmodified) {
        this.passwdLastmodified = passwdLastmodified;
    }

    public String getLegacyVerification_behavior() {
        return legacyVerification_behavior;
    }

    public void setLegacyVerification_behavior(String legacyVerification_behavior) {
        this.legacyVerification_behavior = legacyVerification_behavior;
    }

    public String getPasswdChange_required() {
        return passwdChange_required;
    }

    public void setPasswdChange_required(String passwdChange_required) {
        this.passwdChange_required = passwdChange_required;
    }

    public BigInteger getLastLogonSuccessTime() {
        return lastLogonSuccessTime;
    }

    public void setLastLogonSuccessTime(BigInteger lastLogonSuccessTime) {
        this.lastLogonSuccessTime = lastLogonSuccessTime;
    }

    public BigInteger getPreviousLogonSuccessTime() {
        return previousLogonSuccessTime;
    }

    public void setPreviousLogonSuccessTime(BigInteger previousLogonSuccessTime) {
        this.previousLogonSuccessTime = previousLogonSuccessTime;
    }

    @Override
    public String toString() {
        return "Users{" + "id='" + id + '\'' + ", created=" + created + ", lastmodified=" + lastmodified + ", version=" + version + ", userName='" + userName + '\'' + ", password='" + password + '\'' + ", email='" + email + '\'' + ", givenName='" + givenName + '\'' + ", familyName='" + familyName + '\'' + ", active='" + active + '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", authorities='" + authorities + '\'' + ", verified='" + verified + '\'' + ", origin='" + origin + '\'' + ", externalId='" + externalId + '\'' + ", identityZoneId='" + identityZoneId + '\'' + ", salt='" + salt + '\'' + ", passwdLastmodified=" + passwdLastmodified + ", legacyVerification_behavior='" + legacyVerification_behavior + '\'' + ", passwdChange_required='" + passwdChange_required + '\'' + ", lastLogonSuccessTime=" + lastLogonSuccessTime + ", previousLogonSuccessTime=" + previousLogonSuccessTime + '}';
    }
}

