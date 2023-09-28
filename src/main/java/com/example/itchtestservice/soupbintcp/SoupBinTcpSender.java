package com.example.itchtestservice.soupbintcp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/tcp")
public class SoupBinTcpSender {

    @Autowired
    private SoupBinTcpReceiver receiver;


    @RequestMapping("/connect")
    public String connect() throws IOException {
        return receiver.consumeMessage();
    }


}
