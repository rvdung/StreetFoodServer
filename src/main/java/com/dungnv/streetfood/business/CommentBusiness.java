/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.CommentDTO;
import com.dungnv.streetfood.model.Comment;
import com.dungnv.streetfood.dao.CommentDAO;
import org.hibernate.Session;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/25/2016 10:10 PM
 */
@Service("commentBusiness")
@Transactional
public class CommentBusiness extends BaseFWServiceImpl<CommentDAO, CommentDTO, Comment> implements CommentBusinessInterface{
	
    @Autowired
    private CommentDAO commentDAO;

    public CommentBusiness() {
        tModel = new Comment();
        tDAO = commentDAO;
    }
    @Override
    public CommentDAO gettDAO() {
        return commentDAO;
    }
    
    public CommentBusiness(Session session) {
        this.session = session;
        tModel = new Comment();
        tDAO = commentDAO;
    }
}


