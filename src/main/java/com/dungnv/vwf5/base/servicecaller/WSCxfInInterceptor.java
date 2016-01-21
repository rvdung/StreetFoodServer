/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.vwf5.base.servicecaller;

import com.dungnv.vfw5.base.utils.DateTimeUtils;
import com.dungnv.vfw5.base.utils.ErrorUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.message.Message;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.LoggingMessage;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

/**
 *
 * @author dungnv50
 */
public class WSCxfInInterceptor extends AbstractPhaseInterceptor<Message> {

    private static final int PROPERTIES_SIZE = 128;
    private static final Map<String, String> mapLogWS = new HashMap();
    private static final Map<String, String> mapDateWS = new HashMap();
    static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(WSCxfInInterceptor.class);

    public WSCxfInInterceptor() {
        super(Phase.RECEIVE);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        try {
            String requestId = "";
            try {
                Map<String, List<String>> protocolHeader = (TreeMap) message.get(Message.PROTOCOL_HEADERS);
                if (protocolHeader != null) {
                    requestId = protocolHeader.get("RequestId").get(0);
                }
            } catch (Exception e) {
                requestId = LoggingMessage.nextId();
            }

            String id = (String) message.getExchange().get(LoggingMessage.ID_KEY);
            if (id == null) {
                message.getExchange().put(LoggingMessage.ID_KEY, requestId);
            }

            InputStream in = message.getContent(InputStream.class);

            if (in == null) {
                return;
            }

            StringBuilder buffer;
            CachedOutputStream cache = new CachedOutputStream();
            try {
                InputStream origIn = in;
                IOUtils.copy(in, cache);

                if (cache.size() > 0) {
                    in = cache.getInputStream();
                } else {
                    in = new ByteArrayInputStream(new byte[0]);
                }

                // set the inputstream back as message payload
                message.setContent(InputStream.class, in);
                cache.close();
                origIn.close();
                int contentSize = (int) cache.size();

                buffer = new StringBuilder(contentSize + PROPERTIES_SIZE);

                cache.writeCacheTo(buffer, "UTF-8");
            } catch (IOException e) {
                throw new Fault(e);
            }
            mapLogWS.put(requestId, buffer.toString());
            mapDateWS.put(requestId, DateTimeUtils.convertDateToString(new Date(), "dd/MM/yyyy HH:mm:ss"));
        } catch (Exception e) {
            logger.error("Gap loi trong qua trinh ghi log Request: " + ErrorUtils.printLog(e));
        }
    }

    public static String getLog(String requestId) {
        return mapLogWS.get(requestId);
    }

    public static String removeLog(String requestId) {
        return mapLogWS.remove(requestId);
    }

    public static String getRequestTime(String requestId) {
        return mapDateWS.get(requestId);
    }

    public static String removeRequestTime(String requestId) {
        return mapDateWS.remove(requestId);
    }

}
