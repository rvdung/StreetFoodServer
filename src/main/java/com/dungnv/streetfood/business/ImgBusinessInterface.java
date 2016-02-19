/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.streetfood.dto.ImgDTO;
import com.dungnv.streetfood.model.Img;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/25/2016 10:05 PM
 */
public interface ImgBusinessInterface extends BaseFWServiceInterface<ImgDTO, Img> {

    public ResultDTO insertImg(String userName, String localeCode, String countryCode, String token, ImgDTO dto);

    public ResultDTO updateImg(String userName, String localeCode, String countryCode, String token, ImgDTO dto);

    public ResultDTO updateMerge(String userName, String localeCode, String countryCode, String token, ImgDTO dto);

    public ResultDTO deleteImg(String userName, String localeCode, String countryCode, String token, String id);

    public ResultDTO attachImg(String userName, String localeCode, String countryCode//
            , String token, List<String> urls, String objectId, String objectType);
}
