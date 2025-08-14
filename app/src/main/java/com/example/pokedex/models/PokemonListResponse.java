package com.example.pokedex.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PokemonListResponse {
    @SerializedName("results")
    private List<PokemonListItem> results;

    public List<PokemonListItem> getResults() {
        return results;
    }

}
