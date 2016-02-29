
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;
import com.dungnv.streetfood.dto.RestaurantLanguageDTO;
import com.dungnv.streetfood.business.RestaurantLanguageBusinessInterface;
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
* @since 2/21/2016 11:30 AM
*/
@WebService(endpointInterface = "com.dungnv.streetfood.service.RestaurantLanguageService")
public class RestaurantLanguageServiceImpl implements RestaurantLanguageService {
    @Autowired
    RestaurantLanguageBusinessInterface restaurantLanguageBusiness;
    
   
    @Override
    public String updateRestaurantLanguage(RestaurantLanguageDTO restaurantLanguageDTO) {
        return restaurantLanguageBusiness.update(restaurantLanguageDTO);
    }

    @Override
    public String deleteRestaurantLanguage(Long id) {
        return restaurantLanguageBusiness.delete(id);
    }

    @Override
    public String deleteListRestaurantLanguage(List<RestaurantLanguageDTO> restaurantLanguageListDTO) {
        return restaurantLanguageBusiness.delete(restaurantLanguageListDTO);
    }

    @Override
    public RestaurantLanguageDTO findRestaurantLanguageById(Long id) {
        if (id != null && id > 0) {
            return (RestaurantLanguageDTO)restaurantLanguageBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<RestaurantLanguageDTO> getListRestaurantLanguageDTO(RestaurantLanguageDTO restaurantLanguageDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
         if (restaurantLanguageDTO != null ) {
            return restaurantLanguageBusiness.search(restaurantLanguageDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertRestaurantLanguage(RestaurantLanguageDTO restaurantLanguageDTO) {
        return restaurantLanguageBusiness.createObject(restaurantLanguageDTO);
    }
    @Override
    public String insertOrUpdateListRestaurantLanguage(List<RestaurantLanguageDTO> restaurantLanguageDTO) {
         return restaurantLanguageBusiness.insertList(restaurantLanguageDTO);
    }

    @Override
    public List<String> getSequenseRestaurantLanguage(String seqName, int... size) {
        int number = ( size[0] > 0 ? size[0] : 1 );
       
        return restaurantLanguageBusiness.getListSequense(seqName, number);
    }
    
       @Override
    public List<RestaurantLanguageDTO> getListRestaurantLanguageByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<RestaurantLanguageDTO> lstRestaurantLanguage = new ArrayList<>();
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

        lstRestaurantLanguage = restaurantLanguageBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstRestaurantLanguage;
    }

}
