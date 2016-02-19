
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.dao;
import com.dungnv.vfw5.base.dao.BaseFWDAOImpl;
import com.dungnv.streetfood.model.TagCategory;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
* @author dungnv
* @version 1.0
* @since 1/24/2016 7:34 PM
*/
@Repository("tagCategoryDAO")
public class TagCategoryDAO extends BaseFWDAOImpl<TagCategory, Long> {

    public TagCategoryDAO() {
        this.model= new TagCategory();
    }

    public TagCategoryDAO(Session session) {
        this.session = session;
    }
}

