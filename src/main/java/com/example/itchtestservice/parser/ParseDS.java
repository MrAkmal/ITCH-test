package com.example.itchtestservice.parser;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParseDS {


    private final Map<Object, Object> yamlMap;
    private Map<Object, Object> formatsMap;
    private Map<Object, Object> messagesMap;
    private Yaml yml;

    ParseDS(String fileName) throws FileNotFoundException {

        yml = new Yaml();

        InputStream inputStream = new FileInputStream(fileName);

        Object ymlObject = yml.load(inputStream);

        yamlMap = (Map<Object, Object>) ymlObject;
        buildFormats();
        buildMessages();

    }

    public void buildFormats() {
        formatsMap = new HashMap<>();
        ArrayList<Object> fArray = (ArrayList<Object>) yamlMap.get("formats");

        for (int i = 0; i < fArray.size(); ++i) {
            Map<Object, Object> tempMap = (Map<Object, Object>) (fArray.get(i));
            formatsMap.put(tempMap.keySet().toArray()[0],
                    tempMap.values().toArray()[0]);
        }
    }

    // Build the messages data structure
    public void buildMessages() {
        messagesMap = (Map<Object, Object>) yamlMap.get("messages");
    }

    // Given Message type, return an array of the fields
    public ArrayList<Object> getFields(String mType) {
        Map<Object, Object> tempMap = (Map<Object, Object>) messagesMap.get(mType);
        assert tempMap != null : "File type missing: " + mType;
        return (ArrayList<Object>) tempMap.get("fields");
    }

    // Given the field, get the correct parser format for that field
    public ArrayList<Object> getFormat(String field) {
        Object fieldVal = formatsMap.get(field);
        assert fieldVal != null : "Format field missing: " + field;
        return (ArrayList<Object>) fieldVal;
    }

}
