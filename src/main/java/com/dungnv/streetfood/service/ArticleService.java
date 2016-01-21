
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.ArticleDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebParam;
import javax.jws.WebMethod;
import com.dungnv.vfw5.base.dto.ResultDTO;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/21/2016 12:48 AM
 */
@WebService
public interface ArticleService {

    @WebMethod(operationName = "getListArticleDTO")
    public List<ArticleDTO> getListArticleDTO(@WebParam(name = "articleDTO") ArticleDTO articleDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    @WebMethod(operationName = "updateArticle")
    public String updateArticle(@WebParam(name = "articleDTO") ArticleDTO articleDTO);

    @WebMethod(operationName = "deleteArticle")
    public String deleteArticle(@WebParam(name = "articleDTOId") Long id);

    @WebMethod(operationName = "deleteListArticle")
    public String deleteListArticle(@WebParam(name = "articleListDTO") List<ArticleDTO> articleListDTO);

    @WebMethod(operationName = "findArticleById")
    public ArticleDTO findArticleById(@WebParam(name = "articleDTOId") Long id);

    @WebMethod(operationName = "insertArticle")
    public ResultDTO insertArticle(@WebParam(name = "articleDTO") ArticleDTO articleDTO);

    @WebMethod(operationName = "insertOrUpdateListArticle")
    public String insertOrUpdateListArticle(@WebParam(name = "articleDTO") List<ArticleDTO> articleDTO);

    @WebMethod(operationName = "getSequenseArticle")
    public List<String> getSequenseArticle(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);

    @WebMethod(operationName = "getListArticleByCondition")
    public List<ArticleDTO> getListArticleByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
