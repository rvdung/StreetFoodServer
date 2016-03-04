/*
 * Copyright (C) 2012 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.vfw5.base.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import org.apache.commons.lang.StringEscapeUtils;

/**
 * @author binhnt22@viettel.com.vn
 * @since May 2012
 * @version 1.1
 */
public final class StringUtils {

    /**
     * alphabeUpCaseNumber.
     */
    private static String alphabeUpCaseNumber = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static String mask = "0123456789_aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
    private static String maskEN = "0123456789_aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
    private static String RANDOM_STRING = "0123456789 _ aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";

    /**
     * ZERO.
     */
    private static final String ZERO = "0";
    private static final String c[] = {"<", ">"};
    private static final String expansion[] = {"&lt;", "&gt;"};

    private StringUtils() {
    }

    public static String HTMLEncode(String s) {
        String sVal = s;
        for (int j = 0; j < c.length; j++) {
            sVal = sVal.replace(c[j], expansion[j]);
        }
        return sVal;
    }

    public static String HTMLDecode(String s) {
        String mine = s;
        for (int i = 0; i < c.length; i++) {
            mine.replaceAll(expansion[i], (c[i] + ""));
        }
        return mine;
    }

    /**
     * method compare two string
     *
     * @param str1 String
     * @param str2 String
     * @return boolean
     */
    public static boolean compareString(String str1, String str2) {
        String str1Val = str1;
        String str1Va2 = str2;
        if (str1 == null) {
            str1Val = "";
        }
        if (str2 == null) {
            str1Va2 = "";
        }

        if (str1Val.equals(str1Va2)) {
            return true;
        }
        return false;
    }

    /**
     * method convert long to string
     *
     * @param lng Long
     * @return String
     * @throws abc Exception
     */
    public static String convertFromLongToString(Long lng) throws Exception {
        return Long.toString(lng);
    }

    /*
     * @todo: convert from Long array to String array
     */
    public static String[] convertFromLongToString(Long[] arrLong) throws Exception {
        String[] arrResult = new String[arrLong.length];
        for (int i = 0; i < arrLong.length; i++) {
            arrResult[i] = convertFromLongToString(arrLong[i]);
        }
        return arrResult;
    }

    /*
     * @todo: convert from String array to Long array
     */
    public static long[] convertFromStringToLong(String[] arrStr) throws Exception {
        long[] arrResult = new long[arrStr.length];
        for (int i = 0; i < arrStr.length; i++) {
            arrResult[i] = Long.parseLong(arrStr[i]);
        }
        return arrResult;
    }

    /*
     * @todo: convert from String value to Long value
     */
    public static long convertFromStringToLong(String value) throws Exception {
        return Long.parseLong(value);
    }


    /*
     * Check String that containt only AlphabeUpCase and Number Return True if
     * String was valid, false if String was not valid
     */
    public static boolean checkAlphabeUpCaseNumber(String value) {
        boolean result = true;
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (alphabeUpCaseNumber.indexOf(temp) == -1) {
                result = false;
                return result;
            }
        }
        return result;
    }

    public static boolean validString(Object temp) {
        if (temp == null || temp.toString().trim().equals("")) {
            return false;
        }
        return true;
    }

    public static boolean maskVN(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (mask.indexOf(str.charAt(i)) < 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean maskEN(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (maskEN.indexOf(str.charAt(i)) < 0) {
                return false;
            }
        }
        if (str.toLowerCase(Locale.ENGLISH).charAt(0) < 'a' || str.toLowerCase(Locale.ENGLISH).charAt(0) > 'z') {
            return false;
        }
        return true;
    }

    public static boolean maskCode(String str) {
        String maskCode = "([A-Za-z0-9]|[A-Za-z0-9]+[A-Za-z0-9_]+[A-Za-z0-9]+)";
        return str.matches(maskCode);
    }

    public static boolean isInteger(String str) {
        if (str == null || !str.matches("[0-9]+$")) {
            return false;
        }
        return true;
    }

    public static boolean isLongtitude(String str) {
        if (str == null || !str.matches("^(\\+|-)?(?:180(?:(?:\\.0{1,10})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,10})?))$")) {
            return false;
        }
        return true;
    }

    public static boolean isLatitude(String str) {
        if (str == null || !str.matches("^(\\+|-)?(?:90(?:(?:\\.0{1,10})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,10})?))$")) {
            return false;
        }
        return true;
    }

    public static String formatString(String str) {
        return " '" + str.trim().toLowerCase() + "'";
    }

    public static String formatLike(String str) {
//        return "%" + str.trim().toLowerCase().replaceAll("_", "\\\\_") + "%";
        return "%" + str.trim().toLowerCase() + "%";
    }

    public static String formatOrder(String str, String direction) {
//        return " NLSSORT(" + str + ",'NLS_SORT=vietnamese') " + direction;
        return str + "  " + direction;
    }

    public static String formatDate(Date date) {
//        return " to_date('" + DateTimeUtils.convertDateToString(date, ParamUtils.ddMMyyyyHHmmss) + "', '" + ParamUtils.ddMMyyyy + "')";
        return DateTimeUtils.convertDateToString(date, ParamUtils.ddMMyyyyHHmmss);
    }

    public static String formatFunction(String function, String str) {
        return " " + function + "(" + str + ") ";
    }

    public static String formatConstant(String str) {
        String str1 = "";
        int index = 0;
        String alphabe = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 1; i < str.length(); i++) {
            if (alphabe.indexOf(str.charAt(i)) > 0) {
                str1 = str1 + str.substring(index, i).toUpperCase() + "_";
                index = i;
            }
        }
        str1 = str1 + str.substring(index, str.length()).toUpperCase() + "_";
        return str1;
    }

    public static void main(String[] args) {
        System.out.println(maskCode("123 123"));

    }

    public static boolean isLong(String str) {
        try {
            Long.valueOf(str);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean containSpecialCharacteristic(String str) {
        if (str == null) {
            return false;
        }
        List lstSpecialCharacteristic = new ArrayList<String>();
        lstSpecialCharacteristic.add("!");
        lstSpecialCharacteristic.add("@");
        lstSpecialCharacteristic.add("#");
        lstSpecialCharacteristic.add("%");
        lstSpecialCharacteristic.add("^");
        lstSpecialCharacteristic.add("&");
        lstSpecialCharacteristic.add("*");
        lstSpecialCharacteristic.add("(");
        lstSpecialCharacteristic.add(")");
        lstSpecialCharacteristic.add(" ");
        for (int i = 0; i < lstSpecialCharacteristic.size(); i++) {
            if (str.contains(lstSpecialCharacteristic.get(i).toString())) {
                return true;
            }
        }
        return false;
    }

    public static String upperFirstChar(String input) {
        if (isNullOrEmpty(input)) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public static boolean isNullOrEmpty(String obj1) {
        return (obj1 == null || "".equals(obj1.trim()));
    }

    public static boolean isLongNullOrEmpty(Long obj1) {
        return (obj1 == null || "0L".equals(obj1));
    }

    public static boolean isDoubleNullOrEmpty(Double obj1) {
        return (obj1 == null || "0D".equals(obj1));
    }

    //
    public static boolean isStringNullOrEmpty(Object obj1) {
        return obj1 == null || obj1.toString().trim().equals("");
    }

    //Convert operator type
    public static String convertTypeOperator(String operator) {
        String opConvert = "";
        if (StringUtils.isNullOrEmpty(operator)) {
            return opConvert;
        }
        switch (operator) {
            case ParamUtils.NAME_EQUAL:
                opConvert = ParamUtils.OP_EQUAL;
                break;
            case ParamUtils.NAME_GREATER:
                opConvert = ParamUtils.OP_GREATER;
                break;
            case ParamUtils.NAME_GREATER_EQUAL:
                opConvert = ParamUtils.OP_GREATER_EQUAL;
                break;
            case ParamUtils.NAME_LESS:
                opConvert = ParamUtils.OP_LESS;
                break;
            case ParamUtils.NAME_LESS_EQUAL:
                opConvert = ParamUtils.OP_LESS_EQUAL;
                break;
            case ParamUtils.NAME_NOT_EQUAL:
                opConvert = ParamUtils.OP_NOT_EQUAL;
                break;
            case ParamUtils.NAME_LIKE:
                opConvert = ParamUtils.OP_LIKE;
                break;
            case ParamUtils.NAME_IN:
                opConvert = ParamUtils.OP_IN;
                break;
            default:
                opConvert = "";
                break;
        }
        return opConvert;
    }

    public static void escapeHTMLString(Object escapeObject) {
        String oldData = "";
        String newData = "";
        try {
            if (escapeObject != null) {
                Class escapeClass = escapeObject.getClass();
                Field fields[] = escapeClass.getDeclaredFields();
                Field superFields[] = escapeClass.getSuperclass().getDeclaredFields();
                Field allField[] = new Field[fields.length + superFields.length];
                System.arraycopy(fields, 0, allField, 0, fields.length);
                System.arraycopy(superFields, 0, allField, fields.length, superFields.length);
                for (Field f : allField) {
                    f.setAccessible(true);
                    if (f.getType().equals(java.lang.String.class) && !Modifier.isFinal(f.getModifiers())) {
                        if (f.get(escapeObject) != null) {
                            oldData = f.get(escapeObject).toString();
                            newData = StringEscapeUtils.escapeSql(oldData);
                            f.set(escapeObject, newData);
                        }
                    } else if (f.getType().isArray()) {
                        if (f.getType().getComponentType().equals(java.lang.String.class)) {
                            String[] tmpArr = (String[]) f.get(escapeObject);
                            if (tmpArr != null) {
                                for (int i = 0; i < tmpArr.length; i++) {
                                    tmpArr[i] = StringEscapeUtils.escapeSql(tmpArr[i]);
                                }
                                f.set(escapeObject, tmpArr);
                            }
                        }
                    } else if (f.get(escapeObject) instanceof List) {
                        List<Object> tmpList = (List<Object>) f.get(escapeObject);
                        for (int i = 0; i < tmpList.size(); i++) {
                            if (tmpList.get(i) instanceof java.lang.String) {
                                tmpList.set(i, StringEscapeUtils.escapeSql(tmpList.get(i).toString()));
                            }
                        }
                        f.set(escapeObject, tmpList);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void escapeHTMLString(List escapeObjectList) {
        Object obj = null;
        for (int i = 0; i < escapeObjectList.size(); i++) {
            obj = escapeObjectList.get(i);
            escapeHTMLString(obj);
            escapeObjectList.set(i, obj);
        }
    }

    public static String trimString(String str, boolean isLower) {
        return isLower ? str.trim().toLowerCase() : str.trim();
    }

    public static void trimString(Object obj, boolean isLower) {
        String oldData = "";
        String newData = "";
        try {
            if (obj != null) {
                Class escapeClass = obj.getClass();
                Field fields[] = escapeClass.getDeclaredFields();
                Field superFields[] = escapeClass.getSuperclass().getDeclaredFields();
                Field allField[] = new Field[fields.length + superFields.length];
                System.arraycopy(fields, 0, allField, 0, fields.length);
                System.arraycopy(superFields, 0, allField, fields.length, superFields.length);
                for (Field f : allField) {
                    f.setAccessible(true);
                    if (f.getType().equals(java.lang.String.class) && !Modifier.isFinal(f.getModifiers())) {
                        if (f.get(obj) != null) {
                            oldData = f.get(obj).toString();
                            newData = isLower ? oldData.trim().toLowerCase() : oldData.trim();
                            f.set(obj, newData);
                        }
                    } else if (f.getType().isArray()) {
                        if (f.getType().getComponentType().equals(java.lang.String.class)) {
                            String[] tmpArr = (String[]) f.get(obj);
                            if (tmpArr != null) {
                                for (int i = 0; i < tmpArr.length; i++) {
                                    tmpArr[i] = isLower ? tmpArr[i].trim().toLowerCase() : tmpArr[i].trim();
                                }
                                f.set(obj, tmpArr);
                            }
                        }
                    } else if (f.get(obj) instanceof List) {
                        List<Object> tmpList = (List<Object>) f.get(obj);
                        for (int i = 0; i < tmpList.size(); i++) {
                            if (tmpList.get(i) instanceof java.lang.String) {
                                tmpList.set(i, isLower ? tmpList.get(i).toString().trim().toLowerCase() : tmpList.get(i).toString().trim());
                            }
                        }
                        f.set(obj, tmpList);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void trimString(List escapeObjectList, boolean isLower) {
        Object obj = null;
        for (int i = 0; i < escapeObjectList.size(); i++) {
            obj = escapeObjectList.get(i);
            trimString(obj, isLower);
            escapeObjectList.set(i, obj);
        }
    }

    public static List<String> trimString(List<String> list, Boolean isLower) {

        String str = null;
        for (int i = 0; i < list.size(); i++) {
            str = list.get(i);
            formatString(str);
            list.set(i, str);
        }
        return list;
    }

    public static List<String> trimStringToNewList(List<String> list, Boolean isLower) {
        List<String> temp = new ArrayList<>();
        String str = null;
        for (int i = 0; i < list.size(); i++) {
            temp.add(isLower ? list.get(i).trim().toLowerCase() : list.get(i).trim());
        }
        return temp;
    }

    public static String generateRandomString(int length) {
        Random rngRandom = new Random();
        char[] text = new char[length];
        int lengRnd = RANDOM_STRING.length();
        for (int i = 0; i < length; i++) {
            text[i] = RANDOM_STRING.charAt(rngRandom.nextInt(lengRnd));
        }
        return new String(text);
    }
//
//    public static String subString(String input, int start, int count) {
//        if (start < 0) {
//            start = 0;
//        }
//        if (start > input.length() - 1) {
//            start = input.length() - 1;
//        }
//        if(count < 0 || count < start)
//    }
}
