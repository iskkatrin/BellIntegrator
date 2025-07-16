package com.example.stub_api.service;


import com.example.stub_api.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class DataBaseWorker {
    private String url;
    private String user;
    private String password;

    public DataBaseWorker() {
        this.url = "jdbc:postgresql://localhost:5432/testdb";
        this.user = "user";
        this.password = "password";
    }

    // select по login (try/catch, Statement)
    public User getUserByLogin(String login) {
        User userObj = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            String sql = "SELECT login, email FROM profiles WHERE login = '" + login + "'";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                userObj = new User(rs.getString("login"), null, rs.getString("email"), null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }
        return userObj;
    }

    // insert (try-with-resources, PreparedStatement)
    public int insertUser(User user) {
        String sql = "INSERT INTO profiles (login, email) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(url, this.user, this.password);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getEmail());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
