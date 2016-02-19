
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;
import com.dungnv.streetfood.dto.CategoryDishDTO;
import com.dungnv.streetfood.business.CategoryDishBusinessInterface;
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
* @since 1/25/2016 10:09 PM
*/
@WebService(endpointInterface = "com.dungnv.streetfood.service.CategoryDishService")
public class CategoryDishServiceImpl implements CategoryDishService {
    @Autowired
    CategoryDishBusinessInterface categoryDishBusiness;
    
   
    @Override
    public String updateCategoryDish(CategoryDishDTO categoryDishDTO) {
        return categoryDishBusiness.update(categoryDishDTO);
    }

    @Override
    public String deleteCategoryDish(Long id) {
        return categoryDishBusiness.delete(id);
    }

    @Override
    public String deleteListCategoryDish(List<CategoryDishDTO> categoryDishListDTO) {
        return categoryDishBusiness.delete(categoryDishListDTO);
    }

    @Override
    public CategoryDishDTO findCategoryDishById(Long id) {
        if (id != null && id > 0) {
            return (CategoryDishDTO)categoryDishBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<CategoryDishDTO> getListCategoryDishDTO(CategoryDishDTO categoryDishDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
         if (categoryDishDTO != null ) {
            return categoryDishBusiness.search(categoryDishDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertCategoryDish(CategoryDishDTO categoryDishDTO) {
        return categoryDishBusiness.createObject(categoryDishDTO);
    }
    @Override
    public String insertOrUpdateListCategoryDish(List<CategoryDishDTO> categoryDishDTO) {
         return categoryDishBusiness.insertList(categoryDishDTO);
    }

    @Override
    public List<String> getSequenseCategoryDish(String seqName, int... size) {
        int number = ( size[0] > 0 ? size[0] : 1 );
       
        return categoryDishBusiness.getListSequense(seqName, number);
    }
    
       @Override
    public List<CategoryDishDTO> getListCategoryDishByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<CategoryDishDTO> lstCategoryDish = new ArrayList<>();
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

        lstCategoryDish = categoryDishBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstCategoryDish;
    }

}
