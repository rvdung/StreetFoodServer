/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.test.service;

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

//    @Test
    public void insertCategory() {
        System.out.println("----------------- TEST INSERT CATEGORY");
        for (int i = 0; i < 10; i++) {
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

            List<String> listImg = Arrays.asList(new String[]{"http://" + StringUtils.generateRandomString(10) + ".jpg"//
                , "http://" + StringUtils.generateRandomString(10) + ".jpg"//
                , "http://" + StringUtils.generateRandomString(10) + ".jpg"//
                , "http://" + StringUtils.generateRandomString(10) + ".jpg"});
            categoryDTO.setListImgUrl(listImg);

            ResultDTO result = clientService.insertCategory("dungnv50", "en", "US", "123434234", categoryDTO);
            System.out.println("----------------- TEST INSERT CATEGORY - RESULT:\n" + result.toString());
            assertEquals(result.getMessage(), ParamUtils.SUCCESS);
        }

    }

//    @Test
    public void insertDish() {
        System.out.println("----------------- TEST INSERT DISH");
        for (int i = 0; i < 10; i++) {
            DishDTO dishDTO = new DishDTO();
            dishDTO.setName("JUNIT NAME " + StringUtils.generateRandomString(50));
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
            System.out.println("----------------- TEST INSERT DISH - RESULT:\n" + result.toString());
            assertEquals(result.getMessage(), ParamUtils.SUCCESS);
        }
    }

//    @Test
    public void insertDishesToCategory() {
        System.out.println("----------------- TEST INSERT DISHES TO CATEGORY");

        List<String> listDist = new ArrayList<>();

        listDist.add("1");
        listDist.add("1");
        listDist.add("2");
        listDist.add("2");
        listDist.add("3");
        listDist.add("3");
        listDist.add("3");
        listDist.add("4");
        ResultDTO result = clientService.insertListDishToCategory("dungnv50", "en", "US", "123434234", "11", listDist);
        System.out.println("----------------- TEST INSERT DISH - RESULT:\n" + result.toString());
        assertEquals(result.getMessage(), ParamUtils.SUCCESS);

    }

//    @Test
    public void insertCategoriesToDish() {
        System.out.println("----------------- TEST INSERT CATEGORIES TO DISH");

        List<String> listDist = new ArrayList<>();
        listDist.add("1");
        listDist.add("1");
        listDist.add("2");
        listDist.add("2");
        listDist.add("3");
        listDist.add("3");
        listDist.add("3");
        listDist.add("4");
        ResultDTO result = clientService.insertListCategoryToDish("dungnv50", "en", "US", "123434234", "22", listDist);
        System.out.println("----------------- TEST INSERT DISH - RESULT:\n" + result.toString());
        assertEquals(result.getMessage(), ParamUtils.SUCCESS);
    }

    
    @Test
    public void insertArticle() {
        System.out.println("----------------- TEST INSERT Article");
        for (int i = 0; i < 10; i++) {
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setTitle("JUNIT NAME " + StringUtils.generateRandomString(50));
            articleDTO.setContent("JUNIT CONTENT " + StringUtils.generateRandomString(200));
            articleDTO.setShortContent("JUNIT SHORT CONTENT " + StringUtils.generateRandomString(200));

            List<ArticleLanguageDTO> listDishLanguage = new ArrayList<>();
            ArticleLanguageDTO language = new ArticleLanguageDTO();
            language.setLanguageCode("1");
            language.setTitle("JUNIT LANGUAGE NAME " + StringUtils.generateRandomString(50));
            language.setContent("JUNIT LANGUAGE CONTENT " + StringUtils.generateRandomString(200));
            language.setShortContent("JUNIT LANGUAGE SHORT CONTENT " + StringUtils.generateRandomString(200));
            listDishLanguage.add(language);

            articleDTO.setListLanguage(listDishLanguage);

            List<String> tagList = Arrays.asList(new String[]{StringUtils.generateRandomString(10)//
                , StringUtils.generateRandomString(10)//
                , StringUtils.generateRandomString(10)//
                , StringUtils.generateRandomString(10)});
            articleDTO.setListTag(tagList);

            List<String> listImg = Arrays.asList(new String[]{"http://" + StringUtils.generateRandomString(10) + ".jpg"//
                , "http://" + StringUtils.generateRandomString(10) + ".jpg"//
                , "http://" + StringUtils.generateRandomString(10) + ".jpg"//
                , "http://" + StringUtils.generateRandomString(10) + ".jpg"});
            articleDTO.setListImgUrl(listImg);

            ResultDTO result = clientService.insertArticle("dungnv50", "en", "US", "123434234", articleDTO);
            System.out.println("----------------- TEST INSERT Article - RESULT:\n" + result.toString());
            assertEquals(result.getMessage(), ParamUtils.SUCCESS);
        }
    }
    
    
    @Test
    public void insertListDishToArticle() {
        System.out.println("----------------- TEST INSERT DISHES TO ARTICLE");

        List<String> listDist = new ArrayList<>();

        listDist.add("1");
        listDist.add("1");
        listDist.add("2");
        listDist.add("2");
        listDist.add("3");
        listDist.add("3");
        listDist.add("3");
        listDist.add("4");
        ResultDTO result = clientService.insertListDishToArticle("dungnv50", "en", "US", "123434234", "2", listDist);
        System.out.println("----------------- TEST INSERT DISHES TO ARTICLE - RESULT:\n" + result.toString());
        assertEquals(result.getMessage(), ParamUtils.SUCCESS);
    }

    @Test
    public void insertListArticleToDish() {
        System.out.println("----------------- TEST INSERT ARTICLES TO DISH");

        List<String> listDist = new ArrayList<>();
        listDist.add("1");
        listDist.add("1");
        listDist.add("2");
        listDist.add("2");
        listDist.add("3");
        listDist.add("3");
        listDist.add("3");
        listDist.add("4");
        ResultDTO result = clientService.insertListArticleToDish("dungnv50", "en", "US", "123434234", "1", listDist);
        System.out.println("----------------- TEST INSERT ARTICLES TO DISH - RESULT:\n" + result.toString());
        assertEquals(result.getMessage(), ParamUtils.SUCCESS);
    }


    @Before
    public void init() {
    }

    @After
    public void cleanup() {
    }

}
