/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.service.test;

import com.dungnv.streetfood.dto.CategoryDTO;
import com.dungnv.streetfood.service.CategoryService;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.utils.ParamUtils;
import com.dungnv.vfw5.base.utils.Constants;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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
public class CategoryServiceTest extends Assert {

    @Autowired
    CategoryService categoryService;

   
    public void testCategoryService() {
        assertEquals("com.dungnv.streetfood.service.CategoryServiceImpl", this.categoryService.getClass().getName());
    }

    @Test
    public void insertCategory() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("Junit test 1");
        categoryDTO.setCategoryStatus("1");
        categoryDTO.setDescription("Junit test 2");
        ResultDTO result = categoryService.insertCategory(categoryDTO);
        assertEquals(result.getMessage(), ParamUtils.SUCCESS);
    }

    @Before
    public void init() {
    }

    @After
    public void cleanup() {
    }

}
