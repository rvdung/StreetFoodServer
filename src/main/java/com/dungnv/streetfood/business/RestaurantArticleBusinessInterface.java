/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.streetfood.dto.ArticleDTO;
import com.dungnv.streetfood.dto.RestaurantArticleDTO;
import com.dungnv.streetfood.dto.RestaurantDTO;
import com.dungnv.streetfood.model.RestaurantArticle;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 2/21/2016 12:49 PM
 */
public interface RestaurantArticleBusinessInterface extends BaseFWServiceInterface<RestaurantArticleDTO, RestaurantArticle> {

    public List<ArticleDTO> getListArticleByRestaurant(String userName, String localeCode, String countryCode, String token, String id);

    public List<RestaurantDTO> getListRestaurantByArticle(String userName, String localeCode, String countryCode, String token, String id);

    public ResultDTO insertListRestaurantToArticle(String userName, String localeCode, String countryCode, String token, String id, List<String> list);

    public ResultDTO insertListArticleToRestaurant(String userName, String localeCode, String countryCode, String token, String id, List<String> list);
}
