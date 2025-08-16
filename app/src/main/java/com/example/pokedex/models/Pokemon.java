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

    @SerializedName("height")
    private int height;

    @SerializedName("weight")
    private int weight;

    @SerializedName("types")
    private List<TypeSlot> types;

    public int getId() { return id; }
    public String getName() { return name; }
    public Sprites getSprites() { return sprites; }
    public List<PokemonStat> getStats() { return stats; }
    public int getHeight() { return height; }
    public int getWeight() { return weight; }
    public List<TypeSlot> getTypes() { return types; }

    public String getNumber() {
        return String.format("#%03d", id);
    }

    public static class TypeSlot {
        @SerializedName("slot")
        private int slot;

        @SerializedName("type")
        private Type type;

        public int getSlot() { return slot; }
        public Type getType() { return type; }
    }

    public static class Type {
        @SerializedName("name")
        private String name;

        @SerializedName("url")
        private String url;

        public String getName() { return name; }
        public String getUrl() { return url; }
    }
}
