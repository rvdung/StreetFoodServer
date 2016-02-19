
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;
import com.dungnv.streetfood.dto.CommentDTO;
import com.dungnv.streetfood.business.CommentBusinessInterface;
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
* @since 1/25/2016 10:10 PM
*/
@WebService(endpointInterface = "com.dungnv.streetfood.service.CommentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentBusinessInterface commentBusiness;
    
   
    @Override
    public String updateComment(CommentDTO commentDTO) {
        return commentBusiness.update(commentDTO);
    }

    @Override
    public String deleteComment(Long id) {
        return commentBusiness.delete(id);
    }

    @Override
    public String deleteListComment(List<CommentDTO> commentListDTO) {
        return commentBusiness.delete(commentListDTO);
    }

    @Override
    public CommentDTO findCommentById(Long id) {
        if (id != null && id > 0) {
            return (CommentDTO)commentBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<CommentDTO> getListCommentDTO(CommentDTO commentDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
         if (commentDTO != null ) {
            return commentBusiness.search(commentDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertComment(CommentDTO commentDTO) {
        return commentBusiness.createObject(commentDTO);
    }
    @Override
    public String insertOrUpdateListComment(List<CommentDTO> commentDTO) {
         return commentBusiness.insertList(commentDTO);
    }

    @Override
    public List<String> getSequenseComment(String seqName, int... size) {
        int number = ( size[0] > 0 ? size[0] : 1 );
       
        return commentBusiness.getListSequense(seqName, number);
    }
    
       @Override
    public List<CommentDTO> getListCommentByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<CommentDTO> lstComment = new ArrayList<>();
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

        lstComment = commentBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstComment;
    }

}
