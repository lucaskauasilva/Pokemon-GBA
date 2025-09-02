package com.example.pokemongba;

import com.example.pokemongba.modelo.Pokemon;
import com.example.pokemongba.modelo.Ataque;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class TelaBatalhaController {

    @FXML private Label labelStatusP1;
    @FXML private Label labelStatusP2;
    @FXML private ProgressBar barraVidaP1;
    @FXML private ProgressBar barraVidaP2;
    @FXML private Label labelResultado;
    @FXML private ImageView imgPikachu;
    @FXML private ImageView imgBulbasaur;

    private Pokemon pikachu;
    private Pokemon bulbasaur;

    public void initialize() {
        Image pikachuImg = new Image(getClass().getResource("/imagens/pikachu.png").toExternalForm());
        Image bulbasaurImg = new Image(getClass().getResource("/imagens/bulbasaur.png").toExternalForm());
        imgPikachu.setImage(pikachuImg);
        imgBulbasaur.setImage(bulbasaurImg);

        pikachu = new Pokemon("Pikachu", "Elétrico", 5, 100);
        pikachu.adicionarAtaque(new Ataque("Choque do Trovão", "Elétrico", 30, 90, "Paralisia"));
        pikachu.adicionarAtaque(new Ataque("Ataque Rápido", "Normal", 20, 100, "Nenhum"));
        pikachu.adicionarAtaque(new Ataque("Cauda de Ferro", "Aço", 35, 85, "Nenhum"));

        bulbasaur = new Pokemon("Bulbasaur", "Planta", 5, 100);
        bulbasaur.adicionarAtaque(new Ataque("Chicote de Vinha", "Planta", 25, 95, "Nenhum"));
        bulbasaur.adicionarAtaque(new Ataque("Investida", "Normal", 20, 100, "Nenhum"));
        bulbasaur.adicionarAtaque(new Ataque("Pó do Sono", "Planta", 0, 80, "Sono")); // efeito futuro
        bulbasaur.adicionarAtaque(new Ataque("Poison", "Poison", 0, 80, "Envenenamento"));

        atualizarStatus();
    }

    private void animarAtaque(ImageView atacante) {
        TranslateTransition transicao = new TranslateTransition(Duration.millis(200), atacante);
        transicao.setByX(30); // avança
        transicao.setAutoReverse(true);
        transicao.setCycleCount(2); // vai e volta
        transicao.play();
    }

    private void animarDano(ImageView defensor) {
        TranslateTransition tremor = new TranslateTransition(Duration.millis(50), defensor);
        tremor.setByX(10);
        tremor.setAutoReverse(true);
        tremor.setCycleCount(6); // tremor rápido
        tremor.play();
    }

    private void usarAtaque(int index) {
        animarAtaque((imgPikachu));
        Ataque ataque = pikachu.getAtaques().get(index);
        String resultado = "Pikachu usou " + ataque.getNome() + "!\n";

        if (ataque.acertou()) {
            bulbasaur.receberDano(ataque.getPoder());
            animarDano(imgBulbasaur);
            resultado += "✨ O ataque acertou! Bulbasaur perdeu " + ataque.getPoder() + " de vida.";
        } else {
            resultado += "😵 O ataque errou";
        }

        labelResultado.setText(resultado);
        atualizarStatus();
    }

    @FXML private void usarAtaque1() { usarAtaque(0); }
    @FXML private void usarAtaque2() { usarAtaque(1); }
    @FXML private void usarAtaque3() { usarAtaque(2); }

    private void atualizarStatus() {
        labelStatusP1.setText(pikachu.getNome() + " - Vida: " + pikachu.getVidaAtual() + "/" + pikachu.getVidaMaxima());
        labelStatusP2.setText(bulbasaur.getNome() + "- Vida: " + bulbasaur.getVidaAtual() + "/" + bulbasaur.getVidaMaxima());

        barraVidaP1.setProgress((double) pikachu.getVidaAtual() / pikachu.getVidaMaxima());
        barraVidaP2.setProgress((double) bulbasaur.getVidaAtual() / bulbasaur.getVidaMaxima());
    }
}