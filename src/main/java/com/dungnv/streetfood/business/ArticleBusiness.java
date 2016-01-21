/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.ArticleDTO;
import com.dungnv.streetfood.model.Article;
import com.dungnv.streetfood.dao.ArticleDAO;
import org.hibernate.Session;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/21/2016 12:48 AM
 */
@Service("articleBusiness")
@Transactional
public class ArticleBusiness extends BaseFWServiceImpl<ArticleDAO, ArticleDTO, Article> implements ArticleBusinessInterface{
	
    @Autowired
    private ArticleDAO articleDAO;

    public ArticleBusiness() {
        tModel = new Article();
        tDAO = articleDAO;
    }
    @Override
    public ArticleDAO gettDAO() {
        return articleDAO;
    }
    
    public ArticleBusiness(Session session) {
        this.session = session;
        tModel = new Article();
        tDAO = articleDAO;
    }
}


