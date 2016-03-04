
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.DishArticleDTO;
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
* @since 2/29/2016 10:24 PM
*/
@WebService(targetNamespace = "http://streetfood.dungnv.vn")
public interface DishArticleService {
    @WebMethod(operationName = "getListDishArticleDTO")
    @WebResult(name = "dishArticleDTO", targetNamespace = "http://streetfood.dungnv.vn")
    public List<DishArticleDTO> getListDishArticleDTO(@WebParam(name="dishArticleDTO") DishArticleDTO dishArticleDTO,@WebParam(name="rowStart") int rowStart,@WebParam(name="maxRow") int maxRow,@WebParam(name="sortType") String sortType,@WebParam(name="sortFieldList") String sortFieldList);
    //
    @WebMethod(operationName = "updateDishArticle")
    @WebResult(name = "message", targetNamespace = "http://streetfood.dungnv.vn")
    public String updateDishArticle(@WebParam(name = "dishArticleDTO") DishArticleDTO dishArticleDTO);
    //
    @WebMethod(operationName = "deleteDishArticle")
    @WebResult(name = "message", targetNamespace = "http://streetfood.dungnv.vn")
    public String deleteDishArticle(@WebParam(name = "dishArticleDTOId") Long id);
    //
    @WebMethod(operationName = "deleteListDishArticle")
    @WebResult(name = "message", targetNamespace = "http://streetfood.dungnv.vn")
    public String deleteListDishArticle(@WebParam(name = "dishArticleListDTO") List<DishArticleDTO> dishArticleListDTO);
    //
    @WebMethod(operationName = "findDishArticleById")
    @WebResult(name = "dishArticle", targetNamespace = "http://streetfood.dungnv.vn")
    public DishArticleDTO findDishArticleById(@WebParam(name = "dishArticleDTOId") Long id);  
    //
    @WebMethod(operationName = "insertDishArticle")
    @WebResult(name = "resultDTO", targetNamespace = "http://streetfood.dungnv.vn")
    public ResultDTO insertDishArticle(@WebParam(name="dishArticleDTO") DishArticleDTO dishArticleDTO);
    //
    @WebMethod(operationName = "insertOrUpdateListDishArticle")
    @WebResult(name = "insertListDishArticle", targetNamespace = "http://streetfood.dungnv.vn")
    public String insertOrUpdateListDishArticle(@WebParam(name = "dishArticleDTO") List<DishArticleDTO> dishArticleDTO);   
    //
    @WebMethod(operationName = "getSequenseDishArticle")
    @WebResult(name = "getSequense", targetNamespace = "http://streetfood.dungnv.vn")
    public List<String> getSequenseDishArticle(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
    //    
    @WebMethod(operationName = "getListDishArticleByCondition")
    @WebResult(name = "DishArticleDTO", targetNamespace = "http://streetfood.dungnv.vn")
    public List<DishArticleDTO> getListDishArticleByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
