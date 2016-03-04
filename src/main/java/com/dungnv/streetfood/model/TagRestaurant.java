/**
 * @(#)TagRestaurantBO.java 2/21/2016 12:58 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.model;

import com.dungnv.vfw5.base.model.BaseFWModel;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.dungnv.streetfood.dto.TagRestaurantDTO;

/**
 * @author dungnv
 * @version 1.0
 * @since 2/21/2016 12:58 PM
 */
@Entity
@Table(name = "tag_restaurant")
public class TagRestaurant extends BaseFWModel {

    //Fields
    private Long id;
    private Long tagId;
    private Long restaurantId;

    //Constructors
    public TagRestaurant() {
        setColId("id");
        setColName("id");
        setUniqueColumn(new String[]{"id"});
    }

    public TagRestaurant(Long id) {
        this.id = id;
    }

    public TagRestaurant(Long id, Long tagId, Long restaurantId) {
        this.id = id;
        this.tagId = tagId;
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

    @Column(name = "tag_id", nullable = false)
    public Long getTagId() {
        return this.tagId;
    }

    public void setTagId(final Long tagId) {
        this.tagId = tagId;
    }

    @Column(name = "restaurant_id", nullable = false)
    public Long getRestaurantId() {
        return this.restaurantId;
    }

    public void setRestaurantId(final Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public TagRestaurantDTO toDTO() {
        TagRestaurantDTO dto = new TagRestaurantDTO(
                id == null ? null : id.toString(), tagId == null ? null : tagId.toString(), restaurantId == null ? null : restaurantId.toString()
        );
        return dto;
    }
}
