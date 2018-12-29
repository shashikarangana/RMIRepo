package lk.ijse.edu.elite.server.service.impl;

import lk.ijse.edu.elite.common.service.service.custom.ReportService;
import lk.ijse.edu.elite.common.db.DBConnection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.HashMap;

public class ReportServiceImpl extends UnicastRemoteObject implements ReportService {

    public ReportServiceImpl() throws RemoteException {

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

    @Override
    public void loadBestChefReport() throws Exception {
        loadReports("/lk/ijse/edu/elite/client/reports/BestCustomers.jasper");
//        loadReports(url);
    }

    @Override
    public void loadBestChefDeliverBoy() throws Exception {
        loadReports("/lk/ijse/edu/elite/client/reports/TopDelivery.jasper");
    }

    @Override
    public void topSellingItem() throws Exception {
        loadReports("/lk/ijse/edu/elite/client/reports/TopSellingItem.jasper");
    }

    @Override
    public void homeDelivery() throws Exception {
        loadReports("/lk/ijse/edu/elite/client/reports/HomeDelivery.jasper");
    }

    @Override
    public void takeAway() throws Exception {
        loadReports("/lk/ijse/edu/elite/client/reports/TakeAway.jasper");
    }

    @Override
    public void bestReception() throws Exception {
        loadReports("/lk/ijse/edu/elite/client/reports/TopReception.jasper");
    }

    @Override
    public void bestCustomer() throws Exception {
        loadReports("/lk/ijse/edu/elite/client/reports/BestCustomers.jasper");
    }

    @Override
    public void currentChefReport() throws Exception {
        loadReports("/lk/ijse/edu/elite/client/reports/Chef_Report.jasper");
    }

    @Override
    public void currentCustomerReport() throws Exception {
        loadReports("/lk/ijse/edu/elite/client/reports/Customer_Report.jasper");
    }

    @Override
    public void currentDeliveryBoy() throws Exception {
        loadReports("/lk/ijse/edu/elite/client/reports/Deliver_Report.jasper");
    }

    @Override
    public void CurrentItems() throws Exception {
        loadReports("/lk/ijse/edu/elite/client/reports/Food_Report.jasper");
    }
}
