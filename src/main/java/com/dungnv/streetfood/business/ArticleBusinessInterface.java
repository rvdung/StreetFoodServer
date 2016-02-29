/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.streetfood.dto.ArticleDTO;
import com.dungnv.streetfood.model.Article;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/21/2016 12:48 AM
 */
public interface ArticleBusinessInterface extends BaseFWServiceInterface<ArticleDTO, Article> {

    public ResultDTO insertArticle(String userName, String localeCode, String countryCode, String token, ArticleDTO dto);

    public ResultDTO updateArticle(String userName, String localeCode, String countryCode, String token, ArticleDTO dto);

    public ResultDTO deleteArticle(String userName, String localeCode, String countryCode, String token, String id);

    public ResultDTO activeArticle(String userName, String localeCode, String countryCode, String token, String id, Boolean active);

    public List<ArticleDTO> getListArticleDTOLess(String userName, String localeCode, String countryCode, String token, //
            ArticleDTO dishDTO, int rowStart, int maxRow, boolean isCount, String sortType, String sortFieldList);

    public ArticleDTO getArticleDetail(String userName, String localeCode, String countryCode, String token, String id);
}
