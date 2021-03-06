
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;
import com.dungnv.streetfood.dto.TagRestaurantDTO;
import com.dungnv.streetfood.business.TagRestaurantBusinessInterface;
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
* @since 2/21/2016 12:58 PM
*/
@WebService(endpointInterface = "com.dungnv.streetfood.service.TagRestaurantService")
public class TagRestaurantServiceImpl implements TagRestaurantService {
    @Autowired
    TagRestaurantBusinessInterface tagRestaurantBusiness;
    
   
    @Override
    public String updateTagRestaurant(TagRestaurantDTO tagRestaurantDTO) {
        return tagRestaurantBusiness.update(tagRestaurantDTO);
    }

    @Override
    public String deleteTagRestaurant(Long id) {
        return tagRestaurantBusiness.delete(id);
    }

    @Override
    public String deleteListTagRestaurant(List<TagRestaurantDTO> tagRestaurantListDTO) {
        return tagRestaurantBusiness.delete(tagRestaurantListDTO);
    }

    @Override
    public TagRestaurantDTO findTagRestaurantById(Long id) {
        if (id != null && id > 0) {
            return (TagRestaurantDTO)tagRestaurantBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<TagRestaurantDTO> getListTagRestaurantDTO(TagRestaurantDTO tagRestaurantDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
         if (tagRestaurantDTO != null ) {
            return tagRestaurantBusiness.search(tagRestaurantDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertTagRestaurant(TagRestaurantDTO tagRestaurantDTO) {
        return tagRestaurantBusiness.createObject(tagRestaurantDTO);
    }
    @Override
    public String insertOrUpdateListTagRestaurant(List<TagRestaurantDTO> tagRestaurantDTO) {
         return tagRestaurantBusiness.insertList(tagRestaurantDTO);
    }

    @Override
    public List<String> getSequenseTagRestaurant(String seqName, int... size) {
        int number = ( size[0] > 0 ? size[0] : 1 );
       
        return tagRestaurantBusiness.getListSequense(seqName, number);
    }
    
       @Override
    public List<TagRestaurantDTO> getListTagRestaurantByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<TagRestaurantDTO> lstTagRestaurant = new ArrayList<>();
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

        lstTagRestaurant = tagRestaurantBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstTagRestaurant;
    }

}
