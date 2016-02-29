/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.model.TagRestaurant;
import com.dungnv.streetfood.dao.TagRestaurantDAO;
import com.dungnv.streetfood.dto.TagRestaurantDTO;
import com.dungnv.streetfood.dto.TagsDTO;
import com.dungnv.streetfood.model.Restaurant;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.utils.DataUtil;
import com.dungnv.vfw5.base.utils.LanguageBundleUtils;
import com.dungnv.vfw5.base.utils.ParamUtils;
import com.dungnv.vfw5.base.utils.StringUtils;
import org.hibernate.Session;
import java.util.*;
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
 * @since 2/21/2016 12:58 PM
 */
@Service("tagRestaurantBusiness")
@Transactional
public class TagRestaurantBusiness extends BaseFWServiceImpl<TagRestaurantDAO, TagRestaurantDTO, TagRestaurant> implements TagRestaurantBusinessInterface {

    @Autowired
    private TagRestaurantDAO tagRestaurantDAO;
    @Autowired
    private TagsBusinessInterface tagsBusiness;
    @Autowired
    private RestaurantBusinessInterface restaurantBusiness;

    public TagRestaurantBusiness() {
        tModel = new TagRestaurant();
        tDAO = tagRestaurantDAO;
    }

    @Override
    public TagRestaurantDAO gettDAO() {
        return tagRestaurantDAO;
    }

    public TagRestaurantBusiness(Session session) {
        this.session = session;
        tModel = new TagRestaurant();
        tDAO = tagRestaurantDAO;
    }

    @Override
    public ResultDTO insertTagRestaurant(String userName, String localeCode, String countryCode, String token, Long restaurantId, List<String> listTag) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);

        String validate = validate(locale, restaurantId, listTag);
        if (!StringUtils.isNullOrEmpty(validate)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(validate);
            return result;
        }

        // Get all current tag
        TagRestaurantDTO tagRestaurantCondition = new TagRestaurantDTO(null, null, String.valueOf(restaurantId));
        List<TagRestaurantDTO> listCurrTag = search(tagRestaurantCondition, 0, Integer.MAX_VALUE, "ASC", "id");

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

                    TagRestaurantDTO tagRestaurantDTO = new TagRestaurantDTO(null, result.getId(), String.valueOf(restaurantId));
                    result = insertTagRestaurant(userName, localeCode, countryCode, token, tagRestaurantDTO);
                    if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                        TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                        return result;
                    }
                } else {
                    TagRestaurantDTO tagRestaurantDTO = new TagRestaurantDTO(null, tagId, String.valueOf(restaurantId));
                    if (listCurrTag.contains(tagRestaurantDTO)) {
                        listCurrTag.remove(tagRestaurantDTO);
                        continue;
                    }
                    result = insertTagRestaurant(userName, localeCode, countryCode, token, tagRestaurantDTO);
                    if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                        TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                        return result;
                    }
                }
            }
        }

        //remove unused tag_restaurant record
        for (TagRestaurantDTO tag : listCurrTag) {
            result = deleteTagRestaurant(userName, localeCode, countryCode, token, tag.getId());
            if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                return result;
            }
        }
        result.setMessage(ParamUtils.SUCCESS);

        return result;
    }

    @Override
    public ResultDTO insertTagRestaurant(String userName, String localeCode, String countryCode, String token, TagRestaurantDTO dto) {
        return createObject(dto);
    }

    @Override
    public ResultDTO updateTagRestaurant(String userName, String localeCode, String countryCode, String token, TagRestaurantDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultDTO deleteTagRestaurant(String userName, String localeCode, String countryCode, String token, String id) {
          ResultDTO result = new ResultDTO();
        String resultStr = delete(Long.valueOf(id));
        result.setMessage(resultStr);
        return result;
    }

    @Override
    public List<String> getTagsListByRestaurant(String userName, String localeCode, String countryCode, String token, String id) {
        SQLQuery query = gettDAO().getSession().createSQLQuery("select t.name id from tags t "
                + "inner join tag_restaurant l on t.id =l.tag_id"
                + " where l.restaurant_id = ? ");
        query.addScalar("id", StringType.INSTANCE);
        query.setLong(0, Long.valueOf(id));
        return query.list();
    }

    private String validate(Locale locale, Long restaurantId, List<String> listTag) {
        if (restaurantId == null) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.id.null");
        }
        Restaurant restaurant = restaurantBusiness.findById(restaurantId);
        if (restaurant == null) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.id.notExist");
        }
        return null;
    }
}
