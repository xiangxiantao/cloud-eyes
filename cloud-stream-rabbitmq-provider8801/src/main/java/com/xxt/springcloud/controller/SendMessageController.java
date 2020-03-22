package com.xxt.springcloud.controller;

import com.xxt.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SendMessageController {

    @Autowired
    private IMessageProvider messageProvider;


    @GetMapping(value = "/send")
    public String sendMessage() {
        return messageProvider.send();
    }
}
