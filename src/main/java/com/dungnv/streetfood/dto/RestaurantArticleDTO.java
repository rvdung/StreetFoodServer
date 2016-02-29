/**
 * @(#)RestaurantArticleForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.dto;

import com.dungnv.vfw5.base.dto.BaseFWDTO;
import com.dungnv.streetfood.model.RestaurantArticle;
import com.dungnv.vfw5.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author dungnv
 * @version 1.0
 * @since 2/21/2016 12:49 PM
 */
@XmlRootElement(name = "RestaurantArticle")
public class RestaurantArticleDTO extends BaseFWDTO<RestaurantArticle> {
    //Fields

    private String id;
    private String articleId;
    private String articleIdName;
    private String restaurantId;
    private String restaurantIdName;
    private static long changedTime = 0;

    public final static String RESTAURANT_ID = "restaurantId";
    public final static String ARTICLE_ID = "articleId";
    public final static String MODEL_NAME = "RestaurantArticle";

    //Constructor
    public RestaurantArticleDTO() {
        setDefaultSortField("id");
    }

    public RestaurantArticleDTO(String id, String articleId, String restaurantId) {
        this.id = id;
        this.articleId = articleId;
        this.restaurantId = restaurantId;
    }
    //Getters and setters

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleIdName(String articleIdName) {
        this.articleIdName = articleIdName;
    }

    public String getArticleIdName() {
        return articleIdName;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantIdName(String restaurantIdName) {
        this.restaurantIdName = restaurantIdName;
    }

    public String getRestaurantIdName() {
        return restaurantIdName;
    }

    @Override
    public RestaurantArticle toModel() {
        RestaurantArticle model = new RestaurantArticle(
                !StringUtils.validString(id) ? null
                : Long.valueOf(id),
                !StringUtils.validString(articleId) ? null
                : Long.valueOf(articleId),
                !StringUtils.validString(restaurantId) ? null
                : Long.valueOf(restaurantId));
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
