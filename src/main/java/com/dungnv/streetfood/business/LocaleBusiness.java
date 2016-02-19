/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.LocaleDTO;
import com.dungnv.streetfood.model.LocaleModel;
import com.dungnv.streetfood.dao.LocaleDAO;
import org.hibernate.Session;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/22/2016 9:45 PM
 */
@Service("localeBusiness")
@Transactional
public class LocaleBusiness extends BaseFWServiceImpl<LocaleDAO, LocaleDTO, LocaleModel> implements LocaleBusinessInterface{
	
    @Autowired
    private LocaleDAO localeDAO;

    public LocaleBusiness() {
        tModel = new LocaleModel();
        tDAO = localeDAO;
    }
    @Override
    public LocaleDAO gettDAO() {
        return localeDAO;
    }
    
    public LocaleBusiness(Session session) {
        this.session = session;
        tModel = new LocaleModel();
        tDAO = localeDAO;
    }
}


