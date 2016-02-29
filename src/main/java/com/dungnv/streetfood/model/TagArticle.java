/**
 * @(#)TagArticleBO.java 2/21/2016 12:59 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.model;

import com.dungnv.vfw5.base.model.BaseFWModel;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.dungnv.streetfood.dto.TagArticleDTO;
/**
* @author dungnv
* @version 1.0
* @since 2/21/2016 12:59 PM
*/
@Entity
@Table(name = "tag_article")
public class TagArticle extends BaseFWModel {

    //Fields
	private Long id;
	private Long tagId;
	private Long articleId;

    //Constructors
	public TagArticle() {
		setColId("id");
		setColName("id");
		setUniqueColumn(new String[] {"id" });
	}
	public TagArticle(Long id) {
            this.id = id;
	}	
	
	public TagArticle(Long id, Long tagId, Long articleId){
			this.id = id;
			this.tagId = tagId;
			this.articleId = articleId;
    }

    //Getters and Setters
	
	
    @Id
    @GeneratedValue
    @Column(name = "id", unique=true, nullable=false  )
    public Long getId() {
        return this.id;
    }
	public void setId(final Long id) {
		this.id = id;
	}
    @Column(name = "tag_id", nullable=false , columnDefinition="Tags" )
    public Long getTagId() {
        return this.tagId;
    }
	public void setTagId(final Long tagId) {
		this.tagId = tagId;
	}
    @Column(name = "article_id", nullable=false , columnDefinition="Article" )
    public Long getArticleId() {
        return this.articleId;
    }
	public void setArticleId(final Long articleId) {
		this.articleId = articleId;
	}
	
	@Override
	public TagArticleDTO toDTO() {
		TagArticleDTO dto = new TagArticleDTO(
            id==null?null:id.toString(),             tagId==null?null:tagId.toString(),             articleId==null?null:articleId.toString()
        );
		return dto;
	}
}

