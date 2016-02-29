
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;
import com.dungnv.streetfood.dto.RestaurantArticleDTO;
import com.dungnv.streetfood.business.RestaurantArticleBusinessInterface;
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
@WebService(endpointInterface = "com.dungnv.streetfood.service.RestaurantArticleService")
public class RestaurantArticleServiceImpl implements RestaurantArticleService {
    @Autowired
    RestaurantArticleBusinessInterface restaurantArticleBusiness;
    
   
    @Override
    public String updateRestaurantArticle(RestaurantArticleDTO restaurantArticleDTO) {
        return restaurantArticleBusiness.update(restaurantArticleDTO);
    }

    @Override
    public String deleteRestaurantArticle(Long id) {
        return restaurantArticleBusiness.delete(id);
    }

    @Override
    public String deleteListRestaurantArticle(List<RestaurantArticleDTO> restaurantArticleListDTO) {
        return restaurantArticleBusiness.delete(restaurantArticleListDTO);
    }

    @Override
    public RestaurantArticleDTO findRestaurantArticleById(Long id) {
        if (id != null && id > 0) {
            return (RestaurantArticleDTO)restaurantArticleBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<RestaurantArticleDTO> getListRestaurantArticleDTO(RestaurantArticleDTO restaurantArticleDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
         if (restaurantArticleDTO != null ) {
            return restaurantArticleBusiness.search(restaurantArticleDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertRestaurantArticle(RestaurantArticleDTO restaurantArticleDTO) {
        return restaurantArticleBusiness.createObject(restaurantArticleDTO);
    }
    @Override
    public String insertOrUpdateListRestaurantArticle(List<RestaurantArticleDTO> restaurantArticleDTO) {
         return restaurantArticleBusiness.insertList(restaurantArticleDTO);
    }

    @Override
    public List<String> getSequenseRestaurantArticle(String seqName, int... size) {
        int number = ( size[0] > 0 ? size[0] : 1 );
       
        return restaurantArticleBusiness.getListSequense(seqName, number);
    }
    
       @Override
    public List<RestaurantArticleDTO> getListRestaurantArticleByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<RestaurantArticleDTO> lstRestaurantArticle = new ArrayList<>();
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

        lstRestaurantArticle = restaurantArticleBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstRestaurantArticle;
    }

}
