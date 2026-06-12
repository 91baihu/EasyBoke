package com.easyboke;

import com.easyboke.entity.po.Admin;
import com.easyboke.service.AdminService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class LoginTest {

    public static void main(String[] args) {
        System.out.println("=== 启动Spring Boot应用进行登录测试 ===\n");
        System.out.println("提示: 使用端口 8081 启动应用\n");

        String[] testArgs = new String[]{"--server.port=8081"};

        ConfigurableApplicationContext context = SpringApplication.run(EasyBokeApplication.class, testArgs);

        try {
            AdminService adminService = context.getBean(AdminService.class);

            System.out.println("\n等待应用完全启动...");
            Thread.sleep(2000);

            testLogin(adminService);

        } catch (Exception e) {
            System.err.println("测试执行失败: " + e.getMessage());
            e.printStackTrace();
        } finally {
            context.close();
        }
    }

    private static void testLogin(AdminService adminService) {
        System.out.println("\n========================================");
        System.out.println("=== 开始测试管理员登录功能 ===");
        System.out.println("========================================\n");

        int passCount = 0;
        int failCount = 0;

        // 测试用例1: 使用正确的用户名和密码登录
        System.out.println("【测试1】使用正确的用户名和密码登录");
        System.out.println("  输入: username=admin, password=123456");
        try {
            Admin admin = adminService.login("admin", "123456");
            if (admin != null) {
                System.out.println("  结果: ✓ 登录成功！");
                System.out.println("    - 管理员ID: " + admin.getId());
                System.out.println("    - 用户名: " + admin.getUsername());
                System.out.println("    - 昵称: " + admin.getNickname());
                if (admin.getEmail() != null) {
                    System.out.println("    - 邮箱: " + admin.getEmail());
                }
                if (admin.getPhone() != null) {
                    System.out.println("    - 手机号: " + admin.getPhone());
                }
                System.out.println("    - 状态: " + (admin.getStatus() == 1 ? "启用" : "禁用"));
                if (admin.getPassword() == null) {
                    System.out.println("    - 安全: ✓ 密码字段已清空");
                }
                passCount++;
            } else {
                System.out.println("  结果: ✗ 登录失败: 返回的管理员对象为null");
                failCount++;
            }
        } catch (Exception e) {
            System.out.println("  结果: ✗ 登录失败: " + e.getMessage());
            failCount++;
        }

        // 测试用例2: 使用错误的用户名登录
        System.out.println("\n【测试2】使用错误的用户名登录");
        System.out.println("  输入: username=wronguser, password=123456");
        try {
            adminService.login("wronguser", "123456");
            System.out.println("  结果: ✗ 应该抛出异常但没有抛出");
            failCount++;
        } catch (Exception e) {
            System.out.println("  结果: ✓ 正确捕获异常");
            System.out.println("    - 异常信息: " + e.getMessage());
            passCount++;
        }

        // 测试用例3: 使用错误的密码登录
        System.out.println("\n【测试3】使用错误的密码登录");
        System.out.println("  输入: username=admin, password=wrongpass");
        try {
            adminService.login("admin", "wrongpass");
            System.out.println("  结果: ✗ 应该抛出异常但没有抛出");
            failCount++;
        } catch (Exception e) {
            System.out.println("  结果: ✓ 正确捕获异常");
            System.out.println("    - 异常信息: " + e.getMessage());
            passCount++;
        }

        // 测试用例4: 用户名为空
        System.out.println("\n【测试4】用户名为空");
        System.out.println("  输入: username=(空), password=123456");
        try {
            adminService.login("", "123456");
            System.out.println("  结果: ✗ 应该抛出异常但没有抛出");
            failCount++;
        } catch (Exception e) {
            System.out.println("  结果: ✓ 正确捕获异常");
            System.out.println("    - 异常信息: " + e.getMessage());
            passCount++;
        }

        // 测试用例5: 密码为空
        System.out.println("\n【测试5】密码为空");
        System.out.println("  输入: username=admin, password=(空)");
        try {
            adminService.login("admin", "");
            System.out.println("  结果: ✗ 应该抛出异常但没有抛出");
            failCount++;
        } catch (Exception e) {
            System.out.println("  结果: ✓ 正确捕获异常");
            System.out.println("    - 异常信息: " + e.getMessage());
            passCount++;
        }

        // 输出测试总结
        System.out.println("\n========================================");
        System.out.println("=== 测试完成 ===");
        System.out.println("========================================");
        System.out.println("总测试数: " + (passCount + failCount));
        System.out.println("通过: " + passCount + " ✓");
        System.out.println("失败: " + failCount + " ✗");
        System.out.println("========================================\n");
    }
}
