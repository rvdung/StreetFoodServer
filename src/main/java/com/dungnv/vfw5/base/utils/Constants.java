/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.vfw5.base.utils;

/**
 *
 * @author BSS_KIENTT
 */
public class Constants {

    public static final String PROJECT_NAME = "VIETWEBFRAMEWORK5";
    public static final String DEFAULT = "DEFAULT";
    public static final String TRANSACTION_SEQUENCE = "transaction_seq";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String REPORT_OBJECT = "objReportObject";
    public static final String USER_TOKEN = "userToken";
    public static final String IS_EXPORT_EXCEL = "isExportExcel";
    public static final String IS_EXPORT_HTML = "isExportHTML";
    public static final String EXCEL_STREAM = "excelStream";
    public static final String SESSION_TIMEOUT = "sessionTimeout";
    public static final String RULE_OBJECT = "objRule";
    public static final String COPY_RULE_OBJECT = "objCopyRule";
    public static final String COPY_RULES_OF_EVENT_OBJECT = "objCopyRulesOfEvent";
    public static final String FIST_VALUE = "-1";
    public static final String UNKNOWN = "UNKNOWN";
    public static final String PLEASE_SELECT = "--Please select--";
    public final static String EVENT_GROUP = "ONEOFF";
    public final static String IS_SERIAL = "1"; //Quan ly hang hoa theo Serial
    public final static String IS_SERIAL_STRIP = "1"; //Quan ly hang hoa theo dai Serial    
    public final static String STATUS_SERIAL_IN_STOCK = "1"; //Trang thai serial trong kho
    public final static String STATUS_SERIAL_WAIT_STOCK = "2"; //Trang thai serial cho nhap kho
    public final static String STATUS_SERIAL_OUT_STOCK = "3"; //Trang thai serial da ban
    public final static int MAX_SERIAL_QUANTITY = 50000; //So luong toi da dai serial
    public final static int SERIAL_LIMIT = 19; //Nguong cat serial (Do dai Long chi duoc 19 chu so)
    public final static String STOCK_OWNER = "1"; //Quan ly hang hoa theo dai Serial
    public final static int MAX_RESULT = 999999999; //Nguong cat serial (Do dai Long chi duoc 19 chu so)
    public final static String NOT_ENOUGH_AMOUNT = "NotEnoughAmount";
    public final static String ORDER_ACTION_NEW = "1"; //Tao moi lenh
    public final static String ORDER_ACTION_PLAN = "2"; //Lenh da duoc lap ke hoach
    public final static String ORDER_ACTION_IMPORT_STOCK = "3"; //Lenh da duoc nhap kho
    public final static String ACTIVE_STATUS = "1"; //Lenh da duoc nhap kho
    public final static String CELL_UPDATED_STATUS = "1"; //Da cap nhat Cell

    public final class SEQ_CONSTANTS {

        public final static String ruleMapSeq = "RT_RULE_MAP_SEQ";
        public final static String ruleMapDetailSeq = "RT_RULE_MAP_DETAIL_SEQ";
        public final static String ruleSeq = "RT_RULE_SEQ";
        public final static String actionSeq = "RT_ACTION_SEQ";
        public final static String expressionSeq = "RT_EXPRESSION_SEQ";
        public final static String subExpressionSeq = "RT_SUB_EXPRESSION_SEQ";
    }

    public final class CDRTYPE_CONSTANTS {

        public final static String SYSTEM = "SYSTEM";
    }

    public final class DATA_TYPE {

        public final static String INTEGER = "Integer";
        public final static String LONG = "Long";
        public final static String DOUBLE = "Double";
        public final static String DATE = "Date";
        public final static String STRING = "String";
        public final static String BOOLEAN = "Boolean";
    }

    public final class ACTION_TYPE {

        public final static String SELECT = "SELECT";
        public final static String INSERT = "INSERT";
        public final static String UPDATE = "UPDATE";
        public final static String DELETE = "DELETE";
        public final static String COPY = "COPY";
    }

    public final class WEB_SERVICE_CONS {

        public final static String SEPERATE_URL = "/";
        public final static String SEPERATE_OPERATOR = ":";
        public final static String ARG_HEADER = "$headerArgument$";
        public final static String ARG_BODY = "$argument$";
    }

    public final class OBJECT_TYPE {

        public final static String CATEGORY = "CATEGORY";
        public final static String DISH = "DISH";
        public final static String RESTAURANT = "RESTAURANT";
        public final static String ARTICLE = "ARTICLE";
        public final static String SLIDESHOW = "SLIDESHOW";

    }

}
