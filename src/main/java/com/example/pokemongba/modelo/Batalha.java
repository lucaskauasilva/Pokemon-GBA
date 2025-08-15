package com.example.pokemongba.modelo;

import java.util.List;
import java.util.Scanner;

public class Batalha {
    private Pokemon p1;
    private Pokemon p2;
    private Scanner scanner = new Scanner(System.in);

    public Batalha(Pokemon p1, Pokemon p2) {
        this.p1 = p1;
        this.p2 = p2;
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
        } else {
            System.out.println("🥴 O ataque errou!");
        }
    }

}
