package com.easyboke.entity.po;

import java.io.Serializable;

/**
 * @author 高98
 * @Description: 文章标签关联表
 * @date: 2026/05/10
 */
public class ArticleTag implements Serializable{

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

	@Override
	public String toString() {
		return "ID:" + (id == null ? "空" : id) + ",文章ID:" + (articleId == null ? "空" : articleId) + ",标签ID:" + (tagId == null ? "空" : tagId);
	}

}