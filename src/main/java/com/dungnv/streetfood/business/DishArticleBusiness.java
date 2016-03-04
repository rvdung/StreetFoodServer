/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.streetfood.model.DishArticle;
import com.dungnv.streetfood.dao.DishArticleDAO;
import com.dungnv.streetfood.dto.ArticleDTO;
import com.dungnv.streetfood.dto.DishArticleDTO;
import com.dungnv.streetfood.dto.DishDTO;
import com.dungnv.streetfood.model.Article;
import com.dungnv.streetfood.model.Dish;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.vfw5.base.utils.DataUtil;
import com.dungnv.vfw5.base.utils.LanguageBundleUtils;
import com.dungnv.vfw5.base.utils.ParamUtils;
import com.dungnv.vfw5.base.utils.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.hibernate.Session;
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
 * @since 2/29/2016 10:24 PM
 */
@Service("dishArticleBusiness")
@Transactional
public class DishArticleBusiness extends BaseFWServiceImpl<DishArticleDAO, DishArticleDTO, DishArticle> implements DishArticleBusinessInterface {

    @Autowired
    private DishArticleDAO dishArticleDAO;
    @Autowired
    private ArticleBusinessInterface articleBusiness;
    @Autowired
    private DishBusinessInterface dishBusiness;

    enum TYPE {
        DISHES_TO_ARTICLE, ARTICLES_TO_DISH
    }

    public DishArticleBusiness() {
        tModel = new DishArticle();
        tDAO = dishArticleDAO;
    }

    @Override
    public DishArticleDAO gettDAO() {
        return dishArticleDAO;
    }

    public DishArticleBusiness(Session session) {
        this.session = session;
        tModel = new DishArticle();
        tDAO = dishArticleDAO;
    }

    @Override
    public List<ArticleDTO> getListArticleByDish(String userName, String localeCode, String countryCode, String token, String id) {
        SQLQuery query = gettDAO().getSession().createSQLQuery("select c.id, c.name from article c "
                + " inner join dish_article l on c.id = l.article_id"
                + " where l.dish_id = ? ");
        query.addScalar("id", StringType.INSTANCE);
        query.addScalar("name", StringType.INSTANCE);
        query.setLong(0, Long.valueOf(id));
        query.setResultTransformer(Transformers.aliasToBean(ArticleDTO.class));
        return query.list();
    }

    @Override
    public List<DishDTO> getListDishByArticle(String userName, String localeCode, String countryCode, String token, String id) {
        SQLQuery query = gettDAO().getSession().createSQLQuery("select c.id, c.name from dish c "
                + " inner join dish_article l on c.id =l.dish_id"
                + " where l.article_id = ? ");
        query.addScalar("id", StringType.INSTANCE);
        query.addScalar("name", StringType.INSTANCE);
        query.setLong(0, Long.valueOf(id));
        query.setResultTransformer(Transformers.aliasToBean(DishDTO.class));
        return query.list();
    }

    @Override
    public ResultDTO insertListDishToArticle(String userName, String localeCode, String countryCode, String token, String id, List<String> list) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);

        if (list != null) {
            list = DataUtil.removeDuplicateString(list, Boolean.TRUE);
        }

        String validate = validate(locale, id, list, DishArticleBusiness.TYPE.DISHES_TO_ARTICLE);
        if (!StringUtils.isNullOrEmpty(validate)) {
            return rollBackTransaction(validate);
        }

        Map<String, DishArticleDTO> mapcurrentDish = new HashMap<String, DishArticleDTO>();

        DishArticleDTO searchDTO = new DishArticleDTO();
        searchDTO.setArticleId(id);
        List<DishArticleDTO> ListcurrentDish = search(searchDTO, 0, 0, "ASC", "id");

        for (DishArticleDTO dish : ListcurrentDish) {
            mapcurrentDish.put(dish.getDishId(), dish);
        }

        if (list != null) {
            for (String dishId : list) {
                if (mapcurrentDish.containsKey(dishId)) {
                    mapcurrentDish.remove(dishId);
                } else {
                    DishArticleDTO dto = new DishArticleDTO();
                    dto.setDishId(dishId);
                    dto.setArticleId(id);
                    result = createObject(dto);
                    if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                        TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                        return result;
                    }
                }
            }
        }

        if (!mapcurrentDish.isEmpty()) {
            String resultDelete = delete(mapcurrentDish.values());
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
    public ResultDTO insertListArticleToDish(String userName, String localeCode, String countryCode, String token, String id, List<String> list) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);

        if (list != null) {
            list = DataUtil.removeDuplicateString(list, Boolean.TRUE);
        }

        String validate = validate(locale, id, list, DishArticleBusiness.TYPE.ARTICLES_TO_DISH);
        if (!StringUtils.isNullOrEmpty(validate)) {
            return rollBackTransaction(validate);
        }

        Map<String, DishArticleDTO> mapcurrentArticle = new HashMap<>();

        DishArticleDTO searchDTO = new DishArticleDTO();
        searchDTO.setDishId(id);
        List<DishArticleDTO> ListcurrentArticle = search(searchDTO, 0, 0, "ASC", "id");

        for (DishArticleDTO article : ListcurrentArticle) {
            mapcurrentArticle.put(article.getArticleId(), article);
        }

        if (list != null) {
            for (String articleId : list) {
                if (mapcurrentArticle.containsKey(articleId)) {
                    mapcurrentArticle.remove(articleId);
                } else {
                    DishArticleDTO dto = new DishArticleDTO();
                    dto.setDishId(id);
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

    private String validate(Locale locale, String id, List<String> ids, DishArticleBusiness.TYPE type) {
        if (type.equals(DishArticleBusiness.TYPE.ARTICLES_TO_DISH)) {
            if (StringUtils.isNullOrEmpty(id)) {
                return LanguageBundleUtils.getString(locale, "message.dish.id.null");
            }

            Dish model = dishBusiness.findById(Long.valueOf(id));

            if (model == null) {
                return LanguageBundleUtils.getString(locale, "message.dish.id.notExist");
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
                lstConditionBean.add(new ConditionBean(DishDTO.ID, ParamUtils.OP_IN, DataUtil.formatInputList(ids), ParamUtils.TYPE_NUMBER));
                List<DishDTO> listDTO = dishBusiness.searchByConditionBean(lstConditionBean, 0, 0, "ASC", "id");

                if (listDTO == null || listDTO.size() != ids.size()) {
                    return LanguageBundleUtils.getString(locale, "message.dish.id.notExist");
                }
            }
        }
        return null;
    }
}
