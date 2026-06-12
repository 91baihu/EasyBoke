package com.easyboke.service.impl;

import com.easyboke.entity.po.ArticleTag;
import com.easyboke.entity.query.ArticleQuery;
import com.easyboke.entity.query.SimplePage;
import com.easyboke.entity.po.Article;
import com.easyboke.entity.vo.PaginationResultVO;
import com.easyboke.mappers.ArticleMapper;
import com.easyboke.mappers.ArticleTagMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Date;
import javax.annotation.Resource;
import com.easyboke.entity.enums.PageSize;
import com.easyboke.service.ArticleService;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService{

	@Resource
	private ArticleMapper<Article,ArticleQuery> articleMapper;
	@Resource
	private ArticleTagMapper<ArticleTag,ArticleQuery> articleTagMapper;

	@Override
	public List<Article>findListByParam(ArticleQuery query){
		return this.articleMapper.selectList(query);
	}

	@Override
	public Integer findCountByParam(ArticleQuery query){
		return this.articleMapper.selectCount(query);
	}

	@Override
	public PaginationResultVO<Article> findListByPage(ArticleQuery query ){
		Integer count = this.findCountByParam(query);
		Integer pageSize=query.getPageSize()==null? PageSize.SIZE15.getSize():query.getPageSize();
		SimplePage page=new SimplePage(query.getPageNo(),count,pageSize);
		query.setSimplePage(page);
		List<Article> list = this.findListByParam(query);
		PaginationResultVO<Article> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	@Override
	public Integer add(Article bean){
		return this.articleMapper.insert(bean);
	}

	@Override
	public Integer addBatch(List<Article> listBean){
		if(listBean==null || listBean.isEmpty()){
			return 0;
		}
		return this.articleMapper.insertBatch(listBean);
	}

	@Override
	public Integer addOrUpdate(Article article){
		return this.articleMapper.insertOrUpdate(article);
	}

	@Override
	public Integer addOrUpdateBatch(List<Article> listBean){
		if(listBean==null || listBean.isEmpty()){
			return 0;
		}
		return this.articleMapper.insertOrUpdateBatch(listBean);
	}

	@Override
	public Article getById(Integer id){
		return this.articleMapper.selectById(id);
	}

	@Override
	public Integer updateById(Article bean , Integer id){
		return this.articleMapper.updateById(bean,id);
	}

	@Override
	public Integer deleteById(Integer id){
		return this.articleMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer publishArticle(Article article, List<Integer> tagIds) {
		article.setStatus(1);
		article.setPublishTime(new Date());
		article.setCreateTime(new Date());
		article.setUpdateTime(new Date());
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

		this.articleMapper.insert(article);

		if (tagIds != null && !tagIds.isEmpty()) {
			for (Integer tagId : tagIds) {
				ArticleTag articleTag = new ArticleTag();
				articleTag.setArticleId(article.getId());
				articleTag.setTagId(tagId);
				this.articleTagMapper.insert(articleTag);
			}
		}

		return article.getId();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer updateArticle(Article article, List<Integer> tagIds) {
		article.setUpdateTime(new Date());
		this.articleMapper.updateById(article, article.getId());

		this.articleTagMapper.deleteByArticleId(article.getId());

		if (tagIds != null && !tagIds.isEmpty()) {
			for (Integer tagId : tagIds) {
				ArticleTag articleTag = new ArticleTag();
				articleTag.setArticleId(article.getId());
				articleTag.setTagId(tagId);
				this.articleTagMapper.insert(articleTag);
			}
		}

		return article.getId();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Article getArticleDetail(Integer id) {
		return getArticleDetail(id, null, 0, 0);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Article getArticleDetail(Integer id, Integer userId, Integer gender, Integer age) {
		Article article = this.articleMapper.selectById(id);
		if (article != null && article.getStatus() == 1) {
			this.articleMapper.incrementViewCount(id);

			if (gender != null && gender == 1) {
				this.articleMapper.incrementMaleViewCount(id);
			} else if (gender != null && gender == 2) {
				this.articleMapper.incrementFemaleViewCount(id);
			} else {
				this.articleMapper.incrementUnknownViewCount(id);
			}

			if (age != null && age > 0) {
				if (age < 18) {
					this.articleMapper.incrementAgeUnder18(id);
				} else if (age <= 25) {
					this.articleMapper.incrementAge1825(id);
				} else if (age <= 35) {
					this.articleMapper.incrementAge2635(id);
				} else if (age <= 45) {
					this.articleMapper.incrementAge3645(id);
				} else {
					this.articleMapper.incrementAgeAbove45(id);
				}
			}

			article.setViewCount(article.getViewCount() + 1);
		}
		return article;
	}

	@Override
	public List<Article> searchArticles(String keyword) {
		ArticleQuery query = new ArticleQuery();
		query.setTitleFuzzy(keyword);
		query.setContentFuzzy(keyword);
		query.setSummaryFuzzy(keyword);
		query.setStatus(1);
		query.setOrderBy("publish_time desc");
		return this.articleMapper.selectList(query);
	}

	@Override
	public List<Article> getArticlesByCategoryId(Integer categoryId, Integer status) {
		ArticleQuery query = new ArticleQuery();
		query.setCategoryId(categoryId);
		query.setStatus(status);
		query.setOrderBy("publish_time desc");
		return this.articleMapper.selectList(query);
	}

	@Override
	public List<Article> getArticlesByTagId(Integer tagId, Integer status) {
		return this.articleMapper.selectListByTagId(tagId, status);
	}

	@Override
	public List<Article> getLatestArticles(Integer limit, Integer status) {
		ArticleQuery query = new ArticleQuery();
		query.setOrderBy("publish_time desc");
		SimplePage page = new SimplePage(1, limit, limit);
		query.setSimplePage(page);
		return this.articleMapper.selectList(query);
	}

	@Override
	public List<Article> getHotArticles(Integer limit, Integer status) {
		ArticleQuery query = new ArticleQuery();
		query.setStatus(status);
		query.setOrderBy("view_count desc");
		SimplePage page = new SimplePage(1, limit, limit);
		query.setSimplePage(page);
		return this.articleMapper.selectList(query);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean likeArticle(Integer articleId, Integer userId) throws com.easyboke.exception.BusinessException {
		if (userId == null || userId <= 0) {
			throw new com.easyboke.exception.BusinessException("请先登录");
		}

		Article article = this.articleMapper.selectById(articleId);
		if (article == null) {
			throw new com.easyboke.exception.BusinessException("文章不存在");
		}

		if (article.getStatus() != 1) {
			throw new com.easyboke.exception.BusinessException("文章未发布");
		}

		String likeKey = "article_like_" + articleId + "_" + userId;
		Object existingLike = com.easyboke.common.Constants.LIKE_CACHE.get(likeKey);

		if (existingLike != null) {
			throw new com.easyboke.exception.BusinessException("已经点赞过");
		}

		this.articleMapper.incrementLikeCount(articleId);

		com.easyboke.common.Constants.LIKE_CACHE.put(likeKey, System.currentTimeMillis());

		return true;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean unlikeArticle(Integer articleId, Integer userId) throws com.easyboke.exception.BusinessException {
		if (userId == null || userId <= 0) {
			throw new com.easyboke.exception.BusinessException("请先登录");
		}

		Article article = this.articleMapper.selectById(articleId);
		if (article == null) {
			throw new com.easyboke.exception.BusinessException("文章不存在");
		}

		String likeKey = "article_like_" + articleId + "_" + userId;
		Object existingLike = com.easyboke.common.Constants.LIKE_CACHE.get(likeKey);

		if (existingLike == null) {
			throw new com.easyboke.exception.BusinessException("尚未点赞");
		}

		this.articleMapper.decrementLikeCount(articleId);

		com.easyboke.common.Constants.LIKE_CACHE.remove(likeKey);

		return true;
	}

	@Override
	public Boolean isLiked(Integer articleId, Integer userId) {
		if (userId == null || userId <= 0) {
			return false;
		}

		String likeKey = "article_like_" + articleId + "_" + userId;
		return com.easyboke.common.Constants.LIKE_CACHE.get(likeKey) != null;
	}

	@Override
	public List<Article> getTopByHeat(String period, Integer limit) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime = null;
		String endTime = sdf.format(new Date());

		java.util.Calendar cal = java.util.Calendar.getInstance();
		if ("day".equals(period)) {
			cal.set(java.util.Calendar.HOUR_OF_DAY, 0);
			cal.set(java.util.Calendar.MINUTE, 0);
			cal.set(java.util.Calendar.SECOND, 0);
			cal.set(java.util.Calendar.MILLISECOND, 0);
			startTime = sdf.format(cal.getTime());
		} else if ("week".equals(period)) {
			cal.add(java.util.Calendar.DAY_OF_WEEK, -7);
			startTime = sdf.format(cal.getTime());
		} else if ("month".equals(period)) {
			cal.add(java.util.Calendar.MONTH, -1);
			startTime = sdf.format(cal.getTime());
		}
		// "all" — 不设时间筛选

		if (limit == null || limit <= 0) {
			limit = 10;
		}
		return this.articleMapper.selectTopByHeat(startTime, endTime, limit);
	}

}
