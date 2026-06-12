package com.easyboke.entity.query;

import java.util.Date;

/**
 * @author 高98
 * @Description: 文章标签表查询对象
 * @date: 2026/05/10
 */
public class TagQuery extends BaseQuery{

	/**
	 * 标签ID
	 */
	private Integer id;

	/**
	 * 标签名称
	 */
	private String tagName;

	private String tagNameFuzzy;

	/**
	 * 使用次数
	 */
	private Integer useCount;

	/**
	 * 创建时间
	 */
	private Date createTime;

	private String createTimeStart;

	private String createTimeEnd;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagName() {
		return this.tagName;
	}

	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}

	public Integer getUseCount() {
		return this.useCount;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setTagNameFuzzy(String tagNameFuzzy) {
		this.tagNameFuzzy = tagNameFuzzy;
	}

	public String getTagNameFuzzy() {
		return this.tagNameFuzzy;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeStart() {
		return this.createTimeStart;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getCreateTimeEnd() {
		return this.createTimeEnd;
	}

}