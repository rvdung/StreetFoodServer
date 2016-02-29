
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.TagRestaurantDTO;
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
* @since 2/21/2016 12:58 PM
*/
@WebService
public interface TagRestaurantService {
    @WebMethod(operationName = "getListTagRestaurantDTO")
    
    public List<TagRestaurantDTO> getListTagRestaurantDTO(@WebParam(name="tagRestaurantDTO") TagRestaurantDTO tagRestaurantDTO,@WebParam(name="rowStart") int rowStart,@WebParam(name="maxRow") int maxRow,@WebParam(name="sortType") String sortType,@WebParam(name="sortFieldList") String sortFieldList);
    //
    @WebMethod(operationName = "updateTagRestaurant")
    
    public String updateTagRestaurant(@WebParam(name = "tagRestaurantDTO") TagRestaurantDTO tagRestaurantDTO);
    //
    @WebMethod(operationName = "deleteTagRestaurant")
    
    public String deleteTagRestaurant(@WebParam(name = "tagRestaurantDTOId") Long id);
    //
    @WebMethod(operationName = "deleteListTagRestaurant")
    
    public String deleteListTagRestaurant(@WebParam(name = "tagRestaurantListDTO") List<TagRestaurantDTO> tagRestaurantListDTO);
    //
    @WebMethod(operationName = "findTagRestaurantById")
    
    public TagRestaurantDTO findTagRestaurantById(@WebParam(name = "tagRestaurantDTOId") Long id);  
    //
    @WebMethod(operationName = "insertTagRestaurant")
    
    public ResultDTO insertTagRestaurant(@WebParam(name="tagRestaurantDTO") TagRestaurantDTO tagRestaurantDTO);
    //
    @WebMethod(operationName = "insertOrUpdateListTagRestaurant")
    
    public String insertOrUpdateListTagRestaurant(@WebParam(name = "tagRestaurantDTO") List<TagRestaurantDTO> tagRestaurantDTO);   
    //
    @WebMethod(operationName = "getSequenseTagRestaurant")
    
    public List<String> getSequenseTagRestaurant(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
    //    
    @WebMethod(operationName = "getListTagRestaurantByCondition")
    
    public List<TagRestaurantDTO> getListTagRestaurantByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
