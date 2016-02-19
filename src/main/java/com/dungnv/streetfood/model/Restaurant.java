/**
 * @(#)RestaurantBO.java 1/27/2016 11:45 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.model;

import com.dungnv.vfw5.base.model.BaseFWModel;
import java.util.Date;
import com.dungnv.streetfood.dto.RestaurantDTO;
import com.dungnv.vfw5.base.utils.DateTimeUtils;
import com.dungnv.vfw5.base.utils.ParamUtils;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/27/2016 11:45 PM
 */
@Entity
@Table(name = "restaurant")
public class Restaurant extends BaseFWModel {

    //Fields
    private Long id;
    private String name;
    private Double lon;
    private Double lat;
    private String videoUrl;
    private String address;
    private String phoneNumber;
    private String siteUrl;
    private String introduce;
    private Double priceFromVn;
    private Double priceFromEn;
    private Double priceToVn;
    private Double priceToEn;
    private Long carParking;
    private Long motobikeParking;
    private String capacity;
    private Long waitingTime;
    private Date operatingTimeStart;
    private Date operatingTimeEnd;
    private Long restaurantStatus;
    private Date restaurantUpdateTime;
    private Date restaurantCreateTime;
    private Date restaurantUpdateTimeGmt;
    private Date restaurantCreateTimeGmt;
    private Long viewCount;
    private Long commentCount;
    private Long shareCount;
    private Double rating;

    //Constructors
    public Restaurant() {
        setColId("id");
        setColName("id");
        setUniqueColumn(new String[]{"id"});
    }

    public Restaurant(Long id) {
        this.id = id;
    }

    public Restaurant(Long id, String name, Double lon, Double lat, String videoUrl, String address//
            , String phoneNumber, String siteUrl, String introduce, Double priceFromVn//
            , Double priceFromEn, Double priceToVn, Double priceToEn, Long carParking//
            , Long motobikeParking, String capacity, Long waitingTime, Date operatingTimeStart//
            , Date operatingTimeEnd, Long restaurantStatus, Date restaurantUpdateTime//
            , Date restaurantCreateTime, Date restaurantUpdateTimeGmt, Date restaurantCreateTimeGmt//
            , Long viewCount, Long commentCount, Long shareCount, Double rating) {
        this.id = id;
        this.name = name;
        this.lon = lon;
        this.lat = lat;
        this.videoUrl = videoUrl;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.siteUrl = siteUrl;
        this.introduce = introduce;
        this.priceFromVn = priceFromVn;
        this.priceFromEn = priceFromEn;
        this.priceToVn = priceToVn;
        this.priceToEn = priceToEn;
        this.carParking = carParking;
        this.motobikeParking = motobikeParking;
        this.capacity = capacity;
        this.waitingTime = waitingTime;
        this.operatingTimeStart = operatingTimeStart;
        this.operatingTimeEnd = operatingTimeEnd;
        this.restaurantStatus = restaurantStatus;
        this.restaurantUpdateTime = restaurantUpdateTime;
        this.restaurantCreateTime = restaurantCreateTime;
        this.restaurantUpdateTimeGmt = restaurantUpdateTimeGmt;
        this.restaurantCreateTimeGmt = restaurantCreateTimeGmt;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.shareCount = shareCount;
        this.rating = rating;
    }

    //Getters and Setters
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Column(name = "lon")
    public Double getLon() {
        return this.lon;
    }

    public void setLon(final Double lon) {
        this.lon = lon;
    }

    @Column(name = "lat")
    public Double getLat() {
        return this.lat;
    }

    public void setLat(final Double lat) {
        this.lat = lat;
    }

    @Column(name = "video_url")
    public String getVideoUrl() {
        return this.videoUrl;
    }

    public void setVideoUrl(final String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Column(name = "address")
    public String getAddress() {
        return this.address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "site_url")
    public String getSiteUrl() {
        return this.siteUrl;
    }

    public void setSiteUrl(final String siteUrl) {
        this.siteUrl = siteUrl;
    }

    @Column(name = "introduce")
    public String getIntroduce() {
        return this.introduce;
    }

    public void setIntroduce(final String introduce) {
        this.introduce = introduce;
    }

    @Column(name = "price_from_vn")
    public Double getPriceFromVn() {
        return this.priceFromVn;
    }

    public void setPriceFromVn(final Double priceFromVn) {
        this.priceFromVn = priceFromVn;
    }

    @Column(name = "price_from_en")
    public Double getPriceFromEn() {
        return this.priceFromEn;
    }

    public void setPriceFromEn(final Double priceFromEn) {
        this.priceFromEn = priceFromEn;
    }

    @Column(name = "price_to_vn")
    public Double getPriceToVn() {
        return this.priceToVn;
    }

    public void setPriceToVn(final Double priceToVn) {
        this.priceToVn = priceToVn;
    }

    @Column(name = "price_to_en")
    public Double getPriceToEn() {
        return this.priceToEn;
    }

    public void setPriceToEn(final Double priceToEn) {
        this.priceToEn = priceToEn;
    }

    @Column(name = "car_parking")
    public Long getCarParking() {
        return this.carParking;
    }

    public void setCarParking(final Long carParking) {
        this.carParking = carParking;
    }

    @Column(name = "motobike_parking")
    public Long getMotobikeParking() {
        return this.motobikeParking;
    }

    public void setMotobikeParking(final Long motobikeParking) {
        this.motobikeParking = motobikeParking;
    }

    @Column(name = "capacity")
    public String getCapacity() {
        return this.capacity;
    }

    public void setCapacity(final String capacity) {
        this.capacity = capacity;
    }

    @Column(name = "waiting_time")
    public Long getWaitingTime() {
        return this.waitingTime;
    }

    public void setWaitingTime(final Long waitingTime) {
        this.waitingTime = waitingTime;
    }

    @Column(name = "operating_time_start")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getOperatingTimeStart() {
        return this.operatingTimeStart;
    }

    public void setOperatingTimeStart(final Date operatingTimeStart) {
        this.operatingTimeStart = operatingTimeStart;
    }

    @Column(name = "operating_time_end")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getOperatingTimeEnd() {
        return this.operatingTimeEnd;
    }

    public void setOperatingTimeEnd(final Date operatingTimeEnd) {
        this.operatingTimeEnd = operatingTimeEnd;
    }

    @Column(name = "restaurant_status")
    public Long getRestaurantStatus() {
        return this.restaurantStatus;
    }

    public void setRestaurantStatus(final Long restaurantStatus) {
        this.restaurantStatus = restaurantStatus;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "restaurant_update_time")
    public Date getRestaurantUpdateTime() {
        return this.restaurantUpdateTime;
    }

    public void setRestaurantUpdateTime(final Date restaurantUpdateTime) {
        this.restaurantUpdateTime = restaurantUpdateTime;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "restaurant_create_time")
    public Date getRestaurantCreateTime() {
        return this.restaurantCreateTime;
    }

    public void setRestaurantCreateTime(final Date restaurantCreateTime) {
        this.restaurantCreateTime = restaurantCreateTime;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "restaurant_update_time_gmt")
    public Date getRestaurantUpdateTimeGmt() {
        return this.restaurantUpdateTimeGmt;
    }

    public void setRestaurantUpdateTimeGmt(final Date restaurantUpdateTimeGmt) {
        this.restaurantUpdateTimeGmt = restaurantUpdateTimeGmt;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "restaurant_create_time_gmt")
    public Date getRestaurantCreateTimeGmt() {
        return this.restaurantCreateTimeGmt;
    }

    public void setRestaurantCreateTimeGmt(final Date restaurantCreateTimeGmt) {
        this.restaurantCreateTimeGmt = restaurantCreateTimeGmt;
    }

    @Column(name = "view_count")
    public Long getViewCount() {
        return this.viewCount;
    }

    public void setViewCount(final Long viewCount) {
        this.viewCount = viewCount;
    }

    @Column(name = "comment_count")
    public Long getCommentCount() {
        return this.commentCount;
    }

    public void setCommentCount(final Long commentCount) {
        this.commentCount = commentCount;
    }

    @Column(name = "share_count")
    public Long getShareCount() {
        return this.shareCount;
    }

    public void setShareCount(final Long shareCount) {
        this.shareCount = shareCount;
    }

    @Column(name = "rating")
    public Double getRating() {
        return this.rating;
    }

    public void setRating(final Double rating) {
        this.rating = rating;
    }

    @Override
    public RestaurantDTO toDTO() {
        RestaurantDTO dto = new RestaurantDTO(
                id == null ? null : id.toString(), name, lon == null ? null : lon.toString(), lat == null ? null : lat.toString(), videoUrl, address, phoneNumber, siteUrl, introduce, priceFromVn == null ? null : priceFromVn.toString(), priceFromEn == null ? null : priceFromEn.toString(), priceToVn == null ? null : priceToVn.toString(), priceToEn == null ? null : priceToEn.toString(), carParking == null ? null : carParking.toString(), motobikeParking == null ? null : motobikeParking.toString(), capacity, waitingTime == null ? null : waitingTime.toString(), operatingTimeStart == null ? null : operatingTimeStart.toString(), operatingTimeEnd == null ? null : operatingTimeEnd.toString(), restaurantStatus == null ? null : restaurantStatus.toString(), restaurantUpdateTime == null ? null : DateTimeUtils.convertDateToString(restaurantUpdateTime, ParamUtils.ddMMyyyyHHmmss), restaurantCreateTime == null ? null : DateTimeUtils.convertDateToString(restaurantCreateTime, ParamUtils.ddMMyyyyHHmmss), restaurantUpdateTimeGmt == null ? null : DateTimeUtils.convertDateToString(restaurantUpdateTimeGmt, ParamUtils.ddMMyyyyHHmmss), restaurantCreateTimeGmt == null ? null : DateTimeUtils.convertDateToString(restaurantCreateTimeGmt, ParamUtils.ddMMyyyyHHmmss), viewCount == null ? null : viewCount.toString(), commentCount == null ? null : commentCount.toString(), shareCount == null ? null : shareCount.toString(), rating == null ? null : rating.toString()
        );
        return dto;
    }
}
