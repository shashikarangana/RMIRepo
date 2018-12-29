package lk.ijse.edu.elite.client.main;

import javafx.application.Application;
import javafx.stage.Stage;

public class ChefAreaInitailizer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new AppInitializer().uiInitializer(primaryStage, "/lk/ijse/edu/elite/client/view/Chef.fxml","Chef Area");
    }
}
