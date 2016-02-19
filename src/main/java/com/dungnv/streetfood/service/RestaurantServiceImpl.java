
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;
import com.dungnv.streetfood.dto.RestaurantDTO;
import com.dungnv.streetfood.business.RestaurantBusinessInterface;
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
* @since 1/27/2016 11:45 PM
*/
@WebService(endpointInterface = "com.dungnv.streetfood.service.RestaurantService")
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    RestaurantBusinessInterface restaurantBusiness;
    
   
    @Override
    public String updateRestaurant(RestaurantDTO restaurantDTO) {
        return restaurantBusiness.update(restaurantDTO);
    }

    @Override
    public String deleteRestaurant(Long id) {
        return restaurantBusiness.delete(id);
    }

    @Override
    public String deleteListRestaurant(List<RestaurantDTO> restaurantListDTO) {
        return restaurantBusiness.delete(restaurantListDTO);
    }

    @Override
    public RestaurantDTO findRestaurantById(Long id) {
        if (id != null && id > 0) {
            return (RestaurantDTO)restaurantBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<RestaurantDTO> getListRestaurantDTO(RestaurantDTO restaurantDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
         if (restaurantDTO != null ) {
            return restaurantBusiness.search(restaurantDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertRestaurant(RestaurantDTO restaurantDTO) {
        return restaurantBusiness.createObject(restaurantDTO);
    }
    @Override
    public String insertOrUpdateListRestaurant(List<RestaurantDTO> restaurantDTO) {
         return restaurantBusiness.insertList(restaurantDTO);
    }

    @Override
    public List<String> getSequenseRestaurant(String seqName, int... size) {
        int number = ( size[0] > 0 ? size[0] : 1 );
       
        return restaurantBusiness.getListSequense(seqName, number);
    }
    
       @Override
    public List<RestaurantDTO> getListRestaurantByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<RestaurantDTO> lstRestaurant = new ArrayList<>();
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

        lstRestaurant = restaurantBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstRestaurant;
    }

}
