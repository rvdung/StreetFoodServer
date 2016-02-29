/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.streetfood.dto.TagDishDTO;
import com.dungnv.streetfood.model.TagDish;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/26/2016 9:17 PM
 */
public interface TagDishBusinessInterface extends BaseFWServiceInterface<TagDishDTO, TagDish> {

    public ResultDTO insertTagDish(String userName, String localeCode, String countryCode, String token, Long categoryId, List<String> listTag);

    public ResultDTO insertTagDish(String userName, String localeCode, String countryCode, String token, TagDishDTO dto);

    public ResultDTO updateTagDish(String userName, String localeCode, String countryCode, String token, TagDishDTO dto);

    public ResultDTO deleteTagDish(String userName, String localeCode, String countryCode, String token, String id);
    
    public List<String> getTagsListByDish(String userName, String localeCode, String countryCode, String token, String id);
}
