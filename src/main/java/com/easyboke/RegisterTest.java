 package com.easyboke;

import com.easyboke.EasyBokeApplication;
import com.easyboke.entity.po.User;
import com.easyboke.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class RegisterTest {

    public static void main(String[] args) {
        System.out.println("=== 启动Spring Boot应用进行注册测试 ===\n");
        System.out.println("提示: 使用端口 8081 启动应用\n");

        String[] testArgs = new String[]{"--server.port=8081"};

        ConfigurableApplicationContext context = SpringApplication.run(EasyBokeApplication.class, testArgs);

        try {
            UserService userService = context.getBean(UserService.class);

            System.out.println("\n等待应用完全启动...");
            Thread.sleep(2000);

            testRegister(userService);

        } catch (Exception e) {
            System.err.println("测试执行失败: " + e.getMessage());
            e.printStackTrace();
        } finally {
            context.close();
        }
    }

    private static void testRegister(UserService userService) {
        System.out.println("\n========================================");
        System.out.println("=== 开始测试用户注册功能 ===");
        System.out.println("========================================\n");

        int passCount = 0;
        int failCount = 0;

        // 测试用例1: 使用正确的信息注册新用户
        System.out.println("【测试1】使用正确的信息注册新用户");
        System.out.println("  输入: username=testuser1, account=testuser1, password=123456, gender=1, age=25");
        try {
            User user = new User();
            user.setUsername("testuser1");
            user.setAccount("testuser1");
            user.setPassword("123456");
            user.setGender(1);
            user.setAge(25);

            User registeredUser = userService.register(user);
            if (registeredUser != null) {
                System.out.println("  结果: ✓ 注册成功！");
                System.out.println("    - 用户ID: " + registeredUser.getId());
                System.out.println("    - 用户名: " + registeredUser.getUsername());
                System.out.println("    - 账号: " + registeredUser.getAccount());
                System.out.println("    - 性别: " + (registeredUser.getGender() == 1 ? "男" : registeredUser.getGender() == 2 ? "女" : "未知"));
                System.out.println("    - 年龄: " + registeredUser.getAge());
                System.out.println("    - 状态: " + (registeredUser.getStatus() == 1 ? "启用" : "禁用"));
                if (registeredUser.getPassword() == null) {
                    System.out.println("    - 安全: ✓ 密码字段已清空");
                }
                if (registeredUser.getCreateTime() != null) {
                    System.out.println("    - 创建时间: " + registeredUser.getCreateTime());
                }
                passCount++;
            } else {
                System.out.println("  结果: ✗ 注册失败: 返回的用户对象为null");
                failCount++;
            }
        } catch (Exception e) {
            System.out.println("  结果: ✗ 注册失败: " + e.getMessage());
            failCount++;
        }

        // 测试用例2: 注册重复的用户名
        System.out.println("\n【测试2】注册重复的用户名");
        System.out.println("  输入: username=testuser1, account=testuser2, password=123456");
        try {
            User user = new User();
            user.setUsername("testuser1");
            user.setAccount("testuser2");
            user.setPassword("123456");

            userService.register(user);
            System.out.println("  结果: ✗ 应该抛出异常但没有抛出");
            failCount++;
        } catch (Exception e) {
            System.out.println("  结果: ✓ 正确捕获异常");
            System.out.println("    - 异常信息: " + e.getMessage());
            passCount++;
        }

        // 测试用例3: 注册重复的账号
        System.out.println("\n【测试3】注册重复的账号");
        System.out.println("  输入: username=testuser3, account=testuser1, password=123456");
        try {
            User user = new User();
            user.setUsername("testuser3");
            user.setAccount("testuser1");
            user.setPassword("123456");

            userService.register(user);
            System.out.println("  结果: ✗ 应该抛出异常但没有抛出");
            failCount++;
        } catch (Exception e) {
            System.out.println("  结果: ✓ 正确捕获异常");
            System.out.println("    - 异常信息: " + e.getMessage());
            passCount++;
        }

        // 测试用例4: 用户名为空
        System.out.println("\n【测试4】用户名为空");
        System.out.println("  输入: username=(空), account=testuser4, password=123456");
        try {
            User user = new User();
            user.setUsername("");
            user.setAccount("testuser4");
            user.setPassword("123456");

            userService.register(user);
            System.out.println("  结果: ✗ 应该抛出异常但没有抛出");
            failCount++;
        } catch (Exception e) {
            System.out.println("  结果: ✓ 正确捕获异常");
            System.out.println("    - 异常信息: " + e.getMessage());
            passCount++;
        }

        // 测试用例5: 账号为空
        System.out.println("\n【测试5】账号为空");
        System.out.println("  输入: username=testuser5, account=(空), password=123456");
        try {
            User user = new User();
            user.setUsername("testuser5");
            user.setAccount("");
            user.setPassword("123456");

            userService.register(user);
            System.out.println("  结果: ✗ 应该抛出异常但没有抛出");
            failCount++;
        } catch (Exception e) {
            System.out.println("  结果: ✓ 正确捕获异常");
            System.out.println("    - 异常信息: " + e.getMessage());
            passCount++;
        }

        // 测试用例6: 密码为空
        System.out.println("\n【测试6】密码为空");
        System.out.println("  输入: username=testuser6, account=testuser6, password=(空)");
        try {
            User user = new User();
            user.setUsername("testuser6");
            user.setAccount("testuser6");
            user.setPassword("");

            userService.register(user);
            System.out.println("  结果: ✗ 应该抛出异常但没有抛出");
            failCount++;
        } catch (Exception e) {
            System.out.println("  结果: ✓ 正确捕获异常");
            System.out.println("    - 异常信息: " + e.getMessage());
            passCount++;
        }

        // 测试用例7: 密码长度不足
        System.out.println("\n【测试7】密码长度不足（少于6位）");
        System.out.println("  输入: username=testuser7, account=testuser7, password=12345");
        try {
            User user = new User();
            user.setUsername("testuser7");
            user.setAccount("testuser7");
            user.setPassword("12345");

            userService.register(user);
            System.out.println("  结果: ✗ 应该抛出异常但没有抛出");
            failCount++;
        } catch (Exception e) {
            System.out.println("  结果: ✓ 正确捕获异常");
            System.out.println("    - 异常信息: " + e.getMessage());
            passCount++;
        }

        // 测试用例8: 用户名格式不正确（包含特殊字符）
        System.out.println("\n【测试8】用户名格式不正确（包含特殊字符）");
        System.out.println("  输入: username=test@user, account=testuser8, password=123456");
        try {
            User user = new User();
            user.setUsername("test@user");
            user.setAccount("testuser8");
            user.setPassword("123456");

            userService.register(user);
            System.out.println("  结果: ✗ 应该抛出异常但没有抛出");
            failCount++;
        } catch (Exception e) {
            System.out.println("  结果: ✓ 正确捕获异常");
            System.out.println("    - 异常信息: " + e.getMessage());
            passCount++;
        }

        // 测试用例9: 用户名长度不符合要求（太短）
        System.out.println("\n【测试9】用户名长度不符合要求（太短）");
        System.out.println("  输入: username=abc, account=testuser9, password=123456");
        try {
            User user = new User();
            user.setUsername("abc");
            user.setAccount("testuser9");
            user.setPassword("123456");

            userService.register(user);
            System.out.println("  结果: ✗ 应该抛出异常但没有抛出");
            failCount++;
        } catch (Exception e) {
            System.out.println("  结果: ✓ 正确捕获异常");
            System.out.println("    - 异常信息: " + e.getMessage());
            passCount++;
        }

        // 测试用例10: 注册时使用默认值（性别、年龄为空）
        System.out.println("\n【测试10】注册时使用默认值（性别、年龄为空）");
        System.out.println("  输入: username=testuser10, account=testuser10, password=123456, gender=null, age=null");
        try {
            User user = new User();
            user.setUsername("testuser10");
            user.setAccount("testuser10");
            user.setPassword("123456");
            user.setGender(null);
            user.setAge(null);

            User registeredUser = userService.register(user);
            if (registeredUser != null) {
                System.out.println("  结果: ✓ 注册成功！");
                System.out.println("    - 用户ID: " + registeredUser.getId());
                System.out.println("    - 用户名: " + registeredUser.getUsername());
                System.out.println("    - 性别（应为默认值0）: " + registeredUser.getGender());
                System.out.println("    - 年龄（应为默认值0）: " + registeredUser.getAge());
                System.out.println("    - 状态: " + (registeredUser.getStatus() == 1 ? "启用" : "禁用"));
                passCount++;
            } else {
                System.out.println("  结果: ✗ 注册失败: 返回的用户对象为null");
                failCount++;
            }
        } catch (Exception e) {
            System.out.println("  结果: ✗ 注册失败: " + e.getMessage());
            failCount++;
        }

        // 测试用例11: 注册女性用户
        System.out.println("\n【测试11】注册女性用户");
        System.out.println("  输入: username=testuser11, account=testuser11, password=123456, gender=2, age=30");
        try {
            User user = new User();
            user.setUsername("testuser11");
            user.setAccount("testuser11");
            user.setPassword("123456");
            user.setGender(2);
            user.setAge(30);

            User registeredUser = userService.register(user);
            if (registeredUser != null) {
                System.out.println("  结果: ✓ 注册成功！");
                System.out.println("    - 用户ID: " + registeredUser.getId());
                System.out.println("    - 用户名: " + registeredUser.getUsername());
                System.out.println("    - 性别: " + (registeredUser.getGender() == 2 ? "女" : "错误"));
                System.out.println("    - 年龄: " + registeredUser.getAge());
                passCount++;
            } else {
                System.out.println("  结果: ✗ 注册失败: 返回的用户对象为null");
                failCount++;
            }
        } catch (Exception e) {
            System.out.println("  结果: ✗ 注册失败: " + e.getMessage());
            failCount++;
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
