/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.vfw5.base.utils;

/**
 *
 * @author dungnv50
 */
public final class ErrorUtils {

    private ErrorUtils() {
    }

    public static String printLog(Exception e) {
        String str;
        try {
            str = new StringBuilder().append(":").append(e.toString()).toString();
            if ((e.getCause() != null) && (e.getCause().getMessage() != null)) {
                str = new StringBuilder().append(str).append(" - Caused by ").append(e.getCause().getMessage()).toString();
            }
            str = new StringBuilder().append(str).append("\n").toString();
            StackTraceElement[] traceList = e.getStackTrace();
            for (StackTraceElement trace : traceList) {
                if (trace.getClassName().contains("com.dungnv")
                        || trace.getClassName().contains("com.dungnv.vfw5")
                        || trace.getClassName().contains("com.dungnv.vwf5")) {
                    str = new StringBuilder().append(str).append(" [").append(trace.getClassName()).append(".class][").append(trace.getMethodName()).append("][").append(trace.getLineNumber()).append("]\n").toString();
                }
            }
        } catch (Exception ex) {
            str = new StringBuilder().append(":").append(e.toString()).toString();
        }
        return str;
    }
}
