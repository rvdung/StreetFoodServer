/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.UserDTO;
import com.dungnv.streetfood.model.User;
import com.dungnv.streetfood.dao.UserDAO;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.utils.DataUtil;
import com.dungnv.vfw5.base.utils.ErrorUtils;
import com.dungnv.vfw5.base.utils.ParamUtils;
import org.hibernate.Session;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/31/2016 10:31 PM
 */
@Service("userBusiness")
@Transactional
public class UserBusiness extends BaseFWServiceImpl<UserDAO, UserDTO, User> implements UserBusinessInterface {

    @Autowired
    private UserDAO userDAO;

    public UserBusiness() {
        tModel = new User();
        tDAO = userDAO;
    }

    @Override
    public UserDAO gettDAO() {
        return userDAO;
    }

    public UserBusiness(Session session) {
        this.session = session;
        tModel = new User();
        tDAO = userDAO;
    }

    @Override
    public ResultDTO login(String userName, String localeCode, String countryCode, String token, String password) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);
        result.setMessage(ParamUtils.FAIL);
        Criteria cri = gettDAO().getSession().createCriteria(User.class);
        cri.add(Restrictions.eq(User.USERNAME, userName).ignoreCase());
        cri.add(Restrictions.eq(User.PASSWORD, password));
        try {
            User user = (User) cri.uniqueResult();
            if(user!=null && user.getIsActive()!=null && user.getIsActive().equals(1L)){
                 result.setMessage(ParamUtils.SUCCESS);
                 result.setId(user.getId().toString());
            }
        } catch (HibernateException e) {
            result.setMessage(ParamUtils.FAIL);
            result.setKey(ErrorUtils.printLog(e));
        }
        return result;
    }
}
