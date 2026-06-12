package com.easyboke;

import com.easyboke.EasyBokeApplication;
import com.easyboke.entity.po.Article;
import com.easyboke.entity.po.User;
import com.easyboke.service.ArticleService;
import com.easyboke.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class ArticleStatisticsTest {

    public static void main(String[] args) {
        System.out.println("=== 测试文章访问统计功能 ===\n");

        String[] testArgs = new String[]{"--server.port=8081"};
        ConfigurableApplicationContext context = SpringApplication.run(EasyBokeApplication.class, testArgs);

        try {
            ArticleService articleService = context.getBean(ArticleService.class);
            UserService userService = context.getBean(UserService.class);
            Thread.sleep(2000);

            System.out.println("\n========================================");
            System.out.println("步骤1: 创建测试文章");
            System.out.println("========================================\n");

            Article testArticle = new Article();
            testArticle.setTitle("测试文章 - 访问统计功能");
            testArticle.setSummary("这是一篇用于测试访问统计功能的文章");
            testArticle.setContent("测试内容...");
            testArticle.setStatus(1);
            testArticle.setViewCount(0);
            testArticle.setMaleViewCount(0);
            testArticle.setFemaleViewCount(0);
            testArticle.setUnknownViewCount(0);
            testArticle.setAgeUnder18(0);
            testArticle.setAge1825(0);
            testArticle.setAge2635(0);
            testArticle.setAge3645(0);
            testArticle.setAgeAbove45(0);

            Integer articleId = articleService.publishArticle(testArticle, null);
            System.out.println("✓ 测试文章创建成功，ID: " + articleId);

            System.out.println("\n========================================");
            System.out.println("步骤2: 模拟不同用户访问文章");
            System.out.println("========================================\n");

            System.out.println("--- 测试1: 男性用户（20岁）访问 ---");
            Article article1 = articleService.getArticleDetail(articleId, 1, 1, 20);
            printArticleStats(article1);

            System.out.println("\n--- 测试2: 女性用户（30岁）访问 ---");
            Article article2 = articleService.getArticleDetail(articleId, 2, 2, 30);
            printArticleStats(article2);

            System.out.println("\n--- 测试3: 未知性别用户（15岁）访问 ---");
            Article article3 = articleService.getArticleDetail(articleId, 3, 0, 15);
            printArticleStats(article3);

            System.out.println("\n--- 测试4: 男性用户（40岁）访问 ---");
            Article article4 = articleService.getArticleDetail(articleId, 4, 1, 40);
            printArticleStats(article4);

            System.out.println("\n--- 测试5: 女性用户（50岁）访问 ---");
            Article article5 = articleService.getArticleDetail(articleId, 5, 2, 50);
            printArticleStats(article5);

            System.out.println("\n--- 测试6: 未登录用户访问（不传参数）---");
            Article article6 = articleService.getArticleDetail(articleId);
            printArticleStats(article6);

            System.out.println("\n========================================");
            System.out.println("步骤3: 验证统计结果");
            System.out.println("========================================\n");

            Article finalArticle = articleService.getById(articleId);

            boolean success = true;

            System.out.println("预期统计结果：");
            System.out.println("  - 总浏览量: 6");
            System.out.println("  - 男性访问量: 2");
            System.out.println("  - 女性访问量: 2");
            System.out.println("  - 未知性别: 2");
            System.out.println("  - 18岁以下: 1");
            System.out.println("  - 18-25岁: 1");
            System.out.println("  - 26-35岁: 1");
            System.out.println("  - 36-45岁: 1");
            System.out.println("  - 46岁以上: 1");

            System.out.println("\n实际统计结果：");
            System.out.println("  - 总浏览量: " + finalArticle.getViewCount());
            System.out.println("  - 男性访问量: " + finalArticle.getMaleViewCount());
            System.out.println("  - 女性访问量: " + finalArticle.getFemaleViewCount());
            System.out.println("  - 未知性别: " + finalArticle.getUnknownViewCount());
            System.out.println("  - 18岁以下: " + finalArticle.getAgeUnder18());
            System.out.println("  - 18-25岁: " + finalArticle.getAge1825());
            System.out.println("  - 26-35岁: " + finalArticle.getAge2635());
            System.out.println("  - 36-45岁: " + finalArticle.getAge3645());
            System.out.println("  - 46岁以上: " + finalArticle.getAgeAbove45());

            if (finalArticle.getViewCount() != 6) {
                System.out.println("\n✗ 总浏览量错误！");
                success = false;
            }
            if (finalArticle.getMaleViewCount() != 2) {
                System.out.println("✗ 男性访问量错误！");
                success = false;
            }
            if (finalArticle.getFemaleViewCount() != 2) {
                System.out.println("✗ 女性访问量错误！");
                success = false;
            }
            if (finalArticle.getUnknownViewCount() != 2) {
                System.out.println("✗ 未知性别访问量错误！");
                success = false;
            }
            if (finalArticle.getAgeUnder18() != 1) {
                System.out.println("✗ 18岁以下访问量错误！");
                success = false;
            }
            if (finalArticle.getAge1825() != 1) {
                System.out.println("✗ 18-25岁访问量错误！");
                success = false;
            }
            if (finalArticle.getAge2635() != 1) {
                System.out.println("✗ 26-35岁访问量错误！");
                success = false;
            }
            if (finalArticle.getAge3645() != 1) {
                System.out.println("✗ 36-45岁访问量错误！");
                success = false;
            }
            if (finalArticle.getAgeAbove45() != 1) {
                System.out.println("✗ 46岁以上访问量错误！");
                success = false;
            }

            System.out.println("\n========================================");
            if (success) {
                System.out.println("✓✓✓ 所有测试通过！访问统计功能正常 ✓✓✓");
            } else {
                System.out.println("✗✗✗ 部分测试失败，请检查代码 ✗✗✗");
            }
            System.out.println("========================================\n");

        } catch (Exception e) {
            System.err.println("测试失败: " + e.getMessage());
            e.printStackTrace();
        } finally {
            context.close();
        }
    }

    private static void printArticleStats(Article article) {
        System.out.println("  当前统计：");
        System.out.println("    - 总浏览: " + article.getViewCount());
        System.out.println("    - 男性: " + article.getMaleViewCount());
        System.out.println("    - 女性: " + article.getFemaleViewCount());
        System.out.println("    - 未知: " + article.getUnknownViewCount());
        System.out.println("    - <18岁: " + article.getAgeUnder18());
        System.out.println("    - 18-25岁: " + article.getAge1825());
        System.out.println("    - 26-35岁: " + article.getAge2635());
        System.out.println("    - 36-45岁: " + article.getAge3645());
        System.out.println("    - >46岁: " + article.getAgeAbove45());
    }
}
