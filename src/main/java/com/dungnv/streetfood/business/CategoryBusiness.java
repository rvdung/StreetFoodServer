/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.CategoryDTO;
import com.dungnv.streetfood.model.Category;
import com.dungnv.streetfood.dao.CategoryDAO;
import com.dungnv.streetfood.dto.CategoryDishDTO;
import com.dungnv.streetfood.dto.DishGroupLangageDTO;
import com.dungnv.streetfood.dto.ImgDTO;
import com.dungnv.streetfood.dto.TagCategoryDTO;
import com.dungnv.streetfood.model.DishGroupLangage;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import com.dungnv.vfw5.base.utils.Constants;
import com.dungnv.vfw5.base.utils.DataUtil;
import com.dungnv.vfw5.base.utils.DateTimeUtils;
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
 * @since 1/22/2016 9:48 PM
 */
@Service("categoryBusiness")
@Transactional
public class CategoryBusiness extends BaseFWServiceImpl<CategoryDAO, CategoryDTO, Category> implements CategoryBusinessInterface {

    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private DishGroupLangageBusinessInterface dishGroupLangageBusiness;
    @Autowired
    private TagCategoryBusinessInterface tagCategoryBusiness;
    @Autowired
    private ImgBusinessInterface imgBusiness;

    public CategoryBusiness() {
        tModel = new Category();
        tDAO = categoryDAO;
    }

    @Override
    public CategoryDAO gettDAO() {
        return categoryDAO;
    }

    public CategoryBusiness(Session session) {
        this.session = session;
        tModel = new Category();
        tDAO = categoryDAO;
    }

    @Override
    @Transactional
    public ResultDTO insertCategory(String userName, String localeCode, String countryCode, String token, CategoryDTO dto) {
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
        dto.setCategoryCreateTime(currDate);
        dto.setCategoryUpdateTime(currDate);
        dto.setCategoryCreateTimeGmt(currDateGMT);
        dto.setCategoryUpdateTimeGmt(currDateGMT);
        if (StringUtils.isNullOrEmpty(dto.getCategoryStatus())) {
            dto.setCategoryStatus("1");
        }
        result = createObject(dto);
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }

        dto.setId(result.getId());

        // Ghi danh sách ngôn ngữ
        if (dto.getListLanguage() != null && !dto.getListLanguage().isEmpty()) {
            for (DishGroupLangageDTO langDTO : dto.getListLanguage()) {
                langDTO.setDishGroupId(dto.getId());
                result = dishGroupLangageBusiness.insertDishGroupLangage(userName, localeCode, countryCode, token, langDTO);
                if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                    TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                    return result;
                }
            }
        }

        // insert tag
        result = tagCategoryBusiness.insertTagCategory(userName, localeCode, countryCode, token, Long.valueOf(dto.getId()), dto.getListTag());
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }

        // insert img
        result = imgBusiness.attachImg(userName, localeCode, countryCode, token, dto.getListImgUrl(), dto.getId(), Constants.OBJECT_TYPE.CATEGORY);
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }

        return result;
    }

    @Override
    public ResultDTO updateCategory(String userName, String localeCode, String countryCode, String token, CategoryDTO dto) {
        ResultDTO result = new ResultDTO();
        Locale locale = DataUtil.getLocale(localeCode, countryCode);

        String validate = validate(locale, dto, Constants.ACTION_TYPE.UPDATE);

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
        dto.setCategoryUpdateTime(currDate);
        dto.setCategoryUpdateTimeGmt(currDateGMT);

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

        //update language
        if (dto.getListLanguage() != null && !dto.getListLanguage().isEmpty()) {
            for (DishGroupLangageDTO langDTO : dto.getListLanguage()) {
                langDTO.setDishGroupId(dto.getId());
                result = dishGroupLangageBusiness.insertDishGroupLangage(userName, localeCode, countryCode, token, langDTO);
                if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                    TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                    return result;
                }
            }
        }

        // update tag
        result = tagCategoryBusiness.insertTagCategory(userName, localeCode, countryCode, token, Long.valueOf(dto.getId()), dto.getListTag());
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }

        // update img
        result = imgBusiness.attachImg(userName, localeCode, countryCode, token//
                , dto.getListImgUrl(), dto.getId(), Constants.OBJECT_TYPE.CATEGORY);
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }

        return result;
    }

    @Override
    public ResultDTO deleteCategory(String userName, String localeCode, String countryCode, String token, String id) {
        ResultDTO result = new ResultDTO();
        Long ids = Long.valueOf(id);

        List<ConditionBean> lstCondition = new ArrayList<ConditionBean>();
        lstCondition.add(new ConditionBean(
                DishGroupLangage.DISH_GROUP_ID,
                ParamUtils.OP_EQUAL,
                String.valueOf(id),
                ParamUtils.TYPE_NUMBER));

        // Delete language
        String resultDeleteLanguage = gettDAO().delete(DishGroupLangage.MODEL_NAME, lstCondition);
        if (!ParamUtils.SUCCESS.equals(resultDeleteLanguage)
                && !ParamUtils.FAIL.equals(resultDeleteLanguage)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(resultDeleteLanguage);
            return result;
        }

        // Delete Tag
        lstCondition.clear();
        lstCondition.add(new ConditionBean(
                TagCategoryDTO.CATEGORY_ID,
                ParamUtils.OP_EQUAL,
                String.valueOf(id),
                ParamUtils.TYPE_NUMBER));
        String resultDeleteTag = gettDAO().delete(TagCategoryDTO.MODEL_NAME, lstCondition);
        if (!ParamUtils.SUCCESS.equals(resultDeleteTag)
                && !ParamUtils.FAIL.equals(resultDeleteTag)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(resultDeleteTag);
            return result;
        }

        // Delete IMG
        lstCondition.clear();
        lstCondition.add(new ConditionBean(
                ImgDTO.DISH_GROUP_ID,
                ParamUtils.OP_EQUAL,
                String.valueOf(id),
                ParamUtils.TYPE_NUMBER));
        String resultDeleteImg = gettDAO().delete(ImgDTO.MODEL_NAME, lstCondition);
        if (!ParamUtils.SUCCESS.equals(resultDeleteImg)
                && !ParamUtils.FAIL.equals(resultDeleteImg)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(resultDeleteImg);
            return result;
        }
        
        // Delete Category Dish
        lstCondition.clear();
        lstCondition.add(new ConditionBean(
                CategoryDishDTO.CATEGORY_ID,
                ParamUtils.OP_EQUAL,
                String.valueOf(id),
                ParamUtils.TYPE_NUMBER));
        String resultDeleteCategoryDish = gettDAO().delete(CategoryDishDTO.MODEL_NAME, lstCondition);
        if (!ParamUtils.SUCCESS.equals(resultDeleteCategoryDish)
                && !ParamUtils.FAIL.equals(resultDeleteCategoryDish)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(resultDeleteCategoryDish);
            return result;
        }

        // delete model
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
    public ResultDTO activeCategory(String userName, String localeCode, String countryCode, String token, String id, Boolean active) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String validate(Locale locale, CategoryDTO dto, String action) {
        if (dto == null) {
            return LanguageBundleUtils.getString(locale, "message.category.model.null");
        }
        
        if (dto.getName() == null) {
            return LanguageBundleUtils.getString(locale, "message.category.name.null");
        }
        if (dto.getName().length() > 255) {
            return LanguageBundleUtils.getString(locale, "message.category.name.overLength.255");
        }

        if (dto.getDescription() != null && dto.getDescription().length() > 65000) {
            return LanguageBundleUtils.getString(locale, "message.category.name.overLength.65000");
        }

        if (StringUtils.isNullOrEmpty(dto.getCategoryStatus())) {
            return LanguageBundleUtils.getString(locale, "message.category.status.null");
        }
        if (!"1".equals(dto.getCategoryStatus()) && !"0".equals(dto.getCategoryStatus())) {
            return LanguageBundleUtils.getString(locale, "message.category.status.invalid");
        }

        return null;
    }

    @Override
    public CategoryDTO getCategoryDetail(String userName, String localeCode, String countryCode, String token, String id) {
        CategoryDTO result = null;
        Category model;

        model = findById(Long.valueOf(id));
        if (model != null) {
            result = model.toDTO();

            // get Language
            List<ConditionBean> lstCondition = new ArrayList<ConditionBean>();
            lstCondition.add(new ConditionBean(
                    DishGroupLangage.DISH_GROUP_ID,
                    ParamUtils.OP_EQUAL,
                    String.valueOf(id),
                    ParamUtils.TYPE_NUMBER));
            List<DishGroupLangageDTO> listCategoryLanguage = dishGroupLangageBusiness.searchByConditionBean(lstCondition, 0, 0, "ASC", "id");
            result.setListLanguage(listCategoryLanguage);

            // get Tag
            List<String> listTagCategory = tagCategoryBusiness.getTagsListByCategory(userName, localeCode, countryCode, token, id);
            result.setListTag(listTagCategory);

            // get img
            lstCondition = new ArrayList<>();
            lstCondition.add(new ConditionBean(
                    DishGroupLangage.DISH_GROUP_ID,
                    ParamUtils.OP_EQUAL,
                    String.valueOf(id),
                    ParamUtils.TYPE_NUMBER));
            List<ImgDTO> listImg = imgBusiness.searchByConditionBean(lstCondition, 0, 0, "ASC", "order");
            List<String> listImgUrl = new ArrayList<>();
            if (listImg != null && !listImg.isEmpty()) {
                result.setImageId(listImg.get(0).getId());
                result.setImageUrl(listImg.get(0).getUrl());
                for (int i = 0; i < listImg.size(); i++) {
                    listImgUrl.add(listImg.get(i).getUrl());
                }
                result.setListImgUrl(listImgUrl);
            }
            // get img

        }
        return result;
    }

    @Override
    public List<CategoryDTO> getListCategoryDTOLess(String userName, String localeCode, String countryCode, String token//
            , CategoryDTO categoryDTO, int rowStart, int maxRow, boolean isCount, String sortType, String sortFieldList) {
        return gettDAO().getListCategoryDTOLess(categoryDTO, rowStart, maxRow, isCount, sortType, sortFieldList);
    }
}
