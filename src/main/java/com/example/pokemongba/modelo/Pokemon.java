package com.example.pokemongba.modelo;

public class Pokemon {
    /* Atributos Principais */
    private String nome;
    private String tipo;
    private int nivel;
    private int vidaMaxima;
    private int vidaAtual;

    /* Construtor */
    public Pokemon(String nome, String tipo, int nivel, int vidaMaxima) {
        this.nome = nome;
        this.tipo = tipo;
        this.nivel = nivel;
        this.vidaMaxima = vidaMaxima;
        this.vidaAtual = vidaMaxima; // Começa com vida cheia
    }

    /* Getters */
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

    /* Exibir Status */
    public void mostrarStatus() {
        System.out.println("🐾 " + nome + " | Tipo: " + tipo + " | Nível: " + nivel);
        System.out.println("❤ vida: " + vidaAtual + "/" +vidaMaxima + "\n");
    }
}