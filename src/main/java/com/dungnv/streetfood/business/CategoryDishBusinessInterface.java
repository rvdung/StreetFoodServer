/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.streetfood.dto.CategoryDTO;
import com.dungnv.streetfood.dto.CategoryDishDTO;
import com.dungnv.streetfood.dto.DishDTO;
import com.dungnv.streetfood.model.CategoryDish;
import com.dungnv.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/25/2016 10:09 PM
 */
public interface CategoryDishBusinessInterface extends BaseFWServiceInterface<CategoryDishDTO, CategoryDish> {

    public List<CategoryDTO> getListCategoryByDish(String userName, String localeCode, String countryCode, String token, String id);

    public List<DishDTO> getListDishByCategory(String userName, String localeCode, String countryCode, String token, String id);
}
