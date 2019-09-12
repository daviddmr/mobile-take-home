package com.example.mobile_take_home.controller;

import com.example.mobile_take_home.model.Planet;

import org.json.JSONException;
import org.json.JSONObject;

public class PlanetController {

    static Planet fromJson(JSONObject jsonObject) throws JSONException {
        Planet planet = new Planet();

        planet.setName(jsonObject.getString("name"));
        planet.setUrl(jsonObject.getString("url"));

        return planet;
    }

}
