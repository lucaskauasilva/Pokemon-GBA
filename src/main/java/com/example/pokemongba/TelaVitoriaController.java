package com.example.pokemongba;

import com.example.pokemongba.modelo.Batalha;
import com.example.pokemongba.modelo.Inventario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaVitoriaController {

    @FXML private Label labelMensagem;
    @FXML private Label labelResultado;

    private Batalha batalha;
    private Inventario inventario;

    @FXML
    private void voltarParaBatalha(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TelaBatalha.fxml"));
        Stage stage = (Stage) labelMensagem.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    private void mostrarTelaInventario() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/pokemongba/TelaInventario.fxml"));
            Stage stage = (Stage) labelResultado.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar a tela de inventário.");
        }
    }

    @FXML
    private void tentarCaptura(ActionEvent event) {
        boolean sucesso = batalha.tentarCaptura();

        if (sucesso) {
            inventario.adicionarPokemon(batalha.getP2());
            mostrarTelaInventario();
        } else {
            labelMensagem.setText("O Pokémon escapou!");
        }
    }
}