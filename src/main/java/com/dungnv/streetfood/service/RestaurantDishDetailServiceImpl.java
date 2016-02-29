
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;
import com.dungnv.streetfood.dto.RestaurantDishDetailDTO;
import com.dungnv.streetfood.business.RestaurantDishDetailBusinessInterface;
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
* @since 2/21/2016 12:49 PM
*/
@WebService(endpointInterface = "com.dungnv.streetfood.service.RestaurantDishDetailService")
public class RestaurantDishDetailServiceImpl implements RestaurantDishDetailService {
    @Autowired
    RestaurantDishDetailBusinessInterface restaurantDishDetailBusiness;
    
   
    @Override
    public String updateRestaurantDishDetail(RestaurantDishDetailDTO restaurantDishDetailDTO) {
        return restaurantDishDetailBusiness.update(restaurantDishDetailDTO);
    }

    @Override
    public String deleteRestaurantDishDetail(Long id) {
        return restaurantDishDetailBusiness.delete(id);
    }

    @Override
    public String deleteListRestaurantDishDetail(List<RestaurantDishDetailDTO> restaurantDishDetailListDTO) {
        return restaurantDishDetailBusiness.delete(restaurantDishDetailListDTO);
    }

    @Override
    public RestaurantDishDetailDTO findRestaurantDishDetailById(Long id) {
        if (id != null && id > 0) {
            return (RestaurantDishDetailDTO)restaurantDishDetailBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<RestaurantDishDetailDTO> getListRestaurantDishDetailDTO(RestaurantDishDetailDTO restaurantDishDetailDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
         if (restaurantDishDetailDTO != null ) {
            return restaurantDishDetailBusiness.search(restaurantDishDetailDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertRestaurantDishDetail(RestaurantDishDetailDTO restaurantDishDetailDTO) {
        return restaurantDishDetailBusiness.createObject(restaurantDishDetailDTO);
    }
    @Override
    public String insertOrUpdateListRestaurantDishDetail(List<RestaurantDishDetailDTO> restaurantDishDetailDTO) {
         return restaurantDishDetailBusiness.insertList(restaurantDishDetailDTO);
    }

    @Override
    public List<String> getSequenseRestaurantDishDetail(String seqName, int... size) {
        int number = ( size[0] > 0 ? size[0] : 1 );
       
        return restaurantDishDetailBusiness.getListSequense(seqName, number);
    }
    
       @Override
    public List<RestaurantDishDetailDTO> getListRestaurantDishDetailByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<RestaurantDishDetailDTO> lstRestaurantDishDetail = new ArrayList<>();
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

        lstRestaurantDishDetail = restaurantDishDetailBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstRestaurantDishDetail;
    }

}
