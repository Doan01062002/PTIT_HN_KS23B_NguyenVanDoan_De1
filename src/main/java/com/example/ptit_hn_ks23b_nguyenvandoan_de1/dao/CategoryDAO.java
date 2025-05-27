package com.example.ptit_hn_ks23b_nguyenvandoan_de1.dao;


import com.example.ptit_hn_ks23b_nguyenvandoan_de1.config.ConnectionDB;
import com.example.ptit_hn_ks23b_nguyenvandoan_de1.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryDAO {
    private final ConnectionDB connectionDB;

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String query = "{CALL get_all_categories()}";
        Connection conn = null;
        try {
            conn = connectionDB.getConnection();
            try (CallableStatement stmt = conn.prepareCall(query);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    categories.add(new Category(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getBoolean("status"),
                            rs.getTimestamp("created_at").toLocalDateTime()
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionDB.closeConnection(conn);
        }
        return categories;
    }

    public Category getCategoryById(int id) {
        String query = "{CALL get_category_by_id(?)}";
        Connection conn = null;
        try {
            conn = connectionDB.getConnection();
            try (CallableStatement stmt = conn.prepareCall(query)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Category(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getBoolean("status"),
                                rs.getTimestamp("created_at").toLocalDateTime()
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionDB.closeConnection(conn);
        }
        return null;
    }

    public boolean addCategory(Category category) {
        String query = "{CALL add_category(?, ?, ?)}";
        Connection conn = null;
        try {
            conn = connectionDB.getConnection();
            try (CallableStatement stmt = conn.prepareCall(query)) {
                stmt.setString(1, category.getName());
                stmt.setString(2, category.getDescription());
                stmt.setBoolean(3, category.isStatus());
                stmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            connectionDB.closeConnection(conn);
        }
    }

    public boolean updateCategory(Category category) {
        String query = "{CALL update_category(?, ?, ?, ?)}";
        Connection conn = null;
        try {
            conn = connectionDB.getConnection();
            try (CallableStatement stmt = conn.prepareCall(query)) {
                stmt.setInt(1, category.getId());
                stmt.setString(2, category.getName());
                stmt.setString(3, category.getDescription());
                stmt.setBoolean(4, category.isStatus());
                stmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            connectionDB.closeConnection(conn);
        }
    }

    public boolean deleteCategory(int id) {
        String query = "{CALL delete_category(?)}";
        Connection conn = null;
        try {
            conn = connectionDB.getConnection();
            try (CallableStatement stmt = conn.prepareCall(query)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            connectionDB.closeConnection(conn);
        }
    }

    public List<Category> searchCategories(String name) {
        List<Category> categories = new ArrayList<>();
        String query = "{CALL search_categories(?)}";
        Connection conn = null;
        try {
            conn = connectionDB.getConnection();
            try (CallableStatement stmt = conn.prepareCall(query)) {
                stmt.setString(1, "%" + name + "%");
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        categories.add(new Category(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getBoolean("status"),
                                rs.getTimestamp("created_at").toLocalDateTime()
                        ));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionDB.closeConnection(conn);
        }
        return categories;
    }

    public boolean hasProducts(int categoryId) {
        String query = "{CALL check_category_products(?)}";
        Connection conn = null;
        try {
            conn = connectionDB.getConnection();
            try (CallableStatement stmt = conn.prepareCall(query)) {
                stmt.setInt(1, categoryId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("product_count") > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionDB.closeConnection(conn);
        }
        return false;
    }
}