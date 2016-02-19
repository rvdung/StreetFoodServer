/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.CategoryDishDTO;
import com.dungnv.streetfood.model.CategoryDish;
import com.dungnv.streetfood.dao.CategoryDishDAO;
import org.hibernate.Session;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/25/2016 10:09 PM
 */
@Service("categoryDishBusiness")
@Transactional
public class CategoryDishBusiness extends BaseFWServiceImpl<CategoryDishDAO, CategoryDishDTO, CategoryDish> implements CategoryDishBusinessInterface{
	
    @Autowired
    private CategoryDishDAO categoryDishDAO;

    public CategoryDishBusiness() {
        tModel = new CategoryDish();
        tDAO = categoryDishDAO;
    }
    @Override
    public CategoryDishDAO gettDAO() {
        return categoryDishDAO;
    }
    
    public CategoryDishBusiness(Session session) {
        this.session = session;
        tModel = new CategoryDish();
        tDAO = categoryDishDAO;
    }
}


