package com.example.pokedex.models;

import com.google.gson.annotations.SerializedName;

public class PokemonListItem {
    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    public String getName() { return name; }
    public String getUrl() { return url; }

    public int getIdFromUrl() {
        String[] parts = url.split("/");
        return Integer.parseInt(parts[parts.length - 1].isEmpty()
                ? parts[parts.length - 2]
                : parts[parts.length - 1]);
    }

    public String getImageUrl() {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + getIdFromUrl() + ".png";
    }
}


