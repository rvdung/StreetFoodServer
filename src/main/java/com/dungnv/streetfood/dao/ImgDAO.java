
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.dao;
import com.dungnv.vfw5.base.dao.BaseFWDAOImpl;
import com.dungnv.streetfood.model.Img;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
* @author dungnv
* @version 1.0
* @since 1/25/2016 10:05 PM
*/
@Repository("imgDAO")
public class ImgDAO extends BaseFWDAOImpl<Img, Long> {

    public ImgDAO() {
        this.model= new Img();
    }

    public ImgDAO(Session session) {
        this.session = session;
    }
}

