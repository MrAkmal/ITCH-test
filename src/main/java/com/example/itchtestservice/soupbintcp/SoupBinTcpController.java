package com.example.itchtestservice.soupbintcp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/tcp")
public class SoupBinTcpController {

    @Autowired
    private SoupBinTcpService receiver;



    @RequestMapping("/handle")
    public String handle() throws IOException {
        return receiver.messageParse();
    }

    @RequestMapping("/login")
    public String login() throws IOException {
        return receiver.login();
    }


}
