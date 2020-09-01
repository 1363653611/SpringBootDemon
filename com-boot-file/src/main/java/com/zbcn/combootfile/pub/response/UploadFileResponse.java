package com.zbcn.combootfile.pub.response;
/**
 *  文件上传响应
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/1 14:56
 */
public class UploadFileResponse {

    /**
     * 文件名称
     */
    private String fileName;
    /**
     *
     */
    private String fileDownloadUri;
    /**
     * 文件大小
     */
    private String fileType;
    /**
     * 文件大小
     */
    private long size;

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
