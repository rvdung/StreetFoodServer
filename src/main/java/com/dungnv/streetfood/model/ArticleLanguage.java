/**
 * @(#)ArticleLanguageBO.java 1/25/2016 10:07 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.model;

import com.dungnv.vfw5.base.model.BaseFWModel;
import com.dungnv.streetfood.dto.ArticleLanguageDTO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
* @author dungnv
* @version 1.0
* @since 1/25/2016 10:07 PM
*/
@Entity
@Table(name = "article_language")
public class ArticleLanguage extends BaseFWModel {

    //Fields
	private Long id;
	private String title;
	private String content;
	private String shortContent;
	private Long languageCode;

    //Constructors
	public ArticleLanguage() {
		setColId("id");
		setColName("id");
		setUniqueColumn(new String[] {"id" });
	}
	public ArticleLanguage(Long id) {
            this.id = id;
	}	
	
	public ArticleLanguage(Long id, String title, String content, String shortContent, Long languageCode){
			this.id = id;
			this.title = title;
			this.content = content;
			this.shortContent = shortContent;
			this.languageCode = languageCode;
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
    @Column(name = "title", nullable=false  )
    public String getTitle() {
        return this.title;
    }
	public void setTitle(final String title) {
		this.title = title;
	}
    @Column(name = "content"  )
    public String getContent() {
        return this.content;
    }
	public void setContent(final String content) {
		this.content = content;
	}
    @Column(name = "short_content"  )
    public String getShortContent() {
        return this.shortContent;
    }
	public void setShortContent(final String shortContent) {
		this.shortContent = shortContent;
	}
    @Column(name = "language_code", nullable=false  )
    public Long getLanguageCode() {
        return this.languageCode;
    }
	public void setLanguageCode(final Long languageCode) {
		this.languageCode = languageCode;
	}
	
	@Override
	public ArticleLanguageDTO toDTO() {
		ArticleLanguageDTO dto = new ArticleLanguageDTO(
            id==null?null:id.toString(),             title,             content,             shortContent,             languageCode==null?null:languageCode.toString()
        );
		return dto;
	}
}

