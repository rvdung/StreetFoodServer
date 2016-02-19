/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.business.ArticleBusinessInterface;
import com.dungnv.streetfood.business.CategoryBusinessInterface;
import com.dungnv.streetfood.business.LocaleBusinessInterface;
import com.dungnv.streetfood.business.TagsBusinessInterface;
import com.dungnv.streetfood.business.UserBusinessInterface;
import com.dungnv.streetfood.dto.ArticleDTO;
import com.dungnv.streetfood.dto.CategoryDTO;
import com.dungnv.streetfood.dto.LocaleDTO;
import com.dungnv.streetfood.dto.TagsDTO;
import com.dungnv.vfw5.base.dto.ResultDTO;
import java.util.List;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ODIN NGUYEN
 */
@WebService(endpointInterface = "com.dungnv.streetfood.service.ClientService")
public class ClientServiceImpl implements ClientService {

    @Autowired
    ArticleBusinessInterface articleBusiness;
    @Autowired
    CategoryBusinessInterface categoryBusiness;
    @Autowired
    UserBusinessInterface userBusiness;
    @Autowired
    TagsBusinessInterface tagsBusiness;
    @Autowired
    LocaleBusinessInterface localeBusiness;

    @Override
    public List<ArticleDTO> getListArticleDTO(ArticleDTO articleDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        if (articleDTO != null) {
            return articleBusiness.search(articleDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public String updateArticle(String username, ArticleDTO articleDTO) {
        return articleBusiness.update(articleDTO);
    }

    @Override
    public String deleteArticle(String username, Long id) {
        return articleBusiness.delete(id);
    }

    @Override
    public ArticleDTO findArticleById(Long id) {
        if (id != null && id > 0) {
            return (ArticleDTO) articleBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public ResultDTO insertArticle(String username, ArticleDTO articleDTO) {
        ResultDTO result = articleBusiness.createObject(articleDTO);
//        String delete = deleteArticle(username,8L);
        return result;

    }

    @Override
    public String insertOrUpdateListArticle(List<ArticleDTO> articleDTO) {
        return articleBusiness.insertList(articleDTO);
    }

    @Override
    public ResultDTO insertCategory(String userName, String localeCode, String countryCode, String token, CategoryDTO categoryDTO) {
        return categoryBusiness.insertCategory(userName, localeCode, countryCode, token, categoryDTO);
    }

    @Override
    public ResultDTO updateCategory(String userName, String localeCode, String countryCode, String token, CategoryDTO categoryDTO) {
        return categoryBusiness.updateCategory(userName, localeCode, countryCode, token, categoryDTO);
    }

    @Override
    public ResultDTO deleteCategory(String userName, String localeCode, String countryCode, String token, String id) {
        return categoryBusiness.deleteCategory(userName, localeCode, countryCode, token, id);
    }

    @Override
    public ResultDTO login(String userName, String localeCode, String countryCode, String password) {
        return userBusiness.login(userName, localeCode, countryCode, userName, password);
    }

    @Override
    public List<CategoryDTO> getListCategoryDTOLess(String userName, String localeCode//
            , String countryCode, String token, CategoryDTO categoryDTO, int rowStart, int maxRow//
            , boolean isCount, String sortType, String sortFieldList) {
        return categoryBusiness.getListCategoryDTOLess(userName, localeCode, countryCode//
                , token, categoryDTO, rowStart, maxRow, isCount, sortType, sortFieldList);
    }

    @Override
    public List<TagsDTO> getListTagsDTO(String userName, String localeCode, String countryCode, String token, TagsDTO tagsDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return tagsBusiness.search(tagsDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public CategoryDTO getCategoryDetail(String userName, String localeCode, String countryCode, String token, String id) {
        return categoryBusiness.getCategoryDetail(userName, localeCode, countryCode, token, id);
    }

    @Override
    public List<LocaleDTO> getListLocaleDTO(String userName, String localeCode, String countryCode, String token, LocaleDTO localeDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return localeBusiness.search(localeDTO, rowStart, maxRow, sortType, sortFieldList);
    }

}
