package com.example.ptit_hn_ks23b_nguyenvandoan_de1.services;

import com.example.ptit_hn_ks23b_nguyenvandoan_de1.dao.ProductDAO;
import com.example.ptit_hn_ks23b_nguyenvandoan_de1.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDAO productDAO;

    public List<Product> getAllProducts(int page, int pageSize) {
        return productDAO.getAllProducts(page, pageSize);
    }

    public int getTotalProducts() {
        return productDAO.getTotalProducts();
    }

    public Product getProductById(int id) {
        return productDAO.getProductById(id);
    }

    public boolean addProduct(Product product) {
        return productDAO.addProduct(product);
    }

    public boolean updateProduct(Product product) {
        return productDAO.updateProduct(product);
    }

    public boolean deleteProduct(int id) {
        return productDAO.deleteProduct(id);
    }

    public List<Product> searchProducts(String name) {
        return productDAO.searchProducts(name);
    }
}
