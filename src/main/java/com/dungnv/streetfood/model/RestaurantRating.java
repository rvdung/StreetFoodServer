/**
 * @(#)RestaurantRatingBO.java 2/21/2016 12:50 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.model;

import com.dungnv.vfw5.base.model.BaseFWModel;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.dungnv.streetfood.dto.RestaurantRatingDTO;
import com.dungnv.vfw5.base.utils.DateTimeUtils;
import com.dungnv.vfw5.base.utils.ParamUtils;
/**
* @author dungnv
* @version 1.0
* @since 2/21/2016 12:50 PM
*/
@Entity
@Table(name = "restaurant_rating")
public class RestaurantRating extends BaseFWModel {

    //Fields
	private Long id;
	private Long point;
	private Long restaurantId;
	private Long userId;
	private Date rateTime;
	private Date rateTimeGmt;

    //Constructors
	public RestaurantRating() {
		setColId("id");
		setColName("id");
		setUniqueColumn(new String[] {"id" });
	}
	public RestaurantRating(Long id) {
            this.id = id;
	}	
	
	public RestaurantRating(Long id, Long point, Long restaurantId, Long userId, Date rateTime, Date rateTimeGmt){
			this.id = id;
			this.point = point;
			this.restaurantId = restaurantId;
			this.userId = userId;
			this.rateTime = rateTime;
			this.rateTimeGmt = rateTimeGmt;
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
    @Column(name = "point"  )
    public Long getPoint() {
        return this.point;
    }
	public void setPoint(final Long point) {
		this.point = point;
	}
    @Column(name = "restaurant_id", nullable=false , columnDefinition="Restaurant" )
    public Long getRestaurantId() {
        return this.restaurantId;
    }
	public void setRestaurantId(final Long restaurantId) {
		this.restaurantId = restaurantId;
	}
    @Column(name = "user_id", nullable=false , columnDefinition="User" )
    public Long getUserId() {
        return this.userId;
    }
	public void setUserId(final Long userId) {
		this.userId = userId;
	}
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "rate_time"  )
    public Date getRateTime() {
        return this.rateTime;
    }
	public void setRateTime(final Date rateTime) {
		this.rateTime = rateTime;
	}
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "rate_time_gmt"  )
    public Date getRateTimeGmt() {
        return this.rateTimeGmt;
    }
	public void setRateTimeGmt(final Date rateTimeGmt) {
		this.rateTimeGmt = rateTimeGmt;
	}
	
	@Override
	public RestaurantRatingDTO toDTO() {
		RestaurantRatingDTO dto = new RestaurantRatingDTO(
            id==null?null:id.toString(),             point==null?null:point.toString(),             restaurantId==null?null:restaurantId.toString(),             userId==null?null:userId.toString(),             rateTime==null?null:DateTimeUtils.convertDateToString(rateTime, ParamUtils.ddMMyyyy),             rateTimeGmt==null?null:DateTimeUtils.convertDateToString(rateTimeGmt, ParamUtils.ddMMyyyy)
        );
		return dto;
	}
}

