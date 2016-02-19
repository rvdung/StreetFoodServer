
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.TagsDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import java.util.List;

import javax.jws.WebMethod;

import javax.jws.WebService;
import javax.jws.WebParam;
import com.dungnv.vfw5.base.dto.ResultDTO;
/**
* @author dungnv
* @version 1.0
* @since 1/24/2016 8:00 PM
*/
@WebService
public interface TagsService {
    @WebMethod(operationName = "getListTagsDTO")
    
    public List<TagsDTO> getListTagsDTO(@WebParam(name="tagsDTO") TagsDTO tagsDTO,@WebParam(name="rowStart") int rowStart,@WebParam(name="maxRow") int maxRow,@WebParam(name="sortType") String sortType,@WebParam(name="sortFieldList") String sortFieldList);
    //
    @WebMethod(operationName = "updateTags")
    
    public String updateTags(@WebParam(name = "tagsDTO") TagsDTO tagsDTO);
    //
    @WebMethod(operationName = "deleteTags")
    
    public String deleteTags(@WebParam(name = "tagsDTOId") Long id);
    //
    @WebMethod(operationName = "deleteListTags")
    
    public String deleteListTags(@WebParam(name = "tagsListDTO") List<TagsDTO> tagsListDTO);
    //
    @WebMethod(operationName = "findTagsById")
    
    public TagsDTO findTagsById(@WebParam(name = "tagsDTOId") Long id);  
    //
    @WebMethod(operationName = "insertTags")
    
    public ResultDTO insertTags(@WebParam(name="tagsDTO") TagsDTO tagsDTO);
    //
    @WebMethod(operationName = "insertOrUpdateListTags")
    
    public String insertOrUpdateListTags(@WebParam(name = "tagsDTO") List<TagsDTO> tagsDTO);   
    //
    @WebMethod(operationName = "getSequenseTags")
    
    public List<String> getSequenseTags(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
    //    
    @WebMethod(operationName = "getListTagsByCondition")
    
    public List<TagsDTO> getListTagsByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
