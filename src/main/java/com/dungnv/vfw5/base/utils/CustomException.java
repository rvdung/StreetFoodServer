/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.vfw5.base.utils;

/**
 *
 * @author pm2-vdi-02
 */
public class CustomException extends Exception {

    private String messageDetail;

    public CustomException(String messageDetail) {
        this.messageDetail = messageDetail;
    }

    public String getMessageDetail() {
        return messageDetail;
    }

    public void setMessageDetail(String messageDetail) {
        this.messageDetail = messageDetail;
    }

}
