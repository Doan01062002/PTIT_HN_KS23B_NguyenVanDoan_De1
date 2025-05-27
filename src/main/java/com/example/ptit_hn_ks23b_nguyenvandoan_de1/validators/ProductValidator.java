package com.example.ptit_hn_ks23b_nguyenvandoan_de1.validators;

import com.example.ptit_hn_ks23b_nguyenvandoan_de1.model.Product;
import com.example.ptit_hn_ks23b_nguyenvandoan_de1.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class ProductValidator implements Validator {

    private final CategoryService categoryService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;

        // Kiểm tra tên sản phẩm
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            errors.rejectValue("name", "notblank.product.name", "Tên sản phẩm không được để trống");
        }

        // Kiểm tra giá
        if (product.getPrice() <= 0) {
            errors.rejectValue("price", "min.product.price", "Giá phải lớn hơn 0");
        }

        // Kiểm tra danh mục
        if (product.getCategoryId() <= 0 || categoryService.getCategoryById(product.getCategoryId()) == null) {
            errors.rejectValue("categoryId", "invalid.product.categoryId", "Danh mục không hợp lệ");
        }
    }
}