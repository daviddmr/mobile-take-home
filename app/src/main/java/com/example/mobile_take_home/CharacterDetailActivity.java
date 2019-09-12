package com.example.mobile_take_home;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mobile_take_home.model.Character;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CharacterDetailActivity extends AppCompatActivity {

    public static final String ARG_CHARACTER = "arg_character";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        Character character = getIntent().getParcelableExtra(ARG_CHARACTER);

        setupToolbar(character.getName());
        setupComponents();
        TextView tvName = findViewById(R.id.tv_name);
        tvName.setText(character.getName());
    }

    private void setupToolbar(String title) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
    }

}
