package lk.ijse.edu.elite.client.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import lk.ijse.edu.elite.client.main.AppInitializer;
import lk.ijse.edu.elite.client.proxy.ProxyHandler;
import lk.ijse.edu.elite.common.service.ServiceFactory;
import lk.ijse.edu.elite.common.service.service.custom.UserService;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginForOthersController implements Initializable {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXComboBox cmbuserType;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXPasswordField txtPassword;
    private UserService userService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            userService=(UserService) ProxyHandler.getInstance().getSuperService(ServiceFactory.ServiceTypes.USER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Chef");
        strings.add("Delivery");
        strings.add("Reception");
        cmbuserType.setItems(FXCollections.observableArrayList(strings));

    }

    @FXML
    void login(ActionEvent actionEvent) {
        String selectedItem = (String) cmbuserType.getSelectionModel().getSelectedItem();
        try {
            if(selectedItem.equalsIgnoreCase("Chef")){
                if(userService.searchUser("Chef").getPassword().equalsIgnoreCase(txtPassword.getText())){
                    new AppInitializer().loginInitializer(rootPane,"/lk/ijse/edu/elite/client/view/Chef.fxml");
                }else {
                    txtPassword.requestFocus();
                    txtPassword.setFocusColor(Color.RED);
                }
            }
            if(selectedItem.equalsIgnoreCase("Reception")){
                if(userService.searchUser("Reception").getPassword().equalsIgnoreCase(txtPassword.getText())){
                    new AppInitializer().loginInitializer(rootPane,"/lk/ijse/edu/elite/client/view/Reseption.fxml");
                }else {
                    txtPassword.requestFocus();
                    txtPassword.setFocusColor(Color.RED);
                }
            }
            if(selectedItem.equalsIgnoreCase("Delivery")){
                if(userService.searchUser("Delivery").getPassword().equalsIgnoreCase(txtPassword.getText())){
                    new AppInitializer().loginInitializer(rootPane,"/lk/ijse/edu/elite/client/view/Deliver.fxml");
                }else {
                    txtPassword.requestFocus();
                    txtPassword.setFocusColor(Color.RED);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
