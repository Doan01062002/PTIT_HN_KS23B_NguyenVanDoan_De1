package com.example.ptit_hn_ks23b_nguyenvandoan_de1.services;

import com.example.ptit_hn_ks23b_nguyenvandoan_de1.dao.CategoryDAO;
import com.example.ptit_hn_ks23b_nguyenvandoan_de1.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryDAO categoryDAO;

    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    public Category getCategoryById(int id) {
        return categoryDAO.getCategoryById(id);
    }

    public boolean addCategory(Category category) {
        return categoryDAO.addCategory(category);
    }

    public boolean updateCategory(Category category) {
        return categoryDAO.updateCategory(category);
    }

    public boolean deleteCategory(int id) {
        if (categoryDAO.hasProducts(id)) {
            return false;
        }
        return categoryDAO.deleteCategory(id);
    }

    public List<Category> searchCategories(String name) {
        return categoryDAO.searchCategories(name);
    }
}