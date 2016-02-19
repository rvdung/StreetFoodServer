/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.streetfood.dto.TagCategoryDTO;
import com.dungnv.streetfood.dto.TagsDTO;
import com.dungnv.streetfood.model.TagCategory;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/24/2016 7:34 PM
 */
public interface TagCategoryBusinessInterface extends BaseFWServiceInterface<TagCategoryDTO, TagCategory> {

    public ResultDTO insertTagCategory(String userName, String localeCode, String countryCode, String token, Long categoryId, List<String> listTag);

    public ResultDTO insertTagCategory(String userName, String localeCode, String countryCode, String token, TagCategoryDTO dto);

    public ResultDTO updateTagCategory(String userName, String localeCode, String countryCode, String token, TagCategoryDTO dto);

    public ResultDTO deleteTagCategory(String userName, String localeCode, String countryCode, String token, String id);
    
    public List<String> getTagsListByCategory(String userName, String localeCode, String countryCode, String token, String id);

}
