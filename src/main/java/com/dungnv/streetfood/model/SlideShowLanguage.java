/**
 * @(#)SlideShowLanguageBO.java 2/21/2016 1:04 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.model;

import com.dungnv.vfw5.base.model.BaseFWModel;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.dungnv.streetfood.dto.SlideShowLanguageDTO;

/**
 * @author dungnv
 * @version 1.0
 * @since 2/21/2016 1:04 PM
 */
@Entity
@Table(name = "slide_show_language")
public class SlideShowLanguage extends BaseFWModel {

    //Fields
    private Long id;
    private String name;
    private String description;
    private Long languageCode;
    private Long slideShowId;
    public final static String SLIDESHOW_ID = "slideShowId";

    //Constructors
    public SlideShowLanguage() {
        setColId("id");
        setColName("id");
        setUniqueColumn(new String[]{"id"});
    }

    public SlideShowLanguage(Long id) {
        this.id = id;
    }

    public SlideShowLanguage(Long id, String name, String description, Long languageCode, Long slideShowId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.languageCode = languageCode;
        this.slideShowId = slideShowId;
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

    @Column(name = "language_code", nullable = false)
    public Long getLanguageCode() {
        return this.languageCode;
    }

    public void setLanguageCode(final Long languageCode) {
        this.languageCode = languageCode;
    }

    @Column(name = "slide_show_id")
    public Long getSlideShowId() {
        return this.slideShowId;
    }

    public void setSlideShowId(final Long slideShowId) {
        this.slideShowId = slideShowId;
    }

    @Override
    public SlideShowLanguageDTO toDTO() {
        SlideShowLanguageDTO dto = new SlideShowLanguageDTO(
                id == null ? null : id.toString(), name, description, languageCode == null ? null : languageCode.toString(), slideShowId == null ? null : slideShowId.toString()
        );
        return dto;
    }
}
