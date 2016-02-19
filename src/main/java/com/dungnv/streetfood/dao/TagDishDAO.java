
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.dao;
import com.dungnv.vfw5.base.dao.BaseFWDAOImpl;
import com.dungnv.streetfood.model.TagDish;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
* @author dungnv
* @version 1.0
* @since 1/26/2016 9:17 PM
*/
@Repository("tagDishDAO")
public class TagDishDAO extends BaseFWDAOImpl<TagDish, Long> {

    public TagDishDAO() {
        this.model= new TagDish();
    }

    public TagDishDAO(Session session) {
        this.session = session;
    }
}

