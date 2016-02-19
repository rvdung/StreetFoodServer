/**
 * @(#)LocaleBO.java 1/22/2016 9:45 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.model;

import com.dungnv.vfw5.base.model.BaseFWModel;
import com.dungnv.streetfood.dto.LocaleDTO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/22/2016 9:45 PM
 */
@Entity
@Table(name = "Locale")
public class LocaleModel extends BaseFWModel {

    //Fields
    private Long id;
    private String locale;
    private String gmt;
    private Long status;

    //Constructors
    public LocaleModel() {
        setColId("id");
        setColName("id");
        setUniqueColumn(new String[]{"id"});
    }

    public LocaleModel(Long id) {
        this.id = id;
    }

    public LocaleModel(Long id, String locale, String gmt, Long status) {
        this.id = id;
        this.locale = locale;
        this.gmt = gmt;
        this.status = status;
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

    @Column(name = "locale", nullable = false)
    public String getLocale() {
        return this.locale;
    }

    public void setLocale(final String locale) {
        this.locale = locale;
    }

    @Column(name = "gmt")
    public String getGmt() {
        return this.gmt;
    }

    public void setGmt(final String gmt) {
        this.gmt = gmt;
    }

    @Column(name = "status", nullable = false)
    public Long getStatus() {
        return this.status;
    }

    public void setStatus(final Long status) {
        this.status = status;
    }

    @Override
    public LocaleDTO toDTO() {
        LocaleDTO dto = new LocaleDTO(
                id == null ? null : id.toString(), locale, gmt, status == null ? null : status.toString()
        );
        return dto;
    }
}
