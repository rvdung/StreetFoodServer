
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;
import com.dungnv.streetfood.dto.TagDishDTO;
import com.dungnv.streetfood.business.TagDishBusinessInterface;
import java.util.List;
import java.util.Locale;
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
* @since 1/26/2016 9:17 PM
*/
@WebService(endpointInterface = "com.dungnv.streetfood.service.TagDishService")
public class TagDishServiceImpl implements TagDishService {
    @Autowired
    TagDishBusinessInterface tagDishBusiness;
    
   
    @Override
    public String updateTagDish(TagDishDTO tagDishDTO) {
        return tagDishBusiness.update(tagDishDTO);
    }

    @Override
    public String deleteTagDish(Long id) {
        return tagDishBusiness.delete(id);
    }

    @Override
    public String deleteListTagDish(List<TagDishDTO> tagDishListDTO) {
        return tagDishBusiness.delete(tagDishListDTO);
    }

    @Override
    public TagDishDTO findTagDishById(Long id) {
        if (id != null && id > 0) {
            return (TagDishDTO)tagDishBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<TagDishDTO> getListTagDishDTO(TagDishDTO tagDishDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
         if (tagDishDTO != null ) {
            return tagDishBusiness.search(tagDishDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertTagDish(TagDishDTO tagDishDTO) {
        return tagDishBusiness.createObject(tagDishDTO);
    }
    @Override
    public String insertOrUpdateListTagDish(List<TagDishDTO> tagDishDTO) {
         return tagDishBusiness.insertList(tagDishDTO);
    }

    @Override
    public List<String> getSequenseTagDish(String seqName, int... size) {
        int number = ( size[0] > 0 ? size[0] : 1 );
       
        return tagDishBusiness.getListSequense(seqName, number);
    }
    
       @Override
    public List<TagDishDTO> getListTagDishByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<TagDishDTO> lstTagDish = new ArrayList<>();
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

        lstTagDish = tagDishBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstTagDish;
    }

}
