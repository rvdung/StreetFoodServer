/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.business;

import com.dungnv.vfw5.base.service.BaseFWServiceImpl;
import com.dungnv.streetfood.dao.ArticleDAO;
import com.dungnv.streetfood.dto.ImgDTO;
import com.dungnv.streetfood.dto.ArticleDTO;
import com.dungnv.streetfood.dto.ArticleLanguageDTO;
import com.dungnv.streetfood.dto.RestaurantArticleDTO;
import com.dungnv.streetfood.dto.TagArticleDTO;
import com.dungnv.streetfood.model.Article;
import com.dungnv.streetfood.model.ArticleLanguage;
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
 * @since 1/21/2016 12:48 AM
 */
@Service("articleBusiness")
@Transactional
public class ArticleBusiness extends BaseFWServiceImpl<ArticleDAO, ArticleDTO, Article> implements ArticleBusinessInterface {

    @Autowired
    private ArticleDAO articleDAO;
    @Autowired
    private ArticleLanguageBusinessInterface articleLanguageBusiness;
    @Autowired
    private TagArticleBusinessInterface tagArticleBusiness;
    @Autowired
    private ImgBusinessInterface imgBusiness;

    public ArticleBusiness() {
        tModel = new Article();
        tDAO = articleDAO;
    }

    @Override
    public ArticleDAO gettDAO() {
        return articleDAO;
    }

    public ArticleBusiness(Session session) {
        this.session = session;
        tModel = new Article();
        tDAO = articleDAO;
    }

    @Override
    public ResultDTO insertArticle(String userName, String localeCode, String countryCode, String token, ArticleDTO dto) {
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
        dto.setUpdateTime(currDate);
        dto.setUpdateTimeGmt(currDateGMT);
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
        // Ghi danh sách ngôn ng?
        if (dto.getListLanguage() != null && !dto.getListLanguage().isEmpty()) {
            for (ArticleLanguageDTO langDTO : dto.getListLanguage()) {
                langDTO.setArticleId(dto.getId());
                result = articleLanguageBusiness.insertArticleLanguage(userName, localeCode, countryCode, token, langDTO);
                if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
                    TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
                    return result;
                }
            }
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="save tag">
        result = tagArticleBusiness.insertTagArticle(userName, localeCode, countryCode, token, Long.valueOf(dto.getId()), dto.getListTag());
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
    public ResultDTO updateArticle(String userName, String localeCode, String countryCode, String token, ArticleDTO dto) {
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
        dto.setUpdateTime(currDate);
        dto.setUpdateTimeGmt(currDateGMT);

        result.setMessage(update(dto));
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }

        //<editor-fold defaultstate="collapsed" desc="save language">
        // Ghi danh sách ngôn ng?
        result = articleLanguageBusiness.insertArticleLanguage(userName, localeCode, countryCode, token, dto.getId(), dto.getListLanguage());
        if (!ParamUtils.SUCCESS.equals(result.getMessage())) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            return result;
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="save tag">
        result = tagArticleBusiness.insertTagArticle(userName, localeCode, countryCode, token, Long.valueOf(dto.getId()), dto.getListTag());
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
    public ResultDTO deleteArticle(String userName, String localeCode, String countryCode, String token, String id) {
        ResultDTO result = new ResultDTO();
        Long ids = Long.valueOf(id);

        List<ConditionBean> lstCondition = new ArrayList<ConditionBean>();
        lstCondition.add(new ConditionBean(
                ArticleLanguageDTO.ARTICLE_ID,
                ParamUtils.OP_EQUAL,
                String.valueOf(id),
                ParamUtils.TYPE_NUMBER));

        // Delete language
        String resultDeleteLanguage = gettDAO().delete(ArticleLanguageDTO.MODEL_NAME, lstCondition);
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
                TagArticleDTO.ARTICLE_ID,
                ParamUtils.OP_EQUAL,
                String.valueOf(id),
                ParamUtils.TYPE_NUMBER));
        String resultDeleteTag = gettDAO().delete(TagArticleDTO.MODEL_NAME, lstCondition);
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
                ImgDTO.ARTICLE_ID,
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

        // Delete Restaurant Article
        lstCondition.clear();
        lstCondition.add(new ConditionBean(
                RestaurantArticleDTO.ARTICLE_ID,
                ParamUtils.OP_EQUAL,
                String.valueOf(id),
                ParamUtils.TYPE_NUMBER));
        String resultDeleteArticleArticle = gettDAO().delete(RestaurantArticleDTO.MODEL_NAME, lstCondition);
        if (!ParamUtils.SUCCESS.equals(resultDeleteArticleArticle)
                && !ParamUtils.FAIL.equals(resultDeleteArticleArticle)) {
            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
            result.setMessage(ParamUtils.FAIL);
            result.setKey(resultDeleteArticleArticle);
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
    public ResultDTO activeArticle(String userName, String localeCode, String countryCode, String token, String id, Boolean active) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ArticleDTO> getListArticleDTOLess(String userName, String localeCode, String countryCode, String token, ArticleDTO dto, int rowStart, int maxRow, boolean isCount, String sortType, String sortFieldList) {
        StringBuilder sbQuery = new StringBuilder();
        List<Object> listParam = new ArrayList<>();
        List<Type> listType = new ArrayList<>();

        if (isCount) {
            sbQuery.append(" select count(a.id) as id from article a where 1=1 ");
        } else {
            sbQuery.append(" select a.id");
            sbQuery.append(" , a.title");
            sbQuery.append(" , a.short_content shortContent");
            sbQuery.append(" , a.view_count viewCount");
            sbQuery.append(" , g.id imageId");
            sbQuery.append(" , g.url imageUrl");
            sbQuery.append("  from article a left outer join img g on a.id = g.article_id and g.orders = 1");
            sbQuery.append(" where 1 = 1");
        }

        if (dto != null) {
            StringUtils.trimString(dto, false);

            if (!StringUtils.isNullOrEmpty(dto.getId())) {
                sbQuery.append(" AND  a.id = ?");
                listParam.add(Long.valueOf(dto.getId()));
                listType.add(LongType.INSTANCE);
            }

            if (!StringUtils.isNullOrEmpty(dto.getTitle())) {
                sbQuery.append(" AND lower(a.title) like ? ");
                listParam.add("%" + dto.getTitle().toLowerCase() + "%");
                listType.add(StringType.INSTANCE);
            }
            if (!StringUtils.isNullOrEmpty(dto.getShortContent())) {
                sbQuery.append(" AND lower(a.short_content) like ? ");
                listParam.add("%" + dto.getShortContent().toLowerCase() + "%");
                listType.add(StringType.INSTANCE);
            }

            if (!StringUtils.isNullOrEmpty(dto.getContent())) {
                sbQuery.append(" AND lower(a.content) like ? ");
                listParam.add("%" + dto.getContent().toLowerCase() + "%");
                listType.add(StringType.INSTANCE);
            }

            if (dto.getListTag() != null && !dto.getListTag().isEmpty()) {
                sbQuery.append(" AND  a.id in (select article_id from tag_article where tag_id in ");
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
            sbQuery.append(" order by a.title DESC");
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
            query.addScalar("title", StringType.INSTANCE);
            query.addScalar("shortContent", StringType.INSTANCE);
            query.addScalar("viewCount", StringType.INSTANCE);
            query.addScalar("imageId", StringType.INSTANCE);
            query.addScalar("imageUrl", StringType.INSTANCE);
        }

        query.setResultTransformer(Transformers.aliasToBean(ArticleDTO.class));

        for (int i = 0; i < listParam.size(); i++) {
            query.setParameter(i, listParam.get(i), listType.get(i));
        }

        List<ArticleDTO> list = query.list();
        return list;
    }

    @Override
    public ArticleDTO getArticleDetail(String userName, String localeCode, String countryCode, String token, String id) {
        ArticleDTO result = null;
        Article model;

        model = findById(Long.valueOf(id));
        if (model != null) {
            result = model.toDTO();

            // get Language
            List<ConditionBean> lstCondition = new ArrayList<ConditionBean>();
            lstCondition.add(new ConditionBean(
                    ArticleLanguage.ARTILCE_ID,
                    ParamUtils.OP_EQUAL,
                    String.valueOf(id),
                    ParamUtils.TYPE_NUMBER));
            List<ArticleLanguageDTO> listLanguage = articleLanguageBusiness.searchByConditionBean(lstCondition, 0, 0, "ASC", "id");
            result.setListLanguage(listLanguage);

            // get Tag
            List<String> listTag = tagArticleBusiness.getTagsListByArticle(userName, localeCode, countryCode, token, id);
            result.setListTag(listTag);

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

    private String validate(Locale locale, ArticleDTO dto, String action) {
        if (dto == null) {
            return LanguageBundleUtils.getString(locale, "message.article.model.null");
        }
        if (dto.getTitle() == null) {
            return LanguageBundleUtils.getString(locale, "message.article.title.null");
        }
        if (dto.getTitle().length() > 255) {
            return LanguageBundleUtils.getString(locale, "message.article.title.overLength.255");
        }

        if (dto.getShortContent() != null && dto.getShortContent().length() > 255) {
            return LanguageBundleUtils.getString(locale, "message.article.shortContent.overLength.255");
        }

        if (dto.getContent() != null && dto.getContent().length() > 65000) {
            return LanguageBundleUtils.getString(locale, "message.article.content.overLength.65000");
        }

        if (dto.getViewCount() != null && !StringUtils.isInteger(dto.getViewCount())) {
            return LanguageBundleUtils.getString(locale, "message.article.viewCount.invalid");
        }

        return null;
    }
}
