package lk.ijse.edu.elite.client.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.ijse.edu.elite.client.main.AppInitializer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdministrationController implements Initializable {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Pane btnHomePane;

    @FXML
    private Pane btnFoodPane;

    @FXML
    private Pane btnReceptionClick;

    @FXML
    private Pane btnLogout;

    @FXML
    private Pane btnChefPane;

    @FXML
    private Pane btnDeliverPane;

    @FXML
    private Pane btnReportPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void FoodOnClick(MouseEvent event) {
        try {
            new AppInitializer().loadPane("/lk/ijse/edu/elite/client/view/Food.fxml",this.rootPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void chefOnMouceClick(MouseEvent event) {
        try {
            new AppInitializer().loadPane("/lk/ijse/edu/elite/client/view/ChefMgt.fxml",this.rootPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deliverOnClick(MouseEvent event) {
        try {
            new AppInitializer().loadPane("/lk/ijse/edu/elite/client/view/DeliveryBikes.fxml",this.rootPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void homeClick(MouseEvent event) {
        try {
            new AppInitializer().loginInitializer(this.rootPane,"/lk/ijse/edu/elite/client/view/Administration.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logout(MouseEvent event) {
        try {
            new AppInitializer().loginInitializer(this.rootPane,"/lk/ijse/edu/elite/client/view/Login.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void receptionOnClick(MouseEvent event) {
        try {
            new AppInitializer().loadPane("/lk/ijse/edu/elite/client/view/ReceptionMgt.fxml",this.rootPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void reportOnClick(MouseEvent event) {
        try {
            new AppInitializer().loadPane("/lk/ijse/edu/elite/client/view/Reports.fxml",this.rootPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void customerOnClick(MouseEvent mouseEvent) {
        try {
            new AppInitializer().loadPane("/lk/ijse/edu/elite/client/view/CustomerMgt.fxml",this.rootPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void orderStatus(MouseEvent mouseEvent) {
        try {
            new AppInitializer().popUp("/lk/ijse/edu/elite/client/view/OrderStatus.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
