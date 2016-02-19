/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.vfw5.base.dto;

import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vtsoft
 */
@XmlRootElement
public class ResultDTO {

    private String id;
    private String key;
    private String message;
    private int quantitySucc;
    private int quantityFail;

    private Double amount;
    private Double amountIssue;

    public ResultDTO(String id, String key, String message) {
        this.id = id;
        this.key = key;
        this.message = message;
    }

    public ResultDTO(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public ResultDTO(String key, int quantitySucc) {
        this.key = key;
        this.quantitySucc = quantitySucc;
    }

    public ResultDTO() {
        // This constructor is intentionally empty. Nothing special is needed here.
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getQuantitySucc() {
        return quantitySucc;
    }

    public void setQuantitySucc(int quantitySucc) {
        this.quantitySucc = quantitySucc;
    }

    public int getQuantityFail() {
        return quantityFail;
    }

    public void setQuantityFail(int quanityFail) {
        this.quantityFail = quanityFail;
    }
    //

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmountIssue() {
        return amountIssue;
    }

    public void setAmountIssue(Double amountIssue) {
        this.amountIssue = amountIssue;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.key);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResultDTO other = (ResultDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.key, other.key)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResultDTO{" + "id=" + id + ", key=" + key + ", message=" + message + ", quantitySucc=" + quantitySucc + ", quantityFail=" + quantityFail + ", amount=" + amount + ", amountIssue=" + amountIssue + '}';
    }

}
