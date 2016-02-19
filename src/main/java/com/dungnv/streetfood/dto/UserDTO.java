/**
 * @(#)UserForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */

package com.dungnv.streetfood.dto;


import com.dungnv.vfw5.base.dto.BaseFWDTO;
import com.dungnv.vfw5.base.utils.DateTimeUtils;
import com.dungnv.streetfood.model.User;
import com.dungnv.vfw5.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;
/**
* @author dungnv
* @version 1.0
* @since 1/31/2016 10:31 PM
*/
@XmlRootElement(name = "User")
public class UserDTO extends BaseFWDTO<User>{    
	//Fields
    private String id;
    private String fullname;
    private String email;
    private String birthday;
    private String username;
    private String password;
    private String fistname;
    private String lastname;
    private String userStatus;
    private String userUpdateTime;
    private String userCreateTime;
    private String userUpdateTimeGmt;
    private String userCreateTimeGmt;
    private String sysRoleId;
    private String sysRoleIdName;
    private String iduserToken;
    private String isActive;
    private String refreshToken;
    private static long changedTime = 0;
    //Constructor
	public UserDTO() {
		setDefaultSortField("id");	
	}
	public UserDTO(String id, String fullname, String email, String birthday, String username, String password, String fistname, String lastname, String userStatus, String userUpdateTime, String userCreateTime, String userUpdateTimeGmt, String userCreateTimeGmt, String sysRoleId, String iduserToken, String isActive, String refreshToken) {
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
	//Getters and setters
    
	public void setId(String id) {
        this.id = id;
    }
	public String getId() {		
        return id;		
    }
    
	public void setFullname(String fullname) {
        this.fullname = fullname;
    }
	public String getFullname() {		
        return fullname;		
    }
    
	public void setEmail(String email) {
        this.email = email;
    }
	public String getEmail() {		
        return email;		
    }
    
	public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
	public String getBirthday() {		
        return birthday;		
    }
    
	public void setUsername(String username) {
        this.username = username;
    }
	public String getUsername() {		
        return username;		
    }
    
	public void setPassword(String password) {
        this.password = password;
    }
	public String getPassword() {		
        return password;		
    }
    
	public void setFistname(String fistname) {
        this.fistname = fistname;
    }
	public String getFistname() {		
        return fistname;		
    }
    
	public void setLastname(String lastname) {
        this.lastname = lastname;
    }
	public String getLastname() {		
        return lastname;		
    }
    
	public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
	public String getUserStatus() {		
        return userStatus;		
    }
    
	public void setUserUpdateTime(String userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }
	public String getUserUpdateTime() {		
        return userUpdateTime;		
    }
    
	public void setUserCreateTime(String userCreateTime) {
        this.userCreateTime = userCreateTime;
    }
	public String getUserCreateTime() {		
        return userCreateTime;		
    }
    
	public void setUserUpdateTimeGmt(String userUpdateTimeGmt) {
        this.userUpdateTimeGmt = userUpdateTimeGmt;
    }
	public String getUserUpdateTimeGmt() {		
        return userUpdateTimeGmt;		
    }
    
	public void setUserCreateTimeGmt(String userCreateTimeGmt) {
        this.userCreateTimeGmt = userCreateTimeGmt;
    }
	public String getUserCreateTimeGmt() {		
        return userCreateTimeGmt;		
    }
    
	public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId;
    }
	public String getSysRoleId() {		
        return sysRoleId;		
    }
    
	public void setSysRoleIdName(String sysRoleIdName) {
        this.sysRoleIdName = sysRoleIdName;
    }
	public String getSysRoleIdName() {		
        return sysRoleIdName;		
    }
	public void setIduserToken(String iduserToken) {
        this.iduserToken = iduserToken;
    }
	public String getIduserToken() {		
        return iduserToken;		
    }
    
	public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
	public String getIsActive() {		
        return isActive;		
    }
    
	public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
	public String getRefreshToken() {		
        return refreshToken;		
    }
    
	@Override
	public User toModel() {
        try {
		User model = new User(
            !StringUtils.validString(id)?null:
                Long.valueOf(id), 
            fullname, 
            email, 
            !StringUtils.validString(birthday)?null:
                DateTimeUtils.convertStringToDate(birthday), 
            username, 
            password, 
            fistname, 
            lastname, 
            !StringUtils.validString(userStatus)?null:
                Long.valueOf(userStatus), 
            !StringUtils.validString(userUpdateTime)?null:
                DateTimeUtils.convertStringToDate(userUpdateTime), 
            !StringUtils.validString(userCreateTime)?null:
                DateTimeUtils.convertStringToDate(userCreateTime), 
            !StringUtils.validString(userUpdateTimeGmt)?null:
                DateTimeUtils.convertStringToDate(userUpdateTimeGmt), 
            !StringUtils.validString(userCreateTimeGmt)?null:
                DateTimeUtils.convertStringToDate(userCreateTimeGmt), 
            !StringUtils.validString(sysRoleId)?null:
                Long.valueOf(sysRoleId), 
            !StringUtils.validString(iduserToken)?null:
                Long.valueOf(iduserToken), 
            !StringUtils.validString(isActive)?null:
                Long.valueOf(isActive), 
            refreshToken);
		return model;
        } catch (Exception e) {
            return null;
        }
	}
	
    @Override
    public Long getFWModelId() {
        return !StringUtils.validString(id)?null:Long.valueOf(id);
    }

    @Override
    public String catchName() {
        return getId().toString();
    }
    
   
}
