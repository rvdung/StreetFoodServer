/**
 * @(#)SlideShowForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.dto;

import com.dungnv.vfw5.base.dto.BaseFWDTO;
import com.dungnv.vfw5.base.utils.DateTimeUtils;
import com.dungnv.streetfood.model.SlideShow;
import com.dungnv.vfw5.base.utils.StringUtils;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/27/2016 11:48 PM
 */
@XmlRootElement(name = "SlideShow")
public class SlideShowDTO extends BaseFWDTO<SlideShow> {
    //Fields

    private String id;
    private String name;
    private String url;
    private String description;
    private String createDateGmt;
    private String validFromGmt;
    private String validToGmt;
    private String order;
    private String desription;
    private static long changedTime = 0;

    private List<SlideShowLanguageDTO> listLanguage;
    private List<String> listImgUrl;
    private String imageId;
    private String imageUrl;

    //Constructor
    public SlideShowDTO() {
        setDefaultSortField("id");
    }

    public SlideShowDTO(String id, String name, String url, String description, String createDateGmt, String validFromGmt, String validToGmt, String order, String desription) {
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
    //Getters and setters

    public static long getChangedTime() {
        return changedTime;
    }

    public static void setChangedTime(long changedTime) {
        SlideShowDTO.changedTime = changedTime;
    }

    public List<SlideShowLanguageDTO> getListLanguage() {
        return listLanguage;
    }

    public void setListLanguage(List<SlideShowLanguageDTO> listLanguage) {
        this.listLanguage = listLanguage;
    }

    public List<String> getListImgUrl() {
        return listImgUrl;
    }

    public void setListImgUrl(List<String> listImgUrl) {
        this.listImgUrl = listImgUrl;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

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

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCreateDateGmt(String createDateGmt) {
        this.createDateGmt = createDateGmt;
    }

    public String getCreateDateGmt() {
        return createDateGmt;
    }

    public void setValidFromGmt(String validFromGmt) {
        this.validFromGmt = validFromGmt;
    }

    public String getValidFromGmt() {
        return validFromGmt;
    }

    public void setValidToGmt(String validToGmt) {
        this.validToGmt = validToGmt;
    }

    public String getValidToGmt() {
        return validToGmt;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrder() {
        return order;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }

    public String getDesription() {
        return desription;
    }

    @Override
    public SlideShow toModel() {
        try {
            SlideShow model = new SlideShow(
                    !StringUtils.validString(id) ? null
                    : Long.valueOf(id),
                    name,
                    url,
                    description,
                    !StringUtils.validString(createDateGmt) ? null
                    : DateTimeUtils.convertStringToDate(createDateGmt),
                    !StringUtils.validString(validFromGmt) ? null
                    : DateTimeUtils.convertStringToDate(validFromGmt),
                    !StringUtils.validString(validToGmt) ? null
                    : DateTimeUtils.convertStringToDate(validToGmt),
                    !StringUtils.validString(order) ? null
                    : Long.valueOf(order),
                    desription);
            return model;
        } catch (Exception e) {
            return null;
        }
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
