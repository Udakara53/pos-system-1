package lk.icet.pos.entity;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private String orderId;
    private String customer;
    private Date date;
    private double total;


    public Order() {
    }

    public Order(String orderId, String customer, Date date, double total) {
        this.orderId = orderId;
        this.customer = customer;
        this.date = date;
        this.total = total;

    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


}
