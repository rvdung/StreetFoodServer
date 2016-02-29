/**
 * @(#)TagRestaurantForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */

package com.dungnv.streetfood.dto;


import com.dungnv.vfw5.base.dto.BaseFWDTO;
import com.dungnv.streetfood.model.TagRestaurant;
import com.dungnv.vfw5.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;
/**
* @author dungnv
* @version 1.0
* @since 2/21/2016 12:58 PM
*/
@XmlRootElement(name = "TagRestaurant")
public class TagRestaurantDTO extends BaseFWDTO<TagRestaurant>{    
	//Fields
    private String id;
    private String tagId;
    private String tagIdName;
    private String restaurantId;
    private String restaurantIdName;
    private static long changedTime = 0;
    
    
    public final static String RESTAURANT_ID = "restaurantId";
    public final static String MODEL_NAME = "TagRestaurant";
    //Constructor
	public TagRestaurantDTO() {
		setDefaultSortField("id");	
	}
	public TagRestaurantDTO(String id, String tagId, String restaurantId) {
		this.id = id;
		this.tagId = tagId;
		this.restaurantId = restaurantId;
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
	public TagRestaurant toModel() {
		TagRestaurant model = new TagRestaurant(
            !StringUtils.validString(id)?null:
                Long.valueOf(id), 
            !StringUtils.validString(tagId)?null:
                Long.valueOf(tagId), 
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
