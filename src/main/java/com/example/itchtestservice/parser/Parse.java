package com.example.itchtestservice.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Parse {

    private Parsers parsers;
    private ParseDS parseDS;

    private InputStream inputStream;

    public Parse(String fileName, String yamlFile) {

        try {
            parseDS = new ParseDS(yamlFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        parsers = new Parsers(parseDS);

        try {
            inputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public byte[] parse() throws IOException {

        int payloadLength = inputStream.read();
        byte[] payloadBytes = new byte[payloadLength];

        ArrayList<String> messageArray = parsers.messageIn(payloadBytes);

        System.out.println("messageArray = " + messageArray);

        return null;
    }

}
