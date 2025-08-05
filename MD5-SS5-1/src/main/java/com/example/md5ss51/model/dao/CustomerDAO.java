package com.example.md5ss51.model.dao;


import com.example.md5ss51.model.entity.Customer;
import com.example.md5ss51.model.entity.Role;

import java.sql.*;

public class CustomerDAO {
    private final String JDBC_URL = "jdbc:mysql://localhost:3306/product_management";
    private final String JDBC_USER = "root";
    private final String JDBC_PASSWORD = "12345678";

    public Customer findByUsernameAndPassword(String username, String password) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "SELECT * FROM customer WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getLong("id"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getString("password"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customer.setGender(rs.getString("gender"));
                customer.setEmail(rs.getString("email"));
                customer.setRole(Role.valueOf(rs.getString("role")));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public CustomerDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
