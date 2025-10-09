package com.example.pokemongba;

import com.example.pokemongba.modelo.Batalha;
import com.example.pokemongba.modelo.Inventario;
import eu.hansolo.toolbox.evt.type.InvalidationEvt;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class TelaCapturaController {

    @FXML private ImageView pokebolaImagem;
    @FXML private Label labelContagem;

    private int etapa = 0;

    public void initialize() {
        Image imgPokebola = new Image(getClass().getResource("/imagens/pokebola_aberta.png").toExternalForm());
        pokebolaImagem.setImage(imgPokebola);
        iniciarCaptura();
    }

    public void iniciarCaptura() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> atualizarEtapa("1...")),
                new KeyFrame(Duration.seconds(2), e -> atualizarEtapa("2...")),
                new KeyFrame(Duration.seconds(3), e -> atualizarEtapa("3...")),
                new KeyFrame(Duration.seconds(4), e -> finalizarCaptura())
        );
        timeline.play();
    }

    public void atualizarEtapa(String texto) {
        labelContagem.setText(texto);
    }

    public void finalizarCaptura() {
        boolean sucesso = Math.random() < 0.6;

        if(sucesso) {
            pokebolaImagem.setImage(new Image("/imagens/pokebola_click.png"));
            labelContagem.setText("🎉 Capturado!");
            AudioClip som = new AudioClip(getClass().getResource("/sounds/click.wav").toExternalForm());
            som.play();

            // Adiciona o inventário e vai pra tela de inventário
            Inventario.getInstancia().adicionarPokemon(Batalha.getInstancia().getP2());
            irParaInventario();
        } else {
            labelContagem.setText("💨 Escapou!");
            pokebolaImagem.setImage(new Image("/imagens/pokebola_aberta.png"));
        }
    }

    private void irParaInventario() {
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(e -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/pokemongba/TelaInventario.fxml"));
                Stage stage = (Stage) labelContagem.getScene().getWindow();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        });
        delay.play();
    }
}
