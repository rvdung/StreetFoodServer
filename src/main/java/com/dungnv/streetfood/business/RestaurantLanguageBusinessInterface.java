/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.streetfood.dto.RestaurantLanguageDTO;
import com.dungnv.streetfood.model.RestaurantLanguage;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 2/21/2016 11:30 AM
 */
public interface RestaurantLanguageBusinessInterface extends BaseFWServiceInterface<RestaurantLanguageDTO, RestaurantLanguage> {

    public ResultDTO insertRestaurantLanguage(String userName, String localeCode, String countryCode, String token, RestaurantLanguageDTO dto);

    public ResultDTO insertRestaurantLanguage(String userName, String localeCode, String countryCode, String token//
            , String restaurantId, List<RestaurantLanguageDTO> listLanguage);

    public ResultDTO updateRestaurantLanguage(String userName, String localeCode, String countryCode, String token, RestaurantLanguageDTO dto);

    public ResultDTO updateMergeRestaurantLanguage(String userName, String localeCode, String countryCode, String token, RestaurantLanguageDTO dto);

    public ResultDTO deleteRestaurantLanguage(String userName, String localeCode, String countryCode, String token, String id);
}
