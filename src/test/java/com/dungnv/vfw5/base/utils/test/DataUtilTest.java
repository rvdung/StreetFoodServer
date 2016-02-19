/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.vfw5.base.utils.test;

import com.dungnv.vfw5.base.utils.DataUtil;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author ODIN NGUYEN
 */
public class DataUtilTest extends Assert {

    @Test
    public void formatInputList() {
        List<String> list = null;
        assertNull(DataUtil.formatInputList(list));
        list = new ArrayList<>();
        assertEquals(DataUtil.formatInputList(list), "");
        list.add("111");
        assertEquals(DataUtil.formatInputList(list), "111");
        list.add("222");
        assertEquals(DataUtil.formatInputList(list), "111,222");
        list.add("333");
        assertEquals(DataUtil.formatInputList(list), "111,222,333");
    }
}
