package com.example.mobile_take_home.controller;

import com.example.mobile_take_home.model.Character;
import com.example.mobile_take_home.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CharacterController {

    //To be used when the response does not have the field 'info'
    public static ArrayList<Character> listFromJson(String data) {

        ArrayList<Character> characterList = new ArrayList<>();

        try {
            characterList = listFromJson(new JSONArray(data));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return characterList;
    }

    public static ArrayList<Character> listFromJson(JSONArray jsonArray) throws JSONException {

        ArrayList<Character> characters = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            characters.add(characterFromJson(jsonObject));
        }

        return characters;
    }

    public static Character characterFromJson(JSONObject jsonObject) throws JSONException {
        Character character = new Character();

        character.setId(jsonObject.getLong("id"));
        character.setName(jsonObject.getString("name"));
        character.setStatus(jsonObject.getString("status"));
        character.setSpecies(jsonObject.getString("species"));
        character.setType(jsonObject.getString("type"));
        character.setGender(jsonObject.getString("gender"));
        character.setOrigin(PlanetController.fromJson(jsonObject.getJSONObject("origin")));
        character.setLocation(PlanetController.fromJson(jsonObject.getJSONObject("location")));
        character.setImage(jsonObject.getString("image"));
        character.setEpisode(JsonUtil.stringListFromJson(jsonObject.getJSONArray("episode")));
        character.setUrl(jsonObject.getString("url"));
        character.setCreated(jsonObject.getString("created"));

        return character;
    }

}
