package com.example.mobile_take_home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.mobile_take_home.controller.EpisodeHttpReponseController;
import com.example.mobile_take_home.http.HttpResponseInterface;
import com.example.mobile_take_home.http.request.HttpRequest;
import com.example.mobile_take_home.http.response.EpisodeHttpResponse;
import com.example.mobile_take_home.model.Episode;
import com.example.mobile_take_home.util.ShowMessageUtil;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements HttpResponseInterface {

    private RelativeLayout mainLayout;
    private ProgressBar progressBar;

    private EpisodesAdapter adapter;
    private ArrayList<Episode> episodeList = new ArrayList<>();
    private String urlNextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupComponents();
        callRequestEpisodes("https://rickandmortyapi.com/api/episode/");
    }

    private void setupComponents() {
        mainLayout = findViewById(R.id.main_layout);
        progressBar = findViewById(R.id.pb);
        RecyclerView recyclerView = findViewById(R.id.rv_episodes);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new EpisodesAdapter(this, episodeList, episodeClickListener());
        recyclerView.setAdapter(adapter);
    }

    private void callRequestEpisodes(String url) {
        progressBar.setVisibility(View.VISIBLE);
        HttpRequest request = new HttpRequest(this);
        request.execute(url);
    }

    private void updateList(ArrayList<Episode> episodes) {
        int lastIndex = episodeList.size();
        episodeList.addAll(episodes);
        adapter.notifyItemRangeInserted(lastIndex, episodes.size());
    }

    @Override
    public void onSuccess(String response) {
        EpisodeHttpResponse episodeResponse = EpisodeHttpReponseController.fromJson(response);
        urlNextPage = episodeResponse.getInfo().getNext();
        updateList(episodeResponse.getEpisodes());
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError() {
        ShowMessageUtil.longSnackBar(mainLayout, "Erro!!!");
        progressBar.setVisibility(View.GONE);
    }

    private View.OnClickListener episodeClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = view.getId();
                Log.d("TAG", "onClick - Pos:" + position);
            }
        };
    }
}
