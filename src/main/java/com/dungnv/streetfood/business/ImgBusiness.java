/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.ImgDTO;
import com.dungnv.streetfood.model.Img;
import com.dungnv.streetfood.dao.ImgDAO;
import com.dungnv.streetfood.dto.ArticleDTO;
import com.dungnv.streetfood.dto.CategoryDTO;
import com.dungnv.streetfood.dto.DishDTO;
import com.dungnv.streetfood.dto.RestaurantDTO;
import com.dungnv.streetfood.dto.SlideShowDTO;
import com.dungnv.streetfood.model.Article;
import com.dungnv.streetfood.model.Category;
import com.dungnv.streetfood.model.Dish;
import com.dungnv.streetfood.model.Restaurant;
import com.dungnv.streetfood.model.SlideShow;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import com.dungnv.vfw5.base.utils.Constants;
import com.dungnv.vfw5.base.utils.DataUtil;
import com.dungnv.vfw5.base.utils.LanguageBundleUtils;
import com.dungnv.vfw5.base.utils.ParamUtils;
import com.dungnv.vfw5.base.utils.StringUtils;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.hibernate.Session;
import javax.transaction.Transactional;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/25/2016 10:05 PM
 */
@Service("imgBusiness")
@Transactional
public class ImgBusiness extends BaseFWServiceImpl<ImgDAO, ImgDTO, Img> implements ImgBusinessInterface {

    @Autowired
    private ImgDAO imgDAO;

    private ArticleDTO articleDTO;
    private CategoryDTO categoryDTO;
    private DishDTO dishDTO;
    private RestaurantDTO restaurantDTO;
    private SlideShowDTO slideShowDTO;

    @Autowired
    private ArticleBusinessInterface articleBusiness;
    @Autowired
    private CategoryBusinessInterface categoryBusiness;
    @Autowired
    private DishBusinessInterface dishBusiness;
    @Autowired
    private RestaurantBusinessInterface restaurantBusiness;
    @Autowired
    private SlideShowBusinessInterface slideShowBusiness;

    public ImgBusiness() {
        tModel = new Img();
        tDAO = imgDAO;
    }

    @Override
    public ImgDAO gettDAO() {
        return imgDAO;
    }

    public ImgBusiness(Session session) {
        this.session = session;
        tModel = new Img();
        tDAO = imgDAO;
    }

    @Override
    public ResultDTO insertImg(String userName, String localeCode, String countryCode, String token, ImgDTO dto) {
        return createObject(dto);
    }

    @Override
    public ResultDTO updateImg(String userName, String localeCode, String countryCode, String token, ImgDTO dto) {
        ResultDTO result = new ResultDTO();
        result.setMessage(update(dto));
        return result;
    }

    @Override
    public ResultDTO updateMerge(String userName, String localeCode, String countryCode, String token, ImgDTO dto) {
        ResultDTO result = new ResultDTO();
        result.setMessage(updateMerge(dto));
        return result;
    }

    @Override
    public ResultDTO deleteImg(String userName, String localeCode, String countryCode, String token, String id) {
        ResultDTO result = new ResultDTO();
        result.setMessage(delete(Long.valueOf(id)));
        return result;
    }

    @Override
    public ResultDTO attachImg(String userName, String localeCode, String countryCode, String token, List<String> urls, String objectId, String objectType) {
        ResultDTO result = new ResultDTO();
        result.setMessage(ParamUtils.SUCCESS);
        locale = DataUtil.getLocale(localeCode, countryCode);
        String validate = validateAttachImg(locale, objectId, objectType);
        if (!StringUtils.isNullOrEmpty(validate)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(validate);
            return result;
        }

        ImgDTO imgDTO = new ImgDTO();

        switch (objectType) {
            case Constants.OBJECT_TYPE.ARTICLE:
                imgDTO.setArticleId(objectId);
                break;
            case Constants.OBJECT_TYPE.CATEGORY:
                imgDTO.setDishGroupId(objectId);
                break;
            case Constants.OBJECT_TYPE.DISH:
                imgDTO.setDishId(objectId);
                break;
            case Constants.OBJECT_TYPE.RESTAURANT:
                imgDTO.setRestaurantId(objectId);
                break;
            case Constants.OBJECT_TYPE.SLIDESHOW:
                imgDTO.setSlideShowId(objectId);
                break;
            default:
                break;
        }
        int insertCount = 0;
        int currentCount = 0;
        List<ImgDTO> listCurrImg = search(imgDTO, 0, 0, "ASC", "order");
        if (listCurrImg != null) {
            currentCount = listCurrImg.size();
        }
        if (urls != null) {
            insertCount = urls.size();

            for (int i = 0; i < insertCount; i++) {
                ImgDTO currDTO;
                if (i < currentCount) {
                    currDTO = listCurrImg.get(i);
                    if (StringUtils.isNullOrEmpty(currDTO.getUrl())
                            || StringUtils.isNullOrEmpty(currDTO.getOrder())
                            || !currDTO.getOrder().equals(String.valueOf(i + 1))
                            || !currDTO.getUrl().equals(urls.get(i))) {
                        currDTO.setOrder(String.valueOf(i + 1));
                        currDTO.setUrl(urls.get(i));
                        result = updateMerge(userName, localeCode, countryCode, token, currDTO); 
                    }
                } else {
                    currDTO = new ImgDTO();
                    currDTO.setOrder(String.valueOf(i + 1));
                    currDTO.setUrl(urls.get(i));
                    switch (objectType) {
                        case Constants.OBJECT_TYPE.ARTICLE:
                            currDTO.setArticleId(objectId);
                            break;
                        case Constants.OBJECT_TYPE.CATEGORY:
                            currDTO.setDishGroupId(objectId);
                            break;
                        case Constants.OBJECT_TYPE.DISH:
                            currDTO.setDishId(objectId);
                            break;
                        case Constants.OBJECT_TYPE.RESTAURANT:
                            currDTO.setRestaurantId(objectId);
                            break;
                        case Constants.OBJECT_TYPE.SLIDESHOW:
                            currDTO.setSlideShowId(objectId);
                            break;
                        default:
                            break;
                    }

                    result = insertImg(userName, localeCode, countryCode, token, imgDTO);
                }
                if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                    TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                    return result;
                }
            }
        }
        while (insertCount < currentCount) {
            result = deleteImg(userName, localeCode, countryCode, token, listCurrImg.get(++insertCount - 1).getId());
            if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                return result;
            }
        }
        return result;
    }

    private String validateAttachImg(Locale locale, String objectId, String objectType) {

        if (StringUtils.isNullOrEmpty(objectId)) {
            return LanguageBundleUtils.getString(locale, "message.img.attachModel.id.null");
        }

        if (StringUtils.isNullOrEmpty(objectType)) {
            return LanguageBundleUtils.getString(locale, "message.img.objectType.null");
        }
        if (!objectType.equals(Constants.OBJECT_TYPE.ARTICLE)
                && !objectType.equals(Constants.OBJECT_TYPE.CATEGORY)
                && !objectType.equals(Constants.OBJECT_TYPE.DISH)
                && !objectType.equals(Constants.OBJECT_TYPE.RESTAURANT)
                && !objectType.equals(Constants.OBJECT_TYPE.SLIDESHOW)) {
            return LanguageBundleUtils.getString(locale, "message.img.objectType.invalid");
        }

        if (objectType.equals(Constants.OBJECT_TYPE.ARTICLE)) {
            if (!NumberUtils.isNumber(objectId)) {
                return LanguageBundleUtils.getString(locale, "message.article.id.invalid");
            }

            Article article = articleBusiness.findById(Long.valueOf(objectId));
            if (article == null) {
                return LanguageBundleUtils.getString(locale, "message.article.id.notExist");
            }
        }

        if (objectType.equals(Constants.OBJECT_TYPE.CATEGORY)) {
            if (!NumberUtils.isNumber(objectId)) {
                return LanguageBundleUtils.getString(locale, "message.category.id.invalid");
            }

            Category category = categoryBusiness.findById(Long.valueOf(objectId));
            if (category == null) {
                return LanguageBundleUtils.getString(locale, "message.category.id.notExist");
            }

        }
        if (objectType.equals(Constants.OBJECT_TYPE.DISH)) {
            if (!NumberUtils.isNumber(objectId)) {
                return LanguageBundleUtils.getString(locale, "message.dish.id.invalid");
            }

            Dish dish = dishBusiness.findById(Long.valueOf(objectId));
            if (dish == null) {
                return LanguageBundleUtils.getString(locale, "message.dish.id.notExist");
            }
        }

        if (objectType.equals(Constants.OBJECT_TYPE.RESTAURANT)) {
            if (!NumberUtils.isNumber(objectId)) {
                return LanguageBundleUtils.getString(locale, "message.restaurant.id.invalid");
            }

            Restaurant restaurant = restaurantBusiness.findById(Long.valueOf(objectId));
            if (restaurant == null) {
                return LanguageBundleUtils.getString(locale, "message.restaurant.id.notExist");
            }
        }
        if (objectType.equals(Constants.OBJECT_TYPE.SLIDESHOW)) {
            if (!NumberUtils.isNumber(objectId)) {
                return LanguageBundleUtils.getString(locale, "message.slideShow.id.invalid");
            }

            SlideShow slideShow = slideShowBusiness.findById(Long.valueOf(objectId));
            if (slideShow == null) {
                return LanguageBundleUtils.getString(locale, "message.slideShow.id.notExist");
            }
        }
        //TODO:
        return null;
    }

    //<editor-fold defaultstate="collapsed" desc="GETS & SETS">
//</editor-fold>
    public ImgDAO getImgDAO() {

        return imgDAO;
    }

    public void setImgDAO(ImgDAO imgDAO) {
        this.imgDAO = imgDAO;
    }

    public ArticleDTO getArticleDTO() {
        return articleDTO;
    }

    public void setArticleDTO(ArticleDTO articleDTO) {
        this.articleDTO = articleDTO;
    }

    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }

    public DishDTO getDishDTO() {
        return dishDTO;
    }

    public void setDishDTO(DishDTO dishDTO) {
        this.dishDTO = dishDTO;
    }

    public RestaurantDTO getRestaurantDTO() {
        return restaurantDTO;
    }

    public void setRestaurantDTO(RestaurantDTO restaurantDTO) {
        this.restaurantDTO = restaurantDTO;
    }

    public SlideShowDTO getSlideShowDTO() {
        return slideShowDTO;
    }

    public void setSlideShowDTO(SlideShowDTO slideShowDTO) {
        this.slideShowDTO = slideShowDTO;
    }

}
