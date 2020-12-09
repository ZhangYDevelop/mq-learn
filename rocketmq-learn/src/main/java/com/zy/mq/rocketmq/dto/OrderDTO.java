package com.zy.mq.rocketmq.dto;

import java.io.Serializable;

public class OrderDTO implements Serializable {

    private  String orderId;

    private String productName;

    private int price;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
