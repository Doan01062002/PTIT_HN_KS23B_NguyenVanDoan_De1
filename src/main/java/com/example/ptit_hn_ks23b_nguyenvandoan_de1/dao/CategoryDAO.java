package com.example.ptit_hn_ks23b_nguyenvandoan_de1.dao;

import com.example.ptit_hn_ks23b_nguyenvandoan_de1.config.ConnectionDB;
import com.example.ptit_hn_ks23b_nguyenvandoan_de1.model.Category;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class CategoryDAO {

    public List<Category> getAll() {
        List<Category> list = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM category");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category s = new Category();
                s.setCategory_id(rs.getInt("category_id"));
                s.setCategory_name(rs.getString("category_name"));
                s.setDescription(rs.getString("description"));
                s.setStatus(rs.getInt("status"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insert(Category s) {
        try (Connection conn = ConnectionDB.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL insert_category(?,?)}");
            cs.setString(1, s.getCategory_name());
            cs.setString(2, s.getDescription());
            cs.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Category s) {
        try (Connection conn = ConnectionDB.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL update_category(?,?,?)}");
            cs.setInt(1, s.getCategory_id());
            cs.setString(2, s.getCategory_name());
            cs.setString(3, s.getDescription());
            cs.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = ConnectionDB.getConnection()) {
            CallableStatement cs = conn.prepareCall("{CALL delete_category(?)}");
            cs.setInt(1, id);
            cs.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Category findById(int id) {
        Category s = null;
        try (Connection conn = ConnectionDB.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM category WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                s = new Category();
                s.setCategory_id(rs.getInt("category_id"));
                s.setCategory_name(rs.getString("category_name"));
                s.setDescription(rs.getString("description"));
                s.setStatus(rs.getInt("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}