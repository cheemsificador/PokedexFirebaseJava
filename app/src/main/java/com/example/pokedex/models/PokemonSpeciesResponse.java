package com.example.pokedex.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PokemonSpeciesResponse {
    @SerializedName("flavor_text_entries")
    private List<FlavorTextEntry> flavorTextEntries;

    @SerializedName("generation")
    private Generation generation;

    public List<FlavorTextEntry> getFlavorTextEntries() { return flavorTextEntries; }
    public Generation getGeneration() { return generation; }

    public static class FlavorTextEntry {
        @SerializedName("flavor_text")
        private String flavorText;

        @SerializedName("language")
        private Language language;

        public String getFlavorText() { return flavorText; }
        public Language getLanguage() { return language; }
    }

    public static class Language {
        @SerializedName("name")
        private String name;

        public String getName() { return name; }
    }

    public static class Generation {
        @SerializedName("name")
        private String name;

        public String getName() { return name; }
    }
}