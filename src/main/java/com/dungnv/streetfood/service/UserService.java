
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.UserDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import com.dungnv.vfw5.base.dto.ResultDTO;
/**
* @author dungnv
* @version 1.0
* @since 1/31/2016 10:31 PM
*/
@WebService
public interface UserService {
    @WebMethod(operationName = "getListUserDTO")
    public List<UserDTO> getListUserDTO(@WebParam(name="userDTO") UserDTO userDTO,@WebParam(name="rowStart") int rowStart,@WebParam(name="maxRow") int maxRow,@WebParam(name="sortType") String sortType,@WebParam(name="sortFieldList") String sortFieldList);
    //
    @WebMethod(operationName = "updateUser")
    public String updateUser(@WebParam(name = "userDTO") UserDTO userDTO);
    //
    @WebMethod(operationName = "deleteUser")
    public String deleteUser(@WebParam(name = "userDTOId") Long id);
    //
    @WebMethod(operationName = "deleteListUser")
    public String deleteListUser(@WebParam(name = "userListDTO") List<UserDTO> userListDTO);
    //
    @WebMethod(operationName = "findUserById")
    public UserDTO findUserById(@WebParam(name = "userDTOId") Long id);  
    //
    @WebMethod(operationName = "insertUser")
    public ResultDTO insertUser(@WebParam(name="userDTO") UserDTO userDTO);
    //
    @WebMethod(operationName = "insertOrUpdateListUser")
    public String insertOrUpdateListUser(@WebParam(name = "userDTO") List<UserDTO> userDTO);   
    //
    @WebMethod(operationName = "getSequenseUser")
    public List<String> getSequenseUser(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
    //    
    @WebMethod(operationName = "getListUserByCondition")
    public List<UserDTO> getListUserByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
