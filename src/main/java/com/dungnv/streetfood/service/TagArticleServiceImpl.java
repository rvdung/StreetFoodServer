
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;
import com.dungnv.streetfood.dto.TagArticleDTO;
import com.dungnv.streetfood.business.TagArticleBusinessInterface;
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
* @since 2/21/2016 12:59 PM
*/
@WebService(endpointInterface = "com.dungnv.streetfood.service.TagArticleService")
public class TagArticleServiceImpl implements TagArticleService {
    @Autowired
    TagArticleBusinessInterface tagArticleBusiness;
    
   
    @Override
    public String updateTagArticle(TagArticleDTO tagArticleDTO) {
        return tagArticleBusiness.update(tagArticleDTO);
    }

    @Override
    public String deleteTagArticle(Long id) {
        return tagArticleBusiness.delete(id);
    }

    @Override
    public String deleteListTagArticle(List<TagArticleDTO> tagArticleListDTO) {
        return tagArticleBusiness.delete(tagArticleListDTO);
    }

    @Override
    public TagArticleDTO findTagArticleById(Long id) {
        if (id != null && id > 0) {
            return (TagArticleDTO)tagArticleBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<TagArticleDTO> getListTagArticleDTO(TagArticleDTO tagArticleDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
         if (tagArticleDTO != null ) {
            return tagArticleBusiness.search(tagArticleDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertTagArticle(TagArticleDTO tagArticleDTO) {
        return tagArticleBusiness.createObject(tagArticleDTO);
    }
    @Override
    public String insertOrUpdateListTagArticle(List<TagArticleDTO> tagArticleDTO) {
         return tagArticleBusiness.insertList(tagArticleDTO);
    }

    @Override
    public List<String> getSequenseTagArticle(String seqName, int... size) {
        int number = ( size[0] > 0 ? size[0] : 1 );
       
        return tagArticleBusiness.getListSequense(seqName, number);
    }
    
       @Override
    public List<TagArticleDTO> getListTagArticleByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<TagArticleDTO> lstTagArticle = new ArrayList<>();
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

        lstTagArticle = tagArticleBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstTagArticle;
    }

}
