package lk.ijse.edu.elite.client.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import lk.ijse.edu.elite.client.main.AppInitializer;
import lk.ijse.edu.elite.client.proxy.ProxyHandler;
import lk.ijse.edu.elite.common.service.ServiceFactory;
import lk.ijse.edu.elite.common.service.service.custom.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXButton btnLogin;
    private UserService userService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            userService=(UserService) ProxyHandler.getInstance().getSuperService(ServiceFactory.ServiceTypes.USER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void login(ActionEvent event) throws IOException {
        try {
            if(txtUserName.getText().equalsIgnoreCase("Admin")){
                if(userService.searchUser("Admin").getPassword().equalsIgnoreCase(txtPassword.getText())){
                    new AppInitializer().loginInitializer(this.rootPane, "/lk/ijse/edu/elite/client/view/Administration.fxml");
                }else {
                    txtPassword.requestFocus();
                    txtPassword.setFocusColor(Color.RED);
                }
            }else {
                txtUserName.requestFocus();
                txtUserName.setFocusColor(Color.RED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
