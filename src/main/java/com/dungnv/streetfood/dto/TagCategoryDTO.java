/**
 * @(#)TagCategoryForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.dto;

import com.dungnv.vfw5.base.dto.BaseFWDTO;
import com.dungnv.streetfood.model.TagCategory;
import com.dungnv.vfw5.base.utils.StringUtils;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/24/2016 7:34 PM
 */
@XmlRootElement(name = "TagCategory")
public class TagCategoryDTO extends BaseFWDTO<TagCategory> {
    //Fields

    private String id;
    private String tagId;
    private String tagIdName;
    private String categoryId;
    private String categoryIdName;
    
    public final static String MODEL_NAME = "TagCategory";
    public final static String CATEGORY_ID = "categoryId";

    //Constructor
    public TagCategoryDTO() {
        setDefaultSortField("id");
    }

    public TagCategoryDTO(String id, String tagId, String categoryId) {
        this.id = id;
        this.tagId = tagId;
        this.categoryId = categoryId;
    }
    //Getters and setters

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagIdName(String tagIdName) {
        this.tagIdName = tagIdName;
    }

    public String getTagIdName() {
        return tagIdName;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryIdName(String categoryIdName) {
        this.categoryIdName = categoryIdName;
    }

    public String getCategoryIdName() {
        return categoryIdName;
    }

    @Override
    public TagCategory toModel() {
        TagCategory model = new TagCategory(
                !StringUtils.validString(id) ? null
                : Long.valueOf(id),
                !StringUtils.validString(tagId) ? null
                : Long.valueOf(tagId),
                !StringUtils.validString(categoryId) ? null
                : Long.valueOf(categoryId));
        return model;
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
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.tagId);
        hash = 97 * hash + Objects.hashCode(this.categoryId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TagCategoryDTO other = (TagCategoryDTO) obj;
        if (!Objects.equals(this.tagId, other.tagId)) {
            return false;
        }
        if (!Objects.equals(this.categoryId, other.categoryId)) {
            return false;
        }
        return true;
    }
    
    

}
