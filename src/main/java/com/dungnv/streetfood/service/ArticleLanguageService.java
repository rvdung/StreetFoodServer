
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.ArticleLanguageDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import java.util.List;

import javax.jws.WebMethod;

import javax.jws.WebService;
import javax.jws.WebParam;
import com.dungnv.vfw5.base.dto.ResultDTO;
/**
* @author dungnv
* @version 1.0
* @since 1/25/2016 10:07 PM
*/
@WebService
public interface ArticleLanguageService {
    @WebMethod(operationName = "getListArticleLanguageDTO")
    
    public List<ArticleLanguageDTO> getListArticleLanguageDTO(@WebParam(name="articleLanguageDTO") ArticleLanguageDTO articleLanguageDTO,@WebParam(name="rowStart") int rowStart,@WebParam(name="maxRow") int maxRow,@WebParam(name="sortType") String sortType,@WebParam(name="sortFieldList") String sortFieldList);
    //
    @WebMethod(operationName = "updateArticleLanguage")
    
    public String updateArticleLanguage(@WebParam(name = "articleLanguageDTO") ArticleLanguageDTO articleLanguageDTO);
    //
    @WebMethod(operationName = "deleteArticleLanguage")
    
    public String deleteArticleLanguage(@WebParam(name = "articleLanguageDTOId") Long id);
    //
    @WebMethod(operationName = "deleteListArticleLanguage")
    
    public String deleteListArticleLanguage(@WebParam(name = "articleLanguageListDTO") List<ArticleLanguageDTO> articleLanguageListDTO);
    //
    @WebMethod(operationName = "findArticleLanguageById")
    
    public ArticleLanguageDTO findArticleLanguageById(@WebParam(name = "articleLanguageDTOId") Long id);  
    //
    @WebMethod(operationName = "insertArticleLanguage")
    
    public ResultDTO insertArticleLanguage(@WebParam(name="articleLanguageDTO") ArticleLanguageDTO articleLanguageDTO);
    //
    @WebMethod(operationName = "insertOrUpdateListArticleLanguage")
    
    public String insertOrUpdateListArticleLanguage(@WebParam(name = "articleLanguageDTO") List<ArticleLanguageDTO> articleLanguageDTO);   
    //
    @WebMethod(operationName = "getSequenseArticleLanguage")
    
    public List<String> getSequenseArticleLanguage(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
    //    
    @WebMethod(operationName = "getListArticleLanguageByCondition")
    
    public List<ArticleLanguageDTO> getListArticleLanguageByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
