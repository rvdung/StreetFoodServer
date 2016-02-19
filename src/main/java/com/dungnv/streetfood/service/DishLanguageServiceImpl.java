
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;
import com.dungnv.streetfood.dto.DishLanguageDTO;
import com.dungnv.streetfood.business.DishLanguageBusinessInterface;
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
* @since 1/25/2016 10:02 PM
*/
@WebService(endpointInterface = "com.dungnv.streetfood.service.DishLanguageService")
public class DishLanguageServiceImpl implements DishLanguageService {
    @Autowired
    DishLanguageBusinessInterface dishLanguageBusiness;
    
   
    @Override
    public String updateDishLanguage(DishLanguageDTO dishLanguageDTO) {
        return dishLanguageBusiness.update(dishLanguageDTO);
    }

    @Override
    public String deleteDishLanguage(Long id) {
        return dishLanguageBusiness.delete(id);
    }

    @Override
    public String deleteListDishLanguage(List<DishLanguageDTO> dishLanguageListDTO) {
        return dishLanguageBusiness.delete(dishLanguageListDTO);
    }

    @Override
    public DishLanguageDTO findDishLanguageById(Long id) {
        if (id != null && id > 0) {
            return (DishLanguageDTO)dishLanguageBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<DishLanguageDTO> getListDishLanguageDTO(DishLanguageDTO dishLanguageDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
         if (dishLanguageDTO != null ) {
            return dishLanguageBusiness.search(dishLanguageDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertDishLanguage(DishLanguageDTO dishLanguageDTO) {
        return dishLanguageBusiness.createObject(dishLanguageDTO);
    }
    @Override
    public String insertOrUpdateListDishLanguage(List<DishLanguageDTO> dishLanguageDTO) {
         return dishLanguageBusiness.insertList(dishLanguageDTO);
    }

    @Override
    public List<String> getSequenseDishLanguage(String seqName, int... size) {
        int number = ( size[0] > 0 ? size[0] : 1 );
       
        return dishLanguageBusiness.getListSequense(seqName, number);
    }
    
       @Override
    public List<DishLanguageDTO> getListDishLanguageByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<DishLanguageDTO> lstDishLanguage = new ArrayList<>();
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

        lstDishLanguage = dishLanguageBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstDishLanguage;
    }

}
