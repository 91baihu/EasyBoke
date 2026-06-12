package com.easyboke.entity.query;


/**
 * @author 高98
 * @Description: 文章标签关联表查询对象
 * @date: 2026/05/10
 */
public class ArticleTagQuery extends BaseQuery{

	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 文章ID
	 */
	private Integer articleId;

	/**
	 * 标签ID
	 */
	private Integer tagId;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getArticleId() {
		return this.articleId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public Integer getTagId() {
		return this.tagId;
	}

}