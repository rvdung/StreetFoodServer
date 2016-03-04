/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.test.service.dish;

import com.dungnv.streetfood.dto.*;
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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author ODIN NGUYEN
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:spring-beans.xml", "classpath:hibernate-config.xml", "file:src/main/webapp/WEB-INF/beans-service.xml"})
public class TestInsertDish extends Assert {

    private List<String> listId;

    @Autowired
    ClientService clientService;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("-----> START TEST TestInsertDish  <-----");
    }

    @Test
    public void insertDish_Common() {
        System.out.println("\n\n----------------- TEST insertDish_Common  ---- START");
        for (int i = 0; i < 10000; i++) {
            DishDTO dishDTO = new DishDTO();
            dishDTO.setName("JUNIT NAME " + StringUtils.generateRandomString(10));
            dishDTO.setDishStatus("1");
            dishDTO.setShortDescription("JUNIT SHORT DES " + StringUtils.generateRandomString(200));
            dishDTO.setLongDescription("JUNIT LONG DES " + StringUtils.generateRandomString(200));

            List<DishLanguageDTO> listDishLanguage = new ArrayList<>();
            DishLanguageDTO language = new DishLanguageDTO();
            language.setName("JUNIT LANGUAGE NAME " + StringUtils.generateRandomString(50));
            language.setLanguageCode("1");
            language.setShortDescription("JUNIT LANGUAGE SHORT DES " + StringUtils.generateRandomString(200));
            language.setLongDescription("JUNIT LANGUAGE LONG DES " + StringUtils.generateRandomString(200));
            listDishLanguage.add(language);

            dishDTO.setListLanguage(listDishLanguage);

            List<String> tagList = Arrays.asList(new String[]{StringUtils.generateRandomString(10)//
                , StringUtils.generateRandomString(10)//
                , StringUtils.generateRandomString(10)//
                , StringUtils.generateRandomString(10)});
            dishDTO.setListTag(tagList);

            List<String> listImg = Arrays.asList(new String[]{"http://" + StringUtils.generateRandomString(10) + ".jpg"//
                , "http://" + StringUtils.generateRandomString(10) + ".jpg"//
                , "http://" + StringUtils.generateRandomString(10) + ".jpg"//
                , "http://" + StringUtils.generateRandomString(10) + ".jpg"});
            dishDTO.setListImgUrl(listImg);

            ResultDTO result = clientService.insertDish("dungnv50", "en", "US", "123434234", dishDTO);
            System.out.println("------- TEST INSERT insertDish_Common - RESULT:\n" + result.toString());

            System.out.println("----------------- TEST insertDish_Common  ---- END\n\n");
            assertEquals(result.getMessage(), ParamUtils.SUCCESS);
            assertNotNull(result.getId());
            listId.add(result.getId());
        }

    }

//    @Test
    public void insertDish_Test1() {
        System.out.println("\n\n----------------- TEST insertDish_Test1  ---- START");
        DishDTO dishDTO = new DishDTO();
        dishDTO.setDishStatus("1");
        dishDTO.setShortDescription("JUNIT SHORT DES " + StringUtils.generateRandomString(200));
        dishDTO.setLongDescription("JUNIT LONG DES " + StringUtils.generateRandomString(200));

        List<DishLanguageDTO> listDishLanguage = new ArrayList<>();
        DishLanguageDTO language = new DishLanguageDTO();
        language.setName("JUNIT LANGUAGE NAME " + StringUtils.generateRandomString(50));
        language.setLanguageCode("1");
        language.setShortDescription("JUNIT LANGUAGE SHORT DES " + StringUtils.generateRandomString(200));
        language.setLongDescription("JUNIT LANGUAGE LONG DES " + StringUtils.generateRandomString(200));
        listDishLanguage.add(language);

        dishDTO.setListLanguage(listDishLanguage);

        List<String> tagList = Arrays.asList(new String[]{StringUtils.generateRandomString(10)//
            , StringUtils.generateRandomString(10)//
            , StringUtils.generateRandomString(10)//
            , StringUtils.generateRandomString(10)});
        dishDTO.setListTag(tagList);

        List<String> listImg = Arrays.asList(new String[]{"http://" + StringUtils.generateRandomString(10) + ".jpg"//
            , "http://" + StringUtils.generateRandomString(10) + ".jpg"//
            , "http://" + StringUtils.generateRandomString(10) + ".jpg"//
            , "http://" + StringUtils.generateRandomString(10) + ".jpg"});
        dishDTO.setListImgUrl(listImg);

        ResultDTO result = clientService.insertDish("dungnv50", "en", "US", "123434234", dishDTO);
        System.out.println("------- TEST insertDish_Test1 - RESULT:\n" + result.toString());
        System.out.println("----------------- TEST insertDish_Test1  ---- END\n\n");
        assertEquals(result.getMessage(), ParamUtils.FAIL);

    }

    @Before
    public void init() {
        listId = new ArrayList<>();
    }

//    @After
    public void cleanup() {
        for (String id : listId) {
            clientService.deleteDish("dungnv50", "en", "US", "123434234", Long.valueOf(id));
        }
    }

}
