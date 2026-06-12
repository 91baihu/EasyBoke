package com.easyboke.entity.query;

import java.util.Date;

/**
 * @author 高98
 * @Description: 文章表查询对象
 * @date: 2026/05/10
 */
public class ArticleQuery extends BaseQuery{

	/**
	 * 文章ID
	 */
	private Integer id;

	/**
	 * 文章标题
	 */
	private String title;

	private String titleFuzzy;

	/**
	 * 文章内容
	 */
	private String content;

	private String contentFuzzy;

	/**
	 * 文章摘要
	 */
	private String summary;

	private String summaryFuzzy;

	/**
	 * 封面图片URL
	 */
	private String coverImage;

	private String coverImageFuzzy;

	/**
	 * 分类ID
	 */
	private Integer categoryId;

	/**
	 * 状态：0-草稿，1-已发布，2-已删除
	 */
	private Integer status;

	/**
	 * 浏览次数
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
	private Date publishTime;

	private String publishTimeStart;

	private String publishTimeEnd;

	/**
	 * 创建时间
	 */
	private Date createTime;

	private String createTimeStart;

	private String createTimeEnd;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	private String updateTimeStart;

	private String updateTimeEnd;

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

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return this.content;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public String getCoverImage() {
		return this.coverImage;
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

	public void setTitleFuzzy(String titleFuzzy) {
		this.titleFuzzy = titleFuzzy;
	}

	public String getTitleFuzzy() {
		return this.titleFuzzy;
	}

	public void setContentFuzzy(String contentFuzzy) {
		this.contentFuzzy = contentFuzzy;
	}

	public String getContentFuzzy() {
		return this.contentFuzzy;
	}

	public void setSummaryFuzzy(String summaryFuzzy) {
		this.summaryFuzzy = summaryFuzzy;
	}

	public String getSummaryFuzzy() {
		return this.summaryFuzzy;
	}

	public void setCoverImageFuzzy(String coverImageFuzzy) {
		this.coverImageFuzzy = coverImageFuzzy;
	}

	public String getCoverImageFuzzy() {
		return this.coverImageFuzzy;
	}

	public void setPublishTimeStart(String publishTimeStart) {
		this.publishTimeStart = publishTimeStart;
	}

	public String getPublishTimeStart() {
		return this.publishTimeStart;
	}

	public void setPublishTimeEnd(String publishTimeEnd) {
		this.publishTimeEnd = publishTimeEnd;
	}

	public String getPublishTimeEnd() {
		return this.publishTimeEnd;
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

	public void setUpdateTimeStart(String updateTimeStart) {
		this.updateTimeStart = updateTimeStart;
	}

	public String getUpdateTimeStart() {
		return this.updateTimeStart;
	}

	public void setUpdateTimeEnd(String updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}

	public String getUpdateTimeEnd() {
		return this.updateTimeEnd;
	}

}