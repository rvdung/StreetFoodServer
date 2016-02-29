/**
 * @(#)CategoryDishForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */

package com.dungnv.streetfood.dto;


import com.dungnv.vfw5.base.dto.BaseFWDTO;
import com.dungnv.streetfood.model.CategoryDish;
import com.dungnv.vfw5.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;
/**
* @author dungnv
* @version 1.0
* @since 1/25/2016 10:09 PM
*/
@XmlRootElement(name = "CategoryDish")
public class CategoryDishDTO extends BaseFWDTO<CategoryDish>{    
	//Fields
    private String id;
    private String categoryId;
    private String categoryIdName;
    private String dishId;
    private String dishIdName;
    
    public final static String CATEGORY_ID = "categoryId";
    public final static String DISH_ID = "dishId";
    public final static String MODEL_NAME = "CategoryDish";
    
    
    
    //Constructor
	public CategoryDishDTO() {
		setDefaultSortField("id");	
	}
	public CategoryDishDTO(String id, String categoryId, String dishId) {
		this.id = id;
		this.categoryId = categoryId;
		this.dishId = dishId;
	}	
	//Getters and setters
    
	public void setId(String id) {
        this.id = id;
    }
	public String getId() {		
        return id;		
    }
    
	public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
	public String getCategoryId() {		
        return categoryId;		
    }
    
	public void setCategoryIdName(String categoryIdName) {
        this.categoryIdName = categoryIdName;
    }
	public String getCategoryIdName() {		
        return categoryIdName;		
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
	public CategoryDish toModel() {
		CategoryDish model = new CategoryDish(
            !StringUtils.validString(id)?null:
                Long.valueOf(id), 
            !StringUtils.validString(categoryId)?null:
                Long.valueOf(categoryId), 
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
