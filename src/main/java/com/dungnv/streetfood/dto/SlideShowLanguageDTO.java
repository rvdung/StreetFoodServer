/**
 * @(#)SlideShowLanguageForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */

package com.dungnv.streetfood.dto;


import com.dungnv.vfw5.base.dto.BaseFWDTO;
import com.dungnv.streetfood.model.SlideShowLanguage;
import com.dungnv.vfw5.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;
/**
* @author dungnv
* @version 1.0
* @since 2/21/2016 1:04 PM
*/
@XmlRootElement(name = "SlideShowLanguage")
public class SlideShowLanguageDTO extends BaseFWDTO<SlideShowLanguage>{    
	//Fields
    private String id;
    private String name;
    private String description;
    private String languageCode;
    private String slideShowId;
    private static long changedTime = 0;
    //Constructor
	public SlideShowLanguageDTO() {
		setDefaultSortField("id");	
	}
	public SlideShowLanguageDTO(String id, String name, String description, String languageCode, String slideShowId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.languageCode = languageCode;
		this.slideShowId = slideShowId;
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
    
	public void setSlideShowId(String slideShowId) {
        this.slideShowId = slideShowId;
    }
	public String getSlideShowId() {		
        return slideShowId;		
    }
    
	@Override
	public SlideShowLanguage toModel() {
		SlideShowLanguage model = new SlideShowLanguage(
            !StringUtils.validString(id)?null:
                Long.valueOf(id), 
            name, 
            description, 
            !StringUtils.validString(languageCode)?null:
                Long.valueOf(languageCode), 
            !StringUtils.validString(slideShowId)?null:
                Long.valueOf(slideShowId));
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
