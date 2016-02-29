/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.RestaurantDTO;
import com.dungnv.streetfood.model.Restaurant;
import com.dungnv.streetfood.dao.RestaurantDAO;
import com.dungnv.streetfood.dto.ImgDTO;
import com.dungnv.streetfood.dto.RestaurantLanguageDTO;
import com.dungnv.streetfood.model.RestaurantLanguage;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import com.dungnv.vfw5.base.utils.Constants;
import com.dungnv.vfw5.base.utils.DataUtil;
import com.dungnv.vfw5.base.utils.DateTimeUtils;
import com.dungnv.vfw5.base.utils.LanguageBundleUtils;
import com.dungnv.vfw5.base.utils.ParamUtils;
import com.dungnv.vfw5.base.utils.QueryUtil;
import com.dungnv.vfw5.base.utils.StringUtils;
import org.hibernate.Session;
import java.util.*;
import javax.transaction.Transactional;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 *
 * @author dungnv
 * @version 1.0
 * @since 1/27/2016 11:45 PM
 */
@Service("restaurantBusiness")
@Transactional
public class RestaurantBusiness extends BaseFWServiceImpl<RestaurantDAO, RestaurantDTO, Restaurant> implements RestaurantBusinessInterface {

    @Autowired
    private RestaurantDAO restaurantDAO;
    @Autowired
    private RestaurantLanguageBusinessInterface restaurantLanguageBusiness;
    @Autowired
    private TagRestaurantBusinessInterface tagRestaurantBusiness;
    @Autowired
    private ImgBusinessInterface imgBusiness;

    public RestaurantBusiness() {
        tModel = new Restaurant();
        tDAO = restaurantDAO;
    }

    @Override
    public RestaurantDAO gettDAO() {
        return restaurantDAO;
    }

    public RestaurantBusiness(Session session) {
        this.session = session;
        tModel = new Restaurant();
        tDAO = restaurantDAO;
    }

    @Override
    public ResultDTO insertRestaurant(String userName, String localeCode, String countryCode, String token, RestaurantDTO dto) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);

        String validate = validate(locale, dto, Constants.ACTION_TYPE.INSERT);
        if (!StringUtils.isNullOrEmpty(validate)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(validate);
            return result;
        }
        String currDate = null;
        String currDateGMT = null;
        try {
            currDate = DateTimeUtils.getSysDateTime(false);
        } catch (Exception ex) {
        }
        try {
            currDateGMT = DateTimeUtils.getSysDateTime(true);
        } catch (Exception ex) {

        }
        dto.setRestaurantCreateTime(currDate);
        dto.setRestaurantUpdateTime(currDate);
        dto.setRestaurantCreateTimeGmt(currDateGMT);
        dto.setRestaurantUpdateTimeGmt(currDateGMT);
        if (StringUtils.isNullOrEmpty(dto.getCommentCount())) {
            dto.setCommentCount("0");
        }
        if (StringUtils.isNullOrEmpty(dto.getRestaurantStatus())) {
            dto.setRestaurantStatus("1");
        }
        if (StringUtils.isNullOrEmpty(dto.getRating())) {
            dto.setRating("0");
        }
        if (StringUtils.isNullOrEmpty(dto.getShareCount())) {
            dto.setShareCount("0");
        }
        if (StringUtils.isNullOrEmpty(dto.getViewCount())) {
            dto.setViewCount("0");
        }

        result = createObject(dto);
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }

        dto.setId(result.getId());

        //<editor-fold defaultstate="collapsed" desc="save language">
        // Ghi danh sách ngôn ngữ
        if (dto.getListLanguage() != null && !dto.getListLanguage().isEmpty()) {
            for (RestaurantLanguageDTO langDTO : dto.getListLanguage()) {
                langDTO.setRestaurantId(dto.getId());
                result = restaurantLanguageBusiness.insertRestaurantLanguage(userName, localeCode, countryCode, token, langDTO);
                if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                    TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                    return result;
                }
            }
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="save tag">
        result = tagRestaurantBusiness.insertTagRestaurant(userName, localeCode, countryCode, token, Long.valueOf(dto.getId()), dto.getListTag());
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="save img">
        result = imgBusiness.attachImg(userName, localeCode, countryCode, token, dto.getListImgUrl(), dto.getId(), Constants.OBJECT_TYPE.RESTAURANT);
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }
//</editor-fold>

        return result;
    }

    @Override
    public ResultDTO updateRestaurant(String userName, String localeCode, String countryCode, String token, RestaurantDTO dto) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);

        String validate = validate(locale, dto, Constants.ACTION_TYPE.UPDATE);
        if (!StringUtils.isNullOrEmpty(validate)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(validate);
            return result;
        }
        String currDate = null;
        String currDateGMT = null;
        try {
            currDate = DateTimeUtils.getSysDateTime(false);
        } catch (Exception ex) {
        }
        try {
            currDateGMT = DateTimeUtils.getSysDateTime(true);
        } catch (Exception ex) {

        }
        dto.setRestaurantUpdateTime(currDate);
        dto.setRestaurantUpdateTimeGmt(currDateGMT);

        result.setMessage(update(dto));
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }

        //<editor-fold defaultstate="collapsed" desc="save language">
        // Ghi danh sách ngôn ngữ
        result = restaurantLanguageBusiness.insertRestaurantLanguage(userName, localeCode, countryCode, token, dto.getId(), dto.getListLanguage());
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="save tag">
        result = tagRestaurantBusiness.insertTagRestaurant(userName, localeCode, countryCode, token, Long.valueOf(dto.getId()), dto.getListTag());
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="save img">
        result = imgBusiness.attachImg(userName, localeCode, countryCode, token, dto.getListImgUrl(), dto.getId(), Constants.OBJECT_TYPE.RESTAURANT);
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }
//</editor-fold>

        return result;
    }

    @Override
    public ResultDTO deleteRestaurant(String userName, String localeCode, String countryCode, String token, String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultDTO activeRestaurant(String userName, String localeCode, String countryCode, String token, String id, Boolean active) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RestaurantDTO> getListRestaurantDTOLess(String userName, String localeCode, String countryCode, String token, RestaurantDTO dto, int rowStart, int maxRow, boolean isCount, String sortType, String sortFieldList) {
        StringBuilder sbQuery = new StringBuilder();
        List<Object> listParam = new ArrayList<>();
        List<Type> listType = new ArrayList<>();

        if (isCount) {
            sbQuery.append(" select count(c.id) as id from restaurant c where 1=1 ");
        } else {
            sbQuery.append(" select ");
            sbQuery.append(" r.id");
            sbQuery.append(" , r.name");
            sbQuery.append(" , r.address");
            sbQuery.append(" , r.restaurant_status restaurantStatus");
            sbQuery.append(" , r.view_count viewCount");
            sbQuery.append(" , r.comment_count commentCount");
            sbQuery.append(" , r.share_count shareCount");
            sbQuery.append(" , r.rating");
            sbQuery.append(" , g.id imageId");
            sbQuery.append(" , g.url imageUrl");
            sbQuery.append(" from restaurant r left outer join img g on r.id = g.restaurant_id and g.orders = 1");
            sbQuery.append(" where 1 = 1");
        }

        if (dto != null) {
            StringUtils.trimString(dto, false);

            if (!StringUtils.isNullOrEmpty(dto.getId())) {
                sbQuery.append(" AND  r.id = ?");
                listParam.add(Long.valueOf(dto.getId()));
                listType.add(LongType.INSTANCE);
            }

            if (!StringUtils.isNullOrEmpty(dto.getName())) {
                sbQuery.append(" AND lower(r.name) like ? ");
                listParam.add("%" + dto.getName().toLowerCase() + "%");
                listType.add(StringType.INSTANCE);
            }

            if (!StringUtils.isNullOrEmpty(dto.getRestaurantStatus())) {
                sbQuery.append(" AND r.restaurant_status = ? ");
                listParam.add(Long.valueOf(dto.getRestaurantStatus()));
                listType.add(LongType.INSTANCE);
            }

            if (!StringUtils.isNullOrEmpty(dto.getAddress())) {
                sbQuery.append(" AND lower(r.address) like ? ");
                listParam.add("%" + dto.getAddress().toLowerCase() + "%");
                listType.add(StringType.INSTANCE);
            }

            if (!StringUtils.isNullOrEmpty(dto.getIntroduce())) {
                sbQuery.append(" AND lower(r.introduce) like ? ");
                listParam.add("%" + dto.getIntroduce().toLowerCase() + "%");
                listType.add(StringType.INSTANCE);
            }

            if (dto.getListTag() != null && !dto.getListTag().isEmpty()) {
                sbQuery.append(" AND  r.id in (select restaurant_id from tag_restaurant where tag_id in ");
                sbQuery.append(QueryUtil.getParameterHolderString(dto.getListTag().size()));
                sbQuery.append(" )");
                List<String> listTag = dto.getListTag();
                for (String tagId : listTag) {
                    listParam.add(Long.valueOf(tagId));
                    listType.add(LongType.INSTANCE);
                }
            }
        }

        if (!isCount) {
            sbQuery.append(" order by r.name DESC");
            if (maxRow != 0) {
                sbQuery.append(" limit ?, ?");
                listParam.add(rowStart);
                listType.add(IntegerType.INSTANCE);
                listParam.add(maxRow);
                listType.add(IntegerType.INSTANCE);
            }

        }

        SQLQuery query = gettDAO().getSession().createSQLQuery(sbQuery.toString());
        query.addScalar("id", StringType.INSTANCE);
        if (!isCount) {
            query.addScalar("name", StringType.INSTANCE);
            query.addScalar("address", StringType.INSTANCE);
            query.addScalar("restaurantStatus", StringType.INSTANCE);
            query.addScalar("viewCount", StringType.INSTANCE);
            query.addScalar("commentCount", StringType.INSTANCE);
            query.addScalar("shareCount", StringType.INSTANCE);
            query.addScalar("rating", StringType.INSTANCE);
            query.addScalar("imageId", StringType.INSTANCE);
            query.addScalar("imageUrl", StringType.INSTANCE);
        }

        query.setResultTransformer(Transformers.aliasToBean(RestaurantDTO.class));

        for (int i = 0; i < listParam.size(); i++) {
            query.setParameter(i, listParam.get(i), listType.get(i));
        }

        List<RestaurantDTO> list = query.list();
        return list;
    }

    @Override
    public RestaurantDTO getRestaurantDetail(String userName, String localeCode, String countryCode, String token, String id) {
        RestaurantDTO result = null;
        Restaurant model;

        model = findById(Long.valueOf(id));
        if (model != null) {
            result = model.toDTO();

            // get Language
            List<ConditionBean> lstCondition = new ArrayList<ConditionBean>();
            lstCondition.add(new ConditionBean(
                    RestaurantLanguage.RESTAURANT_ID,
                    ParamUtils.OP_EQUAL,
                    String.valueOf(id),
                    ParamUtils.TYPE_NUMBER));
            List<RestaurantLanguageDTO> listLanguage = restaurantLanguageBusiness.searchByConditionBean(lstCondition, 0, 0, "ASC", "id");
            result.setListLanguage(listLanguage);

            // get Tag
            List<String> listTag = tagRestaurantBusiness.getTagsListByRestaurant(userName, localeCode, countryCode, token, id);
            result.setListTag(listTag);

            // get img
            lstCondition = new ArrayList<>();
            lstCondition.add(new ConditionBean(
                    ImgDTO.RESTAURANT_ID,
                    ParamUtils.OP_EQUAL,
                    String.valueOf(id),
                    ParamUtils.TYPE_NUMBER));
            List<ImgDTO> listImg = imgBusiness.searchByConditionBean(lstCondition, 0, 0, "ASC", "order");
            List<String> listImgUrl = new ArrayList<>();
            if (listImg != null && !listImg.isEmpty()) {
                result.setImageId(listImg.get(0).getId());
                result.setImageUrl(listImg.get(0).getUrl());
                for (int i = 0; i < listImg.size(); i++) {
                    listImgUrl.add(listImg.get(i).getUrl());
                }
                result.setListImgUrl(listImgUrl);
            }
            // get img

        }
        return result;
    }

    private String validate(Locale locale, RestaurantDTO dto, String action) {
        if (dto == null) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.model.null");
        }
        if (dto.getName() == null) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.name.null");
        }
        if (dto.getName().length() > 255) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.name.overLength.255");
        }

        return null;
    }
}
