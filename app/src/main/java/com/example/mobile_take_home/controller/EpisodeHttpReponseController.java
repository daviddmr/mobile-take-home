package com.example.mobile_take_home.controller;

import com.example.mobile_take_home.http.response.EpisodeHttpResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class EpisodeHttpReponseController {

    public static EpisodeHttpResponse fromJson(String data) {
        EpisodeHttpResponse episodeResponse = new EpisodeHttpResponse();

        try {
            JSONObject jsonObject = new JSONObject(data);

            episodeResponse.setInfo(InfoController.fromJson(jsonObject));
            episodeResponse.setEpisodes(EpisodeController.listFromJson(jsonObject.getJSONArray("results")));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return episodeResponse;
    }

}
