/**
 * @(#)TagsBO.java 1/24/2016 8:00 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.model;

import com.dungnv.vfw5.base.model.BaseFWModel;
import com.dungnv.streetfood.dto.TagsDTO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/24/2016 8:00 PM
 */
@Entity
@Table(name = "tags")
public class Tags extends BaseFWModel {

    //Fields
    private Long id;
    private String name;
    private String description;
    private Long hit;
    private Long dishId;
    private Long restaurantId;
    
    public static final String NAME = "name";

    //Constructors
    public Tags() {
        setColId("id");
        setColName("id");
        setUniqueColumn(new String[]{"id"});
    }

    public Tags(Long id) {
        this.id = id;
    }

    public Tags(Long id, String name, String description, Long hit, Long dishId, Long restaurantId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hit = hit;
        this.dishId = dishId;
        this.restaurantId = restaurantId;
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

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Column(name = "hit")
    public Long getHit() {
        return this.hit;
    }

    public void setHit(final Long hit) {
        this.hit = hit;
    }

    @Column(name = "dish_id")
    public Long getDishId() {
        return this.dishId;
    }

    public void setDishId(final Long dishId) {
        this.dishId = dishId;
    }

    @Column(name = "restaurant_id")
    public Long getRestaurantId() {
        return this.restaurantId;
    }

    public void setRestaurantId(final Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public TagsDTO toDTO() {
        TagsDTO dto = new TagsDTO(
                id == null ? null : id.toString(), name, description, hit == null ? null : hit.toString(), dishId == null ? null : dishId.toString(), restaurantId == null ? null : restaurantId.toString()
        );
        return dto;
    }
}
