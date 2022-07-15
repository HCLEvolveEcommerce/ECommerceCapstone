package com.hcl.ecommerce.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Order extends Product{
    private int orderID;
    private int userID;
    private int quantity;
    private String orderDate;

    public Order(){
        super();
        this.orderID = orderID;
        this.userID = userID;
        this.quantity = quantity;
        this.orderDate = orderDate;

    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", userID=" + userID +
                ", quantity=" + quantity +
                ", orderDate='" + orderDate + '\'' +
                '}';
    }
}
