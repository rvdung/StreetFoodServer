/**
 * @(#)RestaurantLanguageForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */

package com.dungnv.streetfood.dto;


import com.dungnv.vfw5.base.dto.BaseFWDTO;
import com.dungnv.streetfood.model.RestaurantLanguage;
import com.dungnv.vfw5.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;
/**
* @author dungnv
* @version 1.0
* @since 2/21/2016 11:30 AM
*/
@XmlRootElement(name = "RestaurantLanguage")
public class RestaurantLanguageDTO extends BaseFWDTO<RestaurantLanguage>{    
	//Fields
    private String id;
    private String name;
    private String address;
    private String introduce;
    private String languageCode;
    private String restaurantId;
    private String restaurantIdName;
    private static long changedTime = 0;
    //Constructor
	public RestaurantLanguageDTO() {
		setDefaultSortField("id");	
	}
	public RestaurantLanguageDTO(String id, String name, String address, String introduce, String languageCode, String restaurantId) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.introduce = introduce;
		this.languageCode = languageCode;
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
    
	public void setAddress(String address) {
        this.address = address;
    }
	public String getAddress() {		
        return address;		
    }
    
	public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
	public String getIntroduce() {		
        return introduce;		
    }
    
	public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
	public String getLanguageCode() {		
        return languageCode;		
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
	public RestaurantLanguage toModel() {
		RestaurantLanguage model = new RestaurantLanguage(
            !StringUtils.validString(id)?null:
                Long.valueOf(id), 
            name, 
            address, 
            introduce, 
            !StringUtils.validString(languageCode)?null:
                Long.valueOf(languageCode), 
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
