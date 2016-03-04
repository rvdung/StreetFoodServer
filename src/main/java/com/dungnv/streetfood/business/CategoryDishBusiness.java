/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.CategoryDishDTO;
import com.dungnv.streetfood.model.CategoryDish;
import com.dungnv.streetfood.dao.CategoryDishDAO;
import com.dungnv.streetfood.dto.CategoryDTO;
import com.dungnv.streetfood.dto.DishDTO;
import com.dungnv.streetfood.model.Category;
import com.dungnv.streetfood.model.Dish;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import com.dungnv.vfw5.base.utils.DataUtil;
import com.dungnv.vfw5.base.utils.LanguageBundleUtils;
import com.dungnv.vfw5.base.utils.ParamUtils;
import com.dungnv.vfw5.base.utils.StringUtils;
import org.hibernate.Session;
import java.util.*;
import javax.transaction.Transactional;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/25/2016 10:09 PM
 */
@Service("categoryDishBusiness")
@Transactional
public class CategoryDishBusiness extends BaseFWServiceImpl<CategoryDishDAO, CategoryDishDTO, CategoryDish> implements CategoryDishBusinessInterface {

    @Autowired
    private CategoryDishDAO categoryDishDAO;
    @Autowired
    private CategoryBusinessInterface categoryBusiness;
    @Autowired
    private DishBusinessInterface dishBusiness;

    enum TYPE {
        DISHES_TO_CATEGORY, CATEGORIES_TO_DISH
    }

    public CategoryDishBusiness() {
        tModel = new CategoryDish();
        tDAO = categoryDishDAO;
    }

    @Override
    public CategoryDishDAO gettDAO() {
        return categoryDishDAO;
    }

    public CategoryDishBusiness(Session session) {
        this.session = session;
        tModel = new CategoryDish();
        tDAO = categoryDishDAO;
    }

    @Override
    public List<CategoryDTO> getListCategoryByDish(String userName, String localeCode, String countryCode, String token//
            , String id, boolean isIn) {

        StringBuilder sql = new StringBuilder("select c.id, c.name from Category c ");
        sql.append(" where c.id ");
        sql.append(isIn ? " in " : " not in ");
        sql.append(" (select distinct category_id from category_dish where dish_id = ? ) ");

        SQLQuery query = gettDAO().getSession().createSQLQuery(sql.toString());
        query.addScalar("id", StringType.INSTANCE);
        query.addScalar("name", StringType.INSTANCE);
        query.setLong(0, Long.valueOf(id));
        query.setResultTransformer(Transformers.aliasToBean(CategoryDTO.class));
        return query.list();
    }

    @Override
    public List<DishDTO> getListDishByCategory(String userName, String localeCode, String countryCode, String token//
            , String id, boolean isIn) {
        StringBuilder sql = new StringBuilder("select c.id, c.name from dish c ");
        sql.append(" where c.id ");
        sql.append(isIn ? "in" : " not in");
        sql.append(" (select distinct dish_id from category_dish where category_id = ? ) ");

        SQLQuery query = gettDAO().getSession().createSQLQuery(sql.toString());
        query.addScalar("id", StringType.INSTANCE);
        query.addScalar("name", StringType.INSTANCE);
        query.setLong(0, Long.valueOf(id));
        query.setResultTransformer(Transformers.aliasToBean(DishDTO.class));
        return query.list();
    }

    @Override
    public ResultDTO insertListDishToCategory(String userName, String localeCode, String countryCode, String token, String id, List<String> list) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);

        if (list != null) {
            list = DataUtil.removeDuplicateString(list, Boolean.TRUE);
        }

        String validate = validate(locale, id, list, TYPE.DISHES_TO_CATEGORY);
        if (!StringUtils.isNullOrEmpty(validate)) {
            return rollBackTransaction(validate);
        }

        Map<String, CategoryDishDTO> mapcurrentDish = new HashMap<String, CategoryDishDTO>();

        CategoryDishDTO searchDTO = new CategoryDishDTO();
        searchDTO.setCategoryId(id);
        List<CategoryDishDTO> ListcurrentDish = search(searchDTO, 0, 0, "ASC", "id");

        for (CategoryDishDTO dish : ListcurrentDish) {
            mapcurrentDish.put(dish.getDishId(), dish);
        }

        if (list != null) {
            for (String dishId : list) {
                if (mapcurrentDish.containsKey(dishId)) {
                    mapcurrentDish.remove(dishId);
                    continue;
                } else {
                    CategoryDishDTO dto = new CategoryDishDTO();
                    dto.setDishId(dishId);
                    dto.setCategoryId(id);
                    result = createObject(dto);
                    if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                        TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                        return result;
                    }
                }
            }
        }

        if (!mapcurrentDish.isEmpty()) {
            String resultDelete = delete(mapcurrentDish.values());
            if (!ParamUtils.SUCCESS.equals(resultDelete)
                    && !ParamUtils.FAIL.equals(resultDelete)) {
                TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                result.setMessage(ParamUtils.FAIL);
                result.setKey(resultDelete);
                return result;
            }
        }

        result.setMessage(ParamUtils.SUCCESS);
        return result;
    }

    @Override
    public ResultDTO insertListCategoryToDish(String userName, String localeCode, String countryCode, String token, String id, List<String> list) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);

        if (list != null) {
            list = DataUtil.removeDuplicateString(list, Boolean.TRUE);
        }

        String validate = validate(locale, id, list, TYPE.CATEGORIES_TO_DISH);
        if (!StringUtils.isNullOrEmpty(validate)) {
            return rollBackTransaction(validate);
        }

        Map<String, CategoryDishDTO> mapcurrentCategory = new HashMap<String, CategoryDishDTO>();

        CategoryDishDTO searchDTO = new CategoryDishDTO();
        searchDTO.setDishId(id);
        List<CategoryDishDTO> ListcurrentCategory = search(searchDTO, 0, 0, "ASC", "id");

        for (CategoryDishDTO category : ListcurrentCategory) {
            mapcurrentCategory.put(category.getCategoryId(), category);
        }

        if (list != null) {
            for (String categoryId : list) {
                if (mapcurrentCategory.containsKey(categoryId)) {
                    mapcurrentCategory.remove(categoryId);
                } else {
                    CategoryDishDTO dto = new CategoryDishDTO();
                    dto.setDishId(id);
                    dto.setCategoryId(categoryId);
                    result = createObject(dto);
                    if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                        TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                        return result;
                    }
                }
            }
        }

        if (!mapcurrentCategory.isEmpty()) {
            String resultDelete = delete(mapcurrentCategory.values());
            if (!ParamUtils.SUCCESS.equals(resultDelete)
                    && !ParamUtils.FAIL.equals(resultDelete)) {
                TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                result.setMessage(ParamUtils.FAIL);
                result.setKey(resultDelete);
                return result;
            }
        }

        result.setMessage(ParamUtils.SUCCESS);
        return result;
    }

    private String validate(Locale locale, String id, List<String> ids, TYPE type) {
        if (type.equals(TYPE.CATEGORIES_TO_DISH)) {
            if (StringUtils.isNullOrEmpty(id)) {
                return LanguageBundleUtils.getString(locale, "message.dish.id.null");
            }

            Dish model = dishBusiness.findById(Long.valueOf(id));

            if (model == null) {
                return LanguageBundleUtils.getString(locale, "message.dish.id.notExist");
            }

            if (ids != null && !ids.isEmpty()) {
                List<ConditionBean> lstConditionBean = new ArrayList<>();
                lstConditionBean.add(new ConditionBean(CategoryDTO.ID, ParamUtils.OP_IN, DataUtil.formatInputList(ids), ParamUtils.TYPE_NUMBER));
                List<CategoryDTO> listDTO = categoryBusiness.searchByConditionBean(lstConditionBean, 0, 0, "ASC", "id");

                if (listDTO == null || listDTO.size() != ids.size()) {
                    return LanguageBundleUtils.getString(locale, "message.category.id.notExist");
                }
            }
        } else {
            if (StringUtils.isNullOrEmpty(id)) {
                return LanguageBundleUtils.getString(locale, "message.category.id.null");
            }

            Category model = categoryBusiness.findById(Long.valueOf(id));

            if (model == null) {
                return LanguageBundleUtils.getString(locale, "message.category.id.notExist");
            }

            if (ids != null && !ids.isEmpty()) {
                List<ConditionBean> lstConditionBean = new ArrayList<>();
                lstConditionBean.add(new ConditionBean(DishDTO.ID, ParamUtils.OP_IN, DataUtil.formatInputList(ids), ParamUtils.TYPE_NUMBER));
                List<DishDTO> listDTO = dishBusiness.searchByConditionBean(lstConditionBean, 0, 0, "ASC", "id");

                if (listDTO == null || listDTO.size() != ids.size()) {
                    return LanguageBundleUtils.getString(locale, "message.dish.id.notExist");
                }
            }
        }
        return null;
    }
}
