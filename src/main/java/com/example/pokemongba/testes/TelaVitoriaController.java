package com.example.pokemongba.testes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TelaVitoriaController {

    @FXML private Label labelMensagem;

    @FXML
    private void voltarParaBatalha(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TelaBatalha.fxml"));
        Stage stage = (Stage) labelMensagem.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

}
