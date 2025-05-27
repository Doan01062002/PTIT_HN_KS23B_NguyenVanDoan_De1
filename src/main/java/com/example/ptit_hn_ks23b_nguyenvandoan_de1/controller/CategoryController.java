package com.example.ptit_hn_ks23b_nguyenvandoan_de1.controller;

import com.example.ptit_hn_ks23b_nguyenvandoan_de1.dao.CategoryDAO;
import com.example.ptit_hn_ks23b_nguyenvandoan_de1.model.Category;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/category")
public class CategoryController extends HttpServlet {
    private CategoryDAO dao = new CategoryDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                req.getRequestDispatcher("/views/form_category.jsp").forward(req, res);
                break;
            case "edit":
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("category", dao.findById(id));
                req.getRequestDispatcher("/views/form_category.jsp").forward(req, res);
                break;
            case "delete":
                dao.delete(Integer.parseInt(req.getParameter("id")));
                res.sendRedirect("category");
                break;
            default:
                List<Category> categories = dao.getAll();
                req.setAttribute("list", categories);
                req.getRequestDispatcher("/views/list_category.jsp").forward(req, res);
                break;
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = req.getParameter("category_id").isEmpty() ? 0 : Integer.parseInt(req.getParameter("category_id"));
        String categoryName = req.getParameter("category_name");
        String description = req.getParameter("description");

        Category c = new Category();
        c.setCategory_id(id);
        c.setCategory_name(categoryName);
        c.setDescription(description);

        if (id == 0) dao.insert(c);
        else dao.update(c);

        res.sendRedirect("category");
    }
}