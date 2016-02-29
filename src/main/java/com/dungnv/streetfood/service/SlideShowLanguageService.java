
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.SlideShowLanguageDTO;
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
* @since 2/21/2016 1:04 PM
*/
@WebService
public interface SlideShowLanguageService {
    @WebMethod(operationName = "getListSlideShowLanguageDTO")
    
    public List<SlideShowLanguageDTO> getListSlideShowLanguageDTO(@WebParam(name="slideShowLanguageDTO") SlideShowLanguageDTO slideShowLanguageDTO,@WebParam(name="rowStart") int rowStart,@WebParam(name="maxRow") int maxRow,@WebParam(name="sortType") String sortType,@WebParam(name="sortFieldList") String sortFieldList);
    //
    @WebMethod(operationName = "updateSlideShowLanguage")
    
    public String updateSlideShowLanguage(@WebParam(name = "slideShowLanguageDTO") SlideShowLanguageDTO slideShowLanguageDTO);
    //
    @WebMethod(operationName = "deleteSlideShowLanguage")
    
    public String deleteSlideShowLanguage(@WebParam(name = "slideShowLanguageDTOId") Long id);
    //
    @WebMethod(operationName = "deleteListSlideShowLanguage")
    
    public String deleteListSlideShowLanguage(@WebParam(name = "slideShowLanguageListDTO") List<SlideShowLanguageDTO> slideShowLanguageListDTO);
    //
    @WebMethod(operationName = "findSlideShowLanguageById")
    
    public SlideShowLanguageDTO findSlideShowLanguageById(@WebParam(name = "slideShowLanguageDTOId") Long id);  
    //
    @WebMethod(operationName = "insertSlideShowLanguage")
    
    public ResultDTO insertSlideShowLanguage(@WebParam(name="slideShowLanguageDTO") SlideShowLanguageDTO slideShowLanguageDTO);
    //
    @WebMethod(operationName = "insertOrUpdateListSlideShowLanguage")
    
    public String insertOrUpdateListSlideShowLanguage(@WebParam(name = "slideShowLanguageDTO") List<SlideShowLanguageDTO> slideShowLanguageDTO);   
    //
    @WebMethod(operationName = "getSequenseSlideShowLanguage")
    
    public List<String> getSequenseSlideShowLanguage(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
    //    
    @WebMethod(operationName = "getListSlideShowLanguageByCondition")
    
    public List<SlideShowLanguageDTO> getListSlideShowLanguageByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
