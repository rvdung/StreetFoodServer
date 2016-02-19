
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.dao;
import com.dungnv.vfw5.base.dao.BaseFWDAOImpl;
import com.dungnv.streetfood.model.SlideShow;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
* @author dungnv
* @version 1.0
* @since 1/27/2016 11:48 PM
*/
@Repository("slideShowDAO")
public class SlideShowDAO extends BaseFWDAOImpl<SlideShow, Long> {

    public SlideShowDAO() {
        this.model= new SlideShow();
    }

    public SlideShowDAO(Session session) {
        this.session = session;
    }
}

