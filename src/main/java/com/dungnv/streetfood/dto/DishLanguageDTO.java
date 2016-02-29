/**
 * @(#)DishLanguageForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.dto;

import com.dungnv.vfw5.base.dto.BaseFWDTO;
import com.dungnv.streetfood.model.DishLanguage;
import com.dungnv.vfw5.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/25/2016 10:02 PM
 */
@XmlRootElement(name = "DishLanguage")
public class DishLanguageDTO extends BaseFWDTO<DishLanguage> {
    //Fields

    private String id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private String dishId;
    private String dishIdName;
    private String languageCode;

    public final static String DISH_ID = "dishId";
    public final static String MODEL_NAME = "DishLanguage";

    //Constructor
    public DishLanguageDTO() {
        setDefaultSortField("id");
    }

    public DishLanguageDTO(String id, String name, String shortDescription, String longDescription, String dishId, String languageCode) {
        this.id = id;
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.dishId = dishId;
        this.languageCode = languageCode;
    }
    //Getters and setters

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

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public String getDishId() {
        return dishId;
    }

    public void setDishIdName(String dishIdName) {
        this.dishIdName = dishIdName;
    }

    public String getDishIdName() {
        return dishIdName;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    @Override
    public DishLanguage toModel() {
        DishLanguage model = new DishLanguage(
                !StringUtils.validString(id) ? null
                : Long.valueOf(id),
                name,
                shortDescription,
                longDescription,
                !StringUtils.validString(dishId) ? null
                : Long.valueOf(dishId),
                !StringUtils.validString(languageCode) ? null
                : Long.valueOf(languageCode));
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

}
