/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.vwf5.base.servicecaller;

import com.eviware.soapui.support.StringUtils;
import com.dungnv.vfw5.base.utils.DateTimeUtils;
import com.dungnv.vfw5.base.utils.ErrorUtils;
import java.io.OutputStream;
import java.util.Date;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.message.Message;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.LoggingMessage;
import org.apache.cxf.io.CacheAndWriteOutputStream;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.io.CachedOutputStreamCallback;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

/**
 *
 * @author dungnv50
 */
public class WSCxfOutInterceptor extends AbstractPhaseInterceptor<Message> {

    static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(WSCxfOutInterceptor.class);

    public WSCxfOutInterceptor() {
        super(Phase.PRE_STREAM);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        try {
            String requestId = (String) message.getExchange().get(LoggingMessage.ID_KEY);
            if (requestId == null) {
                requestId = LoggingMessage.nextId();
                message.getExchange().put(LoggingMessage.ID_KEY, requestId);
            }

            OutputStream os = message.getContent(OutputStream.class);
            CacheAndWriteOutputStream cwos = new CacheAndWriteOutputStream(os);
            message.setContent(OutputStream.class, cwos);
            cwos.registerCallback(new LoggingOutCallBack(requestId));
        } catch (Exception e) {
            logger.error("Gap loi trong qua trinh ghi log Response: " + ErrorUtils.printLog(e));
        }
    }

    class LoggingOutCallBack implements CachedOutputStreamCallback {

        String requestId;

        public LoggingOutCallBack(String requestId) {
            this.requestId = requestId;
        }

        @Override
        public void onClose(CachedOutputStream cos) {
            try {
                if (cos != null) {
                    String in = WSCxfInInterceptor.getLog(requestId);
                    String out = IOUtils.toString(cos.getInputStream());
                    if (out.length() > 1000) {
                        out = out.substring(0, 1000) + "... (length= " + out.length() + ")";
                    }
//                    if (in.length() > 1000) {
//                        in = in.substring(0, 1000) + "... (length= " + in.length() + ")";
//                    }

                    if (!StringUtils.isNullOrEmpty(in)) {

                        logger.info("====== " + requestId + " - START - " + WSCxfInInterceptor.getRequestTime(requestId));
                        logger.info("==== Request message of " + requestId + " : " + in);
                        logger.info("==== Response message of " + requestId + " : " + out);
                        logger.info("====== " + requestId + " - END - " + DateTimeUtils.convertDateToString(new Date(), "dd/MM/yyyy HH:mm:ss"));
                    }
                    WSCxfInInterceptor.removeLog(requestId);
                    WSCxfInInterceptor.removeRequestTime(requestId);
                }

            } catch (Exception e) {
                logger.error("Gap loi trong qua trinh ghi log Response: " + ErrorUtils.printLog(e));
            }
        }

        @Override
        public void onFlush(CachedOutputStream arg0) {
            // Do nothing
        }
    }
}
