/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.streetfood.dto.TagRestaurantDTO;
import com.dungnv.streetfood.model.TagRestaurant;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 2/21/2016 12:58 PM
 */
public interface TagRestaurantBusinessInterface extends BaseFWServiceInterface<TagRestaurantDTO, TagRestaurant> {

    public ResultDTO insertTagRestaurant(String userName, String localeCode, String countryCode, String token, Long categoryId, List<String> listTag);

    public ResultDTO insertTagRestaurant(String userName, String localeCode, String countryCode, String token, TagRestaurantDTO dto);

    public ResultDTO updateTagRestaurant(String userName, String localeCode, String countryCode, String token, TagRestaurantDTO dto);

    public ResultDTO deleteTagRestaurant(String userName, String localeCode, String countryCode, String token, String id);

    public List<String> getTagsListByRestaurant(String userName, String localeCode, String countryCode, String token, String id);
}
