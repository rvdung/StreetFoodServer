
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;
import com.dungnv.streetfood.dto.UserDTO;
import com.dungnv.streetfood.business.UserBusinessInterface;
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
* @since 1/31/2016 10:31 PM
*/
@WebService(endpointInterface = "com.dungnv.streetfood.service.UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserBusinessInterface userBusiness;
    
   
    @Override
    public String updateUser(UserDTO userDTO) {
        return userBusiness.update(userDTO);
    }

    @Override
    public String deleteUser(Long id) {
        return userBusiness.delete(id);
    }

    @Override
    public String deleteListUser(List<UserDTO> userListDTO) {
        return userBusiness.delete(userListDTO);
    }

    @Override
    public UserDTO findUserById(Long id) {
        if (id != null && id > 0) {
            return (UserDTO)userBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<UserDTO> getListUserDTO(UserDTO userDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
         if (userDTO != null ) {
            return userBusiness.search(userDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertUser(UserDTO userDTO) {
        return userBusiness.createObject(userDTO);
    }
    @Override
    public String insertOrUpdateListUser(List<UserDTO> userDTO) {
         return userBusiness.insertList(userDTO);
    }

    @Override
    public List<String> getSequenseUser(String seqName, int... size) {
        int number = ( size[0] > 0 ? size[0] : 1 );
       
        return userBusiness.getListSequense(seqName, number);
    }
    
       @Override
    public List<UserDTO> getListUserByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<UserDTO> lstUser = new ArrayList<>();
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

        lstUser = userBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstUser;
    }

}
