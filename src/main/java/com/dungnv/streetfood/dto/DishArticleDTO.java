/**
 * @(#)DishArticleForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */

package com.dungnv.streetfood.dto;


import com.dungnv.vfw5.base.dto.BaseFWDTO;
import com.dungnv.streetfood.model.DishArticle;
import com.dungnv.vfw5.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;
/**
* @author dungnv
* @version 1.0
* @since 2/29/2016 10:24 PM
*/
@XmlRootElement(name = "DishArticle")
public class DishArticleDTO extends BaseFWDTO<DishArticle>{    
	//Fields
    private String id;
    private String articleId;
    private String articleIdName;
    private String dishId;
    private String dishIdName;
    
    
    public final static String ARTICLE_ID = "articleId";
    public final static String DISH_ID = "dishId";
    public final static String MODEL_NAME = "DishArticle";
    
    //Constructor
	public DishArticleDTO() {
		setDefaultSortField("id");	
	}
	public DishArticleDTO(String id, String articleId, String dishId) {
		this.id = id;
		this.articleId = articleId;
		this.dishId = dishId;
	}	
	//Getters and setters
    
	public void setId(String id) {
        this.id = id;
    }
	public String getId() {		
        return id;		
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
	public void setDishId(String dishId) {
        this.dishId = dishId;
    }
	public String getDishId() {		
        return dishId;		
    }
    
	public void setDishIdName(String dishIdName) {
        this.dishIdName = dishIdName;
    }
	public String getDishIdName() {		
        return dishIdName;		
    }
	@Override
	public DishArticle toModel() {
		DishArticle model = new DishArticle(
            !StringUtils.validString(id)?null:
                Long.valueOf(id), 
            !StringUtils.validString(articleId)?null:
                Long.valueOf(articleId), 
            !StringUtils.validString(dishId)?null:
                Long.valueOf(dishId));
		return model;
	}
	
    @Override
    public Long getFWModelId() {
        return !StringUtils.validString(id)?null:Long.valueOf(id);
    }

    @Override
    public String catchName() {
        return getId().toString();
    }
    
}
