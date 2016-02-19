
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;
import com.dungnv.streetfood.dto.CategoryDTO;
import com.dungnv.streetfood.business.CategoryBusinessInterface;
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
* @since 1/22/2016 9:48 PM
*/
@WebService(endpointInterface = "com.dungnv.streetfood.service.CategoryService")
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryBusinessInterface categoryBusiness;
    
   
    @Override
    public String updateCategory(CategoryDTO categoryDTO) {
        return categoryBusiness.update(categoryDTO);
    }

    @Override
    public String deleteCategory(Long id) {
        return categoryBusiness.delete(id);
    }

    @Override
    public String deleteListCategory(List<CategoryDTO> categoryListDTO) {
        return categoryBusiness.delete(categoryListDTO);
    }

    @Override
    public CategoryDTO findCategoryById(Long id) {
        if (id != null && id > 0) {
            return (CategoryDTO)categoryBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<CategoryDTO> getListCategoryDTO(CategoryDTO categoryDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
         if (categoryDTO != null ) {
            return categoryBusiness.search(categoryDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertCategory(CategoryDTO categoryDTO) {
        return categoryBusiness.createObject(categoryDTO);
    }
    @Override
    public String insertOrUpdateListCategory(List<CategoryDTO> categoryDTO) {
         return categoryBusiness.insertList(categoryDTO);
    }

    @Override
    public List<String> getSequenseCategory(String seqName, int... size) {
        int number = ( size[0] > 0 ? size[0] : 1 );
       
        return categoryBusiness.getListSequense(seqName, number);
    }
    
       @Override
    public List<CategoryDTO> getListCategoryByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<CategoryDTO> lstCategory = new ArrayList<>();
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

        lstCategory = categoryBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstCategory;
    }

}
