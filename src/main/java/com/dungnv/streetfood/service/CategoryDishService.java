
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.CategoryDishDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import java.util.List;

import javax.jws.WebMethod;

import javax.jws.WebService;
import javax.jws.WebParam;
import com.dungnv.vfw5.base.dto.ResultDTO;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/25/2016 10:09 PM
 */
@WebService
public interface CategoryDishService {

    @WebMethod(operationName = "getListCategoryDishDTO")

    public List<CategoryDishDTO> getListCategoryDishDTO(@WebParam(name = "categoryDishDTO") CategoryDishDTO categoryDishDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    //
    @WebMethod(operationName = "updateCategoryDish")

    public String updateCategoryDish(@WebParam(name = "categoryDishDTO") CategoryDishDTO categoryDishDTO);

    //
    @WebMethod(operationName = "deleteCategoryDish")

    public String deleteCategoryDish(@WebParam(name = "categoryDishDTOId") Long id);

    //
    @WebMethod(operationName = "deleteListCategoryDish")

    public String deleteListCategoryDish(@WebParam(name = "categoryDishListDTO") List<CategoryDishDTO> categoryDishListDTO);

    //
    @WebMethod(operationName = "findCategoryDishById")

    public CategoryDishDTO findCategoryDishById(@WebParam(name = "categoryDishDTOId") Long id);

    //
    @WebMethod(operationName = "insertCategoryDish")

    public ResultDTO insertCategoryDish(@WebParam(name = "categoryDishDTO") CategoryDishDTO categoryDishDTO);

    //
    @WebMethod(operationName = "insertOrUpdateListCategoryDish")

    public String insertOrUpdateListCategoryDish(@WebParam(name = "categoryDishDTO") List<CategoryDishDTO> categoryDishDTO);

    //
    @WebMethod(operationName = "getSequenseCategoryDish")

    public List<String> getSequenseCategoryDish(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);

    //    
    @WebMethod(operationName = "getListCategoryDishByCondition")

    public List<CategoryDishDTO> getListCategoryDishByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
