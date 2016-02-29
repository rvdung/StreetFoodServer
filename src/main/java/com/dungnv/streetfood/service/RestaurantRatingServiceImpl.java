
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;
import com.dungnv.streetfood.dto.RestaurantRatingDTO;
import com.dungnv.streetfood.business.RestaurantRatingBusinessInterface;
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
* @since 2/21/2016 12:50 PM
*/
@WebService(endpointInterface = "com.dungnv.streetfood.service.RestaurantRatingService")
public class RestaurantRatingServiceImpl implements RestaurantRatingService {
    @Autowired
    RestaurantRatingBusinessInterface restaurantRatingBusiness;
    
   
    @Override
    public String updateRestaurantRating(RestaurantRatingDTO restaurantRatingDTO) {
        return restaurantRatingBusiness.update(restaurantRatingDTO);
    }

    @Override
    public String deleteRestaurantRating(Long id) {
        return restaurantRatingBusiness.delete(id);
    }

    @Override
    public String deleteListRestaurantRating(List<RestaurantRatingDTO> restaurantRatingListDTO) {
        return restaurantRatingBusiness.delete(restaurantRatingListDTO);
    }

    @Override
    public RestaurantRatingDTO findRestaurantRatingById(Long id) {
        if (id != null && id > 0) {
            return (RestaurantRatingDTO)restaurantRatingBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<RestaurantRatingDTO> getListRestaurantRatingDTO(RestaurantRatingDTO restaurantRatingDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
         if (restaurantRatingDTO != null ) {
            return restaurantRatingBusiness.search(restaurantRatingDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertRestaurantRating(RestaurantRatingDTO restaurantRatingDTO) {
        return restaurantRatingBusiness.createObject(restaurantRatingDTO);
    }
    @Override
    public String insertOrUpdateListRestaurantRating(List<RestaurantRatingDTO> restaurantRatingDTO) {
         return restaurantRatingBusiness.insertList(restaurantRatingDTO);
    }

    @Override
    public List<String> getSequenseRestaurantRating(String seqName, int... size) {
        int number = ( size[0] > 0 ? size[0] : 1 );
       
        return restaurantRatingBusiness.getListSequense(seqName, number);
    }
    
       @Override
    public List<RestaurantRatingDTO> getListRestaurantRatingByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<RestaurantRatingDTO> lstRestaurantRating = new ArrayList<>();
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

        lstRestaurantRating = restaurantRatingBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstRestaurantRating;
    }

}
