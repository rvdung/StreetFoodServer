/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.streetfood.dto.DishDTO;
import com.dungnv.streetfood.model.Dish;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/25/2016 10:01 PM
 */
public interface DishBusinessInterface extends BaseFWServiceInterface<DishDTO, Dish> {

    public ResultDTO insertDish(String userName, String localeCode, String countryCode, String token, DishDTO dto);

    public ResultDTO updateDish(String userName, String localeCode, String countryCode, String token, DishDTO dto);

    public ResultDTO deleteDish(String userName, String localeCode, String countryCode, String token, String id);

    public ResultDTO activeDish(String userName, String localeCode, String countryCode, String token, String id, Boolean active);

    public List<DishDTO> getListDishDTOLess(String userName, String localeCode, String countryCode, String token, //
            DishDTO dishDTO, int rowStart, int maxRow, boolean isCount, String sortType, String sortFieldList);

    public DishDTO getDishDetail(String userName, String localeCode, String countryCode, String token, String id);
}
