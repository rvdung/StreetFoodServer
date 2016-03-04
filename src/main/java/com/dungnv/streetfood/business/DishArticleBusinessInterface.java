/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.streetfood.dto.ArticleDTO;
import com.dungnv.streetfood.dto.DishArticleDTO;
import com.dungnv.streetfood.dto.DishDTO;
import com.dungnv.streetfood.model.DishArticle;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 2/29/2016 10:24 PM
 */
public interface DishArticleBusinessInterface extends BaseFWServiceInterface<DishArticleDTO, DishArticle> {

    public List<ArticleDTO> getListArticleByDish(String userName, String localeCode, String countryCode, String token, String id);

    public List<DishDTO> getListDishByArticle(String userName, String localeCode, String countryCode, String token, String id);

    public ResultDTO insertListDishToArticle(String userName, String localeCode, String countryCode, String token, String id, List<String> list);

    public ResultDTO insertListArticleToDish(String userName, String localeCode, String countryCode, String token, String id, List<String> list);
}
