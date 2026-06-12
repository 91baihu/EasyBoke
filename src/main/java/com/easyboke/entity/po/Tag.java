package com.easyboke.entity.po;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.easyboke.entity.enums.DateTimePatternEnum;
import com.easyboke.utils.DateUtils;

/**
 * @author 高98
 * @Description: 文章标签表
 * @date: 2026/05/10
 */
public class Tag implements Serializable{

	/**
	 * 标签ID
	 */
	private Integer id;

	/**
	 * 标签名称
	 */
	private String tagName;

	/**
	 * 使用次数
	 */
	private Integer useCount;

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

	@Override
	public String toString() {
		return "标签ID:" + (id == null ? "空" : id) + ",标签名称:" + (tagName == null ? "空" : tagName) + ",使用次数:" + (useCount == null ? "空" : useCount) + ",创建时间:" + (createTime == null ? "空" : DateUtils.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}

}