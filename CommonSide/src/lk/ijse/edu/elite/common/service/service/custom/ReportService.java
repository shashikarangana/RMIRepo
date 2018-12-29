package lk.ijse.edu.elite.common.service.service.custom;

import lk.ijse.edu.elite.common.service.SuperService;

public interface ReportService extends SuperService {
    public void loadBestChefReport() throws Exception;

    public void loadBestChefDeliverBoy() throws Exception;

    public void topSellingItem() throws Exception;

    public void homeDelivery() throws Exception;

    public void takeAway() throws Exception;

    public void bestReception() throws Exception;

    public void bestCustomer() throws Exception;

    public void currentChefReport() throws Exception;

    public void currentCustomerReport() throws Exception;

    public void currentDeliveryBoy() throws Exception;

    public void CurrentItems() throws Exception;

//    public void topSellingItem() throws Exception;
}