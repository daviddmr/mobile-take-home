package com.example.mobile_take_home.http.response;

import com.example.mobile_take_home.model.Episode;

import java.util.ArrayList;

public class EpisodeHttpResponse extends HttpResponse {

    private ArrayList<Episode> episodes;

    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(ArrayList<Episode> episodes) {
        this.episodes = episodes;
    }

}
