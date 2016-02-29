/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dto.SlideShowDTO;
import com.dungnv.streetfood.model.SlideShow;
import com.dungnv.streetfood.dao.SlideShowDAO;
import com.dungnv.streetfood.dto.ImgDTO;
import com.dungnv.streetfood.dto.SlideShowLanguageDTO;
import com.dungnv.streetfood.model.SlideShowLanguage;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import com.dungnv.vfw5.base.utils.Constants;
import com.dungnv.vfw5.base.utils.DataUtil;
import com.dungnv.vfw5.base.utils.DateTimeUtils;
import com.dungnv.vfw5.base.utils.ParamUtils;
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
 * @since 1/27/2016 11:48 PM
 */
@Service("slideShowBusiness")
@Transactional
public class SlideShowBusiness extends BaseFWServiceImpl<SlideShowDAO, SlideShowDTO, SlideShow> implements SlideShowBusinessInterface {

    @Autowired
    private SlideShowDAO slideShowDAO;
    @Autowired
    private SlideShowLanguageBusinessInterface slideShowLanguageBusiness;
    @Autowired
    private ImgBusinessInterface imgBusiness;

    public SlideShowBusiness() {
        tModel = new SlideShow();
        tDAO = slideShowDAO;
    }

    @Override
    public SlideShowDAO gettDAO() {
        return slideShowDAO;
    }

    public SlideShowBusiness(Session session) {
        this.session = session;
        tModel = new SlideShow();
        tDAO = slideShowDAO;
    }

    @Override
    public ResultDTO insertSlideShow(String userName, String localeCode, String countryCode, String token, SlideShowDTO dto) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);

        String validate = validate(locale, dto, Constants.ACTION_TYPE.INSERT);
        if (!StringUtils.isNullOrEmpty(validate)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(validate);
            return result;
        }

        String currDateGMT = null;

        try {
            currDateGMT = DateTimeUtils.getSysDateTime(true);
        } catch (Exception ex) {

        }
        dto.setCreateDateGmt(currDateGMT);

        result = createObject(dto);
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }

        dto.setId(result.getId());

        //<editor-fold defaultstate="collapsed" desc="save language">
        // Ghi danh sách ngôn ng?
        if (dto.getListLanguage() != null && !dto.getListLanguage().isEmpty()) {
            for (SlideShowLanguageDTO langDTO : dto.getListLanguage()) {
                langDTO.setSlideShowId(dto.getId());
                result = slideShowLanguageBusiness.insertSlideShowLanguage(userName, localeCode, countryCode, token, langDTO);
                if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                    TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                    return result;
                }
            }
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="save img">
        result = imgBusiness.attachImg(userName, localeCode, countryCode, token, dto.getListImgUrl(), dto.getId(), Constants.OBJECT_TYPE.ARTICLE);
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }
//</editor-fold>

        return result;
    }

    @Override
    public ResultDTO updateSlideShow(String userName, String localeCode, String countryCode, String token, SlideShowDTO dto) {
        ResultDTO result = new ResultDTO();
        locale = DataUtil.getLocale(localeCode, countryCode);

        String validate = validate(locale, dto, Constants.ACTION_TYPE.UPDATE);
        if (!StringUtils.isNullOrEmpty(validate)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(validate);
            return result;
        }

        String currDateGMT = null;

        try {
            currDateGMT = DateTimeUtils.getSysDateTime(true);
        } catch (Exception ex) {

        }
        dto.setCreateDateGmt(currDateGMT);

        result.setMessage(update(dto));
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }

        //<editor-fold defaultstate="collapsed" desc="save language">
        // Ghi danh sách ngôn ng?
        result = slideShowLanguageBusiness.insertSlideShowLanguage(userName, localeCode, countryCode, token, dto.getId(), dto.getListLanguage());
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="save img">
        result = imgBusiness.attachImg(userName, localeCode, countryCode, token, dto.getListImgUrl(), dto.getId(), Constants.OBJECT_TYPE.ARTICLE);
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }
//</editor-fold>

        return result;
    }

    @Override
    public ResultDTO deleteSlideShow(String userName, String localeCode, String countryCode, String token, String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultDTO activeSlideShow(String userName, String localeCode, String countryCode, String token, String id, Boolean active) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SlideShowDTO> getListSlideShowDTOLess(String userName, String localeCode, String countryCode, String token, SlideShowDTO dto, int rowStart, int maxRow, boolean isCount, String sortType, String sortFieldList) {
        StringBuilder sbQuery = new StringBuilder();
        List<Object> listParam = new ArrayList<>();
        List<Type> listType = new ArrayList<>();

        if (isCount) {
            sbQuery.append(" select count(a.id) as id from slide_show a where 1=1 ");
        } else {
            sbQuery.append(" select a.id ");
            sbQuery.append(" , a.url");
            sbQuery.append(" , a.name");
            sbQuery.append(" , a.description");
            sbQuery.append(" , a.orders as 'order'");
            sbQuery.append(" , a.valid_from_gmt validFromGmt ");
            sbQuery.append(" , a.valid_to_gmt validToGmt");
            sbQuery.append(" , g.id imageId");
            sbQuery.append(" , g.url imageUrl");
            sbQuery.append(" from slide_show a left outer join img g on a.id = g.slide_show_id and g.orders = 1 ");
            sbQuery.append(" where 1=1");
        }

        if (dto != null) {
            StringUtils.trimString(dto, false);

            if (!StringUtils.isNullOrEmpty(dto.getId())) {
                sbQuery.append(" AND  a.id = ?");
                listParam.add(Long.valueOf(dto.getId()));
                listType.add(LongType.INSTANCE);
            }

            if (!StringUtils.isNullOrEmpty(dto.getName())) {
                sbQuery.append(" AND lower(a.name) like ? ");
                listParam.add("%" + dto.getName().toLowerCase() + "%");
                listType.add(StringType.INSTANCE);
            }
        }

        if (!isCount) {
            sbQuery.append(" order by a.orders DESC");
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
            
            query.addScalar("url", StringType.INSTANCE);
            query.addScalar("name", StringType.INSTANCE);
            query.addScalar("description", StringType.INSTANCE);
            query.addScalar("order", StringType.INSTANCE);
            query.addScalar("validFromGmt", StringType.INSTANCE);
            query.addScalar("validToGmt", StringType.INSTANCE);
            query.addScalar("imageId", StringType.INSTANCE);
            query.addScalar("imageUrl", StringType.INSTANCE);
        }

        query.setResultTransformer(Transformers.aliasToBean(SlideShowDTO.class));

        for (int i = 0; i < listParam.size(); i++) {
            query.setParameter(i, listParam.get(i), listType.get(i));
        }

        List<SlideShowDTO> list = query.list();
        return list;
    }

    @Override
    public SlideShowDTO getSlideShowDetail(String userName, String localeCode, String countryCode, String token, String id) {
        SlideShowDTO result = null;
        SlideShow model;

        model = findById(Long.valueOf(id));
        if (model != null) {
            result = model.toDTO();

            // get Language
            List<ConditionBean> lstCondition = new ArrayList<ConditionBean>();
            lstCondition.add(new ConditionBean(
                    SlideShowLanguage.SLIDESHOW_ID,
                    ParamUtils.OP_EQUAL,
                    String.valueOf(id),
                    ParamUtils.TYPE_NUMBER));
            List<SlideShowLanguageDTO> listLanguage = slideShowLanguageBusiness.searchByConditionBean(lstCondition, 0, 0, "ASC", "id");
            result.setListLanguage(listLanguage);

            // get img
            lstCondition = new ArrayList<>();
            lstCondition.add(new ConditionBean(
                    ImgDTO.ARTICLE_ID,
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
        }
        return result;
    }

    private String validate(Locale locale, SlideShowDTO dto, String INSERT) {
        return null;
    }
}
