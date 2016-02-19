
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.dao;
import com.dungnv.vfw5.base.dao.BaseFWDAOImpl;
import com.dungnv.streetfood.model.LocaleModel;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
* @author dungnv
* @version 1.0
* @since 1/22/2016 9:45 PM
*/
@Repository("localeDAO")
public class LocaleDAO extends BaseFWDAOImpl<LocaleModel, Long> {

    public LocaleDAO() {
        this.model= new LocaleModel();
    }

    public LocaleDAO(Session session) {
        this.session = session;
    }
}

