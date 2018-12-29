package lk.ijse.edu.elite.client.main;

import javafx.application.Application;
import javafx.stage.Stage;

public class LoginInitializer extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        new AppInitializer().uiInitializer(primaryStage,"/lk/ijse/edu/elite/client/view/LoginForOthers.fxml","Login for Other");

    }

    public static void main(String[] args) {
        launch(args);
    }
}
