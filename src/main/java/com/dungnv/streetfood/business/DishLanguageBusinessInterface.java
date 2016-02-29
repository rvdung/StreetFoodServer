/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.streetfood.dto.DishLanguageDTO;
import com.dungnv.streetfood.model.DishLanguage;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/25/2016 10:02 PM
 */
public interface DishLanguageBusinessInterface extends BaseFWServiceInterface<DishLanguageDTO, DishLanguage> {

    public ResultDTO insertDishLanguage(String userName, String localeCode, String countryCode, String token, DishLanguageDTO dto);

    public ResultDTO insertDishLanguage(String userName, String localeCode, String countryCode, String token//
            , String dishId, List<DishLanguageDTO> listLanguage);

    public ResultDTO updateDishLanguage(String userName, String localeCode, String countryCode, String token, DishLanguageDTO dto);
    
    public ResultDTO updateMergeDishLanguage(String userName, String localeCode, String countryCode, String token, DishLanguageDTO dto);

    public ResultDTO deleteDishLanguage(String userName, String localeCode, String countryCode, String token, String id);
}
