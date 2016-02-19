/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.DishDTO;
import com.dungnv.streetfood.model.Dish;
import com.dungnv.streetfood.dao.DishDAO;
import com.dungnv.streetfood.dto.DishLanguageDTO;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.utils.Constants;
import com.dungnv.vfw5.base.utils.DataUtil;
import com.dungnv.vfw5.base.utils.DateTimeUtils;
import com.dungnv.vfw5.base.utils.LanguageBundleUtils;
import com.dungnv.vfw5.base.utils.ParamUtils;
import com.dungnv.vfw5.base.utils.StringUtils;
import java.util.Locale;
import org.hibernate.Session;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionInterceptor;
/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/25/2016 10:01 PM
 */
@Service("dishBusiness")
@Transactional
public class DishBusiness extends BaseFWServiceImpl<DishDAO, DishDTO, Dish> implements DishBusinessInterface{
	
    @Autowired
    private DishDAO dishDAO;
    @Autowired
    private DishLanguageBusinessInterface dishLanguageBusiness;
    @Autowired
    private TagDishBusinessInterface tagDishBusiness;

    public DishBusiness() {
        tModel = new Dish();
        tDAO = dishDAO;
    }
    @Override
    public DishDAO gettDAO() {
        return dishDAO;
    }
    
    public DishBusiness(Session session) {
        this.session = session;
        tModel = new Dish();
        tDAO = dishDAO;
    }

    @Override
    public ResultDTO insertDish(String userName, String localeCode, String countryCode, String token, DishDTO dto) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);

        String validate = validate(locale, dto, Constants.ACTION_TYPE.INSERT);
        if (!StringUtils.isNullOrEmpty(validate)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(validate);
            return result;
        }
        String currDate = null;
        String currDateGMT = null;
        try {
            currDate = DateTimeUtils.getSysDateTime(false);
        } catch (Exception ex) {
        }
        try {
            currDateGMT = DateTimeUtils.getSysDateTime(true);
        } catch (Exception ex) {

        }
        dto.setDishCreateTime(currDate);
        dto.setDishUpdateTime(currDate);
        dto.setDishCreateTimeGmt(currDateGMT);
        dto.setDishUpdateTimeGmt(currDateGMT);
        if (StringUtils.isNullOrEmpty(dto.getCommentCount())) {
            dto.setCommentCount("0");
        }
        if (StringUtils.isNullOrEmpty(dto.getDishStatus())) {
            dto.setDishStatus("1");
        }
        if (StringUtils.isNullOrEmpty(dto.getRating())) {
            dto.setRating("0");
        }
        if (StringUtils.isNullOrEmpty(dto.getShareCount())) {
            dto.setShareCount("0");
        }
        if (StringUtils.isNullOrEmpty(dto.getViewCount())) {
            dto.setShareCount("0");
        }
      
        result = createObject(dto);
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }

        dto.setId(result.getId());

        //<editor-fold defaultstate="collapsed" desc="save language">
        // Ghi danh sách ngôn ngữ
        if (dto.getListLanguage() != null && !dto.getListLanguage().isEmpty()) {
            for (DishLanguageDTO langDTO : dto.getListLanguage()) {
                langDTO.setDishId(dto.getId());
                result = dishLanguageBusiness.insertDishLanguage(userName, localeCode, countryCode, token, langDTO);
                if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                    TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                    return result;
                }
            }
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="save tag">
        result = tagDishBusiness.insertTagDish(userName, localeCode, countryCode, token, Long.valueOf(dto.getId()), dto.getListTag());
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="save img">
        if(!StringUtils.isNullOrEmpty(dto.getImgId())){
            
        }
//</editor-fold>

        return result;
    }

    @Override
    public ResultDTO updateDish(String userName, String localeCode, String countryCode, String token, DishDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultDTO deleteDish(String userName, String localeCode, String countryCode, String token, String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultDTO activeDish(String userName, String localeCode, String countryCode, String token, String id, Boolean active) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    private String validate(Locale locale, DishDTO dto, String action) {
        if (dto == null) {
            return LanguageBundleUtils.getString(locale, "message.dish.model.null");
        }
        if (dto.getName() == null) {
            return LanguageBundleUtils.getString(locale, "message.dish.name.null");
        }
        if (dto.getName().length() > 255) {
            return LanguageBundleUtils.getString(locale, "message.dish.name.overLength.255");
        }

        return null;
    }
}


