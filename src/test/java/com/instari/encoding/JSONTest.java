package com.instari.encoding;

import org.json.simple.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class JSONTest {

    @Test
    public void testDictionaryToJSON() throws Exception {

        JSONObject dictionary = new JSONObject();
        dictionary.put("abc", "def");
        dictionary.put("msn", "bc");
        String json = JSON.dictionaryToJSON(dictionary);

        assertEquals("Dictionary to JSON string conversion", json, "{\"abc\":\"def\",\"msn\":\"bc\"}");

    }

    @Test
    public void testJsonToDictionary() throws Exception {

        String json = "{\"abc\":\"def\",\"msn\":\"bc\"}";
        JSONObject dictionary = JSON.jsonToDictionary(json);

        assertEquals("JSON string to dictionary conversion", "def", dictionary.get("abc"));
        assertEquals("JSON string to dictionary conversion", "bc", dictionary.get("msn"));

    }
}