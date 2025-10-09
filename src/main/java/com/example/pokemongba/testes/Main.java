package com.example.pokemongba.testes;

import com.example.pokemongba.modelo.Ataque;
import com.example.pokemongba.modelo.Batalha;
import com.example.pokemongba.modelo.Pokemon;

public class Main {
    public static void main(String[] args) {
        Pokemon charmander = new Pokemon("Charmander", "Fogo", 5, 100);
        charmander.evoluir();

//        Pokemon pikachu = new Pokemon("Pikachu", "Elétrico", 5, 100);
//        pikachu.adicionarAtaque(new Ataque("Choque do Trovão", "Elétrico", 30, 90, "Paralisia"));
//        pikachu.adicionarAtaque(new Ataque("Ataque Rápido", "Normal", 20, 100, "Nenhum"));
//        pikachu.adicionarAtaque(new Ataque("Cauda de Ferro", "Aço", 35, 85, "Nenhum"));
//
//        Pokemon bulbasaur = new Pokemon("Bulbasaur", "Planta", 5, 100);
//        bulbasaur.adicionarAtaque(new Ataque("Chicote de Vinha", "Planta", 25, 95, "Nenhum"));
//        bulbasaur.adicionarAtaque(new Ataque("Investida", "Normal", 20, 100, "Nenhum"));
//        bulbasaur.adicionarAtaque(new Ataque("Pó do Sono", "Planta", 0, 80, "Sono")); // efeito futuro
//        bulbasaur.adicionarAtaque(new Ataque("Poison", "Poison", 0, 80, "Envenenamento"));
//
//        Batalha batalha = new Batalha(pikachu, bulbasaur);
//        batalha.iniciar();

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
