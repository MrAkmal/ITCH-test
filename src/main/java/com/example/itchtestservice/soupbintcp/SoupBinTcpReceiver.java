package com.example.itchtestservice.soupbintcp;

import com.example.itchtestservice.parser.Parse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@Service
public class SoupBinTcpReceiver {

    @Value("${server.host}")
    private String serverHost;
    @Value("${tcp.server.port}")
    private int serverPort;


    public String consumeMessage() throws IOException {

        Socket socket = new Socket(serverHost, serverPort);

        InputStream inputStream = socket.getInputStream();

        System.out.println("socket.isConnected() = " + socket.isConnected());

//        System.out.println("inputStream = " + inputStream);
//
//        int read = inputStream.read();
//        System.out.println("read = " + read);
//        byte[] payload = new byte[read];
//        System.out.println("payload = " + new String(payload));

//        int i = 0;
//
//        while ((i = inputStream.read()) != -1) {
//
//            // converts integer to character
//            char c = (char) i;
//
//            // prints character
//            System.out.print("c  = " + c);
//        }


        InputStreamReader reader = new InputStreamReader(inputStream);
        int character = reader.read();
        System.out.println("reader.getEncoding() = " + reader.getEncoding());
        System.out.println("character = " + character);

        byte[] payloadBytes = inputStream.readAllBytes();

        System.out.println("payloadBytes = " + payloadBytes);

        String fileName = new String(payloadBytes, StandardCharsets.UTF_8);

        System.out.println("fileName = " + fileName);

//        fileName = "C:\\Users\\MrAkmal\\Desktop\\Dohasec\\01302019.NASDAQ_ITCH50";

        Parse parse = new Parse(fileName, "C:\\Users\\MrAkmal\\Desktop\\Dohasec\\itch-test-service\\src\\main\\resources\\itch5.yaml");

        parse.parse();

        return "success";
    }


}
