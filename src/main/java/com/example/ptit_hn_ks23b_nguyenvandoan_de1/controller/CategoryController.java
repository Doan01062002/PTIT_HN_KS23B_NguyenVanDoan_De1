package com.example.ptit_hn_ks23b_nguyenvandoan_de1.controller;

import com.example.ptit_hn_ks23b_nguyenvandoan_de1.model.Category;
import com.example.ptit_hn_ks23b_nguyenvandoan_de1.services.CategoryService;
import com.example.ptit_hn_ks23b_nguyenvandoan_de1.validators.CategoryValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryValidator categoryValidator;

    @GetMapping("/list")
    public String listCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("message", categories.isEmpty() ? "Danh sách trống!" : null);
        return "category/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/add";
    }

    @PostMapping("/add")
    public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
        categoryValidator.validate(category, result);
        if (result.hasErrors()) {
            return "category/add";
        }
        categoryService.addCategory(category);
        return "redirect:/category/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            model.addAttribute("error", "Danh mục không tồn tại!");
            model.addAttribute("categories", categoryService.getAllCategories());
            return "category/list";
        }
        model.addAttribute("category", category);
        return "category/edit";
    }

    @PostMapping("/edit")
    public String updateCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
        categoryValidator.validate(category, result);
        if (result.hasErrors()) {
            return "category/edit";
        }
        categoryService.updateCategory(category);
        return "redirect:/category/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") int id, Model model) {
        boolean deleted = categoryService.deleteCategory(id);
        if (!deleted) {
            model.addAttribute("error", "Không thể xóa danh mục đang có sản phẩm!");
            model.addAttribute("categories", categoryService.getAllCategories());
            return "category/list";
        }
        return "redirect:/category/list";
    }

    @GetMapping("/search")
    public String searchCategories(@RequestParam("name") String name, Model model) {
        List<Category> categories = categoryService.searchCategories(name);
        model.addAttribute("categories", categories);
        model.addAttribute("message", categories.isEmpty() ? "Không tìm thấy danh mục!" : null);
        return "category/list";
    }
}