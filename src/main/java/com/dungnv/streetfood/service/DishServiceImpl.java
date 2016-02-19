
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;
import com.dungnv.streetfood.dto.DishDTO;
import com.dungnv.streetfood.business.DishBusinessInterface;
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
* @since 1/25/2016 10:01 PM
*/
@WebService(endpointInterface = "com.dungnv.streetfood.service.DishService")
public class DishServiceImpl implements DishService {
    @Autowired
    DishBusinessInterface dishBusiness;
    
   
    @Override
    public String updateDish(DishDTO dishDTO) {
        return dishBusiness.update(dishDTO);
    }

    @Override
    public String deleteDish(Long id) {
        return dishBusiness.delete(id);
    }

    @Override
    public String deleteListDish(List<DishDTO> dishListDTO) {
        return dishBusiness.delete(dishListDTO);
    }

    @Override
    public DishDTO findDishById(Long id) {
        if (id != null && id > 0) {
            return (DishDTO)dishBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<DishDTO> getListDishDTO(DishDTO dishDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
         if (dishDTO != null ) {
            return dishBusiness.search(dishDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertDish(DishDTO dishDTO) {
        return dishBusiness.createObject(dishDTO);
    }
    @Override
    public String insertOrUpdateListDish(List<DishDTO> dishDTO) {
         return dishBusiness.insertList(dishDTO);
    }

    @Override
    public List<String> getSequenseDish(String seqName, int... size) {
        int number = ( size[0] > 0 ? size[0] : 1 );
       
        return dishBusiness.getListSequense(seqName, number);
    }
    
       @Override
    public List<DishDTO> getListDishByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<DishDTO> lstDish = new ArrayList<>();
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

        lstDish = dishBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstDish;
    }

}
