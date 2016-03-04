/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.streetfood.dto.DishDTO;
import com.dungnv.streetfood.dto.RestaurantDTO;
import com.dungnv.streetfood.dto.RestaurantDishDetailDTO;
import com.dungnv.streetfood.model.RestaurantDishDetail;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 2/21/2016 12:49 PM
 */
public interface RestaurantDishDetailBusinessInterface extends BaseFWServiceInterface<RestaurantDishDetailDTO, RestaurantDishDetail> {

    public List<RestaurantDTO> getListRestaurantByDish(String userName, String localeCode, String countryCode, String token, String id);

    public List<DishDTO> getListDishByRestaurant(String userName, String localeCode, String countryCode, String token, String id);

    public ResultDTO insertListDishToRestaurant(String userName, String localeCode, String countryCode, String token, String id, List<String> list);

    public ResultDTO insertListRestaurantToDish(String userName, String localeCode, String countryCode, String token, String id, List<String> list);
}
