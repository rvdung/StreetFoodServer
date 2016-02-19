
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;
import com.dungnv.streetfood.dto.ArticleLanguageDTO;
import com.dungnv.streetfood.business.ArticleLanguageBusinessInterface;
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
* @since 1/25/2016 10:07 PM
*/
@WebService(endpointInterface = "com.dungnv.streetfood.service.ArticleLanguageService")
public class ArticleLanguageServiceImpl implements ArticleLanguageService {
    @Autowired
    ArticleLanguageBusinessInterface articleLanguageBusiness;
    
   
    @Override
    public String updateArticleLanguage(ArticleLanguageDTO articleLanguageDTO) {
        return articleLanguageBusiness.update(articleLanguageDTO);
    }

    @Override
    public String deleteArticleLanguage(Long id) {
        return articleLanguageBusiness.delete(id);
    }

    @Override
    public String deleteListArticleLanguage(List<ArticleLanguageDTO> articleLanguageListDTO) {
        return articleLanguageBusiness.delete(articleLanguageListDTO);
    }

    @Override
    public ArticleLanguageDTO findArticleLanguageById(Long id) {
        if (id != null && id > 0) {
            return (ArticleLanguageDTO)articleLanguageBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<ArticleLanguageDTO> getListArticleLanguageDTO(ArticleLanguageDTO articleLanguageDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
         if (articleLanguageDTO != null ) {
            return articleLanguageBusiness.search(articleLanguageDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertArticleLanguage(ArticleLanguageDTO articleLanguageDTO) {
        return articleLanguageBusiness.createObject(articleLanguageDTO);
    }
    @Override
    public String insertOrUpdateListArticleLanguage(List<ArticleLanguageDTO> articleLanguageDTO) {
         return articleLanguageBusiness.insertList(articleLanguageDTO);
    }

    @Override
    public List<String> getSequenseArticleLanguage(String seqName, int... size) {
        int number = ( size[0] > 0 ? size[0] : 1 );
       
        return articleLanguageBusiness.getListSequense(seqName, number);
    }
    
       @Override
    public List<ArticleLanguageDTO> getListArticleLanguageByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<ArticleLanguageDTO> lstArticleLanguage = new ArrayList<>();
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

        lstArticleLanguage = articleLanguageBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstArticleLanguage;
    }

}
