package com.example.pokedex.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokedex.R;
import com.example.pokedex.activities.PokemonDetailActivity;
import com.example.pokedex.models.PokemonListItem;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {

    private List<PokemonListItem> pokemonList;
    private Context context;

    public PokemonAdapter(List<PokemonListItem> pokemonList, Context context) {
        this.pokemonList = pokemonList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PokemonListItem pokemon = pokemonList.get(position);
        int pokemonId = position + 1;

        String formattedNumber = String.format("#%03d", pokemonId);
        String imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemonId + ".png";

        holder.tvName.setText(capitalize(pokemon.getName()));
        holder.tvNumber.setText(formattedNumber);

        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_pokemon_placeholder)
                .into(holder.ivPokemon);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PokemonDetailActivity.class);
            intent.putExtra("pokemon_name", pokemon.getName());
            intent.putExtra("pokemon_id", pokemonId); // ID real
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvNumber;
        ImageView ivPokemon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            ivPokemon = itemView.findViewById(R.id.ivPokemon);
        }
    }

    private String capitalize(String name) {
        if (name == null || name.isEmpty()) return name;
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
