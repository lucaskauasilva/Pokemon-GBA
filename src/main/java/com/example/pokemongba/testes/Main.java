package com.example.pokemongba.testes;

import com.example.pokemongba.modelo.Ataque;
import com.example.pokemongba.modelo.Batalha;
import com.example.pokemongba.modelo.Pokemon;

public class Main {
    public static void main(String[] args) {
        Pokemon charmander = new Pokemon("Charmander", "Fogo", 5, 100);

        Pokemon pikachu = new Pokemon("Pikachu", "Elétrico", 5, 100);
        pikachu.adicionarAtaque(new Ataque("Choque do Trovão", "Elétrico", 30, 90));
        pikachu.adicionarAtaque(new Ataque("Ataque Rápido", "Normal", 20, 100));
        pikachu.adicionarAtaque(new Ataque("Cauda de Ferro", "Aço", 35, 85));

        Pokemon bulbasaur = new Pokemon("Bulbasaur", "Planta", 5, 100);
        bulbasaur.adicionarAtaque(new Ataque("Chicote de Vinha", "Planta", 25, 95));
        bulbasaur.adicionarAtaque(new Ataque("Investida", "Normal", 20, 100));
        bulbasaur.adicionarAtaque(new Ataque("Pó do Sono", "Planta", 0, 80)); // efeito futuro

        Batalha batalha = new Batalha(pikachu, bulbasaur);
        batalha.iniciar();

//        charmander.mostrarStatus();
//        charmander.receberDano(30);
//        charmander.mostrarStatus();
//        charmander.curar(20);
//        charmander.mostrarStatus();
//        charmander.subirNivel();
//        charmander.mostrarStatus();

//        Ataque choqueDoTrovao = new Ataque("Choque do Trovão", "Elétrico", 40, 90);
//        choqueDoTrovao.mostrarAtaque();
//        if (choqueDoTrovao.acertou()) {
//            System.out.println("O ataque acertou");
//        } else {
//            System.out.println("O ataque errou!");
//        }
    }

}
