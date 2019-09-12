package com.example.mobile_take_home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.mobile_take_home.http.HttpResponseInterface;
import com.example.mobile_take_home.http.request.HttpRequest;
import com.example.mobile_take_home.model.Episode;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements HttpResponseInterface {

    private RelativeLayout mainLayout;

    private EpisodesAdapter adapter;
    private ArrayList<Episode> episodeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupComponents();
        callRequestEpisodes("https://rickandmortyapi.com/api/episode/");
    }

    private void setupComponents() {
        mainLayout = findViewById(R.id.main_layout);
        RecyclerView recyclerView = findViewById(R.id.rv_episodes);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new EpisodesAdapter(this, episodeList, episodeClickListener());
        recyclerView.setAdapter(adapter);
    }

    private void callRequestEpisodes(String url) {
        HttpRequest request = new HttpRequest(this);
        request.execute(url);
    }

    @Override
    public void onSuccess(String response) {
        Log.d("TAG", "onSuccess");
    }

    @Override
    public void onError() {
        Log.d("TAG", "onError");
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
