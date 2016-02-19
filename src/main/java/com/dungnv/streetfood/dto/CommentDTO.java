/**
 * @(#)CommentForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */

package com.dungnv.streetfood.dto;


import com.dungnv.vfw5.base.dto.BaseFWDTO;
import com.dungnv.vfw5.base.utils.DateTimeUtils;
import com.dungnv.vfw5.base.utils.StringUtils;
import com.dungnv.streetfood.model.Comment;
import com.dungnv.vfw5.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;
/**
* @author dungnv
* @version 1.0
* @since 1/25/2016 10:10 PM
*/
@XmlRootElement(name = "Comment")
public class CommentDTO extends BaseFWDTO<Comment>{    
	//Fields
    private String id;
    private String content;
    private String title;
    private String dishId;
    private String dishIdName;
    private String restaurantId;
    private String restaurantIdName;
    private String userId;
    private String userIdName;
    private String ipPost;
    private String commentStatus;
    private String commentUpdateTime;
    private String commentCreateTime;
    private String commentUpdateTimeGmt;
    private String commentCreateTimeGmt;
    
    //Constructor
	public CommentDTO() {
		setDefaultSortField("id");	
	}
	public CommentDTO(String id, String content, String title, String dishId, String restaurantId, String userId, String ipPost, String commentStatus, String commentUpdateTime, String commentCreateTime, String commentUpdateTimeGmt, String commentCreateTimeGmt) {
		this.id = id;
		this.content = content;
		this.title = title;
		this.dishId = dishId;
		this.restaurantId = restaurantId;
		this.userId = userId;
		this.ipPost = ipPost;
		this.commentStatus = commentStatus;
		this.commentUpdateTime = commentUpdateTime;
		this.commentCreateTime = commentCreateTime;
		this.commentUpdateTimeGmt = commentUpdateTimeGmt;
		this.commentCreateTimeGmt = commentCreateTimeGmt;
	}	
	//Getters and setters
    
	public void setId(String id) {
        this.id = id;
    }
	public String getId() {		
        return id;		
    }
    
	public void setContent(String content) {
        this.content = content;
    }
	public String getContent() {		
        return content;		
    }
    
	public void setTitle(String title) {
        this.title = title;
    }
	public String getTitle() {		
        return title;		
    }
    
	public void setDishId(String dishId) {
        this.dishId = dishId;
    }
	public String getDishId() {		
        return dishId;		
    }
    
	public void setDishIdName(String dishIdName) {
        this.dishIdName = dishIdName;
    }
	public String getDishIdName() {		
        return dishIdName;		
    }
	public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
	public String getRestaurantId() {		
        return restaurantId;		
    }
    
	public void setRestaurantIdName(String restaurantIdName) {
        this.restaurantIdName = restaurantIdName;
    }
	public String getRestaurantIdName() {		
        return restaurantIdName;		
    }
	public void setUserId(String userId) {
        this.userId = userId;
    }
	public String getUserId() {		
        return userId;		
    }
    
	public void setUserIdName(String userIdName) {
        this.userIdName = userIdName;
    }
	public String getUserIdName() {		
        return userIdName;		
    }
	public void setIpPost(String ipPost) {
        this.ipPost = ipPost;
    }
	public String getIpPost() {		
        return ipPost;		
    }
    
	public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }
	public String getCommentStatus() {		
        return commentStatus;		
    }
    
	public void setCommentUpdateTime(String commentUpdateTime) {
        this.commentUpdateTime = commentUpdateTime;
    }
	public String getCommentUpdateTime() {		
        return commentUpdateTime;		
    }
    
	public void setCommentCreateTime(String commentCreateTime) {
        this.commentCreateTime = commentCreateTime;
    }
	public String getCommentCreateTime() {		
        return commentCreateTime;		
    }
    
	public void setCommentUpdateTimeGmt(String commentUpdateTimeGmt) {
        this.commentUpdateTimeGmt = commentUpdateTimeGmt;
    }
	public String getCommentUpdateTimeGmt() {		
        return commentUpdateTimeGmt;		
    }
    
	public void setCommentCreateTimeGmt(String commentCreateTimeGmt) {
        this.commentCreateTimeGmt = commentCreateTimeGmt;
    }
	public String getCommentCreateTimeGmt() {		
        return commentCreateTimeGmt;		
    }
    
	@Override
	public Comment toModel() {
        try {
		Comment model = new Comment(
            !StringUtils.validString(id)?null:
                Long.valueOf(id), 
            content, 
            title, 
            !StringUtils.validString(dishId)?null:
                Long.valueOf(dishId), 
            !StringUtils.validString(restaurantId)?null:
                Long.valueOf(restaurantId), 
            !StringUtils.validString(userId)?null:
                Long.valueOf(userId), 
            ipPost, 
            !StringUtils.validString(commentStatus)?null:
                Long.valueOf(commentStatus), 
            !StringUtils.validString(commentUpdateTime)?null:
                DateTimeUtils.convertStringToDate(commentUpdateTime), 
            !StringUtils.validString(commentCreateTime)?null:
                DateTimeUtils.convertStringToDate(commentCreateTime), 
            !StringUtils.validString(commentUpdateTimeGmt)?null:
                DateTimeUtils.convertStringToDate(commentUpdateTimeGmt), 
            !StringUtils.validString(commentCreateTimeGmt)?null:
                DateTimeUtils.convertStringToDate(commentCreateTimeGmt));
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
