
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.RestaurantLanguageDTO;
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
* @since 2/21/2016 11:30 AM
*/
@WebService
public interface RestaurantLanguageService {
    @WebMethod(operationName = "getListRestaurantLanguageDTO")
    
    public List<RestaurantLanguageDTO> getListRestaurantLanguageDTO(@WebParam(name="restaurantLanguageDTO") RestaurantLanguageDTO restaurantLanguageDTO,@WebParam(name="rowStart") int rowStart,@WebParam(name="maxRow") int maxRow,@WebParam(name="sortType") String sortType,@WebParam(name="sortFieldList") String sortFieldList);
    //
    @WebMethod(operationName = "updateRestaurantLanguage")
    
    public String updateRestaurantLanguage(@WebParam(name = "restaurantLanguageDTO") RestaurantLanguageDTO restaurantLanguageDTO);
    //
    @WebMethod(operationName = "deleteRestaurantLanguage")
    
    public String deleteRestaurantLanguage(@WebParam(name = "restaurantLanguageDTOId") Long id);
    //
    @WebMethod(operationName = "deleteListRestaurantLanguage")
    
    public String deleteListRestaurantLanguage(@WebParam(name = "restaurantLanguageListDTO") List<RestaurantLanguageDTO> restaurantLanguageListDTO);
    //
    @WebMethod(operationName = "findRestaurantLanguageById")
    
    public RestaurantLanguageDTO findRestaurantLanguageById(@WebParam(name = "restaurantLanguageDTOId") Long id);  
    //
    @WebMethod(operationName = "insertRestaurantLanguage")
    
    public ResultDTO insertRestaurantLanguage(@WebParam(name="restaurantLanguageDTO") RestaurantLanguageDTO restaurantLanguageDTO);
    //
    @WebMethod(operationName = "insertOrUpdateListRestaurantLanguage")
    
    public String insertOrUpdateListRestaurantLanguage(@WebParam(name = "restaurantLanguageDTO") List<RestaurantLanguageDTO> restaurantLanguageDTO);   
    //
    @WebMethod(operationName = "getSequenseRestaurantLanguage")
    
    public List<String> getSequenseRestaurantLanguage(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
    //    
    @WebMethod(operationName = "getListRestaurantLanguageByCondition")
    
    public List<RestaurantLanguageDTO> getListRestaurantLanguageByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
