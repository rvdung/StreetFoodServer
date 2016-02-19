
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.dao;
import com.dungnv.vfw5.base.dao.BaseFWDAOImpl;
import com.dungnv.streetfood.model.Tags;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
* @author dungnv
* @version 1.0
* @since 1/24/2016 8:00 PM
*/
@Repository("tagsDAO")
public class TagsDAO extends BaseFWDAOImpl<Tags, Long> {

    public TagsDAO() {
        this.model= new Tags();
    }

    public TagsDAO(Session session) {
        this.session = session;
    }
}

