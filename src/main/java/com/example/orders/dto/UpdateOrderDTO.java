package com.example.orders.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
public class UpdateOrderDTO {
    @NotEmpty
    private List<String> items;

    @NotNull
    @Positive
    private Double totalPrice;
}