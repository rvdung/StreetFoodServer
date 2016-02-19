/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.DishGroupLangageDTO;
import com.dungnv.streetfood.model.DishGroupLangage;
import com.dungnv.streetfood.dao.DishGroupLangageDAO;
import com.dungnv.streetfood.model.Category;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.utils.Constants;
import com.dungnv.vfw5.base.utils.DataUtil;
import com.dungnv.vfw5.base.utils.LanguageBundleUtils;
import com.dungnv.vfw5.base.utils.ParamUtils;
import com.dungnv.vfw5.base.utils.StringUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.hibernate.Session;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/21/2016 9:12 PM
 */
@Service("dishGroupLangageBusiness")
@Transactional
public class DishGroupLangageBusiness extends BaseFWServiceImpl<DishGroupLangageDAO, DishGroupLangageDTO, DishGroupLangage> implements DishGroupLangageBusinessInterface {

    @Autowired
    private DishGroupLangageDAO dishGroupLangageDAO;
    @Autowired
    private LocaleBusinessInterface localeBusiness;
    @Autowired
    private CategoryBusinessInterface categoryBusiness;

    public DishGroupLangageBusiness() {
        tModel = new DishGroupLangage();
        tDAO = dishGroupLangageDAO;
    }

    @Override
    public DishGroupLangageDAO gettDAO() {
        return dishGroupLangageDAO;
    }

    public DishGroupLangageBusiness(Session session) {
        this.session = session;
        tModel = new DishGroupLangage();
        tDAO = dishGroupLangageDAO;
    }

    @Override
    @Transactional
    public ResultDTO insertDishGroupLangage(String userName, String localeCode, String countryCode, String token, DishGroupLangageDTO dto) {
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
    public ResultDTO updateDishGroupLangage(String userName, String localeCode, String countryCode, String token, DishGroupLangageDTO dto) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);

        String validate = validate(locale, dto, Constants.ACTION_TYPE.UPDATE);
        if (!StringUtils.isNullOrEmpty(validate)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(validate);
            return result;
        }

        result.setMessage(update(dto));
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }

        return result;
    }

    @Override
    public ResultDTO deleteDishGroupLangage(String userName, String localeCode, String countryCode, String token, DishGroupLangageDTO dto) {
        ResultDTO result = new ResultDTO();
        result.setMessage(delete(Long.valueOf(dto.getId())));
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }

        return result;
    }

    @Override
    public ResultDTO activeDishGroupLangage(String userName, String localeCode, String countryCode, String token, String id, Boolean active) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String validate(Locale locale, DishGroupLangageDTO dto, String action) {

        if (dto == null) {
            return LanguageBundleUtils.getString(locale, "message.dishGroupLangage.model.null");
        }
        if (StringUtils.isNullOrEmpty(dto.getName())) {
            return LanguageBundleUtils.getString(locale, "message.dishGroupLangage.name.null");
        }
        if (dto.getName().length() > 255) {
            return LanguageBundleUtils.getString(locale, "message.dishGroupLangage.name.overLength.255");
        }

        if (dto.getDescription() != null && dto.getDescription().length() > 65000) {
            return LanguageBundleUtils.getString(locale, "message.dishGroupLangage.name.overLength.65000");
        }

        if (StringUtils.isNullOrEmpty(dto.getDishGroupId())) {
            return LanguageBundleUtils.getString(locale, "message.category.id.null");
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
            Category category = categoryBusiness.findById(Long.valueOf(dto.getDishGroupId()));
            if (category == null) {
                return LanguageBundleUtils.getString(locale, "message.category.id.notExist");
            }
        } catch (NumberFormatException e) {
            return LanguageBundleUtils.getString(locale, "message.category.id.invalid");
        }

        return null;
    }

    @Override
    public ResultDTO insertDishGroupLangage(String userName, String localeCode, String countryCode, String token, Long categoryId, List<DishGroupLangageDTO> listDTO) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);

        // Get all current tag
        DishGroupLangageDTO condition = new DishGroupLangageDTO();
        condition.setDishGroupId(String.valueOf(categoryId));
        List<DishGroupLangageDTO> listCurr = search(condition, 0, Integer.MAX_VALUE, "ASC", "id");

        if (listDTO != null && !listDTO.isEmpty()) {
            Map<String, DishGroupLangageDTO> mapCurr = new HashMap<>();
            Map<String, DishGroupLangageDTO> mapNew = new HashMap<>();
            for (DishGroupLangageDTO dto : listCurr) {
                mapCurr.put(dto.getLanguageCode(), dto);
            }
            for (DishGroupLangageDTO dto : listDTO) {
                mapNew.put(dto.getLanguageCode(), dto);
            }

            for (DishGroupLangageDTO newDTO : mapNew.values()) {
                newDTO.setDishGroupId(String.valueOf(categoryId));
                DishGroupLangageDTO currDTO = mapCurr.get(newDTO.getLanguageCode());
                if (currDTO != null) {
                    currDTO.setName(newDTO.getName());
                    currDTO.setDescription(newDTO.getDescription());
                    result = updateDishGroupLangage(userName, localeCode, countryCode, token, currDTO);
                    if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                        TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                        return result;
                    }

                    mapCurr.remove(newDTO.getLanguageCode());
                } else {
                    result = insertDishGroupLangage(userName, localeCode, countryCode, token, newDTO);
                    if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                        TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                        return result;
                    }
                }
            }
        }

        //remove unused record
        for (DishGroupLangageDTO dto : listCurr) {
            deleteDishGroupLangage(userName, localeCode, countryCode, token, dto);
        }
        result.setMessage(ParamUtils.SUCCESS);

        return result;
    }

}
