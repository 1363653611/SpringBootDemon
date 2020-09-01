package com.zbcn.combootfile.pub.exception;
/**
 *  文件操作异常
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/1 15:03
 */
public class FileException extends RuntimeException {
    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}
