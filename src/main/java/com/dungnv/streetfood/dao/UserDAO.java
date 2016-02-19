
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.dao;
import com.dungnv.vfw5.base.dao.BaseFWDAOImpl;
import com.dungnv.streetfood.model.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
* @author dungnv
* @version 1.0
* @since 1/31/2016 10:31 PM
*/
@Repository("userDAO")
public class UserDAO extends BaseFWDAOImpl<User, Long> {

    public UserDAO() {
        this.model= new User();
    }

    public UserDAO(Session session) {
        this.session = session;
    }
}

