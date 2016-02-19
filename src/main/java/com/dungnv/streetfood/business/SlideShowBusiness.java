/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.SlideShowDTO;
import com.dungnv.streetfood.model.SlideShow;
import com.dungnv.streetfood.dao.SlideShowDAO;
import org.hibernate.Session;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/27/2016 11:48 PM
 */
@Service("slideShowBusiness")
@Transactional
public class SlideShowBusiness extends BaseFWServiceImpl<SlideShowDAO, SlideShowDTO, SlideShow> implements SlideShowBusinessInterface{
	
    @Autowired
    private SlideShowDAO slideShowDAO;

    public SlideShowBusiness() {
        tModel = new SlideShow();
        tDAO = slideShowDAO;
    }
    @Override
    public SlideShowDAO gettDAO() {
        return slideShowDAO;
    }
    
    public SlideShowBusiness(Session session) {
        this.session = session;
        tModel = new SlideShow();
        tDAO = slideShowDAO;
    }
}


