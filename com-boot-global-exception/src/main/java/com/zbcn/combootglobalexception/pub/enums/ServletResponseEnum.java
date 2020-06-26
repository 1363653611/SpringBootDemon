package com.zbcn.combootglobalexception.pub.enums;

import com.zbcn.combootglobalexception.pub.interfaces.IResponseEnum;

import javax.servlet.http.HttpServletResponse;

/**
 *  servlet 请求级别的异常
 *  <br/>
 *  @author zbcn8
 *  @since  2020/6/26 15:36
 */
public enum ServletResponseEnum implements IResponseEnum {
    MethodArgumentNotValidException(4400,"", HttpServletResponse.SC_BAD_REQUEST),
    MethodArgumentTypeMismatchException(4400, "",HttpServletResponse.SC_BAD_REQUEST),
    MissingServletRequestPartException(4400,"",  HttpServletResponse.SC_BAD_REQUEST),
    MissingPathVariableException(4400,"",  HttpServletResponse.SC_BAD_REQUEST),
    BindException(4400,"",  HttpServletResponse.SC_BAD_REQUEST),
    MissingServletRequestParameterException(4400, "", HttpServletResponse.SC_BAD_REQUEST),
    TypeMismatchException(4400,"",  HttpServletResponse.SC_BAD_REQUEST),
    ServletRequestBindingException(4400,"", HttpServletResponse.SC_BAD_REQUEST),
    HttpMessageNotReadableException(4400,"",  HttpServletResponse.SC_BAD_REQUEST),
    NoHandlerFoundException(4404,"", HttpServletResponse.SC_NOT_FOUND),
    NoSuchRequestHandlingMethodException(4404,"",  HttpServletResponse.SC_NOT_FOUND),
    UnauthenticatedException(4401,"未授权用户",HttpServletResponse.SC_UNAUTHORIZED),
    HttpRequestMethodNotSupportedException(4405,"", HttpServletResponse.SC_METHOD_NOT_ALLOWED),
    HttpMediaTypeNotAcceptableException(4406,"",  HttpServletResponse.SC_NOT_ACCEPTABLE),
    HttpMediaTypeNotSupportedException(4415,"",  HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE),
    ConversionNotSupportedException(4500,"",  HttpServletResponse.SC_INTERNAL_SERVER_ERROR),
    HttpMessageNotWritableException(4500,"",  HttpServletResponse.SC_INTERNAL_SERVER_ERROR),
    AsyncRequestTimeoutException(4503,"", HttpServletResponse.SC_SERVICE_UNAVAILABLE),
    ;

    /**
     * 返回码，目前与{@link #statusCode}相同
     */
    private int code;
    /**
     * 返回信息，直接读取异常的message
     */
    private String message;
    /**
     * HTTP状态码
     */
    private int statusCode;

    ServletResponseEnum(int code, String message, int statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
