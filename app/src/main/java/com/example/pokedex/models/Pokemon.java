package com.example.pokedex.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Pokemon {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("sprites")
    private Sprites sprites;

    @SerializedName("stats")
    private List<PokemonStat> stats;

    public int getId() { return id; }
    public String getName() { return name; }
    public Sprites getSprites() { return sprites; }
    public List<PokemonStat> getStats() { return stats; }

    // Número formateado
    public String getNumber() {
        return String.format("#%03d", id);
    }
}
