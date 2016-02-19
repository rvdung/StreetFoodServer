
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.dao;
import com.dungnv.vfw5.base.dao.BaseFWDAOImpl;
import com.dungnv.streetfood.model.Comment;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
* @author dungnv
* @version 1.0
* @since 1/25/2016 10:10 PM
*/
@Repository("commentDAO")
public class CommentDAO extends BaseFWDAOImpl<Comment, Long> {

    public CommentDAO() {
        this.model= new Comment();
    }

    public CommentDAO(Session session) {
        this.session = session;
    }
}

