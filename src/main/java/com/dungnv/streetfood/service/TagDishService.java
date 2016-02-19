
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.TagDishDTO;
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
* @since 1/26/2016 9:17 PM
*/
@WebService
public interface TagDishService {
    @WebMethod(operationName = "getListTagDishDTO")
    @WebResult(name = "tagDishDTO", targetNamespace = "http://streetfood.dungnv.vn")
    public List<TagDishDTO> getListTagDishDTO(@WebParam(name="tagDishDTO") TagDishDTO tagDishDTO,@WebParam(name="rowStart") int rowStart,@WebParam(name="maxRow") int maxRow,@WebParam(name="sortType") String sortType,@WebParam(name="sortFieldList") String sortFieldList);
    //
    @WebMethod(operationName = "updateTagDish")
    @WebResult(name = "message", targetNamespace = "http://streetfood.dungnv.vn")
    public String updateTagDish(@WebParam(name = "tagDishDTO") TagDishDTO tagDishDTO);
    //
    @WebMethod(operationName = "deleteTagDish")
    @WebResult(name = "message", targetNamespace = "http://streetfood.dungnv.vn")
    public String deleteTagDish(@WebParam(name = "tagDishDTOId") Long id);
    //
    @WebMethod(operationName = "deleteListTagDish")
    @WebResult(name = "message", targetNamespace = "http://streetfood.dungnv.vn")
    public String deleteListTagDish(@WebParam(name = "tagDishListDTO") List<TagDishDTO> tagDishListDTO);
    //
    @WebMethod(operationName = "findTagDishById")
    @WebResult(name = "tagDish", targetNamespace = "http://streetfood.dungnv.vn")
    public TagDishDTO findTagDishById(@WebParam(name = "tagDishDTOId") Long id);  
    //
    @WebMethod(operationName = "insertTagDish")
    @WebResult(name = "resultDTO", targetNamespace = "http://streetfood.dungnv.vn")
    public ResultDTO insertTagDish(@WebParam(name="tagDishDTO") TagDishDTO tagDishDTO);
    //
    @WebMethod(operationName = "insertOrUpdateListTagDish")
    @WebResult(name = "insertListTagDish", targetNamespace = "http://streetfood.dungnv.vn")
    public String insertOrUpdateListTagDish(@WebParam(name = "tagDishDTO") List<TagDishDTO> tagDishDTO);   
    //
    @WebMethod(operationName = "getSequenseTagDish")
    @WebResult(name = "getSequense", targetNamespace = "http://streetfood.dungnv.vn")
    public List<String> getSequenseTagDish(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
    //    
    @WebMethod(operationName = "getListTagDishByCondition")
    @WebResult(name = "TagDishDTO", targetNamespace = "http://streetfood.dungnv.vn")
    public List<TagDishDTO> getListTagDishByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
