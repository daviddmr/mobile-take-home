package com.example.mobile_take_home;

import android.os.Bundle;
import android.widget.TextView;

import com.example.mobile_take_home.model.Character;

import androidx.appcompat.app.AppCompatActivity;

public class CharacterDetailActivity extends AppCompatActivity {

    public static final String ARG_CHARACTER = "arg_character";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);
        setupComponents();

        Character character = getIntent().getParcelableExtra(ARG_CHARACTER);
        TextView tvName = findViewById(R.id.tv_name);
        tvName.setText(character.getName());
    }

    private void setupComponents() {
    }

}
