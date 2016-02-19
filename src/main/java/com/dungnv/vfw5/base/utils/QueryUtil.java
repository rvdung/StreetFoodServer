package com.dungnv.vfw5.base.utils;

import org.hibernate.type.Type;


/**
 * This class provides utility methods in creating criteria queries or EJB QL.
 *
 * @author Shriharsh Mishra
 */
public class QueryUtil {

    /**
     * This method returns a string of place-holders('?') for parameter query.
     *
     * @param size, number of place-holders
     * @return String in the forms of '(?, ?, ? .......)'
     */
    public static String getParameterHolderString(int size) {
        StringBuffer strBf = new StringBuffer();
        if (size > 0) {
            strBf.append(" ( ? ");
            for (int i = 0; i < size - 1; i++) {
                strBf.append(", ? ");
            }
            strBf.append(" )  ");
        }

        return strBf.toString();
    }

    /**
     * This method returns a Hibernate Type array for
     * Restrictions.sqlRestriction API
     *
     * @param size, size of Type[]
     * @param type, Hibernate Type, e.g. Hibernate.STRING
     *
     * NOTE: This method only useful if all the types passed to
     * Restrictions.sqlRestriction API are of the same type.
     * @return
     */
    public static Type[] getTypeArray(int size, Type type) {
        Type[] typeArr = new Type[size];
        for (int i = 0; i < size; i++) {
            typeArr[i] = type;
        }
        return typeArr;
    }

}
