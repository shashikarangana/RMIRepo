package lk.ijse.edu.elite.client.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import lk.ijse.edu.elite.client.common.Validation;
import lk.ijse.edu.elite.client.main.AppInitializer;
import lk.ijse.edu.elite.client.proxy.ProxyHandler;
import lk.ijse.edu.elite.common.dto.*;
import lk.ijse.edu.elite.common.service.ServiceFactory;
import lk.ijse.edu.elite.common.service.service.custom.*;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class ReseptionController implements Initializable {
    @FXML
    public TextField txtQty;
    @FXML
    public JFXTextField txtReceptionID;
    @FXML
    public JFXToggleButton tbtnHomeDelivery;
    @FXML
    private Label lblTime;
    @FXML
    private Label lblDate;
    @FXML
    private TextField txtFoodCode;

    @FXML
    private TextField txtFoodName;

    @FXML
    private TextField txtCategory;

    @FXML
    private JFXTextField txtSearchFood;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TextField txtPrice;

    @FXML
    private JFXButton btnAddFood;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXTextField txtCustomerSearch;

    @FXML
    private TableView<OrderTableDTO> tblFoods;

    @FXML
    private JFXButton btnAddForCook;

    @FXML
    private JFXButton btnCancelOrder;

    @FXML
    private TextField txtOrderID;

    @FXML
    private TextField txtOrderDate;

    @FXML
    private JFXToggleButton tgHomeDelivery;

    @FXML
    private JFXTextField txtCustID;

    @FXML
    private JFXTextField txtCustName;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtNIC;
    @FXML
    private TextField txtOrderTime;
    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtDob;
    private double totAmount = 0.0;
    @FXML
    private TextField txtTotal;
    private boolean orderType;
    @FXML
    private JFXButton btnOrderStatus;
    private ArrayList<String> customerNic;
    private ArrayList<String> foodNames;
    private FoodService foodService;
    private CustomerMService customerMService;
    private ReceptionOrderService receptionOrderService;
    private IDGeneratinService idGeneratinService;
    private ReceptionMgtService receptionMgtService;
    private ArrayList<String>r_ids;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            foodService = (FoodService) ProxyHandler.getInstance().getSuperService(ServiceFactory.ServiceTypes.FOOD);
            customerMService = (CustomerMService) ProxyHandler.getInstance().getSuperService(ServiceFactory.ServiceTypes.CUSTOMER);
            receptionOrderService = (ReceptionOrderService) ProxyHandler.getInstance().getSuperService(ServiceFactory.ServiceTypes.ORDERS);
            idGeneratinService = (IDGeneratinService) ProxyHandler.getInstance().getSuperService(ServiceFactory.ServiceTypes.ID);
            receptionMgtService=(ReceptionMgtService)ProxyHandler.getInstance().getSuperService(ServiceFactory.ServiceTypes.RECEPTION);
            orderType = false;
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        txtOrderDate.setText(LocalDate.now().toString());
        setDateTime();
        tblFoods.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("foodCode"));
        tblFoods.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("foodName"));
        tblFoods.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblFoods.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblFoods.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    getAllCustomers();
                    getAllFoods();
                    setOrderID();
                    loadCustomerNIC();
                    loadFoodNames();
                    getAllReception();
                    loadToText();
                });

            }
        }, new Date(), 5000);
    }

    private void setCustomerID() {
        try {
            String newID = idGeneratinService.getNewID("Customer", "cust_id", "C");
            txtCustID.setText(newID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void getAllReception(){
        try {
            ArrayList<ReceptionDTO> all = receptionMgtService.getAll();
            r_ids=new ArrayList<>();
            for (ReceptionDTO r:all){
                r_ids.add(r.getReception_ID());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void loadToText(){
        TextFields.bindAutoCompletion(txtReceptionID,r_ids);
    }

    private void setOrderID() {
        try {
            String newID = idGeneratinService.getNewID("Orders", "order_ID", "OD");
            txtOrderID.setText(newID);
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
                txtOrderTime.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }

        }), new KeyFrame(Duration.seconds(1)));
        newTimeLine.setCycleCount(Animation.INDEFINITE);
        newTimeLine.play();
    }

    @FXML
    void addFoodToTable(ActionEvent event) {
        if (Validation.orderQty(txtQty.getText())) {
            double tot = Double.parseDouble(txtPrice.getText()) * Double.parseDouble(txtQty.getText());
            totAmount += tot;
            OrderTableDTO orderTableDTO = new OrderTableDTO(txtFoodCode.getText(), txtFoodName.getText(), Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtQty.getText()), tot);
            tblFoods.getItems().add(orderTableDTO);
            clearText();
            txtTotal.setText(totAmount + "");
        } else {
            txtQty.requestFocus();
            Alert alert = new Alert(Alert.AlertType.ERROR, "You should fill the qty", ButtonType.OK);
            alert.show();
        }
    }

    private void clearText() {
        txtFoodCode.clear();
        txtFoodName.clear();
        txtPrice.clear();
        txtDescription.clear();
        txtCategory.clear();
        txtSearchFood.clear();
        txtQty.clear();
    }

    @FXML
    void addTocook(ActionEvent event) {
        String orderID = txtOrderID.getText();
        String customerID = txtCustID.getText();
        String resID = txtReceptionID.getText();
        double total_price = Double.parseDouble(txtTotal.getText());
        boolean selected = tbtnHomeDelivery.isSelected();
        if (orderType) {
            if (Validation.nameValidate(txtCustName.getText())) {
                if (Validation.addressValidate(txtAddress.getText())) {
                    if (Validation.telephoneValidate(txtContact.getText())) {
                        if (Validation.nicValidate(txtNIC.getText())) {
                            if (Validation.dobValidation(txtDob.getText())) {
                                CustomerDTO customerDTO = new CustomerDTO(customerID, txtCustName.getText(), txtAddress.getText(), txtContact.getText(), txtNIC.getText(), txtDob.getText());
                                List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();

                                for (int i = 0; i < tblFoods.getItems().size(); i++) {
                                    String food_code = (String) tblFoods.getColumns().get(0).getCellObservableValue(i).getValue();
                                    double unit_price = (Double) tblFoods.getColumns().get(2).getCellObservableValue(i).getValue();
                                    int orderd_qty = (Integer) tblFoods.getColumns().get(3).getCellObservableValue(i).getValue();
                                    orderDetailDTOS.add(new OrderDetailDTO(food_code, orderID, unit_price, orderd_qty));
                                }
//                                System.out.println("Controller " + total_price);
                                OrdersDTO ordersDTO = new OrdersDTO(orderID, new Date(), new Date(), "Just placed", "Not yet", selected, total_price, orderDetailDTOS, customerDTO, resID);

                                try {
                                    boolean b = receptionOrderService.addOrder(ordersDTO);
                                    if (b) {
                                        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Order added to the system..!", ButtonType.OK);
                                        a.show();
                                        setCustomerID();
                                        tblFoods.getItems().clear();
                                        txtCustName.clear();
                                        txtDob.clear();
                                        txtContact.clear();
                                        txtAddress.clear();
                                        txtNIC.clear();
                                        txtDob.clear();
                                        setOrderID();
                                    } else {
                                        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Order not added to the system..!", ButtonType.OK);
                                        a.show();
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                txtDob.requestFocus();
                                txtDob.setFocusColor(Color.RED);
                            }
                        } else {
                            txtNIC.requestFocus();
                            txtNIC.setFocusColor(Color.RED);
                        }
                    } else {
                        txtContact.requestFocus();
                        txtContact.setFocusColor(Color.RED);
                    }
                } else {
                    txtAddress.requestFocus();
                    txtAddress.setFocusColor(Color.RED);
                }
            } else {
                txtCustName.requestFocus();
                txtCustName.setFocusColor(Color.RED);
            }


        } else {
            CustomerDTO customerDTO = new CustomerDTO(customerID, txtCustName.getText(), txtAddress.getText(), txtContact.getText(), txtNIC.getText(), txtDob.getText());
            List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();

            for (int i = 0; i < tblFoods.getItems().size(); i++) {
                String food_code = (String) tblFoods.getColumns().get(0).getCellObservableValue(i).getValue();
                double unit_price = (Double) tblFoods.getColumns().get(2).getCellObservableValue(i).getValue();
                int orderd_qty = (Integer) tblFoods.getColumns().get(3).getCellObservableValue(i).getValue();
                orderDetailDTOS.add(new OrderDetailDTO(food_code, orderID, unit_price, orderd_qty));
            }
//            System.out.println("Controller " + total_price);
            OrdersDTO ordersDTO = new OrdersDTO(orderID, new Date(), new Date(), "Just placed", "Not yet", selected, total_price, orderDetailDTOS, customerDTO, resID);

            try {
                boolean b = receptionOrderService.addOrderWithoyCustomer(ordersDTO);
                if (b) {
                    Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Order added to the system..!", ButtonType.OK);
                    a.show();
                    setOrderID();
                } else {
                    Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Order not added to the system..!", ButtonType.OK);
                    a.show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void cancelOrder(ActionEvent event) {

    }

    @FXML
    void searchCustomer(ActionEvent event) {
        try {
            CustomerDTO customerDTO = customerMService.searchCustomer(txtCustomerSearch.getText());
            if (customerDTO != null) {
                txtCustID.setText(customerDTO.getCust_id());
                txtCustName.setText(customerDTO.getCust_name());
                txtContact.setText(customerDTO.getContact());
                txtNIC.setText(customerDTO.getNic());
                txtAddress.setText(customerDTO.getAddres());
                txtDob.setText(customerDTO.getDob());
            } else {
                Alert a = new Alert(Alert.AlertType.WARNING, "We couldn't find anything.Would you like to Save new Customer?", ButtonType.OK);
                Optional<ButtonType> buttonType = a.showAndWait();
                if (buttonType.get() == ButtonType.OK) {
                    setCustomerID();
                    enableDesable(false);
                    orderType = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void searchFood(ActionEvent event) {
        try {
            FoodDTO foodDTO = foodService.searchFood(txtSearchFood.getText());
            if (foodDTO != null) {
                txtFoodCode.setText(foodDTO.getFood_ID());
                txtFoodName.setText(foodDTO.getFood_name());
                txtCategory.setText(foodDTO.getCategory());
                txtDescription.setText(foodDTO.getDescription());
                txtPrice.setText(foodDTO.getPrice() + "");
            } else {
                Alert a = new Alert(Alert.AlertType.WARNING, "We couldn't find anything !", ButtonType.OK);
                a.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enableDesable(boolean status) {
        txtCustID.setDisable(status);
        txtCustName.setDisable(status);
        txtContact.setDisable(status);
        txtNIC.setDisable(status);
        txtAddress.setDisable(status);
        txtDob.setDisable(status);
    }

    @FXML
    void openStatus(ActionEvent actionEvent) {
        try {
            new AppInitializer().popUp("/lk/ijse/edu/elite/client/view/OrderStatus.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void loadCustomerNIC() {
        TextFields.bindAutoCompletion(txtCustomerSearch, customerNic);
    }

    void loadFoodNames() {
        TextFields.bindAutoCompletion(txtSearchFood, foodNames);
    }

    void getAllFoods() {
        try {
            ArrayList<FoodDTO> all = foodService.getAll();
            foodNames = new ArrayList<>();
            for (FoodDTO dto : all) {
                foodNames.add(dto.getFood_name());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void getAllCustomers() {
        try {
            ArrayList<CustomerDTO> all = customerMService.getAll();
            customerNic = new ArrayList<>();
            for (CustomerDTO dto : all) {
                customerNic.add(dto.getNic());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
