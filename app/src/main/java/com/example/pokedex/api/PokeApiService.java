package com.example.pokedex.api;

import com.example.pokedex.models.Pokemon;
import com.example.pokedex.models.PokemonListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeApiService {

    @GET("pokemon")
    Call<PokemonListResponse> getPokemonList(@Query("limit") int limit);

    @GET("pokemon/{name}")
    Call<Pokemon> getPokemon(@Path("name") String name);

}
