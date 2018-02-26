package org.openpaas.paasta.portal.common.api.entity.uaa;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by SEJI on 2018-02-20.
 */
@Entity
@Table(name = "users")

public class Users {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private int active;

    @Column(name = "phonenumber", nullable = false)
    private long phoneNumber;

    @Column(name = "authorities", nullable = false)
    private String authorities;

    @Column(name = "verified", nullable = false)
    private int verified;

    @Column(name = "origin", nullable = false)
    private  String origin;

    @Column(name = "external_id", nullable = false)
    private  String externalId;

    @Column(name = "identity_zone_id", nullable = false)
    private  String identityZoneId;

    @Column(name = "salt", nullable = false)
    private  String salt;

    @UpdateTimestamp
    @Column(name = "passwd_lastmodified", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date passwdLastmodified;

    @Column(name = "legacy_verification_behavior", nullable = false)
    private  long legacyVerification_behavior;

    @Column(name = "passwd_change_required", nullable = false)
    private  long passwdChange_required;

    @Column(name = "last_logon_success_time", nullable = false)
    private  long lastLogonSuccessTime;

    @Column(name = "previous_logon_success_time", nullable = false)
    private  long previousLogonSuccessTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
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

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public int getVerified() {
        return verified;
    }

    public void setVerified(int verified) {
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    public Date getPasswdLastmodified() {
        return passwdLastmodified;
    }

    public void setPasswdLastmodified(Date passwdLastmodified) {
        this.passwdLastmodified = passwdLastmodified;
    }

    public long getLegacyVerification_behavior() {
        return legacyVerification_behavior;
    }

    public void setLegacyVerification_behavior(long legacyVerification_behavior) {
        this.legacyVerification_behavior = legacyVerification_behavior;
    }

    public long getPasswdChange_required() {
        return passwdChange_required;
    }

    public void setPasswdChange_required(long passwdChange_required) {
        this.passwdChange_required = passwdChange_required;
    }

    public long getLastLogonSuccessTime() {
        return lastLogonSuccessTime;
    }

    public void setLastLogonSuccessTime(long lastLogonSuccessTime) {
        this.lastLogonSuccessTime = lastLogonSuccessTime;
    }

    public long getPreviousLogonSuccessTime() {
        return previousLogonSuccessTime;
    }

    public void setPreviousLogonSuccessTime(long previousLogonSuccessTime) {
        this.previousLogonSuccessTime = previousLogonSuccessTime;
    }

}

