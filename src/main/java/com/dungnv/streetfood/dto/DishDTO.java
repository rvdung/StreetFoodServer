/**
 * @(#)DishForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.dto;

import com.dungnv.vfw5.base.dto.BaseFWDTO;
import com.dungnv.vfw5.base.utils.DateTimeUtils;
import com.dungnv.streetfood.model.Dish;
import com.dungnv.vfw5.base.utils.StringUtils;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/25/2016 10:01 PM
 */
@XmlRootElement(name = "Dish")
public class DishDTO extends BaseFWDTO<Dish> {
    //Fields

    private String id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private String dishStatus;
    private String dishUpdateTime;
    private String dishCreateTime;
    private String dishUpdateTimeGmt;
    private String dishCreateTimeGmt;
    private String viewCount;
    private String commentCount;
    private String shareCount;
    private String rating;
    private String imgId;

    private List<DishLanguageDTO> listLanguage;
    private List<String> listTag;

    //Constructor
    public DishDTO() {
        setDefaultSortField("id");
    }

    public DishDTO(String id, String name, String shortDescription, String longDescription, String dishStatus, String dishUpdateTime, String dishCreateTime, String dishUpdateTimeGmt, String dishCreateTimeGmt, String viewCount, String commentCount, String shareCount, String rating) {
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
    //Getters and setters

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setDishStatus(String dishStatus) {
        this.dishStatus = dishStatus;
    }

    public String getDishStatus() {
        return dishStatus;
    }

    public void setDishUpdateTime(String dishUpdateTime) {
        this.dishUpdateTime = dishUpdateTime;
    }

    public String getDishUpdateTime() {
        return dishUpdateTime;
    }

    public void setDishCreateTime(String dishCreateTime) {
        this.dishCreateTime = dishCreateTime;
    }

    public String getDishCreateTime() {
        return dishCreateTime;
    }

    public void setDishUpdateTimeGmt(String dishUpdateTimeGmt) {
        this.dishUpdateTimeGmt = dishUpdateTimeGmt;
    }

    public String getDishUpdateTimeGmt() {
        return dishUpdateTimeGmt;
    }

    public void setDishCreateTimeGmt(String dishCreateTimeGmt) {
        this.dishCreateTimeGmt = dishCreateTimeGmt;
    }

    public String getDishCreateTimeGmt() {
        return dishCreateTimeGmt;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setShareCount(String shareCount) {
        this.shareCount = shareCount;
    }

    public String getShareCount() {
        return shareCount;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    @Override
    public Dish toModel() {
        try {
            Dish model = new Dish(
                    !StringUtils.validString(id) ? null
                    : Long.valueOf(id),
                    name,
                    shortDescription,
                    longDescription,
                    !StringUtils.validString(dishStatus) ? null
                    : Long.valueOf(dishStatus),
                    !StringUtils.validString(dishUpdateTime) ? null
                    : DateTimeUtils.convertStringToDate(dishUpdateTime),
                    !StringUtils.validString(dishCreateTime) ? null
                    : DateTimeUtils.convertStringToDate(dishCreateTime),
                    !StringUtils.validString(dishUpdateTimeGmt) ? null
                    : DateTimeUtils.convertStringToDate(dishUpdateTimeGmt),
                    !StringUtils.validString(dishCreateTimeGmt) ? null
                    : DateTimeUtils.convertStringToDate(dishCreateTimeGmt),
                    !StringUtils.validString(viewCount) ? null
                    : Long.valueOf(viewCount),
                    !StringUtils.validString(commentCount) ? null
                    : Long.valueOf(commentCount),
                    !StringUtils.validString(shareCount) ? null
                    : Long.valueOf(shareCount),
                    !StringUtils.validString(rating) ? null
                    : Long.valueOf(rating));
            return model;
        } catch (Exception e) {
            return null;
        }
    }

    public List<DishLanguageDTO> getListLanguage() {
        return listLanguage;
    }

    public void setListLanguage(List<DishLanguageDTO> listLanguage) {
        this.listLanguage = listLanguage;
    }

    public List<String> getListTag() {
        return listTag;
    }

    public void setListTag(List<String> listTag) {
        this.listTag = listTag;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    @Override
    public Long getFWModelId() {
        return !StringUtils.validString(id) ? null : Long.valueOf(id);
    }

    @Override
    public String catchName() {
        return getId().toString();
    }

}
