package com.example.ptit_hn_ks23b_nguyenvandoan_de1.dao;

import com.example.ptit_hn_ks23b_nguyenvandoan_de1.config.ConnectionDB;
import com.example.ptit_hn_ks23b_nguyenvandoan_de1.dto.CreateProductDTO;
import com.example.ptit_hn_ks23b_nguyenvandoan_de1.model.Product;
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
public class ProductDAO {
    private final ConnectionDB connectionDB;

    public List<Product> getAllProducts(int page, int pageSize) {
        List<Product> products = new ArrayList<>();
        String query = "{CALL get_all_products(?, ?)}";
        Connection conn = null;
        try {
            conn = connectionDB.getConnection();
            try (CallableStatement stmt = conn.prepareCall(query)) {
                stmt.setInt(1, (page - 1) * pageSize);
                stmt.setInt(2, pageSize);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        products.add(new Product(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getDouble("price"),
                                rs.getString("image"),
                                rs.getInt("category_id"),
                                rs.getString("category_name"),
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
        return products;
    }

    public int getTotalProducts() {
        String query = "{CALL get_total_products()}";
        Connection conn = null;
        try {
            conn = connectionDB.getConnection();
            try (CallableStatement stmt = conn.prepareCall(query);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionDB.closeConnection(conn);
        }
        return 0;
    }

    public Product getProductById(int id) {
        String query = "{CALL get_product_by_id(?)}";
        Connection conn = null;
        try {
            conn = connectionDB.getConnection();
            try (CallableStatement stmt = conn.prepareCall(query)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Product(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getDouble("price"),
                                rs.getString("image"),
                                rs.getInt("category_id"),
                                rs.getString("category_name"),
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

    public boolean addProduct(Product product) {
        String query = "{CALL add_product(?, ?, ?, ?, ?, ?)}";
        Connection conn = null;
        try {
            conn = connectionDB.getConnection();
            try (CallableStatement stmt = conn.prepareCall(query)) {
                stmt.setString(1, product.getName());
                stmt.setString(2, product.getDescription());
                stmt.setDouble(3, product.getPrice());
                stmt.setString(4, product.getImage());
                stmt.setInt(5, product.getCategoryId());
                stmt.setBoolean(6, product.isStatus());
                return stmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            connectionDB.closeConnection(conn);
        }
    }

    public boolean updateProduct(Product product) {
        String query = "{CALL update_product(?, ?, ?, ?, ?, ?, ?)}";
        Connection conn = null;
        try {
            conn = connectionDB.getConnection();
            try (CallableStatement stmt = conn.prepareCall(query)) {
                stmt.setInt(1, product.getId());
                stmt.setString(2, product.getName());
                stmt.setString(3, product.getDescription());
                stmt.setDouble(4, product.getPrice());
                stmt.setString(5, product.getImage());
                stmt.setInt(6, product.getCategoryId());
                stmt.setBoolean(7, product.isStatus());
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

    public boolean deleteProduct(int id) {
        String query = "{CALL delete_product(?)}";
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

    public List<Product> searchProducts(String name) {
        List<Product> products = new ArrayList<>();
        String query = "{CALL search_products(?)}";
        Connection conn = null;
        try {
            conn = connectionDB.getConnection();
            try (CallableStatement stmt = conn.prepareCall(query)) {
                stmt.setString(1, "%" + name + "%");
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        products.add(new Product(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getDouble("price"),
                                rs.getString("image"),
                                rs.getInt("category_id"),
                                rs.getString("category_name"),
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
        return products;
    }
}
