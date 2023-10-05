package com.example.itchtestservice.soupbintcp;

import com.example.itchtestservice.parser.ParseDS;
import com.example.itchtestservice.parser.Parsers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

@Service
public class SoupBinTcpService {

    @Value("${server.host}")
    private String serverHost;
    @Value("${tcp.server.port}")
    private int serverPort;

    private InetAddress host;


    public String login() throws IOException {
        host = InetAddress.getLocalHost();

        Socket socket = new Socket(host, 1024);

        System.out.println("socket.isConnected() = " + socket.isConnected());

//        while (true) {

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String loginMessage = "user 123";
            writer.write(loginMessage);
            writer.flush();

//            Scanner scanner  = new Scanner(socket.getInputStream());
//
//            if (scanner.hasNext()){
//                String response = scanner.nextLine();
//
//                if (response.equals("success")) break;
//            }

//        }

        return "login success";
    }


    public String messageParse() throws IOException {

        host = InetAddress.getLocalHost();

        Socket socket = new Socket(host, 1024);

        System.out.println("socket.isConnected() = " + socket.isConnected());

//        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//        reader.readLine()


        InputStream inputStream = socket.getInputStream();
        while (true) {
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);

            System.out.println("bytesRead = " + bytesRead);
            if (bytesRead == -1) {
                // End of stream, connection closed
                break;
            }

            // Parse and process the received ITCH message
            Parsers parsers = new Parsers(new ParseDS("C:\\Users\\MrAkmal\\Desktop\\Dohasec\\itch-test-service\\src\\main\\resources\\itch5.yaml"));

            ArrayList<String> messageArray = parsers.messageIn(buffer);

            System.out.println("messageArray = " + messageArray);

        }


        return " ";
    }


//    public String consumeMessage() throws IOException {
//
//        host = InetAddress.getLocalHost();
//
//        boolean check = true;
//
//        String fileName = "";
//        while (check) {
//            Socket socket = new Socket(host, 1024);
//
//            System.out.println("socket.isConnected() = " + socket.isConnected());
//
//            Scanner scanner = new Scanner(socket.getInputStream());
//
//
//            if (scanner.hasNext()) {
//
//                fileName = scanner.nextLine();
//
//                System.out.println("scanner.nextLine() = " + fileName);
//                check = false;
//            }
//        }
//
//
////        String fileName = "C:\\Users\\MrAkmal\\Desktop\\Dohasec\\01302019.NASDAQ_ITCH50";
//
//        Parse parse = new Parse(fileName, "C:\\Users\\MrAkmal\\Desktop\\Dohasec\\itch-test-service\\src\\main\\resources\\itch5.yaml");
//
//        while (parse.parse() != null) {
//        }
//
//        return "success";
//    }


}
