/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.streetfood.dto.RestaurantDTO;
import com.dungnv.streetfood.model.Restaurant;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/27/2016 11:45 PM
 */
public interface RestaurantBusinessInterface extends BaseFWServiceInterface<RestaurantDTO, Restaurant> {

    public ResultDTO insertRestaurant(String userName, String localeCode, String countryCode, String token, RestaurantDTO dto);

    public ResultDTO updateRestaurant(String userName, String localeCode, String countryCode, String token, RestaurantDTO dto);

    public ResultDTO deleteRestaurant(String userName, String localeCode, String countryCode, String token, String id);

    public ResultDTO activeRestaurant(String userName, String localeCode, String countryCode, String token, String id, Boolean active);

    public List<RestaurantDTO> getListRestaurantDTOLess(String userName, String localeCode, String countryCode, String token, //
            RestaurantDTO dishDTO, int rowStart, int maxRow, boolean isCount, String sortType, String sortFieldList);

    public RestaurantDTO getRestaurantDetail(String userName, String localeCode, String countryCode, String token, String id);
}
