/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.vfw5.base.service;

import com.dungnv.vfw5.base.model.BaseFWModel;
import com.dungnv.vfw5.base.dto.BaseFWDTO;
import com.dungnv.vfw5.base.dto.ResultDTO;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author kdvt_binhnt22@viettel.com.vn
 * @version 1.0
 * @since May 2012
 */
public interface BaseFWServiceInterface<TDTO extends BaseFWDTO, TModel extends BaseFWModel> {

    // work with WF3.3+
    public List<ConditionBean> prepareCondition(TDTO tDTO);

    public List<TDTO> getAll();

    public List<TDTO> search(TDTO tDTO,
            int start, int maxResult, String sortType, String sortField);

    public String create(TDTO tDTO);

    public List<TDTO> searchByConditionBean(List<ConditionBean> lstConditionBean,
            int start, int maxResult, String sortType, String sortField);

    public ResultDTO createObject(TDTO tDTO);
//Test thieulq1

    public ResultDTO createObjectSession(TDTO tForm, Session session);

    //end test
    public String insertListNoID(List<TDTO> tForm);

    public String insertList(List<TDTO> tForm);

    public List<String> getListSequense(String seq, int size);

    public String update(TDTO tDTO);

    public String updateSession(TDTO tDTO, Session session);

    public String delete(Long id);

    public String delete(List<TDTO> tDTOOnGrid);

    public TModel findById(Long id);

    public boolean isDuplicate(String name, Long id);

    public boolean isDuplicate(Long id, String... name);

    public long count(TDTO tDTO);

    public List<TDTO> convertListModeltoDTO(List<TModel> listModel);

    public List<TModel> convertListDTOtoModel(List<TDTO> listDTO);

    public String getSysDate(String pattern);

    public boolean isDuplicate(Long id, HashMap<String, String> hashmap);
}
