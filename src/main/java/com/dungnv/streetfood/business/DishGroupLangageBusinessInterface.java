/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.streetfood.dto.DishGroupLangageDTO;
import com.dungnv.streetfood.model.DishGroupLangage;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/21/2016 9:12 PM
 */
public interface DishGroupLangageBusinessInterface extends BaseFWServiceInterface<DishGroupLangageDTO, DishGroupLangage> {

    
    public ResultDTO insertDishGroupLangage(String userName, String localeCode, String countryCode, String token, Long categoryId, List<DishGroupLangageDTO> listDTO);
    
    public ResultDTO insertDishGroupLangage(String userName, String localeCode, String countryCode, String token, DishGroupLangageDTO dto);

    public ResultDTO updateDishGroupLangage(String userName, String localeCode, String countryCode, String token, DishGroupLangageDTO dto);

    public ResultDTO deleteDishGroupLangage(String userName, String localeCode, String countryCode, String token, DishGroupLangageDTO dto);

    public ResultDTO activeDishGroupLangage(String userName, String localeCode, String countryCode, String token, String id, Boolean active);
    
    
}
