/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.CategoryDishDTO;
import com.dungnv.streetfood.model.CategoryDish;
import com.dungnv.streetfood.dao.CategoryDishDAO;
import com.dungnv.streetfood.dto.CategoryDTO;
import com.dungnv.streetfood.dto.DishDTO;
import com.dungnv.streetfood.model.Category;
import org.hibernate.Session;
import java.util.*;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
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
public class CategoryDishBusiness extends BaseFWServiceImpl<CategoryDishDAO, CategoryDishDTO, CategoryDish> implements CategoryDishBusinessInterface {

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

    @Override
    public List<CategoryDTO> getListCategoryByDish(String userName, String localeCode, String countryCode, String token, String id) {
        SQLQuery query = gettDAO().getSession().createSQLQuery("select c.id, c.name from Category c "
                + " inner join category_dish l on c.id =l.category_id"
                + " where l.dish_id = ? ");
        query.addScalar("id", StringType.INSTANCE);
        query.addScalar("name", StringType.INSTANCE);
        query.setLong(0, Long.valueOf(id));
        query.setResultTransformer(Transformers.aliasToBean(CategoryDTO.class));
        return query.list();
    }

    @Override
    public List<DishDTO> getListDishByCategory(String userName, String localeCode, String countryCode, String token, String id) {
        SQLQuery query = gettDAO().getSession().createSQLQuery("select c.id, c.name from dish c "
                + " inner join category_dish l on c.id =l.dish_id"
                + " where l.category_id = ? ");
        query.addScalar("id", StringType.INSTANCE);
        query.addScalar("name", StringType.INSTANCE);
        query.setLong(0, Long.valueOf(id));
        query.setResultTransformer(Transformers.aliasToBean(DishDTO.class));
        return query.list();
    }
}
