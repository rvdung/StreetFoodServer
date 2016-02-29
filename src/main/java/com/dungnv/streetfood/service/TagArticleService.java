
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.TagArticleDTO;
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
* @since 2/21/2016 12:59 PM
*/
@WebService
public interface TagArticleService {
    @WebMethod(operationName = "getListTagArticleDTO")
    
    public List<TagArticleDTO> getListTagArticleDTO(@WebParam(name="tagArticleDTO") TagArticleDTO tagArticleDTO,@WebParam(name="rowStart") int rowStart,@WebParam(name="maxRow") int maxRow,@WebParam(name="sortType") String sortType,@WebParam(name="sortFieldList") String sortFieldList);
    //
    @WebMethod(operationName = "updateTagArticle")
    
    public String updateTagArticle(@WebParam(name = "tagArticleDTO") TagArticleDTO tagArticleDTO);
    //
    @WebMethod(operationName = "deleteTagArticle")
    
    public String deleteTagArticle(@WebParam(name = "tagArticleDTOId") Long id);
    //
    @WebMethod(operationName = "deleteListTagArticle")
    
    public String deleteListTagArticle(@WebParam(name = "tagArticleListDTO") List<TagArticleDTO> tagArticleListDTO);
    //
    @WebMethod(operationName = "findTagArticleById")
    
    public TagArticleDTO findTagArticleById(@WebParam(name = "tagArticleDTOId") Long id);  
    //
    @WebMethod(operationName = "insertTagArticle")
    
    public ResultDTO insertTagArticle(@WebParam(name="tagArticleDTO") TagArticleDTO tagArticleDTO);
    //
    @WebMethod(operationName = "insertOrUpdateListTagArticle")
    
    public String insertOrUpdateListTagArticle(@WebParam(name = "tagArticleDTO") List<TagArticleDTO> tagArticleDTO);   
    //
    @WebMethod(operationName = "getSequenseTagArticle")
    
    public List<String> getSequenseTagArticle(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
    //    
    @WebMethod(operationName = "getListTagArticleByCondition")
    
    public List<TagArticleDTO> getListTagArticleByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
