/**
 * @(#)ArticleBO.java 1/21/2016 12:48 AM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.model;

import com.dungnv.vfw5.base.model.BaseFWModel;
import java.util.Date;
import com.dungnv.streetfood.dto.ArticleDTO;
import com.dungnv.vfw5.base.utils.DateTimeUtils;
import com.dungnv.vfw5.base.utils.ParamUtils;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/21/2016 12:48 AM
 */
@Entity
@Table(name = "article")
public class Article extends BaseFWModel {

    //Fields
    private Long id;
    private String title;
    private String content;
    private String shortContent;
    private Date updateTime;
    private Date updateTimeGmt;
    private Long viewCount;

    //Constructors
    public Article() {
        setColId("id");
        setColName("id");
        setUniqueColumn(new String[]{"id"});
    }

    public Article(Long id) {
        this.id = id;
    }

    public Article(Long id, String title, String content, String shortContent, Date updateTime, Date updateTimeGmt, Long viewCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.shortContent = shortContent;
        this.updateTime = updateTime;
        this.updateTimeGmt = updateTimeGmt;
        this.viewCount = viewCount;
    }

    //Getters and Setters
    @Id
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    @Column(name = "content")
    public String getContent() {
        return this.content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    @Column(name = "short_content")
    public String getShortContent() {
        return this.shortContent;
    }

    public void setShortContent(final String shortContent) {
        this.shortContent = shortContent;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "update_time_gmt")
    public Date getUpdateTimeGmt() {
        return this.updateTimeGmt;
    }

    public void setUpdateTimeGmt(final Date updateTimeGmt) {
        this.updateTimeGmt = updateTimeGmt;
    }

    @Column(name = "view_count")
    public Long getViewCount() {
        return this.viewCount;
    }

    public void setViewCount(final Long viewCount) {
        this.viewCount = viewCount;
    }

    @Override
    public ArticleDTO toDTO() {
        ArticleDTO dto = new ArticleDTO(
                id == null ? null : id.toString(), title, content, shortContent, updateTime == null ? null : DateTimeUtils.convertDateToString(updateTime, ParamUtils.ddMMyyyy), updateTimeGmt == null ? null : DateTimeUtils.convertDateToString(updateTimeGmt, ParamUtils.ddMMyyyy), viewCount == null ? null : viewCount.toString()
        );
        return dto;
    }
}
