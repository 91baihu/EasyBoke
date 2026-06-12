package com.easyboke.entity.po;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.easyboke.entity.enums.DateTimePatternEnum;
import com.easyboke.utils.DateUtils;

/**
 * @author 高98
 * @Description: 文章分类表
 * @date: 2026/05/10
 */
public class Category implements Serializable{

	/**
	 * 分类ID
	 */
	private Integer id;

	/**
	 * 分类名称
	 */
	private String categoryName;

	/**
	 * 排序
	 */
	private Integer sortOrder;

	/**
	 * 分类描述
	 */
	private String description;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	@Override
	public String toString() {
		return "分类ID:" + (id == null ? "空" : id) + ",分类名称:" + (categoryName == null ? "空" : categoryName) + ",排序:" + (sortOrder == null ? "空" : sortOrder) + ",分类描述:" + (description == null ? "空" : description) + ",创建时间:" + (createTime == null ? "空" : DateUtils.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}

}