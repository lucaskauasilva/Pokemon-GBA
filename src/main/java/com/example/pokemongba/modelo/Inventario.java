package com.example.pokemongba.modelo;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private static Inventario instancia;
    private List<Pokemon> pokemonsCapturados = new ArrayList<>();

    public Inventario() {}

    public static Inventario getInstancia() {
        if (instancia == null) {
            instancia = new Inventario();
        }
        return instancia;
    }

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
