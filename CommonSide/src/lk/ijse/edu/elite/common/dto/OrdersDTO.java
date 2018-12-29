package lk.ijse.edu.elite.common.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrdersDTO implements Serializable {
    private String order_ID;
    private Date order_date;
    private Date order_time;
    private String status;
    private String paymentStatus;
    private boolean homeDelivery;
    private double total_price;
    private List<OrderDetailDTO> orderDetailDTOS;
    private CustomerDTO customerDTO;
    private String reception_id;
    private String chef_id;
    private String rider_id;
    private Date chef_order_start;
    private Date chef_order_end;
    private Date deliver_order_start;
    private Date deliver_order_end;

    public OrdersDTO(String order_ID, Date order_date, Date order_time, String status, String paymentStatus, boolean homeDelivery, double total_price, String reception_id, String chef_id, String rider_id) {
        this.order_ID = order_ID;
        this.order_date = order_date;
        this.order_time = order_time;
        this.status = status;
        this.paymentStatus = paymentStatus;
        this.homeDelivery = homeDelivery;
        this.total_price = total_price;
        this.reception_id = reception_id;
        this.chef_id = chef_id;
        this.rider_id = rider_id;
    }

    public OrdersDTO(String order_ID, Date order_date, Date order_time, String status, String paymentStatus, boolean homeDelivery, double total_price, List<OrderDetailDTO> orderDetailDTOS, CustomerDTO customerDTO, String reception_id, String chef_id, String rider_id, Date chef_order_start, Date chef_order_end, Date deliver_order_start, Date deliver_order_end) {
        this.order_ID = order_ID;
        this.order_date = order_date;
        this.order_time = order_time;
        this.status = status;
        this.paymentStatus = paymentStatus;
        this.homeDelivery = homeDelivery;
        this.total_price = total_price;
        this.orderDetailDTOS = orderDetailDTOS;
        this.customerDTO = customerDTO;
        this.reception_id = reception_id;
        this.chef_id = chef_id;
        this.rider_id = rider_id;
        this.chef_order_start = chef_order_start;
        this.chef_order_end = chef_order_end;
        this.deliver_order_start = deliver_order_start;
        this.deliver_order_end = deliver_order_end;
    }

    public OrdersDTO(String order_ID, Date order_date, Date order_time, String status, String paymentStatus, boolean homeDelivery, double total_price, String reception_id, String chef_id, String rider_id, Date chef_order_start, Date chef_order_end, Date deliver_order_start, Date deliver_order_end) {
        this.order_ID = order_ID;
        this.order_date = order_date;
        this.order_time = order_time;
        this.status = status;
        this.paymentStatus = paymentStatus;
        this.homeDelivery = homeDelivery;
        this.total_price = total_price;
        this.reception_id = reception_id;
        this.chef_id = chef_id;
        this.rider_id = rider_id;
        this.chef_order_start = chef_order_start;
        this.chef_order_end = chef_order_end;
        this.deliver_order_start = deliver_order_start;
        this.deliver_order_end = deliver_order_end;
    }

    public OrdersDTO(String order_ID, Date order_date, Date order_time, String status, String paymentStatus, boolean homeDelivery, double total_price) {
        this.order_ID = order_ID;
        this.order_date = order_date;
        this.order_time = order_time;
        this.status = status;
        this.paymentStatus = paymentStatus;
        this.homeDelivery = homeDelivery;
        this.total_price = total_price;
    }

    public OrdersDTO(String order_ID, Date order_date, Date order_time, String status, String paymentStatus, boolean homeDelivery, double total_price, List<OrderDetailDTO> orderDetailDTOS, String reception_id) {
        this.order_ID = order_ID;
        this.order_date = order_date;
        this.order_time = order_time;
        this.status = status;
        this.paymentStatus = paymentStatus;
        this.homeDelivery = homeDelivery;
        this.total_price = total_price;
        this.orderDetailDTOS = orderDetailDTOS;
        this.reception_id = reception_id;
    }

    public OrdersDTO(String order_ID, Date order_date, Date order_time, String status, String paymentStatus, boolean homeDelivery, double total_price, List<OrderDetailDTO> orderDetailDTOS, CustomerDTO customerDTO, String reception_id) {
        this.order_ID = order_ID;
        this.order_date = order_date;
        this.order_time = order_time;
        this.status = status;
        this.paymentStatus = paymentStatus;
        this.homeDelivery = homeDelivery;
        this.total_price = total_price;
        this.orderDetailDTOS = orderDetailDTOS;
        this.customerDTO = customerDTO;
        this.reception_id = reception_id;
    }

    public String getChef_id() {
        return chef_id;
    }

    public void setChef_id(String chef_id) {
        this.chef_id = chef_id;
    }

    public String getRider_id() {
        return rider_id;
    }

    public void setRider_id(String rider_id) {
        this.rider_id = rider_id;
    }

    public OrdersDTO() {
    }

    public Date getChef_order_start() {
        return chef_order_start;
    }

    public void setChef_order_start(Date chef_order_start) {
        this.chef_order_start = chef_order_start;
    }

    public Date getChef_order_end() {
        return chef_order_end;
    }

    public void setChef_order_end(Date chef_order_end) {
        this.chef_order_end = chef_order_end;
    }

    public Date getDeliver_order_start() {
        return deliver_order_start;
    }

    public void setDeliver_order_start(Date deliver_order_start) {
        this.deliver_order_start = deliver_order_start;
    }

    public Date getDeliver_order_end() {
        return deliver_order_end;
    }

    public void setDeliver_order_end(Date deliver_order_end) {
        this.deliver_order_end = deliver_order_end;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public String getReception_id() {
        return reception_id;
    }

    public void setReception_id(String reception_id) {
        this.reception_id = reception_id;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public String getOrder_ID() {
        return order_ID;
    }

    public void setOrder_ID(String order_ID) {
        this.order_ID = order_ID;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public boolean isHomeDelivery() {
        return homeDelivery;
    }

    public void setHomeDelivery(boolean homeDelivery) {
        this.homeDelivery = homeDelivery;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public List<OrderDetailDTO> getOrderDetailDTOS() {
        return orderDetailDTOS;
    }

    public void setOrderDetailDTOS(List<OrderDetailDTO> orderDetailDTOS) {
        this.orderDetailDTOS = orderDetailDTOS;
    }

    @Override
    public String toString() {
        return "OrdersDTO{" +
                "order_ID='" + order_ID + '\'' +
                ", order_date=" + order_date +
                ", order_time=" + order_time +
                ", status='" + status + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", homeDelivery=" + homeDelivery +
                ", total_price=" + total_price +
                ", orderDetailDTOS=" + orderDetailDTOS +
                ", customerDTO=" + customerDTO +
                ", reception_id='" + reception_id + '\'' +
                ", chef_id='" + chef_id + '\'' +
                ", rider_id='" + rider_id + '\'' +
                ", chef_order_start=" + chef_order_start +
                ", chef_order_end=" + chef_order_end +
                ", deliver_order_start=" + deliver_order_start +
                ", deliver_order_end=" + deliver_order_end +
                '}';
    }
}
