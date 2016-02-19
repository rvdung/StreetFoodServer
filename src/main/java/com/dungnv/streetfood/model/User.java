/**
 * @(#)UserBO.java 1/31/2016 10:31 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.model;

import com.dungnv.vfw5.base.model.BaseFWModel;
import java.util.Date;
import com.dungnv.streetfood.dto.UserDTO;
import com.dungnv.vfw5.base.utils.DateTimeUtils;
import com.dungnv.vfw5.base.utils.ParamUtils;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/31/2016 10:31 PM
 */
@Entity
@Table(name = "user")
public class User extends BaseFWModel {

    //Fields
    private Long id;
    private String fullname;
    private String email;
    private Date birthday;
    private String username;
    private String password;
    private String fistname;
    private String lastname;
    private Long userStatus;
    private Date userUpdateTime;
    private Date userCreateTime;
    private Date userUpdateTimeGmt;
    private Date userCreateTimeGmt;
    private Long sysRoleId;
    private Long iduserToken;
    private Long isActive;
    private String refreshToken;
    
    public final static String USERNAME ="username";
    public final static String PASSWORD ="password";

    //Constructors
    public User() {
        setColId("id");
        setColName("id");
        setUniqueColumn(new String[]{"id"});
    }

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String fullname, String email, Date birthday, String username, String password, String fistname, String lastname, Long userStatus, Date userUpdateTime, Date userCreateTime, Date userUpdateTimeGmt, Date userCreateTimeGmt, Long sysRoleId, Long iduserToken, Long isActive, String refreshToken) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.birthday = birthday;
        this.username = username;
        this.password = password;
        this.fistname = fistname;
        this.lastname = lastname;
        this.userStatus = userStatus;
        this.userUpdateTime = userUpdateTime;
        this.userCreateTime = userCreateTime;
        this.userUpdateTimeGmt = userUpdateTimeGmt;
        this.userCreateTimeGmt = userCreateTimeGmt;
        this.sysRoleId = sysRoleId;
        this.iduserToken = iduserToken;
        this.isActive = isActive;
        this.refreshToken = refreshToken;
    }

    //Getters and Setters
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(name = "fullname", nullable = false)
    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(final String fullname) {
        this.fullname = fullname;
    }

    @Column(name = "email")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "birthday")
    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(final Date birthday) {
        this.birthday = birthday;
    }

    @Column(name = "username", nullable = false)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @Column(name = "fistname")
    public String getFistname() {
        return this.fistname;
    }

    public void setFistname(final String fistname) {
        this.fistname = fistname;
    }

    @Column(name = "lastname")
    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    @Column(name = "user_status")
    public Long getUserStatus() {
        return this.userStatus;
    }

    public void setUserStatus(final Long userStatus) {
        this.userStatus = userStatus;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "user_update_time")
    public Date getUserUpdateTime() {
        return this.userUpdateTime;
    }

    public void setUserUpdateTime(final Date userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "user_create_time")
    public Date getUserCreateTime() {
        return this.userCreateTime;
    }

    public void setUserCreateTime(final Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "user_update_time_gmt")
    public Date getUserUpdateTimeGmt() {
        return this.userUpdateTimeGmt;
    }

    public void setUserUpdateTimeGmt(final Date userUpdateTimeGmt) {
        this.userUpdateTimeGmt = userUpdateTimeGmt;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "user_create_time_gmt")
    public Date getUserCreateTimeGmt() {
        return this.userCreateTimeGmt;
    }

    public void setUserCreateTimeGmt(final Date userCreateTimeGmt) {
        this.userCreateTimeGmt = userCreateTimeGmt;
    }

    @Column(name = "sys_role_id", nullable = false, columnDefinition = "SysRole")
    public Long getSysRoleId() {
        return this.sysRoleId;
    }

    public void setSysRoleId(final Long sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    @Column(name = "iduser_token")
    public Long getIduserToken() {
        return this.iduserToken;
    }

    public void setIduserToken(final Long iduserToken) {
        this.iduserToken = iduserToken;
    }

    @Column(name = "is_active")
    public Long getIsActive() {
        return this.isActive;
    }

    public void setIsActive(final Long isActive) {
        this.isActive = isActive;
    }

    @Column(name = "refresh_token")
    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public UserDTO toDTO() {
        UserDTO dto = new UserDTO(
                id == null ? null : id.toString(), fullname, email, birthday == null ? null : DateTimeUtils.convertDateToString(birthday, ParamUtils.ddMMyyyyHHmmss), username, password, fistname, lastname, userStatus == null ? null : userStatus.toString(), userUpdateTime == null ? null : DateTimeUtils.convertDateToString(userUpdateTime, ParamUtils.ddMMyyyyHHmmss), userCreateTime == null ? null : DateTimeUtils.convertDateToString(userCreateTime, ParamUtils.ddMMyyyyHHmmss), userUpdateTimeGmt == null ? null : DateTimeUtils.convertDateToString(userUpdateTimeGmt, ParamUtils.ddMMyyyyHHmmss), userCreateTimeGmt == null ? null : DateTimeUtils.convertDateToString(userCreateTimeGmt, ParamUtils.ddMMyyyyHHmmss), sysRoleId == null ? null : sysRoleId.toString(), iduserToken == null ? null : iduserToken.toString(), isActive == null ? null : isActive.toString(), refreshToken
        );
        return dto;
    }
}
