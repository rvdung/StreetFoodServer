
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;
import com.dungnv.streetfood.dto.DishArticleDTO;
import com.dungnv.streetfood.business.DishArticleBusinessInterface;
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
* @since 2/29/2016 10:24 PM
*/
@WebService(endpointInterface = "com.dungnv.streetfood.service.DishArticleService")
public class DishArticleServiceImpl implements DishArticleService {
    @Autowired
    DishArticleBusinessInterface dishArticleBusiness;
    
   
    @Override
    public String updateDishArticle(DishArticleDTO dishArticleDTO) {
        return dishArticleBusiness.update(dishArticleDTO);
    }

    @Override
    public String deleteDishArticle(Long id) {
        return dishArticleBusiness.delete(id);
    }

    @Override
    public String deleteListDishArticle(List<DishArticleDTO> dishArticleListDTO) {
        return dishArticleBusiness.delete(dishArticleListDTO);
    }

    @Override
    public DishArticleDTO findDishArticleById(Long id) {
        if (id != null && id > 0) {
            return (DishArticleDTO)dishArticleBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<DishArticleDTO> getListDishArticleDTO(DishArticleDTO dishArticleDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
         if (dishArticleDTO != null ) {
            return dishArticleBusiness.search(dishArticleDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertDishArticle(DishArticleDTO dishArticleDTO) {
        return dishArticleBusiness.createObject(dishArticleDTO);
    }
    @Override
    public String insertOrUpdateListDishArticle(List<DishArticleDTO> dishArticleDTO) {
         return dishArticleBusiness.insertList(dishArticleDTO);
    }

    @Override
    public List<String> getSequenseDishArticle(String seqName, int... size) {
        int number = ( size[0] > 0 ? size[0] : 1 );
       
        return dishArticleBusiness.getListSequense(seqName, number);
    }
    
       @Override
    public List<DishArticleDTO> getListDishArticleByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<DishArticleDTO> lstDishArticle = new ArrayList<>();
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

        lstDishArticle = dishArticleBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstDishArticle;
    }

}
