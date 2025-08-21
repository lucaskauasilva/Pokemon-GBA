package com.example.pokemongba;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    @Override
    public void start(Stage janela) throws IOException {
        System.out.println(getClass().getResource("/com/example/pokemongba/TelaBatalha.fxml"));

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/pokemongba/TelaBatalha.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        janela.setTitle("Pokémon Battle Game");
        janela.setScene(scene);
        janela.show();
    }

    public static void main(String[] args) {
        launch();
    }
}