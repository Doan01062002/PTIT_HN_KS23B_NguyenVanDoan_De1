package com.example.ptit_hn_ks23b_nguyenvandoan_de1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDTO {
    private String name;
    private String description;
    private double price;
    private String image;
    private Integer categoryId;
    private boolean status;

}
