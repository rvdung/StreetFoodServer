/**
 * @(#)CategoryBO.java 1/22/2016 9:48 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.model;

import com.dungnv.vfw5.base.model.BaseFWModel;
import java.util.Date;
import javax.persistence.*;
import com.dungnv.streetfood.dto.CategoryDTO;
import com.dungnv.vfw5.base.utils.DateTimeUtils;
import com.dungnv.vfw5.base.utils.ParamUtils;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/22/2016 9:48 PM
 */
@Entity
@Table(name = "Category")
public class Category extends BaseFWModel {

    //Fields
    private Long id;
    private String name;
    private String description;
    private Long categoryStatus;
    private Date categoryUpdateTime;
    private Date categoryCreateTime;
    private Date categoryUpdateTimeGmt;
    private Date categoryCreateTimeGmt;

    //Constructors
    public Category() {
        setColId("id");
        setColName("id");
        setUniqueColumn(new String[]{"id"});
    }

    public Category(Long id) {
        this.id = id;
    }

    public Category(Long id, String name, String description, Long categoryStatus, Date categoryUpdateTime, Date categoryCreateTime, Date categoryUpdateTimeGmt, Date categoryCreateTimeGmt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryStatus = categoryStatus;
        this.categoryUpdateTime = categoryUpdateTime;
        this.categoryCreateTime = categoryCreateTime;
        this.categoryUpdateTimeGmt = categoryUpdateTimeGmt;
        this.categoryCreateTimeGmt = categoryCreateTimeGmt;
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

    @Column(name = "category_status")
    public Long getCategoryStatus() {
        return this.categoryStatus;
    }

    public void setCategoryStatus(final Long categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "category_update_time")
    public Date getCategoryUpdateTime() {
        return this.categoryUpdateTime;
    }

    public void setCategoryUpdateTime(final Date categoryUpdateTime) {
        this.categoryUpdateTime = categoryUpdateTime;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "category_create_time")
    public Date getCategoryCreateTime() {
        return this.categoryCreateTime;
    }

    public void setCategoryCreateTime(final Date categoryCreateTime) {
        this.categoryCreateTime = categoryCreateTime;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "category_update_time_gmt")
    public Date getCategoryUpdateTimeGmt() {
        return this.categoryUpdateTimeGmt;
    }

    public void setCategoryUpdateTimeGmt(final Date categoryUpdateTimeGmt) {
        this.categoryUpdateTimeGmt = categoryUpdateTimeGmt;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "category_create_time_gmt")
    public Date getCategoryCreateTimeGmt() {
        return this.categoryCreateTimeGmt;
    }

    public void setCategoryCreateTimeGmt(final Date categoryCreateTimeGmt) {
        this.categoryCreateTimeGmt = categoryCreateTimeGmt;
    }

    @Override
    public CategoryDTO toDTO() {
        CategoryDTO dto = new CategoryDTO(
                id == null ? null : id.toString()
                , name, description, categoryStatus == null ? null : categoryStatus.toString(), categoryUpdateTime == null ? null : DateTimeUtils.convertDateToString(categoryUpdateTime, ParamUtils.ddMMyyyyHHmmss)
                , categoryCreateTime == null ? null : DateTimeUtils.convertDateToString(categoryCreateTime, ParamUtils.ddMMyyyyHHmmss)
                , categoryUpdateTimeGmt == null ? null : DateTimeUtils.convertDateToString(categoryUpdateTimeGmt, ParamUtils.ddMMyyyyHHmmss)
                , categoryCreateTimeGmt == null ? null : DateTimeUtils.convertDateToString(categoryCreateTimeGmt, ParamUtils.ddMMyyyyHHmmss)
        );
        return dto;
    }

}
