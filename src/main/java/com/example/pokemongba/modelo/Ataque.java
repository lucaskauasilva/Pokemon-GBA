package com.example.pokemongba.modelo;

import java.util.Random;

public class Ataque {
    private String nome;
    private String tipo;
    private int poder;
    private int precisao; // de 0 a 100

    public Ataque(String nome, String tipo, int poder, int precisao) {
        this.nome = nome;
        this.tipo = tipo;
        this.poder = poder;
        this.precisao = precisao;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPoder() {
        return poder;
    }

    public int getPrecisao() {
        return precisao;
    }

    public boolean acertou() {
        Random random = new Random();
        int chance = random.nextInt(100) + 1;
        return chance <= precisao;
    }

    public void mostrarAtaque() {
        System.out.println("⚔ " + nome + " | Tipo: " + tipo + " | Poder: " + poder + " | Precisão: " + precisao + "%");
    }
}