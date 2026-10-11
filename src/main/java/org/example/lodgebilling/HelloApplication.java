package org.example.lodgebilling;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 650);
        stage.setTitle("Lodge Billing Lab - ICT261");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) { launch(); }
}