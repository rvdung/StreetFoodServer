/**
 * @(#)RestaurantArticleBO.java 2/21/2016 12:49 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.model;

import com.dungnv.vfw5.base.model.BaseFWModel;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.dungnv.streetfood.dto.RestaurantArticleDTO;

/**
 * @author dungnv
 * @version 1.0
 * @since 2/21/2016 12:49 PM
 */
@Entity
@Table(name = "restaurant_article")
public class RestaurantArticle extends BaseFWModel {

    //Fields
    private Long id;
    private Long articleId;
    private Long restaurantId;

    //Constructors
    public RestaurantArticle() {
        setColId("id");
        setColName("id");
        setUniqueColumn(new String[]{"id"});
    }

    public RestaurantArticle(Long id) {
        this.id = id;
    }

    public RestaurantArticle(Long id, Long articleId, Long restaurantId) {
        this.id = id;
        this.articleId = articleId;
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

    @Column(name = "article_id", nullable = false, columnDefinition = "Article")
    public Long getArticleId() {
        return this.articleId;
    }

    public void setArticleId(final Long articleId) {
        this.articleId = articleId;
    }

    @Column(name = "restaurant_id", nullable = false, columnDefinition = "Restaurant")
    public Long getRestaurantId() {
        return this.restaurantId;
    }

    public void setRestaurantId(final Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public RestaurantArticleDTO toDTO() {
        RestaurantArticleDTO dto = new RestaurantArticleDTO(
                id == null ? null : id.toString(), articleId == null ? null : articleId.toString(), restaurantId == null ? null : restaurantId.toString()
        );
        return dto;
    }
}
