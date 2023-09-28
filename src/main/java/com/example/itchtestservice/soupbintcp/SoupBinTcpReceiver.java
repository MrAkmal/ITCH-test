package com.example.itchtestservice.soupbintcp;

import com.example.itchtestservice.parser.Parse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

@Service
public class SoupBinTcpReceiver {

    @Value("${server.host}")
    private String serverHost;
    @Value("${tcp.server.port}")
    private int serverPort;


    public String consumeMessage() throws IOException {

        Socket socket = new Socket(serverHost,serverPort);

        InputStream inputStream = socket.getInputStream();

//        int read = inputStream.read();
//        System.out.println("read = " + read);
//        byte[] payload = new byte[read];
//        System.out.println("payload = " + new String(payload));

        byte[] payloadBytes = inputStream.readAllBytes();

        String fileName = new String(payloadBytes);

        System.out.println("fileName = " + fileName);

        fileName = "C:\\Users\\MrAkmal\\Desktop\\Dohasec\\01302019.NASDAQ_ITCH50";

        Parse parse = new Parse(fileName, "C:\\Users\\MrAkmal\\Desktop\\Dohasec\\itch-test-service\\src\\main\\resources\\itch5.yaml");

        parse.parse();

        return "success";
    }


}
