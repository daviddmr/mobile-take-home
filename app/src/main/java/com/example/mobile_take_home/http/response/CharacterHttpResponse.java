package com.example.mobile_take_home.http.response;

import com.example.mobile_take_home.model.Character;

import java.util.ArrayList;

public class CharacterHttpResponse extends HttpResponse {

    private ArrayList<Character> characters;

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }

}
