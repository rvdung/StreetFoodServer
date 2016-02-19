/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.DishLanguageDTO;
import com.dungnv.streetfood.model.DishLanguage;
import com.dungnv.streetfood.dao.DishLanguageDAO;
import com.dungnv.streetfood.model.Dish;
import com.dungnv.streetfood.model.DishGroupLangage;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import com.dungnv.vfw5.base.utils.Constants;
import com.dungnv.vfw5.base.utils.DataUtil;
import com.dungnv.vfw5.base.utils.LanguageBundleUtils;
import com.dungnv.vfw5.base.utils.ParamUtils;
import com.dungnv.vfw5.base.utils.StringUtils;
import org.hibernate.Session;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/25/2016 10:02 PM
 */
@Service("dishLanguageBusiness")
@Transactional
public class DishLanguageBusiness extends BaseFWServiceImpl<DishLanguageDAO, DishLanguageDTO, DishLanguage> implements DishLanguageBusinessInterface {

    @Autowired
    private DishLanguageDAO dishLanguageDAO;
    @Autowired
    private LocaleBusinessInterface localeBusiness;
    @Autowired
    private DishBusinessInterface dishBusiness;

    public DishLanguageBusiness() {
        tModel = new DishLanguage();
        tDAO = dishLanguageDAO;
    }

    @Override
    public DishLanguageDAO gettDAO() {
        return dishLanguageDAO;
    }

    public DishLanguageBusiness(Session session) {
        this.session = session;
        tModel = new DishLanguage();
        tDAO = dishLanguageDAO;
    }

    @Override
    public ResultDTO insertDishLanguage(String userName, String localeCode, String countryCode, String token, DishLanguageDTO dto) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);

        String validate = validate(locale, dto, Constants.ACTION_TYPE.INSERT);
        if (!StringUtils.isNullOrEmpty(validate)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(validate);
            return result;
        }

        result = createObject(dto);
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }

        return result;
    }

    @Override
    public ResultDTO updateDishLanguage(String userName, String localeCode, String countryCode, String token, DishLanguageDTO dto) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);

        String validate = validate(locale, dto, Constants.ACTION_TYPE.UPDATE);
        if (!StringUtils.isNullOrEmpty(validate)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(validate);
            return result;
        }

        String resultStr = update(dto);
        result.setMessage(resultStr);
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }

        return result;
    }

    @Override
    public ResultDTO deleteDishLanguage(String userName, String localeCode, String countryCode, String token, String id) {
        ResultDTO result = new ResultDTO();
        Long ids = Long.valueOf(id);

        String resultDelete = delete(ids);
        if (!ParamUtils.SUCCESS.equals(resultDelete)
                && !ParamUtils.FAIL.equals(resultDelete)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(resultDelete);
            return result;
        }
        result.setMessage(ParamUtils.SUCCESS);
        return result;
    }

    private String validate(Locale locale, DishLanguageDTO dto, String action) {

        if (dto == null) {
            return LanguageBundleUtils.getString(locale, "message.dishLanguage.model.null");
        }
        
        if (Constants.ACTION_TYPE.UPDATE.equals(action)) {
            if(StringUtils.isNullOrEmpty(dto.getId())){
                return LanguageBundleUtils.getString(locale, "message.dishLanguage.id.null");
            }
        }
        
        if (StringUtils.isNullOrEmpty(dto.getName())) {
            return LanguageBundleUtils.getString(locale, "message.dishLanguage.name.null");
        }
        if (dto.getName().length() > 255) {
            return LanguageBundleUtils.getString(locale, "message.dishLanguage.name.overLength.255");
        }

        if (StringUtils.isNullOrEmpty(dto.getDishId())) {
            return LanguageBundleUtils.getString(locale, "message.dish.id.null");
        }

        if (StringUtils.isNullOrEmpty(dto.getLanguageCode())) {
            return LanguageBundleUtils.getString(locale, "message.locale.id.null");
        }

        try {
            com.dungnv.streetfood.model.LocaleModel localeModel = localeBusiness.findById(Long.valueOf(dto.getLanguageCode()));
            if (localeModel == null) {
                return LanguageBundleUtils.getString(locale, "message.locale.id.notExist");
            }
        } catch (NumberFormatException e) {
            return LanguageBundleUtils.getString(locale, "message.locale.id.invalid");
        }
        try {
            Dish dish = dishBusiness.findById(Long.valueOf(dto.getDishId()));
            if (dish == null) {
                return LanguageBundleUtils.getString(locale, "message.dish.id.notExist");
            }
        } catch (NumberFormatException e) {
            return LanguageBundleUtils.getString(locale, "message.dish.id.invalid");
        }

        return null;
    }
}
