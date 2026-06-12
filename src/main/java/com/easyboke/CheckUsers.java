package com.easyboke;

import java.sql.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CheckUsers {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/easyboke?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai";
        String dbUser = "root";
        String dbPass = "12345678";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPass)) {
            System.out.println("=== 检查用户数据 ===");
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT id, username, account, password, status FROM `user` ORDER BY id")) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String username = rs.getString("username");
                    String account = rs.getString("account");
                    String password = rs.getString("password");
                    int status = rs.getInt("status");

                    String pwdPreview = password != null ? password.substring(0, Math.min(30, password.length())) + "..." : "NULL";
                    boolean looksEncrypted = password != null && password.startsWith("$2a$");

                    // Test BCrypt verification
                    BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
                    boolean verify = false;
                    if (looksEncrypted) {
                        verify = enc.matches("123456", password);
                    }

                    System.out.printf("ID=%d | %s | %s | 密码=%s | 状态=%d | BCrypt格式=%s | 验证通过=%s%n",
                            id, username, account, pwdPreview, status, looksEncrypted, verify);
                }
            }

            System.out.println("\n=== 检查admin账号 ===");
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT id, username, password, status FROM admin")) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    int status = rs.getInt("status");

                    String pwdPreview = password != null ? password.substring(0, Math.min(30, password.length())) + "..." : "NULL";
                    boolean looksEncrypted = password != null && (password.startsWith("$2a$") || password.startsWith("$2b$") || password.startsWith("$2y$"));

                    BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
                    boolean verify = false;
                    if (looksEncrypted) {
                        verify = enc.matches("123456", password);
                    }

                    System.out.printf("admin ID=%d | %s | 密码=%s | 状态=%d | BCrypt格式=%s | 验证通过=%s%n",
                            id, username, pwdPreview, status, looksEncrypted, verify);
                }
            }
        }
    }
}
