package com.example.ptit_hn_ks23b_nguyenvandoan_de1.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private String image;
    private Integer categoryId;
    private String categoryName;
    private boolean status;
    private LocalDateTime createdAt;
}