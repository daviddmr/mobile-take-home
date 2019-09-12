package com.example.mobile_take_home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mobile_take_home.controller.CharacterController;
import com.example.mobile_take_home.http.HttpResponseInterface;
import com.example.mobile_take_home.http.request.HttpRequest;
import com.example.mobile_take_home.model.Character;
import com.example.mobile_take_home.util.Constants;

import java.util.ArrayList;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EpisodeDetailActivity extends AppCompatActivity implements HttpResponseInterface {

    public static final String ARG_EPISODE_NAME = "arg_episode_name";
    public static final String ARG_CHARACTERS_URL_LIST = "arg_characters_url_list";

    private ProgressBar progressBar;

    private CharactersAdapter adapter;
    private ArrayList<Character> characterList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode_detail);

        ArrayList<String> charactersUrlList = getIntent().getStringArrayListExtra(ARG_CHARACTERS_URL_LIST);
        String episodeName = getIntent().getStringExtra(ARG_EPISODE_NAME);

        setupToolbar(episodeName);
        setupComponents();

        callRequestCharacters(charactersUrlList);
    }

    private void setupToolbar(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupComponents() {
        progressBar = findViewById(R.id.pb);
        RecyclerView rvCharacters = findViewById(R.id.rv_characters);
        rvCharacters.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(this, 2);
        rvCharacters.setLayoutManager(linearLayoutManager);

        adapter = new CharactersAdapter(this, characterList, characterOnClickListener(), characterOnLongClickListener());
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

        return Constants.CHARACTER_LIST_URL + ids;
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

    private View.OnClickListener characterOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = view.getId();
                Intent intent = new Intent(EpisodeDetailActivity.this, CharacterDetailActivity.class);
                intent.putExtra(CharacterDetailActivity.ARG_CHARACTER, characterList.get(position));
                startActivity(intent);
            }
        };
    }

    private View.OnLongClickListener characterOnLongClickListener() {
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int position = view.getId();
                Character character = characterList.get(position);
                showKillCharacterAlertDialog(character, position);

                return true;
            }
        };
    }

    private void showKillCharacterAlertDialog(final Character character, final int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        String title = String.format(getString(R.string.title_dialog_to_kill), character.getName());
        dialog.setTitle(title);

        dialog.setPositiveButton(getString(R.string.yes_answer), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                character.setStatus("Dead");
                adapter.notifyItemChanged(position);
            }
        });

        dialog.setNegativeButton(getString(R.string.no_answer), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), String.format(getString(R.string.no_answer_complement), character.getName()), Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }
}
