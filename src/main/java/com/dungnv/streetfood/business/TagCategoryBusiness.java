/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.TagCategoryDTO;
import com.dungnv.streetfood.model.TagCategory;
import com.dungnv.streetfood.dao.TagCategoryDAO;
import com.dungnv.streetfood.dto.TagsDTO;
import com.dungnv.streetfood.model.Category;
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
 * @since 1/24/2016 7:34 PM
 */
@Service("tagCategoryBusiness")
@Transactional
public class TagCategoryBusiness extends BaseFWServiceImpl<TagCategoryDAO, TagCategoryDTO, TagCategory> implements TagCategoryBusinessInterface {

    @Autowired
    private TagCategoryDAO tagCategoryDAO;
    @Autowired
    private TagsBusinessInterface tagsBusiness;
    @Autowired
    private CategoryBusinessInterface categoryBusiness;

    public TagCategoryBusiness() {
        tModel = new TagCategory();
        tDAO = tagCategoryDAO;
    }

    @Override
    public TagCategoryDAO gettDAO() {
        return tagCategoryDAO;
    }

    public TagCategoryBusiness(Session session) {
        this.session = session;
        tModel = new TagCategory();
        tDAO = tagCategoryDAO;
    }

    @Override
    public ResultDTO insertTagCategory(String userName, String localeCode, String countryCode, String token, Long categoryId, List<String> listTag) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);

        String validate = validate(locale, categoryId, listTag);
        if (!StringUtils.isNullOrEmpty(validate)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(validate);
            return result;
        }

        // Get all current tag
        TagCategoryDTO tagCategoryCondition = new TagCategoryDTO(null, null, String.valueOf(categoryId));
        List<TagCategoryDTO> listCurrTag = search(tagCategoryCondition, 0, Integer.MAX_VALUE, "ASC", "id");

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

                    TagCategoryDTO tagCategoryDTO = new TagCategoryDTO(null, result.getId(), String.valueOf(categoryId));
                    result = insertTagCategory(userName, localeCode, countryCode, token, tagCategoryDTO);
                    if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                        TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                        return result;
                    }
                } else {
                    TagCategoryDTO tagCategoryDTO = new TagCategoryDTO(null, tagId, String.valueOf(categoryId));
                    if (listCurrTag.contains(tagCategoryDTO)) {
                        listCurrTag.remove(tagCategoryDTO);
                        continue;
                    }
                    result = insertTagCategory(userName, localeCode, countryCode, token, tagCategoryDTO);
                    if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                        TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                        return result;
                    }
                }
            }
        }

        //remove unused tag_category record
        for (TagCategoryDTO tag : listCurrTag) {
            result = deleteTagCategory(userName, localeCode, countryCode, token, tag.getId());
            if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                return result;
            }
        }
        result.setMessage(ParamUtils.SUCCESS);

        return result;
    }

    private String validate(Locale locale, Long categoryId, List<String> listTag) {
        if (categoryId == null) {
            return LanguageBundleUtils.getString(locale, "message.category.id.null");
        }
        Category category = categoryBusiness.findById(categoryId);
        if (category == null) {
            return LanguageBundleUtils.getString(locale, "message.category.id.notExist");
        }
        return null;
    }

    @Override
    public ResultDTO insertTagCategory(String userName, String localeCode, String countryCode, String token, TagCategoryDTO dto) {
        return createObject(dto);
    }

    @Override
    public ResultDTO updateTagCategory(String userName, String localeCode, String countryCode, String token, TagCategoryDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultDTO deleteTagCategory(String userName, String localeCode, String countryCode, String token, String id) {
        ResultDTO result = new ResultDTO();
        String resultStr = delete(Long.valueOf(id));
        result.setMessage(resultStr);
        return result;
    }

    @Override
    public List<String> getTagsListByCategory(String userName, String localeCode, String countryCode, String token, String id) {
        SQLQuery query = gettDAO().getSession().createSQLQuery("select t.name id from tags t "
                + "inner join tag_category l on t.id =l.tag_id"
                + " where l.category_id = ? ");
        query.addScalar("id", StringType.INSTANCE);
        query.setLong(0, Long.valueOf(id));
        return query.list();
    }
}
