/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.RestaurantDTO;
import com.dungnv.streetfood.model.Restaurant;
import com.dungnv.streetfood.dao.RestaurantDAO;
import com.dungnv.streetfood.dto.CommentDTO;
import com.dungnv.streetfood.dto.ImgDTO;
import com.dungnv.streetfood.dto.RestaurantArticleDTO;
import com.dungnv.streetfood.dto.RestaurantDishDetailDTO;
import com.dungnv.streetfood.dto.RestaurantLanguageDTO;
import com.dungnv.streetfood.dto.TagRestaurantDTO;
import com.dungnv.streetfood.model.RestaurantLanguage;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import com.dungnv.vfw5.base.utils.Constants;
import com.dungnv.vfw5.base.utils.DataUtil;
import com.dungnv.vfw5.base.utils.DateTimeUtils;
import com.dungnv.vfw5.base.utils.LanguageBundleUtils;
import com.dungnv.vfw5.base.utils.NumberUtils;
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
        ResultDTO result = new ResultDTO();
        Long ids = Long.valueOf(id);

        List<ConditionBean> lstCondition = new ArrayList<ConditionBean>();
        lstCondition.add(new ConditionBean(
                RestaurantLanguageDTO.RESTAURANT_ID,
                ParamUtils.OP_EQUAL,
                String.valueOf(id),
                ParamUtils.TYPE_NUMBER));

        // Delete language
        String resultDeleteLanguage = gettDAO().delete(RestaurantLanguageDTO.MODEL_NAME, lstCondition);
        if (!ParamUtils.SUCCESS.equals(resultDeleteLanguage)
                && !ParamUtils.FAIL.equals(resultDeleteLanguage)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(resultDeleteLanguage);
            return result;
        }

        // Delete Tag
        lstCondition.clear();
        lstCondition.add(new ConditionBean(
                TagRestaurantDTO.RESTAURANT_ID,
                ParamUtils.OP_EQUAL,
                String.valueOf(id),
                ParamUtils.TYPE_NUMBER));
        String resultDeleteTag = gettDAO().delete(TagRestaurantDTO.MODEL_NAME, lstCondition);
        if (!ParamUtils.SUCCESS.equals(resultDeleteTag)
                && !ParamUtils.FAIL.equals(resultDeleteTag)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(resultDeleteTag);
            return result;
        }

        // Delete IMG
        lstCondition.clear();
        lstCondition.add(new ConditionBean(
                ImgDTO.RESTAURANT_ID,
                ParamUtils.OP_EQUAL,
                String.valueOf(id),
                ParamUtils.TYPE_NUMBER));
        String resultDeleteImg = gettDAO().delete(ImgDTO.MODEL_NAME, lstCondition);
        if (!ParamUtils.SUCCESS.equals(resultDeleteImg)
                && !ParamUtils.FAIL.equals(resultDeleteImg)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(resultDeleteImg);
            return result;
        }

        // Delete COMMENT
        lstCondition.clear();
        lstCondition.add(new ConditionBean(
                CommentDTO.RESTAURANT_ID,
                ParamUtils.OP_EQUAL,
                String.valueOf(id),
                ParamUtils.TYPE_NUMBER));
        String resultDeleteComment = gettDAO().delete(CommentDTO.MODEL_NAME, lstCondition);
        if (!ParamUtils.SUCCESS.equals(resultDeleteComment)
                && !ParamUtils.FAIL.equals(resultDeleteComment)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(resultDeleteComment);
            return result;
        }

        // Delete Restaurant DISH
        lstCondition.clear();
        lstCondition.add(new ConditionBean(
                RestaurantDishDetailDTO.RESTAURANT_ID,
                ParamUtils.OP_EQUAL,
                String.valueOf(id),
                ParamUtils.TYPE_NUMBER));
        String resultDeleteRestaurantDish = gettDAO().delete(RestaurantDishDetailDTO.MODEL_NAME, lstCondition);
        if (!ParamUtils.SUCCESS.equals(resultDeleteRestaurantDish)
                && !ParamUtils.FAIL.equals(resultDeleteRestaurantDish)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(resultDeleteRestaurantDish);
            return result;
        }
        
         // Delete Restaurant Article
        lstCondition.clear();
        lstCondition.add(new ConditionBean(
                RestaurantArticleDTO.RESTAURANT_ID,
                ParamUtils.OP_EQUAL,
                String.valueOf(id),
                ParamUtils.TYPE_NUMBER));
        String resultDeleteRestaurantArticle = gettDAO().delete(RestaurantArticleDTO.MODEL_NAME, lstCondition);
        if (!ParamUtils.SUCCESS.equals(resultDeleteRestaurantArticle)
                && !ParamUtils.FAIL.equals(resultDeleteRestaurantArticle)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(resultDeleteRestaurantArticle);
            return result;
        }

        // delete model
        String resultDelete = delete(ids);
        if (!ParamUtils.SUCCESS.equals(resultDelete)
                && !ParamUtils.FAIL.equals(resultDelete)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(resultDelete);
            return result;
        }
        result.setMessage(ParamUtils.SUCCESS);
        return result;
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

        if (dto.getAddress() != null && dto.getAddress().length() > 255) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.address.overLength.255");
        }
        if (dto.getIntroduce() != null && dto.getIntroduce().length() > 65000) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.introduce.overLength.65000");
        }

        if (dto.getViewCount() != null && !StringUtils.isInteger(dto.getViewCount())) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.viewCount.invalid");
        }

        if (dto.getCommentCount() != null && !StringUtils.isInteger(dto.getCommentCount())) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.commentCount.invalid");
        }

        if (dto.getShareCount() != null && !StringUtils.isInteger(dto.getShareCount())) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.shareCount.invalid");
        }

        if (dto.getRating() != null && !StringUtils.isInteger(dto.getRating())) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.rating.invalid");
        }

        if (StringUtils.isNullOrEmpty(dto.getRestaurantStatus())) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.status.null");
        }

        if (!"1".equals(dto.getRestaurantStatus()) && !"0".equals(dto.getRestaurantStatus())) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.status.invalid");
        }

        if (dto.getLon() != null && !StringUtils.isLongtitude(dto.getLon())) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.lon.invalid");
        }

        if (dto.getLat() != null && !StringUtils.isLatitude(dto.getLat())) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.lat.invalid");
        }

        if (dto.getVideoUrl() != null && dto.getVideoUrl().length() > 255) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.videoUrl.overLength.255");
        }

        if (dto.getPhoneNumber() != null && dto.getPhoneNumber().length() > 100) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.phoneNumber.overLength.100");
        }

        if (dto.getSiteUrl() != null && dto.getSiteUrl().length() > 100) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.siteUrl.overLength.100");
        }

        if (!StringUtils.isNullOrEmpty(dto.getCarParking())//
                && !"1".equals(dto.getCarParking())//
                && !"0".equals(dto.getCarParking())) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.carParking.invalid");
        }

        if (!StringUtils.isNullOrEmpty(dto.getMotobikeParking())//
                && !"1".equals(dto.getMotobikeParking())//
                && !"0".equals(dto.getMotobikeParking())) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.motobikeParking.invalid");
        }

        if (dto.getWaitingTime() != null && !StringUtils.isInteger(dto.getWaitingTime())) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.waitingTime.invalid");
        }

        if (dto.getPriceFromVn() != null && !NumberUtils.isDouble(dto.getPriceFromVn())) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.priceFromVn.invalid");
        }

        if (dto.getPriceToVn() != null && !NumberUtils.isDouble(dto.getPriceToVn())) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.priceToVn.invalid");
        }

        if (!StringUtils.isNullOrEmpty(dto.getPriceFromVn()) && !StringUtils.isNullOrEmpty(dto.getPriceToVn())) {
            Double priceFromVn = Double.valueOf(dto.getPriceFromVn());
            Double priceToVn = Double.valueOf(dto.getPriceToVn());

            if (priceFromVn > priceToVn) {
                return LanguageBundleUtils.getString(locale, "message.restaurant.priceToVn.largerThanPriceFromVn");
            }
        }

        if (dto.getPriceFromEn() != null && !NumberUtils.isDouble(dto.getPriceFromEn())) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.priceFromEn.invalid");
        }

        if (dto.getPriceToEn() != null && !NumberUtils.isDouble(dto.getPriceToEn())) {
            return LanguageBundleUtils.getString(locale, "message.restaurant.priceToEn.invalid");
        }

        if (!StringUtils.isNullOrEmpty(dto.getPriceFromEn()) && !StringUtils.isNullOrEmpty(dto.getPriceToEn())) {
            Double priceFromEn = Double.valueOf(dto.getPriceFromEn());
            Double priceToEn = Double.valueOf(dto.getPriceToEn());

            if (priceFromEn > priceToEn) {
                return LanguageBundleUtils.getString(locale, "message.restaurant.priceToEn.largerThanPriceFromEn");
            }
        }

//    private String capacity;
//    private Date operatingTimeStart;
//    private Date operatingTimeEnd;
        return null;
    }
}
