/**
 * @(#)TagDishBO.java 1/26/2016 9:17 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.model;

import com.dungnv.vfw5.base.model.BaseFWModel;
import com.dungnv.streetfood.dto.TagDishDTO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/26/2016 9:17 PM
 */
@Entity
@Table(name = "tag_dish")
public class TagDish extends BaseFWModel {

    //Fields
    private Long id;
    private Long tagId;
    private Long dishId;

    //Constructors
    public TagDish() {
        setColId("id");
        setColName("id");
        setUniqueColumn(new String[]{"id"});
    }

    public TagDish(Long id) {
        this.id = id;
    }

    public TagDish(Long id, Long tagId, Long dishId) {
        this.id = id;
        this.tagId = tagId;
        this.dishId = dishId;
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

    @Column(name = "dish_id", nullable = false)
    public Long getDishId() {
        return this.dishId;
    }

    public void setDishId(final Long dishId) {
        this.dishId = dishId;
    }

    @Override
    public TagDishDTO toDTO() {
        TagDishDTO dto = new TagDishDTO(
                id == null ? null : id.toString(), tagId == null ? null : tagId.toString(), dishId == null ? null : dishId.toString()
        );
        return dto;
    }
}
