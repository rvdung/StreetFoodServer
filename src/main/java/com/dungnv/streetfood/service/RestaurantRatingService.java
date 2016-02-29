
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.RestaurantRatingDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import java.util.List;
import java.util.Locale;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.WebParam;
import com.dungnv.vfw5.base.dto.ResultDTO;
/**
* @author dungnv
* @version 1.0
* @since 2/21/2016 12:50 PM
*/
@WebService
public interface RestaurantRatingService {
    @WebMethod(operationName = "getListRestaurantRatingDTO")
    
    public List<RestaurantRatingDTO> getListRestaurantRatingDTO(@WebParam(name="restaurantRatingDTO") RestaurantRatingDTO restaurantRatingDTO,@WebParam(name="rowStart") int rowStart,@WebParam(name="maxRow") int maxRow,@WebParam(name="sortType") String sortType,@WebParam(name="sortFieldList") String sortFieldList);
    //
    @WebMethod(operationName = "updateRestaurantRating")
    
    public String updateRestaurantRating(@WebParam(name = "restaurantRatingDTO") RestaurantRatingDTO restaurantRatingDTO);
    //
    @WebMethod(operationName = "deleteRestaurantRating")
    
    public String deleteRestaurantRating(@WebParam(name = "restaurantRatingDTOId") Long id);
    //
    @WebMethod(operationName = "deleteListRestaurantRating")
    
    public String deleteListRestaurantRating(@WebParam(name = "restaurantRatingListDTO") List<RestaurantRatingDTO> restaurantRatingListDTO);
    //
    @WebMethod(operationName = "findRestaurantRatingById")
    
    public RestaurantRatingDTO findRestaurantRatingById(@WebParam(name = "restaurantRatingDTOId") Long id);  
    //
    @WebMethod(operationName = "insertRestaurantRating")
    
    public ResultDTO insertRestaurantRating(@WebParam(name="restaurantRatingDTO") RestaurantRatingDTO restaurantRatingDTO);
    //
    @WebMethod(operationName = "insertOrUpdateListRestaurantRating")
    
    public String insertOrUpdateListRestaurantRating(@WebParam(name = "restaurantRatingDTO") List<RestaurantRatingDTO> restaurantRatingDTO);   
    //
    @WebMethod(operationName = "getSequenseRestaurantRating")
    
    public List<String> getSequenseRestaurantRating(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
    //    
    @WebMethod(operationName = "getListRestaurantRatingByCondition")
    
    public List<RestaurantRatingDTO> getListRestaurantRatingByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
