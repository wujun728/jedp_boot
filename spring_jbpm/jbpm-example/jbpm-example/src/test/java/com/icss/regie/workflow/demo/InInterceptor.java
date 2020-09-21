package com.icss.regie.workflow.demo;

import java.io.IOException;  
import java.io.InputStream;  
  
import org.apache.cxf.helpers.IOUtils;  
import org.apache.cxf.interceptor.Fault;  
import org.apache.cxf.interceptor.LoggingMessage;  
import org.apache.cxf.io.CachedOutputStream;  
import org.apache.cxf.message.Message;  
import org.apache.cxf.phase.AbstractPhaseInterceptor;  
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;  

public class InInterceptor extends AbstractPhaseInterceptor<Message> {  
	  
	private  static final Logger logger = LoggerFactory.getLogger(InInterceptor.class);
	
    private int limit = 102400;  
  
    public int getLimit() {  
        return limit;  
    }  
  
    public void setLimit(int limit) {  
        this.limit = limit;  
    }  
  
    public InInterceptor() {  
        // 拦截器在调用方法之前拦截SOAP消息  
        super(Phase.RECEIVE);  
    }  
  
    public void handleMessage(Message message) throws Fault {  
        logging(message);  
    }  
  
    private void logging(Message message) throws Fault {  
        if (message.containsKey(LoggingMessage.ID_KEY)) {  
            return;  
        }  
        String id = (String) message.getExchange().get(LoggingMessage.ID_KEY);  
        if (id == null) {  
            id = LoggingMessage.nextId();  
            message.getExchange().put(LoggingMessage.ID_KEY, id);  
        }  
        message.put(LoggingMessage.ID_KEY, id);  
        LoggingMessage buffer = new LoggingMessage("Inbound Message\n----------------------------", id);  
  
        String encoding = (String) message.get(Message.ENCODING);  
  
        if (encoding != null) {  
            buffer.getEncoding().append(encoding);  
        }  
        String ct = (String) message.get("Content-Type");  
        if (ct != null) {  
            buffer.getContentType().append(ct);  
        }  
        Object headers = message.get(Message.PROTOCOL_HEADERS);  
  
        if (headers != null) {  
            buffer.getHeader().append(headers);  
        }  
        String uri = (String) message.get(Message.REQUEST_URI);  
        if (uri != null) {  
            buffer.getAddress().append(uri);  
        }  
  
        InputStream is = (InputStream) message.getContent(InputStream.class);  
        if (is != null) {  
            CachedOutputStream bos = new CachedOutputStream();  
            try {  
                IOUtils.copy(is, bos);  
  
                bos.flush();  
                is.close();  
  
                message.setContent(InputStream.class, bos.getInputStream());  
                if (bos.getTempFile() != null) {  
                    buffer.getMessage().append("\nMessage (saved to tmp file):\n");  
                    buffer.getMessage().append("Filename: " + bos.getTempFile().getAbsolutePath() + "\n");  
                }  
                if (bos.size() > this.limit) {  
                    buffer.getMessage().append("(message truncated to " + this.limit + " bytes)\n");  
                }  
                bos.writeCacheTo(buffer.getPayload(), this.limit);  
  
                bos.close();  
            } catch (IOException e) {  
                throw new Fault(e);  
            }  
        }  
        // 打印日志，保存日志保存这里就可以，可写库或log4j记录日志  
        System.out.println(buffer.toString());  
        logger.debug(buffer.toString());
  
    }  
  
    // 出现错误输出错误信息和栈信息  
    public void handleFault(Message message) {  
        Exception exeption = message.getContent(Exception.class);  
        System.out.println(exeption.getMessage());  
        logger.debug(exeption.getMessage());
    }  
  
}  