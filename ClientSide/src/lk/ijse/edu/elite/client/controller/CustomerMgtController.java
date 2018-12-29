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
import lk.ijse.edu.elite.common.dto.CustomerDTO;
import lk.ijse.edu.elite.common.observer.Observer;
import lk.ijse.edu.elite.common.service.ServiceFactory;
import lk.ijse.edu.elite.common.service.service.custom.CustomerMService;
import lk.ijse.edu.elite.common.service.service.custom.IDGeneratinService;
import org.controlsfx.control.textfield.TextFields;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public class CustomerMgtController implements Initializable ,Observer,Remote {
    @FXML
    private JFXTextField txtCustomerSearch;

    @FXML
    private JFXTextField txtCustomerID;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtCustContact;

    @FXML
    private JFXTextField txtCustAddres;

    @FXML
    private JFXTextField txtCustNIC;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private TableView<CustomerDTO> tblCustomer;

    @FXML
    private JFXButton btnRemove;
    private ArrayList<String> strings;
    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXTextField txtxCustDOB;
    private CustomerMService customerMService;
    private IDGeneratinService idGeneratinService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            customerMService = (CustomerMService) ProxyHandler.getInstance().getSuperService(ServiceFactory.ServiceTypes.CUSTOMER);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
       tblLoadCustomer();
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getAllCustomers();
//                setCustomerID();
                loadCustomerNIC();
            }
        },new Date(),5000);
    }

    @FXML
    void cancel(ActionEvent event) {
        clearText();
    }
    void loadCustomerNIC(){
        TextFields.bindAutoCompletion(txtCustomerSearch,strings);
    }


    @FXML
    void removeCustomer(ActionEvent event) {
        if (tblCustomer.getSelectionModel().getSelectedIndex()!=-1) {
            try {
                boolean b = customerMService.deleteCustomer(new CustomerDTO(txtCustomerID.getText(),txtCustomerName.getText(),txtCustAddres.getText(),txtCustContact.getText(),txtCustNIC.getText(),txtxCustDOB.getText()));
                if (b) {
                    Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted !", ButtonType.OK);
                    a.show();
//                    setCustomerID();
                    getAllCustomers();
                    clearText();
                }
            } catch (Exception e) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Customer not Deleted !", ButtonType.OK);
                a.show();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Select Customer to Delete!", ButtonType.OK);
            a.show();
        }
    }

//    void setCustomerID() {
//        try {
//            String newID = idGeneratinService.getNewID("Customer", "cust_id", "C");
//            txtCustomerID.setText(newID);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @FXML
    void searchChef(ActionEvent event) {
        CustomerDTO customerDTO = null;
        try {
            customerDTO = customerMService.searchCustomer(txtCustomerSearch.getText());
            if (customerDTO != null) {
                txtCustomerID.setText(customerDTO.getCust_id());
                txtCustomerName.setText(customerDTO.getCust_name());
                txtCustAddres.setText(customerDTO.getAddres());
                txtCustContact.setText(customerDTO.getContact());
                txtCustNIC.setText(customerDTO.getNic());
                txtxCustDOB.setText(customerDTO.getDob());
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "We couldn't find anything!", ButtonType.OK);
                a.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void tblClicked(MouseEvent event) {
        if (tblCustomer.getSelectionModel().getSelectedIndex() != -1) {
            CustomerDTO customerDTO = tblCustomer.getSelectionModel().getSelectedItem();
            txtCustomerID.setText(customerDTO.getCust_id());
            txtCustomerName.setText(customerDTO.getCust_name());
            txtCustAddres.setText(customerDTO.getAddres());
            txtCustContact.setText(customerDTO.getContact());
            txtCustNIC.setText(customerDTO.getNic());
            txtxCustDOB.setText(customerDTO.getDob());
        }
    }

    @FXML
    void updateCustomer(ActionEvent event) {
        if (Validation.nameValidate(txtCustomerName.getText())) {
            if (Validation.addressValidate(txtCustAddres.getText())) {
                if (Validation.telephoneValidate(txtCustContact.getText())) {
                    if (Validation.nicValidate(txtCustNIC.getText())) {
                        if (Validation.dobValidation(txtxCustDOB.getText())) {
                            try {
                                boolean b = customerMService.updateCustomer(new CustomerDTO(txtCustomerID.getText(), txtCustomerName.getText(), txtCustAddres.getText(), txtCustContact.getText(), txtCustNIC.getText(), txtxCustDOB.getText()));
                                if (b) {
                                    Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated !", ButtonType.OK);
                                    a.show();
//                                    setCustomerID();
                                    getAllCustomers();
                                    clearText();
                                }
                            } catch (Exception e) {
                                Alert a = new Alert(Alert.AlertType.ERROR, "Customer not Updated !", ButtonType.OK);
                                a.show();
                            }
                        } else {
                            txtxCustDOB.requestFocus();
                            txtxCustDOB.setFocusColor(Color.RED);
                        }
                    } else {
                        txtCustNIC.requestFocus();
                        txtCustNIC.setFocusColor(Color.RED);
                    }
                } else {
                    txtCustContact.requestFocus();
                    txtCustContact.setFocusColor(Color.RED);
                }
            } else {
                txtCustAddres.requestFocus();
                txtCustAddres.setFocusColor(Color.RED);
            }
        } else {
            txtCustomerName.requestFocus();
            txtCustomerName.setFocusColor(Color.RED);
        }
    }

    private void clearText() {
//        txtCustomerID.clear();
        txtCustomerName.clear();
        txtCustAddres.clear();
        txtCustContact.clear();
        txtCustNIC.clear();
        txtxCustDOB.clear();
    }

    private void getAllCustomers() {
        try {
            ArrayList<CustomerDTO> all = customerMService.getAll();
            tblCustomer.setItems(FXCollections.observableArrayList(all));
            strings = new ArrayList<>();
            for (CustomerDTO c : all) {
                strings.add(c.getNic());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void tblLoadCustomer(){
        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("cust_id"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("cust_name"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("addres"));
        tblCustomer.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblCustomer.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("dob"));
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
                        tblLoadCustomer();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }).start();
    }

}
