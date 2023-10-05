package com.example.itchtestservice.soupbintcp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

@RestController
@RequestMapping("/tcp")
public class SoupBinTcpSender {

    @Autowired
    private SoupBinTcpReceiver receiver;



    @RequestMapping("/handle")
    public String handle() throws IOException {
        return receiver.messageParse();
    }

    @RequestMapping("/login")
    public String login() throws IOException {
        return receiver.login();
    }


}
