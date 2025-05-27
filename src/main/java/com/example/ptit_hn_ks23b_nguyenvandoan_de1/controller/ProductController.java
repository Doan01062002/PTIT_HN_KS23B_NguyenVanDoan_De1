package com.example.ptit_hn_ks23b_nguyenvandoan_de1.controller;

import com.example.ptit_hn_ks23b_nguyenvandoan_de1.model.Product;
import com.example.ptit_hn_ks23b_nguyenvandoan_de1.services.CategoryService;
import com.example.ptit_hn_ks23b_nguyenvandoan_de1.services.ProductService;
import com.example.ptit_hn_ks23b_nguyenvandoan_de1.validators.ProductValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductValidator productValidator;

    @GetMapping("/list")
    public String listProducts(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        int pageSize = 5;
        List<Product> products = productService.getAllProducts(page, pageSize);
        int totalProducts = productService.getTotalProducts();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);
        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("message", products.isEmpty() ? "Danh sách trống!" : null);
        return "product/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "product/add";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult result,
                             @RequestParam(value = "imageUrl", required = false) String imageUrl, Model model) {
        // Validate sản phẩm
        if (!result.hasErrors()) {
            productValidator.validate(product, result);
        }

        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "product/add";
        }

        // Kiểm tra và gán URL ảnh
        if (imageUrl != null && !imageUrl.trim().isEmpty()) {
            // Kiểm tra định dạng URL (cơ bản)
            if (!imageUrl.matches("^(https?|ftp)://.*\\.(jpeg|jpg|png)$")) {
                model.addAttribute("error", "URL ảnh không hợp lệ! Chỉ hỗ trợ JPEG hoặc PNG.");
                model.addAttribute("categories", categoryService.getAllCategories());
                return "product/add";
            }
            product.setImage(imageUrl);
        }

        productService.addProduct(product);
        return "redirect:/product/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            model.addAttribute("error", "Sản phẩm không tồn tại!");
            return "product/list";
        }
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "product/edit";
    }

    @PostMapping("/edit")
    public String updateProduct(@Valid @ModelAttribute("product") Product product, BindingResult result,
                                @RequestParam(value = "imageUrl", required = false) String imageUrl, Model model) {
        productValidator.validate(product, result);
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "product/edit";
        }

        // Kiểm tra và gán URL ảnh
        if (imageUrl != null && !imageUrl.trim().isEmpty()) {
            // Kiểm tra định dạng URL (cơ bản)
            if (!imageUrl.matches("^(https?|ftp)://.*\\.(jpeg|jpg|png)$")) {
                model.addAttribute("error", "URL ảnh không hợp lệ! Chỉ hỗ trợ JPEG hoặc PNG.");
                model.addAttribute("categories", categoryService.getAllCategories());
                return "product/edit";
            }
            product.setImage(imageUrl);
        }

        productService.updateProduct(product);
        return "redirect:/product/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return "redirect:/product/list";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam("name") String name, Model model) {
        List<Product> products = productService.searchProducts(name);
        model.addAttribute("products", products);
        model.addAttribute("message", products.isEmpty() ? "Không tìm thấy sản phẩm!" : null);
        return "product/list";
    }
}