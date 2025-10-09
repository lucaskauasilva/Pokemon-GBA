package com.example.pokemongba.modelo;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Batalha {
    private static Batalha instancia;
    private Pokemon p1;
    private Pokemon p2;
    private Scanner scanner = new Scanner(System.in);

    public Batalha(Pokemon p1, Pokemon p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public static void criarInstancia(Pokemon p1, Pokemon p2) {
        instancia = new Batalha(p1, p2);
    }

    /* Getters */
    public Pokemon getP1() {
        return p1;
    }
    public Pokemon getP2() {
        return p2;
    }
    public static Batalha getInstancia() {
        return instancia;
    }

    public void iniciar() {
        System.out.println("🔥 Batalha iniciada entre " + p1.getNome() + " e " + p2.getNome());

        while (p1.getVidaAtual() > 0 && p2.getVidaAtual() > 0) {
            turno(p1, p2);
            if (p2.getVidaAtual() <= 0) break;

            turno(p2, p1);
        }

        System.out.println("\n🏁 Batalha encerrada!");
        if (p1.getVidaAtual() <= 0) {
            System.out.println(p1.getNome() + " foi derrotado!");
        } else {
            System.out.println(p2.getNome() + " foi derrotado!");
        }
    }

    public void turnoJogador(int index) {
        if (p1.getVidaAtual() <= 0 || p2.getVidaAtual() <= 0) {
            System.out.println("A batalha já terminou.");
            return;
        }

        // Verifica se o índice é válido
        if (index < 0 || index >= p1.getAtaques().size()) {
            System.out.println("Ataque inválido.");
            return;
        }

        Ataque ataqueEscolhido = p1.getAtaques().get(index);
        System.out.println(p1.getNome() + " usou " + ataqueEscolhido.getNome() + "!");

        aplicarAtaque(p1, p2, ataqueEscolhido);

        if (p2.getVidaAtual() <= 0) {
            System.out.println(p2.getNome() + " foi derrotado!");
        }
    }

    public void turnoIA() {
        if (p2.getVidaAtual() <= 0 || p1.getVidaAtual() <= 0) {
            System.out.println("A batalha já terminou.");
            return;
        }

        // Verifica se o oponente está dormindo
        if (p2.estaDormindo()) {
            System.out.println(p2.getNome() + " está dormindo e não pode atacar!");
            p2.reduzirTurnoSono();
            return;
        }

        // verifica se o oponente está paralisado
        if (p2.estaParalisado()) {
            if (Math.random() < 0.25) {
                System.out.println(p2.getNome() + " está paralisado e não pode atacar!");
                return;
            }
        }

        // Escolhe um ataque aleatório
        List<Ataque> ataques = p2.getAtaques();
        int indexAleatorio = new Random().nextInt(ataques.size());
        Ataque ataqueEscolhido = ataques.get(indexAleatorio);

        System.out.println(p2.getNome() + " usou " + ataqueEscolhido.getNome() + "!");
        aplicarAtaque(p2, p1, ataqueEscolhido);

        if (p1.getVidaAtual() <= 0) {
            System.out.println(p1.getNome() + " foi derrotado!");
        }
    }

    private void aplicarAtaque(Pokemon atacante, Pokemon defensor, Ataque ataque) {
        Random rand = new Random();
        int chance = rand.nextInt(100);

        if (chance < ataque.getPrecisao()) {
            defensor.receberDano(ataque.getPoder());
            System.out.println( atacante.getNome() + " acertou! " + defensor.getNome() + " perdeu " + ataque.getPoder() + " de vida.");
        } else {
            System.out.println("O ataque falhou!");
        }
    }

    private void turno(Pokemon atacante, Pokemon defensor) {
        System.out.println("\n🔁 Turno de " + atacante.getNome());
        atacante.mostrarStatus();
        defensor.mostrarStatus();

        System.out.println("Escolha um ataque: ");
        List<Ataque> listaAtaques = atacante.getAtaques();
        for (int i = 0; i < listaAtaques.size(); i++) {
            System.out.println((i + 1) + " - " + listaAtaques.get(i).getNome());
        }

        int escolha = -1;
        while (escolha < 1 || escolha > listaAtaques.size()) {
            System.out.println("Digite o número do ataque: ");
            try {
                escolha = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException exception) {
                System.out.println("Entrada inválida. Tente novamente.");
            }
        }

        Ataque ataque = atacante.getAtaques().get(escolha - 1); // por enquanto, sempre o primeiro ataque
        System.out.println(atacante.getNome() + " usou " + ataque.getNome());
        if (ataque.acertou()) {
            defensor.receberDano(ataque.getPoder());
            System.out.println("✨ O ataque acertou! " + defensor.getNome() + " perdeu " + ataque.getPoder() + " de vida.");
            if (ataque.getEfeito() != null) {
                switch (ataque.getEfeito()) {
                    case "Paralisia":
                        if (Math.random() < 0.25) {
                            defensor.setParalisado(true);
                            System.out.println(defensor.getNome() + " foi paralisado!");
                        }
                        break;
                    case "Sono":
                        int turnos = (int)(Math.random() * 3) + 1;
                        defensor.setTurnosDormindo(turnos);
                        System.out.println(defensor.getNome() + " caiu no sono por " + turnos + " turnos!");
                        defensor.estaDormindo();
                        break;
                    case "Cura":
                        int cura = ataque.getPoder();
                        atacante.curar(cura);
                        System.out.println(atacante.getNome() + " recuperou " + cura + " de vida!");
                        break;
                    case "Envenenamento":
                        defensor.setEnvenenamento(true);
                        System.out.println(defensor.getNome() + " foi envenenado!");
                        break;
                }
            }
        } else {
            System.out.println("🥴 O ataque errou!");
        }
    }

    public boolean tentarCaptura() {
        if (p2.getVidaAtual() > 0) {
            System.out.print("Você só pode capturar pokémon derrotado.");
            return false;
        }

        double chance = Math.random();
        boolean capturado = chance <= 0.6; // 60% de chance de captura
        if (capturado) {
            System.out.println("🎉 Parabéns você capturou " + p2.getNome() + "!");
        } else {
            System.out.println("💨 O " + p2.getNome() + " escapou da Pokébola!");
        }

        return capturado;
    }


}
