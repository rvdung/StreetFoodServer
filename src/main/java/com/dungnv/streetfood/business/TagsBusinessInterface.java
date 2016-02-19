/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.streetfood.dto.TagsDTO;
import com.dungnv.streetfood.model.Tags;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;
import java.util.Map;
/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/24/2016 8:00 PM
 */
public interface TagsBusinessInterface extends BaseFWServiceInterface<TagsDTO,Tags>{
    Map<String, String> getMapTagsByName(List<String> listTagName);
    
    public ResultDTO insertTags(String userName , String localeCode, String countryCode, String token, TagsDTO dto);
    public ResultDTO updateTags(String userName , String localeCode, String countryCode, String token, TagsDTO dto);
    public ResultDTO deleteTags(String userName , String localeCode, String countryCode, String token, String id);
}


