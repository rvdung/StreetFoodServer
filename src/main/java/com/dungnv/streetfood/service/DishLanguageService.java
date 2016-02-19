
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.DishLanguageDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import java.util.List;

import javax.jws.WebMethod;

import javax.jws.WebService;
import javax.jws.WebParam;
import com.dungnv.vfw5.base.dto.ResultDTO;
/**
* @author dungnv
* @version 1.0
* @since 1/25/2016 10:02 PM
*/
@WebService
public interface DishLanguageService {
    @WebMethod(operationName = "getListDishLanguageDTO")
    
    public List<DishLanguageDTO> getListDishLanguageDTO(@WebParam(name="dishLanguageDTO") DishLanguageDTO dishLanguageDTO,@WebParam(name="rowStart") int rowStart,@WebParam(name="maxRow") int maxRow,@WebParam(name="sortType") String sortType,@WebParam(name="sortFieldList") String sortFieldList);
    //
    @WebMethod(operationName = "updateDishLanguage")
    
    public String updateDishLanguage(@WebParam(name = "dishLanguageDTO") DishLanguageDTO dishLanguageDTO);
    //
    @WebMethod(operationName = "deleteDishLanguage")
    
    public String deleteDishLanguage(@WebParam(name = "dishLanguageDTOId") Long id);
    //
    @WebMethod(operationName = "deleteListDishLanguage")
    
    public String deleteListDishLanguage(@WebParam(name = "dishLanguageListDTO") List<DishLanguageDTO> dishLanguageListDTO);
    //
    @WebMethod(operationName = "findDishLanguageById")
    
    public DishLanguageDTO findDishLanguageById(@WebParam(name = "dishLanguageDTOId") Long id);  
    //
    @WebMethod(operationName = "insertDishLanguage")
    
    public ResultDTO insertDishLanguage(@WebParam(name="dishLanguageDTO") DishLanguageDTO dishLanguageDTO);
    //
    @WebMethod(operationName = "insertOrUpdateListDishLanguage")
    
    public String insertOrUpdateListDishLanguage(@WebParam(name = "dishLanguageDTO") List<DishLanguageDTO> dishLanguageDTO);   
    //
    @WebMethod(operationName = "getSequenseDishLanguage")
    
    public List<String> getSequenseDishLanguage(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
    //    
    @WebMethod(operationName = "getListDishLanguageByCondition")
    
    public List<DishLanguageDTO> getListDishLanguageByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
