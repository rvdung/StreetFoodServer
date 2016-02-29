/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.RestaurantDishDetailDTO;
import com.dungnv.streetfood.model.RestaurantDishDetail;
import com.dungnv.streetfood.dao.RestaurantDishDetailDAO;
import org.hibernate.Session;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author dungnv
 * @version 1.0
 * @since 2/21/2016 12:49 PM
 */
@Service("restaurantDishDetailBusiness")
@Transactional
public class RestaurantDishDetailBusiness extends BaseFWServiceImpl<RestaurantDishDetailDAO, RestaurantDishDetailDTO, RestaurantDishDetail> implements RestaurantDishDetailBusinessInterface{
	
    @Autowired
    private RestaurantDishDetailDAO restaurantDishDetailDAO;

    public RestaurantDishDetailBusiness() {
        tModel = new RestaurantDishDetail();
        tDAO = restaurantDishDetailDAO;
    }
    @Override
    public RestaurantDishDetailDAO gettDAO() {
        return restaurantDishDetailDAO;
    }
    
    public RestaurantDishDetailBusiness(Session session) {
        this.session = session;
        tModel = new RestaurantDishDetail();
        tDAO = restaurantDishDetailDAO;
    }
}


