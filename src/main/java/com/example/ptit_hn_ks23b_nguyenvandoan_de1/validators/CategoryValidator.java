package com.example.ptit_hn_ks23b_nguyenvandoan_de1.validators;


import com.example.ptit_hn_ks23b_nguyenvandoan_de1.model.Category;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CategoryValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Category.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Category category = (Category) target;
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            errors.rejectValue("name", "category.name.empty", "Tên danh mục không được để trống");
        }
    }
}