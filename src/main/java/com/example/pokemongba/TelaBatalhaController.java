package com.example.pokemongba;

import com.example.pokemongba.modelo.Batalha;
import com.example.pokemongba.modelo.Pokemon;
import com.example.pokemongba.modelo.Ataque;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
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
    private Batalha batalha;

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

        batalha = new Batalha(pikachu, bulbasaur);

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

        if (batalha.getP2().getVidaAtual() <= 0) {
            mostrarTelaVitoria();
        }
    }

    private void mostrarTelaVitoria() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("TelaVitoria.fxml"));
            Stage stage = (Stage) labelResultado.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML private void usarAtaque1() {
        batalha.turnoJogador(0);
        batalha.turnoIA();

        atualizarInterface();
        atualizarStatus();

        if (batalha.getP2().getVidaAtual() <= 0) {
            mostrarTelaVitoria();
        }
    }
    @FXML private void usarAtaque2() {
        batalha.turnoJogador(1);
        batalha.turnoIA();

        atualizarInterface();
        atualizarStatus();}
    @FXML private void usarAtaque3() {
        batalha.turnoJogador(2);
        batalha.turnoIA();

        atualizarInterface();
        atualizarStatus();
    }

    private void atualizarStatus() {
        labelStatusP1.setText(pikachu.getNome() + " - Vida: " + pikachu.getVidaAtual() + "/" + pikachu.getVidaMaxima());
        labelStatusP2.setText(bulbasaur.getNome() + "- Vida: " + bulbasaur.getVidaAtual() + "/" + bulbasaur.getVidaMaxima());

        barraVidaP1.setProgress((double) pikachu.getVidaAtual() / pikachu.getVidaMaxima());
        barraVidaP2.setProgress((double) bulbasaur.getVidaAtual() / bulbasaur.getVidaMaxima());
    }

    public void atualizarInterface() {
        labelStatusP1.setText(batalha.getP1().mostrarStatus());
        labelStatusP2.setText(batalha.getP2().mostrarStatus());

        barraVidaP1.setProgress(batalha.getP1().getVidaAtual());
        barraVidaP2.setProgress(batalha.getP2().getVidaAtual());
    }
}