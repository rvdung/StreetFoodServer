
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.dao;
import com.dungnv.vfw5.base.dao.BaseFWDAOImpl;
import com.dungnv.streetfood.model.RestaurantDishDetail;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
* @author dungnv
* @version 1.0
* @since 2/21/2016 12:49 PM
*/
@Repository("restaurantDishDetailDAO")
public class RestaurantDishDetailDAO extends BaseFWDAOImpl<RestaurantDishDetail, Long> {

    public RestaurantDishDetailDAO() {
        this.model= new RestaurantDishDetail();
    }

    public RestaurantDishDetailDAO(Session session) {
        this.session = session;
    }
}

