
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.dao;
import com.dungnv.vfw5.base.dao.BaseFWDAOImpl;
import com.dungnv.streetfood.model.Article;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
* @author dungnv
* @version 1.0
* @since 1/21/2016 12:48 AM
*/
@Repository("articleDAO")
public class ArticleDAO extends BaseFWDAOImpl<Article, Long> {

    public ArticleDAO() {
        this.model= new Article();
    }

    public ArticleDAO(Session session) {
        this.session = session;
    }
}

