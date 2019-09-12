package com.example.mobile_take_home.controller;

import com.example.mobile_take_home.model.Info;

import org.json.JSONException;
import org.json.JSONObject;

public class InfoController {

    public static Info fromJson(JSONObject jsonObject) throws JSONException {
        Info info = new Info();

        JSONObject jsonInfo;
        jsonInfo = jsonObject.getJSONObject("info");
        info.setCount(jsonInfo.getLong("count"));
        info.setPages(jsonInfo.getLong("pages"));
        info.setNext(jsonInfo.getString("next"));
        info.setPrev(jsonInfo.getString("prev"));

        return info;
    }

}
