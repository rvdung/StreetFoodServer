/**
 * @(#)DishBO.java 1/25/2016 10:01 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.model;

import com.dungnv.vfw5.base.model.BaseFWModel;
import java.util.Date;
import com.dungnv.streetfood.dto.DishDTO;
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
 * @since 1/25/2016 10:01 PM
 */
@Entity
@Table(name = "dish")
public class Dish extends BaseFWModel {

    //Fields
    private Long id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private Long dishStatus;
    private Date dishUpdateTime;
    private Date dishCreateTime;
    private Date dishUpdateTimeGmt;
    private Date dishCreateTimeGmt;
    private Long viewCount;
    private Long commentCount;
    private Long shareCount;
    private Long rating;

    //Constructors
    public Dish() {
        setColId("id");
        setColName("id");
        setUniqueColumn(new String[]{"id"});
    }

    public Dish(Long id) {
        this.id = id;
    }

    public Dish(Long id, String name, String shortDescription, String longDescription, Long dishStatus, Date dishUpdateTime, Date dishCreateTime, Date dishUpdateTimeGmt, Date dishCreateTimeGmt, Long viewCount, Long commentCount, Long shareCount, Long rating) {
        this.id = id;
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.dishStatus = dishStatus;
        this.dishUpdateTime = dishUpdateTime;
        this.dishCreateTime = dishCreateTime;
        this.dishUpdateTimeGmt = dishUpdateTimeGmt;
        this.dishCreateTimeGmt = dishCreateTimeGmt;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.shareCount = shareCount;
        this.rating = rating;
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

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Column(name = "short_description")
    public String getShortDescription() {
        return this.shortDescription;
    }

    public void setShortDescription(final String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Column(name = "long_description")
    public String getLongDescription() {
        return this.longDescription;
    }

    public void setLongDescription(final String longDescription) {
        this.longDescription = longDescription;
    }

    @Column(name = "dish_status")
    public Long getDishStatus() {
        return this.dishStatus;
    }

    public void setDishStatus(final Long dishStatus) {
        this.dishStatus = dishStatus;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "dish_update_time")
    public Date getDishUpdateTime() {
        return this.dishUpdateTime;
    }

    public void setDishUpdateTime(final Date dishUpdateTime) {
        this.dishUpdateTime = dishUpdateTime;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "dish_create_time")
    public Date getDishCreateTime() {
        return this.dishCreateTime;
    }

    public void setDishCreateTime(final Date dishCreateTime) {
        this.dishCreateTime = dishCreateTime;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "dish_update_time_gmt")
    public Date getDishUpdateTimeGmt() {
        return this.dishUpdateTimeGmt;
    }

    public void setDishUpdateTimeGmt(final Date dishUpdateTimeGmt) {
        this.dishUpdateTimeGmt = dishUpdateTimeGmt;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "dish_create_time_gmt")
    public Date getDishCreateTimeGmt() {
        return this.dishCreateTimeGmt;
    }

    public void setDishCreateTimeGmt(final Date dishCreateTimeGmt) {
        this.dishCreateTimeGmt = dishCreateTimeGmt;
    }

    @Column(name = "view_count")
    public Long getViewCount() {
        return this.viewCount;
    }

    public void setViewCount(final Long viewCount) {
        this.viewCount = viewCount;
    }

    @Column(name = "comment_count")
    public Long getCommentCount() {
        return this.commentCount;
    }

    public void setCommentCount(final Long commentCount) {
        this.commentCount = commentCount;
    }

    @Column(name = "share_count")
    public Long getShareCount() {
        return this.shareCount;
    }

    public void setShareCount(final Long shareCount) {
        this.shareCount = shareCount;
    }

    @Column(name = "rating")
    public Long getRating() {
        return this.rating;
    }

    public void setRating(final Long rating) {
        this.rating = rating;
    }

    @Override
    public DishDTO toDTO() {
        DishDTO dto = new DishDTO(
                id == null ? null : id.toString(), name, shortDescription, longDescription, dishStatus == null ? null : dishStatus.toString(), dishUpdateTime == null ? null : DateTimeUtils.convertDateToString(dishUpdateTime, ParamUtils.ddMMyyyyHHmmss), dishCreateTime == null ? null : DateTimeUtils.convertDateToString(dishCreateTime, ParamUtils.ddMMyyyyHHmmss), dishUpdateTimeGmt == null ? null : DateTimeUtils.convertDateToString(dishUpdateTimeGmt, ParamUtils.ddMMyyyyHHmmss), dishCreateTimeGmt == null ? null : DateTimeUtils.convertDateToString(dishCreateTimeGmt, ParamUtils.ddMMyyyyHHmmss), viewCount == null ? null : viewCount.toString(), commentCount == null ? null : commentCount.toString(), shareCount == null ? null : shareCount.toString(), rating == null ? null : rating.toString()
        );
        return dto;
    }
}
