/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.streetfood.dto.SlideShowLanguageDTO;
import com.dungnv.streetfood.model.SlideShowLanguage;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 2/21/2016 1:04 PM
 */
public interface SlideShowLanguageBusinessInterface extends BaseFWServiceInterface<SlideShowLanguageDTO, SlideShowLanguage> {

    public ResultDTO insertSlideShowLanguage(String userName, String localeCode, String countryCode, String token, SlideShowLanguageDTO dto);

    public ResultDTO insertSlideShowLanguage(String userName, String localeCode, String countryCode, String token//
            , String dishId, List<SlideShowLanguageDTO> listLanguage);

    public ResultDTO updateSlideShowLanguage(String userName, String localeCode, String countryCode, String token, SlideShowLanguageDTO dto);

    public ResultDTO updateMergeSlideShowLanguage(String userName, String localeCode, String countryCode, String token, SlideShowLanguageDTO dto);

    public ResultDTO deleteSlideShowLanguage(String userName, String localeCode, String countryCode, String token, String id);
}
