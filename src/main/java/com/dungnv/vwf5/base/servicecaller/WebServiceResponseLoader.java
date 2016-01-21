package com.dungnv.vwf5.base.servicecaller;

import org.apache.commons.configuration.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Created by vtsoft on 1/16/2015.
 */
public class WebServiceResponseLoader {

    private final static String DEFAULT_FILE = "wsresponse.xml";
    private final static String ALIAS = ".alias";
    private final static String ALIAS_FIELD = ".aliasField";
    private final static String IMPLICIT = ".addImplicitCollection";
    private final static String TYPE = ".type";
    private final static String TYPE_SINGLE = "single";
    private final static String TYPE_LIST = "multiple";
    private final static String TYPE_CUSTOM = "custom";

    private final static String TAG = ".tag";
    private final static String CLASS = ".class";
    private final static String FIELD = ".field";

    public Object wsServiceHandler(String configFile, String configId, String responseContent) throws Exception {
        String configFileVal = configFile;
        if (configFile == null) {
            configFileVal = DEFAULT_FILE;
        }
        XMLConfiguration rootConfig = null;
        CombinedConfiguration configuration = new CombinedConfiguration();
        //load all config file
        String wsResponseId = configId;

        rootConfig = new XMLConfiguration(configFileVal);
        configuration.addConfiguration(rootConfig);
        Iterator<String> keys = configuration.getKeys(wsResponseId);
        if (keys.hasNext()) {

            String parseType = configuration.getString(wsResponseId + TYPE);
            //alias
            //get alias tag
            List<Object> aliasTags = configuration.getList(wsResponseId + ALIAS + TAG);
            //get alias class
            List<Object> aliasClasses = configuration.getList(wsResponseId + ALIAS + CLASS);

            if (aliasTags.size() != aliasClasses.size()) {
                throw new Exception("The configuration of alias is invalid!");
            }

            //aliasField
            //get aliasField tag
            List<Object> aliasFieldTags = configuration.getList(wsResponseId + ALIAS_FIELD + TAG);
            //get aliasField class
            List<Object> aliasFieldClasses = configuration.getList(wsResponseId + ALIAS_FIELD + CLASS);
            //get aliasField name
            List<Object> aliasFieldNames = configuration.getList(wsResponseId + ALIAS_FIELD + FIELD);

            if (aliasFieldTags.size() != aliasFieldClasses.size()
                    || (aliasFieldNames.size() != aliasFieldClasses.size())
                    || (aliasFieldTags.size() != aliasFieldNames.size())) {
                throw new Exception("The configuration of aliasFields is invalid!");
            }

            //addImplicitCollection
            //get aliasField tag
            List<Object> implicitTags = configuration.getList(wsResponseId + IMPLICIT + TAG);
            //get aliasField class
            List<Object> implicitClassed = configuration.getList(wsResponseId + IMPLICIT + CLASS);
            //get aliasField name

            if (implicitTags.size() != implicitClassed.size()) {
                throw new Exception("The configuration of implicit is invalid!");
            }

            List<XStreamStorage> parseObject = new ArrayList<>();

            for (int i = 0; i < aliasTags.size(); i++) {
                String tag = (String) aliasTags.get(i);
                String className = (String) aliasClasses.get(i);
                Mapper.alias(parseObject, className, tag);
            }

            for (int i = 0; i < aliasFieldTags.size(); i++) {
                String tag = (String) aliasFieldTags.get(i);
                String className = (String) aliasFieldClasses.get(i);
                String fieldName = (String) aliasFieldNames.get(i);
                Mapper.aliasField(parseObject, className, tag, fieldName);
            }

            for (int i = 0; i < implicitTags.size(); i++) {
                String tag = (String) implicitTags.get(i);
                String className = (String) implicitClassed.get(i);
                Mapper.addImplicitCollection(parseObject, className, tag);
            }

            XmlStream xmlStream = new XmlStream();
            xmlStream.config(parseObject);
            if (parseType.toLowerCase(Locale.ENGLISH).equals(TYPE_SINGLE)) {
                xmlStream.isSingleType = true;
            } else if (parseType.toLowerCase(Locale.ENGLISH).equals(TYPE_LIST)) {
                xmlStream.isSingleType = false;
            }
            return WebServiceHandler.wsServiceHandler(responseContent, xmlStream);
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        XMLConfiguration rootConfig = null;
        CombinedConfiguration configuration = new CombinedConfiguration();
        //load all config file
        String wsResponseId = "getOrderTypes";

        rootConfig = new XMLConfiguration("wsresponse.xml");
        configuration.addConfiguration(rootConfig);
        Iterator<String> keys = configuration.getKeys(wsResponseId);
        if (keys.hasNext()) {

            String parseType = configuration.getString(wsResponseId + ".type");
            System.out.println(parseType);
            //alias
            //get alias tag
            List<Object> aliasTags = configuration.getList(wsResponseId + ".alias.tag");
            //get alias class
            List<Object> aliasClasses = configuration.getList(wsResponseId + ".alias.class");

            if (aliasTags.size() != aliasClasses.size()) {
                throw new Exception("The configuration of alias is invalid!");
            }

            //aliasField
            //get aliasField tag
            List<Object> aliasFieldTags = configuration.getList(wsResponseId + ".aliasField.tag");
            //get aliasField class
            List<Object> aliasFieldClasses = configuration.getList(wsResponseId + ".aliasField.class");
            //get aliasField name
            List<Object> aliasFieldNames = configuration.getList(wsResponseId + ".aliasField.field");

            if (aliasFieldTags.size() != aliasFieldClasses.size()
                    || (aliasFieldNames.size() != aliasFieldClasses.size())
                    || (aliasFieldTags.size() != aliasFieldNames.size())) {
                throw new Exception("The configuration of aliasFields is invalid!");
            }

            //addImplicitCollection
            //get aliasField tag
            List<Object> implicitTags = configuration.getList(wsResponseId + ".addImplicitCollection.tag");
            //get aliasField class
            List<Object> implicitClassed = configuration.getList(wsResponseId + ".addImplicitCollection.class");
            //get aliasField name

            if (implicitTags.size() != implicitClassed.size()) {
                throw new Exception("The configuration of implicit is invalid!");
            }

            List<XStreamStorage> parseObject = new ArrayList<>();

            for (int i = 0; i < aliasTags.size(); i++) {
                String tag = (String) aliasTags.get(i);
                String className = (String) aliasClasses.get(i);
                Mapper.alias(parseObject, className, tag);
            }

            for (int i = 0; i < aliasFieldTags.size(); i++) {
                String tag = (String) aliasFieldTags.get(i);
                String className = (String) aliasFieldClasses.get(i);
                String fieldName = (String) aliasFieldNames.get(i);
                Mapper.aliasField(parseObject, className, tag, fieldName);
            }

            for (int i = 0; i < implicitTags.size(); i++) {
                String tag = (String) implicitTags.get(i);
                String className = (String) implicitTags.get(i);
                Mapper.addImplicitCollection(parseObject, className, tag);
            }

            XmlStream xmlStream = new XmlStream();
            xmlStream.config(parseObject);

        }

    }

}
