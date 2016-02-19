/*
 * Copyright (C) 2015 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.vfw5.base.dao;

import com.dungnv.vfw5.base.model.BaseFWModel;
import com.dungnv.vfw5.base.pojo.ConditionBean;
import com.dungnv.vfw5.base.utils.Constants;
import com.dungnv.vfw5.base.utils.DataUtil;
import com.dungnv.vfw5.base.utils.ParamUtils;
import com.dungnv.vfw5.base.utils.StringUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author BinhNT22
 * @version: 2.0
 * @param <T> - a Model that extends BaseFWModel
 * @param <ID> - id of the model
 * @since: 31/03/2015
 */
@Repository
public class BaseFWDAOImpl<T extends BaseFWModel, ID extends Serializable> {

    // Trong truong hop co nhieu data source thi tao SessionFactory moi
    // extend HibernateSessionFactory va khai bao them vao hibernate-config.xml
    long i;
    public Session session;
    protected static Logger log = Logger.getLogger(BaseFWDAOImpl.class);
    protected T model;

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
//        SessionFactoryImpl sfsdf = (SessionFactoryImpl) sessionFactory;
//        LocalSessionFactoryBean test = (LocalSessionFactoryBean) sessionFactory;
//        Properties pro =  sfsdf.getProperties();
//        Set<Object> set = pro.keySet();
//        for (Object key : set) {
//            System.out.println("--- " + key.toString() + "  -> " + pro.get(key).toString());
//        }

        return sessionFactory.getCurrentSession();
    }

    public T getModel(ID key) {
        return (T) getSession().get(model.getClass(), key);
    }

    public List<T> findByCriteria(
            String sessionName, int firstResult, int maxResult,
            ProjectionList projs, Criterion[] criterion, Order[] orders) {
        Criteria crit
                = getSession().createCriteria(model.getClass());
        if (projs != null) {
            crit.setProjection(projs);
        }
        if (criterion != null && criterion.length > 0) {
            for (Criterion c : criterion) {
                crit.add(c);
            }
        }
        if (orders != null && orders.length > 0) {
            for (Order o : orders) {
                crit.addOrder(o);
            }
        }
        if (firstResult > 0) {
            crit.setFirstResult(firstResult);
        }
        if (maxResult > 0) {
            crit.setMaxResults(maxResult);
        }
        return crit.list();
    }

    public List<T> findByCriteria(ProjectionList projections,
            Criterion[] criterions, Order[] orders) {
        return findByCriteria("default session", 0, 0,
                projections, criterions, orders);
    }

    public List<T> findByCriteria(ProjectionList projections,
            Criterion[] criterions, Order order) {
        Order orders[] = new Order[1];
        orders[0] = order;
        return findByCriteria("default session", 0, 0,
                projections, criterions, orders);
    }

    public List<T> findByCriteria(ProjectionList projections, Order order) {
        Order orders[] = new Order[1];
        orders[0] = order;
        return findByCriteria("default session", 0, 0,
                projections, null, orders);
    }

    public List<T> findByCriteria(
            String sessionName, int firstResult, int maxResult,
            Criterion[] criterions, Order[] orders) {
        return findByCriteria(sessionName, firstResult, maxResult,
                null, criterions, orders);
    }

    public List<T> findByCriteria(int firstResult, int maxResult,
            Criterion[] criterion, Order[] orders) {
        return findByCriteria("default session",
                firstResult, maxResult, criterion, orders);
    }

    public List<T> findByCriteria(
            String sessionName, Criterion[] criterion, Order[] orders) {
        return findByCriteria(sessionName, 0, 0, criterion, orders);
    }

    public List<T> findByCriteria(Criterion[] criterion, Order[] orders) {
        return findByCriteria("default session", criterion, orders);
    }

    public List<T> findByCriteria(Criterion[] criterion, Order order) {
        Order orders[] = new Order[1];
        orders[0] = order;
        return findByCriteria("default session", criterion, orders);
    }

    public List<T> findByCriteria(Criterion crit, Order order) {
        Order orders[] = new Order[1];
        orders[0] = order;
        Criterion[] criterion = new Criterion[1];
        criterion[0] = crit;
        return findByCriteria("default session", criterion, orders);
    }

    public List<T> findByCriteria(Criterion crit) {
        Criterion[] criterion = new Criterion[1];
        criterion[0] = crit;
        return findByCriteria("default session", criterion, null);
    }

    public List<T> findByCriteria(String sessionName,
            int firstResult, int maxResult, Order[] orders) {
        return findByCriteria(sessionName,
                firstResult, maxResult, new Criterion[0], orders);
    }

    public List<T> findByCriteria(int firstResult, int maxResult, Order[] orders) {
        return findByCriteria("default session",
                firstResult, maxResult, new Criterion[0], orders);
    }

    public List<T> findByCriteria(String sessionName,
            int firstResult, int maxResult) {
        return findByCriteria(sessionName, firstResult, maxResult, new Order[0]);
    }

    public List<T> findByCriteria(int firstResult, int maxResult) {
        return findByCriteria("default session", firstResult, maxResult);
    }

    public List<T> findAll(String sessionName, Order[] orders) {
        return findByCriteria(sessionName, new Criterion[0], orders);
    }

    public List<T> findAll(Order[] orders) {
        return findAll("default session", orders);
    }

    public List<T> findAll(Order order) {
        Order orders[] = new Order[1];
        orders[0] = order;
        return findAll("default session", orders);
    }

    public List<T> findDistinct(String[] pros, Criterion[] crits) {
        ProjectionList listProjections = Projections.projectionList();
        ProjectionList listProperties = Projections.projectionList();
        Order[] orders = new Order[pros.length];
        int index = 0;
        for (String property : pros) {
            listProperties.add(Projections.property(property));
            orders[index++] = Order.asc(property);
        }
        listProjections.add(Projections.distinct(listProperties));
        return findByCriteria("default session", 0, 0, listProjections, crits, orders);
    }

    public List<T> findDistinct(String[] pros) {
        return findDistinct(pros, new Criterion[0]);
    }

    public List<T> findByCriteria(Criterion[] crits) {
        return findByCriteria(crits, new Order[0]);
    }

    public List<T> findByCondition(int firstResult, int maxResult, Criterion[] crits) {
        return findByCriteria("default session", firstResult, maxResult, crits, new Order[0]);
    }

    @Transactional
    public String delete(T obj) {
        try {
            getSession().delete(obj);
            return ParamUtils.SUCCESS;
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return he.getMessage();
        }
    }

    @Transactional
    public String deleteObject(T obj, Session session) {
        try {
            session.delete(obj);
            return ParamUtils.SUCCESS;
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return he.getMessage();
        }
    }

    @Transactional
    public String save(T obj) {
        try {
            getSession().save(obj);
            return ParamUtils.SUCCESS;
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return he.getMessage();
        }
    }
    //
//    @Transactional
//    public ResultDTO saveObject(T obj) {
//        ResultDTO resultDTO = new ResultDTO();
//        try {
//            
//            resultDTO.setId((String) getSession().save(obj));
//            resultDTO.setMessage(ParamUtils.SUCCESS);
//        } catch (HibernateException he) {
//            log.error(he.getMessage(), he);
//            resultDTO.setMessage(he.getMessage());        
//        }
//        return resultDTO
//    }
    //

    //
    @Transactional
    public String saveList(List<T> obj, String methodName) {

        try {

            for (T item : obj) {
                getSession().saveOrUpdate(item);
            }

//            Class c = obj.get(0).getClass();
//            Method method = c.getMethod("get" + StringUtils.upperFirstChar(methodName));
//
//            for (T item : obj) {
//                String value;
//                try {
//                    value = String.valueOf(method.invoke(item));
//
//                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//                    return ParamUtils.ERROR;
//                }
//
//                if (value == "null") {
//                    getSession().saveOrUpdate(item);
//                } else {
//                    getSession().update(item);
//                }
//            }
        } catch (SecurityException ex) {
            return ex.getMessage();
        }
        return ParamUtils.SUCCESS;

    }

    @Transactional
    public String update(T obj) {
        try {
            getSession().update(obj);
            return ParamUtils.SUCCESS;
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return he.getMessage();
        }
    }

    @Transactional
    public String update(T obj, String field) {
        try {
            getSession().update(field, obj);
            return ParamUtils.SUCCESS;
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return he.getMessage();
        }
    }

    @Transactional
    public String updateMerge(T obj) {
        try {
            getSession().merge(obj);
            return ParamUtils.SUCCESS;
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return he.getMessage();
        }
    }

    public List<T> find(String boName, List<ConditionBean> lstCondition) {
        return find(boName, lstCondition, null, 0, 0, null);
    }

    public List<T> find(String boName, List<ConditionBean> lstCondition, String logic) {
        return find(boName, lstCondition, null, 0, 0, logic);
    }

    public List<T> find(String boName, List<ConditionBean> lstCondition, String order, int start, int maxResult) {
        return find(boName, lstCondition, order, start, maxResult, null);
    }

    public List<T> find(String boName, List<ConditionBean> lstCondition, String order, int start, int maxResult, String logic) {
//        if (logic == null) {
//            logic = ParamUtils.LOGIC_AND;
//        }
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" from ");
            sql.append(boName);
            sql.append(" where 1=1 ");
            if (lstCondition != null && !lstCondition.isEmpty()) {
                buildConditionQuery(sql, lstCondition);
            }
            if (order != null && !order.equals("")) {
                sql.append(" order by ");
                sql.append(order);
            }
            Query query = getSession().createQuery(sql.toString());
            if (maxResult != 0) {
                query.setFirstResult(start);
                query.setMaxResults(maxResult);
            }
            fillConditionQuery(query, lstCondition);
            return query.list();
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return null;
        }
    }

    //
    public List<T> findSession(String boName, List<ConditionBean> lstCondition, String order, int start, int maxResult, String logic, Session session) {
//        if (logic == null) {
//            logic = ParamUtils.LOGIC_AND;
//        }
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" from ");
            sql.append(boName);
            sql.append(" where 1=1 ");
            if (lstCondition != null && !lstCondition.isEmpty()) {
                buildConditionQuery(sql, lstCondition);
            }
            if (order != null && !order.equals("")) {
                sql.append(" order by ");
                sql.append(order);
            }
            Query query = session.createQuery(sql.toString());
            if (maxResult != 0) {
                query.setFirstResult(start);
                query.setMaxResults(maxResult);
            }
            fillConditionQuery(query, lstCondition);
            return query.list();
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return null;
        }
    }

//    public String delete(String boName, List<ConditionBean> lstCondition, Long transId) {
//        try {
//            List<T> listDelete = find(boName, lstCondition);
//            if (listDelete != null && !listDelete.isEmpty()) {
//                for (T obj : listDelete) {
//                    List<RaRecycleBinBO> listRecycle = getListRecycle(obj, transId);
//                    if (listRecycle != null && !listRecycle.isEmpty()) {
//                        for (RaRecycleBinBO bo : listRecycle) {
//                            session.save(bo);
//                        }
//                    }
//                    session.delete(obj);
//                }
//            }
//            return ParamUtils.SUCCESS;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return e.getMessage();
//        }
//
//    }
    @Transactional
    public String delete(String boName, List<ConditionBean> lstCondition) {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("delete from ");
            sql.append(boName);
            sql.append(" where 1=1 ");
            int result = 0;
            if (lstCondition != null) {
                buildConditionQuery(sql, lstCondition);
                Query query = getSession().createQuery(sql.toString());
                fillConditionQuery(query, lstCondition);
                result = query.executeUpdate();
            }
            if (result == 0) {
                return ParamUtils.FAIL;
            } else {
                return ParamUtils.SUCCESS;
            }
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return he.getMessage();
        }
    }

    @Transactional
    public int deleteList(String boName, List<ConditionBean> lstCondition) {
        try {
//            Transaction tx = (Transaction) getSession().beginTransaction();
            StringBuilder sql = new StringBuilder();
            sql.append("delete from ");
            sql.append(boName);
            sql.append(" where 1=1 ");
            int result = 0;
            if (lstCondition != null) {
                buildConditionQueryDeleteList(sql, lstCondition);
                Query query = getSession().createQuery(sql.toString());
                fillConditionQuery(query, lstCondition);
                result = query.executeUpdate();

            }

//            if (result == 0) {
//                return ParamUtils.FAIL;
//            } else {
//                return ParamUtils.SUCCESS;
//            }
            return result;
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return 0;
        }
    }

    public long count(String boName, List<ConditionBean> lstCondition) {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select count(*) from ");
            sql.append(boName);
            sql.append(" where 1=1 ");
            long result = 0;
            if (lstCondition != null) {
                buildConditionQuery(sql, lstCondition);
                Query query = getSession().createQuery(sql.toString());
                fillConditionQuery(query, lstCondition);
                result = (Long) query.list().get(0);
            }
            return result;
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return 0l;
        }
    }

    public void buildConditionQuery(StringBuilder sql, List<ConditionBean> lstCondition) {
        if (lstCondition != null) {
            int index = 0;
            for (ConditionBean con : lstCondition) {
                sql.append(ParamUtils.LOGIC_AND);
                sql.append(con.getField());
                sql.append(con.getOperator());
                if (con.getType().equals(ParamUtils.TYPE_STRING)) {
                    sql.append(":idx").append(String.valueOf(index++));
                    if (con.getOperator().equals(ParamUtils.OP_LIKE)) {
//                        sql.append(" ESCAPE '\\' ");
                    }
                } else if (con.getType().equals(ParamUtils.TYPE_DATE)) {
                    sql.append(" to_date(:idx").append(String.valueOf(index++))
                            .append(", '").append(ParamUtils.ddMMyyyyHHmmss).append("')");
                } else if (con.getOperator().equals(ParamUtils.OP_IN)) {
                    sql.append("( :idx").append(String.valueOf(index++)).append(" )");
                } else {
                    sql.append(" :idx").append(String.valueOf(index++));
                }
            }
        }
    }

    //Test
    public void buildConditionQueryDeleteList(StringBuilder sql, List<ConditionBean> lstCondition) {
        if (lstCondition != null) {
            int index = 0;
            sql.append(ParamUtils.LOGIC_AND);
            for (ConditionBean con : lstCondition) {
                if (con != lstCondition.get(0)) {
                    sql.append(ParamUtils.LOGIC_OR);
                }
                sql.append(con.getField());
                sql.append(con.getOperator());
                if (con.getType().equals(ParamUtils.TYPE_STRING)) {
                    sql.append(":idx").append(String.valueOf(index++));
                    if (con.getOperator().equals(ParamUtils.OP_LIKE)) {
//                        sql.append(" ESCAPE '\\' "); 
                    }
                } else if (con.getType().equals(ParamUtils.TYPE_DATE)) {
                    sql.append(" to_date(:idx").append(String.valueOf(index++))
                            .append(", '").append(ParamUtils.ddMMyyyyHHmmss).append("')");
                } else if (con.getOperator().equals(ParamUtils.OP_IN)) {
                    sql.append(con.getValue());
                } else {
                    sql.append(":idx").append(String.valueOf(index++));
                }
            }
        }
    }
    //endTest

    public void fillConditionQuery(Query query, List<ConditionBean> lstCondition) {
        int index = 0;
        for (ConditionBean con : lstCondition) {
            if (con.getType().equals(ParamUtils.TYPE_NUMBER)) {
                if (con.getOperator().equals(ParamUtils.OP_IN)) {
                    query.setParameterList("idx" + String.valueOf(index++), DataUtil.parseInputListLong(con.getValue()));
                } else {
                    query.setParameter("idx" + String.valueOf(index++), Long.parseLong(con.getValue()));
                }

            } else if (con.getType().equals(ParamUtils.NUMBER_DOUBLE)) {
                if (con.getOperator().equals(ParamUtils.OP_IN)) {
                    query.setParameterList("idx" + String.valueOf(index++), DataUtil.parseInputListDouble(con.getValue()));
                } else {
                    query.setParameter("idx" + String.valueOf(index++), Double.parseDouble(con.getValue()));
                }
            } else if (con.getType().equals(ParamUtils.TYPE_STRING)) {
                if (con.getOperator().equals(ParamUtils.OP_IN)) {
                    query.setParameterList("idx" + String.valueOf(index++), DataUtil.parseInputListString(con.getValue()));
                } else {
                    query.setParameter("idx" + String.valueOf(index++), con.getValue());
                }
            } else {
                query.setParameter("idx" + String.valueOf(index++), con.getValue());
            }
        }
    }

    public static String genSubQuery(String tableName, String colId, String colName, String value) {
//        return "(select " + colId + tableName + " where " + StringUtils.formatFunction("lower", colName) + " like '" + StringUtils.formatLike(value) + "'  ESCAPE '\\' )";
        return "(select " + colId + tableName + " where " + StringUtils.formatFunction("lower", colName) + " like '" + StringUtils.formatLike(value) + "')";
    }

    public Long getTransactionId() {
        String sql = "select " + Constants.TRANSACTION_SEQUENCE + ".nextval from dual";
        Query query = getSession().createSQLQuery(sql);
        return ((BigDecimal) query.list().get(0)).longValue();
    }

    public Long getSequenId(String sequense) {
        String sql = "select " + sequense + ".nextval from dual";
        Query query = getSession().createSQLQuery(sql);
        return ((BigDecimal) query.list().get(0)).longValue();

    }

    @Transactional
    public String saveObject(T obj) {
        try {
            long id = (long) getSession().save(obj);
            getSession().flush();
            return String.valueOf(id);
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return he.getMessage();
        }
    }

    //Test thieulq1
    @Transactional
    public String saveObjectSession(T obj, Session session) {
        try {
            long id = (long) session.save(obj);
            return String.valueOf(id);
        } catch (Exception he) {
            log.error(he.getMessage(), he);
            return he.getMessage();
        }
    }

    @Transactional
    public String updateSession(T obj, Session session) {
        try {
            session.update(obj);
            return ParamUtils.SUCCESS;
        } catch (Exception he) {
            log.error(he.getMessage(), he);
            return he.getMessage();
        }
    }
    //Endtest

    public String getSysDate(String pattern) throws Exception {
        String queryString = "SELECT to_char(sysdate,:id)  from dual";
        Query query = getSession().createSQLQuery(queryString);
        query.setParameter("id", pattern);
        return query.list().get(0).toString();

    }

    @Transactional
    public String saveListNoId(List<T> obj) {
        try {
            for (T item : obj) {
                getSession().save(item);
            }
            return ParamUtils.SUCCESS;
        } catch (SecurityException ex) {
            return ex.getMessage();
        }
    }
//    public List<RaRecycleBinBO> getListRecycle(T obj, Long transId) {
//        List<RaRecycleBinBO> listRecycle = new ArrayList<RaRecycleBinBO>();
//        if (obj != null) {
//            Class c = obj.getClass();
//            Table t = (Table) c.getAnnotation(Table.class);
//            Method methods[] = c.getDeclaredMethods();
//            Long recordId = null;
//            for (Method method : methods) {
//                if (ReflectUtils.isGetter(method)) {
//                    if (method.getAnnotation(Id.class) != null) {
//                        try {
//                            recordId = (Long) method.invoke(obj);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//            for (Method method : methods) {
//                if (ReflectUtils.isGetter(method)) {
//                    RaRecycleBinBO bo = new RaRecycleBinBO();
//                    bo.setTransactionId(transId);
//                    bo.setTableName(t.name());
//                    bo.setColumnName(ReflectUtils.getColumnName(method));
//                    bo.setRecordId(recordId);
//                    try {
//                        Object value = method.invoke(obj);
//                        bo.setColumnValue(String.valueOf(value));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    bo.setDateDelete(new Date());
//                    listRecycle.add(bo);
//                }
//            }
//        }
//        return listRecycle;
//    }

    public String printLog(Exception e) {
        String str;
        try {
            e.printStackTrace();
            str = " [DEATAIL]" + e.toString();
            if (e.getCause() != null && e.getCause().getMessage() != null) {
                str += " - Caused by " + e.getCause().getMessage();
            }
            StackTraceElement[] traceList = e.getStackTrace();
            for (StackTraceElement trace : traceList) {
                if (trace.getClassName().contains("com.dungnv.framework.interceptor.ActionInterceptor")
                        || trace.getClassName().contains("com.dungnv.config.tms.interceptor.LogActionInterceptor")) {
                    break;
                } else if (trace.getClassName().contains("com.dungnv")) {
                    str += "\n [" + trace.getClassName() + ".class][" + trace.getMethodName() + "][" + trace.getLineNumber() + "]";
                }
            }
        } catch (Exception ex) {
            str = ":" + e.getMessage();
        }
        return str;
    }
}
