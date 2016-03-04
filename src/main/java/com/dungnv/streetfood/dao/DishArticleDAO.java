
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.dao;

import com.dungnv.vfw5.base.dao.BaseFWDAOImpl;
import com.dungnv.streetfood.model.DishArticle;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author dungnv
 * @version 1.0
 * @since 2/29/2016 10:24 PM
 */
@Repository("dishArticleDAO")
public class DishArticleDAO extends BaseFWDAOImpl<DishArticle, Long> {

    public DishArticleDAO() {
        this.model = new DishArticle();
    }

    public DishArticleDAO(Session session) {
        this.session = session;
    }
}
