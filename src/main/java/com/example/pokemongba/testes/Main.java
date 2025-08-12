package com.example.pokemongba.testes;

import com.example.pokemongba.modelo.Pokemon;

public class Main {
    public static void main(String[] args) {
        Pokemon charmander = new Pokemon("Charmander", "Fogo", 5, 100);

        charmander.mostrarStatus();
        charmander.receberDano(30);
        charmander.mostrarStatus();
        charmander.curar(20);
        charmander.mostrarStatus();
        charmander.subirNivel();
        charmander.mostrarStatus();
    }

}
