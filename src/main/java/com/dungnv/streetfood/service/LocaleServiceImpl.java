
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;
import com.dungnv.streetfood.dto.LocaleDTO;
import com.dungnv.streetfood.business.LocaleBusinessInterface;
import java.util.List;

import javax.jws.WebService;
import java.util.ArrayList;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import com.dungnv.vfw5.base.utils.ParamUtils;
import com.dungnv.vfw5.base.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.dungnv.vfw5.base.dto.ResultDTO;

/**
* @author dungnv
* @version 1.0
* @since 1/22/2016 9:45 PM
*/
@WebService(endpointInterface = "com.dungnv.streetfood.service.LocaleService")
public class LocaleServiceImpl implements LocaleService {
    @Autowired
    LocaleBusinessInterface localeBusiness;
    
   
    @Override
    public String updateLocale(LocaleDTO localeDTO) {
        return localeBusiness.update(localeDTO);
    }

    @Override
    public String deleteLocale(Long id) {
        return localeBusiness.delete(id);
    }

    @Override
    public String deleteListLocale(List<LocaleDTO> localeListDTO) {
        return localeBusiness.delete(localeListDTO);
    }

    @Override
    public LocaleDTO findLocaleById(Long id) {
        if (id != null && id > 0) {
            return (LocaleDTO)localeBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<LocaleDTO> getListLocaleDTO(LocaleDTO localeDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
         if (localeDTO != null ) {
            return localeBusiness.search(localeDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertLocale(LocaleDTO localeDTO) {
        return localeBusiness.createObject(localeDTO);
    }
    @Override
    public String insertOrUpdateListLocale(List<LocaleDTO> localeDTO) {
         return localeBusiness.insertList(localeDTO);
    }

    @Override
    public List<String> getSequenseLocale(String seqName, int... size) {
        int number = ( size[0] > 0 ? size[0] : 1 );
       
        return localeBusiness.getListSequense(seqName, number);
    }
    
       @Override
    public List<LocaleDTO> getListLocaleByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<LocaleDTO> lstLocale = new ArrayList<>();
        for (ConditionBean con : lstCondition) {
            if (con.getType().equalsIgnoreCase(ParamUtils.TYPE_DATE)) {
                con.setField(StringUtils.formatFunction("trunc", con.getField()));
            } else if (con.getType().equalsIgnoreCase(ParamUtils.NUMBER)) {
                con.setType(ParamUtils.TYPE_NUMBER);
            } else if (con.getType().equalsIgnoreCase(ParamUtils.NUMBER_DOUBLE)) {
                con.setType(ParamUtils.NUMBER_DOUBLE);
            } else {
                String value = "";
                if (con.getOperator().equalsIgnoreCase(ParamUtils.NAME_LIKE)) {
                    value = StringUtils.formatLike(con.getValue());
                } else{
                    value = con.getValue();
                }
                con.setValue(value.toLowerCase());
                con.setField(StringUtils.formatFunction("lower", con.getField()));
            }
            con.setOperator(StringUtils.convertTypeOperator(con.getOperator()));
        }

        lstLocale = localeBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstLocale;
    }

}
