/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.RestaurantDTO;
import com.dungnv.streetfood.model.Restaurant;
import com.dungnv.streetfood.dao.RestaurantDAO;
import org.hibernate.Session;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/27/2016 11:45 PM
 */
@Service("restaurantBusiness")
@Transactional
public class RestaurantBusiness extends BaseFWServiceImpl<RestaurantDAO, RestaurantDTO, Restaurant> implements RestaurantBusinessInterface{
	
    @Autowired
    private RestaurantDAO restaurantDAO;

    public RestaurantBusiness() {
        tModel = new Restaurant();
        tDAO = restaurantDAO;
    }
    @Override
    public RestaurantDAO gettDAO() {
        return restaurantDAO;
    }
    
    public RestaurantBusiness(Session session) {
        this.session = session;
        tModel = new Restaurant();
        tDAO = restaurantDAO;
    }
}


