package com.zbcn.combootfile.pub.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *  文件配置相关信息
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/1 11:48
 */
@ConfigurationProperties(prefix = "file")
public class FileProperties {
    private String uploadDir;

    public FileProperties() {
    }

    public FileProperties(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
