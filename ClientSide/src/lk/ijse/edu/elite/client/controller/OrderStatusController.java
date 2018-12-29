package lk.ijse.edu.elite.client.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.edu.elite.client.proxy.ProxyHandler;
import lk.ijse.edu.elite.common.dto.CustomerDTO;
import lk.ijse.edu.elite.common.dto.OrderDetailTableDTO;
import lk.ijse.edu.elite.common.dto.OrdersDTO;
import lk.ijse.edu.elite.common.service.ServiceFactory;
import lk.ijse.edu.elite.common.service.service.custom.CustomerMService;
import lk.ijse.edu.elite.common.service.service.custom.ReceptionOrderService;
import org.controlsfx.control.textfield.TextFields;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;

public class OrderStatusController implements Initializable {
    @FXML
    private JFXButton btnCancel;
    @FXML
    private TableView<OrderDetailTableDTO> tblOrderItems;
    @FXML
    private JFXTextField txtSearchCustomer;

    @FXML
    private JFXButton btnClose;

    @FXML
    private JFXTextField txtCustID;

    @FXML
    private JFXTextField txtCustName;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtAddress;

    private ArrayList<String> customerNic;
    @FXML
    private TableView<OrdersDTO> tblStatusOrders;
    private CustomerMService customerMService;
    private ReceptionOrderService receptionOrderService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            customerMService = (CustomerMService) ProxyHandler.getInstance().getSuperService(ServiceFactory.ServiceTypes.CUSTOMER);
            receptionOrderService = (ReceptionOrderService) ProxyHandler.getInstance().getSuperService(ServiceFactory.ServiceTypes.ORDERS);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tblStatusOrders.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("order_ID"));
        tblStatusOrders.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("order_date"));
        tblStatusOrders.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("order_time"));
        tblStatusOrders.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("status"));
        tblStatusOrders.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
        tblStatusOrders.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("chef_id"));
        tblStatusOrders.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("rider_id"));
        tblStatusOrders.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("reception_id"));
        tblStatusOrders.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("chef_order_start"));
        tblStatusOrders.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("chef_order_end"));
        tblStatusOrders.getColumns().get(10).setCellValueFactory(new PropertyValueFactory<>("deliver_order_start"));
        tblStatusOrders.getColumns().get(11).setCellValueFactory(new PropertyValueFactory<>("deliver_order_end"));
        tblStatusOrders.getColumns().get(12).setCellValueFactory(new PropertyValueFactory<>("homeDelivery"));
        tblStatusOrders.getColumns().get(13).setCellValueFactory(new PropertyValueFactory<>("total_price"));

        tblOrderItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("food_id"));
        tblOrderItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("food_name"));
        tblOrderItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblOrderItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("category"));
        tblOrderItems.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblOrderItems.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("orderdQty"));
        tblOrderItems.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("total"));
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                getAllCustomerNames();
                loadCutomerNIC();
            }
        }, new Date(), 5000);
    }

    @FXML
    void closeWindow(ActionEvent event) {

    }

    void loadCutomerNIC() {
        TextFields.bindAutoCompletion(txtSearchCustomer, customerNic);
    }

    @FXML
    void searchCustomer(ActionEvent actionEvent) {
        try {
            CustomerDTO customerDTO = customerMService.searchCustomer(txtSearchCustomer.getText());
            if (customerDTO != null) {
                txtCustID.setText(customerDTO.getCust_id());
                txtCustName.setText(customerDTO.getCust_name());
                txtContact.setText(customerDTO.getContact());
                txtAddress.setText(customerDTO.getAddres());
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        loadOrders();
                    }
                }, new Date(), 5000);
//                loadOrders();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadOrders() {
        ArrayList<OrdersDTO> ordersByID = null;
        try {
            ordersByID = receptionOrderService.getOrdersByID(txtCustID.getText());
            tblStatusOrders.setItems(FXCollections.observableArrayList(ordersByID));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void tableOnClick(MouseEvent mouseEvent) {
        if (tblStatusOrders.getSelectionModel().getSelectedIndex() != -1) {
            try {
                String order_id = tblStatusOrders.getSelectionModel().getSelectedItem().getOrder_ID();
                ArrayList<OrderDetailTableDTO> orderDetailByID = receptionOrderService.getOrderDetailByID(order_id);
                for (OrderDetailTableDTO o : orderDetailByID) {
                    double unitPrice = o.getUnitPrice();
                    int orderdQty = o.getOrderdQty();
                    o.setTotal(unitPrice * orderdQty);
                }
                tblOrderItems.setItems(FXCollections.observableArrayList(orderDetailByID));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void cancelOrder(ActionEvent actionEvent) {
//        try {
//            boolean b = receptionOrderService.deleteOrder(orde);
//            if (b) {
//                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Order canceled successfully", ButtonType.OK);
//                a.show();
//                tblOrderItems.getItems().clear();
//            } else {
//                Alert a = new Alert(Alert.AlertType.ERROR, "Order not canceled successfully", ButtonType.OK);
//                a.show();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private void getAllCustomerNames() {
        try {
            ArrayList<CustomerDTO> all = customerMService.getAll();
            customerNic = new ArrayList<>();
            for (CustomerDTO a : all) {
                customerNic.add(a.getNic());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
