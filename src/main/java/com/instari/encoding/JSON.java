package com.instari.encoding;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * Created by arik on 2/23/15.
 */
public class JSON {

    public static String dictionaryToJSON(JSONObject dictionary) {

        return dictionary.toJSONString();

    }

    public static JSONObject jsonToDictionary(String json){

        Object dictionary = JSONValue.parse(json);
        return (JSONObject)dictionary;

    }

}
