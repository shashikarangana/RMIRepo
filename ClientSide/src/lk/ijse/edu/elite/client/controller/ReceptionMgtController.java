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
import javafx.scene.paint.Color;
import lk.ijse.edu.elite.client.common.Validation;
import lk.ijse.edu.elite.client.proxy.ProxyHandler;
import lk.ijse.edu.elite.common.dto.ReceptionDTO;
import lk.ijse.edu.elite.common.service.ServiceFactory;
import lk.ijse.edu.elite.common.service.service.custom.IDGeneratinService;
import lk.ijse.edu.elite.common.service.service.custom.ReceptionMgtService;
import org.controlsfx.control.textfield.TextFields;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;

public class ReceptionMgtController implements Initializable {
    @FXML
    private JFXTextField txtSearchReception;

    @FXML
    private JFXTextField txtRecID;

    @FXML
    private JFXTextField txtResName;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXTextField txtResContact;

    @FXML
    private JFXTextField txtResAddres;

    @FXML
    private JFXTextField txtRecNIC;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private TableView<ReceptionDTO> tblReception;
    private ArrayList<String> strings;
    @FXML
    private JFXButton btnRemove;
    private ReceptionMgtService receptionMgtService;
    private IDGeneratinService idGeneratinService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            receptionMgtService = (ReceptionMgtService) ProxyHandler.getInstance().getSuperService(ServiceFactory.ServiceTypes.RECEPTION);
            idGeneratinService = (IDGeneratinService) ProxyHandler.getInstance().getSuperService(ServiceFactory.ServiceTypes.ID);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tblReception.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("reception_ID"));
        tblReception.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("reception_Name"));
        tblReception.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("reception_Contact"));
        tblReception.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("reception_address"));
        tblReception.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("reception_nic"));
        getAllReception();
        setReceptionID();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                loadReceptionNIC();
            }
        }, new Date(), 5000);
    }

    @FXML
    void cancel(ActionEvent event) {
        clearText();
        setReceptionID();
    }

    void setReceptionID() {
        try {
            String newID = idGeneratinService.getNewID("Reception", "reception_ID ", "R");
            txtRecID.setText(newID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void loadReceptionNIC() {
        TextFields.bindAutoCompletion(txtSearchReception, strings);
    }

    @FXML
    void removeReception(ActionEvent event) {
        if(tblReception.getSelectionModel().getSelectedIndex()!=-1) {
            try {
                boolean b = receptionMgtService.deleteReception(new ReceptionDTO(txtRecID.getText(), txtResName.getText(), txtResContact.getText(), txtResAddres.getText(), txtRecNIC.getText()));
                if (b) {
                    Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Reception Deleted !", ButtonType.OK);
                    a.show();
                    getAllReception();
                    setReceptionID();
                    clearText();
                }
            } catch (Exception e) {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Reception not Deleted !", ButtonType.OK);
                a.show();
                e.printStackTrace();
            }
        }else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Please Select reception to delete !", ButtonType.OK);
            a.show();
        }
    }

    @FXML
    void updateReception(ActionEvent event) {
        if(Validation.nameValidate(txtResName.getText())) {
            if(Validation.telephoneValidate(txtResContact.getText())){
                if(Validation.addressValidate(txtResAddres.getText())){
                    if(Validation.nicValidate(txtRecNIC.getText())){
                        try {
                            boolean b = receptionMgtService.updateReception(new ReceptionDTO(txtRecID.getText(), txtResName.getText(), txtResContact.getText(), txtResAddres.getText(), txtRecNIC.getText()));
                            if (b) {
                                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Reception Updated !", ButtonType.OK);
                                a.show();
                                getAllReception();
                                setReceptionID();
                                clearText();
                            }
                        } catch (Exception e) {
                            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Reception not Updated !", ButtonType.OK);
                            a.show();
                            e.printStackTrace();
                        }
                    }else {
                        txtRecNIC.requestFocus();
                        txtRecNIC.setFocusColor(Color.RED);
                    }
                }else {
                    txtResAddres.requestFocus();
                    txtResAddres.setFocusColor(Color.RED);
                }
            }else{
                txtResContact.requestFocus();
                txtResContact.setFocusColor(Color.RED);
            }
        }else {
            txtResName.requestFocus();
            txtResName.setFocusColor(Color.RED);
        }
    }

    @FXML
    void searchReception(ActionEvent event) {
        try {
            ReceptionDTO receptionDTO = receptionMgtService.searchReception(txtSearchReception.getText());
            if (receptionDTO != null) {
                txtRecID.setText(receptionDTO.getReception_ID());
                txtRecNIC.setText(receptionDTO.getReception_nic());
                txtResAddres.setText(receptionDTO.getReception_address());
                txtResContact.setText(receptionDTO.getReception_Contact());
                txtResName.setText(receptionDTO.getReception_Name());
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "We couldn't find anything !", ButtonType.OK);
                a.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void tblOnClick(MouseEvent event) {
        if (tblReception.getSelectionModel().getSelectedIndex() != -1) {
            ReceptionDTO receptionDTO = tblReception.getSelectionModel().getSelectedItem();
            txtRecID.setText(receptionDTO.getReception_ID());
            txtRecNIC.setText(receptionDTO.getReception_nic());
            txtResAddres.setText(receptionDTO.getReception_address());
            txtResContact.setText(receptionDTO.getReception_Contact());
            txtResName.setText(receptionDTO.getReception_Name());
        }
    }

    @FXML
    void saveReception(ActionEvent event) {
        if(Validation.nameValidate(txtResName.getText())) {
            if(Validation.telephoneValidate(txtResContact.getText())){
                if(Validation.addressValidate(txtResAddres.getText())){
                    if(Validation.nicValidate(txtRecNIC.getText())){
                        try {
                            boolean b = receptionMgtService.addReception(new ReceptionDTO(txtRecID.getText(), txtResName.getText(), txtResContact.getText(), txtResAddres.getText(), txtRecNIC.getText()));
                            if (b) {
                                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Reception Saved !", ButtonType.OK);
                                a.show();
                                getAllReception();
                                setReceptionID();
                                clearText();
                            }
                        } catch (Exception e) {
                            Alert a = new Alert(Alert.AlertType.ERROR, "Reception not Saved !", ButtonType.OK);
                            a.show();
                        }
                    }else {
                        txtRecNIC.requestFocus();
                        txtRecNIC.setFocusColor(Color.RED);
                    }
                }else {
                    txtResAddres.requestFocus();
                    txtResAddres.setFocusColor(Color.RED);
                }
            }else{
                txtResContact.requestFocus();
                txtResContact.setFocusColor(Color.RED);
            }
        }else {
            txtResName.requestFocus();
            txtResName.setFocusColor(Color.RED);
        }
    }

    private void clearText() {
//        txtRecID.clear();
        txtRecNIC.clear();
        txtResAddres.clear();
        txtResContact.clear();
        txtResName.clear();

    }

    private void getAllReception() {
        try {
            ArrayList<ReceptionDTO> all = receptionMgtService.getAll();
            tblReception.setItems(FXCollections.observableArrayList(all));
            strings = new ArrayList<>();
            for (ReceptionDTO e : all) {
                strings.add(e.getReception_nic());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
