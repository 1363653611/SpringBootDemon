package com.zbcn.rabbitmqproducer.controller;

import com.zbcn.common.entity.TSendMessage;
import com.zbcn.common.response.ResponseResult;
import com.zbcn.rabbitmqproducer.service.IMessageSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  消息发送
 *  <br/>
 *  @author zbcn8
 *  @since  2020/10/22 14:11
 */
@RestController
@RequestMapping("/message/send")
public class MessageSendController {

    @Autowired
    private IMessageSendService messageSendService;

    /**
     * 发布/订阅 方式获取消息
     * @param sendMessage
     * @return
     */
    @PostMapping(path = "/topic", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseResult topicSend(@RequestBody TSendMessage sendMessage){
        return ResponseResult.success(messageSendService.topicSend(sendMessage));
    }
}
