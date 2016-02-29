/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.streetfood.dto.TagArticleDTO;
import com.dungnv.streetfood.model.TagArticle;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 2/21/2016 12:59 PM
 */
public interface TagArticleBusinessInterface extends BaseFWServiceInterface<TagArticleDTO, TagArticle> {

    public ResultDTO insertTagArticle(String userName, String localeCode, String countryCode, String token, Long categoryId, List<String> listTag);

    public ResultDTO insertTagArticle(String userName, String localeCode, String countryCode, String token, TagArticleDTO dto);

    public ResultDTO updateTagArticle(String userName, String localeCode, String countryCode, String token, TagArticleDTO dto);

    public ResultDTO deleteTagArticle(String userName, String localeCode, String countryCode, String token, String id);

    public List<String> getTagsListByArticle(String userName, String localeCode, String countryCode, String token, String id);
}
