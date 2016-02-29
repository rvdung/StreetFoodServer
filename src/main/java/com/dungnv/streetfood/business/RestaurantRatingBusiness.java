/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.RestaurantRatingDTO;
import com.dungnv.streetfood.model.RestaurantRating;
import com.dungnv.streetfood.dao.RestaurantRatingDAO;
import org.hibernate.Session;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author dungnv
 * @version 1.0
 * @since 2/21/2016 12:50 PM
 */
@Service("restaurantRatingBusiness")
@Transactional
public class RestaurantRatingBusiness extends BaseFWServiceImpl<RestaurantRatingDAO, RestaurantRatingDTO, RestaurantRating> implements RestaurantRatingBusinessInterface{
	
    @Autowired
    private RestaurantRatingDAO restaurantRatingDAO;

    public RestaurantRatingBusiness() {
        tModel = new RestaurantRating();
        tDAO = restaurantRatingDAO;
    }
    @Override
    public RestaurantRatingDAO gettDAO() {
        return restaurantRatingDAO;
    }
    
    public RestaurantRatingBusiness(Session session) {
        this.session = session;
        tModel = new RestaurantRating();
        tDAO = restaurantRatingDAO;
    }
}


