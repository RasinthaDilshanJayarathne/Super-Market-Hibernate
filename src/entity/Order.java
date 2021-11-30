package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Order {
    @Id
    private String orderId;
    private LocalDate orderDate;
    private LocalTime orderTime;
    //private String customerId;
    private double coust;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<Item> itemList = new ArrayList();

    public Order() {
    }

//    public Order(String orderId, LocalDate orderDate, LocalTime orderTime, double coust) {
//        this.setOrderId(orderId);
//        this.setOrderDate(orderDate);
//        this.setOrderTime(orderTime);
//        this.setCoust(coust);
//    }

    public Order(String orderId, LocalDate orderDate, LocalTime orderTime, double coust, Customer customer) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.coust = coust;
        this.customer = customer;
    }

    public Order(String orderId, LocalDate orderDate, LocalTime orderTime, double coust, Customer customer, List<Item> itemList) {
        this.setOrderId(orderId);
        this.setOrderDate(orderDate);
        this.setOrderTime(orderTime);
        this.setCoust(coust);
        this.setCustomer(customer);
        this.setItemList(itemList);
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }

    public double getCoust() {
        return coust;
    }

    public void setCoust(double coust) {
        this.coust = coust;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderDate=" + orderDate +
                ", orderTime=" + orderTime +
                ", coust=" + coust +
                ", customer=" + customer +
                ", itemList=" + itemList +
                '}';
    }
}
