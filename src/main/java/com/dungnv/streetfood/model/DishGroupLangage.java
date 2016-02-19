/**
 * @(#)DishGroupLangageBO.java 1/21/2016 9:12 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.model;

import com.dungnv.vfw5.base.model.BaseFWModel;
import com.dungnv.streetfood.dto.DishGroupLangageDTO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/21/2016 9:12 PM
 */
@Entity
@Table(name = "dish_group_langage")
public class DishGroupLangage extends BaseFWModel {

    //Fields
    private Long id;
    private String name;
    private String description;
    private Long languageCode;
    private Long dishGroupId;

    public final static String MODEL_NAME = "DishGroupLangage";
    public final static String DISH_GROUP_ID = "dishGroupId";

    public DishGroupLangage() {
        setColId("id");
        setColName("id");
        setUniqueColumn(new String[]{"id"});
    }

    public DishGroupLangage(Long id) {
        this.id = id;
    }

    public DishGroupLangage(Long id, String name, String description, Long languageCode, Long dishGroupId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.languageCode = languageCode;
        this.dishGroupId = dishGroupId;
    }

    //Getters and Setters
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
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

    @Column(name = "language_code")
    public Long getLanguageCode() {
        return this.languageCode;
    }

    public void setLanguageCode(final Long languageCode) {
        this.languageCode = languageCode;
    }

    @Column(name = "dish_group_id", nullable = false)
    public Long getDishGroupId() {
        return this.dishGroupId;
    }

    public void setDishGroupId(final Long dishGroupId) {
        this.dishGroupId = dishGroupId;
    }

    @Override
    public DishGroupLangageDTO toDTO() {
        DishGroupLangageDTO dto = new DishGroupLangageDTO(
                id == null ? null : id.toString(), name, description, languageCode == null ? null : languageCode.toString(), dishGroupId == null ? null : dishGroupId.toString()
        );
        return dto;
    }
}
