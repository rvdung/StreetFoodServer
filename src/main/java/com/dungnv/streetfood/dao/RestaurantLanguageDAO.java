
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.dao;
import com.dungnv.vfw5.base.dao.BaseFWDAOImpl;
import com.dungnv.streetfood.model.RestaurantLanguage;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
* @author dungnv
* @version 1.0
* @since 2/21/2016 11:30 AM
*/
@Repository("restaurantLanguageDAO")
public class RestaurantLanguageDAO extends BaseFWDAOImpl<RestaurantLanguage, Long> {

    public RestaurantLanguageDAO() {
        this.model= new RestaurantLanguage();
    }

    public RestaurantLanguageDAO(Session session) {
        this.session = session;
    }
}

