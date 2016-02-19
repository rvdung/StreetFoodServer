
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;
import com.dungnv.streetfood.dto.DishGroupLangageDTO;
import com.dungnv.streetfood.business.DishGroupLangageBusinessInterface;
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
* @since 1/21/2016 9:12 PM
*/
@WebService(endpointInterface = "com.dungnv.streetfood.service.DishGroupLangageService")
public class DishGroupLangageServiceImpl implements DishGroupLangageService {
    @Autowired
    DishGroupLangageBusinessInterface dishGroupLangageBusiness;
    
   
    @Override
    public String updateDishGroupLangage(DishGroupLangageDTO dishGroupLangageDTO) {
        return dishGroupLangageBusiness.update(dishGroupLangageDTO);
    }

    @Override
    public String deleteDishGroupLangage(Long id) {
        return dishGroupLangageBusiness.delete(id);
    }

    @Override
    public String deleteListDishGroupLangage(List<DishGroupLangageDTO> dishGroupLangageListDTO) {
        return dishGroupLangageBusiness.delete(dishGroupLangageListDTO);
    }

    @Override
    public DishGroupLangageDTO findDishGroupLangageById(Long id) {
        if (id != null && id > 0) {
            return (DishGroupLangageDTO)dishGroupLangageBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<DishGroupLangageDTO> getListDishGroupLangageDTO(DishGroupLangageDTO dishGroupLangageDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
         if (dishGroupLangageDTO != null ) {
            return dishGroupLangageBusiness.search(dishGroupLangageDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertDishGroupLangage(DishGroupLangageDTO dishGroupLangageDTO) {
        return dishGroupLangageBusiness.createObject(dishGroupLangageDTO);
    }
    @Override
    public String insertOrUpdateListDishGroupLangage(List<DishGroupLangageDTO> dishGroupLangageDTO) {
         return dishGroupLangageBusiness.insertList(dishGroupLangageDTO);
    }

    @Override
    public List<String> getSequenseDishGroupLangage(String seqName, int... size) {
        int number = ( size[0] > 0 ? size[0] : 1 );
       
        return dishGroupLangageBusiness.getListSequense(seqName, number);
    }
    
       @Override
    public List<DishGroupLangageDTO> getListDishGroupLangageByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<DishGroupLangageDTO> lstDishGroupLangage = new ArrayList<>();
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

        lstDishGroupLangage = dishGroupLangageBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstDishGroupLangage;
    }

}
