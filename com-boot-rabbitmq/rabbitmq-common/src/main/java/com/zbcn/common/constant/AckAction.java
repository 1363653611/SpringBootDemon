package com.zbcn.common.constant;
/**
 *  @title AckAction
 *  @Description ack应答拒绝模式
 *  @author zbcn8
 *  @Date 2020/1/15 19:46
 */
public interface AckAction {

	/*消费成功*/
	String ACK_SUCCESSFUL = "success";
	/*重新入队*/
	String ACK_RETRY = "retry";
	/*拒绝入队*/
	String ACK_REJECT = "reject";
}
