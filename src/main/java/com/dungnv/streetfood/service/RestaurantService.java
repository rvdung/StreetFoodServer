
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.RestaurantDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.WebParam;
import com.dungnv.vfw5.base.dto.ResultDTO;
/**
* @author dungnv
* @version 1.0
* @since 1/27/2016 11:45 PM
*/
@WebService
public interface RestaurantService {
    @WebMethod(operationName = "getListRestaurantDTO")
    public List<RestaurantDTO> getListRestaurantDTO(@WebParam(name="restaurantDTO") RestaurantDTO restaurantDTO,@WebParam(name="rowStart") int rowStart,@WebParam(name="maxRow") int maxRow,@WebParam(name="sortType") String sortType,@WebParam(name="sortFieldList") String sortFieldList);
    //
    @WebMethod(operationName = "updateRestaurant")
    public String updateRestaurant(@WebParam(name = "restaurantDTO") RestaurantDTO restaurantDTO);
    //
    @WebMethod(operationName = "deleteRestaurant")
    public String deleteRestaurant(@WebParam(name = "restaurantDTOId") Long id);
    //
    @WebMethod(operationName = "deleteListRestaurant")
    public String deleteListRestaurant(@WebParam(name = "restaurantListDTO") List<RestaurantDTO> restaurantListDTO);
    //
    @WebMethod(operationName = "findRestaurantById")
    public RestaurantDTO findRestaurantById(@WebParam(name = "restaurantDTOId") Long id);  
    //
    @WebMethod(operationName = "insertRestaurant")
    public ResultDTO insertRestaurant(@WebParam(name="restaurantDTO") RestaurantDTO restaurantDTO);
    //
    @WebMethod(operationName = "insertOrUpdateListRestaurant")
    public String insertOrUpdateListRestaurant(@WebParam(name = "restaurantDTO") List<RestaurantDTO> restaurantDTO);   
    //
    @WebMethod(operationName = "getSequenseRestaurant")
    public List<String> getSequenseRestaurant(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
    //    
    @WebMethod(operationName = "getListRestaurantByCondition")
    public List<RestaurantDTO> getListRestaurantByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
