package lk.ijse.edu.elite.client.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import lk.ijse.edu.elite.client.proxy.ProxyHandler;
import lk.ijse.edu.elite.common.dto.ChefDTO;
import lk.ijse.edu.elite.common.dto.CustomerDTO;
import lk.ijse.edu.elite.common.dto.OrderDetailTableDTO;
import lk.ijse.edu.elite.common.dto.OrdersDTO;
import lk.ijse.edu.elite.common.observer.Observer;
import lk.ijse.edu.elite.common.service.ServiceFactory;
import lk.ijse.edu.elite.common.service.service.custom.ChefService;
import lk.ijse.edu.elite.common.service.service.custom.CustomerMService;
import lk.ijse.edu.elite.common.service.service.custom.ReceptionOrderService;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.*;

public class ChefController implements Initializable {
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    private TextField txtOrderTime;
    @FXML
    private JFXTextField txtChefID;
    @FXML
    private TableView<OrdersDTO> tblAvailableOrders;
    @FXML
    private JFXButton btnNewOrder;



    @FXML
    private TextField txtCustomerID;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtDOB;

    @FXML
    private TextField txtOrderID;

    @FXML
    private TextField txtOrderDate;

    @FXML
    private TableView<OrderDetailTableDTO> tblOrders;

    @FXML
    private TextField txtTotal;

    @FXML
    private TextField txtOrderType;
    private ArrayList<String>chefID;
    @FXML
    private JFXButton btnFinishOrder;

    private ReceptionOrderService receptionOrderService;
    private CustomerMService customerMService;
    private ChefService chefService;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            receptionOrderService = (ReceptionOrderService) ProxyHandler.getInstance().getSuperService(ServiceFactory.ServiceTypes.ORDERS);
            customerMService = (CustomerMService) ProxyHandler.getInstance().getSuperService(ServiceFactory.ServiceTypes.CUSTOMER);
            chefService=(ChefService)ProxyHandler.getInstance().getSuperService(ServiceFactory.ServiceTypes.CHEF);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tblAvailableOrders.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("order_ID"));
        tblAvailableOrders.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("order_date"));
        tblAvailableOrders.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("order_time"));
        tblAvailableOrders.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("homeDelivery"));
        tblAvailableOrders.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total_price"));
        tblAvailableOrders.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("status"));

        tblOrders.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("food_id"));
        tblOrders.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("food_name"));
        tblOrders.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("category"));
        tblOrders.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblOrders.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblOrders.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("orderdQty"));
        setDateTime();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    getAllAvailableOrders();
                    getAllChef();
                    setTotext();
                });
            }
        }, new Date(), 5000);


    }
    void setTotext(){
        TextFields.bindAutoCompletion(txtChefID,chefID);
    }
    void getAllChef(){
        try {
            ArrayList<ChefDTO> all = chefService.getAll();
            chefID=new ArrayList<>();
            for (ChefDTO chefDTO:all){
                chefID.add(chefDTO.getChef_ID());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDateTime() {
        Timeline newTimeLine = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lblDate.setText(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
                lblTime.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }

        }), new KeyFrame(Duration.seconds(1)));
        newTimeLine.setCycleCount(Animation.INDEFINITE);
        newTimeLine.play();
    }

    @FXML
    void getNewOrder(ActionEvent event) {
        if (tblAvailableOrders.getSelectionModel().getSelectedIndex() != -1) {
            OrdersDTO selectedItem = tblAvailableOrders.getSelectionModel().getSelectedItem();
            txtOrderID.setText(selectedItem.getOrder_ID());
            try {

                OrdersDTO ordersDTO = receptionOrderService.searchOrder(txtOrderID.getText());
                CustomerDTO customerDTO = customerMService.searchCustomerByOrderID(selectedItem.getOrder_ID());
                if (customerDTO != null) {
                    txtCustomerID.setText(customerDTO.getCust_id());
                    txtCustomerName.setText(customerDTO.getContact());
                    txtContact.setText(customerDTO.getAddres());
                    txtAddress.setText(customerDTO.getCust_name());
                    txtDOB.setText(customerDTO.getNic());
                }
                if (ordersDTO != null) {
                    txtOrderID.setText(ordersDTO.getOrder_ID());
                    txtOrderDate.setText(ordersDTO.getOrder_date().toString());
                    txtOrderTime.setText(ordersDTO.getOrder_time().toString());
                    txtOrderType.setText(ordersDTO.isHomeDelivery() + "");
                    ArrayList<OrderDetailTableDTO> orderDetailByID = receptionOrderService.getOrderDetailByID(txtOrderID.getText());
                    tblOrders.setItems(FXCollections.observableArrayList(orderDetailByID));
                    tblAvailableOrders.setDisable(true);
                    selectedItem.setStatus("Cooking");
                    selectedItem.setChef_id(txtChefID.getText());
                    selectedItem.setChef_order_start(new Date());
                    receptionOrderService.updateOrder(selectedItem);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Notifications notificationsn = Notifications.create().title("Chef Section").text("Please Select Order ..!").hideAfter(Duration.seconds(4)).position(Pos.BOTTOM_CENTER);
            notificationsn.darkStyle();
            notificationsn.showWarning();

//            Alert a = new Alert(Alert.AlertType.WARNING, "Please Select Order ..!");
//            a.show();
        }
    }

    @FXML
    void finishOrder(ActionEvent actionEvent) {
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setOrder_ID(txtOrderID.getText());
        ordersDTO.setChef_order_end(new Date());
        ordersDTO.setStatus("Cooked");
        ordersDTO.setChef_id(txtChefID.getText());
        tblAvailableOrders.setDisable(false);
        try {
            boolean b = receptionOrderService.updateChef_end_time(ordersDTO);
            if (b) {
                Notifications notificationsn = Notifications.create().title("Chef Section").text("Order status update as cooked !").hideAfter(Duration.seconds(4)).position(Pos.BOTTOM_CENTER);
                notificationsn.darkStyle();
                notificationsn.showInformation();
//                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Order status update as cooked !");
//                a.showAndWait();
                clearText();
            } else {
                Alert a = new Alert(Alert.AlertType.WARNING, "Something went wrong !");
                a.show();
            }
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Nothing to Finish !");
            a.show();
        }
    }

    @FXML
    void tblAvailableOnClick(MouseEvent mouseEvent) {
    }

    private void getAllAvailableOrders() {
        try {
            ArrayList<OrdersDTO> availableOrder = receptionOrderService.getAvailableOrder();
            tblAvailableOrders.setItems(FXCollections.observableArrayList(availableOrder));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void clearText() {
        txtCustomerID.clear();
        txtCustomerName.clear();
        txtContact.clear();
        txtAddress.clear();
        txtDOB.clear();
        txtOrderID.clear();
        txtOrderDate.clear();
        txtOrderTime.clear();
        txtOrderType.clear();
        tblOrders.getItems().clear();
    }

}




