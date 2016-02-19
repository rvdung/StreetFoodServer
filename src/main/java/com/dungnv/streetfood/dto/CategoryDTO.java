/**
 * @(#)CategoryForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.dto;

import com.dungnv.vfw5.base.dto.BaseFWDTO;
import com.dungnv.vfw5.base.utils.DateTimeUtils;
import com.dungnv.streetfood.model.Category;
import com.dungnv.vfw5.base.utils.StringUtils;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/22/2016 9:48 PM
 */
@XmlRootElement(name = "Category")
public class CategoryDTO extends BaseFWDTO<Category> {
    //Fields

    private String id;
    private String name;
    private String description;
    private String categoryStatus;
    private String categoryUpdateTime;
    private String categoryCreateTime;
    private String categoryUpdateTimeGmt;
    private String categoryCreateTimeGmt;

    //extend
    private List<DishGroupLangageDTO> listLanguage;
    private List<String> listTag;
    private List<String> listImgUrl;
    private String imageId;
    private String imageUrl;

    //Constructor
    public CategoryDTO() {
        setDefaultSortField("id");
    }

    public CategoryDTO(String id, String name, String description, String categoryStatus, String categoryUpdateTime, String categoryCreateTime, String categoryUpdateTimeGmt, String categoryCreateTimeGmt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryStatus = categoryStatus;
        this.categoryUpdateTime = categoryUpdateTime;
        this.categoryCreateTime = categoryCreateTime;
        this.categoryUpdateTimeGmt = categoryUpdateTimeGmt;
        this.categoryCreateTimeGmt = categoryCreateTimeGmt;
    }
    //Getters and setters

    public List<String> getListImgUrl() {
        return listImgUrl;
    }

    public void setListImgUrl(List<String> listImgUrl) {
        this.listImgUrl = listImgUrl;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCategoryStatus(String categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    public String getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryUpdateTime(String categoryUpdateTime) {
        this.categoryUpdateTime = categoryUpdateTime;
    }

    public String getCategoryUpdateTime() {
        return categoryUpdateTime;
    }

    public void setCategoryCreateTime(String categoryCreateTime) {
        this.categoryCreateTime = categoryCreateTime;
    }

    public String getCategoryCreateTime() {
        return categoryCreateTime;
    }

    public void setCategoryUpdateTimeGmt(String categoryUpdateTimeGmt) {
        this.categoryUpdateTimeGmt = categoryUpdateTimeGmt;
    }

    public String getCategoryUpdateTimeGmt() {
        return categoryUpdateTimeGmt;
    }

    public void setCategoryCreateTimeGmt(String categoryCreateTimeGmt) {
        this.categoryCreateTimeGmt = categoryCreateTimeGmt;
    }

    public String getCategoryCreateTimeGmt() {
        return categoryCreateTimeGmt;
    }

    @Override
    public Category toModel() {
        try {
            Category model = new Category(
                    !StringUtils.validString(id) ? null
                    : Long.valueOf(id),
                    name,
                    description,
                    !StringUtils.validString(categoryStatus) ? null
                    : Long.valueOf(categoryStatus),
                    !StringUtils.validString(categoryUpdateTime) ? null
                    : DateTimeUtils.convertStringToDate(categoryUpdateTime),
                    !StringUtils.validString(categoryCreateTime) ? null
                    : DateTimeUtils.convertStringToDate(categoryCreateTime),
                    !StringUtils.validString(categoryUpdateTimeGmt) ? null
                    : DateTimeUtils.convertStringToDate(categoryUpdateTimeGmt),
                    !StringUtils.validString(categoryCreateTimeGmt) ? null
                    : DateTimeUtils.convertStringToDate(categoryCreateTimeGmt));
            return model;
        } catch (Exception e) {
            return null;
        }
    }

    public List<DishGroupLangageDTO> getListLanguage() {
        return listLanguage;
    }

    public void setListLanguage(List<DishGroupLangageDTO> listLanguage) {
        this.listLanguage = listLanguage;
    }

    public List<String> getListTag() {
        return listTag;
    }

    public void setListTag(List<String> listTag) {
        this.listTag = listTag;
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

    @Override
    public Long getFWModelId() {
        return !StringUtils.validString(id) ? null : Long.valueOf(id);
    }

    @Override
    public String catchName() {
        return getId().toString();
    }

    @Override
    public String toString() {
        return "CategoryDTO{" + "id=" + id + ", name=" + name + ", categoryStatus=" + categoryStatus + ", categoryUpdateTime=" + categoryUpdateTime + ", categoryCreateTime=" + categoryCreateTime + ", categoryUpdateTimeGmt=" + categoryUpdateTimeGmt + ", categoryCreateTimeGmt=" + categoryCreateTimeGmt + '}';
    }
}
