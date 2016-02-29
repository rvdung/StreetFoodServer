/**
 * @(#)ArticleLanguageForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.dto;

import com.dungnv.vfw5.base.dto.BaseFWDTO;
import com.dungnv.streetfood.model.ArticleLanguage;
import com.dungnv.vfw5.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/25/2016 10:07 PM
 */
@XmlRootElement(name = "ArticleLanguage")
public class ArticleLanguageDTO extends BaseFWDTO<ArticleLanguage> {
    //Fields

    private String id;
    private String articleId;
    private String title;
    private String content;
    private String shortContent;
    private String languageCode;

    //Constructor
    public ArticleLanguageDTO() {
        setDefaultSortField("id");
    }

    public ArticleLanguageDTO(String id, String articleId, String title, String content, String shortContent, String languageCode) {
        this.id = id;
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.shortContent = shortContent;
        this.languageCode = languageCode;
    }
    //Getters and setters

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    @Override
    public ArticleLanguage toModel() {
        ArticleLanguage model = new ArticleLanguage(
                !StringUtils.validString(id) ? null
                : Long.valueOf(id),
                !StringUtils.validString(articleId) ? null
                : Long.valueOf(articleId),
                title,
                content,
                shortContent,
                !StringUtils.validString(languageCode) ? null
                : Long.valueOf(languageCode));
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
