package com.example.mobile_take_home.controller;

import com.example.mobile_take_home.model.Episode;
import com.example.mobile_take_home.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EpisodeController {

    public static ArrayList<Episode> listFromJson(JSONArray jsonArray) throws JSONException {

        ArrayList<Episode> episodes = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            episodes.add(episodeFromJson(jsonObject));
        }

        return episodes;
    }

    private static Episode episodeFromJson(JSONObject jsonObject) throws JSONException {
        Episode episode = new Episode();

        episode.setId(jsonObject.getLong("id"));
        episode.setName(jsonObject.getString("name"));
        episode.setEpisode(jsonObject.getString("episode"));
        episode.setUrl(jsonObject.getString("url"));
        episode.setCreated(jsonObject.getString("created"));
        episode.setAirDate(jsonObject.getString("air_date"));
        episode.setCharacters(JsonUtil.stringListFromJson(jsonObject.getJSONArray("characters")));

        return episode;
    }

}
