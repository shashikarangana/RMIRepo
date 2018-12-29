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
import lk.ijse.edu.elite.common.dto.CustomerDTO;
import lk.ijse.edu.elite.common.dto.DeliveryBikesDTO;
import lk.ijse.edu.elite.common.dto.OrderDetailTableDTO;
import lk.ijse.edu.elite.common.dto.OrdersDTO;
import lk.ijse.edu.elite.common.service.ServiceFactory;
import lk.ijse.edu.elite.common.service.service.custom.CustomerMService;
import lk.ijse.edu.elite.common.service.service.custom.DeliveryBikeService;
import lk.ijse.edu.elite.common.service.service.custom.ReceptionOrderService;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DeliveryController implements Initializable {
    @FXML
    private Label lblTime;
    @FXML
    private Label lblDate;
    @FXML
    private JFXTextField txtRiderID;
    @FXML
    private TableView<OrdersDTO> tblAvailableOrders;
    @FXML
    private TextField txtOrderTime;
    @FXML
    private TextField txtCustID;

    @FXML
    private TextField txtCustName;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtNIC;

    @FXML
    private JFXButton btnGetNewOrder;

    @FXML
    private TextField txtOrderID;

    @FXML
    private TextField txtOrderDate;

    @FXML
    private TableView<OrderDetailTableDTO> tblFoodOrder;

    @FXML
    private TextField txtDeliveryFee;

    @FXML
    private TextField txtFoodCost;

    @FXML
    private TextField txtTotalCost;

    @FXML
    private TextField txtPaid;

    @FXML
    private TextField txtBalance;

    @FXML
    private JFXButton btnDeliver;
    private ReceptionOrderService receptionOrderService;
    private CustomerMService customerMService;
    private DeliveryBikeService deliveryBikeService;
    private ArrayList<String>d_ids;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            receptionOrderService = (ReceptionOrderService) ProxyHandler.getInstance().getSuperService(ServiceFactory.ServiceTypes.ORDERS);
            customerMService = (CustomerMService) ProxyHandler.getInstance().getSuperService(ServiceFactory.ServiceTypes.CUSTOMER);
            deliveryBikeService=(DeliveryBikeService)ProxyHandler.getInstance().getSuperService(ServiceFactory.ServiceTypes.DELIVERY);
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
        tblAvailableOrders.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("total_price"));
        tblAvailableOrders.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("status"));
        setDateTime();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    getAllAvailableDeliveryOrders();
                    getAllDelivery();
                    loadToText();
                });
            }
        }, new Date(), 4000);
        tblFoodOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("food_id"));
        tblFoodOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("food_name"));
        tblFoodOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("category"));
        tblFoodOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblFoodOrder.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("orderdQty"));
        tblFoodOrder.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("total"));
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
    void getAllDelivery(){
        try {
            ArrayList<DeliveryBikesDTO> all = deliveryBikeService.getAll();
            d_ids=new ArrayList<>();
            for (DeliveryBikesDTO d:all){
                d_ids.add(d.getDeliverID());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void loadToText(){
        TextFields.bindAutoCompletion(txtRiderID,d_ids);
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
                    txtCustID.setText(customerDTO.getCust_id());
                    txtCustName.setText(customerDTO.getContact());
                    txtContact.setText(customerDTO.getAddres());
                    txtAddress.setText(customerDTO.getCust_name());
                    txtNIC.setText(customerDTO.getDob());
                }
                if (ordersDTO != null) {
                    txtOrderID.setText(ordersDTO.getOrder_ID());
                    txtOrderDate.setText(ordersDTO.getOrder_date().toString());
                    txtOrderTime.setText(ordersDTO.getOrder_time().toString());
                    ArrayList<OrderDetailTableDTO> orderDetailByID = receptionOrderService.getOrderDetailByID(txtOrderID.getText());
                    for (OrderDetailTableDTO o : orderDetailByID) {
                        double unitPrice = o.getUnitPrice();
                        int orderdQty = o.getOrderdQty();
                        o.setTotal(unitPrice * orderdQty);
                    }
                    tblFoodOrder.setItems(FXCollections.observableArrayList(orderDetailByID));
                    double value = 0.0;
                    for (int i = 0; i < tblFoodOrder.getItems().size(); i++) {
                        value += (Double) tblFoodOrder.getColumns().get(5).getCellObservableValue(i).getValue();
                    }
                    txtTotalCost.setText(value + "");
                    tblAvailableOrders.setDisable(true);
                    selectedItem.setStatus("Delivering");
                    selectedItem.setPaymentStatus("Not yet");
                    selectedItem.setDeliver_order_start(new Date());
                    selectedItem.setRider_id(txtRiderID.getText());
                    receptionOrderService.updateOrderAsDeliverd(selectedItem);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            Alert a = new Alert(Alert.AlertType.WARNING, "Please select order ", ButtonType.OK);
            a.show();
        }
    }

    @FXML
    void orderDelivered(ActionEvent event) {
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setOrder_ID(txtOrderID.getText());
        ordersDTO.setDeliver_order_end(new Date());
        ordersDTO.setStatus("Delivered");
        ordersDTO.setPaymentStatus("Paid success");
        ordersDTO.setRider_id(txtRiderID.getText());
        tblAvailableOrders.setDisable(false);
        try {
            boolean b = receptionOrderService.updateDeliver_end_time(ordersDTO);
            if (b) {
                Notifications notificationsn = Notifications.create().title("Deliver Section").text("Order status update as Delivered !").hideAfter(Duration.seconds(4)).position(Pos.BOTTOM_CENTER);
                notificationsn.darkStyle();
                notificationsn.showInformation();
//                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Order status update as Delivered !");
//                a.show();
                clearText();
            } else {
                Alert a = new Alert(Alert.AlertType.WARNING, "Something went wrong !");
                a.show();
            }
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Nothing to deliver !");
            a.show();
        }
    }

    private void clearText() {
        txtCustID.clear();
        txtCustName.clear();
        txtContact.clear();
        txtNIC.clear();
        txtAddress.clear();
        txtTotalCost.clear();
        txtOrderID.clear();
        txtOrderDate.clear();
        txtOrderTime.clear();
        tblFoodOrder.getItems().clear();
    }

    @FXML
    void tblAvailableOnClick(MouseEvent mouseEvent) {

    }

    private void getAllAvailableDeliveryOrders() {
        try {
            ArrayList<OrdersDTO> availableOrder = receptionOrderService.getDeliverOrders();
            tblAvailableOrders.setItems(FXCollections.observableArrayList(availableOrder));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
