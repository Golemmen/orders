package com.example.orders.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

public class CreateOrderDTO {

    @NotEmpty
    private List<String> items;

    @NotNull
    @Positive
    private Double totalPrice;

    @NotNull
    private String customerName;

    // Геттеры и сеттеры
    public @NotEmpty List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
