
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.DishDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import java.util.List;

import javax.jws.WebMethod;

import javax.jws.WebService;
import javax.jws.WebParam;
import com.dungnv.vfw5.base.dto.ResultDTO;
/**
* @author dungnv
* @version 1.0
* @since 1/25/2016 10:01 PM
*/
@WebService
public interface DishService {
    @WebMethod(operationName = "getListDishDTO")
    
    public List<DishDTO> getListDishDTO(@WebParam(name="dishDTO") DishDTO dishDTO,@WebParam(name="rowStart") int rowStart,@WebParam(name="maxRow") int maxRow,@WebParam(name="sortType") String sortType,@WebParam(name="sortFieldList") String sortFieldList);
    //
    @WebMethod(operationName = "updateDish")
    public String updateDish(@WebParam(name = "dishDTO") DishDTO dishDTO);
    //
    @WebMethod(operationName = "deleteDish")
    
    public String deleteDish(@WebParam(name = "dishDTOId") Long id);
    //
    @WebMethod(operationName = "deleteListDish")
    
    public String deleteListDish(@WebParam(name = "dishListDTO") List<DishDTO> dishListDTO);
    //
    @WebMethod(operationName = "findDishById")
    
    public DishDTO findDishById(@WebParam(name = "dishDTOId") Long id);  
    //
    @WebMethod(operationName = "insertDish")
    
    public ResultDTO insertDish(@WebParam(name="dishDTO") DishDTO dishDTO);
    //
    @WebMethod(operationName = "insertOrUpdateListDish")
    
    public String insertOrUpdateListDish(@WebParam(name = "dishDTO") List<DishDTO> dishDTO);   
    //
    @WebMethod(operationName = "getSequenseDish")
    
    public List<String> getSequenseDish(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
    //    
    @WebMethod(operationName = "getListDishByCondition")
    
    public List<DishDTO> getListDishByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
