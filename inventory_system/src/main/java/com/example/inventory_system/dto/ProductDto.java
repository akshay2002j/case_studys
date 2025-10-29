package com.example.inventory_system.dto;

import com.example.inventory_system.entity.Supplier;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private int quantity;
    private double price;
    private Supplier supplier;
}
