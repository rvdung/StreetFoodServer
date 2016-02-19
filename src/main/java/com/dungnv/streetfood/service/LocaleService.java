
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.LocaleDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import java.util.List;

import javax.jws.WebMethod;

import javax.jws.WebService;
import javax.jws.WebParam;
import com.dungnv.vfw5.base.dto.ResultDTO;
/**
* @author dungnv
* @version 1.0
* @since 1/22/2016 9:45 PM
*/
@WebService
public interface LocaleService {
    @WebMethod(operationName = "getListLocaleDTO")
    public List<LocaleDTO> getListLocaleDTO(@WebParam(name="localeDTO") LocaleDTO localeDTO,@WebParam(name="rowStart") int rowStart,@WebParam(name="maxRow") int maxRow,@WebParam(name="sortType") String sortType,@WebParam(name="sortFieldList") String sortFieldList);
    //
    @WebMethod(operationName = "updateLocale")
    
    public String updateLocale(@WebParam(name = "localeDTO") LocaleDTO localeDTO);
    //
    @WebMethod(operationName = "deleteLocale")
    
    public String deleteLocale(@WebParam(name = "localeDTOId") Long id);
    //
    @WebMethod(operationName = "deleteListLocale")
    
    public String deleteListLocale(@WebParam(name = "localeListDTO") List<LocaleDTO> localeListDTO);
    //
    @WebMethod(operationName = "findLocaleById")
    
    public LocaleDTO findLocaleById(@WebParam(name = "localeDTOId") Long id);  
    //
    @WebMethod(operationName = "insertLocale")
    
    public ResultDTO insertLocale(@WebParam(name="localeDTO") LocaleDTO localeDTO);
    //
    @WebMethod(operationName = "insertOrUpdateListLocale")
    
    public String insertOrUpdateListLocale(@WebParam(name = "localeDTO") List<LocaleDTO> localeDTO);   
    //
    @WebMethod(operationName = "getSequenseLocale")
    
    public List<String> getSequenseLocale(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
    //    
    @WebMethod(operationName = "getListLocaleByCondition")
    
    public List<LocaleDTO> getListLocaleByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
