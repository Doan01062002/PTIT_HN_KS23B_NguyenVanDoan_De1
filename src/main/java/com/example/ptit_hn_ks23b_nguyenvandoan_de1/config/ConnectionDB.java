package com.example.ptit_hn_ks23b_nguyenvandoan_de1.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hackathon_web_java", "root", "01062002"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}