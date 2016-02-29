/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.vfw5.base.utils;

/**
 *
 * @author nvdung
 */
public class NumberUtils {

    public static Boolean isDouble(String value) {
        try {
            return Double.valueOf(value) != null;
        } catch (Exception e) {
            return false;
        }
    }
    
}
