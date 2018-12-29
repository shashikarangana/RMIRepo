package lk.ijse.edu.elite.server.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
public class Orders {
    @Id
    private String order_ID;
    @Temporal(TemporalType.DATE)
    private Date order_date;
    @Temporal(TemporalType.TIME)
    private Date order_time;
    private double total_price;
    private String status;
    private String paymentStatus;
    private boolean homeDelivery;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Customer customer;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Reception reception;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Chef chef;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Delivery delivery;
    @OneToMany(mappedBy = "orders",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private List<OrderDetails>orderDetails=new ArrayList<>();
    @Temporal(TemporalType.TIME)
    private Date chef_order_start;
    @Temporal(TemporalType.TIME)
    private Date chef_order_end;
    @Temporal(TemporalType.TIME)
    private Date deliver_order_start;
    @Temporal(TemporalType.TIME)
    private Date deliver_order_end;

    public Orders(String order_ID, Date order_date, Date order_time, double total_price, String status, String paymentStatus, boolean homeDelivery, Customer customer, Reception reception, Chef chef, Delivery delivery, List<OrderDetails> orderDetails) {
        this.order_ID = order_ID;
        this.order_date = order_date;
        this.order_time = order_time;
        this.total_price = total_price;
        this.status = status;
        this.paymentStatus = paymentStatus;
        this.homeDelivery = homeDelivery;
        this.customer = customer;
        this.reception = reception;
        this.chef = chef;
        this.delivery = delivery;
        this.orderDetails = orderDetails;
    }

    public Orders(String order_ID) {
        this.order_ID = order_ID;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Orders(String order_ID, Date order_date, Date order_time, double total_price, String status, String paymentStatus, boolean homeDelivery) {
        this.order_ID = order_ID;
        this.order_date = order_date;
        this.order_time = order_time;
        this.total_price = total_price;
        this.status = status;
        this.paymentStatus = paymentStatus;
        this.homeDelivery = homeDelivery;
    }

    public Orders(String order_ID, Date order_date, Date order_time, double total_price, String status, String paymentStatus, boolean homeDelivery, Customer customer, Reception reception, Chef chef, Delivery delivery, List<OrderDetails> orderDetails, Date chef_order_start, Date chef_order_end, Date deliver_order_start, Date deliver_order_end) {
        this.order_ID = order_ID;
        this.order_date = order_date;
        this.order_time = order_time;
        this.total_price = total_price;
        this.status = status;
        this.paymentStatus = paymentStatus;
        this.homeDelivery = homeDelivery;
        this.customer = customer;
        this.reception = reception;
        this.chef = chef;
        this.delivery = delivery;
        this.orderDetails = orderDetails;
        this.chef_order_start = chef_order_start;
        this.chef_order_end = chef_order_end;
        this.deliver_order_start = deliver_order_start;
        this.deliver_order_end = deliver_order_end;
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

    public Orders(String order_ID, Date order_date, Date order_time, double total_price, String status, String paymentStatus, boolean homeDelivery, Date chef_order_start, Date chef_order_end, Date deliver_order_start, Date deliver_order_end) {
        this.order_ID = order_ID;
        this.order_date = order_date;
        this.order_time = order_time;
        this.total_price = total_price;
        this.status = status;
        this.paymentStatus = paymentStatus;
        this.homeDelivery = homeDelivery;
        this.chef_order_start = chef_order_start;
        this.chef_order_end = chef_order_end;
        this.deliver_order_start = deliver_order_start;
        this.deliver_order_end = deliver_order_end;
    }

    public Orders() {
    }

    public boolean isHomeDelivery() {
        return homeDelivery;
    }

    public void setHomeDelivery(boolean homeDelivery) {
        this.homeDelivery = homeDelivery;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
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

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Reception getReception() {
        return reception;
    }

    public void setReception(Reception reception) {
        this.reception = reception;
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "order_ID='" + order_ID + '\'' +
                ", order_date=" + order_date +
                ", order_time=" + order_time +
                ", total_price=" + total_price +
                ", status='" + status + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", homeDelivery=" + homeDelivery +
                ", customer=" + customer +
                ", reception=" + reception +
                ", chef=" + chef +
                ", delivery=" + delivery +
                ", orderDetails=" + orderDetails +
                ", chef_order_start=" + chef_order_start +
                ", chef_order_end=" + chef_order_end +
                ", deliver_order_start=" + deliver_order_start +
                ", deliver_order_end=" + deliver_order_end +
                '}';
    }
}
