/**
 * @(#)CommentBO.java 1/25/2016 10:10 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.model;

import com.dungnv.vfw5.base.model.BaseFWModel;
import java.util.Date;
import com.dungnv.streetfood.dto.CommentDTO;
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
* @since 1/25/2016 10:10 PM
*/
@Entity
@Table(name = "comment")
public class Comment extends BaseFWModel {

    //Fields
	private Long id;
	private String content;
	private String title;
	private Long dishId;
	private Long restaurantId;
	private Long userId;
	private String ipPost;
	private Long commentStatus;
	private Date commentUpdateTime;
	private Date commentCreateTime;
	private Date commentUpdateTimeGmt;
	private Date commentCreateTimeGmt;

    //Constructors
	public Comment() {
		setColId("id");
		setColName("id");
		setUniqueColumn(new String[] {"id" });
	}
	public Comment(Long id) {
            this.id = id;
	}	
	
	public Comment(Long id, String content, String title, Long dishId, Long restaurantId, Long userId, String ipPost, Long commentStatus, Date commentUpdateTime, Date commentCreateTime, Date commentUpdateTimeGmt, Date commentCreateTimeGmt){
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

    //Getters and Setters
	
	@Id 
    @GeneratedValue
    @Column(name = "id", unique=true, nullable=false  )
    public Long getId() {
        return this.id;
    }
	public void setId(final Long id) {
		this.id = id;
	}
    @Column(name = "content", nullable=false  )
    public String getContent() {
        return this.content;
    }
	public void setContent(final String content) {
		this.content = content;
	}
    @Column(name = "title", nullable=false  )
    public String getTitle() {
        return this.title;
    }
	public void setTitle(final String title) {
		this.title = title;
	}
    @Column(name = "dish_id" , columnDefinition="Dish" )
    public Long getDishId() {
        return this.dishId;
    }
	public void setDishId(final Long dishId) {
		this.dishId = dishId;
	}
    @Column(name = "restaurant_id" , columnDefinition="Restaurant" )
    public Long getRestaurantId() {
        return this.restaurantId;
    }
	public void setRestaurantId(final Long restaurantId) {
		this.restaurantId = restaurantId;
	}
    @Column(name = "user_id", nullable=false , columnDefinition="User" )
    public Long getUserId() {
        return this.userId;
    }
	public void setUserId(final Long userId) {
		this.userId = userId;
	}
    @Column(name = "ip_post"  )
    public String getIpPost() {
        return this.ipPost;
    }
	public void setIpPost(final String ipPost) {
		this.ipPost = ipPost;
	}
    @Column(name = "comment_status"  )
    public Long getCommentStatus() {
        return this.commentStatus;
    }
	public void setCommentStatus(final Long commentStatus) {
		this.commentStatus = commentStatus;
	}
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "comment_update_time"  )
    public Date getCommentUpdateTime() {
        return this.commentUpdateTime;
    }
	public void setCommentUpdateTime(final Date commentUpdateTime) {
		this.commentUpdateTime = commentUpdateTime;
	}
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "comment_create_time"  )
    public Date getCommentCreateTime() {
        return this.commentCreateTime;
    }
	public void setCommentCreateTime(final Date commentCreateTime) {
		this.commentCreateTime = commentCreateTime;
	}
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "comment_update_time_gmt"  )
    public Date getCommentUpdateTimeGmt() {
        return this.commentUpdateTimeGmt;
    }
	public void setCommentUpdateTimeGmt(final Date commentUpdateTimeGmt) {
		this.commentUpdateTimeGmt = commentUpdateTimeGmt;
	}
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "comment_create_time_gmt"  )
    public Date getCommentCreateTimeGmt() {
        return this.commentCreateTimeGmt;
    }
	public void setCommentCreateTimeGmt(final Date commentCreateTimeGmt) {
		this.commentCreateTimeGmt = commentCreateTimeGmt;
	}
	
	@Override
	public CommentDTO toDTO() {
		CommentDTO dto = new CommentDTO(
            id==null?null:id.toString(),             content,             title,             dishId==null?null:dishId.toString(),             restaurantId==null?null:restaurantId.toString(),             userId==null?null:userId.toString(),             ipPost,             commentStatus==null?null:commentStatus.toString(),             commentUpdateTime==null?null:DateTimeUtils.convertDateToString(commentUpdateTime, ParamUtils.ddMMyyyyHHmmss),             commentCreateTime==null?null:DateTimeUtils.convertDateToString(commentCreateTime, ParamUtils.ddMMyyyyHHmmss),             commentUpdateTimeGmt==null?null:DateTimeUtils.convertDateToString(commentUpdateTimeGmt, ParamUtils.ddMMyyyyHHmmss),             commentCreateTimeGmt==null?null:DateTimeUtils.convertDateToString(commentCreateTimeGmt, ParamUtils.ddMMyyyyHHmmss)
        );
		return dto;
	}
}

