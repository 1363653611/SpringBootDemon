package com.zbcn.zbcnjosejwt.execption;

public class JwtInvalidException extends RuntimeException {

    public JwtInvalidException(String message) {
        super(message);
    }
}
