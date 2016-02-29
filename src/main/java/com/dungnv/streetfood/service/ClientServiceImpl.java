/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.business.ArticleBusinessInterface;
import com.dungnv.streetfood.business.CategoryBusinessInterface;
import com.dungnv.streetfood.business.DishBusinessInterface;
import com.dungnv.streetfood.business.LocaleBusinessInterface;
import com.dungnv.streetfood.business.RestaurantBusinessInterface;
import com.dungnv.streetfood.business.SlideShowBusinessInterface;
import com.dungnv.streetfood.business.TagsBusinessInterface;
import com.dungnv.streetfood.business.UserBusinessInterface;
import com.dungnv.streetfood.dto.ArticleDTO;
import com.dungnv.streetfood.dto.CategoryDTO;
import com.dungnv.streetfood.dto.DishDTO;
import com.dungnv.streetfood.dto.LocaleDTO;
import com.dungnv.streetfood.dto.RestaurantDTO;
import com.dungnv.streetfood.dto.SlideShowDTO;
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
    RestaurantBusinessInterface restaurantBusiness;
    @Autowired
    CategoryBusinessInterface categoryBusiness;
    @Autowired
    DishBusinessInterface dishBusiness;
    @Autowired
    SlideShowBusinessInterface slideShowBusiness;
    @Autowired
    UserBusinessInterface userBusiness;
    @Autowired
    TagsBusinessInterface tagsBusiness;
    @Autowired
    LocaleBusinessInterface localeBusiness;

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

    @Override
    public ResultDTO insertDish(String userName, String localeCode//
            , String countryCode, String token, DishDTO dishDTO) {
        return dishBusiness.insertDish(userName, localeCode, countryCode, token, dishDTO);
    }

    @Override
    public ResultDTO updateDish(String userName, String localeCode//
            , String countryCode, String token, DishDTO dishDTO) {
        return dishBusiness.updateDish(userName, localeCode, countryCode, token, dishDTO);
    }

    @Override
    public ResultDTO deleteDish(String userName, String localeCode//
            , String countryCode, String token, Long id) {
        return dishBusiness.deleteDish(userName, localeCode, countryCode, token, String.valueOf(id));
    }

    @Override
    public List<DishDTO> getListDishDTOLess(String userName, String localeCode, String countryCode, String token//
            , DishDTO dishDTO, int rowStart, int maxRow, boolean isCount, String sortType, String sortFieldList) {
        return dishBusiness.getListDishDTOLess(userName, localeCode, countryCode//
                , token, dishDTO, rowStart, maxRow, isCount, sortType, sortFieldList);
    }

    @Override
    public DishDTO getDishDetail(String userName, String localeCode, String countryCode, String token, String id) {
        return dishBusiness.getDishDetail(userName, localeCode, countryCode, token, id);
    }

    @Override
    public ResultDTO insertArticle(String userName, String localeCode//
            , String countryCode, String token, ArticleDTO articleDTO) {
        return articleBusiness.insertArticle(userName, localeCode, countryCode, token, articleDTO);
    }

    @Override
    public ResultDTO updateArticle(String userName, String localeCode//
            , String countryCode, String token, ArticleDTO articleDTO) {
        return articleBusiness.updateArticle(userName, localeCode, countryCode, token, articleDTO);
    }

    @Override
    public ResultDTO deleteArticle(String userName, String localeCode//
            , String countryCode, String token, Long id) {
        return articleBusiness.deleteArticle(userName, localeCode, countryCode, token, String.valueOf(id));
    }

    @Override
    public List<ArticleDTO> getListArticleDTOLess(String userName, String localeCode, String countryCode, String token//
            , ArticleDTO articleDTO, int rowStart, int maxRow, boolean isCount, String sortType, String sortFieldList) {
        return articleBusiness.getListArticleDTOLess(userName, localeCode, countryCode//
                , token, articleDTO, rowStart, maxRow, isCount, sortType, sortFieldList);
    }

    @Override
    public ArticleDTO getArticleDetail(String userName, String localeCode, String countryCode, String token, String id) {
        return articleBusiness.getArticleDetail(userName, localeCode, countryCode, token, id);
    }

    @Override
    public ResultDTO insertRestaurant(String userName, String localeCode//
            , String countryCode, String token, RestaurantDTO restaurantDTO) {
        return restaurantBusiness.insertRestaurant(userName, localeCode, countryCode, token, restaurantDTO);
    }

    @Override
    public ResultDTO updateRestaurant(String userName, String localeCode//
            , String countryCode, String token, RestaurantDTO restaurantDTO) {
        return restaurantBusiness.updateRestaurant(userName, localeCode, countryCode, token, restaurantDTO);
    }

    @Override
    public ResultDTO deleteRestaurant(String userName, String localeCode//
            , String countryCode, String token, Long id) {
        return restaurantBusiness.deleteRestaurant(userName, localeCode, countryCode, token, String.valueOf(id));
    }

    @Override
    public List<RestaurantDTO> getListRestaurantDTOLess(String userName, String localeCode, String countryCode, String token//
            , RestaurantDTO restaurantDTO, int rowStart, int maxRow, boolean isCount, String sortType, String sortFieldList) {
        return restaurantBusiness.getListRestaurantDTOLess(userName, localeCode, countryCode//
                , token, restaurantDTO, rowStart, maxRow, isCount, sortType, sortFieldList);
    }

    @Override
    public RestaurantDTO getRestaurantDetail(String userName, String localeCode, String countryCode, String token, String id) {
        return restaurantBusiness.getRestaurantDetail(userName, localeCode, countryCode, token, id);
    }

    @Override
    public ResultDTO insertSlideShow(String userName, String localeCode//
            , String countryCode, String token, SlideShowDTO slideShowDTO) {
        return slideShowBusiness.insertSlideShow(userName, localeCode, countryCode, token, slideShowDTO);
    }

    @Override
    public ResultDTO updateSlideShow(String userName, String localeCode//
            , String countryCode, String token, SlideShowDTO slideShowDTO) {
        return slideShowBusiness.updateSlideShow(userName, localeCode, countryCode, token, slideShowDTO);
    }

    @Override
    public ResultDTO deleteSlideShow(String userName, String localeCode//
            , String countryCode, String token, Long id) {
        return slideShowBusiness.deleteSlideShow(userName, localeCode, countryCode, token, String.valueOf(id));
    }

    @Override
    public List<SlideShowDTO> getListSlideShowDTOLess(String userName, String localeCode, String countryCode, String token//
            , SlideShowDTO slideShowDTO, int rowStart, int maxRow, boolean isCount, String sortType, String sortFieldList) {
        return slideShowBusiness.getListSlideShowDTOLess(userName, localeCode, countryCode//
                , token, slideShowDTO, rowStart, maxRow, isCount, sortType, sortFieldList);
    }

    @Override
    public SlideShowDTO getSlideShowDetail(String userName, String localeCode, String countryCode, String token, String id) {
        return slideShowBusiness.getSlideShowDetail(userName, localeCode, countryCode, token, id);
    }

}
