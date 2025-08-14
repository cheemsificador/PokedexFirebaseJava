package com.example.pokedex.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.pokedex.R;
import com.example.pokedex.api.ApiClient;
import com.example.pokedex.api.PokeApiService;
import com.example.pokedex.models.Pokemon;
import com.example.pokedex.models.PokemonStat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonDetailActivity extends AppCompatActivity {

    private ImageView pokemonImage;
    private TextView pokemonName, pokemonNumber, pokemonDescription, pokemonStats, pokemonGeneration;
    private PokeApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        pokemonImage = findViewById(R.id.pokemonImage);
        pokemonName = findViewById(R.id.pokemonName);
        pokemonNumber = findViewById(R.id.pokemonNumber);
        pokemonDescription = findViewById(R.id.pokemonDescription);
        pokemonStats = findViewById(R.id.pokemonStats);
        pokemonGeneration = findViewById(R.id.pokemonGeneration);

        service = ApiClient.getClient().create(PokeApiService.class);

        String pokemonNameExtra = getIntent().getStringExtra("pokemon_name");

        service.getPokemon(pokemonNameExtra).enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Pokemon p = response.body();

                    pokemonName.setText(capitalize(p.getName()));
                    pokemonNumber.setText(p.getNumber());

                    if (p.getSprites() != null && p.getSprites().getOther() != null
                            && p.getSprites().getOther().getOfficialArtwork() != null) {
                        Glide.with(PokemonDetailActivity.this)
                                .load(p.getSprites().getOther().getOfficialArtwork().getFrontDefault())
                                .placeholder(R.drawable.ic_pokemon_placeholder)
                                .into(pokemonImage);
                    }

                    List<PokemonStat> stats = p.getStats();
                    if (stats != null) {
                        StringBuilder statsText = new StringBuilder();
                        for (PokemonStat stat : stats) {
                            statsText.append(capitalize(stat.getStat().getName()))
                                    .append(": ")
                                    .append(stat.getBaseStat())
                                    .append("\n");
                        }
                        pokemonStats.setText(statsText.toString());
                    } else {
                        pokemonStats.setText("Estadísticas no disponibles");
                    }

                    // Descripción inventada (puedes personalizarla como quieras)
                    pokemonDescription.setText(createCustomDescription(p));

                    // Generación estimada por ID (rango básico)
                    pokemonGeneration.setText("Generación: " + getGenerationById(p.getId()));

                } else {
                    Toast.makeText(PokemonDetailActivity.this, "Error al obtener datos del Pokémon", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Toast.makeText(PokemonDetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String createCustomDescription(Pokemon p) {
        // Aquí puedes generar una descripción basada en nombre, tipos, stats, etc.
        // Por ejemplo:
        return "El Pokémon " + capitalize(p.getName()) + " es fuerte y resistente. ¡Prepárado para la batalla!";
    }

    private String getGenerationById(int id) {
        if (id >= 1 && id <= 151) return "Generación 1";
        else if (id <= 251) return "Generación 2";
        else if (id <= 386) return "Generación 3";
        else if (id <= 493) return "Generación 4";
        else if (id <= 649) return "Generación 5";
        else if (id <= 721) return "Generación 6";
        else if (id <= 809) return "Generación 7";
        else if (id <= 905) return "Generación 8";
        else if (id <= 1025) return "Generación 9";
        else return "Generación desconocida";
    }

    private String capitalize(String text) {
        if (text == null || text.isEmpty()) return text;
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}
