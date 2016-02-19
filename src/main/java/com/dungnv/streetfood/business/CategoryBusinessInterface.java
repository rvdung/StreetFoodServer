/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.streetfood.dto.CategoryDTO;
import com.dungnv.streetfood.model.Category;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/22/2016 9:48 PM
 */
public interface CategoryBusinessInterface extends BaseFWServiceInterface<CategoryDTO, Category> {

    public ResultDTO insertCategory(String userName, String localeCode, String countryCode, String token, CategoryDTO dto);

    public ResultDTO updateCategory(String userName, String localeCode, String countryCode, String token, CategoryDTO dto);

    public ResultDTO deleteCategory(String userName, String localeCode, String countryCode, String token, String id);

    public ResultDTO activeCategory(String userName, String localeCode, String countryCode, String token, String id, Boolean active);

    public List<CategoryDTO> getListCategoryDTOLess(String userName, String localeCode, String countryCode, String token, //
            CategoryDTO categoryDTO, int rowStart, int maxRow, boolean isCount, String sortType, String sortFieldList);

    public CategoryDTO getCategoryDetail(String userName, String localeCode, String countryCode, String token, String id);
}
