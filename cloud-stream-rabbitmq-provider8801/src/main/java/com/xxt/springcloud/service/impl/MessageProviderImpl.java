package com.xxt.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.xxt.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(Source.class)//定义消息的推送管道
@Slf4j
public class MessageProviderImpl implements IMessageProvider {

    @Autowired
    private MessageChannel output;//消息发送管道

    @Override
    public String send() {
        String serialNo= IdUtil.simpleUUID();
        log.info("sending:{}...",serialNo);
        Message<String> msg = MessageBuilder.withPayload(serialNo).build();//构建消息
        output.send(msg);//发送消息
        return null;
    }
}
