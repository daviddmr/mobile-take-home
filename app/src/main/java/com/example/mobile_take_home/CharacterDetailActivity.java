package com.example.mobile_take_home;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobile_take_home.http.request.ImageRequest;
import com.example.mobile_take_home.model.Character;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CharacterDetailActivity extends AppCompatActivity {

    public static final String ARG_CHARACTER = "arg_character";

    private ImageView ivCharacterPhoto;
    private TextView tvName;
    private TextView tvStatus;
    private TextView tvSpecies;
    private TextView tvType;
    private TextView tvGender;
    private TextView tvOrigin;
    private TextView tvLocation;

    private Character character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        character = getIntent().getParcelableExtra(ARG_CHARACTER);

        setupToolbar(character.getName());
        setupComponents();
        fillCharactersFields();
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

    private void setupComponents() {
        ivCharacterPhoto = findViewById(R.id.iv_character_photo);
        tvName = findViewById(R.id.tv_name);
        tvStatus = findViewById(R.id.tv_status);
        tvSpecies = findViewById(R.id.tv_species);
        tvType = findViewById(R.id.tv_type);
        tvGender = findViewById(R.id.tv_gender);
        tvOrigin = findViewById(R.id.tv_origin);
        tvLocation = findViewById(R.id.tv_location);
    }

    private void fillCharactersFields() {
        ImageRequest imageRequest = new ImageRequest(ivCharacterPhoto);
        imageRequest.execute(character.getImage());

        tvName.setText(character.getName());
        tvStatus.setText(character.getStatus());
        tvSpecies.setText(character.getSpecies());
        tvType.setText(character.getType());
        tvGender.setText(character.getGender());
        tvOrigin.setText(character.getOrigin().getName());
        tvLocation.setText(character.getLocation().getName());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


}
