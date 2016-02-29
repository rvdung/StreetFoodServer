/**
 * @(#)RestaurantRatingForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */

package com.dungnv.streetfood.dto;


import com.dungnv.vfw5.base.dto.BaseFWDTO;
import com.dungnv.vfw5.base.utils.DateTimeUtils;
import com.dungnv.vfw5.base.utils.StringUtils;
import com.dungnv.streetfood.model.RestaurantRating;
import com.dungnv.vfw5.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;
/**
* @author dungnv
* @version 1.0
* @since 2/21/2016 12:50 PM
*/
@XmlRootElement(name = "RestaurantRating")
public class RestaurantRatingDTO extends BaseFWDTO<RestaurantRating>{    
	//Fields
    private String id;
    private String point;
    private String restaurantId;
    private String restaurantIdName;
    private String userId;
    private String userIdName;
    private String rateTime;
    private String rateTimeGmt;
    private static long changedTime = 0;
    //Constructor
	public RestaurantRatingDTO() {
		setDefaultSortField("id");	
	}
	public RestaurantRatingDTO(String id, String point, String restaurantId, String userId, String rateTime, String rateTimeGmt) {
		this.id = id;
		this.point = point;
		this.restaurantId = restaurantId;
		this.userId = userId;
		this.rateTime = rateTime;
		this.rateTimeGmt = rateTimeGmt;
	}	
	//Getters and setters
    
	public void setId(String id) {
        this.id = id;
    }
	public String getId() {		
        return id;		
    }
    
	public void setPoint(String point) {
        this.point = point;
    }
	public String getPoint() {		
        return point;		
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
	public void setUserId(String userId) {
        this.userId = userId;
    }
	public String getUserId() {		
        return userId;		
    }
    
	public void setUserIdName(String userIdName) {
        this.userIdName = userIdName;
    }
	public String getUserIdName() {		
        return userIdName;		
    }
	public void setRateTime(String rateTime) {
        this.rateTime = rateTime;
    }
	public String getRateTime() {		
        return rateTime;		
    }
    
	public void setRateTimeGmt(String rateTimeGmt) {
        this.rateTimeGmt = rateTimeGmt;
    }
	public String getRateTimeGmt() {		
        return rateTimeGmt;		
    }
    
	@Override
	public RestaurantRating toModel() {
        try {
		RestaurantRating model = new RestaurantRating(
            !StringUtils.validString(id)?null:
                Long.valueOf(id), 
            !StringUtils.validString(point)?null:
                Long.valueOf(point), 
            !StringUtils.validString(restaurantId)?null:
                Long.valueOf(restaurantId), 
            !StringUtils.validString(userId)?null:
                Long.valueOf(userId), 
            !StringUtils.validString(rateTime)?null:
                DateTimeUtils.convertStringToDate(rateTime), 
            !StringUtils.validString(rateTimeGmt)?null:
                DateTimeUtils.convertStringToDate(rateTimeGmt));
		return model;
        } catch (Exception e) {
            return null;
        }
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
