package com.example.orders.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private List<String> items;
    private Double totalPrice;
    private LocalDateTime orderDate;
}