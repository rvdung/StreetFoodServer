/**
 * @(#)RestaurantLanguageBO.java 2/21/2016 11:30 AM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.model;

import com.dungnv.vfw5.base.model.BaseFWModel;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.dungnv.streetfood.dto.RestaurantLanguageDTO;

/**
 * @author dungnv
 * @version 1.0
 * @since 2/21/2016 11:30 AM
 */
@Entity
@Table(name = "restaurant_language")
public class RestaurantLanguage extends BaseFWModel {

    //Fields
    private Long id;
    private String name;
    private String address;
    private String introduce;
    private Long languageCode;
    private Long restaurantId;

    public static final String RESTAURANT_ID = "restaurantId";

    //Constructors
    public RestaurantLanguage() {
        setColId("id");
        setColName("id");
        setUniqueColumn(new String[]{"id"});
    }

    public RestaurantLanguage(Long id) {
        this.id = id;
    }

    public RestaurantLanguage(Long id, String name, String address, String introduce, Long languageCode, Long restaurantId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.introduce = introduce;
        this.languageCode = languageCode;
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

    @Column(name = "address")
    public String getAddress() {
        return this.address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    @Column(name = "introduce")
    public String getIntroduce() {
        return this.introduce;
    }

    public void setIntroduce(final String introduce) {
        this.introduce = introduce;
    }

    @Column(name = "language_code", nullable = false)
    public Long getLanguageCode() {
        return this.languageCode;
    }

    public void setLanguageCode(final Long languageCode) {
        this.languageCode = languageCode;
    }

    @Column(name = "restaurant_id", nullable = false)
    public Long getRestaurantId() {
        return this.restaurantId;
    }

    public void setRestaurantId(final Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public RestaurantLanguageDTO toDTO() {
        RestaurantLanguageDTO dto = new RestaurantLanguageDTO(
                id == null ? null : id.toString(), name, address, introduce, languageCode == null ? null : languageCode.toString(), restaurantId == null ? null : restaurantId.toString()
        );
        return dto;
    }
}
