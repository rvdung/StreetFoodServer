
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.CategoryDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import java.util.List;

import javax.jws.WebMethod;

import javax.jws.WebService;
import javax.jws.WebParam;
import com.dungnv.vfw5.base.dto.ResultDTO;
/**
* @author dungnv
* @version 1.0
* @since 1/22/2016 9:48 PM
*/
@WebService
public interface CategoryService {
    @WebMethod(operationName = "getListCategoryDTO")
    public List<CategoryDTO> getListCategoryDTO(@WebParam(name="categoryDTO") CategoryDTO categoryDTO,@WebParam(name="rowStart") int rowStart,@WebParam(name="maxRow") int maxRow,@WebParam(name="sortType") String sortType,@WebParam(name="sortFieldList") String sortFieldList);
    
    
    //
    @WebMethod(operationName = "updateCategory")
    
    public String updateCategory(@WebParam(name = "categoryDTO") CategoryDTO categoryDTO);
    //
    @WebMethod(operationName = "deleteCategory")
    
    public String deleteCategory(@WebParam(name = "categoryDTOId") Long id);
    //
    @WebMethod(operationName = "deleteListCategory")
    
    public String deleteListCategory(@WebParam(name = "categoryListDTO") List<CategoryDTO> categoryListDTO);
    //
    @WebMethod(operationName = "findCategoryById")
    
    public CategoryDTO findCategoryById(@WebParam(name = "categoryDTOId") Long id);  
    //
    @WebMethod(operationName = "insertCategory")
    
    public ResultDTO insertCategory(@WebParam(name="categoryDTO") CategoryDTO categoryDTO);
    //
    @WebMethod(operationName = "insertOrUpdateListCategory")
    
    public String insertOrUpdateListCategory(@WebParam(name = "categoryDTO") List<CategoryDTO> categoryDTO);   
    //
    @WebMethod(operationName = "getSequenseCategory")
    
    public List<String> getSequenseCategory(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
    //    
    @WebMethod(operationName = "getListCategoryByCondition")
    
    public List<CategoryDTO> getListCategoryByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
