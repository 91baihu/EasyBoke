# EasyBoke — 个人博客系统

基于 Spring Boot + MyBatis + MySQL 的个人博客系统，支持文章/视频发布、分类标签、评论互动、视频播放、访问统计和忘记密码等功能。

## 技术栈

| 层面 | 技术 |
|------|------|
| 后端框架 | Spring Boot 2.7.18 |
| ORM | MyBatis + MyBatis-Spring-Boot-Starter 2.3.1 |
| 数据库 | MySQL 8.0 |
| 密码加密 | BCrypt (spring-security-crypto) |
| JSON | Fastjson 2.0.40 |
| 邮件 | Spring Boot Mail (QQ SMTP) |
| 图表 | Chart.js (CDN) |
| 前端 | 原生 HTML + CSS + JavaScript（QQ 风格 UI） |
| Java | JDK 1.8 |
| 构建 | Maven |

## 功能模块

### 前台（读者/游客）

| 功能 | 说明 |
|------|------|
| 文章浏览 | 首页列表、分类筛选、标签筛选、关键词搜索、热度排行 |
| 文章详情 | 自动统计阅读量、性别/年龄分布 |
| 用户注册/登录 | Session 认证，BCrypt 密码加密 |
| 评论互动 | 发表评论、嵌套回复（楼中楼） |
| 文章点赞 | 点赞/取消点赞 |
| 视频列表 | 视频卡片展示，播放量/点赞/时长信息 |
| 视频播放 | 支持 B站 iframe 嵌入、直链视频、通用 iframe |
| 视频评论 | 评论列表 + 嵌套回复 |
| 视频点赞 | DB 持久化点赞（重启不丢失） |
| 分类浏览 | 按分类浏览文章 |
| 标签浏览 | 标签云 + 按标签筛选文章 |
| 忘记密码 | 通过账号发送重置邮件 |
| 个人中心 | 查看/修改个人信息、修改密码 |

### 后台（博主/管理员）

| 功能 | 说明 |
|------|------|
| 控制台 | 快捷导航入口 |
| 文章管理 | 发布、编辑、删除、Markdown/富文本编辑 |
| 视频管理 | 发布、编辑、删除、封面上传、B站链接时长自动获取 |
| 分类管理 | 增删改查、排序 |
| 标签管理 | 增删改查 |
| 评论管理 | 审核、删除 |
| 用户管理 | 查看、编辑、禁用 |
| 访问统计 | 文章/视频详情统计（性别/年龄分布图表） |
| 管理员资料 | 查看/修改管理员信息 |

## 项目结构

```
easy-boke
├── pom.xml
├── easyboke.sql                          # 数据库完整脚本（含建表+初始数据）
├── migration.sql                         # 增量迁移脚本（仅新增表）
├── bind_email.sql                        # 批量绑定邮箱脚本
├── README.md
└── src/main
    ├── java/com/easyboke
    │   ├── EasyBokeApplication.java      # 应用入口
    │   ├── common/Constants.java         # 常量与会话管理
    │   ├── config/
    │   │   ├── WebConfig.java            # 拦截器注册
    │   │   ├── LoginInterceptor.java     # 管理员登录拦截
    │   │   └── UserLoginInterceptor.java # 用户登录拦截
    │   ├── controller/
    │   │   ├── AdminController.java      # 管理员接口
    │   │   ├── UserController.java       # 用户接口（注册/登录/忘记密码/个人中心）
    │   │   ├── ArticleController.java    # 文章接口
    │   │   ├── CategoryController.java   # 分类接口
    │   │   ├── TagController.java        # 标签接口
    │   │   ├── CommentController.java    # 评论接口（发表/回复/审核）
    │   │   ├── VideoController.java      # 视频接口（CRUD/点赞/访问记录/时长获取/统计）
    │   │   ├── ArticleTagController.java # 文章标签关联接口
    │   │   ├── VisitLogController.java   # 访问日志接口
    │   │   ├── VisitorStatisticsController.java
    │   │   ├── ABaseController.java      # 基础控制器（统一响应）
    │   │   └── AGlobalExceptionHandlerController.java
    │   ├── dto/                          # 数据传输对象
    │   ├── entity/
    │   │   ├── po/                       # 持久化对象
    │   │   │   ├── Admin.java
    │   │   │   ├── User.java
    │   │   │   ├── Article.java
    │   │   │   ├── Category.java
    │   │   │   ├── Tag.java
    │   │   │   ├── ArticleTag.java
    │   │   │   ├── Video.java
    │   │   │   ├── VideoTag.java         # 视频标签
    │   │   │   ├── VideoTagRelation.java # 视频-标签关联
    │   │   │   ├── VideoLike.java        # 视频点赞记录
    │   │   │   ├── VideoAccessLog.java   # 视频访问日志
    │   │   │   ├── Comment.java
    │   │   │   ├── VisitLog.java
    │   │   │   └── VisitorStatistics.java
    │   │   ├── query/                    # 查询条件对象
    │   │   ├── vo/                       # 响应对象
    │   │   └── enums/                    # 枚举
    │   ├── exception/                    # 自定义异常
    │   ├── mappers/                      # MyBatis Mapper 接口
    │   │   ├── AdminMapper.java
    │   │   ├── UserMapper.java
    │   │   ├── ArticleMapper.java
    │   │   ├── CategoryMapper.java
    │   │   ├── TagMapper.java
    │   │   ├── ArticleTagMapper.java
    │   │   ├── VideoMapper.java
    │   │   ├── VideoTagMapper.java
    │   │   ├── VideoTagRelationMapper.java
    │   │   ├── VideoLikeMapper.java
    │   │   ├── VideoAccessLogMapper.java
    │   │   ├── CommentMapper.java
    │   │   ├── VisitLogMapper.java
    │   │   ├── VisitorStatisticsMapper.java
    │   │   └── BaseMapper.java
    │   ├── service/                      # 服务接口
    │   │   └── impl/                     # 服务实现
    │   └── utils/                        # 工具类
    │       ├── DateUtils.java
    │       ├── MailUtil.java             # 邮件发送
    │       └── PasswordResetTokenManager.java  # 密码重置令牌
    └── resources
        ├── application.yml               # 应用配置
        ├── mapper/                       # MyBatis XML 映射文件
        └── static/                       # 前端静态页面（QQ 风格 UI）
            ├── index.html                # 首页（文章列表+热度排行）
            ├── article-detail.html       # 文章详情（评论区+嵌套回复）
            ├── category.html             # 分类浏览
            ├── tag.html                  # 标签云+标签文章
            ├── video-list.html           # 视频列表（卡片+播放覆盖层）
            ├── video-play.html           # 视频播放（评论区+嵌套回复+点赞+分享）
            ├── login.html                # 读者登录
            ├── register.html             # 读者注册
            ├── forgot-password.html      # 忘记密码（账号查找）
            ├── reset-password.html       # 重置密码
            ├── profile.html              # 个人中心
            ├── admin-login.html          # 管理员登录
            ├── admin-dashboard.html      # 管理后台首页
            ├── admin-profile.html        # 管理员资料
            ├── article-management.html   # 文章管理
            ├── category-management.html  # 分类管理
            ├── tag-management.html       # 标签管理
            ├── comment-management.html   # 评论管理
            ├── user-management.html      # 用户管理
            ├── video-management.html     # 视频管理（时长自动获取）
            └── statistics.html           # 访问统计（文章/视频详情）
```

## 快速开始

### 1. 环境要求

- JDK 1.8+
- MySQL 8.0+
- Maven 3.6+

### 2. 创建数据库

在 MySQL 中创建数据库并导入完整脚本：

```sql
CREATE DATABASE easyboke DEFAULT CHARACTER SET utf8mb4;
USE easyboke;
SOURCE easyboke.sql;
```

> `easyboke.sql` 已包含所有建表语句和初始数据，可直接导入使用。

### 3. 配置邮箱

编辑 `src/main/resources/application.yml`：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/easyboke?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai
    username: root
    password: 你的数据库密码

  mail:
    host: smtp.qq.com
    port: 587
    username: 你的QQ邮箱@qq.com
    password: 你的QQ邮箱SMTP授权码
```

### 4. 启动项目

```bash
# 方式一：Maven 插件
mvn spring-boot:run

# 方式二：打包运行
mvn clean package -DskipTests
java -jar target/easy-boke.jar
```

### 5. 访问

| 入口 | 地址 |
|------|------|
| 前台首页 | http://localhost:8080/index.html |
| 读者登录 | http://localhost:8080/login.html |
| 读者注册 | http://localhost:8080/register.html |
| 忘记密码 | http://localhost:8080/forgot-password.html |
| 后台登录 | http://localhost:8080/admin-login.html |

### 6. 初始账号

| 角色 | 账号 | 密码 |
|------|------|------|
| 管理员 | admin | 123456 |
| 测试用户 | liutao / dengjian / hehongjie / zengwanrong / niebaixin / zhouyan | 123456 |

> 所有账号邮箱已统一绑定，忘记密码功能可通过**账号**找回。

## QQ 风格 UI 设计规范

| 用途 | 色值 | 说明 |
|------|------|------|
| 主色蓝 | `#12B7F5` | 按钮、链接、选中态 |
| 强调蓝 | `#0099FF` | 悬停态、渐变辅助 |
| 标题文字 | `#1A1A2E` | 页面标题、卡片标题 |
| 正文 | `#333333` | 表格内容、主要文字 |
| 辅助文字 | `#555555` | 描述、表单标签 |
| 轻量文字 | `#888888` | 时间、元信息、占位 |
| 卡片背景 | `#FFFFFF` | 卡片、面板 |
| 页面背景 | `#F5F6F7` | 全局背景 |
| 边框 | `#E5E7EB` / `#EBEDF0` | 输入框、分割线 |
| 成功绿 | `#67C23A` | 成功状态 |
| 危险红 | `#F56C6C` | 删除、错误 |
| 警告橙 | `#FF9500` | 警告状态 |

**设计原则**：白色文字仅出现在深色/彩色背景上（按钮、渐变导航栏、头像），浅色背景使用深色文字确保对比度。

## API 接口概览

### 用户相关 `/user`

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | `/user/login` | 用户登录 |
| POST | `/user/register` | 用户注册 |
| POST | `/user/logout` | 退出登录 |
| GET | `/user/currentUser` | 获取当前登录用户 |
| POST | `/user/forgotPassword?account=` | 发送重置邮件（通过账号） |
| POST | `/user/resetPassword` | 重置密码 |
| POST | `/user/changePassword` | 修改密码 |
| POST | `/user/updateProfile` | 更新个人资料 |

### 视频相关 `/video`

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/video/loadDataList` | 分页查询视频列表 |
| GET | `/video/getVideoById?id=` | 查询视频详情 |
| POST | `/video/addVideo` | 新增视频 |
| POST | `/video/updateVideo` | 更新视频 |
| POST | `/video/deleteVideo?id=` | 删除视频 |
| POST | `/video/uploadCover` | 上传封面图片 |
| POST | `/video/like` | 点赞视频（DB持久化） |
| POST | `/video/unlike` | 取消点赞 |
| GET | `/video/isLiked?videoId=` | 检查点赞状态 |
| POST | `/video/view/{videoId}` | 记录访问（IP防刷：1小时/IP/视频仅计1次） |
| GET | `/video/fetchDuration?videoUrl=` | 自动获取视频时长（支持B站） |
| GET | `/video/statsSummary` | 视频访问统计摘要 |
| GET | `/video/dailyViews?videoId=` | 日访问量统计 |
| GET | `/video/totalViewsRank` | 视频访问排行 |

### 评论相关 `/comment`

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | `/comment/publish` | 发表评论 |
| POST | `/comment/reply` | 回复评论（二级评论） |
| GET | `/comment/listByArticle?articleId=` | 文章评论列表 |
| GET | `/comment/listByVideo?videoId=` | 视频评论列表 |
| POST | `/comment/audit` | 审核评论（管理员） |

## 数据库表

| 表名 | 说明 |
|------|------|
| `admin` | 管理员（博主） |
| `user` | 注册用户 |
| `article` | 文章（含性别/年龄访问统计字段） |
| `category` | 文章分类 |
| `tag` | 文章标签 |
| `article_tag` | 文章-标签关联 |
| `video` | 视频（含点赞/播放/时长/性别/年龄字段） |
| `video_tag` | 视频标签 |
| `video_tag_relation` | 视频-标签多对多关联 |
| `video_like` | 视频点赞记录（持久化，防止重启丢失） |
| `video_access_log` | 视频访问日志（IP防刷统计） |
| `comment` | 评论（支持嵌套回复，parent_id + reply_comment_id） |
| `visitor_statistics` | 每日访客统计 |
| `visit_log` | 通用访问日志 |

## 权限说明

| 路径 | 权限 |
|------|------|
| `/admin/**` | 需管理员登录（LoginInterceptor） |
| `/user/profile/**`、`/user/changePassword`、`/user/updateProfile` | 需用户登录 |
| `/comment/publish`、`/comment/reply` | 需用户登录 |
| `/video/like`、`/video/unlike` | 需用户登录 |
| 其余接口和页面 | 公开访问 |

## 核心特性说明

### 视频点赞持久化

点赞记录存储在 `video_like` 表中（`video_id` + `user_id` 唯一约束），应用重启后数据不丢失。

### 浏览量防刷

同一 IP 对同一视频在 1 小时内只计 1 次访问，记录写入 `video_access_log` 表。

### 视频时长自动获取

后端调用 [B站 API](https://api.bilibili.com/x/web-interface/view) 根据 BV 号自动获取视频时长。管理后台粘贴链接后点击按钮即可自动填充，无需手动输入。

### 评论嵌套回复

支持二级评论（楼中楼）：一级评论 `parent_id = 0`，回复评论 `parent_id` 指向父评论 ID，`reply_comment_id` 指向被回复的评论 ID。

### 忘记密码

通过**账号**查找用户 → 向绑定邮箱发送重置链接 → 链接 24 小时内有效 → 设置新密码。
