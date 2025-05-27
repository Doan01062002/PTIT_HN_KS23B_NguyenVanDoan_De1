package com.example.ptit_hn_ks23b_nguyenvandoan_de1.dao;


import com.example.ptit_hn_ks23b_nguyenvandoan_de1.config.ConnectionDB;
import com.example.ptit_hn_ks23b_nguyenvandoan_de1.model.Product;

import java.sql.*;
import java.util.*;

public class ProductDAO {

    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM product");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product s = new Product();
                s.setProduct_id(rs.getInt("product_id"));
                s.setProduct_name(rs.getString("product_name"));
                s.setDescription(rs.getString("description"));
                s.setPrice(rs.getDouble("price"));
                s.setImage_url(rs.getString("image_url"));
                s.setStatus(rs.getInt("status"));
                s.setCreated_at(rs.getDate("created_at"));
                s.setCategory_id(rs.getInt("category_id"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insert(Product s) {
        try (Connection conn = ConnectionDB.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL insert_product(?,?,?,?,?)}");
            cs.setString(1, s.getProduct_name());
            cs.setString(2, s.getDescription());
            cs.setDouble(3, s.getPrice());
            cs.setString(4, s.getImage_url());
            cs.setInt(5, s.getCategory_id());
            cs.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Product s) {
        try (Connection conn = ConnectionDB.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL update_product(?,?,?,?,?,?)}");
            cs.setInt(1, s.getProduct_id());
            cs.setString(2, s.getProduct_name());
            cs.setString(3, s.getDescription());
            cs.setDouble(4, s.getPrice());
            cs.setString(5, s.getImage_url());
            cs.setInt(6, s.getCategory_id());
            cs.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = ConnectionDB.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL delete_product(?)}");
            cs.setInt(1, id);
            cs.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Product findById(int id) {
        Product s = null;
        try (Connection conn = ConnectionDB.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM product WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                s = new Product();
                s.setProduct_id(rs.getInt("product_id"));
                s.setProduct_name(rs.getString("product_name"));
                s.setDescription(rs.getString("description"));
                s.setPrice(rs.getDouble("price"));
                s.setImage_url(rs.getString("image_url"));
                s.setStatus(rs.getInt("status"));
                s.setCreated_at(rs.getDate("created_at"));
                s.setCategory_id(rs.getInt("category_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}