package com.example.itchtestservice.parser;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class Parsers {


    private ParseDS parseDS;


    Parsers(ParseDS parseDS) {
        this.parseDS = parseDS;
    }

    public String getString(byte[] payload) {
        return new String(payload);
    }


    public String getInt(byte[] payload) {
        long value = 0;
        for (int i = 0; i < payload.length; i++) {
            value = (value << 8) + (payload[i] & 0xff);
        }
        return "" + value;
    }

    public String getDouble(byte[] payload) {
        return ("" + (Double.parseDouble(getInt(payload)) / 10000));
    }



    // Payload in, get the messageType, the fields, then the get the message
    public ArrayList<String> messageIn(byte[] payload) throws UnsupportedEncodingException {
        // Keep track of where we are at in the message
        int messagePointer = 0;
        ArrayList<String> messageArray = new ArrayList<>();
        // Get the messageType (getChar only returns A char in the first byte)
        String messageType = "" +new String(payload).charAt(0);
        messageArray.add(messageType);
        // increment after look at first char
        messagePointer = messagePointer + 1;
        // Get the fields for this message
        ArrayList<Object> fieldsArray = this.parseDS.getFields(messageType);
        // loop over fields array, parsing messages --start at 1, we already have messageType
        for (int i = 1; i < fieldsArray.size(); i++) {
            // Get the field for this part of the message
            ArrayList<Object> fieldArray = this.parseDS.getFormat((String) fieldsArray.get(i));
            // Call appropriate parser on the split payload
            messageArray.add(parse(Arrays.copyOfRange(payload, messagePointer,
                            messagePointer + ((Integer) fieldArray.get(1))),
                    fieldArray));
            // Move the array cursor after looking at this field
            messagePointer = messagePointer + ((Integer) fieldArray.get(1));
        }
        return messageArray;
    }

    // With input byteArray, len & parse type, call approp parser
    public String parse(byte[] arr, ArrayList<Object> fieldArray) throws UnsupportedEncodingException {
        String value = "";
        switch ((Integer) fieldArray.get(0)) {
            case 1:
                value = getString(arr);
                break;
            case 2:
                value = "" + Long.parseLong(getInt(arr));
                break;
            case 3:
                value = "" + Double.parseDouble(getDouble(arr));
                break;
            case 4:
                value = "" + (Double.parseDouble(getDouble(arr)) / 1000);
                break;
        }
        return value.trim();
    }

}
