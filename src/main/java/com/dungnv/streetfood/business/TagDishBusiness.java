/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.model.TagDish;
import com.dungnv.streetfood.dao.TagDishDAO;
import com.dungnv.streetfood.dto.TagDishDTO;
import com.dungnv.streetfood.dto.TagsDTO;
import com.dungnv.streetfood.model.Dish;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.utils.DataUtil;
import com.dungnv.vfw5.base.utils.LanguageBundleUtils;
import com.dungnv.vfw5.base.utils.ParamUtils;
import com.dungnv.vfw5.base.utils.StringUtils;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.hibernate.Session;
import javax.transaction.Transactional;
import org.hibernate.SQLQuery;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/26/2016 9:17 PM
 */
@Service("tagDishBusiness")
@Transactional
public class TagDishBusiness extends BaseFWServiceImpl<TagDishDAO, TagDishDTO, TagDish> implements TagDishBusinessInterface {

    @Autowired
    private TagDishDAO tagDishDAO;
    @Autowired
    private TagsBusinessInterface tagsBusiness;
    @Autowired
    private DishBusinessInterface dishBusiness;

    public TagDishBusiness() {
        tModel = new TagDish();
        tDAO = tagDishDAO;
    }

    @Override
    public TagDishDAO gettDAO() {
        return tagDishDAO;
    }

    public TagDishBusiness(Session session) {
        this.session = session;
        tModel = new TagDish();
        tDAO = tagDishDAO;
    }

    @Override
    public ResultDTO insertTagDish(String userName, String localeCode, String countryCode, String token, Long dishId, List<String> listTag) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);

        String validate = validate(locale, dishId, listTag);
        if (!StringUtils.isNullOrEmpty(validate)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(validate);
            return result;
        }

        // Get all current tag
        TagDishDTO tagDishCondition = new TagDishDTO(null, null, String.valueOf(dishId));
        List<TagDishDTO> listCurrTag = search(tagDishCondition, 0, Integer.MAX_VALUE, "ASC", "id");

        if (listTag != null && !listTag.isEmpty()) {
            listTag = DataUtil.removeDuplicateString(listTag, Boolean.TRUE);
            Map<String, String> mapTagId = tagsBusiness.getMapTagsByName(listTag);
            for (String tagStr : listTag) {
                String tagId = mapTagId.get(tagStr.toLowerCase());
                if (tagId == null) {
                    TagsDTO tagDTO = new TagsDTO(null, tagStr, null, null, null, null);
                    result = tagsBusiness.insertTags(userName, localeCode, countryCode, token, tagDTO);

                    if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                        TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                        return result;
                    }

                    TagDishDTO tagDishDTO = new TagDishDTO(null, result.getId(), String.valueOf(dishId));
                    result = insertTagDish(userName, localeCode, countryCode, token, tagDishDTO);
                    if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                        TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                        return result;
                    }
                } else {
                    TagDishDTO tagDishDTO = new TagDishDTO(null, tagId, String.valueOf(dishId));
                    if (listCurrTag.contains(tagDishDTO)) {
                        listCurrTag.remove(tagDishDTO);
                        continue;
                    }
                    result = insertTagDish(userName, localeCode, countryCode, token, tagDishDTO);
                    if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                        TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                        return result;
                    }
                }
            }
        }

        //remove unused tag_dish record
        for (TagDishDTO tag : listCurrTag) {
            result = deleteTagDish(userName, localeCode, countryCode, token, tag.getId());
            if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                return result;
            }
        }
        result.setMessage(ParamUtils.SUCCESS);

        return result;
    }

    private String validate(Locale locale, Long dishId, List<String> listTag) {
        if (dishId == null) {
            return LanguageBundleUtils.getString(locale, "message.dish.id.null");
        }
        Dish dish = dishBusiness.findById(dishId);
        if (dish == null) {
            return LanguageBundleUtils.getString(locale, "message.dish.id.notExist");
        }
        return null;
    }

    @Override
    public ResultDTO insertTagDish(String userName, String localeCode, String countryCode, String token, TagDishDTO dto) {
        return createObject(dto);
    }

    @Override
    public ResultDTO updateTagDish(String userName, String localeCode, String countryCode, String token, TagDishDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultDTO deleteTagDish(String userName, String localeCode, String countryCode, String token, String id) {
        ResultDTO result = new ResultDTO();
        String resultStr = delete(Long.valueOf(id));
        result.setMessage(resultStr);
        return result;
    }

    @Override
    public List<String> getTagsListByDish(String userName, String localeCode, String countryCode, String token, String id) {
        SQLQuery query = gettDAO().getSession().createSQLQuery("select t.name id from tags t "
                + "inner join tag_dish l on t.id =l.tag_id"
                + " where l.dish_id = ? ");
        query.addScalar("id", StringType.INSTANCE);
        query.setLong(0, Long.valueOf(id));
        return query.list();
    }
}
