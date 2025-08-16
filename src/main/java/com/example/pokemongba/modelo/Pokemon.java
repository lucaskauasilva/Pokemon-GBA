package com.example.pokemongba.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    /* Atributos Principais */
    private String nome;
    private String tipo;
    private int nivel;
    private int vidaMaxima;
    private int vidaAtual;
    private List<Ataque> ataques = new ArrayList<>();
    private boolean paralisado = false;
    private int turnosDormindo = 0;
    private boolean envenenado = false;

    /* Construtor */
    public Pokemon(String nome, String tipo, int nivel, int vidaMaxima) {
        this.nome = nome;
        this.tipo = tipo;
        this.nivel = nivel;
        this.vidaMaxima = vidaMaxima;
        this.vidaAtual = vidaMaxima; // Começa com vida cheia
    }

    /* Getters and Setters */
    public String getNome() {
        return nome;
    }
    public String getTipo() {
        return tipo;
    }
    public int getNivel() {
        return nivel;
    }
    public int getVidaMaxima() {
        return vidaMaxima;
    }
    public int getVidaAtual() {
        return vidaAtual;
    }
    public List<Ataque> getAtaques() { return ataques; }
    public boolean getParalisado() { return paralisado; }
    public void setParalisia(boolean paralisado) { this.paralisado = paralisado; }
    public int getTurnosDormindo() { return turnosDormindo; }
    public void setTurnosDormindo(int turnosDormindo) { this.turnosDormindo = turnosDormindo; }
    public boolean getEnvenenado() { return envenenado; }
    public void setEnvenenamento(boolean invenenado) { this.envenenado = envenenado; }

    /* Métodos de Ação */
    public void receberDano(int dano) {
        vidaAtual -= dano;
        if (vidaAtual < 0) vidaAtual = 0;
    }

    public void curar(int quantidade) {
        vidaAtual += quantidade;
        if (vidaAtual > vidaMaxima) vidaAtual = vidaMaxima;
    }

    public void subirNivel() {
        nivel ++;
        vidaMaxima += 10;
        vidaAtual = vidaMaxima;
    }

    public void adicionarAtaque(Ataque ataque) {
        ataques.add(ataque);
    }

    /* Exibir Status */
    public void mostrarStatus() {
        System.out.println("🐾 " + nome + " | Tipo: " + tipo + " | Nível: " + nivel);
        System.out.println("❤ vida: " + vidaAtual + "/" +vidaMaxima + "\n");
    }
}