/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.business;

import com.dungnv.streetfood.dao.DishDAO;
import com.dungnv.streetfood.dto.DishDTO;
import com.dungnv.vfw5.base.dto.ResultDTO;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nvdung
 */
public class DishBusinessTest {

    public DishBusinessTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of gettDAO method, of class DishBusiness.
     */
    @Test
    public void testGettDAO() {
        System.out.println("gettDAO");
        DishBusiness instance = new DishBusiness();
        DishDAO expResult = null;
        DishDAO result = instance.gettDAO();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertDish method, of class DishBusiness.
     */
    @Test
    public void testInsertDish() {
        System.out.println("insertDish");
        String userName = "";
        String localeCode = "";
        String countryCode = "";
        String token = "";
        DishDTO dto = null;
        DishBusiness instance = new DishBusiness();
        ResultDTO expResult = null;
        ResultDTO result = instance.insertDish(userName, localeCode, countryCode, token, dto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateDish method, of class DishBusiness.
     */
    @Test
    public void testUpdateDish() {
        System.out.println("updateDish");
        String userName = "";
        String localeCode = "";
        String countryCode = "";
        String token = "";
        DishDTO dto = null;
        DishBusiness instance = new DishBusiness();
        ResultDTO expResult = null;
        ResultDTO result = instance.updateDish(userName, localeCode, countryCode, token, dto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteDish method, of class DishBusiness.
     */
    @Test
    public void testDeleteDish() {
        System.out.println("deleteDish");
        String userName = "";
        String localeCode = "";
        String countryCode = "";
        String token = "";
        String id = "";
        DishBusiness instance = new DishBusiness();
        ResultDTO expResult = null;
        ResultDTO result = instance.deleteDish(userName, localeCode, countryCode, token, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of activeDish method, of class DishBusiness.
     */
    @Test
    public void testActiveDish() {
        System.out.println("activeDish");
        String userName = "";
        String localeCode = "";
        String countryCode = "";
        String token = "";
        String id = "";
        Boolean active = null;
        DishBusiness instance = new DishBusiness();
        ResultDTO expResult = null;
        ResultDTO result = instance.activeDish(userName, localeCode, countryCode, token, id, active);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListDishDTOLess method, of class DishBusiness.
     */
    @Test
    public void testGetListDishDTOLess() {
        System.out.println("getListDishDTOLess");
        String userName = "";
        String localeCode = "";
        String countryCode = "";
        String token = "";
        DishDTO dto = null;
        int rowStart = 0;
        int maxRow = 0;
        boolean isCount = false;
        String sortType = "";
        String sortFieldList = "";
        DishBusiness instance = new DishBusiness();
        List<DishDTO> expResult = null;
        List<DishDTO> result = instance.getListDishDTOLess(userName, localeCode, countryCode, token, dto, rowStart, maxRow, isCount, sortType, sortFieldList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDishDetail method, of class DishBusiness.
     */
    @Test
    public void testGetDishDetail() {
        System.out.println("getDishDetail");
        String userName = "";
        String localeCode = "";
        String countryCode = "";
        String token = "";
        String id = "";
        DishBusiness instance = new DishBusiness();
        DishDTO expResult = null;
        DishDTO result = instance.getDishDetail(userName, localeCode, countryCode, token, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
