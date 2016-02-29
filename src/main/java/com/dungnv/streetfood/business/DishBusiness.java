/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.DishDTO;
import com.dungnv.streetfood.model.Dish;
import com.dungnv.streetfood.dao.DishDAO;
import com.dungnv.streetfood.dto.CategoryDTO;
import com.dungnv.streetfood.dto.DishLanguageDTO;
import com.dungnv.streetfood.dto.ImgDTO;
import com.dungnv.streetfood.model.DishLanguage;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import com.dungnv.vfw5.base.utils.Constants;
import com.dungnv.vfw5.base.utils.DataUtil;
import com.dungnv.vfw5.base.utils.DateTimeUtils;
import com.dungnv.vfw5.base.utils.LanguageBundleUtils;
import com.dungnv.vfw5.base.utils.ParamUtils;
import com.dungnv.vfw5.base.utils.QueryUtil;
import com.dungnv.vfw5.base.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.hibernate.Session;
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
 * @since 1/25/2016 10:01 PM
 */
@Service("dishBusiness")
@Transactional
public class DishBusiness extends BaseFWServiceImpl<DishDAO, DishDTO, Dish> implements DishBusinessInterface {

    @Autowired
    private DishDAO dishDAO;
    @Autowired
    private CategoryBusinessInterface categoryBusiness;
    @Autowired
    private CategoryDishBusinessInterface categoryDishBusiness;
    @Autowired
    private DishLanguageBusinessInterface dishLanguageBusiness;
    @Autowired
    private TagDishBusinessInterface tagDishBusiness;
    @Autowired
    private ImgBusinessInterface imgBusiness;

    public DishBusiness() {
        tModel = new Dish();
        tDAO = dishDAO;
    }

    @Override
    public DishDAO gettDAO() {
        return dishDAO;
    }

    public DishBusiness(Session session) {
        this.session = session;
        tModel = new Dish();
        tDAO = dishDAO;
    }

    @Override
    public ResultDTO insertDish(String userName, String localeCode, String countryCode, String token, DishDTO dto) {
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
        dto.setDishCreateTime(currDate);
        dto.setDishUpdateTime(currDate);
        dto.setDishCreateTimeGmt(currDateGMT);
        dto.setDishUpdateTimeGmt(currDateGMT);
        if (StringUtils.isNullOrEmpty(dto.getCommentCount())) {
            dto.setCommentCount("0");
        }
        if (StringUtils.isNullOrEmpty(dto.getDishStatus())) {
            dto.setDishStatus("1");
        }
        if (StringUtils.isNullOrEmpty(dto.getRating())) {
            dto.setRating("0");
        }
        if (StringUtils.isNullOrEmpty(dto.getShareCount())) {
            dto.setShareCount("0");
        }
        if (StringUtils.isNullOrEmpty(dto.getViewCount())) {
            dto.setShareCount("0");
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
            for (DishLanguageDTO langDTO : dto.getListLanguage()) {
                langDTO.setDishId(dto.getId());
                result = dishLanguageBusiness.insertDishLanguage(userName, localeCode, countryCode, token, langDTO);
                if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                    TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                    return result;
                }
            }
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="save tag">
        result = tagDishBusiness.insertTagDish(userName, localeCode, countryCode, token, Long.valueOf(dto.getId()), dto.getListTag());
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="save img">
        result = imgBusiness.attachImg(userName, localeCode, countryCode, token, dto.getListImgUrl(), dto.getId(), Constants.OBJECT_TYPE.DISH);
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }
//</editor-fold>

        return result;
    }

    @Override
    public ResultDTO updateDish(String userName, String localeCode, String countryCode, String token, DishDTO dto) {
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
        dto.setDishUpdateTime(currDate);
        dto.setDishUpdateTimeGmt(currDateGMT);

        result.setMessage(update(dto));
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }

        //<editor-fold defaultstate="collapsed" desc="save language">
        // Ghi danh sách ngôn ngữ
        result = dishLanguageBusiness.insertDishLanguage(userName, localeCode, countryCode, token, dto.getId(), dto.getListLanguage());
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="save tag">
        result = tagDishBusiness.insertTagDish(userName, localeCode, countryCode, token, Long.valueOf(dto.getId()), dto.getListTag());
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="save img">
        result = imgBusiness.attachImg(userName, localeCode, countryCode, token, dto.getListImgUrl(), dto.getId(), Constants.OBJECT_TYPE.DISH);
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }
//</editor-fold>

        return result;
    }

    @Override
    public ResultDTO deleteDish(String userName, String localeCode, String countryCode, String token, String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultDTO activeDish(String userName, String localeCode, String countryCode, String token, String id, Boolean active) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DishDTO> getListDishDTOLess(String userName, String localeCode, String countryCode, String token, DishDTO dto, int rowStart, int maxRow, boolean isCount, String sortType, String sortFieldList) {
        StringBuilder sbQuery = new StringBuilder();
        List<Object> listParam = new ArrayList<>();
        List<Type> listType = new ArrayList<>();

        if (isCount) {
            sbQuery.append(" select count(c.id) as id from dish c where 1=1 ");
        } else {
            sbQuery.append(" select c.id , c.name");
            sbQuery.append(" , c.short_description shortDescription");
            sbQuery.append(" , c.dish_status dishStatus");
            sbQuery.append(" , c.view_count viewCount");
            sbQuery.append(" , c.comment_count commentCount ");
            sbQuery.append(" , c.share_count shareCount");
            sbQuery.append(" , c.rating ");
            sbQuery.append(" , g.id imageId");
            sbQuery.append(" , g.url imageUrl");
            sbQuery.append(" from dish c left outer join img g on c.id = g.dish_id and g.orders = 1 ");
            sbQuery.append(" where 1=1");
        }

        if (dto != null) {
            StringUtils.trimString(dto, false);

            if (!StringUtils.isNullOrEmpty(dto.getId())) {
                sbQuery.append(" AND  c.id = ?");
                listParam.add(Long.valueOf(dto.getId()));
                listType.add(LongType.INSTANCE);
            }

            if (!StringUtils.isNullOrEmpty(dto.getName())) {
                sbQuery.append(" AND lower(c.name) like ? ");
                listParam.add("%" + dto.getName().toLowerCase() + "%");
                listType.add(StringType.INSTANCE);
            }

            if (!StringUtils.isNullOrEmpty(dto.getDishStatus())) {
                sbQuery.append(" AND c.category_status = ? ");
                listParam.add(Long.valueOf(dto.getDishStatus()));
                listType.add(LongType.INSTANCE);
            }

            if (!StringUtils.isNullOrEmpty(dto.getDishStatus())) {
                sbQuery.append(" AND c.dish_status = ? ");
                listParam.add(Long.valueOf(dto.getDishStatus()));
                listType.add(LongType.INSTANCE);
            }

            if (!StringUtils.isNullOrEmpty(dto.getShortDescription())) {
                sbQuery.append(" AND lower(c.description) like ? ");
                listParam.add("%" + dto.getShortDescription().toLowerCase() + "%");
                listType.add(StringType.INSTANCE);
            }

            if (!StringUtils.isNullOrEmpty(dto.getLongDescription())) {
                sbQuery.append(" AND lower(c.long_description) like ? ");
                listParam.add("%" + dto.getLongDescription().toLowerCase() + "%");
                listType.add(StringType.INSTANCE);
            }

            if (dto.getListTag() != null && !dto.getListTag().isEmpty()) {
                sbQuery.append(" AND  c.id in (select dish_id from tag_dish where tag_id in ");
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
            sbQuery.append(" order by c.name DESC");
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
            query.addScalar("shortDescription", StringType.INSTANCE);
            query.addScalar("dishStatus", StringType.INSTANCE);
            query.addScalar("viewCount", StringType.INSTANCE);
            query.addScalar("commentCount", StringType.INSTANCE);
            query.addScalar("shareCount", StringType.INSTANCE);
            query.addScalar("rating", StringType.INSTANCE);
            query.addScalar("imageId", StringType.INSTANCE);
            query.addScalar("imageUrl", StringType.INSTANCE);
        }

        query.setResultTransformer(Transformers.aliasToBean(DishDTO.class));

        for (int i = 0; i < listParam.size(); i++) {
            query.setParameter(i, listParam.get(i), listType.get(i));
        }

        List<DishDTO> list = query.list();
//        StringUtils.escapeHTMLString(list);
        return list;
    }

    @Override
    public DishDTO getDishDetail(String userName, String localeCode, String countryCode, String token, String id) {
        DishDTO result = null;
        Dish model;

        model = findById(Long.valueOf(id));
        if (model != null) {
            result = model.toDTO();

            // get Language
            List<ConditionBean> lstCondition = new ArrayList<ConditionBean>();
            lstCondition.add(new ConditionBean(
                    DishLanguage.DISH_ID,
                    ParamUtils.OP_EQUAL,
                    String.valueOf(id),
                    ParamUtils.TYPE_NUMBER));
            List<DishLanguageDTO> listDishLanguage = dishLanguageBusiness.searchByConditionBean(lstCondition, 0, 0, "ASC", "id");
            result.setListLanguage(listDishLanguage);

            // get list category
            List<CategoryDTO> listCategory = categoryDishBusiness.getListCategoryByDish(userName, localeCode, countryCode, token, id);
            result.setListCategory(listCategory);

            // get Tag
            List<String> listTag = tagDishBusiness.getTagsListByDish(userName, localeCode, countryCode, token, id);
            result.setListTag(listTag);

            // get img
            lstCondition = new ArrayList<>();
            lstCondition.add(new ConditionBean(
                    DishLanguage.DISH_ID,
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

    private String validate(Locale locale, DishDTO dto, String action) {
        if (dto == null) {
            return LanguageBundleUtils.getString(locale, "message.dish.model.null");
        }
        if (dto.getName() == null) {
            return LanguageBundleUtils.getString(locale, "message.dish.name.null");
        }
        if (dto.getName().length() > 255) {
            return LanguageBundleUtils.getString(locale, "message.dish.name.overLength.255");
        }

        return null;
    }

}
