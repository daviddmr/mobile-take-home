package com.example.mobile_take_home.util;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class JsonUtil {

    public static ArrayList<String> stringListFromJson(JSONArray jsonArray) throws JSONException {

        ArrayList<String> urlList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            urlList.add(jsonArray.get(i).toString());
        }

        return urlList;
    }
}
