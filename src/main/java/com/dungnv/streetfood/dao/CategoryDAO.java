
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.dao;

import com.dungnv.streetfood.dto.CategoryDTO;
import com.dungnv.vfw5.base.dao.BaseFWDAOImpl;
import com.dungnv.streetfood.model.Category;
import com.dungnv.vfw5.base.utils.QueryUtil;
import com.dungnv.vfw5.base.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/22/2016 9:48 PM
 */
@Repository("categoryDAO")
public class CategoryDAO extends BaseFWDAOImpl<Category, Long> {

    public CategoryDAO() {
        this.model = new Category();
    }

    public CategoryDAO(Session session) {
        this.session = session;
    }

    public List<CategoryDTO> getListCategoryDTOLess(CategoryDTO categoryDTO, int rowStart, int maxRow, boolean isCount, String sortType, String sortFieldList) {
        StringBuilder sbQuery = new StringBuilder();
        List<Object> listParam = new ArrayList<>();
        List<Type> listType = new ArrayList<>();

        if (isCount) {
            sbQuery.append(" select count(c.id) as id from Category c where 1=1 ");
        } else {
            sbQuery.append(" select c.id ");
            sbQuery.append(" , c.name");
            if (categoryDTO == null || !"1".equals(categoryDTO.getIsGetOnlyIdentified())) {
                sbQuery.append(" , c.description");
                sbQuery.append(" , c.category_status categoryStatus");
                sbQuery.append(" , g.id imageId");
                sbQuery.append(" , g.url imageUrl");
            }

            sbQuery.append(" from Category c ");
            sbQuery.append(" left outer join img g on c.id = g.dish_group_id and g.orders = 1  ");
            sbQuery.append(" where 1=1");
        }

        if (categoryDTO != null) {
            StringUtils.trimString(categoryDTO, false);

            if (!StringUtils.isNullOrEmpty(categoryDTO.getId())) {
                sbQuery.append(" AND  c.id = ?");
                listParam.add(Long.valueOf(categoryDTO.getId()));
                listType.add(LongType.INSTANCE);
            }
            
            if (!StringUtils.isNullOrEmpty(categoryDTO.getDishId())) {
                sbQuery.append(" AND c.id in ( select category_id from category_dish where dish_id = ? ) ");
                listParam.add(Long.valueOf(categoryDTO.getDishId()));
                listType.add(LongType.INSTANCE);
            }

            if (!StringUtils.isNullOrEmpty(categoryDTO.getNotDishId())) {
                sbQuery.append(" AND c.id not in ( select category_id from category_dish where dish_id = ? ) ");
                listParam.add(Long.valueOf(categoryDTO.getNotDishId()));
                listType.add(LongType.INSTANCE);
            }

            if (!StringUtils.isNullOrEmpty(categoryDTO.getName())) {
                sbQuery.append(" AND lower(c.name) like ? ");
                listParam.add("%" + categoryDTO.getName().toLowerCase() + "%");
                listType.add(StringType.INSTANCE);
            }

            if (!StringUtils.isNullOrEmpty(categoryDTO.getCategoryStatus())) {
                sbQuery.append(" AND c.category_status = ? ");
                listParam.add(Long.valueOf(categoryDTO.getCategoryStatus()));
                listType.add(LongType.INSTANCE);
            }

            if (!StringUtils.isNullOrEmpty(categoryDTO.getDescription())) {
                sbQuery.append(" AND lower(c.description) like ? ");
                listParam.add("%" + categoryDTO.getDescription().toLowerCase() + "%");
                listType.add(StringType.INSTANCE);
            }

            if (categoryDTO.getListTag() != null && !categoryDTO.getListTag().isEmpty()) {
                sbQuery.append(" AND  c.id in (select category_id from tag_category where tag_id in ");
                sbQuery.append(QueryUtil.getParameterHolderString(categoryDTO.getListTag().size()));
                sbQuery.append(" )");
                List<String> listTag = categoryDTO.getListTag();
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

        SQLQuery query = getSession().createSQLQuery(sbQuery.toString());
        query.addScalar("id", StringType.INSTANCE);
        if (!isCount) {
            query.addScalar("name", StringType.INSTANCE);
            if (categoryDTO == null || !"1".equals(categoryDTO.getIsGetOnlyIdentified())) {
                query.addScalar("description", StringType.INSTANCE);
                query.addScalar("categoryStatus", StringType.INSTANCE);
                query.addScalar("imageId", StringType.INSTANCE);
                query.addScalar("imageUrl", StringType.INSTANCE);
            }
        }

        query.setResultTransformer(Transformers.aliasToBean(CategoryDTO.class));

        for (int i = 0; i < listParam.size(); i++) {
            query.setParameter(i, listParam.get(i), listType.get(i));
        }

        List<CategoryDTO> list = query.list();
        StringUtils.escapeHTMLString(list);
        return list;
    }
}
