package lk.ijse.edu.elite.client.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import lk.ijse.edu.elite.client.common.Validation;
import lk.ijse.edu.elite.client.proxy.ProxyHandler;
import lk.ijse.edu.elite.common.dto.ChefDTO;
import lk.ijse.edu.elite.common.observer.Observer;
import lk.ijse.edu.elite.common.service.ServiceFactory;
import lk.ijse.edu.elite.common.service.service.custom.ChefService;
import lk.ijse.edu.elite.common.service.service.custom.IDGeneratinService;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ChefManagementController implements Initializable,Observer,Remote {
    @FXML
    private JFXTextField txtChefSearch;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXTextField txtChefID;

    @FXML
    private JFXTextField txtChefName;

    @FXML
    private JFXTextField txtChefContact;

    @FXML
    private JFXTextField txtChefAddres;

    @FXML
    private JFXTextField txtChefNIC;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private TableView<ChefDTO> tblChef;

    @FXML
    private JFXButton btnRemove;
    private static ChefService chefService;
    private ArrayList<String> nic;
    private static IDGeneratinService idGeneratinService;
    static {
        try {
            chefService = (ChefService) ProxyHandler.getInstance().getSuperService(ServiceFactory.ServiceTypes.CHEF);
            idGeneratinService = (IDGeneratinService)ProxyHandler.getInstance().getSuperService(ServiceFactory.ServiceTypes.ID);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ChefManagementController() throws RemoteException {

        try {
            UnicastRemoteObject.exportObject(this,0);
            chefService.register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            tblLoadChef();
        } catch (Exception e) {
            e.printStackTrace();
        }
        getAllChefDetails();
        setChefID();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                loadChef();
            }
        }, new Date(), 5000);


    }

    @FXML
    void cancel(ActionEvent event) {
        if (!txtChefID.getText().trim().isEmpty()){
            try {
                chefService.released(txtChefID.getText());
                chefService.unregister(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        clearText();
        setChefID();
    }

    void setChefID() {
        try {
            String newID = idGeneratinService.getNewID("Chef", "chef_ID", "CH");
            txtChefID.setText(newID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updateChef(ActionEvent event) {
        if(Validation.nameValidate(txtChefName.getText())) {
            if(Validation.addressValidate(txtChefAddres.getText())){
                if(Validation.telephoneValidate(txtChefContact.getText())){
                    if(Validation.nicValidate(txtChefNIC.getText())){
                        try {
                            boolean b = chefService.updateChef(new ChefDTO(txtChefID.getText(), txtChefName.getText(), txtChefContact.getText(), txtChefAddres.getText(), txtChefNIC.getText()));
                            if (b) {
                                Notifications notificationsn = Notifications.create().title("Chef Management").text("Chef is Updated!").hideAfter(Duration.seconds(4)).position(Pos.BOTTOM_CENTER);
                                notificationsn.darkStyle();
                                notificationsn.showInformation();
//                                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Chef Updated !", ButtonType.OK);
//                                a.show();
                                chefService.released(txtChefID.getText());
                                chefService.unregister(this);
                                getAllChefDetails();
                                setChefID();
                                clearText();
                            }
                        } catch (Exception e) {
                            Notifications notificationsn = Notifications.create().title("Chef Management").text("Chef is not Updated!").hideAfter(Duration.seconds(4)).position(Pos.BOTTOM_CENTER);
                            notificationsn.darkStyle();
                            notificationsn.showError();
//                            Alert a = new Alert(Alert.AlertType.ERROR, "Chef not Updated !", ButtonType.OK);
//                            a.show();
                        }
                    }else {
                        txtChefNIC.requestFocus();
                        txtChefNIC.setFocusColor(Color.RED);
                    }
                }else {
                    txtChefContact.requestFocus();
                    txtChefContact.setFocusColor(Color.RED);
                }
            }else{
                txtChefAddres.requestFocus();
                txtChefAddres.setFocusColor(Color.RED);
            }
        }else {
            txtChefName.requestFocus();
            txtChefName.setFocusColor(Color.RED);
        }
    }

    void loadChef() {
        TextFields.bindAutoCompletion(txtChefSearch, nic);
    }

    @FXML
    void removeChef(ActionEvent event) {
        if (!txtChefID.getText().trim().isEmpty()) {
            try {
                boolean b = chefService.deleteChef(new ChefDTO(txtChefID.getText(),txtChefName.getText(),txtChefContact.getText(),txtChefAddres.getText(),txtChefNIC.getText()));
                if (b) {
                    Notifications notificationsn = Notifications.create().title("Chef Management").text("Chef is Deleted!").hideAfter(Duration.seconds(4)).position(Pos.BOTTOM_CENTER);
                    notificationsn.darkStyle();
                    notificationsn.showInformation();
                    getAllChefDetails();
                    setChefID();
                    clearText();
                }
            } catch (Exception e) {
                Notifications notificationsn = Notifications.create().title("Chef Management").text("Chef is not Deleted!").hideAfter(Duration.seconds(4)).position(Pos.BOTTOM_CENTER);
                notificationsn.darkStyle();
                notificationsn.showError();
            }
        }else {
            Notifications notificationsn = Notifications.create().title("Chef Management").text("Please Select Chef to delete !").hideAfter(Duration.seconds(4)).position(Pos.BOTTOM_CENTER);
            notificationsn.darkStyle();
            notificationsn.showError();

//            Alert a=new Alert(Alert.AlertType.ERROR,"Please Select Chef to delete !");
//            a.show();
        }
    }

    @FXML
    void searchChef(ActionEvent event) {
        try {
            ChefDTO chefDTO = chefService.searchChef(txtChefSearch.getText());
            if (chefDTO != null) {
                txtChefID.setText(chefDTO.getChef_ID());
                chefService.reserved(txtChefID.getText());
                txtChefName.setText(chefDTO.getChef_Name());
                txtChefAddres.setText(chefDTO.getChef_address());
                txtChefNIC.setText(chefDTO.getNic());
                txtChefContact.setText(chefDTO.getChef_Contact());
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "We didn't find any thing...!", ButtonType.OK);
                a.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void saveChef(ActionEvent event) {
        if(Validation.nameValidate(txtChefName.getText())) {
            if(Validation.addressValidate(txtChefAddres.getText())){
                if(Validation.telephoneValidate(txtChefContact.getText())){
                    if(Validation.nicValidate(txtChefNIC.getText())){
                        try {
                            boolean b = chefService.addChef(new ChefDTO(txtChefID.getText(), txtChefName.getText(), txtChefContact.getText(), txtChefAddres.getText(), txtChefNIC.getText()));
                            if (b) {
                                Notifications notificationsn = Notifications.create().title("Chef Management").text("Chef is Saved !").hideAfter(Duration.seconds(4)).position(Pos.BOTTOM_CENTER);
                                notificationsn.darkStyle();
                                notificationsn.showInformation();
//                                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Chef Saved !", ButtonType.OK);
//                                a.show();
                                chefService.released(txtChefID.getText());
                                chefService.unregister(this);
                                getAllChefDetails();
                                setChefID();
                                clearText();
                            }

                        } catch (Exception e) {
                            Notifications notificationsn = Notifications.create().title("Chef Management").text("Chef is Not Saved !").hideAfter(Duration.seconds(4)).position(Pos.BOTTOM_CENTER);
                            notificationsn.darkStyle();
                            notificationsn.showError();
//                            Alert a = new Alert(Alert.AlertType.ERROR, "Chef not Saved !", ButtonType.OK);
//                            a.show();
                        }
                    }else {
                        txtChefNIC.requestFocus();
                        txtChefNIC.setFocusColor(Color.RED);
                    }
                }else {
                    txtChefContact.requestFocus();
                    txtChefContact.setFocusColor(Color.RED);
                }
            }else{
                txtChefAddres.requestFocus();
                txtChefAddres.setFocusColor(Color.RED);
            }
        }else {
            txtChefName.requestFocus();
            txtChefName.setFocusColor(Color.RED);
        }
    }

    @FXML
    void tblClicked(MouseEvent event) {
        if (tblChef.getSelectionModel().getSelectedIndex() != -1) {
            ChefDTO selectedItem = tblChef.getSelectionModel().getSelectedItem();
            txtChefID.setText(selectedItem.getChef_ID());
            try {
                chefService.reserved(txtChefID.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
            txtChefName.setText(selectedItem.getChef_Name());
            txtChefAddres.setText(selectedItem.getChef_address());
            txtChefNIC.setText(selectedItem.getNic());
            txtChefContact.setText(selectedItem.getChef_Contact());
        }
    }

    private void getAllChefDetails() {
        try {
            ArrayList<ChefDTO> all = chefService.getAll();
            tblChef.setItems(FXCollections.observableArrayList(all));
            nic = new ArrayList<>();
            for (ChefDTO d : all) {
                nic.add(d.getNic());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void clearText() {
//        txtChefID.clear();
        txtChefName.clear();
        txtChefAddres.clear();
        txtChefNIC.clear();
        txtChefContact.clear();
    }

    private void tblLoadChef() throws Exception {

        tblChef.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("chef_ID"));
        tblChef.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("chef_Name"));
        tblChef.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("chef_Contact"));
        tblChef.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("chef_address"));
        tblChef.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblChef.setItems(FXCollections.observableArrayList(chefService.getAll()));

    }

    @Override
    public void update(String message) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    try {
                        Alert a = new Alert(Alert.AlertType.CONFIRMATION, message);
                        a.show();
                        getAllChefDetails();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }).start();
    }
}
