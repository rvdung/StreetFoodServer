/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.RestaurantLanguageDTO;
import com.dungnv.streetfood.model.RestaurantLanguage;
import com.dungnv.streetfood.dao.RestaurantLanguageDAO;
import com.dungnv.streetfood.model.Restaurant;
import com.dungnv.vfw5.base.dto.ResultDTO;
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
 * @since 2/21/2016 11:30 AM
 */
@Service("restaurantLanguageBusiness")
@Transactional
public class RestaurantLanguageBusiness extends BaseFWServiceImpl<RestaurantLanguageDAO, RestaurantLanguageDTO, RestaurantLanguage> implements RestaurantLanguageBusinessInterface {

    @Autowired
    private RestaurantLanguageDAO restaurantLanguageDAO;
    @Autowired
    private LocaleBusinessInterface localeBusiness;
    @Autowired
    private RestaurantBusinessInterface restaurantBusiness;

    public RestaurantLanguageBusiness() {
        tModel = new RestaurantLanguage();
        tDAO = restaurantLanguageDAO;
    }

    @Override
    public RestaurantLanguageDAO gettDAO() {
        return restaurantLanguageDAO;
    }

    public RestaurantLanguageBusiness(Session session) {
        this.session = session;
        tModel = new RestaurantLanguage();
        tDAO = restaurantLanguageDAO;
    }

    @Override
    public ResultDTO insertRestaurantLanguage(String userName, String localeCode, String countryCode, String token, RestaurantLanguageDTO dto) {
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
    public ResultDTO updateRestaurantLanguage(String userName, String localeCode, String countryCode, String token, RestaurantLanguageDTO dto) {
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
    public ResultDTO updateMergeRestaurantLanguage(String userName, String localeCode, String countryCode, String token, RestaurantLanguageDTO dto) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);

        String validate = validate(locale, dto, Constants.ACTION_TYPE.UPDATE);
        if (!StringUtils.isNullOrEmpty(validate)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(validate);
            return result;
        }

        String resultStr = updateMerge(dto);
        result.setMessage(resultStr);
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }

        return result;
    }

    @Override
    public ResultDTO deleteRestaurantLanguage(String userName, String localeCode, String countryCode, String token, String id) {
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

    @Override
    public ResultDTO insertRestaurantLanguage(String userName, String localeCode, String countryCode//
            , String token, String restaurantId, List<RestaurantLanguageDTO> listLanguage) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);

        String validate = validate(locale, restaurantId, listLanguage);
        if (!StringUtils.isNullOrEmpty(validate)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(validate);
            return result;
        }

        // Get all current language
        RestaurantLanguageDTO restaurantLanguageCondition = new RestaurantLanguageDTO();
        restaurantLanguageCondition.setRestaurantId(String.valueOf(restaurantId));
        List<RestaurantLanguageDTO> listCurrLanguage = search(restaurantLanguageCondition, 0, Integer.MAX_VALUE, "ASC", "id");
        Map<String, RestaurantLanguageDTO> mapCurrLanguage = new HashMap<>();
        for (RestaurantLanguageDTO obj : listCurrLanguage) {
            mapCurrLanguage.put(obj.getLanguageCode(), obj);
        }

        if (listLanguage != null && !listLanguage.isEmpty()) {
            for (RestaurantLanguageDTO restaurantLangDTO : listLanguage) {
                RestaurantLanguageDTO currLang = mapCurrLanguage.get(restaurantLangDTO.getLanguageCode());
                if (currLang == null) {
                    currLang = restaurantLangDTO;
                    currLang.setRestaurantId(restaurantId);
                    result = insertRestaurantLanguage(userName, localeCode, countryCode, token, currLang);

                    if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                        TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                        return result;
                    }
                } else {
                    currLang.setName(restaurantLangDTO.getName());
                    currLang.setAddress(restaurantLangDTO.getAddress());
                    currLang.setIntroduce(restaurantLangDTO.getIntroduce());

                    result = updateMergeRestaurantLanguage(userName, localeCode, countryCode, token, currLang);
                    if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                        TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                        return result;
                    }
                    // remove exist language
                    listCurrLanguage.remove(currLang);
                }
            }
        }

        //remove unused tag_restaurant record
        for (RestaurantLanguageDTO tag : listCurrLanguage) {
            result = deleteRestaurantLanguage(userName, localeCode, countryCode, token, tag.getId());
            if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                return result;
            }
        }
        result.setMessage(ParamUtils.SUCCESS);
        return result;
    }

    private String validate(Locale locale, RestaurantLanguageDTO dto, String action) {

        if (dto == null) {
            return LanguageBundleUtils.getString(locale, "message.restaurantLanguage.model.null");
        }

        if (Constants.ACTION_TYPE.UPDATE.equals(action)) {
            if (StringUtils.isNullOrEmpty(dto.getId())) {
                return LanguageBundleUtils.getString(locale, "message.restaurantLanguage.id.null");
            }
        }

        if (StringUtils.isNullOrEmpty(dto.getName())) {
            return LanguageBundleUtils.getString(locale, "message.restaurantLanguage.name.null");
        }
        if (dto.getName().length() > 255) {
            return LanguageBundleUtils.getString(locale, "message.restaurantLanguage.name.overLength.255");
        }

        if (StringUtils.isNullOrEmpty(dto.getRestaurantId())) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.id.null");
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
            Restaurant restaurant = restaurantBusiness.findById(Long.valueOf(dto.getRestaurantId()));
            if (restaurant == null) {
                return LanguageBundleUtils.getString(locale, "message.restaurant.id.notExist");
            }
        } catch (NumberFormatException e) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.id.invalid");
        }

        return null;
    }

    private String validate(Locale locale, String restaurantId, List<RestaurantLanguageDTO> listLanguage) {
        if (restaurantId == null) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.id.null");
        }

        return null;
    }
}
