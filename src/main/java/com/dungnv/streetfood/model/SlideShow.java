/**
 * @(#)SlideShowBO.java 1/27/2016 11:48 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.model;

import com.dungnv.vfw5.base.model.BaseFWModel;
import java.util.Date;
import com.dungnv.streetfood.dto.SlideShowDTO;
import com.dungnv.vfw5.base.utils.DateTimeUtils;
import com.dungnv.vfw5.base.utils.ParamUtils;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/27/2016 11:48 PM
 */
@Entity
@Table(name = "slide_show")
public class SlideShow extends BaseFWModel {

    //Fields
    private Long id;
    private String name;
    private String url;
    private String description;
    private Date createDateGmt;
    private Date validFromGmt;
    private Date validToGmt;
    private Long order;
    private String desription;

    //Constructors
    public SlideShow() {
        setColId("id");
        setColName("id");
        setUniqueColumn(new String[]{"id"});
    }

    public SlideShow(Long id) {
        this.id = id;
    }

    public SlideShow(Long id, String name, String url, String description, Date createDateGmt, Date validFromGmt, Date validToGmt, Long order, String desription) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.description = description;
        this.createDateGmt = createDateGmt;
        this.validFromGmt = validFromGmt;
        this.validToGmt = validToGmt;
        this.order = order;
        this.desription = desription;
    }

    //Getters and Setters
    @Id
    @GeneratedValue
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

    @Column(name = "url")
    public String getUrl() {
        return this.url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "create_date_gmt")
    public Date getCreateDateGmt() {
        return this.createDateGmt;
    }

    public void setCreateDateGmt(final Date createDateGmt) {
        this.createDateGmt = createDateGmt;
    }

    @Column(name = "valid_from_gmt")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getValidFromGmt() {
        return this.validFromGmt;
    }

    public void setValidFromGmt(final Date validFromGmt) {
        this.validFromGmt = validFromGmt;
    }

    @Column(name = "valid_to_gmt")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getValidToGmt() {
        return this.validToGmt;
    }

    public void setValidToGmt(final Date validToGmt) {
        this.validToGmt = validToGmt;
    }

    @Column(name = "order")
    public Long getOrder() {
        return this.order;
    }

    public void setOrder(final Long order) {
        this.order = order;
    }

    @Column(name = "desription")
    public String getDesription() {
        return this.desription;
    }

    public void setDesription(final String desription) {
        this.desription = desription;
    }

    @Override
    public SlideShowDTO toDTO() {
        SlideShowDTO dto = new SlideShowDTO(
                id == null ? null : id.toString(), name, url, description, createDateGmt == null ? null : DateTimeUtils.convertDateToString(createDateGmt, ParamUtils.ddMMyyyyHHmmss), validFromGmt == null ? null : validFromGmt.toString(), validToGmt == null ? null : validToGmt.toString(), order == null ? null : order.toString(), desription
        );
        return dto;
    }
}
