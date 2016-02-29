
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.RestaurantDishDetailDTO;
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
* @since 2/21/2016 12:49 PM
*/
@WebService
public interface RestaurantDishDetailService {
    @WebMethod(operationName = "getListRestaurantDishDetailDTO")
    
    public List<RestaurantDishDetailDTO> getListRestaurantDishDetailDTO(@WebParam(name="restaurantDishDetailDTO") RestaurantDishDetailDTO restaurantDishDetailDTO,@WebParam(name="rowStart") int rowStart,@WebParam(name="maxRow") int maxRow,@WebParam(name="sortType") String sortType,@WebParam(name="sortFieldList") String sortFieldList);
    //
    @WebMethod(operationName = "updateRestaurantDishDetail")
    
    public String updateRestaurantDishDetail(@WebParam(name = "restaurantDishDetailDTO") RestaurantDishDetailDTO restaurantDishDetailDTO);
    //
    @WebMethod(operationName = "deleteRestaurantDishDetail")
    
    public String deleteRestaurantDishDetail(@WebParam(name = "restaurantDishDetailDTOId") Long id);
    //
    @WebMethod(operationName = "deleteListRestaurantDishDetail")
    
    public String deleteListRestaurantDishDetail(@WebParam(name = "restaurantDishDetailListDTO") List<RestaurantDishDetailDTO> restaurantDishDetailListDTO);
    //
    @WebMethod(operationName = "findRestaurantDishDetailById")
    
    public RestaurantDishDetailDTO findRestaurantDishDetailById(@WebParam(name = "restaurantDishDetailDTOId") Long id);  
    //
    @WebMethod(operationName = "insertRestaurantDishDetail")
    
    public ResultDTO insertRestaurantDishDetail(@WebParam(name="restaurantDishDetailDTO") RestaurantDishDetailDTO restaurantDishDetailDTO);
    //
    @WebMethod(operationName = "insertOrUpdateListRestaurantDishDetail")
    
    public String insertOrUpdateListRestaurantDishDetail(@WebParam(name = "restaurantDishDetailDTO") List<RestaurantDishDetailDTO> restaurantDishDetailDTO);   
    //
    @WebMethod(operationName = "getSequenseRestaurantDishDetail")
    
    public List<String> getSequenseRestaurantDishDetail(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
    //    
    @WebMethod(operationName = "getListRestaurantDishDetailByCondition")
    
    public List<RestaurantDishDetailDTO> getListRestaurantDishDetailByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
