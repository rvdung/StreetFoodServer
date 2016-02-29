
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.service;
import com.dungnv.streetfood.dto.SlideShowLanguageDTO;
import com.dungnv.streetfood.business.SlideShowLanguageBusinessInterface;
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
* @since 2/21/2016 1:04 PM
*/
@WebService(endpointInterface = "com.dungnv.streetfood.service.SlideShowLanguageService")
public class SlideShowLanguageServiceImpl implements SlideShowLanguageService {
    @Autowired
    SlideShowLanguageBusinessInterface slideShowLanguageBusiness;
    
   
    @Override
    public String updateSlideShowLanguage(SlideShowLanguageDTO slideShowLanguageDTO) {
        return slideShowLanguageBusiness.update(slideShowLanguageDTO);
    }

    @Override
    public String deleteSlideShowLanguage(Long id) {
        return slideShowLanguageBusiness.delete(id);
    }

    @Override
    public String deleteListSlideShowLanguage(List<SlideShowLanguageDTO> slideShowLanguageListDTO) {
        return slideShowLanguageBusiness.delete(slideShowLanguageListDTO);
    }

    @Override
    public SlideShowLanguageDTO findSlideShowLanguageById(Long id) {
        if (id != null && id > 0) {
            return (SlideShowLanguageDTO)slideShowLanguageBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<SlideShowLanguageDTO> getListSlideShowLanguageDTO(SlideShowLanguageDTO slideShowLanguageDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
         if (slideShowLanguageDTO != null ) {
            return slideShowLanguageBusiness.search(slideShowLanguageDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertSlideShowLanguage(SlideShowLanguageDTO slideShowLanguageDTO) {
        return slideShowLanguageBusiness.createObject(slideShowLanguageDTO);
    }
    @Override
    public String insertOrUpdateListSlideShowLanguage(List<SlideShowLanguageDTO> slideShowLanguageDTO) {
         return slideShowLanguageBusiness.insertList(slideShowLanguageDTO);
    }

    @Override
    public List<String> getSequenseSlideShowLanguage(String seqName, int... size) {
        int number = ( size[0] > 0 ? size[0] : 1 );
       
        return slideShowLanguageBusiness.getListSequense(seqName, number);
    }
    
       @Override
    public List<SlideShowLanguageDTO> getListSlideShowLanguageByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<SlideShowLanguageDTO> lstSlideShowLanguage = new ArrayList<>();
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

        lstSlideShowLanguage = slideShowLanguageBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstSlideShowLanguage;
    }

}
