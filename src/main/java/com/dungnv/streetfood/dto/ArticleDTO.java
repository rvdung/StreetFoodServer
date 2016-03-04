/**
 * @(#)ArticleForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.dto;

import com.dungnv.vfw5.base.dto.BaseFWDTO;
import com.dungnv.vfw5.base.utils.DateTimeUtils;
import com.dungnv.streetfood.model.Article;
import com.dungnv.vfw5.base.utils.StringUtils;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/21/2016 12:48 AM
 */
@XmlRootElement(name = "Article")
public class ArticleDTO extends BaseFWDTO<Article> {
    //Fields

    private String id;
    private String title;
    private String content;
    private String shortContent;
    private String updateTime;
    private String updateTimeGmt;
    private String viewCount;

    private String restaurantId;
    private String notRestaurantId;
    private String dishId;
    private String notDishId;
    private String isGetOnlyIdentified;
    private List<ArticleLanguageDTO> listLanguage;
    private List<String> listTag;
    private List<String> listImgUrl;
    private String imageId;
    private String imageUrl;
    public final static String ID = "id";

    //Constructor
    public ArticleDTO() {
        setDefaultSortField("id");
    }

    public ArticleDTO(String id, String title, String content, String shortContent, String updateTime, String updateTimeGmt, String viewCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.shortContent = shortContent;
        this.updateTime = updateTime;
        this.updateTimeGmt = updateTimeGmt;
        this.viewCount = viewCount;
    }
    //Getters and setters

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getNotRestaurantId() {
        return notRestaurantId;
    }

    public void setNotRestaurantId(String notRestaurantId) {
        this.notRestaurantId = notRestaurantId;
    }

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public String getNotDishId() {
        return notDishId;
    }

    public void setNotDishId(String notDishId) {
        this.notDishId = notDishId;
    }

    public String getIsGetOnlyIdentified() {
        return isGetOnlyIdentified;
    }

    public void setIsGetOnlyIdentified(String isGetOnlyIdentified) {
        this.isGetOnlyIdentified = isGetOnlyIdentified;
    }

    public List<ArticleLanguageDTO> getListLanguage() {
        return listLanguage;
    }

    public void setListLanguage(List<ArticleLanguageDTO> listLanguage) {
        this.listLanguage = listLanguage;
    }

    public List<String> getListTag() {
        return listTag;
    }

    public void setListTag(List<String> listTag) {
        this.listTag = listTag;
    }

    public List<String> getListImgUrl() {
        return listImgUrl;
    }

    public void setListImgUrl(List<String> listImgUrl) {
        this.listImgUrl = listImgUrl;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTimeGmt(String updateTimeGmt) {
        this.updateTimeGmt = updateTimeGmt;
    }

    public String getUpdateTimeGmt() {
        return updateTimeGmt;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getViewCount() {
        return viewCount;
    }

    @Override
    public Article toModel() {
        try {
            Article model = new Article(
                    !StringUtils.validString(id) ? null
                    : Long.valueOf(id),
                    title,
                    content,
                    shortContent,
                    !StringUtils.validString(updateTime) ? null
                    : DateTimeUtils.convertStringToDate(updateTime),
                    !StringUtils.validString(updateTimeGmt) ? null
                    : DateTimeUtils.convertStringToDate(updateTimeGmt),
                    !StringUtils.validString(viewCount) ? null
                    : Long.valueOf(viewCount));
            return model;
        } catch (Exception e) {
            return null;
        }
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
