package com.easyboke.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.easyboke.service.ArticleService;
import com.easyboke.entity.po.Article;
import com.easyboke.entity.query.ArticleQuery;
import com.easyboke.entity.vo.ResponseVO;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController extends ABaseController{

	@Resource
	private ArticleService articleService;

	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(ArticleQuery query) {
		return getSuccessResponseVo(articleService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVO add(Article bean){
		return getSuccessResponseVo(articleService.add(bean));
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<Article> listBean){
		return getSuccessResponseVo(articleService.addBatch(listBean));
	}

	/**
	 * 新增或者修改
	 */
	@RequestMapping("addOrUpdate")
	public ResponseVO addOrUpdate(Article bean){
		return getSuccessResponseVo(articleService.addOrUpdate(bean));
	}

	/**
	 * 批量新增或修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdate(@RequestBody List<Article> listBean){
		return getSuccessResponseVo(articleService.addOrUpdateBatch(listBean));
	}

	/**
	 * 根据Id查询
	 */
	@RequestMapping("getArticleById")
	public ResponseVO getArticleById(Integer id){
		return getSuccessResponseVo(this.articleService.getById(id));
	}

	/**
	 * 根据Id更新
	 */
	@RequestMapping("updateArticleById")
	public ResponseVO updateArticleById(Article bean,Integer id){
		return getSuccessResponseVo(this.articleService.updateById(bean,id));
	}

	/**
	 * 根据Id删除
	 */
	@RequestMapping("deleteArticleById")
	public ResponseVO deleteArticleById(Integer id){
		return getSuccessResponseVo(this.articleService.deleteById(id));
	}

	/**
	 * 发布文章
	 */
	@RequestMapping("publish")
	public ResponseVO publishArticle(Article article, @RequestParam(required = false) List<Integer> tagIds) {
		return getSuccessResponseVo(articleService.publishArticle(article, tagIds));
	}

	/**
	 * 更新文章
	 */
	@RequestMapping("update")
	public ResponseVO updateArticle(Article article, @RequestParam(required = false) List<Integer> tagIds) {
		return getSuccessResponseVo(articleService.updateArticle(article, tagIds));
	}

	/**
	 * 搜索文章
	 */
	@RequestMapping("search")
	public ResponseVO searchArticles(String keyword) {
		return getSuccessResponseVo(articleService.searchArticles(keyword));
	}

	/**
	 * 根据分类ID查询文章
	 */
	@RequestMapping("listByCategory")
	public ResponseVO getArticlesByCategory(Integer categoryId, @RequestParam(defaultValue = "1") Integer status) {
		return getSuccessResponseVo(articleService.getArticlesByCategoryId(categoryId, status));
	}

	/**
	 * 根据标签ID查询文章
	 */
	@RequestMapping("listByTag")
	public ResponseVO getArticlesByTag(Integer tagId, @RequestParam(defaultValue = "1") Integer status) {
		return getSuccessResponseVo(articleService.getArticlesByTagId(tagId, status));
	}

	/**
	 * 获取最新文章
	 */
	@RequestMapping("latest")
	public ResponseVO getLatestArticles(@RequestParam(defaultValue = "10") Integer limit,
										@RequestParam(defaultValue = "1") Integer status) {
		return getSuccessResponseVo(articleService.getLatestArticles(limit, status));
	}

	/**
	 * 获取热门文章
	 */
	@RequestMapping("hot")
	public ResponseVO getHotArticles(@RequestParam(defaultValue = "10") Integer limit,
									 @RequestParam(defaultValue = "1") Integer status) {
		return getSuccessResponseVo(articleService.getHotArticles(limit, status));
	}


	/**
	 * 查看文章详情（自动增加浏览量，并记录性别年龄统计）
	 */
	@RequestMapping("detail")
	public ResponseVO getArticleDetail(Integer id, javax.servlet.http.HttpSession session) {
		com.easyboke.entity.po.User user = (com.easyboke.entity.po.User) session.getAttribute(com.easyboke.common.Constants.SESSION_KEY_USER);

		Integer userId = null;
		Integer gender = 0;
		Integer age = 0;

		if (user != null) {
			userId = user.getId();
			gender = user.getGender() != null ? user.getGender() : 0;
			age = user.getAge() != null ? user.getAge() : 0;
		}

		return getSuccessResponseVo(articleService.getArticleDetail(id, userId, gender, age));
	}
	@RequestMapping("like")
	public ResponseVO likeArticle(Integer id, javax.servlet.http.HttpSession session) {
		try {
			com.easyboke.entity.po.User user = (com.easyboke.entity.po.User) session.getAttribute(com.easyboke.common.Constants.SESSION_KEY_USER);
			if (user == null) {
				return getErrorResponseVo("请先登录");
			}

			articleService.likeArticle(id, user.getId());
			return getSuccessResponseVo("点赞成功");
		} catch (Exception e) {
			return getErrorResponseVo(e.getMessage());
		}
	}

	@RequestMapping("unlike")
	public ResponseVO unlikeArticle(Integer id, javax.servlet.http.HttpSession session) {
		try {
			com.easyboke.entity.po.User user = (com.easyboke.entity.po.User) session.getAttribute(com.easyboke.common.Constants.SESSION_KEY_USER);
			if (user == null) {
				return getErrorResponseVo("请先登录");
			}

			articleService.unlikeArticle(id, user.getId());
			return getSuccessResponseVo("取消点赞成功");
		} catch (Exception e) {
			return getErrorResponseVo(e.getMessage());
		}
	}

	@RequestMapping("isLiked")
	public ResponseVO isLiked(Integer id, javax.servlet.http.HttpSession session) {
		com.easyboke.entity.po.User user = (com.easyboke.entity.po.User) session.getAttribute(com.easyboke.common.Constants.SESSION_KEY_USER);
		if (user == null) {
			return getSuccessResponseVo(false);
		}

		Boolean liked = articleService.isLiked(id, user.getId());
		return getSuccessResponseVo(liked);
	}

	/**
	 * 获取TOP热度排行文章（支持日/周/月/总）
	 */
	@RequestMapping("top")
	public ResponseVO getTopArticles(@RequestParam(defaultValue = "all") String period,
									 @RequestParam(defaultValue = "10") Integer limit) {
		return getSuccessResponseVo(articleService.getTopByHeat(period, limit));
	}

}
