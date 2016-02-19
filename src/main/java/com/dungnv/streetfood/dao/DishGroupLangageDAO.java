
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.dao;
import com.dungnv.vfw5.base.dao.BaseFWDAOImpl;
import com.dungnv.streetfood.model.DishGroupLangage;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
* @author dungnv
* @version 1.0
* @since 1/21/2016 9:12 PM
*/
@Repository("dishGroupLangageDAO")
public class DishGroupLangageDAO extends BaseFWDAOImpl<DishGroupLangage, Long> {

    public DishGroupLangageDAO() {
        this.model= new DishGroupLangage();
    }

    public DishGroupLangageDAO(Session session) {
        this.session = session;
    }
}

