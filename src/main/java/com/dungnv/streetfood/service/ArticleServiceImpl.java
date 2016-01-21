
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.ArticleDTO;
import com.dungnv.streetfood.business.ArticleBusinessInterface;
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
 * @since 1/21/2016 12:48 AM
 */
@WebService(endpointInterface = "com.dungnv.streetfood.service.ArticleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleBusinessInterface articleBusiness;

    @Override
    public String updateArticle(ArticleDTO articleDTO) {
        return articleBusiness.update(articleDTO);
    }

    @Override
    public String deleteArticle(Long id) {
        return articleBusiness.delete(id);
    }

    @Override
    public String deleteListArticle(List<ArticleDTO> articleListDTO) {
        return articleBusiness.delete(articleListDTO);
    }

    @Override
    public ArticleDTO findArticleById(Long id) {
        if (id != null && id > 0) {
            return (ArticleDTO) articleBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<ArticleDTO> getListArticleDTO(ArticleDTO articleDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        if (articleDTO != null) {
            return articleBusiness.search(articleDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertArticle(ArticleDTO articleDTO) {
        return articleBusiness.createObject(articleDTO);
    }

    @Override
    public String insertOrUpdateListArticle(List<ArticleDTO> articleDTO) {
        return articleBusiness.insertList(articleDTO);
    }

    @Override
    public List<String> getSequenseArticle(String seqName, int... size) {
        int number = (size[0] > 0 ? size[0] : 1);

        return articleBusiness.getListSequense(seqName, number);
    }

    @Override
    public List<ArticleDTO> getListArticleByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<ArticleDTO> lstArticle = new ArrayList<>();
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
                } else {
                    value = con.getValue();
                }
                con.setValue(value.toLowerCase());
                con.setField(StringUtils.formatFunction("lower", con.getField()));
            }
            con.setOperator(StringUtils.convertTypeOperator(con.getOperator()));
        }

        lstArticle = articleBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstArticle;
    }

}
