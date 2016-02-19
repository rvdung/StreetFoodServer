/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.streetfood.dto.UserDTO;
import com.dungnv.streetfood.model.User;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.service.BaseFWServiceInterface;
/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/31/2016 10:31 PM
 */
public interface UserBusinessInterface extends BaseFWServiceInterface<UserDTO,User>{
    public ResultDTO login(String userName , String localeCode, String countryCode, String token, String password);
}


