/**
 * @(#)TagArticleForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.dto;

import com.dungnv.vfw5.base.dto.BaseFWDTO;
import com.dungnv.streetfood.model.TagArticle;
import com.dungnv.vfw5.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author dungnv
 * @version 1.0
 * @since 2/21/2016 12:59 PM
 */
@XmlRootElement(name = "TagArticle")
public class TagArticleDTO extends BaseFWDTO<TagArticle> {
    //Fields

    private String id;
    private String tagId;
    private String tagIdName;
    private String articleId;
    private String articleIdName;
    private static long changedTime = 0;

    public final static String ARTICLE_ID = "articleId";
    public final static String MODEL_NAME = "ArticleLanguage";

    //Constructor
    public TagArticleDTO() {
        setDefaultSortField("id");
    }

    public TagArticleDTO(String id, String tagId, String articleId) {
        this.id = id;
        this.tagId = tagId;
        this.articleId = articleId;
    }
    //Getters and setters

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagIdName(String tagIdName) {
        this.tagIdName = tagIdName;
    }

    public String getTagIdName() {
        return tagIdName;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleIdName(String articleIdName) {
        this.articleIdName = articleIdName;
    }

    public String getArticleIdName() {
        return articleIdName;
    }

    @Override
    public TagArticle toModel() {
        TagArticle model = new TagArticle(
                !StringUtils.validString(id) ? null
                : Long.valueOf(id),
                !StringUtils.validString(tagId) ? null
                : Long.valueOf(tagId),
                !StringUtils.validString(articleId) ? null
                : Long.valueOf(articleId));
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
