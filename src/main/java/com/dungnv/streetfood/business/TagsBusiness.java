/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.TagsDTO;
import com.dungnv.streetfood.model.Tags;
import com.dungnv.streetfood.dao.TagsDAO;
import com.dungnv.streetfood.dto.DishGroupLangageDTO;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.utils.Constants;
import com.dungnv.vfw5.base.utils.DataUtil;
import com.dungnv.vfw5.base.utils.DateTimeUtils;
import com.dungnv.vfw5.base.utils.LanguageBundleUtils;
import com.dungnv.vfw5.base.utils.ParamUtils;
import com.dungnv.vfw5.base.utils.QueryUtil;
import com.dungnv.vfw5.base.utils.StringUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.hibernate.Session;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/24/2016 8:00 PM
 */
@Service("tagsBusiness")
@Transactional
public class TagsBusiness extends BaseFWServiceImpl<TagsDAO, TagsDTO, Tags> implements TagsBusinessInterface {

    @Autowired
    private TagsDAO tagsDAO;

    public TagsBusiness() {
        tModel = new Tags();
        tDAO = tagsDAO;
    }

    @Override
    public TagsDAO gettDAO() {
        return tagsDAO;
    }

    public TagsBusiness(Session session) {
        this.session = session;
        tModel = new Tags();
        tDAO = tagsDAO;
    }

    @Override
    public Map<String, String> getMapTagsByName(List<String> listTagName) {
        Map<String, String> map = new HashMap<>();

        Type[] types = new Type[listTagName.size()];
        String[] param = new String[listTagName.size()];
        Arrays.fill(types, StringType.INSTANCE);
        Criteria cri = gettDAO().getSession().createCriteria(Tags.class);
        cri.add(Restrictions.sqlRestriction(" lower(" + Tags.NAME + ") in " + QueryUtil.getParameterHolderString(listTagName.size())//
                , StringUtils.trimStringToNewList(listTagName, Boolean.TRUE).toArray(param)//
                , types));
        
        List<Tags> listTags = cri.list();
        for (Tags tags : listTags) {
            map.put(tags.getName().toLowerCase(), tags.getId().toString());
        }
        for (String tags : listTagName) {
            if (!map.containsKey(tags.trim().toLowerCase())) {
                map.put(tags.trim().toLowerCase(), null);
            }
        }
        return map;
    }

    @Override
    public ResultDTO insertTags(String userName, String localeCode, String countryCode, String token, TagsDTO dto) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);

        String validate = validate(locale, dto, Constants.ACTION_TYPE.INSERT);
        if (!StringUtils.isNullOrEmpty(validate)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(validate);
            return result;
        }
        
        dto.setHit("0");
        return result = createObject(dto);
        
    }

    @Override
    public ResultDTO updateTags(String userName, String localeCode, String countryCode, String token, TagsDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultDTO deleteTags(String userName, String localeCode, String countryCode, String token, String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private String validate(Locale locale, TagsDTO dto, String action) {
        if (dto == null) {
            return LanguageBundleUtils.getString(locale, "message.tags.model.null");
        }
        if (dto.getName() == null) {
            return LanguageBundleUtils.getString(locale, "message.tags.name.null");
        }
        if (dto.getName().length() > 255) {
            return LanguageBundleUtils.getString(locale, "message.tags.name.overLength.255");
        }

        if (dto.getDescription() != null && dto.getDescription().length() > 65000) {
            return LanguageBundleUtils.getString(locale, "message.tags.description.overLength.65000");
        }
        return null;
    }
}
