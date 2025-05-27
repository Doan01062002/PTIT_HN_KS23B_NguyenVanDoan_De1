package com.example.ptit_hn_ks23b_nguyenvandoan_de1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int product_id;
    private String product_name;
    private String description;
    private double price;
    private String image_url;
    private int status;
    private Date created_at;
    private int category_id;
}
