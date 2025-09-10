package com.example.pokemongba.modelo;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Pokemon> pokemonsCapturados = new ArrayList<>();

    public void adicionarPokemon(Pokemon pokemon) {
        pokemonsCapturados.add(pokemon);
    }

    public List<Pokemon> getTodos() {
        return pokemonsCapturados;
    }

    public boolean estaVazio() {
        return pokemonsCapturados.isEmpty();
    }
}
