package lk.ijse.edu.elite.client.main;

import javafx.application.Application;
import javafx.stage.Stage;

public class ReseptionInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent load = FXMLLoader.load(getClass().getResource("/lk/ijse/edu/elite/view/Reseption.fxml"));
//        Scene scene=new Scene(load);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Reseption Area");
//        primaryStage.show();
        AppInitializer app=new AppInitializer();
        app.uiInitializer(primaryStage, "/lk/ijse/edu/dinemore/client/view/Reseption.fxml","Reseption Area");
    }
}
