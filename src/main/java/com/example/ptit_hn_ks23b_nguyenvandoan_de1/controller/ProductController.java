package com.example.ptit_hn_ks23b_nguyenvandoan_de1.controller;

import com.example.ptit_hn_ks23b_nguyenvandoan_de1.dao.ProductDAO;
import com.example.ptit_hn_ks23b_nguyenvandoan_de1.model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/product")
public class ProductController extends HttpServlet {
    private ProductDAO dao = new ProductDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                req.getRequestDispatcher("/views/form_product.jsp").forward(req, res);
                break;
            case "edit":
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("product", dao.findById(id));
                req.getRequestDispatcher("/views/form_product.jsp").forward(req, res);
                break;
            case "delete":
                dao.delete(Integer.parseInt(req.getParameter("id")));
                res.sendRedirect("product");
                break;
            default:
                List<Product> products = dao.getAll();
                req.setAttribute("list", products);
                req.getRequestDispatcher("/views/list_product.jsp").forward(req, res);
                break;
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = req.getParameter("id").isEmpty() ? 0 : Integer.parseInt(req.getParameter("id"));
        String productName = req.getParameter("product_name");
        String description = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));
        String imageUrl = req.getParameter("image_url");
        int status = Integer.parseInt(req.getParameter("status"));
        int categoryId = Integer.parseInt(req.getParameter("category_id"));

        Product p = new Product();
        p.setProduct_id(id);
        p.setProduct_name(productName);
        p.setDescription(description);
        p.setPrice(price);
        p.setImage_url(imageUrl);
        p.setStatus(status);
        p.setCategory_id(categoryId);

        if (id == 0) dao.insert(p);
        else dao.update(p);

        res.sendRedirect("product");
    }
}