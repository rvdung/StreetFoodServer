/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author ODIN NGUYEN
 */
@Configuration
public class ServiceConfig {

    @Bean
    public CategoryService getCategoryService() {
        return new CategoryServiceImpl();
    }

    @Bean
    public ClientService getClientService() {
        return new ClientServiceImpl();
    }
}
