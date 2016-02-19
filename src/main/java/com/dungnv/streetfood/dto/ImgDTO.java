/**
 * @(#)ImgForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */

package com.dungnv.streetfood.dto;


import com.dungnv.vfw5.base.dto.BaseFWDTO;
import com.dungnv.streetfood.model.Img;
import com.dungnv.vfw5.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;
/**
* @author dungnv
* @version 1.0
* @since 1/25/2016 10:05 PM
*/
@XmlRootElement(name = "Img")
public class ImgDTO extends BaseFWDTO<Img>{    
	//Fields
    private String id;
    private String url;
    private String dishId;
    private String restaurantId;
    private String dishGroupId;
    private String width;
    private String heigh;
    private String slideShowId;
    private String articleId;
    private String type;
    private String order;
    
    public static String DISH_GROUP_ID = "dishGroupId";
    public static String MODEL_NAME = "Img";
    
    //Constructor
	public ImgDTO() {
		setDefaultSortField("id");	
	}
	public ImgDTO(String id, String url, String dishId, String restaurantId, String dishGroupId, String width, String heigh, String slideShowId, String articleId, String type, String order) {
		this.id = id;
		this.url = url;
		this.dishId = dishId;
		this.restaurantId = restaurantId;
		this.dishGroupId = dishGroupId;
		this.width = width;
		this.heigh = heigh;
		this.slideShowId = slideShowId;
		this.articleId = articleId;
		this.type = type;
		this.order = order;
	}	
	//Getters and setters
    
	public void setId(String id) {
        this.id = id;
    }
	public String getId() {		
        return id;		
    }
    
	public void setUrl(String url) {
        this.url = url;
    }
	public String getUrl() {		
        return url;		
    }
    
	public void setDishId(String dishId) {
        this.dishId = dishId;
    }
	public String getDishId() {		
        return dishId;		
    }
    
	public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
	public String getRestaurantId() {		
        return restaurantId;		
    }
    
	public void setDishGroupId(String dishGroupId) {
        this.dishGroupId = dishGroupId;
    }
	public String getDishGroupId() {		
        return dishGroupId;		
    }
    
	public void setWidth(String width) {
        this.width = width;
    }
	public String getWidth() {		
        return width;		
    }
    
	public void setHeigh(String heigh) {
        this.heigh = heigh;
    }
	public String getHeigh() {		
        return heigh;		
    }
    
	public void setSlideShowId(String slideShowId) {
        this.slideShowId = slideShowId;
    }
	public String getSlideShowId() {		
        return slideShowId;		
    }
    
	public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
	public String getArticleId() {		
        return articleId;		
    }
    
	public void setType(String type) {
        this.type = type;
    }
	public String getType() {		
        return type;		
    }
    
	public void setOrder(String order) {
        this.order = order;
    }
	public String getOrder() {		
        return order;		
    }
    
	@Override
	public Img toModel() {
		Img model = new Img(
            !StringUtils.validString(id)?null:
                Long.valueOf(id), 
            url, 
            !StringUtils.validString(dishId)?null:
                Long.valueOf(dishId), 
            !StringUtils.validString(restaurantId)?null:
                Long.valueOf(restaurantId), 
            !StringUtils.validString(dishGroupId)?null:
                Long.valueOf(dishGroupId), 
            width, 
            heigh, 
            !StringUtils.validString(slideShowId)?null:
                Long.valueOf(slideShowId), 
            !StringUtils.validString(articleId)?null:
                Long.valueOf(articleId), 
            type, 
            !StringUtils.validString(order)?null:
                Long.valueOf(order));
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
