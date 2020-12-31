package com.zbcn.zbcnjosejwt.execption;
/**
 *  过期异常
 *  <br/>
 *  @author zbcn8
 *  @since  2020/12/30 10:01
 */
public class JwtExpiredException extends RuntimeException {

    public JwtExpiredException(String message) {
        super(message);
    }
}
