/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.model.TagArticle;
import com.dungnv.streetfood.dao.TagArticleDAO;
import com.dungnv.streetfood.dto.TagArticleDTO;
import com.dungnv.streetfood.dto.TagsDTO;
import com.dungnv.streetfood.model.Article;
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
 * @since 2/21/2016 12:59 PM
 */
@Service("tagArticleBusiness")
@Transactional
public class TagArticleBusiness extends BaseFWServiceImpl<TagArticleDAO, TagArticleDTO, TagArticle> implements TagArticleBusinessInterface{
	
    @Autowired
    private TagArticleDAO tagArticleDAO;
      @Autowired
    private TagsBusinessInterface tagsBusiness;
    @Autowired
    private ArticleBusinessInterface articleBusiness;

    public TagArticleBusiness() {
        tModel = new TagArticle();
        tDAO = tagArticleDAO;
    }
    @Override
    public TagArticleDAO gettDAO() {
        return tagArticleDAO;
    }
    
    public TagArticleBusiness(Session session) {
        this.session = session;
        tModel = new TagArticle();
        tDAO = tagArticleDAO;
    }

    @Override
    public ResultDTO insertTagArticle(String userName, String localeCode, String countryCode, String token, Long articleId, List<String> listTag) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);

        String validate = validate(locale, articleId, listTag);
        if (!StringUtils.isNullOrEmpty(validate)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(validate);
            return result;
        }

        // Get all current tag
        TagArticleDTO tagArticleCondition = new TagArticleDTO(null, null, String.valueOf(articleId));
        List<TagArticleDTO> listCurrTag = search(tagArticleCondition, 0, Integer.MAX_VALUE, "ASC", "id");

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

                    TagArticleDTO tagArticleDTO = new TagArticleDTO(null, result.getId(), String.valueOf(articleId));
                    result = insertTagArticle(userName, localeCode, countryCode, token, tagArticleDTO);
                    if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                        TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                        return result;
                    }
                } else {
                    TagArticleDTO tagArticleDTO = new TagArticleDTO(null, tagId, String.valueOf(articleId));
                    if (listCurrTag.contains(tagArticleDTO)) {
                        listCurrTag.remove(tagArticleDTO);
                        continue;
                    }
                    result = insertTagArticle(userName, localeCode, countryCode, token, tagArticleDTO);
                    if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                        TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                        return result;
                    }
                }
            }
        }

        //remove unused tag_article record
        for (TagArticleDTO tag : listCurrTag) {
            result = deleteTagArticle(userName, localeCode, countryCode, token, tag.getId());
            if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                return result;
            }
        }
        result.setMessage(ParamUtils.SUCCESS);

        return result;
    }

    @Override
    public ResultDTO insertTagArticle(String userName, String localeCode, String countryCode, String token, TagArticleDTO dto) {
        return createObject(dto);
    }

    @Override
    public ResultDTO updateTagArticle(String userName, String localeCode, String countryCode, String token, TagArticleDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultDTO deleteTagArticle(String userName, String localeCode, String countryCode, String token, String id) {
          ResultDTO result = new ResultDTO();
        String resultStr = delete(Long.valueOf(id));
        result.setMessage(resultStr);
        return result;
    }

    @Override
    public List<String> getTagsListByArticle(String userName, String localeCode, String countryCode, String token, String id) {
        SQLQuery query = gettDAO().getSession().createSQLQuery("select t.name id from tags t "
                + "inner join tag_article l on t.id =l.tag_id"
                + " where l.article_id = ? ");
        query.addScalar("id", StringType.INSTANCE);
        query.setLong(0, Long.valueOf(id));
        return query.list();
    }
    
     private String validate(Locale locale, Long articleId, List<String> listTag) {
        if (articleId == null) {
            return LanguageBundleUtils.getString(locale, "message.article.id.null");
        }
        Article article = articleBusiness.findById(articleId);
        if (article == null) {
            return LanguageBundleUtils.getString(locale, "message.article.id.notExist");
        }
        return null;
    }
}


