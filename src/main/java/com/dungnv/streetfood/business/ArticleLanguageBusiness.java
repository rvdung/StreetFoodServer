/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.ArticleLanguageDTO;
import com.dungnv.streetfood.model.ArticleLanguage;
import com.dungnv.streetfood.dao.ArticleLanguageDAO;
import org.hibernate.Session;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/25/2016 10:07 PM
 */
@Service("articleLanguageBusiness")
@Transactional
public class ArticleLanguageBusiness extends BaseFWServiceImpl<ArticleLanguageDAO, ArticleLanguageDTO, ArticleLanguage> implements ArticleLanguageBusinessInterface{
	
    @Autowired
    private ArticleLanguageDAO articleLanguageDAO;

    public ArticleLanguageBusiness() {
        tModel = new ArticleLanguage();
        tDAO = articleLanguageDAO;
    }
    @Override
    public ArticleLanguageDAO gettDAO() {
        return articleLanguageDAO;
    }
    
    public ArticleLanguageBusiness(Session session) {
        this.session = session;
        tModel = new ArticleLanguage();
        tDAO = articleLanguageDAO;
    }
}


