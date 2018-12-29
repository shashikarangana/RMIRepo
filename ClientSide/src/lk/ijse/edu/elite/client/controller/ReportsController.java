package lk.ijse.edu.elite.client.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lk.ijse.edu.elite.client.proxy.ProxyHandler;
import lk.ijse.edu.elite.common.db.DBConnection;
import lk.ijse.edu.elite.common.service.ServiceFactory;
import lk.ijse.edu.elite.common.service.service.custom.ReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {
    @FXML
    private JFXButton btnBestD_Boy;

    @FXML
    private JFXButton btnBestChef;

    @FXML
    private JFXButton btnBestReception;

    @FXML
    private JFXButton btnBestCustomer;

    @FXML
    private JFXButton btnTopSellingItem;

    @FXML
    private JFXButton btnCustomerReport;

    @FXML
    private JFXButton btnFoodItems;

    @FXML
    private JFXButton btnChefs;

    @FXML
    private JFXButton btnDelivery;

    @FXML
    private JFXButton btnHomeDelivery;
    private ReportService reportService;
    @FXML
    private JFXButton btnTakeAway;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            reportService = (ReportService) ProxyHandler.getInstance().getSuperService(ServiceFactory.ServiceTypes.REPORT);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadReports(String url) throws SQLException, IOException, ClassNotFoundException, JRException {
        InputStream resourceAsStream = getClass().getResourceAsStream(url);

        HashMap map = new HashMap();
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport(resourceAsStream, map, DBConnection.getInstance().getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JasperViewer.viewReport(jasperPrint, false);

    }

    @FXML
    void bestChef(ActionEvent event) {
        try {
            loadReports("/lk/ijse/edu/elite/client/reports/BestChef.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bestCustomer(ActionEvent event) {
        try {
            loadReports("/lk/ijse/edu/elite/client/reports/BestCustomers.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bestDeliveryBoy(ActionEvent event) {
        try {
            loadReports("/lk/ijse/edu/elite/client/reports/TopDelivery.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bestReception(ActionEvent event) {
        try {
            loadReports("/lk/ijse/edu/elite/client/reports/TopReception.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void chefReport(ActionEvent event) {
        try {
            loadReports("/lk/ijse/edu/elite/client/reports/Chef_Report.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void customerReport(ActionEvent event) {
        try {
            loadReports("/lk/ijse/edu/elite/client/reports/Customer_Report.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deliveryReport(ActionEvent event) {
        try {
            loadReports("/lk/ijse/edu/elite/client/reports/Deliver_Report.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void foodReport(ActionEvent event) {
        try {
            loadReports("/lk/ijse/edu/elite/client/reports/Food_Report.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void homeDelivery(ActionEvent event) {
        try {
            loadReports("/lk/ijse/edu/elite/client/reports/HomeDelivery.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void takeAway(ActionEvent event) {
        try {
            loadReports("/lk/ijse/edu/elite/client/reports/TakeAway.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void topSellingItem(ActionEvent event) {
        try {
            loadReports("/lk/ijse/edu/elite/client/reports/TopSellingItem.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
