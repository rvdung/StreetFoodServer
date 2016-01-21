/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.vfw5.base.utils;

/**
 *
 * @author kdvt_binhnt22@viettel.com.vn
 * @version 1.0
 * @since since_text
 */
public final class ParamUtils {

    public static final String TYPE_NUMBER = "LONG,INTEGER,SHORT,BYTE,INT,DOUBLE,FLOAT";
    public static final String TYPE_STRING = "STRING";
    public static final String NUMBER = "NUMBER";
    public static final String NUMBER_DOUBLE = "DOUBLE";

    public static final String TYPE_DATE = "DATE";
    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";
    public static final String FAIL = "FAIL";
    public static final String NOT_EXIST = "NOT_EXIST";
    public static final int GENDER_MALE = 1;
    public static final int GENDER_FEMALE = 0;
    public static final int ACTIVE = 1;
    public static final int INACTIVE = 0;
    public static final int DEFAULT_VALUE = -99;
    public static final String USER_TOKEN = "userToken";
    public static final String VSA_USER_TOKEN = "vsaUserToken";
    public static final String SESSION_TIMEOUT = "sessionTimeout";
    public static final String ERROR_PAGE = "error";
    public static final String BASE_ID = "baseId";
    public static final String PARAMETER_LOCALE = "request_locale=";
    public static final String REQUEST_HEADER_REFERER = "referer";
    public static final String LOCALE_SEPARATOR = "_";
    //------------------OPERATOR FOR GENERATE SQL
    public static final String OP_EQUAL = " = ";
    public static final String OP_NOT_EQUAL = " != ";
    public static final String OP_GREATER = " > ";
    public static final String OP_GREATER_EQUAL = " >= ";
    public static final String OP_LESS = " < ";
    public static final String OP_LESS_EQUAL = " <= ";
    public static final String OP_LIKE = " like ";
    public static final String OP_IN = " in ";
    public static final String LOGIC_OR = " or ";
    public static final String LOGIC_AND = " and ";
    public static final String ddMMyyyy = "dd/MM/yyyy";
    public static final String ddMMyyyyHHmmss = "dd/MM/yyyy HH:mm:ss";
    //  NAME OF OPERATOR
    public static final String NAME_EQUAL = "NAME_EQUAL";
    public static final String NAME_LESS_EQUAL = "NAME_LESS_EQUAL";
    public static final String NAME_GREATER_EQUAL = "NAME_GREATER_EQUAL";
    public static final String NAME_NOT_EQUAL = "NAME_NOT_EQUAL";
    public static final String NAME_LESS = "NAME_LESS";
    public static final String NAME_GREATER = "NAME_GREATER";
    public static final String NAME_LIKE = "NAME_LIKE";
    public static final String NAME_IN = "NAME_IN";
    public static final String NAME_OR = "NAME_OR";
    public static final String NAME_AND = "NAME_AND";
    //------------------DIV MESSAGE
    public static final String SEPARATOR = "|";
    public static final String SPLITTER = ",";
    public static final String RETURN_MESSAGE = "RETURN_MESSAGE";
    public static final String ACTION_MESSAGE = "ACTION_MESSAGE";
    public static final String REASON_MESSAGE = "REASON_MESSAGE";
    public static final String ACTION_FLAG = "ACTION_FLAG";
    // Constant
    public static final int MAX_PIC_SIZE = 50 * 1024;
    // -----------------SEARCH FORM ---------------------
    public static final String RECORD_ID = "id";
    public static final String SEARCH_STARTVAL = "startval";
    public static final String SEARCH_SORT = "sort";
    public static final String SORT_ASC = "asc";
    public static final String SORT_DESC = "desc";
    public static final String SEARCH_COUNT = "count";
    public static final String SEARCH_PARAM_DELIM = "-";
    public static final String SEARCH_NUMROW = "10";
    // -----------------RAPROCESS FORM ---------------------
    public static final String DIV_PROCESS_CODE = "VALIDATE_RA_PROCESS_PROCESS_CODE";
    public static final String DIV_PROCESS_CLASS = "VALIDATE_RA_PROCESS_PROCESS_CLASS";
    public static final String DIV_SLEEP_TIME = "VALIDATE_RA_PROCESS_SLEEP_TIME";
    public static final String DIV_PROCESS_TYPE = "VALIDATE_RA_PROCESS_PROCESS_TYPE";
    public static final String DIV_DESCRIPTION = "VALIDATE_RA_PROCESS_DESCRIPTION";
    public static final String DIV_STATUS = "VALIDATE_RA_PROCESS_STATUS";
    public static final String DIV_RA_PROCESS_RETURN_MESSAGE = "RA_PROCESS_FORM_RETURN_MESSAGE";
    public static final String DIV_RA_PROCESS_ACTION_MESSAGE = "RA_PROCESS_FORM_ACTION_MESSAGE";
    public static final String DIV_RA_PROCESS_REASON_MESSAGE = "RA_PROCESS_FORM_REASON_MESSAGE";
    public static final String DIV_RA_PROCESS_ACTION_FLAG = "RA_PROCESS_FORM_ACTION_FLAG";
    // ----------------------RaHivePartition FORM -------------------- 
    public static final String DIV_RA_HIVE_PARTITION_PARTITION_NAME = "VALIDATE_RA_HIVE_PARTITION_PARTITION_NAME";
    public static final String DIV_RA_HIVE_PARTITION_DESCRIPTION = "VALIDATE_RA_HIVE_PARTITION_DESCRIPTION";
    public static final String DIV_RA_HIVE_PARTITION_RETURN_MESSAGE = "RA_HIVE_PARTITION_FORM_RETURN_MESSAGE";
    public static final String DIV_RA_HIVE_PARTITION_ACTION_MESSAGE = "RA_HIVE_PARTITION_FORM_ACTION_MESSAGE";
    public static final String DIV_RA_HIVE_PARTITION_REASON_MESSAGE = "RA_HIVE_PARTITION_FORM_REASON_MESSAGE";
    public static final String DIV_RA_HIVE_PARTITION_ACTION_FLAG = "RA_HIVE_PARTITION_FORM_ACTION_FLAG";
    // ----------------------RaHivePartitionDetail FORM -------------------- 
    public static final String DIV_RA_HIVE_PARTITION_DETAIL_HIVE_PARTITION_ID = "VALIDATE_RA_HIVE_PARTITION_DETAIL_HIVE_PARTITION_ID";
    public static final String DIV_RA_HIVE_PARTITION_DETAIL_PARTITION_COLUMN = "VALIDATE_RA_HIVE_PARTITION_DETAIL_PARTITION_COLUMN";
    public static final String DIV_RA_HIVE_PARTITION_DETAIL_PARTITION_VALUE = "VALIDATE_RA_HIVE_PARTITION_DETAIL_PARTITION_VALUE";
    public static final String DIV_RA_HIVE_PARTITION_DETAIL_PARAM1_VALUE = "VALIDATE_RA_HIVE_PARTITION_DETAIL_PARAM1_VALUE";
    public static final String DIV_RA_HIVE_PARTITION_DETAIL_PARAM2_VALUE = "VALIDATE_RA_HIVE_PARTITION_DETAIL_PARAM2_VALUE";
    public static final String DIV_RA_HIVE_PARTITION_DETAIL_RETURN_MESSAGE = "RA_HIVE_PARTITION_DETAIL_FORM_RETURN_MESSAGE";
    public static final String DIV_RA_HIVE_PARTITION_DETAIL_ACTION_MESSAGE = "RA_HIVE_PARTITION_DETAIL_FORM_ACTION_MESSAGE";
    public static final String DIV_RA_HIVE_PARTITION_DETAIL_REASON_MESSAGE = "RA_HIVE_PARTITION_DETAIL_FORM_REASON_MESSAGE";
    public static final String DIV_RA_HIVE_PARTITION_DETAIL_ACTION_FLAG = "RA_HIVE_PARTITION_DETAIL_FORM_ACTION_FLAG";
    // ----------------------RaHiveLoadFunction FORM -------------------- 
    public static final String DIV_RA_HIVE_LOAD_FUNCTION_FUNCTION_NAME = "VALIDATE_RA_HIVE_LOAD_FUNCTION_FUNCTION_NAME";
    public static final String DIV_RA_HIVE_LOAD_FUNCTION_PARAM1_NAME = "VALIDATE_RA_HIVE_LOAD_FUNCTION_PARAM1_NAME";
    public static final String DIV_RA_HIVE_LOAD_FUNCTION_PARAM2_NAME = "VALIDATE_RA_HIVE_LOAD_FUNCTION_PARAM2_NAME";
    public static final String DIV_RA_HIVE_LOAD_FUNCTION_PARAM1_TYPE = "VALIDATE_RA_HIVE_LOAD_FUNCTION_PARAM1_TYPE";
    public static final String DIV_RA_HIVE_LOAD_FUNCTION_PARAM2_TYPE = "VALIDATE_RA_HIVE_LOAD_FUNCTION_PARAM2_TYPE";
    public static final String DIV_RA_HIVE_LOAD_FUNCTION_RETURN_TYPE = "VALIDATE_RA_HIVE_LOAD_FUNCTION_RETURN_TYPE";
    public static final String DIV_RA_HIVE_LOAD_FUNCTION_DESCRIPTION = "VALIDATE_RA_HIVE_LOAD_FUNCTION_DESCRIPTION";
    public static final String DIV_RA_HIVE_LOAD_FUNCTION_RETURN_MESSAGE = "RA_HIVE_LOAD_FUNCTION_FORM_RETURN_MESSAGE";
    public static final String DIV_RA_HIVE_LOAD_FUNCTION_ACTION_MESSAGE = "RA_HIVE_LOAD_FUNCTION_FORM_ACTION_MESSAGE";
    public static final String DIV_RA_HIVE_LOAD_FUNCTION_REASON_MESSAGE = "RA_HIVE_LOAD_FUNCTION_FORM_REASON_MESSAGE";
    public static final String DIV_RA_HIVE_LOAD_FUNCTION_ACTION_FLAG = "RA_HIVE_LOAD_FUNCTION_FORM_ACTION_FLAG";
    // ----------------------RaIndividual FORM -------------------- 
    public static final String DIV_RA_INDIVIDUAL_INDIVIDUAL_NAME = "VALIDATE_RA_INDIVIDUAL_INDIVIDUAL_NAME";
    public static final String DIV_RA_INDIVIDUAL_DESCRIPTION = "VALIDATE_RA_INDIVIDUAL_DESCRIPTION";
    public static final String DIV_RA_INDIVIDUAL_GENDER = "VALIDATE_RA_INDIVIDUAL_GENDER";
    public static final String DIV_RA_INDIVIDUAL_INDIVIDUAL_CODE = "VALIDATE_RA_INDIVIDUAL_INDIVIDUAL_CODE";
    public static final String DIV_RA_INDIVIDUAL_RETURN_MESSAGE = "RA_INDIVIDUAL_FORM_RETURN_MESSAGE";
    public static final String DIV_RA_INDIVIDUAL_ACTION_MESSAGE = "RA_INDIVIDUAL_FORM_ACTION_MESSAGE";
    public static final String DIV_RA_INDIVIDUAL_REASON_MESSAGE = "RA_INDIVIDUAL_FORM_REASON_MESSAGE";
    public static final String DIV_RA_INDIVIDUAL_ACTION_FLAG = "RA_INDIVIDUAL_FORM_ACTION_FLAG";
    // ----------------------RaStandardKey FORM -------------------- 
    public static final String DIV_RA_STANDARD_KEY_STANDARD_COLUMN = "VALIDATE_RA_STANDARD_KEY_STANDARD_COLUMN";
    public static final String DIV_RA_STANDARD_KEY_DESCRIPTION = "VALIDATE_RA_STANDARD_KEY_DESCRIPTION";
    public static final String DIV_RA_STANDARD_KEY_RETURN_MESSAGE = "RA_STANDARD_KEY_FORM_RETURN_MESSAGE";
    public static final String DIV_RA_STANDARD_KEY_ACTION_MESSAGE = "RA_STANDARD_KEY_FORM_ACTION_MESSAGE";
    public static final String DIV_RA_STANDARD_KEY_REASON_MESSAGE = "RA_STANDARD_KEY_FORM_REASON_MESSAGE";
    public static final String DIV_RA_STANDARD_KEY_ACTION_FLAG = "RA_STANDARD_KEY_FORM_ACTION_FLAG";
    // ----------------------RaCompareKey FORM -------------------- 
    public static final String DIV_RA_COMPARE_KEY_COMPARE_KEY_NAME = "VALIDATE_RA_COMPARE_KEY_COMPARE_KEY_NAME";
    public static final String DIV_RA_COMPARE_KEY_DESCRIPTION = "VALIDATE_RA_COMPARE_KEY_DESCRIPTION";
    public static final String DIV_RA_COMPARE_KEY_RETURN_MESSAGE = "RA_COMPARE_KEY_FORM_RETURN_MESSAGE";
    public static final String DIV_RA_COMPARE_KEY_ACTION_MESSAGE = "RA_COMPARE_KEY_FORM_ACTION_MESSAGE";
    public static final String DIV_RA_COMPARE_KEY_REASON_MESSAGE = "RA_COMPARE_KEY_FORM_REASON_MESSAGE";
    public static final String DIV_RA_COMPARE_KEY_ACTION_FLAG = "RA_COMPARE_KEY_FORM_ACTION_FLAG";
    // ----------------------RaCompareKeyDetail FORM -------------------- 
    public static final String DIV_RA_COMPARE_KEY_DETAIL_STANDARD_KEY_ID = "VALIDATE_RA_COMPARE_KEY_DETAIL_STANDARD_KEY_ID";
    public static final String DIV_RA_COMPARE_KEY_DETAIL_COMPARE_KEY_ID = "VALIDATE_RA_COMPARE_KEY_DETAIL_COMPARE_KEY_ID";
    public static final String DIV_RA_COMPARE_KEY_DETAIL_KEY_ORDER = "VALIDATE_RA_COMPARE_KEY_DETAIL_KEY_ORDER";
    public static final String DIV_RA_COMPARE_KEY_DETAIL_DESCRIPTION = "VALIDATE_RA_COMPARE_KEY_DETAIL_DESCRIPTION";
    public static final String DIV_RA_COMPARE_KEY_DETAIL_RETURN_MESSAGE = "RA_COMPARE_KEY_DETAIL_FORM_RETURN_MESSAGE";
    public static final String DIV_RA_COMPARE_KEY_DETAIL_ACTION_MESSAGE = "RA_COMPARE_KEY_DETAIL_FORM_ACTION_MESSAGE";
    public static final String DIV_RA_COMPARE_KEY_DETAIL_REASON_MESSAGE = "RA_COMPARE_KEY_DETAIL_FORM_REASON_MESSAGE";
    public static final String DIV_RA_COMPARE_KEY_DETAIL_ACTION_FLAG = "RA_COMPARE_KEY_DETAIL_FORM_ACTION_FLAG";
    // ----------------------RaStandardKpi FORM -------------------- 
    public static final String DIV_RA_STANDARD_KPI_KPI_NAME = "VALIDATE_RA_STANDARD_KPI_KPI_NAME";
    public static final String DIV_RA_STANDARD_KPI_DESCRIPTION = "VALIDATE_RA_STANDARD_KPI_DESCRIPTION";
    public static final String DIV_RA_STANDARD_KPI_STA_DATE = "VALIDATE_RA_STANDARD_KPI_STA_DATE";
    public static final String DIV_RA_STANDARD_KPI_END_DATE = "VALIDATE_RA_STANDARD_KPI_END_DATE";
    public static final String DIV_RA_STANDARD_KPI_JOIN_RULE = "VALIDATE_RA_STANDARD_KPI_JOIN_RULE";
    public static final String DIV_RA_STANDARD_KPI_RETURN_MESSAGE = "RA_STANDARD_KPI_FORM_RETURN_MESSAGE";
    public static final String DIV_RA_STANDARD_KPI_ACTION_MESSAGE = "RA_STANDARD_KPI_FORM_ACTION_MESSAGE";
    public static final String DIV_RA_STANDARD_KPI_REASON_MESSAGE = "RA_STANDARD_KPI_FORM_REASON_MESSAGE";
    public static final String DIV_RA_STANDARD_KPI_ACTION_FLAG = "RA_STANDARD_KPI_FORM_ACTION_FLAG";
    // ----------------------RaStandardKpiDetail FORM -------------------- 
    public static final String DIV_RA_STANDARD_KPI_DETAIL_STANDARD_KPI_ID = "VALIDATE_RA_STANDARD_KPI_DETAIL_STANDARD_KPI_ID";
    public static final String DIV_RA_STANDARD_KPI_DETAIL_COMPARATOR = "VALIDATE_RA_STANDARD_KPI_DETAIL_COMPARATOR";
    public static final String DIV_RA_STANDARD_KPI_DETAIL_KPI_VALUE = "VALIDATE_RA_STANDARD_KPI_DETAIL_KPI_VALUE";
    public static final String DIV_RA_STANDARD_KPI_DETAIL_TOLERANCE_VALUE = "VALIDATE_RA_STANDARD_KPI_DETAIL_TOLERANCE_VALUE";
    public static final String DIV_RA_STANDARD_KPI_DETAIL_DESCRIPTION = "VALIDATE_RA_STANDARD_KPI_DETAIL_DESCRIPTION";
    public static final String DIV_RA_STANDARD_KPI_DETAIL_RETURN_MESSAGE = "RA_STANDARD_KPI_DETAIL_FORM_RETURN_MESSAGE";
    public static final String DIV_RA_STANDARD_KPI_DETAIL_ACTION_MESSAGE = "RA_STANDARD_KPI_DETAIL_FORM_ACTION_MESSAGE";
    public static final String DIV_RA_STANDARD_KPI_DETAIL_REASON_MESSAGE = "RA_STANDARD_KPI_DETAIL_FORM_REASON_MESSAGE";
    public static final String DIV_RA_STANDARD_KPI_DETAIL_ACTION_FLAG = "RA_STANDARD_KPI_DETAIL_FORM_ACTION_FLAG";
    // ----------------------RaCompareValue FORM -------------------- 
    public static final String DIV_RA_COMPARE_VALUE_COMPARE_VALUE_NAME = "VALIDATE_RA_COMPARE_VALUE_COMPARE_VALUE_NAME";
    public static final String DIV_RA_COMPARE_VALUE_DESCRIPTION = "VALIDATE_RA_COMPARE_VALUE_DESCRIPTION";
    public static final String DIV_RA_COMPARE_VALUE_RETURN_MESSAGE = "RA_COMPARE_VALUE_FORM_RETURN_MESSAGE";
    public static final String DIV_RA_COMPARE_VALUE_ACTION_MESSAGE = "RA_COMPARE_VALUE_FORM_ACTION_MESSAGE";
    public static final String DIV_RA_COMPARE_VALUE_REASON_MESSAGE = "RA_COMPARE_VALUE_FORM_REASON_MESSAGE";
    public static final String DIV_RA_COMPARE_VALUE_ACTION_FLAG = "RA_COMPARE_VALUE_FORM_ACTION_FLAG";
    // ----------------------RaCompareValueDetail FORM -------------------- 
    public static final String DIV_RA_COMPARE_VALUE_DETAIL_STANDARD_KEY_ID = "VALIDATE_RA_COMPARE_VALUE_DETAIL_STANDARD_KEY_ID";
    public static final String DIV_RA_COMPARE_VALUE_DETAIL_COMPARE_VALUE_ID = "VALIDATE_RA_COMPARE_VALUE_DETAIL_COMPARE_VALUE_ID";
    public static final String DIV_RA_COMPARE_VALUE_DETAIL_KEY_ORDER = "VALIDATE_RA_COMPARE_VALUE_DETAIL_KEY_ORDER";
    public static final String DIV_RA_COMPARE_VALUE_DETAIL_STANDARD_KPI = "VALIDATE_RA_COMPARE_VALUE_DETAIL_STANDARD_KPI";
    public static final String DIV_RA_COMPARE_VALUE_DETAIL_DESCRIPTION = "VALIDATE_RA_COMPARE_VALUE_DETAIL_DESCRIPTION";
    public static final String DIV_RA_COMPARE_VALUE_DETAIL_RETURN_MESSAGE = "RA_COMPARE_VALUE_DETAIL_FORM_RETURN_MESSAGE";
    public static final String DIV_RA_COMPARE_VALUE_DETAIL_ACTION_MESSAGE = "RA_COMPARE_VALUE_DETAIL_FORM_ACTION_MESSAGE";
    public static final String DIV_RA_COMPARE_VALUE_DETAIL_REASON_MESSAGE = "RA_COMPARE_VALUE_DETAIL_FORM_REASON_MESSAGE";
    public static final String DIV_RA_COMPARE_VALUE_DETAIL_ACTION_FLAG = "RA_COMPARE_VALUE_DETAIL_FORM_ACTION_FLAG";
    // ----------------------RaFaultReason FORM -------------------- 
    public static final String DIV_RA_FAULT_REASON_FAULT_REASON_NAME = "VALIDATE_RA_FAULT_REASON_FAULT_REASON_NAME";
    public static final String DIV_RA_FAULT_REASON_DESCRIPTION = "VALIDATE_RA_FAULT_REASON_DESCRIPTION";
    public static final String DIV_RA_FAULT_REASON_FAULT_REASON_TYPE = "VALIDATE_RA_FAULT_REASON_FAULT_REASON_TYPE";
    public static final String DIV_RA_FAULT_REASON_RETURN_MESSAGE = "RA_FAULT_REASON_FORM_RETURN_MESSAGE";
    public static final String DIV_RA_FAULT_REASON_ACTION_MESSAGE = "RA_FAULT_REASON_FORM_ACTION_MESSAGE";
    public static final String DIV_RA_FAULT_REASON_REASON_MESSAGE = "RA_FAULT_REASON_FORM_REASON_MESSAGE";
    public static final String DIV_RA_FAULT_REASON_ACTION_FLAG = "RA_FAULT_REASON_FORM_ACTION_FLAG";
    // ----------------------RaStandardThreshold FORM -------------------- 
    public static final String DIV_RA_STANDARD_THRESHOLD_THRESHOLD_NAME = "VALIDATE_RA_STANDARD_THRESHOLD_THRESHOLD_NAME";
    public static final String DIV_RA_STANDARD_THRESHOLD_DESCRIPTION = "VALIDATE_RA_STANDARD_THRESHOLD_DESCRIPTION";
    public static final String DIV_RA_STANDARD_THRESHOLD_THRESHOLD_TYPE = "VALIDATE_RA_STANDARD_THRESHOLD_THRESHOLD_TYPE";
    public static final String DIV_RA_STANDARD_THRESHOLD_THRESHOLD_VALUE = "VALIDATE_RA_STANDARD_THRESHOLD_THRESHOLD_VALUE";
    public static final String DIV_RA_STANDARD_THRESHOLD_THRESHOLD_FIELD = "VALIDATE_RA_STANDARD_THRESHOLD_THRESHOLD_FIELD";
    public static final String DIV_RA_STANDARD_THRESHOLD_RETURN_MESSAGE = "RA_STANDARD_THRESHOLD_FORM_RETURN_MESSAGE";
    public static final String DIV_RA_STANDARD_THRESHOLD_ACTION_MESSAGE = "RA_STANDARD_THRESHOLD_FORM_ACTION_MESSAGE";
    public static final String DIV_RA_STANDARD_THRESHOLD_REASON_MESSAGE = "RA_STANDARD_THRESHOLD_FORM_REASON_MESSAGE";
    public static final String DIV_RA_STANDARD_THRESHOLD_ACTION_FLAG = "RA_STANDARD_THRESHOLD_FORM_ACTION_FLAG";
    // ----------------------RaWarningContent FORM -------------------- 
    public static final String DIV_RA_WARNING_CONTENT_CONTENT_NAME = "VALIDATE_RA_WARNING_CONTENT_CONTENT_NAME";
    public static final String DIV_RA_WARNING_CONTENT_DESCRIPTION = "VALIDATE_RA_WARNING_CONTENT_DESCRIPTION";
    public static final String DIV_RA_WARNING_CONTENT_RETURN_MESSAGE = "RA_WARNING_CONTENT_FORM_RETURN_MESSAGE";
    public static final String DIV_RA_WARNING_CONTENT_ACTION_MESSAGE = "RA_WARNING_CONTENT_FORM_ACTION_MESSAGE";
    public static final String DIV_RA_WARNING_CONTENT_REASON_MESSAGE = "RA_WARNING_CONTENT_FORM_REASON_MESSAGE";
    public static final String DIV_RA_WARNING_CONTENT_ACTION_FLAG = "RA_WARNING_CONTENT_FORM_ACTION_FLAG";
    // ----------------------RaNotification FORM -------------------- 
    public static final String DIV_RA_NOTIFICATION_NOTIFICATION_NAME = "VALIDATE_RA_NOTIFICATION_NOTIFICATION_NAME";
    public static final String DIV_RA_NOTIFICATION_DESCRIPTION = "VALIDATE_RA_NOTIFICATION_DESCRIPTION";
    public static final String DIV_RA_NOTIFICATION_NOTIFICATION_TYPE = "VALIDATE_RA_NOTIFICATION_NOTIFICATION_TYPE";
    public static final String DIV_RA_NOTIFICATION_RETURN_MESSAGE = "RA_NOTIFICATION_FORM_RETURN_MESSAGE";
    public static final String DIV_RA_NOTIFICATION_ACTION_MESSAGE = "RA_NOTIFICATION_FORM_ACTION_MESSAGE";
    public static final String DIV_RA_NOTIFICATION_REASON_MESSAGE = "RA_NOTIFICATION_FORM_REASON_MESSAGE";
    public static final String DIV_RA_NOTIFICATION_ACTION_FLAG = "RA_NOTIFICATION_FORM_ACTION_FLAG";
    // ----------------------RaInteractionRole FORM -------------------- 
    public static final String DIV_RA_INTERACTION_ROLE_INTERACTION_ROLE_NAME = "VALIDATE_RA_INTERACTION_ROLE_INTERACTION_ROLE_NAME";
    public static final String DIV_RA_INTERACTION_ROLE_INDIVIDUAL_ID = "VALIDATE_RA_INTERACTION_ROLE_INDIVIDUAL_ID";
    public static final String DIV_RA_INTERACTION_ROLE_NOTIFICATION_ID = "VALIDATE_RA_INTERACTION_ROLE_NOTIFICATION_ID";
    public static final String DIV_RA_INTERACTION_ROLE_STATUS = "VALIDATE_RA_INTERACTION_ROLE_STATUS";
    public static final String DIV_RA_INTERACTION_ROLE_RETURN_MESSAGE = "RA_INTERACTION_ROLE_FORM_RETURN_MESSAGE";
    public static final String DIV_RA_INTERACTION_ROLE_ACTION_MESSAGE = "RA_INTERACTION_ROLE_FORM_ACTION_MESSAGE";
    public static final String DIV_RA_INTERACTION_ROLE_REASON_MESSAGE = "RA_INTERACTION_ROLE_FORM_REASON_MESSAGE";
    public static final String DIV_RA_INTERACTION_ROLE_ACTION_FLAG = "RA_INTERACTION_ROLE_FORM_ACTION_FLAG";
    // ----------------------RaSenderMail FORM -------------------- 
    public static final String DIV_RA_SENDER_MAIL_SENDER_MAIL_ADDRESS = "VALIDATE_RA_SENDER_MAIL_SENDER_MAIL_ADDRESS";
    public static final String DIV_RA_SENDER_MAIL_SENDER_MAIL_PASSWORD = "VALIDATE_RA_SENDER_MAIL_SENDER_MAIL_PASSWORD";
    public static final String DIV_RA_SENDER_MAIL_SENDER_MAIL_SMTP_HOST = "VALIDATE_RA_SENDER_MAIL_SENDER_MAIL_SMTP_HOST";
    public static final String DIV_RA_SENDER_MAIL_SENDER_MAIL_PORT = "VALIDATE_RA_SENDER_MAIL_SENDER_MAIL_PORT";
    public static final String DIV_RA_SENDER_MAIL_DESCRIPTION = "VALIDATE_RA_SENDER_MAIL_DESCRIPTION";
    public static final String DIV_RA_SENDER_MAIL_RETURN_MESSAGE = "RA_SENDER_MAIL_FORM_RETURN_MESSAGE";
    public static final String DIV_RA_SENDER_MAIL_ACTION_MESSAGE = "RA_SENDER_MAIL_FORM_ACTION_MESSAGE";
    public static final String DIV_RA_SENDER_MAIL_REASON_MESSAGE = "RA_SENDER_MAIL_FORM_REASON_MESSAGE";
    public static final String DIV_RA_SENDER_MAIL_ACTION_FLAG = "RA_SENDER_MAIL_FORM_ACTION_FLAG";
    // ----------------------RaSenderSms FORM -------------------- 
    public static final String DIV_RA_SENDER_SMS_WS_URL = "VALIDATE_RA_SENDER_SMS_WS_URL";
    public static final String DIV_RA_SENDER_SMS_WS_XMLNS = "VALIDATE_RA_SENDER_SMS_WS_XMLNS";
    public static final String DIV_RA_SENDER_SMS_USERNAME = "VALIDATE_RA_SENDER_SMS_USERNAME";
    public static final String DIV_RA_SENDER_SMS_PASSWORD = "VALIDATE_RA_SENDER_SMS_PASSWORD";
    public static final String DIV_RA_SENDER_SMS_SENDER_NUMBER = "VALIDATE_RA_SENDER_SMS_SENDER_NUMBER";
    public static final String DIV_RA_SENDER_SMS_DESCRIPTION = "VALIDATE_RA_SENDER_SMS_DESCRIPTION";
    public static final String DIV_RA_SENDER_SMS_RETURN_MESSAGE = "RA_SENDER_SMS_FORM_RETURN_MESSAGE";
    public static final String DIV_RA_SENDER_SMS_ACTION_MESSAGE = "RA_SENDER_SMS_FORM_ACTION_MESSAGE";
    public static final String DIV_RA_SENDER_SMS_REASON_MESSAGE = "RA_SENDER_SMS_FORM_REASON_MESSAGE";
    public static final String DIV_RA_SENDER_SMS_ACTION_FLAG = "RA_SENDER_SMS_FORM_ACTION_FLAG";
    // ----------------------RaWarning FORM -------------------- 
    public static final String DIV_RA_WARNING_WARNING_NAME = "VALIDATE_RA_WARNING_WARNING_NAME";
    public static final String DIV_RA_WARNING_DESCRIPTION = "VALIDATE_RA_WARNING_DESCRIPTION";
    public static final String DIV_RA_WARNING_RETURN_MESSAGE = "RA_WARNING_FORM_RETURN_MESSAGE";
    public static final String DIV_RA_WARNING_ACTION_MESSAGE = "RA_WARNING_FORM_ACTION_MESSAGE";
    public static final String DIV_RA_WARNING_REASON_MESSAGE = "RA_WARNING_FORM_REASON_MESSAGE";
    public static final String DIV_RA_WARNING_ACTION_FLAG = "RA_WARNING_FORM_ACTION_FLAG";
    // ----------------------RaContactMedium FORM -------------------- 
    public static final String DIV_RA_CONTACT_MEDIUM_MEDIUM_NAME = "VALIDATE_RA_CONTACT_MEDIUM_MEDIUM_NAME";
    public static final String DIV_RA_CONTACT_MEDIUM_DESCRIPTION = "VALIDATE_RA_CONTACT_MEDIUM_DESCRIPTION";
    public static final String DIV_RA_CONTACT_MEDIUM_MEDIUM_TYPE = "VALIDATE_RA_CONTACT_MEDIUM_MEDIUM_TYPE";
    public static final String DIV_RA_CONTACT_MEDIUM_MEDIUM_ADDRESS = "VALIDATE_RA_CONTACT_MEDIUM_MEDIUM_ADDRESS";
    public static final String DIV_RA_CONTACT_MEDIUM_INDIVIDUAL_ID = "VALIDATE_RA_CONTACT_MEDIUM_INDIVIDUAL_ID";
    public static final String DIV_RA_CONTACT_MEDIUM_RETURN_MESSAGE = "RA_CONTACT_MEDIUM_FORM_RETURN_MESSAGE";
    public static final String DIV_RA_CONTACT_MEDIUM_ACTION_MESSAGE = "RA_CONTACT_MEDIUM_FORM_ACTION_MESSAGE";
    public static final String DIV_RA_CONTACT_MEDIUM_REASON_MESSAGE = "RA_CONTACT_MEDIUM_FORM_REASON_MESSAGE";
    public static final String DIV_RA_CONTACT_MEDIUM_ACTION_FLAG = "RA_CONTACT_MEDIUM_FORM_ACTION_FLAG";
    // ----------------------RaWarningDetail FORM -------------------- 
    public static final String DIV_RA_WARNING_DETAIL_WARNING_DETAIL_NAME = "VALIDATE_RA_WARNING_DETAIL_WARNING_DETAIL_NAME";
    public static final String DIV_RA_WARNING_DETAIL_DESCRIPTION = "VALIDATE_RA_WARNING_DETAIL_DESCRIPTION";
    public static final String DIV_RA_WARNING_DETAIL_WARNING_ID = "VALIDATE_RA_WARNING_DETAIL_WARNING_ID";
    public static final String DIV_RA_WARNING_DETAIL_NOTIFICATION_ID = "VALIDATE_RA_WARNING_DETAIL_NOTIFICATION_ID";
    public static final String DIV_RA_WARNING_DETAIL_WARNING_CONTENT_ID = "VALIDATE_RA_WARNING_DETAIL_WARNING_CONTENT_ID";
    public static final String DIV_RA_WARNING_DETAIL_THRESHOLD_ID = "VALIDATE_RA_WARNING_DETAIL_THRESHOLD_ID";
    public static final String DIV_RA_WARNING_DETAIL_FAULT_REASON_ID = "VALIDATE_RA_WARNING_DETAIL_FAULT_REASON_ID";
    public static final String DIV_RA_WARNING_DETAIL_STATUS = "VALIDATE_RA_WARNING_DETAIL_STATUS";
    public static final String DIV_RA_WARNING_DETAIL_RETURN_MESSAGE = "RA_WARNING_DETAIL_FORM_RETURN_MESSAGE";
    public static final String DIV_RA_WARNING_DETAIL_ACTION_MESSAGE = "RA_WARNING_DETAIL_FORM_ACTION_MESSAGE";
    public static final String DIV_RA_WARNING_DETAIL_REASON_MESSAGE = "RA_WARNING_DETAIL_FORM_REASON_MESSAGE";
    public static final String DIV_RA_WARNING_DETAIL_ACTION_FLAG = "RA_WARNING_DETAIL_FORM_ACTION_FLAG";
    // ----------------------RaTroubleTicket FORM --------------------
    public static final String DIV_RA_TROUBLE_TICKET_FAULT_REASON_ID = "VALIDATE_RA_TROUBLE_TICKET_FAULT_REASON_ID";
    public static final String DIV_RA_TROUBLE_TICKET_TROUBLE_DETECTION_DATE = "VALIDATE_RA_TROUBLE_TICKET_TROUBLE_DETECTION_DATE";
    public static final String DIV_RA_TROUBLE_TICKET_SERVICE_RESTORED_DATE = "VALIDATE_RA_TROUBLE_TICKET_SERVICE_RESTORED_DATE";
    public static final String DIV_RA_TROUBLE_TICKET_STATE = "VALIDATE_RA_TROUBLE_TICKET_STATE";
    public static final String DIV_RA_TROUBLE_TICKET_SERVICE_TYPE = "VALIDATE_RA_TROUBLE_TICKET_SERVICE_TYPE";
    public static final String DIV_RA_TROUBLE_TICKET_RETURN_MESSAGE = "RA_TROUBLE_TICKET_FORM_RETURN_MESSAGE";
    public static final String DIV_RA_TROUBLE_TICKET_ACTION_MESSAGE = "RA_TROUBLE_TICKET_FORM_ACTION_MESSAGE";
    public static final String DIV_RA_TROUBLE_TICKET_REASON_MESSAGE = "RA_TROUBLE_TICKET_FORM_REASON_MESSAGE";
    public static final String DIV_RA_TROUBLE_TICKET_ACTION_FLAG = "RA_TROUBLE_TICKET_FORM_ACTION_FLAG";
    // ----------------------RaWarningContentDetail FORM -------------------- 
    public static final String DIV_RA_WARNING_CONTENT_DETAIL_CONTENT = "VALIDATE_RA_WARNING_CONTENT_DETAIL_CONTENT";
    public static final String DIV_RA_WARNING_CONTENT_DETAIL_MEDIUM_TYPE = "VALIDATE_RA_WARNING_CONTENT_DETAIL_MEDIUM_TYPE";
    public static final String DIV_RA_WARNING_CONTENT_DETAIL_SUBJECT = "VALIDATE_RA_WARNING_CONTENT_DETAIL_SUBJECT";
    public static final String DIV_RA_WARNING_CONTENT_DETAIL_DESCRIPTION = "VALIDATE_RA_WARNING_CONTENT_DETAIL_DESCRIPTION";
    public static final String DIV_RA_WARNING_CONTENT_DETAIL_WARNING_CONTENT_ID = "VALIDATE_RA_WARNING_CONTENT_DETAIL_WARNING_CONTENT_ID";
    public static final String DIV_RA_WARNING_CONTENT_DETAIL_RETURN_MESSAGE = "RA_WARNING_CONTENT_DETAIL_FORM_RETURN_MESSAGE";
    public static final String DIV_RA_WARNING_CONTENT_DETAIL_ACTION_MESSAGE = "RA_WARNING_CONTENT_DETAIL_FORM_ACTION_MESSAGE";
    public static final String DIV_RA_WARNING_CONTENT_DETAIL_REASON_MESSAGE = "RA_WARNING_CONTENT_DETAIL_FORM_REASON_MESSAGE";
    public static final String DIV_RA_WARNING_CONTENT_DETAIL_ACTION_FLAG = "RA_WARNING_CONTENT_DETAIL_FORM_ACTION_FLAG";
    // ----------------------RaSubTicketGgsn FORM --------------------
    public static final String DIV_RA_SUB_TICKET_GGSN_TROUBLE_TICKET_ID = "VALIDATE_RA_SUB_TICKET_GGSN_TROUBLE_TICKET_ID";
    public static final String DIV_RA_SUB_TICKET_GGSN_CDR_GGSN_DEVIATION_ID = "VALIDATE_RA_SUB_TICKET_GGSN_CDR_GGSN_DEVIATION_ID";
    public static final String DIV_RA_SUB_TICKET_GGSN_STA_DATE = "VALIDATE_RA_SUB_TICKET_GGSN_STA_DATE";
    public static final String DIV_RA_SUB_TICKET_GGSN_END_DATE = "VALIDATE_RA_SUB_TICKET_GGSN_END_DATE";
    public static final String DIV_RA_SUB_TICKET_GGSN_STATE = "VALIDATE_RA_SUB_TICKET_GGSN_STATE";
    public static final String DIV_RA_SUB_TICKET_GGSN_RETURN_MESSAGE = "RA_SUB_TICKET_GGSN_FORM_RETURN_MESSAGE";
    public static final String DIV_RA_SUB_TICKET_GGSN_ACTION_MESSAGE = "RA_SUB_TICKET_GGSN_FORM_ACTION_MESSAGE";
    public static final String DIV_RA_SUB_TICKET_GGSN_REASON_MESSAGE = "RA_SUB_TICKET_GGSN_FORM_REASON_MESSAGE";
    public static final String DIV_RA_SUB_TICKET_GGSN_ACTION_FLAG = "RA_SUB_TICKET_GGSN_FORM_ACTION_FLAG";
    // -----------------------RaReport-----------------
    public static final String SPLIT_PATTERN = ";";
    public static final String NOT_FOUND_DATA = "NotFoundData";//Khong tim thay du lieu
    public static final String NOT_EQUAL_QUANTITY = "NotEqualQuantity";//Khong dung so luong
    //public static final String FROM_TO_QUANTITY_NOT_NULL = "FromToQuantiyIsNotNull"; //Tu serial, den serial, so luong khong duoc de trong
    public static final String SYSTEM_ERROR = "SystemError";//Khong tin tai kho hang
    public static final String NOT_EXIST_STOCK_GOODS = "NotExistStockGoods";//Khong ton tai kho hang tren he thong
    public static final String NOT_EXIST_SERIAL_STRIP_IN_STOCK = "NotExistSerialStripInStock";//Khong ton tai dai serial trong kho
    public static final String NOT_EXIST_OR_ONE_MORE_STOCK_GOODS = "NotExistOrOneMoreStockGoods";//Khong ton tai kho hang hoac nhieu hon 1 kho hang
    public static final String SYSTEM_OR_DATA_ERROR = "SystemOrDataError";//Loi du lieu hoac he thong
    public static final String GOODS_IS_NOT_EXIST = "GoodsIsNotExist";//Mat hang khong ton tai

    // ----------------------WoType FORM -------------------- 
    public static final String DIV_WO_TYPE_WO_TYPE_CODE = "VALIDATE_WO_TYPE_WO_TYPE_CODE";
    public static final String DIV_WO_TYPE_WO_TYPE_NAME = "VALIDATE_WO_TYPE_WO_TYPE_NAME";
    public static final String DIV_WO_TYPE_RETURN_MESSAGE = "WO_TYPE_FORM_RETURN_MESSAGE";
    public static final String DIV_WO_TYPE_ACTION_MESSAGE = "WO_TYPE_FORM_ACTION_MESSAGE";
    public static final String DIV_WO_TYPE_REASON_MESSAGE = "WO_TYPE_FORM_REASON_MESSAGE";
    public static final String DIV_WO_TYPE_ACTION_FLAG = "WO_TYPE_FORM_ACTION_FLAG";

    // ----------------------WoTypeLocale FORM -------------------- 
    public static final String DIV_WO_TYPE_LOCALE_WO_TYPE_LOCALE_ID = "VALIDATE_WO_TYPE_LOCALE_WO_TYPE_LOCALE_ID";
    public static final String DIV_WO_TYPE_LOCALE_LOCALE_CODE = "VALIDATE_WO_TYPE_LOCALE_LOCALE_CODE";
    public static final String DIV_WO_TYPE_LOCALE_WO_TYPE_NAME = "VALIDATE_WO_TYPE_LOCALE_WO_TYPE_NAME";
    public static final String DIV_WO_TYPE_LOCALE_RETURN_MESSAGE = "WO_TYPE_LOCALE_FORM_RETURN_MESSAGE";
    public static final String DIV_WO_TYPE_LOCALE_ACTION_MESSAGE = "WO_TYPE_LOCALE_FORM_ACTION_MESSAGE";
    public static final String DIV_WO_TYPE_LOCALE_REASON_MESSAGE = "WO_TYPE_LOCALE_FORM_REASON_MESSAGE";
    public static final String DIV_WO_TYPE_LOCALE_ACTION_FLAG = "WO_TYPE_LOCALE_FORM_ACTION_FLAG";

    // ----------------------WoPriority FORM -------------------- 
    public static final String DIV_WO_PRIORITY_WO_TYPE_ID = "VALIDATE_WO_PRIORITY_WO_TYPE_ID";
    public static final String DIV_WO_PRIORITY_CD_RECEIVEDALARM = "VALIDATE_WO_PRIORITY_CD_RECEIVEDALARM";
    public static final String DIV_WO_PRIORITY_CD_RECEIVEDALARM_TYPE = "VALIDATE_WO_PRIORITY_CD_RECEIVEDALARM_TYPE";
    public static final String DIV_WO_PRIORITY_CD_RECEIVED_THRESHOLD = "VALIDATE_WO_PRIORITY_CD_RECEIVED_THRESHOLD";
    public static final String DIV_WO_PRIORITY_CD_RECEIVED_THRESHOLD_TYPE = "VALIDATE_WO_PRIORITY_CD_RECEIVED_THRESHOLD_TYPE";
    public static final String DIV_WO_PRIORITY_FT_RECEIVEDALARM = "VALIDATE_WO_PRIORITY_FT_RECEIVEDALARM";
    public static final String DIV_WO_PRIORITY_FT_RECEIVEDALARM_TYPE = "VALIDATE_WO_PRIORITY_FT_RECEIVEDALARM_TYPE";
    public static final String DIV_WO_PRIORITY_FT_RECEIVED_THRESHOLD = "VALIDATE_WO_PRIORITY_FT_RECEIVED_THRESHOLD";
    public static final String DIV_WO_PRIORITY_FT_RECEIVED_THRESHOLD_TYPE = "VALIDATE_WO_PRIORITY_FT_RECEIVED_THRESHOLD_TYPE";
    public static final String DIV_WO_PRIORITY_FT_PROCESSALARM = "VALIDATE_WO_PRIORITY_FT_PROCESSALARM";
    public static final String DIV_WO_PRIORITY_FT_PROCESSALARM_TYPE = "VALIDATE_WO_PRIORITY_FT_PROCESSALARM_TYPE";
    public static final String DIV_WO_PRIORITY_FT_PROCESS_THRESHOLD = "VALIDATE_WO_PRIORITY_FT_PROCESS_THRESHOLD";
    public static final String DIV_WO_PRIORITY_FT_PROCESS_THRESHOLD_TYPE = "VALIDATE_WO_PRIORITY_FT_PROCESS_THRESHOLD_TYPE";
    public static final String DIV_WO_PRIORITY_PRIORITY_NAME = "VALIDATE_WO_PRIORITY_PRIORITY_NAME";
    public static final String DIV_WO_PRIORITY_RETURN_MESSAGE = "WO_PRIORITY_FORM_RETURN_MESSAGE";
    public static final String DIV_WO_PRIORITY_ACTION_MESSAGE = "WO_PRIORITY_FORM_ACTION_MESSAGE";
    public static final String DIV_WO_PRIORITY_REASON_MESSAGE = "WO_PRIORITY_FORM_REASON_MESSAGE";
    public static final String DIV_WO_PRIORITY_ACTION_FLAG = "WO_PRIORITY_FORM_ACTION_FLAG";

    private ParamUtils() {
    }

//    public static final Map<String, String> mapStatus
//            = Collections.unmodifiableMap(new HashMap<String, String>() {
//
//                {
//                    put(String.valueOf(ACTIVE), "MSG.ACTIVE");
//                    put(String.valueOf(INACTIVE), "MSG.INACTIVE");
//                    put(String.valueOf(DEFAULT_VALUE), "MSG.CHOOSE_STATUS");
//                }
//            });
}
