
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.ImgDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import java.util.List;

import javax.jws.WebMethod;

import javax.jws.WebService;
import javax.jws.WebParam;
import com.dungnv.vfw5.base.dto.ResultDTO;
/**
* @author dungnv
* @version 1.0
* @since 1/25/2016 10:05 PM
*/
@WebService
public interface ImgService {
    @WebMethod(operationName = "getListImgDTO")
    
    public List<ImgDTO> getListImgDTO(@WebParam(name="imgDTO") ImgDTO imgDTO,@WebParam(name="rowStart") int rowStart,@WebParam(name="maxRow") int maxRow,@WebParam(name="sortType") String sortType,@WebParam(name="sortFieldList") String sortFieldList);
    //
    @WebMethod(operationName = "updateImg")
    
    public String updateImg(@WebParam(name = "imgDTO") ImgDTO imgDTO);
    //
    @WebMethod(operationName = "deleteImg")
    
    public String deleteImg(@WebParam(name = "imgDTOId") Long id);
    //
    @WebMethod(operationName = "deleteListImg")
    
    public String deleteListImg(@WebParam(name = "imgListDTO") List<ImgDTO> imgListDTO);
    //
    @WebMethod(operationName = "findImgById")
    
    public ImgDTO findImgById(@WebParam(name = "imgDTOId") Long id);  
    //
    @WebMethod(operationName = "insertImg")
    
    public ResultDTO insertImg(@WebParam(name="imgDTO") ImgDTO imgDTO);
    //
    @WebMethod(operationName = "insertOrUpdateListImg")
    
    public String insertOrUpdateListImg(@WebParam(name = "imgDTO") List<ImgDTO> imgDTO);   
    //
    @WebMethod(operationName = "getSequenseImg")
    
    public List<String> getSequenseImg(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
    //    
    @WebMethod(operationName = "getListImgByCondition")
    
    public List<ImgDTO> getListImgByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
