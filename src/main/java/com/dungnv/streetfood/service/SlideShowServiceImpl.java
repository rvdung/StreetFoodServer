
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;
import com.dungnv.streetfood.dto.SlideShowDTO;
import com.dungnv.streetfood.business.SlideShowBusinessInterface;
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
* @since 1/27/2016 11:48 PM
*/
@WebService(endpointInterface = "com.dungnv.streetfood.service.SlideShowService")
public class SlideShowServiceImpl implements SlideShowService {
    @Autowired
    SlideShowBusinessInterface slideShowBusiness;
    
   
    @Override
    public String updateSlideShow(SlideShowDTO slideShowDTO) {
        return slideShowBusiness.update(slideShowDTO);
    }

    @Override
    public String deleteSlideShow(Long id) {
        return slideShowBusiness.delete(id);
    }

    @Override
    public String deleteListSlideShow(List<SlideShowDTO> slideShowListDTO) {
        return slideShowBusiness.delete(slideShowListDTO);
    }

    @Override
    public SlideShowDTO findSlideShowById(Long id) {
        if (id != null && id > 0) {
            return (SlideShowDTO)slideShowBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<SlideShowDTO> getListSlideShowDTO(SlideShowDTO slideShowDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
         if (slideShowDTO != null ) {
            return slideShowBusiness.search(slideShowDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertSlideShow(SlideShowDTO slideShowDTO) {
        return slideShowBusiness.createObject(slideShowDTO);
    }
    @Override
    public String insertOrUpdateListSlideShow(List<SlideShowDTO> slideShowDTO) {
         return slideShowBusiness.insertList(slideShowDTO);
    }

    @Override
    public List<String> getSequenseSlideShow(String seqName, int... size) {
        int number = ( size[0] > 0 ? size[0] : 1 );
       
        return slideShowBusiness.getListSequense(seqName, number);
    }
    
       @Override
    public List<SlideShowDTO> getListSlideShowByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<SlideShowDTO> lstSlideShow = new ArrayList<>();
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

        lstSlideShow = slideShowBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstSlideShow;
    }

}
