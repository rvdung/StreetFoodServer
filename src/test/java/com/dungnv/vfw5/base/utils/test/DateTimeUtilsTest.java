/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.vfw5.base.utils.test;

import com.dungnv.vfw5.base.utils.DateTimeUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author ODIN NGUYEN
 */
public class DateTimeUtilsTest extends Assert  {
    @Test
    public void getSysdate() throws Exception{
        String nowTime = DateTimeUtils.getSysDateTime(Boolean.FALSE);
        String nowTimeGMT = DateTimeUtils.getSysDateTime(Boolean.TRUE);
        System.out.println("nowTime " + nowTime);
        System.out.println("nowTimeGMT " + nowTimeGMT);
        Assert.assertNotEquals("nowTime: " + nowTime + ", nowTimeGMT: " + nowTimeGMT, nowTime, nowTimeGMT);
//        org.springframework.orm.hibernate4.LocalSessionFactoryBean test = new org.springframework.orm.hibernate4.LocalSessionFactoryBean();
//        test.setHibernateProperties(hibernateProperties);
//        java.util.Properties 
    }
}
