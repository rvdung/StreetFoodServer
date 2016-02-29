/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.streetfood.dto.ArticleLanguageDTO;
import com.dungnv.streetfood.model.ArticleLanguage;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/25/2016 10:07 PM
 */
public interface ArticleLanguageBusinessInterface extends BaseFWServiceInterface<ArticleLanguageDTO, ArticleLanguage> {

    public ResultDTO insertArticleLanguage(String userName, String localeCode, String countryCode, String token, ArticleLanguageDTO dto);

    public ResultDTO insertArticleLanguage(String userName, String localeCode, String countryCode, String token//
            , String dishId, List<ArticleLanguageDTO> listLanguage);

    public ResultDTO updateArticleLanguage(String userName, String localeCode, String countryCode, String token, ArticleLanguageDTO dto);

    public ResultDTO updateMergeArticleLanguage(String userName, String localeCode, String countryCode, String token, ArticleLanguageDTO dto);

    public ResultDTO deleteArticleLanguage(String userName, String localeCode, String countryCode, String token, String id);
}
