package com.easyboke;

import com.easyboke.EasyBokeApplication;
import com.easyboke.entity.po.Article;
import com.easyboke.entity.query.ArticleQuery;
import com.easyboke.entity.vo.PaginationResultVO;
import com.easyboke.service.ArticleService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.List;

public class ArticleTest {

    public static void main(String[] args) {
        System.out.println("=== 启动Spring Boot应用进行文章管理测试 ===\n");
        System.out.println("提示: 使用端口 8081 启动应用\n");

        String[] testArgs = new String[]{"--server.port=8081"};

        ConfigurableApplicationContext context = SpringApplication.run(EasyBokeApplication.class, testArgs);

        try {
            ArticleService articleService = context.getBean(ArticleService.class);

            System.out.println("\n等待应用完全启动...");
            Thread.sleep(2000);

            testArticleManagement(articleService);

        } catch (Exception e) {
            System.err.println("测试执行失败: " + e.getMessage());
            e.printStackTrace();
        } finally {
            context.close();
        }
    }

    private static void testArticleManagement(ArticleService articleService) {
        System.out.println("\n========================================");
        System.out.println("=== 开始测试文章管理功能 ===");
        System.out.println("========================================\n");

        int passCount = 0;
        int failCount = 0;

        // 测试用例1: 发布新文章（不带标签）
        System.out.println("【测试1】发布新文章（不带标签）");
        System.out.println("  输入: title=我的第一篇文章, content=这是文章内容, summary=文章摘要, categoryId=1");
        try {
            Article article = new Article();
            article.setTitle("我的第一篇文章");
            article.setContent("这是文章的详细内容，包含Java编程的相关知识。");
            article.setSummary("文章摘要");
            article.setCoverImage("https://example.com/cover1.jpg");
            article.setCategoryId(1);

            Integer articleId = articleService.publishArticle(article, null);
            if (articleId != null && articleId > 0) {
                System.out.println("  结果: ✓ 发布成功！");
                System.out.println("    - 文章ID: " + articleId);

                Article savedArticle = articleService.getById(articleId);
                if (savedArticle != null) {
                    System.out.println("    - 标题: " + savedArticle.getTitle());
                    System.out.println("    - 状态: " + (savedArticle.getStatus() == 1 ? "已发布" : "错误"));
                    System.out.println("    - 浏览量: " + savedArticle.getViewCount());
                    System.out.println("    - 点赞数: " + savedArticle.getLikeCount());
                    System.out.println("    - 评论数: " + savedArticle.getCommentCount());
                    if (savedArticle.getPublishTime() != null) {
                        System.out.println("    - 发布时间: " + savedArticle.getPublishTime());
                    }
                }
                passCount++;
            } else {
                System.out.println("  结果: ✗ 发布失败: 返回的文章ID无效");
                failCount++;
            }
        } catch (Exception e) {
            System.out.println("  结果: ✗ 发布失败: " + e.getMessage());
            e.printStackTrace();
            failCount++;
        }

        // 测试用例2: 发布新文章（带标签）
        System.out.println("\n【测试2】发布新文章（带标签）");
        System.out.println("  输入: title=Java进阶教程, content=Java高级特性详解, categoryId=1, tagIds=[1,2]");
        try {
            Article article = new Article();
            article.setTitle("Java进阶教程");
            article.setContent("本文详细介绍Java的高级特性，包括泛型、反射、注解等。");
            article.setSummary("Java高级特性详解");
            article.setCoverImage("https://example.com/cover2.jpg");
            article.setCategoryId(1);

            List<Integer> tagIds = Arrays.asList(1, 2);
            Integer articleId = articleService.publishArticle(article, tagIds);
            if (articleId != null && articleId > 0) {
                System.out.println("  结果: ✓ 发布成功！");
                System.out.println("    - 文章ID: " + articleId);
                System.out.println("    - 标题: " + article.getTitle());
                System.out.println("    - 标签数量: 2个");
                passCount++;
            } else {
                System.out.println("  结果: ✗ 发布失败: 返回的文章ID无效");
                failCount++;
            }
        } catch (Exception e) {
            System.out.println("  结果: ✗ 发布失败: " + e.getMessage());
            failCount++;
        }

        // 测试用例3: 查看文章详情（自动增加浏览量）
        System.out.println("\n【测试3】查看文章详情（自动增加浏览量）");
        System.out.println("  输入: id=1");
        try {
            Article article1 = articleService.getArticleDetail(1);
            if (article1 != null) {
                Integer viewCount1 = article1.getViewCount();
                System.out.println("  第一次查看 - 浏览量: " + viewCount1);

                Article article2 = articleService.getArticleDetail(1);
                Integer viewCount2 = article2.getViewCount();
                System.out.println("  第二次查看 - 浏览量: " + viewCount2);

                if (viewCount2 > viewCount1) {
                    System.out.println("  结果: ✓ 浏览量自动增加成功！");
                    System.out.println("    - 增加了: " + (viewCount2 - viewCount1) + " 次");
                    passCount++;
                } else {
                    System.out.println("  结果: ✗ 浏览量未增加");
                    failCount++;
                }
            } else {
                System.out.println("  结果: ✗ 文章不存在");
                failCount++;
            }
        } catch (Exception e) {
            System.out.println("  结果: ✗ 查看详情失败: " + e.getMessage());
            failCount++;
        }

        // 测试用例4: 更新文章
        System.out.println("\n【测试4】更新文章");
        System.out.println("  输入: id=1, title=更新后的标题, content=更新后的内容");
        try {
            Article article = articleService.getById(1);
            if (article != null) {
                String oldTitle = article.getTitle();
                article.setTitle("更新后的标题");
                article.setContent("这是更新后的文章内容");
                article.setSummary("更新后的摘要");

                Integer result = articleService.updateArticle(article, Arrays.asList(3, 4));
                if (result != null && result > 0) {
                    Article updatedArticle = articleService.getById(1);
                    System.out.println("  结果: ✓ 更新成功！");
                    System.out.println("    - 原标题: " + oldTitle);
                    System.out.println("    - 新标题: " + updatedArticle.getTitle());
                    System.out.println("    - 更新时间: " + updatedArticle.getUpdateTime());
                    passCount++;
                } else {
                    System.out.println("  结果: ✗ 更新失败");
                    failCount++;
                }
            } else {
                System.out.println("  结果: ✗ 文章不存在");
                failCount++;
            }
        } catch (Exception e) {
            System.out.println("  结果: ✗ 更新失败: " + e.getMessage());
            failCount++;
        }

        // 测试用例5: 搜索文章
        System.out.println("\n【测试5】搜索文章");
        System.out.println("  输入: keyword=Java");
        try {
            List<Article> articles = articleService.searchArticles("Java");
            if (articles != null && !articles.isEmpty()) {
                System.out.println("  结果: ✓ 搜索成功！");
                System.out.println("    - 找到 " + articles.size() + " 篇文章");
                for (int i = 0; i < Math.min(3, articles.size()); i++) {
                    System.out.println("    - [" + (i+1) + "] " + articles.get(i).getTitle());
                }
                passCount++;
            } else {
                System.out.println("  结果: ✗ 未找到相关文章");
                failCount++;
            }
        } catch (Exception e) {
            System.out.println("  结果: ✗ 搜索失败: " + e.getMessage());
            failCount++;
        }

        // 测试用例6: 获取最新文章列表
        System.out.println("\n【测试6】获取最新文章列表");
        System.out.println("  输入: limit=5");
        try {
            List<Article> articles = articleService.getLatestArticles(5, 1);
            if (articles != null && !articles.isEmpty()) {
                System.out.println("  结果: ✓ 获取成功！");
                System.out.println("    - 共 " + articles.size() + " 篇文章");
                for (int i = 0; i < articles.size(); i++) {
                    System.out.println("    - [" + (i+1) + "] " + articles.get(i).getTitle()
                            + " (发布时间: " + articles.get(i).getPublishTime() + ")");
                }
                passCount++;
            } else {
                System.out.println("  结果: ✗ 未获取到文章");
                failCount++;
            }
        } catch (Exception e) {
            System.out.println("  结果: ✗ 获取失败: " + e.getMessage());
            failCount++;
        }

        // 测试用例7: 获取热门文章列表
        System.out.println("\n【测试7】获取热门文章列表");
        System.out.println("  输入: limit=5");
        try {
            List<Article> articles = articleService.getHotArticles(5, 1);
            if (articles != null && !articles.isEmpty()) {
                System.out.println("  结果: ✓ 获取成功！");
                System.out.println("    - 共 " + articles.size() + " 篇文章");
                for (int i = 0; i < articles.size(); i++) {
                    System.out.println("    - [" + (i+1) + "] " + articles.get(i).getTitle()
                            + " (浏览量: " + articles.get(i).getViewCount() + ")");
                }
                passCount++;
            } else {
                System.out.println("  结果: ✗ 未获取到文章");
                failCount++;
            }
        } catch (Exception e) {
            System.out.println("  结果: ✗ 获取失败: " + e.getMessage());
            failCount++;
        }

        // 测试用例8: 根据分类查询文章
        System.out.println("\n【测试8】根据分类查询文章");
        System.out.println("  输入: categoryId=1, status=1");
        try {
            List<Article> articles = articleService.getArticlesByCategoryId(1, 1);
            if (articles != null) {
                System.out.println("  结果: ✓ 查询成功！");
                System.out.println("    - 分类ID 1 下共有 " + articles.size() + " 篇文章");
                for (int i = 0; i < Math.min(3, articles.size()); i++) {
                    System.out.println("    - [" + (i+1) + "] " + articles.get(i).getTitle());
                }
                passCount++;
            } else {
                System.out.println("  结果: ✗ 查询失败");
                failCount++;
            }
        } catch (Exception e) {
            System.out.println("  结果: ✗ 查询失败: " + e.getMessage());
            failCount++;
        }

        // 测试用例9: 分页查询文章列表
        System.out.println("\n【测试9】分页查询文章列表");
        System.out.println("  输入: pageNo=1, pageSize=3, status=1");
        try {
            ArticleQuery query = new ArticleQuery();
            query.setPageNo(1);
            query.setPageSize(3);
            query.setStatus(1);
            query.setOrderBy("publish_time desc");

            PaginationResultVO<Article> result = articleService.findListByPage(query);
            if (result != null) {
                System.out.println("  结果: ✓ 查询成功！");
                System.out.println("    - 总记录数: " + result.getTotalCount());
                System.out.println("    - 当前页: " + result.getPageNo());
                System.out.println("    - 每页大小: " + result.getPageSize());
                System.out.println("    - 总页数: " + result.getPageTotal());
                System.out.println("    - 当前页数据: " + result.getList().size() + " 条");

                List<Article> articles = result.getList();
                for (int i = 0; i < articles.size(); i++) {
                    System.out.println("    - [" + (i+1) + "] " + articles.get(i).getTitle()
                            + " (浏览量: " + articles.get(i).getViewCount() + ")");
                }
                passCount++;
            } else {
                System.out.println("  结果: ✗ 查询失败");
                failCount++;
            }
        } catch (Exception e) {
            System.out.println("  结果: ✗ 查询失败: " + e.getMessage());
            failCount++;
        }

        // 测试用例10: 删除文章（软删除）
        System.out.println("\n【测试10】删除文章（软删除）");
        System.out.println("  输入: id=1");
        try {
            Article articleBefore = articleService.getById(1);
            if (articleBefore != null) {
                System.out.println("  删除前状态: " + (articleBefore.getStatus() == 1 ? "已发布" : "其他"));

                Integer result = articleService.deleteById(1);
                if (result != null && result > 0) {
                    Article articleAfter = articleService.getById(1);
                    System.out.println("  删除后状态: " + (articleAfter.getStatus() == 2 ? "已删除" : "其他"));

                    if (articleAfter.getStatus() == 2) {
                        System.out.println("  结果: ✓ 软删除成功！");
                        passCount++;
                    } else {
                        System.out.println("  结果: ✗ 状态未正确更新");
                        failCount++;
                    }
                } else {
                    System.out.println("  结果: ✗ 删除失败");
                    failCount++;
                }
            } else {
                System.out.println("  结果: ✗ 文章不存在");
                failCount++;
            }
        } catch (Exception e) {
            System.out.println("  结果: ✗ 删除失败: " + e.getMessage());
            failCount++;
        }

        // 测试用例11: 批量新增文章
        System.out.println("\n【测试11】批量新增文章");
        System.out.println("  输入: 3篇文章");
        try {
            List<Article> articles = Arrays.asList(
                    createArticle("批量文章1", "内容1", 1),
                    createArticle("批量文章2", "内容2", 1),
                    createArticle("批量文章3", "内容3", 2)
            );

            Integer result = articleService.addBatch(articles);
            if (result != null && result > 0) {
                System.out.println("  结果: ✓ 批量新增成功！");
                System.out.println("    - 成功插入 " + result + " 篇文章");
                passCount++;
            } else {
                System.out.println("  结果: ✗ 批量新增失败");
                failCount++;
            }
        } catch (Exception e) {
            System.out.println("  结果: ✗ 批量新增失败: " + e.getMessage());
            failCount++;
        }

        // 测试用例12: 模糊搜索文章标题
        System.out.println("\n【测试12】模糊搜索文章标题");
        System.out.println("  输入: titleFuzzy=文章");
        try {
            ArticleQuery query = new ArticleQuery();
            query.setTitleFuzzy("文章");
            query.setStatus(1);

            List<Article> articles = articleService.findListByParam(query);
            if (articles != null) {
                System.out.println("  结果: ✓ 搜索成功！");
                System.out.println("    - 找到 " + articles.size() + " 篇标题包含'文章'的文章");
                for (int i = 0; i < Math.min(3, articles.size()); i++) {
                    System.out.println("    - [" + (i+1) + "] " + articles.get(i).getTitle());
                }
                passCount++;
            } else {
                System.out.println("  结果: ✗ 搜索失败");
                failCount++;
            }
        } catch (Exception e) {
            System.out.println("  结果: ✗ 搜索失败: " + e.getMessage());
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

    private static Article createArticle(String title, String content, Integer categoryId) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setSummary("摘要-" + title);
        article.setCoverImage("https://example.com/cover.jpg");
        article.setCategoryId(categoryId);
        article.setStatus(0);
        article.setViewCount(0);
        article.setMaleViewCount(0);
        article.setFemaleViewCount(0);
        article.setUnknownViewCount(0);
        article.setAgeUnder18(0);
        article.setAge1825(0);
        article.setAge2635(0);
        article.setAge3645(0);
        article.setAgeAbove45(0);
        article.setLikeCount(0);
        article.setCommentCount(0);
        return article;
    }
}
