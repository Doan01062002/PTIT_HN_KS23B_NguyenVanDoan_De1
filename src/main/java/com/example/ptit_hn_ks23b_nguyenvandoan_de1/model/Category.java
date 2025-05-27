package com.example.ptit_hn_ks23b_nguyenvandoan_de1.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {
    private int id;

    @NotBlank(message = "Tên danh mục không được để trống")
    private String name;

    private String description;
    private boolean status;
    private LocalDateTime createdAt;
}
