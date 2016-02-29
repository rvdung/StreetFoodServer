/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.SlideShowLanguageDTO;
import com.dungnv.streetfood.model.SlideShowLanguage;
import com.dungnv.streetfood.dao.SlideShowLanguageDAO;
import com.dungnv.streetfood.model.SlideShow;
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
 * @since 2/21/2016 1:04 PM
 */
@Service("slideShowLanguageBusiness")
@Transactional
public class SlideShowLanguageBusiness extends BaseFWServiceImpl<SlideShowLanguageDAO, SlideShowLanguageDTO, SlideShowLanguage> implements SlideShowLanguageBusinessInterface {

    @Autowired
    private SlideShowLanguageDAO slideShowLanguageDAO;
    @Autowired
    private LocaleBusinessInterface localeBusiness;
    @Autowired
    private SlideShowBusinessInterface slideShowBusiness;

    public SlideShowLanguageBusiness() {
        tModel = new SlideShowLanguage();
        tDAO = slideShowLanguageDAO;
    }

    @Override
    public SlideShowLanguageDAO gettDAO() {
        return slideShowLanguageDAO;
    }

    public SlideShowLanguageBusiness(Session session) {
        this.session = session;
        tModel = new SlideShowLanguage();
        tDAO = slideShowLanguageDAO;
    }

    @Override
    public ResultDTO insertSlideShowLanguage(String userName, String localeCode, String countryCode, String token, SlideShowLanguageDTO dto) {
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
    public ResultDTO updateSlideShowLanguage(String userName, String localeCode, String countryCode, String token, SlideShowLanguageDTO dto) {
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
    public ResultDTO updateMergeSlideShowLanguage(String userName, String localeCode, String countryCode, String token, SlideShowLanguageDTO dto) {
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
    public ResultDTO deleteSlideShowLanguage(String userName, String localeCode, String countryCode, String token, String id) {
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
    public ResultDTO insertSlideShowLanguage(String userName, String localeCode, String countryCode//
            , String token, String slideShowId, List<SlideShowLanguageDTO> listLanguage) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);

        String validate = validate(locale, slideShowId, listLanguage);
        if (!StringUtils.isNullOrEmpty(validate)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(validate);
            return result;
        }

        // Get all current language
        SlideShowLanguageDTO slideShowLanguageCondition = new SlideShowLanguageDTO();
        slideShowLanguageCondition.setSlideShowId(String.valueOf(slideShowId));
        List<SlideShowLanguageDTO> listCurrLanguage = search(slideShowLanguageCondition, 0, Integer.MAX_VALUE, "ASC", "id");
        Map<String, SlideShowLanguageDTO> mapCurrLanguage = new HashMap<>();
        for (SlideShowLanguageDTO obj : listCurrLanguage) {
            mapCurrLanguage.put(obj.getLanguageCode(), obj);
        }

        if (listLanguage != null && !listLanguage.isEmpty()) {
            for (SlideShowLanguageDTO slideShowLangDTO : listLanguage) {
                SlideShowLanguageDTO currLang = mapCurrLanguage.get(slideShowLangDTO.getLanguageCode());
                if (currLang == null) {
                    currLang = slideShowLangDTO;
                    currLang.setSlideShowId(slideShowId);
                    result = insertSlideShowLanguage(userName, localeCode, countryCode, token, currLang);

                    if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                        TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                        return result;
                    }
                } else {
                    currLang.setName(slideShowLangDTO.getName());
                    currLang.setDescription(slideShowLangDTO.getDescription());

                    result = updateMergeSlideShowLanguage(userName, localeCode, countryCode, token, currLang);
                    if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                        TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                        return result;
                    }
                    // remove exist language
                    listCurrLanguage.remove(currLang);
                }
            }
        }

        //remove unused tag_slideShow record
        for (SlideShowLanguageDTO tag : listCurrLanguage) {
            result = deleteSlideShowLanguage(userName, localeCode, countryCode, token, tag.getId());
            if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                return result;
            }
        }
        result.setMessage(ParamUtils.SUCCESS);

        return result;
    }

    private String validate(Locale locale, SlideShowLanguageDTO dto, String action) {

        if (dto == null) {
            return LanguageBundleUtils.getString(locale, "message.slideShowLanguage.model.null");
        }

        if (Constants.ACTION_TYPE.UPDATE.equals(action)) {
            if (StringUtils.isNullOrEmpty(dto.getId())) {
                return LanguageBundleUtils.getString(locale, "message.slideShowLanguage.id.null");
            }
        }

        if (StringUtils.isNullOrEmpty(dto.getName())) {
            return LanguageBundleUtils.getString(locale, "message.slideShowLanguage.name.null");
        }
        if (dto.getName().length() > 255) {
            return LanguageBundleUtils.getString(locale, "message.slideShowLanguage.name.overLength.255");
        }

        if (StringUtils.isNullOrEmpty(dto.getSlideShowId())) {
            return LanguageBundleUtils.getString(locale, "message.slideShow.id.null");
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
            SlideShow slideShow = slideShowBusiness.findById(Long.valueOf(dto.getSlideShowId()));
            if (slideShow == null) {
                return LanguageBundleUtils.getString(locale, "message.slideShow.id.notExist");
            }
        } catch (NumberFormatException e) {
            return LanguageBundleUtils.getString(locale, "message.slideShow.id.invalid");
        }

        return null;
    }

    private String validate(Locale locale, String slideShowId, List<SlideShowLanguageDTO> listLanguage) {
        if (slideShowId == null) {
            return LanguageBundleUtils.getString(locale, "message.slideShow.id.null");
        }

        return null;
    }
}
