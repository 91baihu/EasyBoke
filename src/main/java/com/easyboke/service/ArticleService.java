package com.easyboke.service;

import com.easyboke.entity.query.ArticleQuery;
import com.easyboke.entity.po.Article;
import com.easyboke.entity.vo.PaginationResultVO;
import java.util.List;
/**
 * @author 高98
 * @Description: 文章表对应的Service
 * @date: 2026/05/10
 */

public interface ArticleService{

	/**
	 * 根据条件查询列表
	 */
	List<Article>findListByParam(ArticleQuery query);

	/**
	 * 根据条件查询数量
	 */
    Integer findCountByParam(ArticleQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<Article> findListByPage(ArticleQuery query );

	/**
	 * 新增
	 */
	Integer add(Article bean);
	/**
	 * 批量新增
	 */
	Integer addBatch(List<Article> listBean);
	/**
	 * 新增或修改
	 */
	Integer addOrUpdate(Article bean);
	/**
	 * 批量新增或修改
	 */
	Integer addOrUpdateBatch(List<Article> listBean);
	/**
	 * 根据Id查询
	 */
	 Article getById(Integer id);

	/**
	 * 根据Id查询
	 */
	 Integer updateById(Article bean , Integer id);

	/**
	 * 根据Id删除
	 */
	 Integer deleteById(Integer id);


	/**
	 * 发布文章
	 */
	Integer publishArticle(Article bean, List<Integer> tagIds);

	/**
	 * 更新文章
	 */
	Integer updateArticle(Article bean, List<Integer> tagIds);

	/**
	 * 查看文章详情（自动增加浏览量）
	 */
	Article getArticleDetail(Integer id);

	/**
	 * 查看文章详情（自动增加浏览量，并记录性别年龄统计）
	 */
	Article getArticleDetail(Integer id, Integer userId, Integer gender, Integer age);

	/**
	 * 搜索文章
	 */
	List<Article> searchArticles(String keyword);

	/**
	 * 根据分类ID查询文章列表
	 */
	List<Article> getArticlesByCategoryId(Integer categoryId, Integer status);

	/**
	 * 根据标签ID查询文章列表
	 */
	List<Article> getArticlesByTagId(Integer tagId, Integer status);

	/**
	 * 获取最新文章列表
	 */
	List<Article> getLatestArticles(Integer limit, Integer status);

	/**
	 * 获取热门文章列表（按浏览量排序）
	 */
	List<Article> getHotArticles(Integer limit, Integer status);

	// ... existing code ...

	Boolean likeArticle(Integer articleId, Integer userId) throws com.easyboke.exception.BusinessException;

	Boolean unlikeArticle(Integer articleId, Integer userId) throws com.easyboke.exception.BusinessException;

	Boolean isLiked(Integer articleId, Integer userId);

	/**
	 * 获取TOP热度排行文章
	 * @param period 时间段：day/week/month/all
	 * @param limit 返回数量
	 */
	List<Article> getTopByHeat(String period, Integer limit);

}