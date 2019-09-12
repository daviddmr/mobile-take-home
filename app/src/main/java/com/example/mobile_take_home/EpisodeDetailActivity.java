package com.example.mobile_take_home;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mobile_take_home.controller.CharacterController;
import com.example.mobile_take_home.http.HttpResponseInterface;
import com.example.mobile_take_home.http.request.HttpRequest;
import com.example.mobile_take_home.model.Character;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EpisodeDetailActivity extends AppCompatActivity implements HttpResponseInterface {

    public static final String ARG_CHARACTERS_URL_LIST = "arg_characters_url_list";

    private ProgressBar progressBar;

    private CharactersAdapter adapter;
    private ArrayList<Character> characterList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode_detail);
        setupComponents();

        ArrayList<String> charactersUrlList = getIntent().getStringArrayListExtra(ARG_CHARACTERS_URL_LIST);
        callRequestCharacters(charactersUrlList);
    }

    private void setupComponents() {
        progressBar = findViewById(R.id.pb);
        RecyclerView rvCharacters = findViewById(R.id.rv_characters);
        rvCharacters.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(this, 2);
        rvCharacters.setLayoutManager(linearLayoutManager);

        adapter = new CharactersAdapter(this, characterList, characterClickListener());
        rvCharacters.setAdapter(adapter);
    }

    private void callRequestCharacters(ArrayList<String> charactersUrlList) {
        progressBar.setVisibility(View.VISIBLE);
        HttpRequest request = new HttpRequest(this);
        request.execute(buildUrl(charactersUrlList));
    }

    private String buildUrl(ArrayList<String> charactersUrlList) {
        StringBuilder ids = new StringBuilder();

        for (String url : charactersUrlList) {
            String characterId = url.substring(url.indexOf("character/") + 10);
            ids.append(characterId);
            ids.append(",");
        }

        return "https://rickandmortyapi.com/api/character/" + ids;
    }

    private void updateList(ArrayList<Character> characters) {
        int lastIndex = characterList.size();
        characterList.addAll(characters);
        adapter.notifyItemRangeInserted(lastIndex, characters.size());
    }

    @Override
    public void onSuccess(String response) {
        updateList(CharacterController.simpleListFromJson(response));
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError() {

    }

    private View.OnClickListener characterClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = view.getId();
            }
        };
    }
}
