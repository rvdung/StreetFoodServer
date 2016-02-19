/**
 * @(#)TagsForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */

package com.dungnv.streetfood.dto;


import com.dungnv.vfw5.base.dto.BaseFWDTO;
import com.dungnv.streetfood.model.Tags;
import com.dungnv.vfw5.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;
/**
* @author dungnv
* @version 1.0
* @since 1/24/2016 8:00 PM
*/
@XmlRootElement(name = "Tags")
public class TagsDTO extends BaseFWDTO<Tags>{    
	//Fields
    private String id;
    private String name;
    private String description;
    private String hit;
    private String dishId;
    private String dishIdName;
    private String restaurantId;
    private String restaurantIdName;
    
    public final static String MODEL_NAME = "Tags";
    public final static String ID = "id";
    
    //Constructor
	public TagsDTO() {
		setDefaultSortField("id");	
	}
	public TagsDTO(String id, String name, String description, String hit, String dishId, String restaurantId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.hit = hit;
		this.dishId = dishId;
		this.restaurantId = restaurantId;
	}	
	//Getters and setters
    
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
    
	public void setDescription(String description) {
        this.description = description;
    }
	public String getDescription() {		
        return description;		
    }
    
	public void setHit(String hit) {
        this.hit = hit;
    }
	public String getHit() {		
        return hit;		
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
	public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
	public String getRestaurantId() {		
        return restaurantId;		
    }
    
	public void setRestaurantIdName(String restaurantIdName) {
        this.restaurantIdName = restaurantIdName;
    }
	public String getRestaurantIdName() {		
        return restaurantIdName;		
    }
	@Override
	public Tags toModel() {
		Tags model = new Tags(
            !StringUtils.validString(id)?null:
                Long.valueOf(id), 
            name, 
            description, 
            !StringUtils.validString(hit)?null:
                Long.valueOf(hit), 
            !StringUtils.validString(dishId)?null:
                Long.valueOf(dishId), 
            !StringUtils.validString(restaurantId)?null:
                Long.valueOf(restaurantId));
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
