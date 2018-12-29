package lk.ijse.edu.elite.client.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import lk.ijse.edu.elite.client.common.Validation;
import lk.ijse.edu.elite.client.proxy.ProxyHandler;
import lk.ijse.edu.elite.common.dto.DeliveryBikesDTO;
import lk.ijse.edu.elite.common.observer.Observer;
import lk.ijse.edu.elite.common.service.ServiceFactory;
import lk.ijse.edu.elite.common.service.service.custom.DeliveryBikeService;
import lk.ijse.edu.elite.common.service.service.custom.IDGeneratinService;
import org.controlsfx.control.textfield.TextFields;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class DeliveryBikesController implements Initializable,Observer,Remote {
    @FXML
    private JFXTextField txtSearchBiker;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXTextField txtBikerID;

    @FXML
    private JFXTextField txtBikeName;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtBikeNumber;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnCancel;
    private ArrayList<String>deliver;
    @FXML
    private TableView<DeliveryBikesDTO> tblBiker;
    private IDGeneratinService idGeneratinService;
    @FXML
    private JFXButton btnRemove;
    private DeliveryBikeService deliveryBikeService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            UnicastRemoteObject.exportObject(this,0);
             deliveryBikeService = (DeliveryBikeService) ProxyHandler.getInstance().getSuperService(ServiceFactory.ServiceTypes.DELIVERY);
            idGeneratinService = (IDGeneratinService) ProxyHandler.getInstance().getSuperService(ServiceFactory.ServiceTypes.ID);
            deliveryBikeService.register(this);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tblBiker.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("deliverID"));
        tblBiker.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("d_Name"));
        tblBiker.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblBiker.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblBiker.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("bike_number"));
        getAllDeliveryBikers();
        setDeliverID();

        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                loadDeliver();
            }
        },new Date(),5000);
    }

    @FXML
    void cancel(ActionEvent event) {
        clearText();
        setDeliverID();
    }

    @FXML
    void removeBiker(ActionEvent event) {
        try {
            boolean b = deliveryBikeService.deleteDelivery(new DeliveryBikesDTO(txtBikerID.getText(),txtBikeName.getText(),txtBikeNumber.getText(),txtContact.getText(),txtAddress.getText()) );
            if(b){
                Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Delivery Biker deleted !", ButtonType.OK);
                a.show();
                getAllDeliveryBikers();
                setDeliverID();
                clearText();
            }
        } catch (Exception e) {
            Alert a=new Alert(Alert.AlertType.ERROR,"Deliver Biker not deleted !", ButtonType.OK);
            a.show();
        }

    }
    void setDeliverID(){
        try {
            String newID = idGeneratinService.getNewID("Delivery", "deliverID", "B");
            txtBikerID.setText(newID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void loadDeliver(){
        TextFields.bindAutoCompletion(txtSearchBiker,deliver);
    }
    @FXML
    void searchBiker(ActionEvent event) {
        try {
            DeliveryBikesDTO deliveryBikesDTO = deliveryBikeService.searchDelivery(txtSearchBiker.getText());
            if(deliveryBikesDTO!=null){
                txtAddress.setText(deliveryBikesDTO.getAddress());
                txtBikeName.setText(deliveryBikesDTO.getD_Name());
                txtBikeNumber.setText(deliveryBikesDTO.getBike_number());
                txtBikerID.setText(deliveryBikesDTO.getDeliverID());
                txtContact.setText(deliveryBikesDTO.getContact());
            }else{
                Alert a=new Alert(Alert.AlertType.ERROR,"We couldn't find anything !", ButtonType.OK);
                a.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void tblClicked(MouseEvent event) {
        if (tblBiker.getSelectionModel().getSelectedIndex()!=-1){
            DeliveryBikesDTO deliveryBikesDTO = tblBiker.getSelectionModel().getSelectedItem();
            txtAddress.setText(deliveryBikesDTO.getAddress());
            txtBikeName.setText(deliveryBikesDTO.getD_Name());
            txtBikeNumber.setText(deliveryBikesDTO.getBike_number());
            txtBikerID.setText(deliveryBikesDTO.getDeliverID());
            txtContact.setText(deliveryBikesDTO.getContact());
        }
    }
    @FXML
    void updateBiker(ActionEvent event) {
        try {
            boolean b = deliveryBikeService.updateDelivery(new DeliveryBikesDTO(txtBikerID.getText(), txtBikeName.getText(), txtBikeNumber.getText(), txtContact.getText(), txtAddress.getText()));
            if(b){
                Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Delivery Biker updated !", ButtonType.OK);
                a.show();
                getAllDeliveryBikers();
                setDeliverID();
                clearText();
            }
        } catch (Exception e) {
            Alert a=new Alert(Alert.AlertType.ERROR,"Deliver Biker not updated !", ButtonType.OK);
            a.show();
        }
    }
    @FXML
    void saveBiker(ActionEvent event) {
        if(Validation.nameValidate(txtBikeName.getText())) {
            if(Validation.telephoneValidate(txtContact.getText())){
                if(Validation.addressValidate(txtAddress.getText())){
                    if(Validation.bike_number(txtBikeNumber.getText())){
                        try {
                            boolean b = deliveryBikeService.addDelivery(new DeliveryBikesDTO(txtBikerID.getText(), txtBikeName.getText(), txtBikeNumber.getText(), txtContact.getText(), txtAddress.getText()));
                            if(b){
                                Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Delivery Biker Saved !", ButtonType.OK);
                                a.show();
                                getAllDeliveryBikers();
                                setDeliverID();
                                clearText();
                            }
                        } catch (Exception e) {
                            Alert a=new Alert(Alert.AlertType.ERROR,"Deliver Biker not Saved !", ButtonType.OK);
                            a.show();
                        }
                    }else {
                        txtBikeNumber.requestFocus();
                        txtBikeNumber.setFocusColor(Color.RED);
                    }
                }else {
                    txtAddress.requestFocus();
                    txtAddress.setFocusColor(Color.RED);
                }
            }else{
                txtContact.requestFocus();
                txtContact.setFocusColor(Color.RED);
            }
        }else {
            txtBikeName.requestFocus();
            txtBikeName.setFocusColor(Color.RED);
        }
    }
    private void getAllDeliveryBikers(){
        try {
            ArrayList<DeliveryBikesDTO> all = deliveryBikeService.getAll();
            tblBiker.setItems(FXCollections.observableArrayList(all));
            deliver=new ArrayList<>();
            for (DeliveryBikesDTO b:all){
                deliver.add(b.getDeliverID());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void clearText(){
        txtAddress.clear();
        txtBikeName.clear();
        txtBikeNumber.clear();
//        txtBikerID.clear();
        txtContact.clear();
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
                        getAllDeliveryBikers();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }).start();
    }

}
