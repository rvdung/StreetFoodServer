/**
 * @(#)RestaurantDishDetailBO.java 2/21/2016 12:49 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.model;

import com.dungnv.vfw5.base.model.BaseFWModel;
import javax.persistence.*;
import com.dungnv.streetfood.dto.RestaurantDishDetailDTO;

/**
 * @author dungnv
 * @version 1.0
 * @since 2/21/2016 12:49 PM
 */
@Entity
@Table(name = "restaurant_dish_detail")
public class RestaurantDishDetail extends BaseFWModel {

    //Fields
    private Long id;
    private Long dishId;
    private Long restaurantId;

    //Constructors
    public RestaurantDishDetail() {
        setColId("id");
        setColName("id");
        setUniqueColumn(new String[]{"id"});
    }

    public RestaurantDishDetail(Long id) {
        this.id = id;
    }

    public RestaurantDishDetail(Long id, Long dishId, Long restaurantId) {
        this.id = id;
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

    @Column(name = "dish_id", nullable = false)
    public Long getDishId() {
        return this.dishId;
    }

    public void setDishId(final Long dishId) {
        this.dishId = dishId;
    }

    @Column(name = "restaurant_id", nullable = false)
    public Long getRestaurantId() {
        return this.restaurantId;
    }

    public void setRestaurantId(final Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public RestaurantDishDetailDTO toDTO() {
        RestaurantDishDetailDTO dto = new RestaurantDishDetailDTO(
                id == null ? null : id.toString(), dishId == null ? null : dishId.toString(), restaurantId == null ? null : restaurantId.toString()
        );
        return dto;
    }
}
