package com.example.inventory_system.dto;
import com.example.inventory_system.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class SupplierDto {
    private Long id;
    private String name;
    private String contactEmail;
    private String phone;
    private List<Product> products = new ArrayList<>();
}
