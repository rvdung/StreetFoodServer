/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.RestaurantArticleDTO;
import com.dungnv.streetfood.model.RestaurantArticle;
import com.dungnv.streetfood.dao.RestaurantArticleDAO;
import com.dungnv.streetfood.dto.ArticleDTO;
import com.dungnv.streetfood.dto.RestaurantArticleDTO;
import com.dungnv.streetfood.dto.RestaurantDTO;
import com.dungnv.streetfood.model.Article;
import com.dungnv.streetfood.model.Restaurant;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import com.dungnv.vfw5.base.utils.DataUtil;
import com.dungnv.vfw5.base.utils.LanguageBundleUtils;
import com.dungnv.vfw5.base.utils.ParamUtils;
import com.dungnv.vfw5.base.utils.StringUtils;
import org.hibernate.Session;
import java.util.*;
import javax.transaction.Transactional;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 2/21/2016 12:49 PM
 */
@Service("restaurantArticleBusiness")
@Transactional
public class RestaurantArticleBusiness extends BaseFWServiceImpl<RestaurantArticleDAO, RestaurantArticleDTO, RestaurantArticle> implements RestaurantArticleBusinessInterface {

    @Autowired
    private RestaurantArticleDAO restaurantArticleDAO;
    @Autowired
    private ArticleBusinessInterface articleBusiness;
    @Autowired
    private RestaurantBusinessInterface restaurantBusiness;

    enum TYPE {
        DISHES_TO_ARTICLE, ARTICLES_TO_DISH
    }

    public RestaurantArticleBusiness() {
        tModel = new RestaurantArticle();
        tDAO = restaurantArticleDAO;
    }

    @Override
    public RestaurantArticleDAO gettDAO() {
        return restaurantArticleDAO;
    }

    public RestaurantArticleBusiness(Session session) {
        this.session = session;
        tModel = new RestaurantArticle();
        tDAO = restaurantArticleDAO;
    }

    @Override
    public List<ArticleDTO> getListArticleByRestaurant(String userName, String localeCode, String countryCode, String token, String id) {
        SQLQuery query = gettDAO().getSession().createSQLQuery("select c.id, c.name from article c "
                + " inner join restaurant_article l on c.id = l.article_id"
                + " where l.restaurant_id = ? ");
        query.addScalar("id", StringType.INSTANCE);
        query.addScalar("name", StringType.INSTANCE);
        query.setLong(0, Long.valueOf(id));
        query.setResultTransformer(Transformers.aliasToBean(ArticleDTO.class));
        return query.list();
    }

    @Override
    public List<RestaurantDTO> getListRestaurantByArticle(String userName, String localeCode, String countryCode, String token, String id) {
        SQLQuery query = gettDAO().getSession().createSQLQuery("select c.id, c.name from restaurant c "
                + " inner join restaurant_article l on c.id =l.restaurant_id"
                + " where l.article_id = ? ");
        query.addScalar("id", StringType.INSTANCE);
        query.addScalar("name", StringType.INSTANCE);
        query.setLong(0, Long.valueOf(id));
        query.setResultTransformer(Transformers.aliasToBean(RestaurantDTO.class));
        return query.list();
    }

    @Override
    public ResultDTO insertListRestaurantToArticle(String userName, String localeCode, String countryCode, String token, String id, List<String> list) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);

        if (list != null) {
            list = DataUtil.removeDuplicateString(list, Boolean.TRUE);
        }

        String validate = validate(locale, id, list, RestaurantArticleBusiness.TYPE.DISHES_TO_ARTICLE);
        if (!StringUtils.isNullOrEmpty(validate)) {
            return rollBackTransaction(validate);
        }

        Map<String, RestaurantArticleDTO> mapcurrentRestaurant = new HashMap<String, RestaurantArticleDTO>();

        RestaurantArticleDTO searchDTO = new RestaurantArticleDTO();
        searchDTO.setArticleId(id);
        List<RestaurantArticleDTO> ListcurrentRestaurant = search(searchDTO, 0, 0, "ASC", "id");

        for (RestaurantArticleDTO restaurant : ListcurrentRestaurant) {
            mapcurrentRestaurant.put(restaurant.getRestaurantId(), restaurant);
        }

        if (list != null) {
            for (String restaurantId : list) {
                if (mapcurrentRestaurant.containsKey(restaurantId)) {
                    mapcurrentRestaurant.remove(restaurantId);
                } else {
                    RestaurantArticleDTO dto = new RestaurantArticleDTO();
                    dto.setRestaurantId(restaurantId);
                    dto.setArticleId(id);
                    result = createObject(dto);
                    if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                        TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                        return result;
                    }
                }
            }
        }

        if (!mapcurrentRestaurant.isEmpty()) {
            String resultDelete = delete(mapcurrentRestaurant.values());
            if (!ParamUtils.SUCCESS.equals(resultDelete)
                    && !ParamUtils.FAIL.equals(resultDelete)) {
                TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                result.setMessage(ParamUtils.FAIL);
                result.setKey(resultDelete);
                return result;
            }
        }

        result.setMessage(ParamUtils.SUCCESS);
        return result;
    }

    @Override
    public ResultDTO insertListArticleToRestaurant(String userName, String localeCode, String countryCode, String token, String id, List<String> list) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);

        if (list != null) {
            list = DataUtil.removeDuplicateString(list, Boolean.TRUE);
        }

        String validate = validate(locale, id, list, RestaurantArticleBusiness.TYPE.ARTICLES_TO_DISH);
        if (!StringUtils.isNullOrEmpty(validate)) {
            return rollBackTransaction(validate);
        }

        Map<String, RestaurantArticleDTO> mapcurrentArticle = new HashMap<>();

        RestaurantArticleDTO searchDTO = new RestaurantArticleDTO();
        searchDTO.setRestaurantId(id);
        List<RestaurantArticleDTO> ListcurrentArticle = search(searchDTO, 0, 0, "ASC", "id");

        for (RestaurantArticleDTO article : ListcurrentArticle) {
            mapcurrentArticle.put(article.getArticleId(), article);
        }

        if (list != null) {
            for (String articleId : list) {
                if (mapcurrentArticle.containsKey(articleId)) {
                    mapcurrentArticle.remove(articleId);
                } else {
                    RestaurantArticleDTO dto = new RestaurantArticleDTO();
                    dto.setRestaurantId(id);
                    dto.setArticleId(articleId);
                    result = createObject(dto);
                    if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                        TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                        return result;
                    }
                }
            }
        }

        if (!mapcurrentArticle.isEmpty()) {
            String resultDelete = delete(mapcurrentArticle.values());
            if (!ParamUtils.SUCCESS.equals(resultDelete)
                    && !ParamUtils.FAIL.equals(resultDelete)) {
                TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                result.setMessage(ParamUtils.FAIL);
                result.setKey(resultDelete);
                return result;
            }
        }

        result.setMessage(ParamUtils.SUCCESS);
        return result;
    }

    private String validate(Locale locale, String id, List<String> ids, RestaurantArticleBusiness.TYPE type) {
        if (type.equals(RestaurantArticleBusiness.TYPE.ARTICLES_TO_DISH)) {
            if (StringUtils.isNullOrEmpty(id)) {
                return LanguageBundleUtils.getString(locale, "message.restaurant.id.null");
            }

            Restaurant model = restaurantBusiness.findById(Long.valueOf(id));

            if (model == null) {
                return LanguageBundleUtils.getString(locale, "message.restaurant.id.notExist");
            }

            if (ids != null && !ids.isEmpty()) {
                List<ConditionBean> lstConditionBean = new ArrayList<>();
                lstConditionBean.add(new ConditionBean(ArticleDTO.ID, ParamUtils.OP_IN, DataUtil.formatInputList(ids), ParamUtils.TYPE_NUMBER));
                List<ArticleDTO> listDTO = articleBusiness.searchByConditionBean(lstConditionBean, 0, 0, "ASC", "id");

                if (listDTO == null || listDTO.size() != ids.size()) {
                    return LanguageBundleUtils.getString(locale, "message.article.id.notExist");
                }
            }
        } else {
            if (StringUtils.isNullOrEmpty(id)) {
                return LanguageBundleUtils.getString(locale, "message.article.id.null");
            }

            Article model = articleBusiness.findById(Long.valueOf(id));

            if (model == null) {
                return LanguageBundleUtils.getString(locale, "message.article.id.notExist");
            }

            if (ids != null && !ids.isEmpty()) {
                List<ConditionBean> lstConditionBean = new ArrayList<>();
                lstConditionBean.add(new ConditionBean(RestaurantDTO.ID, ParamUtils.OP_IN, DataUtil.formatInputList(ids), ParamUtils.TYPE_NUMBER));
                List<RestaurantDTO> listDTO = restaurantBusiness.searchByConditionBean(lstConditionBean, 0, 0, "ASC", "id");

                if (listDTO == null || listDTO.size() != ids.size()) {
                    return LanguageBundleUtils.getString(locale, "message.restaurant.id.notExist");
                }
            }
        }
        return null;
    }
}
