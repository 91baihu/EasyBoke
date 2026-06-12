package com.easyboke;

import com.easyboke.EasyBokeApplication;
import com.easyboke.entity.po.Category;
import com.easyboke.service.CategoryService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.List;

public class CategoryInitTest {

    public static void main(String[] args) {
        System.out.println("=== 初始化分类数据 ===\n");

        String[] testArgs = new String[]{"--server.port=8081"};
        ConfigurableApplicationContext context = SpringApplication.run(EasyBokeApplication.class, testArgs);

        try {
            CategoryService categoryService = context.getBean(CategoryService.class);
            Thread.sleep(2000);

            System.out.println("\n开始初始化分类数据...\n");

            List<Category> categories = Arrays.asList(
                    createCategory(1, "Java技术", "Java相关技术文章", 1),
                    createCategory(2, "前端开发", "前端开发相关文章", 2),
                    createCategory(3, "数据库", "数据库相关文章", 3),
                    createCategory(4, "系统设计", "系统设计相关文章", 4)
            );

            // ... existing code ...

            int count = 0;
            for (Category category : categories) {
                try {
                    Integer result = categoryService.addBatch(Arrays.asList(category));
                    if (result != null && result > 0) {
                        System.out.println("✓ 添加分类成功: " + category.getCategoryName());
                        count++;
                    } else {
                        System.out.println("✗ 添加分类失败: " + category.getCategoryName());
                    }
                } catch (Exception e) {
                    System.out.println("✗ 添加分类失败 (" + category.getCategoryName() + "): " + e.getMessage());
                }
            }

// ... existing code ...


            System.out.println("\n========================================");
            System.out.println("初始化完成！共添加 " + count + " 个分类");
            System.out.println("========================================\n");

        } catch (Exception e) {
            System.err.println("初始化失败: " + e.getMessage());
            e.printStackTrace();
        } finally {
            context.close();
        }
    }

// ... existing code ...

    private static Category createCategory(Integer id, String name, String description, Integer sortOrder) {
        Category category = new Category();
        category.setId(id);
        category.setCategoryName(name);
        category.setDescription(description);
        category.setSortOrder(sortOrder);
        return category;
    }
}


