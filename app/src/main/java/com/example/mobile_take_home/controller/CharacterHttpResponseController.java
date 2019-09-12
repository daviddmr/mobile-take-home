package com.example.mobile_take_home.controller;

import com.example.mobile_take_home.http.response.CharacterHttpResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class CharacterHttpResponseController {

    public static CharacterHttpResponse fromJson(String data) {
        CharacterHttpResponse characterResponse = new CharacterHttpResponse();

        try {
            JSONObject jsonObject = new JSONObject(data);

            characterResponse.setInfo(InfoController.fromJson(jsonObject));
            characterResponse.setCharacters(CharacterController.listFromJson(jsonObject.getJSONArray("results")));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return characterResponse;
    }

}
