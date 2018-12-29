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
import lk.ijse.edu.elite.common.dto.FoodDTO;
import lk.ijse.edu.elite.common.observer.Observer;
import lk.ijse.edu.elite.common.service.ServiceFactory;
import lk.ijse.edu.elite.common.service.service.custom.FoodService;
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

public class FoodController implements Initializable,Observer,Remote {
    @FXML
    private JFXTextField txtFoodSearch;

    @FXML
    private JFXTextField txtFoodCode;

    @FXML
    private JFXTextField txtFoodName;

    @FXML
    private JFXTextField txtCategory;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXTextField txtDesc;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private TableView<FoodDTO> tblFood;

    @FXML
    private JFXButton btnRemove;
    private FoodService foodService;
    private IDGeneratinService idGeneratinService;
    private ArrayList<String>foodName;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            UnicastRemoteObject.exportObject(this,0);
            foodService = (FoodService) ProxyHandler.getInstance().getSuperService(ServiceFactory.ServiceTypes.FOOD);
            idGeneratinService = (IDGeneratinService)ProxyHandler.getInstance().getSuperService(ServiceFactory.ServiceTypes.ID);
            foodService.register(this);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tblFood.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("food_ID"));
        tblFood.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("food_name"));
        tblFood.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("category"));
        tblFood.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblFood.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("price"));
        getAllFood();
        setFoodID();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                loadFoodNames();
            }
        }, new Date(), 5000);

    }

    @FXML
    void cancel(ActionEvent event) {
        clearText();
        setFoodID();
    }
    void loadFoodNames(){
        TextFields.bindAutoCompletion(txtFoodSearch,foodName);
    }
    @FXML
    void updateFood(ActionEvent event) {
        if(Validation.nameValidate(txtFoodName.getText())) {
            if(Validation.nameValidate(txtCategory.getText())){
                if(Validation.nameValidate(txtDesc.getText())){
                    if(Validation.cashValidation(txtPrice.getText())){
                        try {
                            boolean b = foodService.updateFood(new FoodDTO(txtFoodCode.getText(), txtFoodName.getText(), txtCategory.getText(), txtDesc.getText(), Double.parseDouble(txtPrice.getText())));
                            if (b) {
                                Notifications notificationsn = Notifications.create().title("Food Management").text("Food is Updated !").hideAfter(Duration.seconds(4)).position(Pos.BOTTOM_CENTER);
                                notificationsn.darkStyle();
                                notificationsn.showInformation();
//                                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Food Item updated !", ButtonType.OK);
//                                a.show();
                                foodService.released(txtFoodCode.getText());
                                setFoodID();
                                getAllFood();
                                clearText();
                            }
                        } catch (Exception e) {
                            Notifications notificationsn = Notifications.create().title("Food Management").text("Food is Not Updated!").hideAfter(Duration.seconds(4)).position(Pos.BOTTOM_CENTER);
                            notificationsn.darkStyle();
                            notificationsn.showError();
//                            Alert a = new Alert(Alert.AlertType.ERROR, "Food Item not updated !", ButtonType.OK);
//                            a.show();
                        }
                    }else {
                        txtPrice.requestFocus();
                        txtPrice.setFocusColor(Color.RED);
                    }
                }else {
                    txtDesc.requestFocus();
                    txtDesc.setFocusColor(Color.RED);
                }
            }else{
                txtCategory.requestFocus();
                txtCategory.setFocusColor(Color.RED);
            }
        }else {
            txtFoodName.requestFocus();
            txtFoodName.setFocusColor(Color.RED);
        }
    }
    void setFoodID(){
        try {
            String newID = idGeneratinService.getNewID("Foods", "food_ID", "P");
            txtFoodCode.setText(newID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void removeFood(ActionEvent event) {
        if(tblFood.getSelectionModel().getSelectedIndex()!=-1) {
            try {
                boolean b = foodService.deleteFood(new FoodDTO(txtFoodCode.getText(), txtFoodName.getText(), txtCategory.getText(), txtDesc.getText(), Double.parseDouble(txtPrice.getText())));
                if (b) {
                    Notifications notificationsn = Notifications.create().title("Food Management").text("Food is Removed !").hideAfter(Duration.seconds(4)).position(Pos.BOTTOM_CENTER);
                    notificationsn.darkStyle();
                    notificationsn.showInformation();
//                    Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Food Item Deleted !", ButtonType.OK);
//                    a.show();
                    setFoodID();
                    getAllFood();
                    clearText();
                }
            } catch (Exception e) {
                Notifications notificationsn = Notifications.create().title("Food Management").text("Food is Not Removed !").hideAfter(Duration.seconds(4)).position(Pos.BOTTOM_CENTER);
                notificationsn.darkStyle();
                notificationsn.showError();
//                Alert a = new Alert(Alert.AlertType.ERROR, "Food Item not Deleted !", ButtonType.OK);
//                a.show();
            }
        }else {
            Notifications notificationsn = Notifications.create().title("Food Management").text("Please Select Food to Remove !").hideAfter(Duration.seconds(4)).position(Pos.BOTTOM_CENTER);
            notificationsn.darkStyle();
            notificationsn.showWarning();
//            Alert a=new Alert(Alert.AlertType.ERROR,"Please Select Food to delete !");
//            a.show();
        }
    }
    @FXML
    void searchFood(ActionEvent event) {
        try {
            FoodDTO foodDTO = foodService.searchFood(txtFoodSearch.getText());
            if (foodDTO!=null){
                txtCategory.setText(foodDTO.getCategory());
                txtDesc.setText(foodDTO.getDescription());
                txtFoodCode.setText(foodDTO.getFood_ID());
                txtFoodName.setText(foodDTO.getFood_name());
                txtPrice.setText(foodDTO.getPrice()+"");
            }else {
                Alert a = new Alert(Alert.AlertType.ERROR, "We couldn't find anything !", ButtonType.OK);
                a.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void tblOnClick(MouseEvent event) {
        if(tblFood.getSelectionModel().getSelectedIndex()!=-1){
            FoodDTO item = tblFood.getSelectionModel().getSelectedItem();
            txtCategory.setText(item.getCategory());
            txtDesc.setText(item.getDescription());
            txtFoodCode.setText(item.getFood_ID());
            txtFoodName.setText(item.getFood_name());
            txtPrice.setText(item.getPrice()+"");
        }
    }
    @FXML
    void saveFood(ActionEvent event) {
        if(Validation.nameValidate(txtFoodName.getText())) {
            if(Validation.nameValidate(txtCategory.getText())){
                if(Validation.nameValidate(txtDesc.getText())){
                    if(Validation.cashValidation(txtPrice.getText())){
                        try {
                            boolean b = foodService.addFood(new FoodDTO(txtFoodCode.getText(), txtFoodName.getText(), txtCategory.getText(), txtDesc.getText(), Double.parseDouble(txtPrice.getText())));
                            if (b) {
                                Notifications notificationsn = Notifications.create().title("Food Management").text("Food is Saved !").hideAfter(Duration.seconds(4)).position(Pos.BOTTOM_CENTER);
                                notificationsn.darkStyle();
                                notificationsn.showInformation();
                                getAllFood();
                                setFoodID();
                                clearText();
                            }
                        } catch (Exception e) {
                            Notifications notificationsn = Notifications.create().title("Food Management").text("Food is Not saved!").hideAfter(Duration.seconds(4)).position(Pos.BOTTOM_CENTER);
                            notificationsn.darkStyle();
                            notificationsn.showError();
//                            Alert a = new Alert(Alert.AlertType.ERROR, "Food Item not Saved !", ButtonType.OK);
//                            a.show();
                        }
                    }else {
                        txtPrice.requestFocus();
                        txtPrice.setFocusColor(Color.RED);
                    }
                }else {
                    txtDesc.requestFocus();
                    txtDesc.setFocusColor(Color.RED);
                }
            }else{
                txtCategory.requestFocus();
                txtCategory.setFocusColor(Color.RED);
            }
        }else {
            txtFoodName.requestFocus();
            txtFoodName.setFocusColor(Color.RED);
        }
    }
    private void getAllFood(){
        try {
            ArrayList<FoodDTO> all = foodService.getAll();
            tblFood.setItems(FXCollections.observableArrayList(all));
            foodName=new ArrayList<>();
            for (FoodDTO d:all){
                foodName.add(d.getFood_name());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void clearText(){
        txtCategory.clear();
        txtDesc.clear();
//        txtFoodCode.clear();
        txtFoodName.clear();
        txtPrice.clear();
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
                        getAllFood();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }).start();
    }
}
