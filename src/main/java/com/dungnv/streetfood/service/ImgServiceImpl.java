
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;
import com.dungnv.streetfood.dto.ImgDTO;
import com.dungnv.streetfood.business.ImgBusinessInterface;
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
* @since 1/25/2016 10:05 PM
*/
@WebService(endpointInterface = "com.dungnv.streetfood.service.ImgService")
public class ImgServiceImpl implements ImgService {
    @Autowired
    ImgBusinessInterface imgBusiness;
    
   
    @Override
    public String updateImg(ImgDTO imgDTO) {
        return imgBusiness.update(imgDTO);
    }

    @Override
    public String deleteImg(Long id) {
        return imgBusiness.delete(id);
    }

    @Override
    public String deleteListImg(List<ImgDTO> imgListDTO) {
        return imgBusiness.delete(imgListDTO);
    }

    @Override
    public ImgDTO findImgById(Long id) {
        if (id != null && id > 0) {
            return (ImgDTO)imgBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<ImgDTO> getListImgDTO(ImgDTO imgDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
         if (imgDTO != null ) {
            return imgBusiness.search(imgDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertImg(ImgDTO imgDTO) {
        return imgBusiness.createObject(imgDTO);
    }
    @Override
    public String insertOrUpdateListImg(List<ImgDTO> imgDTO) {
         return imgBusiness.insertList(imgDTO);
    }

    @Override
    public List<String> getSequenseImg(String seqName, int... size) {
        int number = ( size[0] > 0 ? size[0] : 1 );
       
        return imgBusiness.getListSequense(seqName, number);
    }
    
       @Override
    public List<ImgDTO> getListImgByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<ImgDTO> lstImg = new ArrayList<>();
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

        lstImg = imgBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstImg;
    }

}
