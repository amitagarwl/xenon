package com.github.amitagarwl.helpers;

import org.json.JSONObject;

public class KiteClient {


    public static String createPayload(){

        JSONObject payload = new JSONObject();
        payload.put("userId","123123");
        return payload.toString();
    }



}
