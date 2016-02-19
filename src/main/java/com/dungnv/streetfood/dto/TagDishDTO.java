/**
 * @(#)TagDishForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */

package com.dungnv.streetfood.dto;


import com.dungnv.vfw5.base.dto.BaseFWDTO;
import com.dungnv.streetfood.model.TagDish;
import com.dungnv.vfw5.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;
/**
* @author dungnv
* @version 1.0
* @since 1/26/2016 9:17 PM
*/
@XmlRootElement(name = "TagDish")
public class TagDishDTO extends BaseFWDTO<TagDish>{    
	//Fields
    private String id;
    private String tagId;
    private String tagIdName;
    private String dishId;
    private String dishIdName;
    //Constructor
	public TagDishDTO() {
		setDefaultSortField("id");	
	}
	public TagDishDTO(String id, String tagId, String dishId) {
		this.id = id;
		this.tagId = tagId;
		this.dishId = dishId;
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
	public TagDish toModel() {
		TagDish model = new TagDish(
            !StringUtils.validString(id)?null:
                Long.valueOf(id), 
            !StringUtils.validString(tagId)?null:
                Long.valueOf(tagId), 
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
