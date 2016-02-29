/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.RestaurantArticleDTO;
import com.dungnv.streetfood.model.RestaurantArticle;
import com.dungnv.streetfood.dao.RestaurantArticleDAO;
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
@Service("restaurantArticleBusiness")
@Transactional
public class RestaurantArticleBusiness extends BaseFWServiceImpl<RestaurantArticleDAO, RestaurantArticleDTO, RestaurantArticle> implements RestaurantArticleBusinessInterface{
	
    @Autowired
    private RestaurantArticleDAO restaurantArticleDAO;

    public RestaurantArticleBusiness() {
        tModel = new RestaurantArticle();
        tDAO = restaurantArticleDAO;
    }
    @Override
    public RestaurantArticleDAO gettDAO() {
        return restaurantArticleDAO;
    }
    
    public RestaurantArticleBusiness(Session session) {
        this.session = session;
        tModel = new RestaurantArticle();
        tDAO = restaurantArticleDAO;
    }
}


