
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.dao;
import com.dungnv.vfw5.base.dao.BaseFWDAOImpl;
import com.dungnv.streetfood.model.RestaurantRating;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
* @author dungnv
* @version 1.0
* @since 2/21/2016 12:50 PM
*/
@Repository("restaurantRatingDAO")
public class RestaurantRatingDAO extends BaseFWDAOImpl<RestaurantRating, Long> {

    public RestaurantRatingDAO() {
        this.model= new RestaurantRating();
    }

    public RestaurantRatingDAO(Session session) {
        this.session = session;
    }
}

