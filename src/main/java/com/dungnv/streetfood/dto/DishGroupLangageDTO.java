/**
 * @(#)DishGroupLangageForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */

package com.dungnv.streetfood.dto;


import com.dungnv.vfw5.base.dto.BaseFWDTO;
import com.dungnv.streetfood.model.DishGroupLangage;
import com.dungnv.vfw5.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;
/**
* @author dungnv
* @version 1.0
* @since 1/21/2016 9:12 PM
*/
@XmlRootElement(name = "DishGroupLangage")
public class DishGroupLangageDTO extends BaseFWDTO<DishGroupLangage>{    
	//Fields
    private String id;
    private String name;
    private String description;
    private String languageCode;
    private String dishGroupId;
    private String dishGroupIdName;
    
    //Constructor
	public DishGroupLangageDTO() {
		setDefaultSortField("id");	
	}
	public DishGroupLangageDTO(String id, String name, String description, String languageCode, String dishGroupId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.languageCode = languageCode;
		this.dishGroupId = dishGroupId;
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
    
	public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
	public String getLanguageCode() {		
        return languageCode;		
    }
    
	public void setDishGroupId(String dishGroupId) {
        this.dishGroupId = dishGroupId;
    }
	public String getDishGroupId() {		
        return dishGroupId;		
    }
    
	public void setDishGroupIdName(String dishGroupIdName) {
        this.dishGroupIdName = dishGroupIdName;
    }
	public String getDishGroupIdName() {		
        return dishGroupIdName;		
    }
	@Override
	public DishGroupLangage toModel() {
		DishGroupLangage model = new DishGroupLangage(
            !StringUtils.validString(id)?null:
                Long.valueOf(id), 
            name, 
            description, 
            !StringUtils.validString(languageCode)?null:
                Long.valueOf(languageCode), 
            !StringUtils.validString(dishGroupId)?null:
                Long.valueOf(dishGroupId));
		return model;
	}
	
    @Override
    public Long getFWModelId() {
        return !StringUtils.validString(id)?null:Long.valueOf(id);
    }

    @Override
    public String catchName() {
        return getId();
    }
    
}
