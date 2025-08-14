package com.example.pokedex.models;

import com.google.gson.annotations.SerializedName;

public class Sprites {
    @SerializedName("other")
    private Other other;

    public Other getOther() {
        return other;
    }
}
