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

    public final static String ID = "id";

    private String viewCountFrom;
    private String commentCountFrom;
    private String shareCountFrom;
    private String ratingFrom;

    private String viewCountTo;
    private String commentCountTo;
    private String shareCountTo;
    private String ratingTo;

    private String categoryId;
    private String notCategoryId;
    private String restaurantId;
    private String notRestaurantId;
    private String articleId;
    private String notArticleId;
    private String isGetOnlyIdentified;
    private List<CategoryDTO> listCategory;
    private List<DishLanguageDTO> listLanguage;
    private List<String> listTag;
    private List<String> listImgUrl;
    private List<String> listLocale;
    private List<String> listNotLocale;
    private String imageId;
    private String imageUrl;

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

    public List<String> getListLocale() {
        return listLocale;
    }

    public void setListLocale(List<String> listLocale) {
        this.listLocale = listLocale;
    }

    public List<String> getListNotLocale() {
        return listNotLocale;
    }

    public void setListNotLocale(List<String> listNotLocale) {
        this.listNotLocale = listNotLocale;
    }

    public String getViewCountFrom() {
        return viewCountFrom;
    }

    public void setViewCountFrom(String viewCountFrom) {
        this.viewCountFrom = viewCountFrom;
    }

    public String getCommentCountFrom() {
        return commentCountFrom;
    }

    public void setCommentCountFrom(String commentCountFrom) {
        this.commentCountFrom = commentCountFrom;
    }

    public String getShareCountFrom() {
        return shareCountFrom;
    }

    public void setShareCountFrom(String shareCountFrom) {
        this.shareCountFrom = shareCountFrom;
    }

    public String getRatingFrom() {
        return ratingFrom;
    }

    public void setRatingFrom(String ratingFrom) {
        this.ratingFrom = ratingFrom;
    }

    public String getViewCountTo() {
        return viewCountTo;
    }

    public void setViewCountTo(String viewCountTo) {
        this.viewCountTo = viewCountTo;
    }

    public String getCommentCountTo() {
        return commentCountTo;
    }

    public void setCommentCountTo(String commentCountTo) {
        this.commentCountTo = commentCountTo;
    }

    public String getShareCountTo() {
        return shareCountTo;
    }

    public void setShareCountTo(String shareCountTo) {
        this.shareCountTo = shareCountTo;
    }

    public String getRatingTo() {
        return ratingTo;
    }

    public void setRatingTo(String ratingTo) {
        this.ratingTo = ratingTo;
    }

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

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getNotArticleId() {
        return notArticleId;
    }

    public void setNotArticleId(String notArticleId) {
        this.notArticleId = notArticleId;
    }

    public String getIsGetOnlyIdentified() {
        return isGetOnlyIdentified;
    }

    public void setIsGetOnlyIdentified(String isGetOnlyIdentified) {
        this.isGetOnlyIdentified = isGetOnlyIdentified;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getNotCategoryId() {
        return notCategoryId;
    }

    public void setNotCategoryId(String notCategoryId) {
        this.notCategoryId = notCategoryId;
    }

    public List<CategoryDTO> getListCategory() {
        return listCategory;
    }

    public void setListCategory(List<CategoryDTO> listCategory) {
        this.listCategory = listCategory;
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
                    : Double.valueOf(rating));
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
