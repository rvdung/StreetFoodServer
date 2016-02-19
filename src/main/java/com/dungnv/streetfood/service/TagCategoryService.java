
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.TagCategoryDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import java.util.List;

import javax.jws.WebMethod;

import javax.jws.WebService;
import javax.jws.WebParam;
import com.dungnv.vfw5.base.dto.ResultDTO;
/**
* @author dungnv
* @version 1.0
* @since 1/24/2016 7:34 PM
*/
@WebService
public interface TagCategoryService {
    @WebMethod(operationName = "getListTagCategoryDTO")
    
    public List<TagCategoryDTO> getListTagCategoryDTO(@WebParam(name="tagCategoryDTO") TagCategoryDTO tagCategoryDTO,@WebParam(name="rowStart") int rowStart,@WebParam(name="maxRow") int maxRow,@WebParam(name="sortType") String sortType,@WebParam(name="sortFieldList") String sortFieldList);
    //
    @WebMethod(operationName = "updateTagCategory")
    
    public String updateTagCategory(@WebParam(name = "tagCategoryDTO") TagCategoryDTO tagCategoryDTO);
    //
    @WebMethod(operationName = "deleteTagCategory")
    
    public String deleteTagCategory(@WebParam(name = "tagCategoryDTOId") Long id);
    //
    @WebMethod(operationName = "deleteListTagCategory")
    
    public String deleteListTagCategory(@WebParam(name = "tagCategoryListDTO") List<TagCategoryDTO> tagCategoryListDTO);
    //
    @WebMethod(operationName = "findTagCategoryById")
    
    public TagCategoryDTO findTagCategoryById(@WebParam(name = "tagCategoryDTOId") Long id);  
    //
    @WebMethod(operationName = "insertTagCategory")
    
    public ResultDTO insertTagCategory(@WebParam(name="tagCategoryDTO") TagCategoryDTO tagCategoryDTO);
    //
    @WebMethod(operationName = "insertOrUpdateListTagCategory")
    
    public String insertOrUpdateListTagCategory(@WebParam(name = "tagCategoryDTO") List<TagCategoryDTO> tagCategoryDTO);   
    //
    @WebMethod(operationName = "getSequenseTagCategory")
    
    public List<String> getSequenseTagCategory(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
    //    
    @WebMethod(operationName = "getListTagCategoryByCondition")
    
    public List<TagCategoryDTO> getListTagCategoryByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
