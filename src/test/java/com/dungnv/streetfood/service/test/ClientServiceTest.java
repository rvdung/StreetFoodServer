/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.service.test;

import com.dungnv.streetfood.dto.CategoryDTO;
import com.dungnv.streetfood.dto.DishGroupLangageDTO;
import com.dungnv.streetfood.service.ClientService;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.utils.ParamUtils;
import com.dungnv.vfw5.base.utils.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ODIN NGUYEN
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {
//    "classpath:spring-beans.xml", "classpath:hibernate-config.xml", "file:src/main/webapp/WEB-INF/beans-service.xml"})
public class ClientServiceTest extends Assert {

    @Autowired
    ClientService clientService;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("-----> START TEST ClientService  <-----");
    }

    @Test
    public void testCategoryService() {
//        com.mchange.v2.c3p0.ComboPooledDataSource source = new com.mchange.v2.c3p0.ComboPooledDataSource();
//        source.set
        assertEquals("com.dungnv.streetfood.service.ClientServiceImpl", this.clientService.getClass().getName());

    }

    @Test
    public void insertCategory() {
        System.out.println("----------------- TEST INSERT CATEGORY");
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("JUNIT NAME " + StringUtils.generateRandomString(50));
        categoryDTO.setCategoryStatus("1");
        categoryDTO.setDescription("JUNIT DES" + StringUtils.generateRandomString(200));

        List<DishGroupLangageDTO> listDishGroupLangage = new ArrayList<>();
        DishGroupLangageDTO language = new DishGroupLangageDTO();
        language.setName("JUNIT LANGUAGE NAME " + StringUtils.generateRandomString(50));
        language.setLanguageCode("1");
        language.setDescription("JUNIT LANGUAGE DES" + StringUtils.generateRandomString(200));
        listDishGroupLangage.add(language);

        categoryDTO.setListLanguage(listDishGroupLangage);
        List<String> tagList = Arrays.asList(new String[]{StringUtils.generateRandomString(10)//
                , StringUtils.generateRandomString(10)//
                , StringUtils.generateRandomString(10)//
                , StringUtils.generateRandomString(10)});
        categoryDTO.setListTag(tagList);

        ResultDTO result = clientService.insertCategory("dungnv50", "en", "US", "123434234", categoryDTO);
        System.out.println("----------------- TEST INSERT CATEGORY - RESULT:\n" + result.toString());
        assertEquals(result.getMessage(), ParamUtils.SUCCESS);

    }

    @Before
    public void init() {
    }

    @After
    public void cleanup() {
    }

}
