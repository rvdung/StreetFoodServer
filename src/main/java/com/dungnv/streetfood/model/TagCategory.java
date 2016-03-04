/**
 * @(#)TagCategoryBO.java 1/24/2016 7:34 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.model;

import com.dungnv.vfw5.base.model.BaseFWModel;
import com.dungnv.streetfood.dto.TagCategoryDTO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/24/2016 7:34 PM
 */
@Entity
@Table(name = "tag_category")
public class TagCategory extends BaseFWModel {

    //Fields
    private Long id;
    private Long tagId;
    private Long categoryId;

    //Constructors
    public TagCategory() {
        setColId("id");
        setColName("id");
        setUniqueColumn(new String[]{"id"});
    }

    public TagCategory(Long id) {
        this.id = id;
    }

    public TagCategory(Long id, Long tagId, Long categoryId) {
        this.id = id;
        this.tagId = tagId;
        this.categoryId = categoryId;
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

    @Column(name = "tag_id", nullable = false)
    public Long getTagId() {
        return this.tagId;
    }

    public void setTagId(final Long tagId) {
        this.tagId = tagId;
    }

    @Column(name = "category_id", nullable = false)
    public Long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(final Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public TagCategoryDTO toDTO() {
        TagCategoryDTO dto = new TagCategoryDTO(
                id == null ? null : id.toString(), tagId == null ? null : tagId.toString(), categoryId == null ? null : categoryId.toString()
        );
        return dto;
    }
}
