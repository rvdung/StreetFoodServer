
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.SlideShowDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import com.dungnv.vfw5.base.dto.ResultDTO;
/**
* @author dungnv
* @version 1.0
* @since 1/27/2016 11:48 PM
*/
@WebService
public interface SlideShowService {
    @WebMethod(operationName = "getListSlideShowDTO")
    public List<SlideShowDTO> getListSlideShowDTO(@WebParam(name="slideShowDTO") SlideShowDTO slideShowDTO,@WebParam(name="rowStart") int rowStart,@WebParam(name="maxRow") int maxRow,@WebParam(name="sortType") String sortType,@WebParam(name="sortFieldList") String sortFieldList);
    //
    @WebMethod(operationName = "updateSlideShow")
    public String updateSlideShow(@WebParam(name = "slideShowDTO") SlideShowDTO slideShowDTO);
    //
    @WebMethod(operationName = "deleteSlideShow")
    public String deleteSlideShow(@WebParam(name = "slideShowDTOId") Long id);
    //
    @WebMethod(operationName = "deleteListSlideShow")
    public String deleteListSlideShow(@WebParam(name = "slideShowListDTO") List<SlideShowDTO> slideShowListDTO);
    //
    @WebMethod(operationName = "findSlideShowById")
    public SlideShowDTO findSlideShowById(@WebParam(name = "slideShowDTOId") Long id);  
    //
    @WebMethod(operationName = "insertSlideShow")
    public ResultDTO insertSlideShow(@WebParam(name="slideShowDTO") SlideShowDTO slideShowDTO);
    //
    @WebMethod(operationName = "insertOrUpdateListSlideShow")
    public String insertOrUpdateListSlideShow(@WebParam(name = "slideShowDTO") List<SlideShowDTO> slideShowDTO);   
    //
    @WebMethod(operationName = "getSequenseSlideShow")
    public List<String> getSequenseSlideShow(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
    //    
    @WebMethod(operationName = "getListSlideShowByCondition")
    public List<SlideShowDTO> getListSlideShowByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
