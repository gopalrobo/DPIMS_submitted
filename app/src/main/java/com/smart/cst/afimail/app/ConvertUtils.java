package com.smart.cst.afimail.app;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



/**
 * Created by Gopal on 15-10-2017.
 */

public class ConvertUtils {

    public static String prefixurl = "http://maps.googleapis.com/maps/api/staticmap?" +
            "zoom=17&size=600x600&maptype=satellite&sensor=false&path=color%3ared|weight:1|fillcolor%3awhite";


    public static JsonObject sample(String value) {
        try {
            JsonParser parser = new JsonParser();
            String result = value.replaceAll("\\\\", "");
            result = result.substring(1, result.length() - 1);
            JsonObject o = parser.parse(result).getAsJsonObject();
            return o;
        } catch (Error | Exception e) {
            JsonParser parser = new JsonParser();
            return parser.parse(value).getAsJsonObject();

        }
    }



}
