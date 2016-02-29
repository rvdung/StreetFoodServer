
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.RestaurantArticleDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import com.dungnv.vfw5.base.dto.ResultDTO;

/**
 * @author dungnv
 * @version 1.0
 * @since 2/21/2016 12:49 PM
 */
@WebService
public interface RestaurantArticleService {

    @WebMethod(operationName = "getListRestaurantArticleDTO")

    public List<RestaurantArticleDTO> getListRestaurantArticleDTO(@WebParam(name = "restaurantArticleDTO") RestaurantArticleDTO restaurantArticleDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    //
    @WebMethod(operationName = "updateRestaurantArticle")

    public String updateRestaurantArticle(@WebParam(name = "restaurantArticleDTO") RestaurantArticleDTO restaurantArticleDTO);

    //
    @WebMethod(operationName = "deleteRestaurantArticle")

    public String deleteRestaurantArticle(@WebParam(name = "restaurantArticleDTOId") Long id);

    //
    @WebMethod(operationName = "deleteListRestaurantArticle")

    public String deleteListRestaurantArticle(@WebParam(name = "restaurantArticleListDTO") List<RestaurantArticleDTO> restaurantArticleListDTO);

    //
    @WebMethod(operationName = "findRestaurantArticleById")

    public RestaurantArticleDTO findRestaurantArticleById(@WebParam(name = "restaurantArticleDTOId") Long id);

    //
    @WebMethod(operationName = "insertRestaurantArticle")

    public ResultDTO insertRestaurantArticle(@WebParam(name = "restaurantArticleDTO") RestaurantArticleDTO restaurantArticleDTO);

    //
    @WebMethod(operationName = "insertOrUpdateListRestaurantArticle")

    public String insertOrUpdateListRestaurantArticle(@WebParam(name = "restaurantArticleDTO") List<RestaurantArticleDTO> restaurantArticleDTO);

    //
    @WebMethod(operationName = "getSequenseRestaurantArticle")

    public List<String> getSequenseRestaurantArticle(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);

    //    
    @WebMethod(operationName = "getListRestaurantArticleByCondition")

    public List<RestaurantArticleDTO> getListRestaurantArticleByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
