/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.ArticleLanguageDTO;
import com.dungnv.streetfood.model.ArticleLanguage;
import com.dungnv.streetfood.dao.ArticleLanguageDAO;
import com.dungnv.streetfood.model.Article;
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
 * @since 1/25/2016 10:07 PM
 */
@Service("articleLanguageBusiness")
@Transactional
public class ArticleLanguageBusiness extends BaseFWServiceImpl<ArticleLanguageDAO, ArticleLanguageDTO, ArticleLanguage> implements ArticleLanguageBusinessInterface {

    @Autowired
    private ArticleLanguageDAO articleLanguageDAO;
    @Autowired
    private LocaleBusinessInterface localeBusiness;
    @Autowired
    private ArticleBusinessInterface articleBusiness;

    public ArticleLanguageBusiness() {
        tModel = new ArticleLanguage();
        tDAO = articleLanguageDAO;
    }

    @Override
    public ArticleLanguageDAO gettDAO() {
        return articleLanguageDAO;
    }

    public ArticleLanguageBusiness(Session session) {
        this.session = session;
        tModel = new ArticleLanguage();
        tDAO = articleLanguageDAO;
    }

    @Override
    public ResultDTO insertArticleLanguage(String userName, String localeCode, String countryCode, String token, ArticleLanguageDTO dto) {
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
    public ResultDTO updateArticleLanguage(String userName, String localeCode, String countryCode, String token, ArticleLanguageDTO dto) {
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
    public ResultDTO updateMergeArticleLanguage(String userName, String localeCode, String countryCode, String token, ArticleLanguageDTO dto) {
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
    public ResultDTO deleteArticleLanguage(String userName, String localeCode, String countryCode, String token, String id) {
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
    public ResultDTO insertArticleLanguage(String userName, String localeCode, String countryCode//
            , String token, String articleId, List<ArticleLanguageDTO> listLanguage) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);

        String validate = validate(locale, articleId, listLanguage);
        if (!StringUtils.isNullOrEmpty(validate)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(validate);
            return result;
        }

        // Get all current language
        ArticleLanguageDTO articleLanguageCondition = new ArticleLanguageDTO();
        articleLanguageCondition.setArticleId(String.valueOf(articleId));
        List<ArticleLanguageDTO> listCurrLanguage = search(articleLanguageCondition, 0, Integer.MAX_VALUE, "ASC", "id");
        Map<String, ArticleLanguageDTO> mapCurrLanguage = new HashMap<>();
        for (ArticleLanguageDTO obj : listCurrLanguage) {
            mapCurrLanguage.put(obj.getLanguageCode(), obj);
        }

        if (listLanguage != null && !listLanguage.isEmpty()) {
            for (ArticleLanguageDTO articleLangDTO : listLanguage) {
                ArticleLanguageDTO currLang = mapCurrLanguage.get(articleLangDTO.getLanguageCode());
                if (currLang == null) {
                    currLang = articleLangDTO;
                    currLang.setArticleId(articleId);
                    result = insertArticleLanguage(userName, localeCode, countryCode, token, currLang);

                    if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                        TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                        return result;
                    }
                } else {
                    currLang.setTitle(articleLangDTO.getTitle());
                    currLang.setShortContent(articleLangDTO.getShortContent());
                    currLang.setContent(articleLangDTO.getContent());

                    result = updateMergeArticleLanguage(userName, localeCode, countryCode, token, currLang);
                    if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                        TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                        return result;
                    }
                    // remove exist language
                    listCurrLanguage.remove(currLang);
                }
            }
        }

        //remove unused tag_article record
        for (ArticleLanguageDTO tag : listCurrLanguage) {
            result = deleteArticleLanguage(userName, localeCode, countryCode, token, tag.getId());
            if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                return result;
            }
        }
        result.setMessage(ParamUtils.SUCCESS);

        return result;
    }

    private String validate(Locale locale, ArticleLanguageDTO dto, String action) {

        if (dto == null) {
            return LanguageBundleUtils.getString(locale, "message.articleLanguage.model.null");
        }

        if (Constants.ACTION_TYPE.UPDATE.equals(action)) {
            if (StringUtils.isNullOrEmpty(dto.getId())) {
                return LanguageBundleUtils.getString(locale, "message.articleLanguage.id.null");
            }
        }

        if (StringUtils.isNullOrEmpty(dto.getTitle())) {
            return LanguageBundleUtils.getString(locale, "message.articleLanguage.title.null");
        }
        if (dto.getTitle().length() > 255) {
            return LanguageBundleUtils.getString(locale, "message.articleLanguage.title.overLength.255");
        }

        if (StringUtils.isNullOrEmpty(dto.getArticleId())) {
            return LanguageBundleUtils.getString(locale, "message.article.id.null");
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
            Article article = articleBusiness.findById(Long.valueOf(dto.getArticleId()));
            if (article == null) {
                return LanguageBundleUtils.getString(locale, "message.article.id.notExist");
            }
        } catch (NumberFormatException e) {
            return LanguageBundleUtils.getString(locale, "message.article.id.invalid");
        }

        return null;
    }
    
    private String validate(Locale locale, String articleId, List<ArticleLanguageDTO> listLanguage) {
        if (articleId == null) {
            return LanguageBundleUtils.getString(locale, "message.article.id.null");
        }

        return null;
    }
}
