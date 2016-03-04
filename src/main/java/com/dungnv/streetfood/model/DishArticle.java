/**
 * @(#)DishArticleBO.java 2/29/2016 10:24 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.model;

import com.dungnv.vfw5.base.model.BaseFWModel;
import javax.persistence.*;
import com.dungnv.streetfood.dto.DishArticleDTO;

/**
 * @author dungnv
 * @version 1.0
 * @since 2/29/2016 10:24 PM
 */
@Entity
@Table(name = "dish_article")
public class DishArticle extends BaseFWModel {

    //Fields
    private Long id;
    private Long articleId;
    private Long dishId;

    //Constructors
    public DishArticle() {
        setColId("id");
        setColName("id");
        setUniqueColumn(new String[]{"id"});
    }

    public DishArticle(Long id) {
        this.id = id;
    }

    public DishArticle(Long id, Long articleId, Long dishId) {
        this.id = id;
        this.articleId = articleId;
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

    @Column(name = "article_id", nullable = false)
    public Long getArticleId() {
        return this.articleId;
    }

    public void setArticleId(final Long articleId) {
        this.articleId = articleId;
    }

    @Column(name = "dish_id", nullable = false)
    public Long getDishId() {
        return this.dishId;
    }

    public void setDishId(final Long dishId) {
        this.dishId = dishId;
    }

    @Override
    public DishArticleDTO toDTO() {
        DishArticleDTO dto = new DishArticleDTO(
                id == null ? null : id.toString(), articleId == null ? null : articleId.toString(), dishId == null ? null : dishId.toString()
        );
        return dto;
    }
}
