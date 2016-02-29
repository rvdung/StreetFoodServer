/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.streetfood.dto.SlideShowDTO;
import com.dungnv.streetfood.model.SlideShow;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/27/2016 11:48 PM
 */
public interface SlideShowBusinessInterface extends BaseFWServiceInterface<SlideShowDTO, SlideShow> {

    public ResultDTO insertSlideShow(String userName, String localeCode, String countryCode, String token, SlideShowDTO dto);

    public ResultDTO updateSlideShow(String userName, String localeCode, String countryCode, String token, SlideShowDTO dto);

    public ResultDTO deleteSlideShow(String userName, String localeCode, String countryCode, String token, String id);

    public ResultDTO activeSlideShow(String userName, String localeCode, String countryCode, String token, String id, Boolean active);

    public List<SlideShowDTO> getListSlideShowDTOLess(String userName, String localeCode, String countryCode, String token, //
            SlideShowDTO dishDTO, int rowStart, int maxRow, boolean isCount, String sortType, String sortFieldList);

    public SlideShowDTO getSlideShowDetail(String userName, String localeCode, String countryCode, String token, String id);
}
