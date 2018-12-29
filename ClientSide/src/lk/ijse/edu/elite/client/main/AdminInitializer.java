package lk.ijse.edu.elite.client.main;

import javafx.application.Application;
import javafx.stage.Stage;

public class AdminInitializer extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        new AppInitializer().uiInitializer(primaryStage, "/lk/ijse/edu/elite/client/view/Login.fxml","Admin Login");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
