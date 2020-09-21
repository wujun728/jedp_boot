package com.springmvc.messageConverter;

import com.springmvc.entity.DemoObject;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by zsq on 16/12/21.
 * 自定义的 HttpMessageConverter 继承 AbstractHttpMessageConverter 抽象类
 * 子类继承抽象类,必须实现抽象类中的所有抽象方法
 *
 */
public class MessageConverter extends AbstractHttpMessageConverter {


    /**
     * 自定义媒体类型为 application/wisely
     */
    public MessageConverter() {
        super(new MediaType("application", "x-wisely", Charset.forName("UTF-8")));
    }

    /**
     * 表明自定义的 HttpMessageConverter 即 MessageConverter,
     * 只处理 DemoObject 这个类
     * @param aClass
     * @return
     */
    @Override
    protected boolean supports(Class aClass) {
        return DemoObject.class.isAssignableFrom(aClass);
    }

    /**
     * 重写 readInternal() 处理请求的代码, 表明我们处理由"-"分隔的数据
     * @param aClass
     * @param httpInputMessage
     * @return
     * @throws IOException
     * @throws HttpMessageNotReadableException
     */
    @Override
    protected Object readInternal(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(httpInputMessage.getBody(), Charset.forName("UTF-8"));
        String[] tempArr = temp.split("-");
        return new DemoObject(new Long(tempArr[0]), tempArr[1]);
    }

    /**
     * 重写writeInternal() 处理如何输出数据到response
     * @param o
     * @param httpOutputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    protected void writeInternal(Object o, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        DemoObject demoObject = (DemoObject) o;
        String outObject = demoObject.getId() + "-" +demoObject.getName();
        httpOutputMessage.getBody().write(outObject.getBytes());
    }
}
