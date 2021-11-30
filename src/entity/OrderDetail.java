package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue
    private int orderDetailId;
    @ManyToOne
    private Order order;
    @ManyToOne
    private Item item;
    private int orderQty;
    private double discount;

    public OrderDetail() {
    }

    public OrderDetail(int orderDetailId, Order order, Item item, int orderQty, double discount) {
        this.setOrderDetailId(orderDetailId);
        this.setOrder(order);
        this.setItem(item);
        this.setOrderQty(orderQty);
        this.setDiscount(discount);
    }

    public OrderDetail(Order order, Item item, int orderQty, double discount) {
        this.order = order;
        this.item = item;
        this.orderQty = orderQty;
        this.discount = discount;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderDetailId=" + orderDetailId +
                ", order=" + order +
                ", item=" + item +
                ", orderQty=" + orderQty +
                ", discount=" + discount +
                '}';
    }
}
