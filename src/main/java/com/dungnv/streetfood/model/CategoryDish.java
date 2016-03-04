/**
 * @(#)CategoryDishBO.java 1/25/2016 10:09 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.model;

import com.dungnv.vfw5.base.model.BaseFWModel;
import com.dungnv.streetfood.dto.CategoryDishDTO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/25/2016 10:09 PM
 */
@Entity
@Table(name = "category_dish")
public class CategoryDish extends BaseFWModel {

    //Fields
    private Long id;
    private Long categoryId;
    private Long dishId;

    //Constructors
    public CategoryDish() {
        setColId("id");
        setColName("id");
        setUniqueColumn(new String[]{"id"});
    }

    public CategoryDish(Long id) {
        this.id = id;
    }

    public CategoryDish(Long id, Long categoryId, Long dishId) {
        this.id = id;
        this.categoryId = categoryId;
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

    @Column(name = "category_id", nullable = false)
    public Long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(final Long categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "dish_id", nullable = false)
    public Long getDishId() {
        return this.dishId;
    }

    public void setDishId(final Long dishId) {
        this.dishId = dishId;
    }

    @Override
    public CategoryDishDTO toDTO() {
        CategoryDishDTO dto = new CategoryDishDTO(
                id == null ? null : id.toString(), categoryId == null ? null : categoryId.toString(), dishId == null ? null : dishId.toString()
        );
        return dto;
    }
}
