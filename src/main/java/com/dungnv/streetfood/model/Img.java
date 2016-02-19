/**
 * @(#)ImgBO.java 1/25/2016 10:05 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.model;

import com.dungnv.vfw5.base.model.BaseFWModel;
import com.dungnv.streetfood.dto.ImgDTO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/25/2016 10:05 PM
 */
@Entity
@Table(name = "img")
public class Img extends BaseFWModel {

    //Fields
    private Long id;
    private String url;
    private Long dishId;
    private Long restaurantId;
    private Long dishGroupId;
    private String width;
    private String heigh;
    private Long slideShowId;
    private Long articleId;
    private String type;
    private Long order;

    //Constructors
    public Img() {
        setColId("id");
        setColName("id");
        setUniqueColumn(new String[]{"id"});
    }

    public Img(Long id) {
        this.id = id;
    }

    public Img(Long id, String url, Long dishId, Long restaurantId, Long dishGroupId, String width, String heigh, Long slideShowId, Long articleId, String type, Long order) {
        this.id = id;
        this.url = url;
        this.dishId = dishId;
        this.restaurantId = restaurantId;
        this.dishGroupId = dishGroupId;
        this.width = width;
        this.heigh = heigh;
        this.slideShowId = slideShowId;
        this.articleId = articleId;
        this.type = type;
        this.order = order;
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

    @Column(name = "url")
    public String getUrl() {
        return this.url;
    }

    public void setUrl(final String url) {
        this.url = url;
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

    @Column(name = "dish_group_id")
    public Long getDishGroupId() {
        return this.dishGroupId;
    }

    public void setDishGroupId(final Long dishGroupId) {
        this.dishGroupId = dishGroupId;
    }

    @Column(name = "width")
    public String getWidth() {
        return this.width;
    }

    public void setWidth(final String width) {
        this.width = width;
    }

    @Column(name = "heigh")
    public String getHeigh() {
        return this.heigh;
    }

    public void setHeigh(final String heigh) {
        this.heigh = heigh;
    }

    @Column(name = "slide_show_id")
    public Long getSlideShowId() {
        return this.slideShowId;
    }

    public void setSlideShowId(final Long slideShowId) {
        this.slideShowId = slideShowId;
    }

    @Column(name = "article_id")
    public Long getArticleId() {
        return this.articleId;
    }

    public void setArticleId(final Long articleId) {
        this.articleId = articleId;
    }

    @Column(name = "type")
    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    @Column(name = "orders")
    public Long getOrder() {
        return this.order;
    }

    public void setOrder(final Long order) {
        this.order = order;
    }

    @Override
    public ImgDTO toDTO() {
        ImgDTO dto = new ImgDTO(
                id == null ? null : id.toString(), url, dishId == null ? null : dishId.toString(), restaurantId == null ? null : restaurantId.toString(), dishGroupId == null ? null : dishGroupId.toString(), width, heigh, slideShowId == null ? null : slideShowId.toString(), articleId == null ? null : articleId.toString(), type, order == null ? null : order.toString()
        );
        return dto;
    }
}
