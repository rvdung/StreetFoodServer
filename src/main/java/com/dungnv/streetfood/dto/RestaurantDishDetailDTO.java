/**
 * @(#)RestaurantDishDetailForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */

package com.dungnv.streetfood.dto;


import com.dungnv.vfw5.base.dto.BaseFWDTO;
import com.dungnv.streetfood.model.RestaurantDishDetail;
import com.dungnv.vfw5.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;
/**
* @author dungnv
* @version 1.0
* @since 2/21/2016 12:49 PM
*/
@XmlRootElement(name = "RestaurantDishDetail")
public class RestaurantDishDetailDTO extends BaseFWDTO<RestaurantDishDetail>{    
	//Fields
    private String id;
    private String dishId;
    private String dishIdName;
    private String restaurantId;
    private String restaurantIdName;
    private static long changedTime = 0;
    
    public final static String RESTAURANT_ID = "restaurantId";
    public final static String DISH_ID = "dishId";
    public final static String MODEL_NAME = "RestaurantDishDetail";
    
    //Constructor
	public RestaurantDishDetailDTO() {
		setDefaultSortField("id");	
	}
	public RestaurantDishDetailDTO(String id, String dishId, String restaurantId) {
		this.id = id;
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
	public RestaurantDishDetail toModel() {
		RestaurantDishDetail model = new RestaurantDishDetail(
            !StringUtils.validString(id)?null:
                Long.valueOf(id), 
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
