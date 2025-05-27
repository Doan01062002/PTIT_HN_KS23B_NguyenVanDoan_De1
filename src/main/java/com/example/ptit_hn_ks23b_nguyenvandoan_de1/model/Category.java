package com.example.ptit_hn_ks23b_nguyenvandoan_de1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private int category_id;
    private String category_name;
    private String description;
    private int status;
}
