package lk.ijse.edu.elite.client.main;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class AppInitializer {
    public void uiInitializer(Stage stage, String url, String title) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource(url));
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
        stage.setResizable(false);
        stage.centerOnScreen();
    }

    public void loginInitializer(AnchorPane pane, String url) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource(url));
        Stage stage = (Stage) (pane.getScene().getWindow());
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
    }

    public void loadPane(String url, AnchorPane anchorPane) throws IOException {
        AnchorPane pane = null;
        pane = FXMLLoader.load(this.getClass().getResource(url));

        anchorPane.getChildren().setAll(pane);
        Scene scene = anchorPane.getScene();
        FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), anchorPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }
    public void popUp(String url) throws IOException {
        Parent parent=FXMLLoader.load(getClass().getResource(url));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
}
