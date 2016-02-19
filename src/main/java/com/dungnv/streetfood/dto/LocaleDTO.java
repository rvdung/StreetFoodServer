/**
 * @(#)LocaleForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.dto;

import com.dungnv.vfw5.base.dto.BaseFWDTO;
import com.dungnv.streetfood.model.LocaleModel;
import com.dungnv.vfw5.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/22/2016 9:45 PM
 */
@XmlRootElement(name = "Locale")
public class LocaleDTO extends BaseFWDTO<LocaleModel> {
    //Fields

    private String id;
    private String locale;
    private String gmt;
    private String status;
    

    //Constructor
    public LocaleDTO() {
        setDefaultSortField("id");
    }

    public LocaleDTO(String id, String locale, String gmt, String status) {
        this.id = id;
        this.locale = locale;
        this.gmt = gmt;
        this.status = status;
    }
    //Getters and setters

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }

    public void setGmt(String gmt) {
        this.gmt = gmt;
    }

    public String getGmt() {
        return gmt;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public LocaleModel toModel() {
        LocaleModel model = new LocaleModel(
                !StringUtils.validString(id) ? null
                : Long.valueOf(id),
                locale,
                gmt,
                !StringUtils.validString(status) ? null
                : Long.valueOf(status));
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
