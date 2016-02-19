
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.dao;
import com.dungnv.vfw5.base.dao.BaseFWDAOImpl;
import com.dungnv.streetfood.model.ArticleLanguage;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
* @author dungnv
* @version 1.0
* @since 1/25/2016 10:07 PM
*/
@Repository("articleLanguageDAO")
public class ArticleLanguageDAO extends BaseFWDAOImpl<ArticleLanguage, Long> {

    public ArticleLanguageDAO() {
        this.model= new ArticleLanguage();
    }

    public ArticleLanguageDAO(Session session) {
        this.session = session;
    }
}

