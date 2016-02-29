
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.dao;
import com.dungnv.vfw5.base.dao.BaseFWDAOImpl;
import com.dungnv.streetfood.model.SlideShowLanguage;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
* @author dungnv
* @version 1.0
* @since 2/21/2016 1:04 PM
*/
@Repository("slideShowLanguageDAO")
public class SlideShowLanguageDAO extends BaseFWDAOImpl<SlideShowLanguage, Long> {

    public SlideShowLanguageDAO() {
        this.model= new SlideShowLanguage();
    }

    public SlideShowLanguageDAO(Session session) {
        this.session = session;
    }
}

