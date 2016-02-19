
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.CommentDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import java.util.List;

import javax.jws.WebMethod;

import javax.jws.WebService;
import javax.jws.WebParam;
import com.dungnv.vfw5.base.dto.ResultDTO;
/**
* @author dungnv
* @version 1.0
* @since 1/25/2016 10:10 PM
*/
@WebService
public interface CommentService {
    @WebMethod(operationName = "getListCommentDTO")
    
    public List<CommentDTO> getListCommentDTO(@WebParam(name="commentDTO") CommentDTO commentDTO,@WebParam(name="rowStart") int rowStart,@WebParam(name="maxRow") int maxRow,@WebParam(name="sortType") String sortType,@WebParam(name="sortFieldList") String sortFieldList);
    //
    @WebMethod(operationName = "updateComment")
    
    public String updateComment(@WebParam(name = "commentDTO") CommentDTO commentDTO);
    //
    @WebMethod(operationName = "deleteComment")
    
    public String deleteComment(@WebParam(name = "commentDTOId") Long id);
    //
    @WebMethod(operationName = "deleteListComment")
    
    public String deleteListComment(@WebParam(name = "commentListDTO") List<CommentDTO> commentListDTO);
    //
    @WebMethod(operationName = "findCommentById")
    
    public CommentDTO findCommentById(@WebParam(name = "commentDTOId") Long id);  
    //
    @WebMethod(operationName = "insertComment")
    
    public ResultDTO insertComment(@WebParam(name="commentDTO") CommentDTO commentDTO);
    //
    @WebMethod(operationName = "insertOrUpdateListComment")
    
    public String insertOrUpdateListComment(@WebParam(name = "commentDTO") List<CommentDTO> commentDTO);   
    //
    @WebMethod(operationName = "getSequenseComment")
    
    public List<String> getSequenseComment(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
    //    
    @WebMethod(operationName = "getListCommentByCondition")
    
    public List<CommentDTO> getListCommentByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
