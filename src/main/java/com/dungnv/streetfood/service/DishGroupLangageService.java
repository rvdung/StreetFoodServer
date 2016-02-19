
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.DishGroupLangageDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import java.util.List;

import javax.jws.WebMethod;

import javax.jws.WebService;
import javax.jws.WebParam;
import com.dungnv.vfw5.base.dto.ResultDTO;
/**
* @author dungnv
* @version 1.0
* @since 1/21/2016 9:12 PM
*/
@WebService
public interface DishGroupLangageService {
    @WebMethod(operationName = "getListDishGroupLangageDTO")
    
    public List<DishGroupLangageDTO> getListDishGroupLangageDTO(@WebParam(name="dishGroupLangageDTO") DishGroupLangageDTO dishGroupLangageDTO,@WebParam(name="rowStart") int rowStart,@WebParam(name="maxRow") int maxRow,@WebParam(name="sortType") String sortType,@WebParam(name="sortFieldList") String sortFieldList);
    //
    @WebMethod(operationName = "updateDishGroupLangage")
    
    public String updateDishGroupLangage(@WebParam(name = "dishGroupLangageDTO") DishGroupLangageDTO dishGroupLangageDTO);
    //
    @WebMethod(operationName = "deleteDishGroupLangage")
    
    public String deleteDishGroupLangage(@WebParam(name = "dishGroupLangageDTOId") Long id);
    //
    @WebMethod(operationName = "deleteListDishGroupLangage")
    
    public String deleteListDishGroupLangage(@WebParam(name = "dishGroupLangageListDTO") List<DishGroupLangageDTO> dishGroupLangageListDTO);
    //
    @WebMethod(operationName = "findDishGroupLangageById")
    
    public DishGroupLangageDTO findDishGroupLangageById(@WebParam(name = "dishGroupLangageDTOId") Long id);  
    //
    @WebMethod(operationName = "insertDishGroupLangage")
    
    public ResultDTO insertDishGroupLangage(@WebParam(name="dishGroupLangageDTO") DishGroupLangageDTO dishGroupLangageDTO);
    //
    @WebMethod(operationName = "insertOrUpdateListDishGroupLangage")
    
    public String insertOrUpdateListDishGroupLangage(@WebParam(name = "dishGroupLangageDTO") List<DishGroupLangageDTO> dishGroupLangageDTO);   
    //
    @WebMethod(operationName = "getSequenseDishGroupLangage")
    
    public List<String> getSequenseDishGroupLangage(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
    //    
    @WebMethod(operationName = "getListDishGroupLangageByCondition")
    
    public List<DishGroupLangageDTO> getListDishGroupLangageByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
