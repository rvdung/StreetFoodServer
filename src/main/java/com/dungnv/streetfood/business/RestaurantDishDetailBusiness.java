/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.model.RestaurantDishDetail;
import com.dungnv.streetfood.dao.RestaurantDishDetailDAO;
import com.dungnv.streetfood.dto.RestaurantDTO;
import com.dungnv.streetfood.dto.RestaurantDishDetailDTO;
import com.dungnv.streetfood.dto.DishDTO;
import com.dungnv.streetfood.model.Restaurant;
import com.dungnv.streetfood.model.Dish;
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
@Service("restaurantDishDetailBusiness")
@Transactional
public class RestaurantDishDetailBusiness extends BaseFWServiceImpl<RestaurantDishDetailDAO, RestaurantDishDetailDTO, RestaurantDishDetail> implements RestaurantDishDetailBusinessInterface {

    @Autowired
    private RestaurantDishDetailDAO restaurantDishDetailDAO;
    @Autowired
    private RestaurantBusinessInterface restaurantBusiness;
    @Autowired
    private DishBusinessInterface dishBusiness;

    enum TYPE {
        DISHES_TO_ARTICLE, ARTICLES_TO_DISH
    }

    public RestaurantDishDetailBusiness() {
        tModel = new RestaurantDishDetail();
        tDAO = restaurantDishDetailDAO;
    }

    @Override
    public RestaurantDishDetailDAO gettDAO() {
        return restaurantDishDetailDAO;
    }

    public RestaurantDishDetailBusiness(Session session) {
        this.session = session;
        tModel = new RestaurantDishDetail();
        tDAO = restaurantDishDetailDAO;
    }

    @Override
    public List<RestaurantDTO> getListRestaurantByDish(String userName, String localeCode, String countryCode, String token, String id) {
        SQLQuery query = gettDAO().getSession().createSQLQuery("select c.id, c.name from restaurant c "
                + " inner join restaurant_dish_detail l on c.id = l.restaurant_id"
                + " where l.dish_id = ? ");
        query.addScalar("id", StringType.INSTANCE);
        query.addScalar("name", StringType.INSTANCE);
        query.setLong(0, Long.valueOf(id));
        query.setResultTransformer(Transformers.aliasToBean(RestaurantDTO.class));
        return query.list();
    }

    @Override
    public List<DishDTO> getListDishByRestaurant(String userName, String localeCode, String countryCode, String token, String id) {
        SQLQuery query = gettDAO().getSession().createSQLQuery("select c.id, c.name from dish c "
                + " inner join restaurant_dish_detail l on c.id =l.dish_id"
                + " where l.restaurant_id = ? ");
        query.addScalar("id", StringType.INSTANCE);
        query.addScalar("name", StringType.INSTANCE);
        query.setLong(0, Long.valueOf(id));
        query.setResultTransformer(Transformers.aliasToBean(DishDTO.class));
        return query.list();
    }

    @Override
    public ResultDTO insertListDishToRestaurant(String userName, String localeCode, String countryCode, String token, String id, List<String> list) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);

        if (list != null) {
            list = DataUtil.removeDuplicateString(list, Boolean.TRUE);
        }

        String validate = validate(locale, id, list, RestaurantDishDetailBusiness.TYPE.DISHES_TO_ARTICLE);
        if (!StringUtils.isNullOrEmpty(validate)) {
            return rollBackTransaction(validate);
        }

        Map<String, RestaurantDishDetailDTO> mapcurrentDish = new HashMap<String, RestaurantDishDetailDTO>();

        RestaurantDishDetailDTO searchDTO = new RestaurantDishDetailDTO();
        searchDTO.setRestaurantId(id);
        List<RestaurantDishDetailDTO> ListcurrentDish = search(searchDTO, 0, 0, "ASC", "id");

        for (RestaurantDishDetailDTO dish : ListcurrentDish) {
            mapcurrentDish.put(dish.getDishId(), dish);
        }

        if (list != null) {
            for (String dishId : list) {
                if (mapcurrentDish.containsKey(dishId)) {
                    mapcurrentDish.remove(dishId);
                } else {
                    RestaurantDishDetailDTO dto = new RestaurantDishDetailDTO();
                    dto.setDishId(dishId);
                    dto.setRestaurantId(id);
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
    public ResultDTO insertListRestaurantToDish(String userName, String localeCode, String countryCode, String token, String id, List<String> list) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);

        if (list != null) {
            list = DataUtil.removeDuplicateString(list, Boolean.TRUE);
        }

        String validate = validate(locale, id, list, RestaurantDishDetailBusiness.TYPE.ARTICLES_TO_DISH);
        if (!StringUtils.isNullOrEmpty(validate)) {
            return rollBackTransaction(validate);
        }

        Map<String, RestaurantDishDetailDTO> mapcurrentRestaurant = new HashMap<>();

        RestaurantDishDetailDTO searchDTO = new RestaurantDishDetailDTO();
        searchDTO.setDishId(id);
        List<RestaurantDishDetailDTO> ListcurrentRestaurant = search(searchDTO, 0, 0, "ASC", "id");

        for (RestaurantDishDetailDTO restaurant : ListcurrentRestaurant) {
            mapcurrentRestaurant.put(restaurant.getRestaurantId(), restaurant);
        }

        if (list != null) {
            for (String restaurantId : list) {
                if (mapcurrentRestaurant.containsKey(restaurantId)) {
                    mapcurrentRestaurant.remove(restaurantId);
                } else {
                    RestaurantDishDetailDTO dto = new RestaurantDishDetailDTO();
                    dto.setDishId(id);
                    dto.setRestaurantId(restaurantId);
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

    private String validate(Locale locale, String id, List<String> ids, RestaurantDishDetailBusiness.TYPE type) {
        if (type.equals(RestaurantDishDetailBusiness.TYPE.ARTICLES_TO_DISH)) {
            if (StringUtils.isNullOrEmpty(id)) {
                return LanguageBundleUtils.getString(locale, "message.dish.id.null");
            }

            Dish model = dishBusiness.findById(Long.valueOf(id));

            if (model == null) {
                return LanguageBundleUtils.getString(locale, "message.dish.id.notExist");
            }

            if (ids != null && !ids.isEmpty()) {
                List<ConditionBean> lstConditionBean = new ArrayList<>();
                lstConditionBean.add(new ConditionBean(RestaurantDTO.ID, ParamUtils.OP_IN, DataUtil.formatInputList(ids), ParamUtils.TYPE_NUMBER));
                List<RestaurantDTO> listDTO = restaurantBusiness.searchByConditionBean(lstConditionBean, 0, 0, "ASC", "id");

                if (listDTO == null || listDTO.size() != ids.size()) {
                    return LanguageBundleUtils.getString(locale, "message.restaurant.id.notExist");
                }
            }
        } else {
            if (StringUtils.isNullOrEmpty(id)) {
                return LanguageBundleUtils.getString(locale, "message.restaurant.id.null");
            }

            Restaurant model = restaurantBusiness.findById(Long.valueOf(id));

            if (model == null) {
                return LanguageBundleUtils.getString(locale, "message.restaurant.id.notExist");
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
