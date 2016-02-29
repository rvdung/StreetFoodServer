/**
 * @(#)DishLanguageBO.java 1/25/2016 10:02 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.model;

import com.dungnv.vfw5.base.model.BaseFWModel;
import javax.persistence.*;
import com.dungnv.streetfood.dto.DishLanguageDTO;
/**
* @author dungnv
* @version 1.0
* @since 1/25/2016 10:02 PM
*/
@Entity
@Table(name = "dish_language")
public class DishLanguage extends BaseFWModel {

    //Fields
	private Long id;
	private String name;
	private String shortDescription;
	private String longDescription;
	private Long dishId;
	private Long languageCode;
        
        

    //Constructors
	public DishLanguage() {
		setColId("id");
		setColName("id");
		setUniqueColumn(new String[] {"id" });
	}
	public DishLanguage(Long id) {
            this.id = id;
	}	
	
	public DishLanguage(Long id, String name, String shortDescription, String longDescription, Long dishId, Long languageCode){
			this.id = id;
			this.name = name;
			this.shortDescription = shortDescription;
			this.longDescription = longDescription;
			this.dishId = dishId;
			this.languageCode = languageCode;
    }

    //Getters and Setters
	
	@Id 
    @GeneratedValue
    @Column(name = "id", unique=true, nullable=false  )
    public Long getId() {
        return this.id;
    }
	public void setId(final Long id) {
		this.id = id;
	}
    @Column(name = "name", nullable=false  )
    public String getName() {
        return this.name;
    }
	public void setName(final String name) {
		this.name = name;
	}
    @Column(name = "short_description"  )
    public String getShortDescription() {
        return this.shortDescription;
    }
	public void setShortDescription(final String shortDescription) {
		this.shortDescription = shortDescription;
	}
    @Column(name = "long_description"  )
    public String getLongDescription() {
        return this.longDescription;
    }
	public void setLongDescription(final String longDescription) {
		this.longDescription = longDescription;
	}
    @Column(name = "dish_id", nullable=false , columnDefinition="Dish" )
    public Long getDishId() {
        return this.dishId;
    }
	public void setDishId(final Long dishId) {
		this.dishId = dishId;
	}
    @Column(name = "language_code", nullable=false  )
    public Long getLanguageCode() {
        return this.languageCode;
    }
	public void setLanguageCode(final Long languageCode) {
		this.languageCode = languageCode;
	}
	
	@Override
	public DishLanguageDTO toDTO() {
		DishLanguageDTO dto = new DishLanguageDTO(
            id==null?null:id.toString(),             name,             shortDescription,             longDescription,             dishId==null?null:dishId.toString(),             languageCode==null?null:languageCode.toString()
        );
		return dto;
	}
}

