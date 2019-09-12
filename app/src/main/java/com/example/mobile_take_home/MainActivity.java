package com.example.mobile_take_home;

import android.os.Bundle;
import android.util.Log;

import com.example.mobile_take_home.http.HttpResponseInterface;
import com.example.mobile_take_home.http.request.HttpRequest;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements HttpResponseInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callRequestEpisodes("https://rickandmortyapi.com/api/episode/");
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

}
