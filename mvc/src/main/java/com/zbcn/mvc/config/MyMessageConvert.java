package com.zbcn.mvc.config;

import com.zbcn.mvc.entity.DemonObj;
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
 * @author zbcn8
 * @version 1.0.0
 * @ClassName MyMessageConvert.java
 * @Description TODO
 * @createTime 2019年08月31日 21:22:00
 */
public class MyMessageConvert extends AbstractHttpMessageConverter<DemonObj> {

    public MyMessageConvert(){
        //自定义媒体类型：x-zbcn
        super(new MediaType("application","x-zbcn", Charset.forName("UTF-8")));
    }

    /**
     * 表明只支持 DemonObj 类的处理
     * @param aClass
     * @return
     */
    @Override
    protected boolean supports(Class<?> aClass) {
        return DemonObj.class.isAssignableFrom(aClass);
    }

    /**
     * 处理请求数据
     * @param aClass
     * @param httpInputMessage
     * @return
     * @throws IOException
     * @throws HttpMessageNotReadableException
     */
    @Override
    protected DemonObj readInternal(Class<? extends DemonObj> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {


        String temp = StreamUtils.copyToString(httpInputMessage.getBody(), Charset.forName("UTF-8"));
        String[] split = temp.split("-");

        return new DemonObj(new Long(split[0]),split[1]);
    }

    /**
     * 处理写入数据
     * @param demonObj
     * @param httpOutputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    protected void writeInternal(DemonObj demonObj, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        String out = "hello:" + demonObj.getId()+"-"+demonObj.getName();
        httpOutputMessage.getBody().write(out.getBytes());
    }
}
