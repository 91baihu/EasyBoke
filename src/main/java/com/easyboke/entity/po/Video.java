package com.easyboke.entity.po;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.easyboke.entity.enums.DateTimePatternEnum;
import com.easyboke.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author 高98
 * @Description: 视频表
 * @date: 2026/05/10
 */
public class Video implements Serializable{

	/**
	 * 视频ID
	 */
	private Integer id;

	/**
	 * 视频标题
	 */
	private String title;

	/**
	 * 视频描述
	 */
	private String description;

	/**
	 * 视频地址
	 */
	private String videoUrl;

	/**
	 * 封面图片URL
	 */
	private String coverImage;

	/**
	 * 视频时长（秒）
	 */
	private Integer duration;

	/**
	 * 文件大小（字节）
	 */
	private Long fileSize;

	/**
	 * 分类ID
	 */
	private Integer categoryId;

	/**
	 * 状态：0-草稿，1-已发布，2-已删除
	 */
	private Integer status;

	/**
	 * 播放次数
	 */
	private Integer viewCount;

	/**
	 * 男性访问量
	 */
	private Integer maleViewCount;

	/**
	 * 女性访问量
	 */
	private Integer femaleViewCount;

	/**
	 * 未知性别访问量
	 */
	private Integer unknownViewCount;

	/**
	 * 18岁以下访问量
	 */
	private Integer ageUnder18;

	/**
	 * 18-25岁访问量
	 */
	private Integer age1825;

	/**
	 * 26-35岁访问量
	 */
	private Integer age2635;

	/**
	 * 36-45岁访问量
	 */
	private Integer age3645;

	/**
	 * 46岁以上访问量
	 */
	private Integer ageAbove45;

	/**
	 * 点赞数
	 */
	private Integer likeCount;

	/**
	 * 评论数
	 */
	private Integer commentCount;

	/**
	 * 发布时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date publishTime;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getVideoUrl() {
		return this.videoUrl;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public String getCoverImage() {
		return this.coverImage;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getDuration() {
		return this.duration;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public Long getFileSize() {
		return this.fileSize;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public Integer getViewCount() {
		return this.viewCount;
	}

	public void setMaleViewCount(Integer maleViewCount) {
		this.maleViewCount = maleViewCount;
	}

	public Integer getMaleViewCount() {
		return this.maleViewCount;
	}

	public void setFemaleViewCount(Integer femaleViewCount) {
		this.femaleViewCount = femaleViewCount;
	}

	public Integer getFemaleViewCount() {
		return this.femaleViewCount;
	}

	public void setUnknownViewCount(Integer unknownViewCount) {
		this.unknownViewCount = unknownViewCount;
	}

	public Integer getUnknownViewCount() {
		return this.unknownViewCount;
	}

	public void setAgeUnder18(Integer ageUnder18) {
		this.ageUnder18 = ageUnder18;
	}

	public Integer getAgeUnder18() {
		return this.ageUnder18;
	}

	public void setAge1825(Integer age1825) {
		this.age1825 = age1825;
	}

	public Integer getAge1825() {
		return this.age1825;
	}

	public void setAge2635(Integer age2635) {
		this.age2635 = age2635;
	}

	public Integer getAge2635() {
		return this.age2635;
	}

	public void setAge3645(Integer age3645) {
		this.age3645 = age3645;
	}

	public Integer getAge3645() {
		return this.age3645;
	}

	public void setAgeAbove45(Integer ageAbove45) {
		this.ageAbove45 = ageAbove45;
	}

	public Integer getAgeAbove45() {
		return this.ageAbove45;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getLikeCount() {
		return this.likeCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getCommentCount() {
		return this.commentCount;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Date getPublishTime() {
		return this.publishTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	@Override
	public String toString() {
		return "视频ID:" + (id == null ? "空" : id) + ",视频标题:" + (title == null ? "空" : title) + ",视频描述:" + (description == null ? "空" : description) + ",视频地址:" + (videoUrl == null ? "空" : videoUrl) + ",封面图片URL:" + (coverImage == null ? "空" : coverImage) + ",视频时长（秒）:" + (duration == null ? "空" : duration) + ",文件大小（字节）:" + (fileSize == null ? "空" : fileSize) + ",分类ID:" + (categoryId == null ? "空" : categoryId) + ",状态：0-草稿，1-已发布，2-已删除:" + (status == null ? "空" : status) + ",播放次数:" + (viewCount == null ? "空" : viewCount) + ",男性访问量:" + (maleViewCount == null ? "空" : maleViewCount) + ",女性访问量:" + (femaleViewCount == null ? "空" : femaleViewCount) + ",未知性别访问量:" + (unknownViewCount == null ? "空" : unknownViewCount) + ",18岁以下访问量:" + (ageUnder18 == null ? "空" : ageUnder18) + ",18-25岁访问量:" + (age1825 == null ? "空" : age1825) + ",26-35岁访问量:" + (age2635 == null ? "空" : age2635) + ",36-45岁访问量:" + (age3645 == null ? "空" : age3645) + ",46岁以上访问量:" + (ageAbove45 == null ? "空" : ageAbove45) + ",点赞数:" + (likeCount == null ? "空" : likeCount) + ",评论数:" + (commentCount == null ? "空" : commentCount) + ",发布时间:" + (publishTime == null ? "空" : DateUtils.format(publishTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())) + ",创建时间:" + (createTime == null ? "空" : DateUtils.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())) + ",更新时间:" + (updateTime == null ? "空" : DateUtils.format(updateTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}

}