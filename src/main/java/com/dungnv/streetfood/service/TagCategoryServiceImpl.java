
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;
import com.dungnv.streetfood.dto.TagCategoryDTO;
import com.dungnv.streetfood.business.TagCategoryBusinessInterface;
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
* @since 1/24/2016 7:34 PM
*/
@WebService(endpointInterface = "com.dungnv.streetfood.service.TagCategoryService")
public class TagCategoryServiceImpl implements TagCategoryService {
    @Autowired
    TagCategoryBusinessInterface tagCategoryBusiness;
    
   
    @Override
    public String updateTagCategory(TagCategoryDTO tagCategoryDTO) {
        return tagCategoryBusiness.update(tagCategoryDTO);
    }

    @Override
    public String deleteTagCategory(Long id) {
        return tagCategoryBusiness.delete(id);
    }

    @Override
    public String deleteListTagCategory(List<TagCategoryDTO> tagCategoryListDTO) {
        return tagCategoryBusiness.delete(tagCategoryListDTO);
    }

    @Override
    public TagCategoryDTO findTagCategoryById(Long id) {
        if (id != null && id > 0) {
            return (TagCategoryDTO)tagCategoryBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<TagCategoryDTO> getListTagCategoryDTO(TagCategoryDTO tagCategoryDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
         if (tagCategoryDTO != null ) {
            return tagCategoryBusiness.search(tagCategoryDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertTagCategory(TagCategoryDTO tagCategoryDTO) {
        return tagCategoryBusiness.createObject(tagCategoryDTO);
    }
    @Override
    public String insertOrUpdateListTagCategory(List<TagCategoryDTO> tagCategoryDTO) {
         return tagCategoryBusiness.insertList(tagCategoryDTO);
    }

    @Override
    public List<String> getSequenseTagCategory(String seqName, int... size) {
        int number = ( size[0] > 0 ? size[0] : 1 );
       
        return tagCategoryBusiness.getListSequense(seqName, number);
    }
    
       @Override
    public List<TagCategoryDTO> getListTagCategoryByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<TagCategoryDTO> lstTagCategory = new ArrayList<>();
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

        lstTagCategory = tagCategoryBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstTagCategory;
    }

}
