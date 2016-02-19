
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;
import com.dungnv.streetfood.dto.TagsDTO;
import com.dungnv.streetfood.business.TagsBusinessInterface;
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
* @since 1/24/2016 8:00 PM
*/
@WebService(endpointInterface = "com.dungnv.streetfood.service.TagsService")
public class TagsServiceImpl implements TagsService {
    @Autowired
    TagsBusinessInterface tagsBusiness;
    
   
    @Override
    public String updateTags(TagsDTO tagsDTO) {
        return tagsBusiness.update(tagsDTO);
    }

    @Override
    public String deleteTags(Long id) {
        return tagsBusiness.delete(id);
    }

    @Override
    public String deleteListTags(List<TagsDTO> tagsListDTO) {
        return tagsBusiness.delete(tagsListDTO);
    }

    @Override
    public TagsDTO findTagsById(Long id) {
        if (id != null && id > 0) {
            return (TagsDTO)tagsBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<TagsDTO> getListTagsDTO(TagsDTO tagsDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
         if (tagsDTO != null ) {
            return tagsBusiness.search(tagsDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertTags(TagsDTO tagsDTO) {
        return tagsBusiness.createObject(tagsDTO);
    }
    @Override
    public String insertOrUpdateListTags(List<TagsDTO> tagsDTO) {
         return tagsBusiness.insertList(tagsDTO);
    }

    @Override
    public List<String> getSequenseTags(String seqName, int... size) {
        int number = ( size[0] > 0 ? size[0] : 1 );
       
        return tagsBusiness.getListSequense(seqName, number);
    }
    
       @Override
    public List<TagsDTO> getListTagsByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<TagsDTO> lstTags = new ArrayList<>();
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

        lstTags = tagsBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstTags;
    }

}
