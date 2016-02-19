
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.dao;
import com.dungnv.vfw5.base.dao.BaseFWDAOImpl;
import com.dungnv.streetfood.model.Restaurant;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
* @author dungnv
* @version 1.0
* @since 1/27/2016 11:45 PM
*/
@Repository("restaurantDAO")
public class RestaurantDAO extends BaseFWDAOImpl<Restaurant, Long> {

    public RestaurantDAO() {
        this.model= new Restaurant();
    }

    public RestaurantDAO(Session session) {
        this.session = session;
    }
}

